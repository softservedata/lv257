<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="existent-props" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">

      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4>Resource Properties</h4>
      </div>
      <div class="modal-body">
        <div class="container-fluid">
          <form>
            <ul>
              <c:forEach items="${properties}" var="property">
                <li><input type="checkbox" id="<c:out value="${property.id}"/>">
                  </input>
                  <label for="<c:out value="${property.id}"/>"><c:out value="${property.title}"/>
                    <c:if test="${property.units != null}">, <c:out value="${property.units}"/></c:if>
                  </label>
                </li>
              </c:forEach>
            </ul>
            <div class="pull-right">
              <button type="reset" class="btn btn-primary" data-dismiss="modal">Accept</button>
              <button type="reset" class="btn btn-primary" data-dismiss="modal">Discard</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<script>

</script>