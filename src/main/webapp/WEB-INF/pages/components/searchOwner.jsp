<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="request"/>

<label for="owner_search">Select type of Owner:</label>
<select id="owner_search" class="form-control">
    <option value="absent">Choose type here</option>
    <option value="company">Company</option>
    <option value="person">Person</option>
</select>


<div id="search_owner_form">


</div>

<%--<script src="${contextPath}/resources/js/ownerAndAddressManagement.js"></script>--%>
<%--<script src="/js/ownerAndAddressManagement.js"></script>--%>

