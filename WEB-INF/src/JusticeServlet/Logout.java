package JusticeServlet;

import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import Justice.GestionJustice;

public class Logout extends HttpServlet
{
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("Procédure de déconnection");
        // Fermer la connexion à la base de donnée
        GestionJustice justiceUpdate = (GestionJustice) request.getSession().getAttribute("justiceUpdate");

        try
        {
            justiceUpdate.fermer();
        }
        catch (SQLException e)
        {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            List<String> listeMessageErreur = new LinkedList<String>();
            listeMessageErreur.add("Erreur dans la déconnection à la base de données");
            listeMessageErreur.add(e.toString());
            request.setAttribute("listeMessageErreur", listeMessageErreur);

            e.printStackTrace();
        }
        finally
        {
            // invalider la session pour libérer les ressources associées à la
            // session
            request.getSession().invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }
}