<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

		<jsp:include page="subview/header.jsp"/>
		<div class="bodyWrapper">
			<h2>Ajouter un article</h2>
			
			<a href="article.jsp">Retour</a>
			<br/>
			
			<form action="ArticleForm?action=${requestScope.action}" method="post" id="articleForm">
			
				<label for="id">Id : </label>	
				<input type="text" name="id" value="${entite.id}" readonly="readonly"/>
				<br/>
							
				<label for="libelle">Libelle : </label>
				<input type="text" autofocus="autofocus" name="libelle" value="${entite.libelle}${libelle}"  <c:if test="${action=='visualiser'}">readonly="readonly"</c:if> />
				
				<span class="error"><c:out value="${erreurLibelle}"/></span>
				<br/>
				
				<label for="prix">Prix : </label>
				<input type="text" name="prix" value="${entite.prix}${prix}"  <c:if test="${action=='visualiser'}">readonly="readonly"</c:if>  />
				 
				<span class="error"><c:out value="${erreurPrix}"/></span>
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
			<section id="image">
				<c:choose>
					<c:when test="${action=='visualiser'}">
						<img alt="${entite.libelle}" src="pics/article/${entite.id}.jpg">
					</c:when>
					<c:when test="${action=='modifier'}">
						
					</c:when>
					<c:otherwise>
						
					</c:otherwise>
				</c:choose>
			</section>
			
			
		</div>
<jsp:include page="subview/footer.jsp"/>