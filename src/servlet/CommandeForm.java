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
import com.afpa59.gc.outils.BASETYPE;
import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceArticle;
import com.afpa59.gc.services.commun.ServiceClient;
import com.afpa59.gc.services.commun.ServiceCommande;
import com.afpa59.gc.services.commun.ServiceEntiteBase;
import com.afpa59.gc.services.commun.ServiceLigneCommande;

/**
 * Servlet implementation class CommandeForm
 */
public class CommandeForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommandeForm() {
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
		HttpSession session = request.getSession();
		String target = null;
		
		String idStr = request.getParameter("id").trim();
		String idClientStr = request.getParameter("idClient").trim();
		int id;
		
		int errors= 0;
		
		if(idClientStr.equals("")){
			errors++;
			request.setAttribute("erreurClient", "L'id du client ne doit pas être vide.");
		} else if(!idClientStr.matches("^[-+]?\\d+(\\.\\d+)?$")){
			errors++;
			request.setAttribute("erreurClient", "L'identifiant doit être un chiffre.");
		}
		
		if(errors==0){
			Commande commande = new Commande();
			try {
				commande.setClient((Client) ServiceClient.getInstance().rechercherParId(Integer.parseInt(idClientStr)));
			
				ServiceCommande service = ServiceCommande.getInstance();
				ServiceLigneCommande sLC;
				switch (action) {
					case "creer":
						service.creer(commande);

						sLC = new ServiceLigneCommande(commande);
						session.setAttribute("service", sLC);
						request.setAttribute("success", "La commande a bien été créée.");
						//request.setAttribute("sLC", sLC);
						request.setAttribute("action", action);
						target = "formLigneCommande.jsp";
						break;
					case "visualiser":
						id = Integer.parseInt(idStr);
						commande = (Commande) service.rechercherParId(id);
						
						sLC = new ServiceLigneCommande(commande);
						session.setAttribute("service", sLC);
						request.setAttribute("entites", sLC.getEntites());
						request.setAttribute("action", "liste");
						//request.setAttribute("success", "La commande a bien été créée.");
						target = "listeLigneCommande.jsp";
						break;
					case "modifier":
						id = Integer.parseInt(idStr);
						commande = (Commande) service.rechercherParId(id);
						
						sLC = new ServiceLigneCommande(commande);
						
						session.setAttribute("service", sLC);
						request.setAttribute("action", action);
						target="ligneCommande.jsp";
						break;
					default:
						request.setAttribute("erreurs", "Une erreur est survenue.");
						break;
				}
			} catch (ObjetInexistantException e) {
				System.out.println(e.getMessage());
				target = "formLigneCommande.jsp";
				request.setAttribute("erreurs", "Une erreur est survenue.");
			}
			
		}else{
			request.setAttribute("idClient", idClientStr);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

}
