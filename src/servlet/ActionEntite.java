package servlet;


import java.io.IOException;

import javax.persistence.PersistenceException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceArticle;
import com.afpa59.gc.services.commun.ServiceClient;
import com.afpa59.gc.services.commun.ServiceCommande;
import com.afpa59.gc.services.commun.ServiceEntite;

/**
 * Servlet implementation class DeleteArticle
 */
public class ActionEntite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionEntite() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String target = null;
		
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		String action = request.getParameter("action");
		String entite = request.getParameter("entite");
		
		
		ServiceEntite se ;
		
		switch (entite) {
		case "article":
			se = ServiceArticle.getInstance();
			break;
		case "client":
			se = ServiceClient.getInstance();
			break;
		case "commande":
			se = ServiceCommande.getInstance();
			break;
		case "ligneCommande":
			se = (ServiceEntite) request.getSession().getAttribute("service");
			break;
		default:
			se = null;
			break;
		}
		
		
		String entiteUp = entite.substring(0, 1).toUpperCase()+entite.substring(1);
		try{
			switch (action) {
			
				case "visualiser":
				case "modifier":
					request.setAttribute("entite", se.rechercherParId(id));
					request.setAttribute("action", action);
					target = "form"+entiteUp+".jsp";
					break;
				case "supprimer":
					try{
						se.supprimer(id);
						request.setAttribute("success", "La référence a bien été supprimée.");
					}catch (PersistenceException e) {
						System.out.println(e.getMessage());
						request.setAttribute("erreurs", "L'objet est référencé ailleurs, vous ne pouvez pas le supprimer.");
					}
					target = entite+".jsp";
					break;
				default:
					break;
			}
		}catch(ObjetInexistantException e){
			System.out.println(e.getMessage());
			target = entite+".jsp";
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
