<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/userheader.jsp" />
<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">



			<jsp:include page="../fragments/usermenu.jsp" />

		</div>
	</nav>



	<div>
		<h3>Student Absence</h3>
		
	</div>
	
		id :   ${userInscirption.idInscription} 	<br>
		
		
		
		<c:forEach items="${inscriptionModel}" var="a">
				<tr>
					<td><c:out value="${a.annee}" /></td>
					<td><c:out value="${a.etat}" /></td>
				</tr>

		</c:forEach>
			
			
			<c:forEach items="${absenceModel}" var="a">
				<tr>
					<td><c:out value="${a.dateHeureDebutAbsence}" /></td>
				</tr>

			</c:forEach>
		
     


<jsp:include page="../fragments/userfooter.jsp" />

