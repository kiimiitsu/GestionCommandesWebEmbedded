package servlet;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.afpa59.gc.donnees.Article;
import com.afpa59.gc.donnees.Client;
import com.afpa59.gc.services.commun.ObjetInexistantException;
import com.afpa59.gc.services.commun.ServiceArticle;
import com.afpa59.gc.services.commun.ServiceClient;

/**
 * Servlet implementation class ArticleForm
 */
public class ClientForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientForm() {
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
		
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String adresse = request.getParameter("adresse").trim();
		
		String idStr = request.getParameter("id").trim();
		
		int errors= 0;
		
		if(nom.equals("")){
			errors++;
			request.setAttribute("erreurNom", "Le nom ne doit pas être vide.");
		}
		if(prenom.equals("")){
			errors++;
			request.setAttribute("erreurPrenom", "Le prenom ne doit pas être vide.");
		}
		if(adresse.equals("")){
			errors++;
			request.setAttribute("erreurAdresse", "Le prix ne doit pas être vide.");
		}
		
		
		if(errors==0){
			Client client = new Client();
			client.setNom(nom);
			client.setPrenom(prenom);
			client.setAdresse(adresse);
			
			ServiceClient sc = ServiceClient.getInstance();
			
			switch (action) {
				case "creer":
					sc.creer(client);
					request.setAttribute("success", "Le client a bien été créé.");
					break;
				case "modifier":
					int id = Integer.parseInt(idStr);
					client.setId(id);
					try {
						sc.modifier(id, client);
						request.setAttribute("success", "Le client a bien été modifié.");
					} catch (ObjetInexistantException e) {
						System.out.println(e.getMessage());
					}
					break;
				default:
					request.setAttribute("erreurs", "Une erreur est survenue.");
					break;
			}
			
		}else{
			request.setAttribute("nom", nom);
			request.setAttribute("prenom", prenom);
			request.setAttribute("adresse", adresse);
			request.setAttribute("action", action);
		}
		request.setAttribute("action", action);
		target = "formClient.jsp";
		
		
		RequestDispatcher rd = request.getRequestDispatcher(target);
		rd.forward(request, response);
	}

}
