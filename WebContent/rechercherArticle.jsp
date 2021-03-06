    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
		<jsp:include page="subview/header.jsp"/>
			<h2>Rechercher un article</h2>
			<a href="article.jsp">Retour</a>

			<!-- AFFICHER MESSAGE ERREUR -->	 
			 <c:if test="${!empty erreurs}">
				<div class="error">
					<c:forEach items="${erreurs}" var="erreur">
						<c:out value="${erreur}"/>
					</c:forEach>
				</div>
			</c:if>
			
			<form action="SearchArticleForm?action=${param.action}" method="post">
				<span class="error"><c:out value="${erreurChamps}"/></span>
				<br/>
				<label for="id">Id : </label>
				<input type="text" name="id" value="${id}" /> 
				<span class="error"><c:out value="${erreurId}"/></span>
				
				<br/>
				<p>ou</p>

				<label for="libelle">Libelle : </label>
				<input type="text" name="libelle" value="${libelle}"/>
				<br/>

				<input type="submit" value = "Rechercher"/>
				
			</form>
			
<jsp:include page="subview/footer.jsp"/>