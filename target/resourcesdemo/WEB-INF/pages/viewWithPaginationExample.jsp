<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="pg" uri="http://pagination/pagination-spring3.tld" %>

<c:url value="${paginationResult.pageLink}" var="pageLink"/>
<form:form id="pgform" method="post" modelAttribute="pparam" action="${pageLink}">
	.
	.
	.
</form:form>

<pg:pagination pparam="${pparam}" paginationResult="${paginationResult}">
    <jsp:attribute name="searchContent">
        <table class="searchtable">
			<tr>
				<td class="caption" style="width:50px;">Filter:</td>
				<td style="width:80px;">Player Name:</td>
				<td style="width:160px;"><form:input path="playerName" cssStyle="width:150px;"/></td>
				<td style="width:75px;">
                  <span class="button">
                    <form:button id="searchButton" name="buttonAction" value="searchButton" class="button">Search</form:button>
                  </span>
				</td>
				<td style="width:75px;">
                  <span class="button">
                    <form:button id="clearButton" name="buttonAction" value="clearButton" class="button">Clear</form:button>
                  </span>
				</td>
			</tr>
		</table>
    </jsp:attribute>
	<jsp:attribute name="controlButton">
        <div style="padding-top:10px;">
            <span class="button">
              <form:button id="deleteButton" name="buttonAction" value="deleteButton" class="button">Delete</form:button>
            </span>
			<span class="button">
              <form:button id="addButton" name="buttonAction" value="addButton" class="button">Add</form:button>
            </span>
		</div>
    </jsp:attribute>
	<jsp:attribute name="columnsContent">
        <td class="cell">
          <span style="white-space:nowrap;">
            <c:out value="${bo.playerName}"/>
          </span>
		</td>
        <td class="cell" style="text-align:right;">
          <span>
            <fmt:formatNumber type="number" value="${bo.minutePerGame}" pattern="###">
            </fmt:formatNumber>
          </span>
		</td>

    </jsp:attribute>
</pg:pagination>