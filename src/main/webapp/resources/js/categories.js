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

    //Build contents of modal window for managing categories
    $('#manage-categories').on('click', function (e) {
        e.preventDefault();
        $('#save-alert').addClass('hidden');
        //Turm on draggable for inner Nestable dialog with text input
        $('#nestable-dialog-inner').draggable({
            handle: ".modal-header"
        });
        buildCategoriesNestableTree();
    });

    //Save changes of category managing in Nestable
    $('#save-changes').on('click', function (e) {
        e.preventDefault();
        saveChangesAfterManaging();
    });

    //Validate inputted text and save changes and close modal window for adding/editing categories
    $('#save-name').on('click', function (e) {
        e.preventDefault();
        let categoryName = $('#category-name-input').val().trim();
        let action = $('#category-name-input').data('action');
        let ownerId = $('#category-name-input').data('ownerId');
        if (!validateInputTextForUnique(categoryName, action, ownerId)) {
            showValidationFailMessage('notUnique');
        } else if (categoryName.length === 0) {
            showValidationFailMessage('blank');
        } else if (categoryName.length < 3 || categoryName.length > 50) {
            showValidationFailMessage('badLength');
        } else {
            if (action === 'add') {
                addCategory(ownerId, categoryName);
            }
            if (action === 'edit') {
                editCategory(ownerId, categoryName);
            }
            $('.close-name-dialog')[0].click();
        }
    });

    //Focus on text input after appearing of inner Nestable dialog
    $('#nestable-dialog').on('shown.bs.modal', function () {
        $('#category-name-input').focus();
    });

    //Return inner Nestable dialog into previous position, if it was dragged
    $('#nestable-dialog').on('hidden.bs.modal', function () {
        $('#nestable-dialog-inner').css({top: 0, left: 0});
    });

    //Discard changes and close modal window for adding/editing categories
    $('.close-name-dialog').on('click', function (e) {
        e.preventDefault();
        $('#category-name-input').removeData('action');
        $('#category-name-input').removeData('ownerId')
        $('#category-name-input').val('');
        $('#input-div').removeClass('has-error has-feedback');
        if ($('#error-icon-span').is(":visible")) {
            $('#error-icon-span').addClass('hidden');
        }
        if ($('#validation-message').is(":visible")) {
            $('#validation-message').text('').addClass('hidden');
        }
    });

    //Discard changes and close modal window with categories managing
    $('#cancel-managing, #close-managing').on('click', function (e) {
        e.preventDefault();
        $('#nestable').nestable('destroy');
    });

    //Expand/collapse Nestable tree
    (function () {
        var toggled = false;
        $('#exp-col').click(function (e) {
            toggled = !toggled;
            $('.dd').nestable(toggled ? 'collapseAll' : 'expandAll');
        });
    })();

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
                passDataToNameDialog(item, 'add');
            })
        });

        //Event handlers for edit-buttons
        let editButtons = $(scope).find('.btn-edit');
        $.each(editButtons, function (i, item) {
            $(item).click(function (e) {
                passDataToNameDialog(item, 'edit');
            })
        });

        //Event handlers for remove-buttons
        let removeButtons = $(scope).find('.btn-remove');
        $.each(removeButtons, function (i, item) {
            $(item).click(function (e) {
                let ownerId = $(item).attr('data-owner-id');
                removeCategory(ownerId);
            })
        });
    }

    /**
     * Select item of selectlist by ID
     * @param id - ID of item("option") of component
     */
    function selectItemById(id) {
        let item = $('[data-value="' + id + '"]').find('a');
        if (item) {
            item.click();
        } else {
            $('#default-item > a').click();
            $('#selected-label').text('Select ' + defaultSelectedLabel);
        }
    }

    /**
     * Select item of selectlist by ID, if it still exist after managing categories in Nestable
     * @param id - ID of item("option") of component
     */
    function selectLastItemAfterManagingCategories(id) {
        let lastSelectedItem = $('[data-value="' + id + '"]').find('a');
        if (lastSelectedItem.text() === $('[data-id="' + id + '"]').attr('data-categoryname')) {
            lastSelectedItem.click();
        }
        else {
            $('#default-item > a').click();
            $('#selected-label').text('Select ' + defaultSelectedLabel);
        }
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
            if (lastSelectedId) {
                selectLastItem(lastSelectedId);
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
                lastTemporaryId = findLastDatabaseId(data);
                let json = JSON.stringify(data);

                //activate Nestable
                $('#nestable').nestable({
                    json: json,
                    maxDepth: 20,
                    contentCallback: function (item) {
                        let content = item.categoryname || '' ? item.categoryname : item.id;
                        // content += ' <i>(id = ' + item.id + ')</i>';
                        return content;
                    }
                }).on('change', updateOutput);

                //initialize handlers for buttons on components
                addNestableButtonsHandlers('#categories-view');

                //output initial serialised data
                updateOutput($('#nestable').data('output', $('#nestable-output')));
            })
            .error(function () {
                // alert("Error " + jqxhr.status);
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

        //Mark non-leaf categories which have resource types
        $('.dd-handle').removeClass('non-leaf-categories-with-types');
        let nonLeafCategoriesWithTypes = $('.dd-list').parent().filter('li').filter(function () {
            return $(this).attr('data-hastypes') === 'true';
        }).children('.dd-handle');
        nonLeafCategoriesWithTypes.addClass('non-leaf-categories-with-types');
    };

    /**
     * Save changes after managing categories
     */
    function saveChangesAfterManaging() {
        let allCategories = $('.dd-item');
        allCategories.each(function (i, item) {
            if ($(item).data('id') > lastDatabaseId) {
                $(item).removeData('id');
            }
            updateOutput($('#nestable'));
        });
        let json = $('#nestable-output').val();
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/resources/categories",
            accept: "application/json",
            data: json,
            success: function (jqXHR) {
                $('#save-alert').removeClass('hidden');
                setTimeout(function () {
                    $('#categories-view').fadeOut(100, function () {
                        $('#categories-view').modal('hide');
                        $('#nestable').nestable('destroy');
                    });
                }, 1000);
                let lastSelectedId = $('#categories-select').data('selectedID');
                $('#categories_and_types').empty();
                loadCategories(lastSelectedId);
            },
            error: function (jqXHR) {
                var error = JSON.parse(jqXHR.responseText);
                alert('Error!\n" + error.message');
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
        addHandlersInSelectlist();
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
    function addHandlersInSelectlist() {
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
    function hasResourceTypes(idCategory) {
        let tree = $('[data-id=' + idCategory + '], [data-id=' + idCategory + '] li');
        let result = [];
        tree.each(function (i, item) {
            if ($(item).attr('data-hastypes') === 'true') {
                result.push($(item).attr('data-categoryname'));
            }
        });
        return result;
    }

    /**
     * Pass information about source category to modal dialog for editing current or adding new child category
     * @param item - add/edit button on source category which was clicked
     * @param action - action which will be performed ('add' or 'edit')
     */
    function passDataToNameDialog(item, action) {
        let ownerCategoryId = $(item).attr('data-owner-id');
        $('#category-name-input').data('ownerId', ownerCategoryId);
        $('#category-name-input').data('action', action);
        if (action === 'add') {
            $('#nestable-dialog .modal-title').text('Add Resource Category');
        }
        if (action === 'edit') {
            $('#nestable-dialog .modal-title').text('Edit Resource Category');
            let owner = $('[data-id=' + ownerCategoryId + ']');
            let currentCategoryName = owner.attr('data-categoryname');
            $('#category-name-input').val(currentCategoryName);
        }
        $('#nestable-dialog').modal('show');
    }

    /**
     * Add category in Nestable as a child of selected category
     * @param id - ID of selected category
     * @param categoryName - name of category which will be created
     */
    function addCategory(id, categoryName) {
        let newItem;
        if (id) {
            newItem = {
                "id": ++lastTemporaryId,
                "categoryname": categoryName,
                "parent_id": id,
                "content": "Item " + lastTemporaryId
            };
        } else {
            newItem = {
                "id": ++lastTemporaryId,
                "categoryname": categoryName,
                "content": "Item " + lastTemporaryId
            };
        }
        $('#nestable').nestable('add', newItem);
        addNestableButtonsHandlers('[data-id=' + lastTemporaryId + ']');
        updateOutput($('#nestable'));
    }

    /**
     * Edit name of selected category
     * @param id - ID of selected category
     * @param categoryName - new name of selected category
     */
    function editCategory(id, categoryName) {
        let category = $('[data-id=' + id + ']');
        category.attr('data-categoryname', categoryName);
        category.data('categoryname', categoryName);
        let label = category.find('.dd-content:first');
        // label.html(categoryName + ' <i>(id = ' + id + ')</i>');
        label.text(categoryName);
        updateOutput($('#nestable'));
    }

    /**
     * Remove category in Nestable
     * @param id - ID of selected category
     */
    function removeCategory(id) {
        let categoriesWithResourceTypes = hasResourceTypes(id);
        if (categoriesWithResourceTypes.length > 0) {
            alert('You can not delete this element, because category(-ies) '
                + categoriesWithResourceTypes.join(', ') + ' have resource types');
        } else {
            $('#nestable').nestable('remove', id);
            updateOutput($('#nestable'));
        }
    }

    /**
     * Check whether already exists resource category with same name as name typed in input field
     * @param text - text from input field
     * @param action - action which will be performed ('add' or 'edit')
     * @param ownerId - ID of selected category (if action is editing)
     * @returns {boolean} true, if typed name is unique
     */
    function validateInputTextForUnique(text, action, ownerId) {
        let allCategories = $('.dd-item');
        for (let i = 0; i < allCategories.length; i++) {
            let categoryName = $(allCategories[i]).attr('data-categoryname');
            if (text.toLowerCase() === categoryName.trim().toLowerCase()) {
                if (action === 'edit' && ownerId == $(allCategories[i]).data('id')) continue;
                return false;
            }
        }
        return true;
    }

    /**
     * Show information about validation error
     * @param reason - type of validation error
     */
    function showValidationFailMessage(reason) {
        let messageText;
        switch (reason) {
            case 'notUnique' :
                messageText = 'Name of resource category must be unique. Category with such name already exists';
                break;
            case 'blank' :
                messageText = 'Name of resourse category can not be empty';
                break;
            case 'badLength' :
                messageText = 'Name of resourse category must be longer than 2 and shorter than 50 characters';
                break;
        }
        $('#input-div').addClass('has-error has-feedback');
        $('#error-icon-span').removeClass('hidden');
        $('#validation-message').text(messageText).removeClass('hidden');
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
