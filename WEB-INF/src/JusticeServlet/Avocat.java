package JusticeServlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Justice.GestionJustice;
import Justice.IFT287Exception;
import Justice.TupleAvocat;

public class Avocat extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        GestionJustice gestionUpdate = (GestionJustice) request.getSession().getAttribute("justiceUpdate");
        
        // Vérification de l'état de la session
        HttpSession session = request.getSession();
        Integer etat = (Integer) session.getAttribute("etat");
        if (etat == null)
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            
            try
            {
                // Si l'utilisateur souhaite ajouter un avocat
                if (request.getParameter("Valider") != null)
                {
                    Integer id, type;
                    String prenom, nom;
                
                    // Test si les champs ne sont pas vides
                    if (request.getParameter("id").equals("") || request.getParameter("prenom").equals("") || request.getParameter("nom").equals("") || request.getParameter("type").equals(""))
                    {
                        throw new IFT287Exception("Les champs ne doivent pas être vides.");
                    }
                    
                    try
                    {
                        id = Integer.parseInt(request.getParameter("id"));
                        prenom = request.getParameter("prenom");
                        nom = request.getParameter("nom");
                        type = Integer.parseInt(request.getParameter("type"));
                    }
                    catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                        throw new IFT287Exception("Le format de l'id est incorrect");
                    }
                    
                    // Ajout du jury
                    synchronized (gestionUpdate)
                    {
                        gestionUpdate.getGestionAvocat().ajouter(new TupleAvocat(id, prenom, nom, type));
                    }
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/avocat.jsp");
                dispatcher.forward(request, response);
            }
            catch (IFT287Exception e)
            {
                e.printStackTrace();
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/avocat.jsp");
                dispatcher.forward(request, response);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.toString());
            }
        }
    }

    // Dans les formulaires, on utilise la méthode POST
    // donc, si le servlet est appelé avec la méthode GET
    // on appelle POST
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }
}