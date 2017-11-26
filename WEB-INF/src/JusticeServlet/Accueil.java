package JusticeServlet;

import java.io.IOException;
import java.sql.SQLException;
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

public class Accueil extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        RequestDispatcher dispatcher;

        // Vérification de l'état de la session
        HttpSession session = request.getSession();
        Integer etat = (Integer) session.getAttribute("etat");
        if (etat == null)
        {

            dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            if (request.getParameter("proces") != null)
            {
                dispatcher = request.getRequestDispatcher("/WEB-INF/proces.jsp");
                dispatcher.forward(request, response);
            }
            else if (request.getParameter("jury") != null)
            {
                dispatcher = request.getRequestDispatcher("/WEB-INF/jury.jsp");
                dispatcher.forward(request, response);
            }
            else if (request.getParameter("juge") != null)
            {
                dispatcher = request.getRequestDispatcher("/WEB-INF/juge.jsp");
                dispatcher.forward(request, response);
            }
            else if (request.getParameter("session") != null)
            {
                dispatcher = request.getRequestDispatcher("/WEB-INF/session.jsp");
                dispatcher.forward(request, response);
            }
            else if (request.getParameter("partie") != null)
            {
                dispatcher = request.getRequestDispatcher("/WEB-INF/partie.jsp");
                dispatcher.forward(request, response);
            }
            else if (request.getParameter("avocat") != null)
            {
                dispatcher = request.getRequestDispatcher("/WEB-INF/avocat.jsp");
                dispatcher.forward(request, response);
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