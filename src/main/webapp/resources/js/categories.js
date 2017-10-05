/**
 * Calling JSP should define variable {showTypesInCategoryHierarchy = true}
 * to allow displaying resource types in select
 * and variable {disableAncestorSelecting = false} to disallow selecting of  intermediate categories
 */

$(document).ready(function() {
    const includeTypes = (typeof showTypesInCategoryHierarchy == 'undefined') ? false : showTypesInCategoryHierarchy;
    const suppressChoosingParents = (typeof disableAncestorSelecting == 'undefined') ? true : disableAncestorSelecting;
    const defaultSelectedLabel = includeTypes ? 'type of resource' : 'category of resource';
    let lastId;

    //Enable categories selectlist
    loadCategories(includeTypes, suppressChoosingParents);

    $('#manage-categories').on('click', function (e) {
        e.preventDefault();
        buildCategoriesNestableTree();
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
        let target = $(e.target),
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
//                    "`nt_id" : 1516,
                "content": "Item " + lastId
            };
            $('#nestable').nestable('add', newItem);
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        }
        if (action === 'remove-item') {
            let branch2_id = $("[data-categoryname='branch2']").attr("data-id");
            $('#nestable').nestable('remove', branch2_id);
            updateOutput($('#nestable').data('output', $('#nestable-output')));
        }
    });

    /**
     * Load data and build categories selectlist
     */
    function loadCategories() {
        let urlSuffix = includeTypes ? 'categorizedTypes' : 'categories';
        $.get("/resources/" + urlSuffix, function (data) {
            showCategoriesSelect(data);
            $('#selected-label').text('Select ' + defaultSelectedLabel);
            $('#categories_and_types li:first').removeClass('active');
        }, "json");
    }

    /**
     * Reload data, rebuild categories selectlist and reselect item
     * which was selected before reloading (if it still exists)
     * @param lastSelectedId - ID of last selected item
     */
    function reloadCategories(lastSelectedId) {
        let urlSuffix = includeTypes ? 'categorizedTypes' : 'categories';
        $.get("/resources/" + urlSuffix, function (data) {
            showCategoriesSelect(data);
            let isLastSelectedItemExists = $('[data-value="' + lastSelectedId + '"] > a').length;
            if (isLastSelectedItemExists) {
                $('[data-value="' + lastSelectedId + '"] > a').click();
            } else {
                $('#selected-label').text('Select ' + defaultSelectedLabel);
            }
        }, "json");
    }

    /**
     * Activate Nestable and build categories tree
     */
    function buildCategoriesNestableTree() {
        let jqxhr = $.getJSON("/resources/categories")
            .success(function (data) {
                data = sortNestedComponents(data, 'categoryname', '', 'asc');
                let json = JSON.stringify(data);
                // activate Nestable
                $('#nestable').nestable({
                    json: json,
                    contentCallback: function (item) {
                        let content = item.categoryname || '' ? item.categoryname : item.id;
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
    let updateOutput = function (e) {
        let list = e.length ? e : $(e.target),
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
        let json = $('#nestable-output').val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/resources/categories",
            accept: "application/json",
            data: json,
            success: function (result) {
                alert("Changes are saved!");
                $('#close-managing').click();
                let lastSelectedId = $('#categories-select').data('selectedID');
                $('#categories_and_types').empty();
                reloadCategories(lastSelectedId);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert("jqXHR: " + jqXHR.status + " Status: " + textStatus + " Error: " + errorThrown);
            }
        })
    }

    /**
     * Sort array of objects by some object key
     * @param data - array of objects
     * @param key - object key for sorting
     * @param way - ascending or descending order
     * @returns {Array.<T>} - array of sorted objects
     */
    function sortComponents(data, key, way) {
        return data.sort(function (a, b) {
            let x = a[key];
            let y = b[key];
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
     * @param categoryKey - object key for sorting categories
     * @param typeKey - object key for sorting types
     * @param way - ascending or descending order
     * @returns {Array.<T>} - array of sorted objects
     */
    function sortNestedComponents(data, categoryKey, typeKey, way) {
        data = sortComponents(data, categoryKey, way);
        for (let i = 0; i < data.length; i++) {
            if (data[i].children && data[i].children.length > 0) {
                sortNestedComponents(data[i].children, categoryKey, typeKey, way);
            }
            let restypes = eval(data[i].restypes);
            if (restypes && restypes.length > 0) {
                restypes = sortComponents(restypes, typeKey, way);
            }
        }
        return data;
    }

    /**
     * Build items of categories select
     * @param categories - JSON with categories and types
     * @param showTypes - include resource types in select
     * @param suppressChoosingParents - allow choosing only leaf categories in select
     * @param level - depth of hierarchy
     * @constructor
     */
    function BuildCategoriesSelect(categories, showTypes, suppressChoosingParents, level = 1) {
        for (let i = 0; i < categories.length; i++) {
            let classPrefix = (showTypes || (suppressChoosingParents && categories[i].children)) ? 'disabled category ' : '';
            let s = $('<li/>', {
                'data-value': categories[i].id,
                'data-level': level,
                class: classPrefix + 'level-' + level,
            }).appendTo($('#categories_and_types'));
            $('<a/>', {href: "#", text: categories[i].categoryname}).appendTo(s);
            if (showTypes) {
                let restypes = eval(categories[i].restypes);
                if (restypes) {
                    for (let j = 0; j < restypes.length; j++) {
                        let s1 = $('<li/>', {
                            'data-value': restypes[j].id,
                            'data-level': level + 1,
                            class: 'level-' + level + 1,
                        }).appendTo($('#categories_and_types'));
                        $('<a/>', {href: "#", text: restypes[j].typeName}).appendTo(s1);

                    }
                }
            }
            if (categories[i].children && categories[i].children.length > 0)
                BuildCategoriesSelect(categories[i].children, showTypes, suppressChoosingParents, level + 1);
        }
    }

    /**
     * Show/repaint categories select on the page
     * @param data - JSON with categories and types
     */
    function showCategoriesSelect(data) {
        data = sortNestedComponents(data, 'categoryname', 'typeName', 'asc');
        console.log(JSON.stringify(data));
        BuildCategoriesSelect(data, includeTypes, suppressChoosingParents);
        addHandlers();
        $('#categories-select').hierarchySelect({
            width: '100%',
            height: '208px',
            hierarchy: includeTypes,
            search: true
        });
    }

    /**
     * Add event handler for clicking on selected item
     */
    function addHandlers() {
        let list = $('#categories_and_types').find('li a');
        $.each(list, function (i, item) {
            $(item).click(function (e) {
                $('#categories-select').data('selectedID', $(e.target).closest('li').data('value'));
                console.log($('#categories-select').data('selectedID'));
                $('#categories_and_types')[0].dispatchEvent(new Event('change'));

            })
        })
    }
});
