$(function () {

    var table = $('#newRequest').DataTable({
        'dom': 'rt<"bottom"lp><"clear">',
        stateSave: true
    });

    $( ".fixed-table-container" ).remove();
    $( ".fixed-table-body" ).remove();

    $(document).ready(function() {
        $('#newRequest').DataTable();
    } );
    $(document).ready(function() {
        $('#declinedRequest').DataTable();
    } );
    $(document).ready(function() {
        $('#acceptedRequest').DataTable();
    } );
    $(document).ready(function() {
        $('#refinementRequest').DataTable();
    } );

    $('.delete').on('click', function(event) {
        var cell = $(this).parents('td');
        var id = cell.attr('data-id');

        $(this).parent().parent().remove();

        $.ajax(
            {
                type: "POST",
                url: projectPathPrefix + "/resources" + "/changeStatus",
                accept: "application/json",
                data: {id: id},

                success: function (responceRequest) {

                    var Info_about_request = $('selector').attr('href');
                    $('selector').attr('href','${pageContext.request.contextPath}/resources/info/${responceRequest.id}');

                    table.cell(cell.closest('tr'), 0).data(responceRequest.resourceName);
                    table.cell(cell.closest('tr'), 1).data(responceRequest.assignerName);
                    table.cell(cell.closest('tr'), 2).data(Info_about_request);
                    table.cell(cell.closest('tr'), 3).data(responceRequest.update);

                    table.destroy();

                    $(document).ready(function() {
                        $('#newRequest').DataTable();
                    } );

                    // table = $('#newRequest').DataTable({
                    //     'dom': 'rt<"bottom"lp><"clear">',
                    //     stateSave: true
                    // });

                    table.order([[5, 'asc'], [3, 'desc']]).draw();

                    // $('#newRequest > tbody:first').append('<tr><td>responceRequest.resourceType</td><td>responceRequest.assignerName</td><td><a href="${pageContext.request.contextPath}/resources/info/${responceRequest.id}">Info about request</a></td><td>responceRequest.update</td></tr>');
                }
            })
    });

})