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



<div class="container">
	<div>
		<h3>Student Reclamation</h3>
	</div>
	
	<c:if test="${not empty msg}">
			<div class="alert alert-success" role="alert">${msg}</div>
		</c:if>


		
		<f:form action="${pageContext.request.contextPath}/student/addReclamation"
			method="POST" modelAttribute="messageModel">
		
			
				<f:hidden path="messageId" />

				<f:hidden path="expediteurId" />
				
				<f:hidden path="destinataireId" />
				
				
				<div class="col">
					<label> Ecrire votre message:</label>
					<f:textarea path="texte" type="text" class="form-control"
						placeholder="texte" />
					<f:errors path="texte" class="text-danger" />
				</div>
				
			</div>
			<div style="text-align: right">
				<button type="submit" class="btn btn-primary">Update</button>
				<button type="reset" class="btn btn-secondary">Rest</button>
			</div>
		</f:form>	
     
</div>

<jsp:include page="../fragments/userfooter.jsp" />

