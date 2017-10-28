$(document).ready(function () {

    let dataSource;
    let categoriesFilterCash = findAllCategoriesNames();
    let typesFilterCash = 'all';
    let adminsFilterCash = 'all';
    loadData();

    function loadData() {
        $.get(projectPathPrefix + "/api/getTypes", function (data) {
            dataSource = data;
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
            ],
            "columnDefs": [{
                "targets": [-1, -2, -3, -4],
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

        table.on( 'order.dt search.dt', function () {
            table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
                cell.innerHTML = i+1;
            } );
        } ).draw();

        $('#categories').change(function (e) {
            $.fn.dataTable.ext.search.pop();
            let selectedCategoryName = $(e.target).data('selectedName');
            let categories = selectedCategoryName === 'all' ? findAllCategoriesNames()
                : findNestedCategoriesNames(selectedCategoryName);
            buildTypesSelect(categories);
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
    }

    function buildActionButtons(rowData, col) {
        let restrictAccess = rowData.administratorName !== currentAdmin ? 'disabled"' : '"';
        let button;
        switch (col) {
            case 4:
                button = rowData.instantiated ? '' : '<button class="btn btn-primary btn-xs ' + restrictAccess
                    + 'data-target=' + rowData.typeId + '>Instantiate</button>';
                break;
            case 5:
                button = '<a style="padding-top: 2px" href="#" class="btn btn-link" data-target='
                    + rowData.typeId + '><span class="glyphicon glyphicon-plus-sign"</span></a>';
                break;
            case 6:
                button = rowData.instantiated ? '' : '<a style="padding-top: 2px" href="#" class="btn btn-link ' + restrictAccess
                    + 'data-target=' + rowData.typeId + '><span class="glyphicon glyphicon-pencil"</span></a>';
                break;
            case 7:
                button = rowData.instantiated ? '' : '<a style="padding-top: 2px" href="#" class="btn btn-link ' + restrictAccess
                    + 'data-target=' + rowData.typeId + '><span class="glyphicon glyphicon-remove"</span></a>';
                break;
        }
        /*            let instantiateButton = rowData.instantiated ? '' : '<button class="btn btn-primary btn-sm ' + restrictAccess
                        + 'data-target=' + rowData.typeId + '>Instantiate</button>';
                    let editButton = '<a href="#" class="btn btn-primary btn-sm ' + restrictAccess
                        + 'data-target=' + rowData.typeId + '>Edit</a>';
                    let cloneButton = '<a href="#" class="btn btn-primary btn-sm" data-target=' + rowData.typeId + '>Clone</a>';
                    let deleteButton = '<a href="#" class="btn btn-primary btn-sm ' + restrictAccess
                        + 'data-target=' + rowData.typeId + '>Delete</a>';
                    return '<div class="btn-toolbar" style="display: inline-block;">' + instantiateButton + editButton + cloneButton + deleteButton + '</div>';*/
        return button;
    }

    function findNestedCategoriesNames(categoryName) {
        let categoryItem = $("li[data-name='" + categoryName + "']");
        let nextCategoryItems = categoryItem.nextAll('.category-item');
        let level = categoryItem.attr('data-level');
        let nestedCategories = [categoryItem];
        $.each(nextCategoryItems, function (i, item) {
            if ($(item).attr('data-level') != level) {
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

    function buildTypesSelect(categories) {
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
});