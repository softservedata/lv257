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

    // activate Nestable for list 1
    $('#nestable').nestable({
        json: inputJson,
        contentCallback: function(item) {
            var content = item.catname || '' ? item.catname : item.id;
            content += ' <i>(id = ' + item.id + ')</i>';
            lastId = item.id;

            return content;
        }
    }).on('change', updateOutput);

    // output initial serialised data
    updateOutput($('#nestable').data('output', $('#nestable-output')));

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
                "catname": "new category",
//                    "parent_id" : 1516,
                "content": "Item " + lastId
            };
            $('#nestable').nestable('add', newItem);
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        }
        if(action === 'remove-item') {
            var branch2_id = $("[data-catname='branch2']").attr("data-id");
            $('#nestable').nestable('remove', branch2_id);
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        }
    });
})();

$('#save-json').on('click', function (e) {
    e.preventDefault();
    var json = $('#nestable-output').val();
    $.ajax({
        type: "POST",
        contentType: "text/plain",
        url: "/resources/manageTypes",
        accept: "text/plain",
        data: json,
        success: function (result) {
            alert("JSON has been uploaded!")
        },
        error: function(jqXHR, textStatus, errorThrown) {
            alert("jqXHR: " + jqXHR.status + " Status: " + textStatus + " Error: " + errorThrown);
        }
    })
});