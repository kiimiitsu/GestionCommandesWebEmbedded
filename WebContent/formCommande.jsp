<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<jsp:include page="subview/header.jsp"/>
		<div class="bodyWrapper">
			<h2>Ajouter une commande</h2>
			
			<a href="commande.jsp">Retour</a>
			
			<form action="CommandeForm?action=${requestScope.action}" method="post">
			
				<label for="id">Id : </label>	
				<input type="text" name="id" value="${entite.id}" readonly="readonly"/>
				<br/>
				
				<label for="idClient">Id client : </label>
				<input type="text" autofocus="autofocus" name="idClient" value="${entite.client.id}${idClient}"  <c:if test="${action=='visualiser'}">readonly="readonly"</c:if> />
				<span class="error"><c:out value="${erreurClient}"/></span>
				<br/>

				<c:choose>
					<c:when test="${action=='visualiser'}">
 						<input type="submit" value="Détails"/>
					</c:when>
					<c:when test="${action=='modifier'}">
						<input type="submit" value="Modifier"/>
					</c:when>
					<c:otherwise>
						<input type="submit" value="Enregistrer"/>
					</c:otherwise>
				</c:choose>
			</form>
			
			
			
			
			
			
		</div>
<jsp:include page="subview/footer.jsp"/>