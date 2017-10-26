$(document).ready(function () {

    let dataSource;
    let currentCategory;
    loadTypes();

    function loadTypes() {
        $.get(projectPathPrefix + "/api/getTypes", function (data) {
            dataSource = data;
            buildTable(data);
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
                "orderable": false,
                "render": function (data, type, row, meta) {
                    return buildActionButtons(row, meta.col);
                }
            },
                {
                    "targets": 0,
                    "orderable": false
                }],
            "order": [[2, 'asc']]
        });

        table.on('order.dt', function () {
            table.column(0, {order: 'applied'}).nodes().each(function (cell, i) {
                cell.innerHTML = i + 1;
            });
        }).draw();

        $('#categories').change(function (e) {
            buildTypesSelect(e);
        });

        $('#admins').change(function () {
            let selected = $(this).val();
            if (selected === 'all') selected = '';
            table
                .columns(3)
                .search(selected)
                .draw();
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

    function findNestedCategories(categoryName) {
        let categoryItem = $("li[data-name='" + categoryName + "']");
        let nextCategoryItems = categoryItem.nextAll('.category-item');
        let level = categoryItem.attr('data-level');
        let nestedCategories = [categoryItem];
        $.each(nextCategoryItems, function (i, item) {
            if ($(item).attr('data-level') != level) {
                nestedCategories.push($(item));
            }
            return ( $(item).attr('data-level') != level );
        });
        return nestedCategories;
    }

    function findAllCategories() {
        let allCategories = $('.category-item');
        let allCategoriesList = [];
        $.each(allCategories, function (i, item) {
            allCategoriesList.push($(item));
        });
        return allCategoriesList;
    }

    function buildTypesSelect(e) {
        let selectedCategoryName = $(e.target).data('selectedName');
        let nestedCategories = selectedCategoryName === 'all' ? findAllCategories()
            : findNestedCategories(selectedCategoryName);
        if (currentCategory !== selectedCategoryName) {
            let typesItems = $('<div></div>');
            $('#types').html('');
            $.each(dataSource, function (i, item) {
                if (nestedCategories.some(function (e) {
                        return e.attr('data-name') === item['categoryName']
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
            currentCategory = selectedCategoryName;
        }
    }
});