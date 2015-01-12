<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<jsp:include page="subview/header.jsp"/>
		<div class="bodyWrapper">
			<h2>Ajouter un client</h2>
			
			<a href="client.jsp">Retour</a>
			
			<form action="ClientForm?action=${requestScope.action}" method="post">
			
				<label for="id">Id : </label>	
				<input type="text" name="id" value="${entite.id}" readonly="readonly"/>
				<br/>
				
				<label for="nom">Nom : </label>
				<input type="text" autofocus="autofocus" name="nom" value="${entite.nom}${nom}"  <c:if test="${action=='visualiser'}">readonly="readonly"</c:if> />
				<span class="error"><c:out value="${erreurNom}"/></span>
				<br/>
				
				<label for="prenom">Prénom : </label>
				<input type="text" name="prenom" value="${entite.prenom}${prenom}"  <c:if test="${action=='visualiser'}">readonly="readonly"</c:if> />
				<span class="error"><c:out value="${erreurPrenom}"/></span>
				<br/>
								
				<label for="adresse">Adresse : </label>
				<input type="text" name="adresse" value="${entite.adresse}${adresse}"  <c:if test="${action=='visualiser'}">readonly="readonly"</c:if> />
				<span class="error"><c:out value="${erreurAdresse}"/></span>
				<br/>
				
				<c:choose>
					<c:when test="${action=='visualiser'}">
<!-- 							<input type="submit" value="Enregistrer"/> -->
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