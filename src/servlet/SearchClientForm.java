package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.afpa59.gc.donnees.Client;
import com.afpa59.gc.donnees.Entite;
import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceClient;

/**
 * Servlet implementation class SearchForm
 */
public class SearchClientForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchClientForm() {
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
		String nom = request.getParameter("nom").trim();
		String action = request.getParameter("action");
		
		int id = 0;
		if(nom.trim().equals("") && idStr.trim().equals("")){
			errors++;
			request.setAttribute("erreurChamps", "Vous devez remplir au moins un des deux champs.");
		} else if(!nom.trim().equals("") && !idStr.trim().equals("")){
			errors++;
			request.setAttribute("erreurChamps", "Vous ne devez remplir qu'un seul champs.");
		}else if(!idStr.trim().equals("") && !idStr.matches("^[-+]?\\d+(\\.\\d+)?$")){
			errors++;
			request.setAttribute("erreurId", "L'identifiant doit être un chiffre.");
		}
		if(errors==0){
			try {
				List<Entite> clients = new ArrayList<Entite>();
				if (!idStr.trim().equals("")){
					
					id = Integer.parseInt(idStr);
					Client client = (Client) ServiceClient.getInstance().rechercherParId(id);
					clients.add(client);
					request.setAttribute("entites", clients);
					
				}else if(!nom.trim().equals("")){
					clients = ServiceClient.getInstance().rechercherParNom(nom);
					
					request.setAttribute("entites", clients);
					
				}
				target = "listeClient.jsp";
				request.setAttribute("action", action);
			} catch (ObjetInexistantException e) {
				target = "rechercherClient.jsp?action="+action;
				request.setAttribute("erreurs", "La recherche n'a retourné aucun résultat");
			}
			
		}else{
			target = "rechercherClient.jsp?action="+action;
			request.setAttribute("nom", nom);
			request.setAttribute("id", idStr);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

}
