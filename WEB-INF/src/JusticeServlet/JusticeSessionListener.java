package JusticeServlet;

import javax.servlet.http.*;

import Justice.GestionJustice;

import java.sql.*;

public class JusticeSessionListener implements HttpSessionListener
{
    public void sessionCreated(HttpSessionEvent se)
    {
    }

    public void sessionDestroyed(HttpSessionEvent se)
    {
        System.out.println("JusticeSessionListener " + se.getSession().getId());

        GestionJustice justiceInterrogation = (GestionJustice) se.getSession().getAttribute("justiceInterrogation");
        if (justiceInterrogation != null)
        {
            System.out.println("connexion =" + justiceInterrogation.getCx());
            try
            {
                justiceInterrogation.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Justice inaccessible.");
        }

        GestionJustice justiceUpdate = (GestionJustice) se.getSession().getAttribute("justiceUpdate");
        if (justiceUpdate != null)
        {
            System.out.println("connexion = " + justiceUpdate.getCx());
            try
            {
                justiceUpdate.fermer();
            }
            catch (SQLException e)
            {
                System.out.println("Impossible de fermer la connexion");
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("Justice inaccessible.");
        }
    }
}