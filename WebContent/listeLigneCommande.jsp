<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<jsp:include page="subview/header.jsp"/>
	
		<h2>Liste des lignes de commandes</h2>
		<a href="ligneCommande.jsp">Retour</a>
		
		<table class="liste">
			<tr>
				<th>Id</th>
				<th>Article</th>
				<th>Quantité</th>
				<th>Sous-total</th>
				
			</tr>
			
			<c:forEach items="${entites}" var="entite">
				<tr>
					<td><c:out value="${entite.id}"/></td>
					<td><c:out value="${entite.article.id} ${entite.article.libelle}"/></td>
					<td><c:out value="${entite.quantite}" /></td>
					<td><c:out value="${service.sousTotal(entite.id)}"/></td>
					<c:choose>
						<c:when test="${action=='visualiser'}">
							<td><a href="ActionEntite?entite=ligneCommande&id=${entite.id}&action=${action}" title="Visualiser"><img alt="visualiser" src="pics/more.png"></a></td>
						</c:when>
						<c:when test="${action=='modifier'}">
							<td><a href="ActionEntite?entite=ligneCommande&id=${entite.id}&action=${action}" title="Modifier"><img alt="modifier" src="pics/pencil.png"></a></td>
						</c:when>
						<c:when test="${action=='supprimer'}">
							<td><a href="ActionEntite?entite=ligneCommande&id=${entite.id}&action=${action}" title="Supprimer"><img alt="supprimer" src="pics/cross.png"></a></td>
						</c:when>
						<c:otherwise>
							
						</c:otherwise>
					</c:choose>
				</tr>
				
			</c:forEach>
		
		</table>
<jsp:include page="subview/footer.jsp"/>