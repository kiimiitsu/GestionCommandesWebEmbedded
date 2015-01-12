package servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.afpa59.gc.donnees.Commande;
import com.afpa59.gc.donnees.Entite;
import com.afpa59.gc.donnees.LigneCommande;
import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceCommande;
import com.afpa59.gc.services.commun.ServiceLigneCommande;

/**
 * Servlet implementation class SearchLigneCommande
 */
public class SearchLigneCommandeForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchLigneCommandeForm() {
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
		HttpSession session = request.getSession();
		ServiceLigneCommande service = (ServiceLigneCommande) session.getAttribute("service");
		String target = null;
		
		String idStr = request.getParameter("id").trim();
		String nomArticle = request.getParameter("nomArticle").trim();
		String action = request.getParameter("action");
		
		int id = 0;
		if(nomArticle.trim().equals("") && idStr.trim().equals("")){
			errors++;
			request.setAttribute("erreurChamps", "Vous devez remplir au moins un des deux champs.");
		} else if(!nomArticle.equals("") && !idStr.equals("")){
			errors++;
			request.setAttribute("erreurChamps", "Vous ne devez remplir qu'un seul champs.");
		}else if(!idStr.trim().equals("") && !idStr.matches("^[-+]?\\d+(\\.\\d+)?$")){
			errors++;
			request.setAttribute("erreurId", "L'identifiant doit être un chiffre.");
		}
		if(errors==0){
			try {
				List<Entite> lignes = new ArrayList<Entite>();
				if (!idStr.equals("")){
					
					id = Integer.parseInt(idStr);
					LigneCommande ligne = (LigneCommande) service.rechercherParId(id);
					
					lignes.add(ligne);
					request.setAttribute("entites", lignes);
					
				}else if(!nomArticle.trim().equals("")){
					lignes = service.rechercherParArticle(nomArticle);
					request.setAttribute("entites", lignes);
					
				}
				target = "listeLigneCommande.jsp";
				request.setAttribute("action", action);
			} catch (ObjetInexistantException e) {
				target = "rechercherLigneCommande.jsp?action="+action;
				request.setAttribute("erreurs", "La recherche n'a retourné aucun résultat");
			}
			
		}else{
			target = "rechercherLigneCommande.jsp?action="+action;
			request.setAttribute("nomArticle", nomArticle);
			request.setAttribute("id", idStr);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

}
