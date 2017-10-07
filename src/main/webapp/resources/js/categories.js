/**
 * Calling JSP should define variable {showTypesInCategoryHierarchy = true}
 * to allow displaying resource types in select
 * and variable {disableAncestorSelecting = false} to disallow selecting of  intermediate categories
 */

$(document).ready(function () {
    const includeTypes = (typeof showTypesInCategoryHierarchy == 'undefined') ? false : showTypesInCategoryHierarchy;
    const suppressChoosingParents = (typeof disableAncestorSelecting == 'undefined') ? true : disableAncestorSelecting;
    const defaultSelectedLabel = includeTypes ? 'type of resource' : 'category of resource';
    let lastDatabaseId;
    let lastTemporaryId;

    //Enable categories selectlist
    loadCategories();

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
    });

    /**
     * Add event handlers for each functional button of Nestable items
     * @param scope - selector, determines for which buttons are set event handlers:
     * for all buttons of container - after building of Nestable;
     * for buttons of particular item - after appending it to hierarchy
     */
    function addNestableButtonsHandlers(scope) {
        let allButtons = $(scope).find('.btn-add, .btn-edit, .btn-remove');
        $.each(allButtons, function (i, item) {
            $(item).on('mousedown', function (e) {
                e.stopPropagation();
                return false;
            });
        });

        //Event handlers for add-buttons
        let addButtons = $(scope).find('.btn-add');
        $.each(addButtons, function (i, item) {
            $(item).click(function (e) {
                let ownerCategoryId = $(item).attr('data-owner-id');
                let newItem = {
                    "id": ++lastTemporaryId,
                    "categoryname": "new category",
                    "parent_id": ownerCategoryId,
                    "content": "Item " + lastTemporaryId
                };
                $('#nestable').nestable('add', newItem);
                addNestableButtonsHandlers('[data-id=' + lastTemporaryId + ']');
                updateOutput($('#nestable').data('output', $('#nestable-output')));
            })
        });

        //Event handlers for edit-buttons
        let editButtons = $(scope).find('.btn-edit');
        $.each(editButtons, function (i, item) {
            $(item).click(function (e) {
                let ownerId = $(item).attr('data-owner-id');
                //TODO
                updateOutput($('#nestable').data('output', $('#nestable-output')));
            })
        });

        //Event handlers for remove-buttons
        let removeButtons = $(scope).find('.btn-remove');
        $.each(removeButtons, function (i, item) {
            $(item).click(function (e) {
                let ownerId = $(item).attr('data-owner-id');
                let categoriesWithResourceTypes = getResourceTypes(ownerId);
                if (categoriesWithResourceTypes.length > 0) {
                    alert('You can not delete this element, because category(-ies) '
                        + categoriesWithResourceTypes.join(', ') + ' have resource types');
                } else {
                    $('#nestable').nestable('remove', ownerId);
                    updateOutput($('#nestable').data('output', $('#nestable-output')));
                }
            })
        });
    }

    /**
     * Load data from server, build categories selectlist and reselect item,
     * if it was selected before and still exist
     * @param lastSelectedId - ID of last selected item
     */
    function loadCategories(lastSelectedId) {
        let urlSuffix = includeTypes ? 'categorizedTypes' : 'categories';
        $.get("/resources/" + urlSuffix, function (data) {
            showCategoriesSelect(data);
            let isLastSelectedItemExists = $('[data-value="' + lastSelectedId + '"] > a');
            if (lastSelectedId && isLastSelectedItemExists) {
                isLastSelectedItemExists.click();
            }
            else {
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
                lastTemporaryId = findLastDatabaseId(data);
                let json = JSON.stringify(data);

                // activate Nestable
                $('#nestable').nestable({
                    json: json,
                    contentCallback: function (item) {
                        let content = item.categoryname || '' ? item.categoryname : item.id;
                        content += ' <i>(id = ' + item.id + ')</i>';
                        return content;
                    }
                }).on('change', updateOutput);

                //initialize handlers for buttons on components
                addNestableButtonsHandlers('#nestable');

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
                loadCategories(lastSelectedId);
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
     * @returns {Array.<T>} array of sorted objects
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
     * @returns {Array.<T>} array of sorted objects
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
                            class: 'level-' + (level + 1),
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
     * Add event handler for clicking on item in selectlist
     */
    function addHandlers() {
        let list = $('#categories_and_types').find('li a');
        $.each(list, function (i, item) {
            $(item).click(function (e) {
                $('#categories-select').data('selectedID', $(e.target).closest('li').data('value'));
                console.log($('#categories-select').data('selectedID'));
                $('#categories-select')[0].dispatchEvent(new Event('change'));

            })
        })
    }

    /**
     * Find last category ID, which is stored in Database
     * @param data - JSON with categories
     * @param level - level of recursion
     * @returns {*} last category ID in Database
     */
    function findLastDatabaseId(data, level = 0) {
        $.each(data, function (i, item) {
            if (level === 0 && i === 0) {
                lastDatabaseId = data[0].id;
            }
            if (item.id > lastDatabaseId) {
                lastDatabaseId = item.id;
            }
            if (item.children && item.children.length > 0) {
                findLastDatabaseId(item.children, level + 1);
            }
        });
        return lastDatabaseId;
    }

    /**
     * Check whether category and all its descendants has resource types
     * @param idCategory - ID of checked element
     * @returns {Array} array of strings with names of all categories, which have resource types
     * or empty array if neither of categories have resource types
     */
    function getResourceTypes(idCategory) {
        let tree = $('[data-id=' + idCategory + '], [data-id=' + idCategory + '] li');
        let result = [];
        tree.each(function (i, item) {
            if ($(item).attr('data-hastypes') === 'true') {
                result.push($(item).attr('data-categoryname'));
            }
        });
        return result;
    }

    /*    function findById(data, id) {
            $.each(data, function (i, item) {
                if (item['id'] === id) {
                    return item;
                } else if (item.children && item.children.length > 0) {
                    return findById(item.children, id);
                }
            });
        }*/
});
