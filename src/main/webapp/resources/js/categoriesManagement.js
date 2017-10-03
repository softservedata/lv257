(function () {
    var lastId;

    $('#categories-manager').on('click', function (e) {
        e.preventDefault();
        buildCategoriesTree();
    });

    $('#save-changes').on('click', function (e) {
        e.preventDefault();
        saveChanges();
    });

    /**
     * Discard changes and close modal window with categories managing
     */
    $('#cancel-managing, #close-managing').on('click', function (e) {
        e.preventDefault();
        $('#nestable').nestable('destroy');
    });

    /**
     * Actions of menu buttons
     */
    $('#nestable-menu').on('click', function (e) {
        var target = $(e.target),
            action = target.data('action');
        if (action === 'expand-all') {
            $('.dd').nestable('expandAll');
        }
        if (action === 'collapse-all') {
            $('.dd').nestable('collapseAll');
        }
        if (action === 'add-item') {
            var newItem = {
//                    "id": ++lastId,
                "categoryname": "new category",
//                    "parent_id" : 1516,
                "content": "Item " + lastId
            };
            $('#nestable').nestable('add', newItem);
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        }
        if (action === 'remove-item') {
            var branch2_id = $("[data-categoryname='branch2']").attr("data-id");
            $('#nestable').nestable('remove', branch2_id);
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        }
    });

    /**
     * Activate Nestable and build categories tree
     */
    function buildCategoriesTree() {
        var jqxhr = $.getJSON("/resources/manageCategories")
            .success(function (data) {
                data = sortNestedComponents(data, 'categoryname', 'asc');
                var json = JSON.stringify(data);
                // activate Nestable
                $('#nestable').nestable({
                    json: json,
                    contentCallback: function (item) {
                        var content = item.categoryname || '' ? item.categoryname : item.id;
                        content += ' <i>(id = ' + item.id + ')</i>';
                        lastId = item.id;
                        return content;
                    }
                }).on('change', updateOutput);

                // output initial serialised data
                updateOutput($('#nestable').data('output', $('#nestable-output')));
            })
            .error(function () {
                alert("Error " + jqxhr.status);
            });
    }

    /**
     * Update output data which produces Nestable plugin after each change
     */
    var updateOutput = function (e) {
        var list = e.length ? e : $(e.target),
            output = list.data('output');
        if (window.JSON) {
            output.val(window.JSON.stringify(list.nestable('serialize')));
        }
        else {
            output.val('JSON browser support required for this demo.');
        }
        console.log(window.JSON.stringify(list.nestable('serialize')));
    };

    /**
     * Save changes after managing categories
     */
    function saveChanges() {
        var json = $('#nestable-output').val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/resources/manageCategories",
            accept: "application/json",
            data: json,
            success: function (result) {
                alert("Changes are saved!")
                $('#close-managing').click();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("jqXHR: " + jqXHR.status + " Status: " + textStatus + " Error: " + errorThrown);
            }
        })
    };

    /**
     * Sort array of objects by some object key
     * @param data - array of objects
     * @param key - object key for sorting
     * @param way - ascending or descending order
     * @returns {Array.<T>} - array of sorted objects
     */
    function sortComponents(data, key, way) {
        return data.sort(function (a, b) {
            var x = a[key];
            var y = b[key];
            if (way === 'asc') {
                return ((x < y) ? -1 : ((x > y) ? 1 : 0));
            }
            if (way === 'desc') {
                return ((x > y) ? -1 : ((x < y) ? 1 : 0));
            }
        });
    }

    /**
     * Sort array of objects which may have nested objects by some object key
     * @param data - array of objects
     * @param key - object key for sorting
     * @param way - ascending or descending order
     * @returns {Array.<T>} - array of sorted objects
     */
    function sortNestedComponents(data, key, way) {
        data = data.sort(function (a, b) {
            var x = a[key];
            var y = b[key];
            if (way === 'asc') {
                return ((x < y) ? -1 : ((x > y) ? 1 : 0));
            }
            if (way === 'desc') {
                return ((x > y) ? -1 : ((x < y) ? 1 : 0));
            }
        });
        for (var i = 0; i < data.length; i++) {
            if (data[i].children && data[i].children.length > 0) {
                sortNestedComponents(data[i].children, key, way);
            }
        }
        return data;
    }

    /**
     *
     * @param categories - JSON with categories and types
     * @param level - depth of hierarchy
     * @constructor
     */
    function ParseCategoriesWithTypes(categories, level = 1) {
        for (var i = 0; i < categories.length; i++) {
            categories = sortComponents(categories, 'categoryname', 'asc');
            $('<li/>', {
                'data-value': categories[i].categoryname,
                'data-level': level,
                'class': 'disabled category',
                text: categories[i].categoryname + " Level: " + level,
                appendTo: $('#categories_and_types')
            });
            var restypes = eval(categories[i].restypes);
            if (restypes) {
                restypes = sortComponents(restypes, 'typename', 'asc');
                for (var j = 0; j < restypes.length; j++) {
                    $('<li/>', {
                        'data-value': restypes[j].typename,
                        'data-level': level,
                        text: restypes[j].typename + " Level: " + (level + 1),
                        appendTo: $('#categories_and_types')
                    });
                }
            }
            if (categories[i].children && categories[i].children.length > 0)
                ParseCategoriesWithTypes(categories[i].children, level + 1);
        }
    }
})();


