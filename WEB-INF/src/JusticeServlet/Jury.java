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
import Justice.TupleJury;
import Justice.TupleProces;

public class Jury extends HttpServlet
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
                // Si l'utilisateur souhaite ajouter un jury
                if (request.getParameter("Valider") != null)
                {
                    Integer nas, age;
                    String prenom, nom, sexe;
                    
                    // Test si les champs ne sont pas vides
                    if (request.getParameter("nas").equals("") || request.getParameter("prenom").equals("") || request.getParameter("nom").equals("") || request.getParameter("sexe").equals(""))
                    {
                        throw new IFT287Exception("Les champs ne doivent pas être vides.");
                    }
                    
                    try
                    {
                        nas = Integer.parseInt(request.getParameter("nas"));
                        prenom = request.getParameter("prenom");
                        nom = request.getParameter("nom");
                        sexe = request.getParameter("sexe");
                        age = Integer.parseInt(request.getParameter("age"));
                    }
                    catch (NumberFormatException e)
                    {
                        e.printStackTrace();
                        throw new IFT287Exception("Le format de l'id ou de l'age est incorrect");
                    }
                    
                    // Ajout du jury
                    synchronized (gestionUpdate)
                    {
                        gestionUpdate.getGestionJury().ajouter(new TupleJury(nas, prenom, nom, sexe, age));
                    }
                }
                else if (request.getParameter("Assigner") != null)
                {
                    // Si il existe des jury
                    if (request.getParameter("NasAssigner") != null)
                    {
                        if (request.getParameter("ProcesAssigner") != null)
                        {
                            // Assigner le jury
                            synchronized (gestionUpdate)
                            {
                                gestionUpdate.getGestionJury().assignerProces(new TupleJury(Integer.parseInt(request.getParameter("NasAssigner"))), new TupleProces(Integer.parseInt(request.getParameter("ProcesAssigner"))));
                            }    
                        }
                    }
                }
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jury.jsp");
                dispatcher.forward(request, response);
            }
            catch (IFT287Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jury.jsp");
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