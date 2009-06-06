<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<h1>Show list</h1>

shows: <c:out value="${shows}" /> <%= request.getParameter("shows") + " --> " + request.getAttribute("shows") %> <br />
<display:table name="shows">
	<display:column property="name" />
</display:table>

