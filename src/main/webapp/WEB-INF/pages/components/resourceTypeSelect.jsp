<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--including JSP should define var ${typeSelectLabel} to set appropriate label--%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<label for="categories-select"><c:out value="${typeSelectLabel}"/></label>
<div class="btn-group hierarchy-select" data-resize="auto" id="categories-select">
    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
        <span class="selected-label pull-left" id="selected-label">&nbsp;</span>
        <span class="caret"></span>
        <span class="sr-only">Toggle Dropdown</span>
    </button>
    <div class="dropdown-menu open">
        <div class="hs-searchbox">
            <input type="text" class="form-control" autocomplete="off">
        </div>
        <ul class="dropdown-menu inner" role="menu" id="categories_and_types">
            <li id="default-item" data-level="1" hidden class="disabled active"><a href="#"></a></li>
            <li id="all-categories" data-level="1" hidden><a href="#">All resource categories</a></li>
        </ul>
    </div>
    <input class="hidden hidden-field" name="search_form[category]" readonly
           aria-hidden="true" type="text"/>
</div>

<script src="${contextPath}/resources/js/hierarchy-select.js"></script>
<script src="${contextPath}/resources/js/categories.js"></script>
