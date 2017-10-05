<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--including JSP should define var ${typeSelectLabel} to set appropriate label
 and include hierarchy-select.js, categories.js--%>

<label for="categories-select"><c:out value="${typeSelectLabel}"/></label>
<div class="btn-group hierarchy-select" data-resize="auto" id="categories-select">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
        <span class="selected-label pull-left">&nbsp;</span>
        <span class="caret"></span>
        <span class="sr-only">Toggle Dropdown</span>
    </button>
    <div class="dropdown-menu open">
        <div class="hs-searchbox">
            <input type="text" class="form-control" autocomplete="off">
        </div>
        <ul class="dropdown-menu inner" role="menu" id="categories_and_types">

        </ul>
    </div>
    <input class="hidden hidden-field" name="search_form[category]" readonly
           aria-hidden="true" type="text"/>
</div>
<script src="${contextPath}/resources/js/categories.js"></script>
<script src="${contextPath}/resources/js/hierarchy-select.js"></script>
