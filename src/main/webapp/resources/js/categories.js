/**
 * Calling JSP should define variable {showTypesInCategoryHierarchy = true}
 * to allow displaying resource types in select
 * and variable {disableAncestorSelecting = false} to disallow selecting of  intermediate categories
 */

(function () {
    var lastId;
    var jsonForSelect;

    //Enable categories selectlist
    loadCategories();

    $('#manage-categories').on('click', function (e) {
        e.preventDefault();
        buildCategoriesTree();
    });

    $('#save-changes').on('click', function (e) {
        e.preventDefault();
        saveChangesAfterManaging();
    });

     //Discard changes and close modal window with categories managing
    $('#cancel-managing, #close-managing').on('click', function (e) {
        e.preventDefault();
        $('#nestable').nestable('destroy');
    });

     //Actions of menu buttons
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
                "categoryname"  : "new category",
//                    "`nt_id" : 1516,
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
     * Enable categories selectlist
     */
    function loadCategories() {
        var includeTypes = (typeof showTypesInCategoryHierarchy == 'undefined') ? false : showTypesInCategoryHierarchy;
        var suppressParents = (typeof disableAncestorSelecting == 'undefined') ? true : disableAncestorSelecting;
        let urlSuffix = includeTypes ? 'categorizedTypes' : 'categories';
        $.get("/resources/" + urlSuffix, function (data) {
            jsonForSelect = sortNestedComponents(data, 'categoryname', 'asc');
            console.log(JSON.stringify(jsonForSelect));
            BuildCategoriesSelect(jsonForSelect, includeTypes, suppressParents);
            addHandlers();
            $('#categories-select').hierarchySelect({
                width: '100%',
                height: '208px',
                hierarchy: includeTypes,
                search: true
            });
        }, "json");
    }

    /**
     * Activate Nestable and build categories tree
     */
    function buildCategoriesTree() {
        var jqxhr = $.getJSON("/resources/categories")
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
    function saveChangesAfterManaging() {
        var json = $('#nestable-output').val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/resources/manageCategories",
            accept: "application/json",
            data: json,
            success: function (result) {
                alert("Changes are saved!");
                jsonForSelect = json;
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
     * @param showTypes - include resource types in select
     * @param suppressParentCategories - allow only leaf categories in select
     * @param level - depth of hierarchy
     * @constructor
     */
    function BuildCategoriesSelect(categories, showTypes, suppressParentCategories, level = 1) {
        for (var i = 0; i < categories.length; i++) {
            categories = sortComponents(categories, 'categoryname', 'asc');
            let classPrefix = (showTypes || (suppressParentCategories && categories[i].children)) ? 'disabled' : '';
            let s = $('<li/>', {
                'data-value': categories[i].id,
                'data-level': level,
                class: classPrefix + ' category ' + 'level-' + level,
            }).appendTo($('#categories_and_types'));
            $('<a/>',{ href:"#", text: categories[i].categoryname}).appendTo(s);
            if (showTypes) {
                var restypes = eval(categories[i].restypes);
                if (restypes) {
                    restypes = sortComponents(restypes, 'typeName', 'asc');
                    for (var j = 0; j < restypes.length; j++) {
                        let s1 =  $('<li/>', {
                            'data-value': restypes[j].id,
                            'data-level': level+1,
                            class: 'level-' + level+1,
                        }).appendTo($('#categories_and_types'));
                        $('<a/>',{ href:"#", text: restypes[j].typeName}).appendTo(s1);

                    }
                }
            }
            if (categories[i].children && categories[i].children.length > 0)
                BuildCategoriesSelect(categories[i].children, showTypes, suppressParentCategories, level + 1);
        }
    }

    /**
     * Add event handler for clicking on selected item
     */
    function addHandlers() {
        let list = $('#categories_and_types li a');
        $.each(list, function (i, item) {
            $(item).click(function (e) {
                $('#categories_and_types').data('categoryID', $(e.target).closest('li').data('value'));
                console.log($('#categories_and_types').data('categoryID'));
            })
        })
    }
})();
