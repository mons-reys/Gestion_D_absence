<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>


<jsp:include page="../fragments/adminHeader.jsp" />

<div class="container">

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">
			<jsp:include page="../fragments/bibliomenu.jsp" />

		</div>
	</nav>




	<div>
		<h3>Enregistrement d'un emprunt</h3>


	</div>
	<div>

		<c:if test="${not empty msg}">
			<div class="alert alert-success" role="alert">${msg}</div>
		</c:if>
		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
		<f:form
			action="${pageContext.request.contextPath}/biblio/realiserEmprunt"
			method="POST" modelAttribute="empruntModel">
	


			<div class="row">
				<div class="col">
					<label>Saisir la CIN de la personne</label>
					<f:input path="utilisateur.cin" type="text" class="form-control"
						placeholder="cin" />
					<f:errors path="utilisateur.cin" class="text-danger" />
				</div>

				<div class="col">
					<label>Saisir le code sur livre</label>
					<f:input path="livre.codeLivre" type="text" class="form-control"
						placeholder="Code Livre" />
					<f:errors path="livre.codeLivre" class="text-danger" />
				</div>
			</div>



			<div style="text-align: right">
				<button type="submit" class="btn btn-primary">Valider</button>
				<button type="reset" class="btn btn-secondary">Rest</button>
			</div>

		</f:form>
	</div>



	<jsp:include page="../fragments/adminfooter.jsp" />