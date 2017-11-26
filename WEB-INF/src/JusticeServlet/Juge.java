package JusticeServlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Juge extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
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
            if (request.getParameter("Valider") != null)
            {
                String id = request.getParameter("id");
                String prenom = request.getParameter("prenom");
                String nom = request.getParameter("nom");
                String age = request.getParameter("age");

                // AJOUTER LE JUGE
            }
            else if (request.getParameter("Supprimer") != null)
            {
                System.out.println("SUPPRIMER");
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