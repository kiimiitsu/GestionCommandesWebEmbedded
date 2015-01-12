package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.afpa59.gc.donnees.Article;
import com.afpa59.gc.donnees.Entite;
import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceArticle;

/**
 * Servlet implementation class SearchForm
 */
public class SearchArticleForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchArticleForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int errors = 0;
		
		String target = null;
		
		String idStr = request.getParameter("id").trim();
		String libelle = request.getParameter("libelle").trim();
		String action = request.getParameter("action");
		
		int id = 0;
		if(libelle.trim().equals("") && idStr.trim().equals("")){
			errors++;
			request.setAttribute("erreurChamps", "Vous devez remplir au moins un des deux champs.");
		} else if(!libelle.trim().equals("") && !idStr.trim().equals("")){
			errors++;
			request.setAttribute("erreurChamps", "Vous ne devez remplir qu'un seul champs.");
		}else if(!idStr.trim().equals("") && !idStr.matches("^[-+]?\\d+(\\.\\d+)?$")){
			errors++;
			request.setAttribute("erreurId", "L'identifiant doit être un chiffre.");
		}
		if(errors==0){
			try {
				List<Entite> articles = new ArrayList<Entite>();
				if (!idStr.trim().equals("")){
					
					id = Integer.parseInt(idStr);
					Article article = (Article) ServiceArticle.getInstance().rechercherParId(id);
					articles.add(article);
					request.setAttribute("entites", articles);
					
				}else if(!libelle.trim().equals("")){
					articles = ServiceArticle.getInstance().rechercherParLibelle(libelle);
					request.setAttribute("entites", articles);
					
				}
				target = "listeArticle.jsp";
				request.setAttribute("action", action);
			} catch (ObjetInexistantException e) {
				target = "rechercherArticle.jsp?action="+action;
				request.setAttribute("erreurs", "La recherche n'a retourné aucun résultat");
			}
			
		}else{
			target = "rechercherArticle.jsp?action="+action;
			request.setAttribute("libelle", libelle);
			request.setAttribute("id", idStr);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

}
