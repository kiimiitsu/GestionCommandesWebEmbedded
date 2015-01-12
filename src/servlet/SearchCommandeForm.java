package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.afpa59.gc.donnees.Commande;
import com.afpa59.gc.donnees.Entite;
import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceCommande;

/**
 * Servlet implementation class SearchCommandeForm
 */
public class SearchCommandeForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchCommandeForm() {
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
		String nomClient = request.getParameter("nomClient").trim();
		String action = request.getParameter("action");
		
		int id = 0;
		if(nomClient.trim().equals("") && idStr.trim().equals("")){
			errors++;
			request.setAttribute("erreurChamps", "Vous devez remplir au moins un des deux champs.");
		} else if(!nomClient.equals("") && !idStr.equals("")){
			errors++;
			request.setAttribute("erreurChamps", "Vous ne devez remplir qu'un seul champs.");
		}else if(!idStr.trim().equals("") && !idStr.matches("^[-+]?\\d+(\\.\\d+)?$")){
			errors++;
			request.setAttribute("erreurId", "L'identifiant doit être un chiffre.");
		}
		if(errors==0){
			try {
				List<Entite> commandes = new ArrayList<Entite>();
				if (!idStr.equals("")){
					
					id = Integer.parseInt(idStr);
					Commande commande = (Commande) ServiceCommande.getInstance().rechercherParId(id);
					
					commandes.add(commande);
					request.setAttribute("entites", commandes);
					
				}else if(!nomClient.trim().equals("")){
					commandes = ServiceCommande.getInstance().rechercherParClient(nomClient);
					request.setAttribute("entites", commandes);
					
				}
				target = "listeCommande.jsp";
				request.setAttribute("action", action);
			} catch (ObjetInexistantException e) {
				target = "rechercherCommande.jsp?action="+action;
				request.setAttribute("erreurs", "La recherche n'a retourné aucun résultat");
			}
			
		}else{
			target = "rechercherCommande.jsp?action="+action;
			request.setAttribute("nomClient", nomClient);
			request.setAttribute("id", idStr);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

}
