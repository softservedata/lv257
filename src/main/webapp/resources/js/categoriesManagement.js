(function () {
    var updateOutput = function(e) {
        var list = e.length ? e : $(e.target),
                output = list.data('output');
        if(window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));
        }
        else {
            output.val('JSON browser support required for this demo.');
        }
        console.log(window.JSON.stringify(list.nestable('serialize')));
    };

    var lastId;

    $('#categories-manager').on('click', function (e) {
        e.preventDefault();

        var jqxhr = $.getJSON("/resources/manageCategories")
            .success(function(data) {
                var json = JSON.stringify(data);
                // activate Nestable for list
                $('#nestable').nestable({
                    json: json,
                    contentCallback: function(item) {
                        var content = item.categoryname || '' ? item.categoryname : item.id;
                        content += ' <i>(id = ' + item.id + ')</i>';
                        lastId = item.id;

                        return content;
                    }
                }).on('change', updateOutput);

                // output initial serialised data
                updateOutput($('#nestable').data('output', $('#nestable-output')));
            })
            .error(function() { alert("Error " + jqxhr.status ); });
    });

    $('#nestable-menu').on('click', function (e) {
        var target = $(e.target),
                action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
        if(action === 'add-item') {
            var newItem = {
//                    "id": ++lastId,
                "categoryname": "new category",
//                    "parent_id" : 1516,
                "content": "Item " + lastId
            };
            $('#nestable').nestable('add', newItem);
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        }
        if(action === 'remove-item') {
            var branch2_id = $("[data-categoryname='branch2']").attr("data-id");
            $('#nestable').nestable('remove', branch2_id);
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        }
    });

    $('#cancel-managing, #close-managing').on('click', function (e) {
        $('#nestable').nestable('destroy');
    })
})();

$('#save-json').on('click', function (e) {
    e.preventDefault();
    var json = $('#nestable-output').val();
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/resources/manageCategories",
        accept: "application/json",
        data: json,
        success: function (result) {
            alert("JSON has been uploaded!")
            $('#close-managing').click();
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("jqXHR: " + jqXHR.status + " Status: " + textStatus + " Error: " + errorThrown);
        }
    })
});

