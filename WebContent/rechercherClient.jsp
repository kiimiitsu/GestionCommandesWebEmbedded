    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
		<jsp:include page="subview/header.jsp"/>
			<h2>Rechercher un client</h2>
			<a href="client.jsp">Retour</a>

			<!-- AFFICHER MESSAGE ERREUR -->	 
			 <c:if test="${!empty erreurs}">
				<div class="error">
					<c:forEach items="${erreurs}" var="erreur">
						<c:out value="${erreur}"/>
					</c:forEach>
				</div>
			</c:if>
			
			<form action="SearchClientForm?action=${param.action}" method="post">
				<span class="error"><c:out value="${erreurChamps}"/></span>
				<br/>
				
				<label for="id">Id : </label>
				<input type="text" name="id" value="${id}" /> 
				<span class="error"><c:out value="${erreurId}"/></span>
				
				<br/>
				<p>ou</p>

				<label for="nom">Nom : </label>
				<input type="text" name="nom" value="${nom}"/>
				<br/>

				<input type="submit" value = "Rechercher"/>
				
			</form>
			
<jsp:include page="subview/footer.jsp"/>