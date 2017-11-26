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
import Justice.TupleJuge;

public class Juge extends HttpServlet
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
                // Si l'utilisateur souhaite ajouter un juge
                if (request.getParameter("Valider") != null)
                {
                    Integer id, age;
                    String prenom, nom;

                    // Test si les champs ne sont pas vides
                    if (request.getParameter("id").equals("") || request.getParameter("prenom").equals("")
                            || request.getParameter("nom").equals("") || request.getParameter("age").equals(""))
                    {
                        throw new IFT287Exception("Les champs ne doivent pas être vides.");
                    }

                    try
                    {
                        id = Integer.parseInt(request.getParameter("id"));
                        prenom = request.getParameter("prenom");
                        nom = request.getParameter("nom");
                        age = Integer.parseInt(request.getParameter("age"));
                    }
                    catch (NumberFormatException e)
                    {
                        throw new IFT287Exception("Le format de l'id ou de l'age est incorrect");
                    }

                    // Ajout du juge
                    synchronized (gestionUpdate)
                    {
                        gestionUpdate.getGestionJuge().ajouter(new TupleJuge(id, prenom, nom, age));
                    }
                } // Si l'utilisateur souhaite supprimer un juge
                else if (request.getParameter("Supprimer") != null)
                {
                    // Si il reste des juges à supprimer
                    if (request.getParameter("IdASupprimer") != null)
                    {
                        // Supprimer le juge
                        synchronized (gestionUpdate)
                        {
                            gestionUpdate.getGestionJuge()
                                    .retirer(new TupleJuge(Integer.parseInt(request.getParameter("IdASupprimer"))));
                        }
                    }
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/juge.jsp");
                dispatcher.forward(request, response);
            }
            catch (IFT287Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/juge.jsp");
                dispatcher.forward(request, response);
            }
            catch (Exception e)
            {
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