$(document).ready(function () {

    let categoriesFilterCash = findAllCategoriesNames();
    let typesFilterCash = 'all';
    let adminsFilterCash = 'all';
    loadData();

    function loadData() {
        $.get(projectPathPrefix + "/api/getTypes", function (data) {
            buildTable(data);
            $('#all-categories a').click();
        }, "json");
    }

    function buildTable(dataSource) {
        let table = $('#types-table').DataTable({
            "dom": 'rt<"bottom"lp><"clear">',
            "language": {
                "zeroRecords": "Nothing found - sorry",
                "infoEmpty": "No records available",
            },
            "data": dataSource,
            "columns": [
                {"data": null},
                {"data": "categoryName"},
                {"data": "typeName"},
                {"data": "administratorName"},
                {"data": null},
                {"data": null},
                {"data": null},
                {"data": null},
                {"data": null},
            ],
            "columnDefs": [{
                "targets": [4, 5, 6, 7, 8],
                "searchable": false,
                "orderable": false,
                "render": function (data, type, row, meta) {
                    return buildActionButtons(row, meta.col);
                }
            },
                {
                    "targets": 0,
                    "searchable": false,
                    "orderable": false,
                }],
            "order": [[2, 'asc']]
        });

        table.on('order.dt search.dt', function () {
            table.column(0, {search: 'applied', order: 'applied'}).nodes().each(function (cell, i) {
                cell.innerHTML = i + 1;
            });
        }).draw();

        $('#categories').change(function (e) {
            $.fn.dataTable.ext.search.pop();
            let selectedCategoryName = $(e.target).data('selectedName');
            let categories = selectedCategoryName === 'all' ? findAllCategoriesNames()
                : findNestedCategoriesNames(selectedCategoryName);
            buildTypesSelect(categories, dataSource);
            $.fn.dataTable.ext.search.push(
                function (settings, searchData) {
                    let category = searchData[1];
                    let admin = searchData[3];
                    return categories.some(function (c) {
                        return c === category
                    }) && (adminsFilterCash === admin || adminsFilterCash === 'all');
                }
            );
            table.draw();
            categoriesFilterCash = categories;
        });

        $('#types').change(function () {
            $.fn.dataTable.ext.search.pop();
            let selectedTypeName = $(this).val();
            $.fn.dataTable.ext.search.push(
                function (settings, searchData) {
                    let category = searchData[1];
                    let type = searchData[2];
                    let admin = searchData[3];
                    return categoriesFilterCash.some(function (c) {
                            return c === category
                        }) && (selectedTypeName === type || selectedTypeName === 'all')
                        && (adminsFilterCash === admin || adminsFilterCash === 'all');
                });
            table.draw();
            typesFilterCash = selectedTypeName;
        });

        $('#admins').change(function () {
            $.fn.dataTable.ext.search.pop();
            let selectedAdminName = $(this).val();
            $.fn.dataTable.ext.search.push(
                function (settings, searchData) {
                    let category = searchData[1];
                    let type = searchData[2];
                    let admin = searchData[3];
                    return categoriesFilterCash.some(function (c) {
                            return c === category
                        }) && (typesFilterCash === type || typesFilterCash === 'all')
                        && (selectedAdminName === admin || selectedAdminName === 'all');
                });
            table.draw();
            adminsFilterCash = selectedAdminName;
        });

        $.each($('.inst-button'), function (i, item) {
            $(item).click(function () {
                if (!$(this).hasClass('disabled')) {
                    $('#confirm-dialog').modal('show');
                    $('#confirm-dialog').data('action', 'instantiate');
                    $('#confirm-dialog').data('id', $(item).attr('data-id'));
                    $('#confirm-title').text('Confirm instantiation of resource type');
                    $('#confirm-body').text('Are you sure you want to instantiate resource type "'
                        + $(item).attr('data-type') + '"?');
                }
            })
        });

        $.each($('.remove-button'), function (i, item) {
            $(item).click(function () {
                if (!$(this).hasClass('disabled')) {
                    $('#confirm-dialog').modal('show');
                    $('#confirm-dialog').data('action', 'remove');
                    $('#confirm-dialog').data('id', $(item).attr('data-id'));
                    $('#confirm-title').text('Confirm removing of resource type');
                    $('#confirm-body').text('Are you sure you want to remove resource type "'
                        + $(item).attr('data-type') + '"?');
                }
            })
        });

        $('#confirm-button').click(function () {
            let id = $('#confirm-dialog').data('id');
            if ($('#confirm-dialog').data('action') === 'instantiate') {
                instantiateType(id);
            } else if ($('#confirm-dialog').data('action') === 'remove') {
                removeType(id, table);
            }
            $('#confirm-dialog').modal('hide');
        });
    }

    function buildActionButtons(rowData, col) {
        let restrictAccess = rowData.administratorName !== currentAdmin ? 'disabled"' : '"';
        let cloneLink = projectPathPrefix + '/resources/cloneType?id=' + -rowData.typeId;
        let editLink = projectPathPrefix + '/resources/editType?id=' + rowData.typeId;
        let button;
        switch (col) {
            case 4:
                button = rowData.instantiated ? '' : '<button class="btn btn-primary btn-xs inst-button '
                    + restrictAccess + 'data-id=' + rowData.typeId + ' data-type=' + rowData.typeName
                    + '>Instantiate</button>';
                break;
            case 5:
                button = '<a style="padding-top: 2px" target="_blank" href="' + cloneLink
                    + '" class="btn btn-link" data-id=' + rowData.typeId
                    + '><span class="glyphicon glyphicon-plus-sign"</span></a>';
                break;
            case 6:
                button = rowData.instantiated ? '' : '<a style="padding-top: 2px" target="_blank" href="'
                    + editLink + '" class="btn btn-link edit-button ' + restrictAccess + 'data-id='
                    + rowData.typeId + '><span class="glyphicon glyphicon-pencil"</span></a>';
                break;
            case 7:
                button = rowData.instantiated ? '' : '<button style="padding-top: 2px" class="btn btn-link remove-button '
                    + restrictAccess + 'data-id=' + rowData.typeId + ' data-type=' + rowData.typeName
                    + '><span class="glyphicon glyphicon-remove"</span></button>';
                break;
            case 8:
                button = '<button style="padding-top: 2px"' + ' class="btn btn-link info-button" data-id='
                    + rowData.typeId + '><span class="glyphicon glyphicon-eye-open"</span></button>';
                break;
        }
        return button;
    }

    function findNestedCategoriesNames(categoryName) {
        let categoryItem = $("li[data-name='" + categoryName + "']");
        let nextCategoryItems = categoryItem.nextAll('.category-item');
        let level = categoryItem.attr('data-level');
        let nestedCategories = [categoryItem.attr('data-name')];
        $.each(nextCategoryItems, function (i, item) {
            if ($(item).attr('data-level') > level) {
                nestedCategories.push($(item).attr('data-name'));
            }
            return ( $(item).attr('data-level') != level );
        });
        return nestedCategories;
    }

    function findAllCategoriesNames() {
        let allCategories = $('.category-item');
        let allCategoriesList = [];
        $.each(allCategories, function (i, item) {
            allCategoriesList.push($(item).attr('data-name'));
        });
        return allCategoriesList;
    }

    function buildTypesSelect(categories, dataSource) {
        let typesItems = $('<div></div>');
        $('#types').html('');
        $.each(dataSource, function (i, item) {
            if (categories.some(function (c) {
                    return c === item['categoryName']
                })) {
                $('<option/>', {
                    value: item['typeName'],
                    text: item['typeName'],
                }).appendTo(typesItems);
            }
        });
        if (typesItems.children().length > 0) {
            $('#types').html('<option value="all">All resource types</option>');
            $('#types').append(typesItems.children());
        }
        $('#types').selectpicker('refresh');
        typesFilterCash = 'all';
    }

    function instantiateType(id) {
        console.log('Instantiating...');
        $('button[data-id=' + id + ']').remove();
        $('a.edit-button').filter(function () {
            return $(this).attr('data-id') === id;
        }).remove();
    }

    function removeType(id, table) {
        console.log('Removing...');
        let row = $('button[data-id=' + id + ']').parents('tr');
        table
            .row(row)
            .remove()
            .draw();
    }
});