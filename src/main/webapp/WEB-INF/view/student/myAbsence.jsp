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
		  p:   <c:forEach items="${profModel}" var="p">
			 ${p}
	</c:forEach>
		
	</div>
	
	<div>
		<h5>Annee: ${currentInscriptionModel.getAnnee()} </h5>
	</div>
	
	
		<table class="table">
		  <thead>
		    <tr>
		      <th scope="col">#</th>
		      <th scope="col">debut </th>
		      <th scope="col">fin</th>
		      <th scope="col">etat</th>
		      <th scope="col">type saisie</th>
		      <th scope="col">type seance</th>
		      <th scope="col">matiere</th>
		      <th scope="col">Professeur</th>
		       <th scope="col">Operation</th>
		    </tr>
		  </thead>
		  <tbody>
		  <c:forEach items="${absenceModel}" var="a">
		  
				<tr>
					<td><c:out value="${a.idAbsence}" /></td>
					<td><c:out value="${a.dateHeureDebutAbsence}" /></td>
					<td><c:out value="${a.dateHeureFinAbsence}" /></td>
					<td><c:out value="${a.etat}" /></td>
					<td><c:out value="${a.typeSaisie}" /></td>
					<td><c:out value="${a.getTypeSeance().alias}" /></td>
					<td><c:out value="${a.getMatiere().nom}" /></td>
					<td>Mr.<c:out value="${a.getObservateur().nom}" /></td>
					<td>
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/student/reclamation/${userInfo.getIdCompte()}/${a.getObservateur().getIdUtilisateur()}">Effectuer une reclamation</a></li>
						</ul>
					</td>
				</tr>
			</c:forEach>
		  </tbody>
		</table>
		
		
		<div>
			<h6>Choisir une année</h6>
		</div>
		<div class="list-group">
		<c:forEach items="${inscriptionsModel}" var="a">
				<a href="${pageContext.request.contextPath}/student/getAbsence/${userInfo.idPersonne}/${a.annee}" class="list-group-item list-group-item-action" aria-current="true">
		   		 ${a.annee}
		  </a>
			</c:forEach>
		</div>
			


<jsp:include page="../fragments/userfooter.jsp" />

