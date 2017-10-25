$(document).ready(function () {

    loadTypes();

    function loadTypes() {
        $.get(projectPathPrefix + "/api/getTypes", function (data) {
            buildTable(data);
        }, "json");
    }

    function buildTable(dataSource) {
        let table = $('#types-table').DataTable({
            "dom": 'rt<"bottom"lp><"clear">',
            stateSave: true,
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
            ],
            "columnDefs": [{
                "targets": [-1, -2, -3],
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

        function buildActionButtons(rowData, col) {
            let restrictAccess = rowData.administratorName !== currentAdmin ? 'disabled"' : '"';
            let button;
            switch (col) {
                case 4:
                    button = rowData.instantiated ? '' : '<button class="btn btn-primary btn-sm ' + restrictAccess
                        + 'data-target=' + rowData.typeId + '>Instantiate</button>';
                    break;
                case 5:
                    button = rowData.instantiated ? '<a href="#" class="btn btn-default btn-link btn-primary-outline" data-target='
                        + rowData.typeId + '><span class="glyphicon glyphicon-plus-sign"</span></a>'
                        : '<a href="#" class="btn btn-default btn-link btn-primary-outline ' + restrictAccess
                        + 'data-target=' + rowData.typeId + '><span class="glyphicon glyphicon-pencil"</span></a>';
                    break;
                case 6:
                    button = rowData.instantiated ? '' : '<a href="#" class="btn btn-default btn-link btn-primary-outline ' + restrictAccess
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
    }
});