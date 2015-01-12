package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.afpa59.gc.donnees.Article;
import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceArticle;

/**
 * Servlet implementation class ArticleForm
 */
public class ArticleForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticleForm() {
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
		String action = request.getParameter("action");
		String target;
		
		String libelle = request.getParameter("libelle").trim();
		String prixStr = request.getParameter("prix").trim();
		float prix = 0;
		
		String idStr = request.getParameter("id").trim();
		
		int errors= 0;
		
		if(libelle.equals("")){
			errors++;
			request.setAttribute("erreurLibelle", "Le libellé ne doit pas être vide.");
		}
		if(prixStr.equals("")){
			errors++;
			request.setAttribute("erreurPrix", "Le prix ne doit pas être vide.");
		}else if(!prixStr.matches("^[-+]?\\d+(\\.\\d+)?$")){
			errors++;
			request.setAttribute("erreurPrix", "Le prix doit être un chiffre.");
		}else{
			prix = Float.parseFloat(prixStr);
		}
		
		if(errors==0){
			Article article = new Article();
			article.setLibelle(libelle);
			article.setPrix(prix);
			
			ServiceArticle sa = ServiceArticle.getInstance();
			
			switch (action) {
				case "creer":
					sa.creer(article);
					request.setAttribute("success", "L'article a bien été créé.");
					break;
				case "modifier":
					int id = Integer.parseInt(idStr);
					article.setId(id);
					try {
						sa.modifier(id, article);
						request.setAttribute("success", "L'article a bien été modifié.");
					} catch (ObjetInexistantException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					request.setAttribute("erreurs", "Une erreur est survenue.");
					break;
			}
			
		}else{
			request.setAttribute("libelle", libelle);
			request.setAttribute("prix", prixStr);
			request.setAttribute("action", action);
		}
		request.setAttribute("action", action);
		target = "formArticle.jsp";
		
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

}
