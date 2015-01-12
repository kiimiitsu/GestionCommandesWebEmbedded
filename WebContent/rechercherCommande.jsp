    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
		<jsp:include page="subview/header.jsp"/>
			<h2>Rechercher une commande</h2>
			<a href="commande.jsp">Retour</a>

			<!-- AFFICHER MESSAGE ERREUR -->	 
			 <c:if test="${!empty erreurs}">
				<div class="error">
					<c:forEach items="${erreurs}" var="erreur">
						<c:out value="${erreur}"/>
					</c:forEach>
				</div>
			</c:if>
			
			<form action="SearchCommandeForm?action=${param.action}" method="post">
				<span class="error"><c:out value="${erreurChamps}"/></span>
				<br/>
				
				<label for="id">Id : </label>
				<input type="text" name="id" value="${id}" /> 
				<span class="error"><c:out value="${erreurId}"/></span>
				
				<br/>
				<p>ou</p>

				<label for="nomClient">Client : </label>
				<input type="text" name="nomClient" value="${nomClient}"/>
				<br/>

				<input type="submit" value = "Rechercher"/>
				
			</form>
			
<jsp:include page="subview/footer.jsp"/>