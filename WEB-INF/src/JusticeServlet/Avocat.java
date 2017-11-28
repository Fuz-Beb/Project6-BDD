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
        GestionJustice justiceUpdate = (GestionJustice) request.getSession().getAttribute("justiceUpdate");

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
                    if (request.getParameter("prenom").equals("") || request.getParameter("nom").equals("")
                            || request.getParameter("type").equals(""))
                    {
                        throw new IFT287Exception("Les champs ne doivent pas être vides.");
                    }

                    if (justiceUpdate.getGestionAvocat().affichage().size() == 0)
                        id = 0;
                    else
                        id = justiceUpdate.getGestionAvocat().affichage()
                                .get(justiceUpdate.getGestionAvocat().affichage().size() - 1).getId() + 1;
                    prenom = request.getParameter("prenom");
                    nom = request.getParameter("nom");
                    type = Integer.parseInt(request.getParameter("type"));

                    // Ajout du jury
                    synchronized (justiceUpdate)
                    {
                        justiceUpdate.getGestionAvocat().ajouter(new TupleAvocat(id, prenom, nom, type));
                    }
                }
                else if (request.getParameter("param") != null)
                {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
                    dispatcher.forward(request, response);
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