<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<jsp:include page="subview/header.jsp"/>
		<div class="bodyWrapper">
			<h2>Ajouter une commande</h2>
			
			<a href="ligneCommande.jsp">Retour</a>
			
			<table class="liste">
				<tr>
					<th>Id</th>
					<th>Article</th>
					<th>Quantité</th>
					<th>Sous-total</th>
				</tr>
			<c:forEach items="${service.entites}" var="entite">
				<tr>
					<td><c:out value="${entite.id}"/></td>			
					<td><c:out value="${entite.article.id} : ${entite.article.libelle}"/></td>
					<td><c:out value="${entite.quantite}"/></td>
					<td><c:out value="${service.sousTotal(entite.id)}"/></td>
				</tr>
			</c:forEach>	
								
				
			
			</table>
			<h2>Saisie des lignes de commande</h2>
			
			<form action="LigneCommandeForm?action=${requestScope.action}" method="post">
			
				<label for="id">Id : </label>	
				<input type="text" name="id" value="${entite.id}" readonly="readonly"/>
				<br/>
				
				<label for="idArticle">Code Article : </label>
				<input type="number" autofocus="autofocus" name="idArticle" value="${entite.article.id}${idArticle}"  <c:if test="${action=='visualiser'}">readonly="readonly"</c:if> />
				<span class="error"><c:out value="${erreurArticle}"/></span>
				<br/>

				<label for="quantite">Quantité : </label>
				<input type="number" name="quantite" value="${entite.quantite}${quantite}"  <c:if test="${action=='visualiser'}">readonly="readonly"</c:if> />
				<span class="error"><c:out value="${erreurQuantité}"/></span>
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