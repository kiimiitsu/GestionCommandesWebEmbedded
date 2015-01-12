package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.afpa59.gc.donnees.Article;
import com.afpa59.gc.donnees.Client;
import com.afpa59.gc.donnees.Commande;
import com.afpa59.gc.donnees.LigneCommande;
import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceArticle;
import com.afpa59.gc.services.commun.ServiceClient;
import com.afpa59.gc.services.commun.ServiceLigneCommande;

/**
 * Servlet implementation class LigneCommandeForm
 */
public class LigneCommandeForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LigneCommandeForm() {
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
		HttpSession session = request.getSession();
		
		String action = request.getParameter("action");
		
		String target;
		
		String idStr = request.getParameter("id");
		String idArticleStr = request.getParameter("idArticle").trim();
		String quantiteStr = request.getParameter("quantite");
		
		int errors= 0;
		
		if(idArticleStr.equals("")){
			errors++;
			request.setAttribute("erreurArticle", "L'id de l'article ne doit pas être vide.");
		} else if(!idArticleStr.matches("^[-+]?\\d+(\\.\\d+)?$")){
			errors++;
			request.setAttribute("erreurArticle", "L'identifiant doit être un chiffre.");
		}
		if(quantiteStr.equals("")){
			errors++;
			request.setAttribute("erreurQuantite", "La quantité ne doit pas être vide.");
		} else if(!quantiteStr.matches("^[-+]?\\d+(\\.\\d+)?$")){
			errors++;
			request.setAttribute("erreurQuantite", "La quantité doit être un chiffre.");
		}
		
		
		if(errors==0){
			LigneCommande lc = new LigneCommande();
			try {
				lc.setArticle((Article) ServiceArticle.getInstance().rechercherParId(Integer.parseInt(idArticleStr)));
			
				lc.setQuantite(Integer.parseInt(quantiteStr));
				
				ServiceLigneCommande service = (ServiceLigneCommande) session.getAttribute("service");
				switch (action) {
					case "creer":
						service.creer(lc);
						request.setAttribute("success", "La ligne a bien été créée.");
						break;
					case "modifier":
						int id = Integer.parseInt(idStr);
						lc.setId(id);
						lc.setCommande((Commande) service.getCommande());
						service.modifier(id, lc);
						request.setAttribute("success", "La ligne a bien été modifiée.");
	
						break;
					default:
						request.setAttribute("erreurs", "Une erreur est survenue.");
						break;
				}
			} catch (ObjetInexistantException e) {
				System.out.println(e.getMessage());
			}
		}else{
			request.setAttribute("idArticle", idArticleStr);
			request.setAttribute("quantite", quantiteStr);
		}
		request.setAttribute("action", action);
		target = "formLigneCommande.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}
}
