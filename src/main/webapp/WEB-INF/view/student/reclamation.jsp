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
	
	
					
							
	
	<!--  chat  --> 
	
		          <div class="panel panel-primary">
                <div class="panel-heading">
                    <span class="glyphicon glyphicon-comment"></span> Messages
                    <div class="btn-group pull-right">
                        <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                            <span class="glyphicon glyphicon-chevron-down"></span>
                        </button>
          
                    </div>
                </div>
                <div class="panel-body">
                    <ul class="chat">
                    
                    <c:forEach items="${conversationModel.getMessages()}" var="a">
                                	<c:choose>
									   <c:when test="${a.getExpediteur().getProprietaire().getIdUtilisateur() == idPersonModel}">
									   <li class="left clearfix"><span class="chat-img pull-left">
				                            <img src="http://placehold.it/50/55C1E7/fff&text=Me" alt="User Avatar" class="img-circle" />
				                        </span>
				                            <div class="chat-body clearfix">
				                                <div class="header">
									   	 <strong class="primary-font">Moi</strong> <small class="pull-right text-muted">
                                       	 <span class="glyphicon glyphicon-time">${a.getDateHeure()}</span></small></c:when> 
									   
									   <c:otherwise>
									   		<li class="left clearfix"><span class="chat-img pull-left">
				                            <img src="http://placehold.it/50/55C1E7/fff&text=Ad" alt="User Avatar" class="img-circle" />
				                        </span>
				                            <div class="chat-body clearfix">
				                                <div class="header">
									   	 <strong class="primary-font">Cadre Admin: Mr.${a.getDestinataire().getProprietaire().getNom()}</strong> <small class="pull-right text-muted">
                                       	 <span class="glyphicon glyphicon-time">${a.getDateHeure()}</span></small></c:otherwise>    
									</c:choose>
                                
                                   
                                </div>
                                <p>
                                   ${a.getTexte()}
                                </p>
                            </div>
                        </li>
					
					</c:forEach>
                       
                       
                    </ul>
                </div>
               
            </div>
        </div>
    


	
	<!--  /chat  --> 	
	<c:if test="${not empty param.msg}">
			<div class="alert alert-success" role="alert">${param.msg}</div>
		</c:if>


		
		<f:form action="${pageContext.request.contextPath}/student/addReclamation/"
			method="POST" modelAttribute="messageModel">
		
			
				<f:hidden path="messageId" />

				<f:hidden path="expediteurId" value="${idPersonModel}" />
				
				<f:hidden path="destinataireId"  value="${idEnseignantModel}" />
				
				
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

