package JusticeServlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import Justice.TupleProces;

public class Proces extends HttpServlet
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
                // Si l'utilisateur souhaite ajouter un proces
                if (request.getParameter("Valider") != null)
                {
                    Integer id, juge_id, partieD_id, partieP_id, devantJury;
                    java.util.Date date;
                    java.sql.Date dateSql;

                    // Test si les champs ne sont pas vides
                    if (request.getParameter("date").equals(""))
                    {
                        throw new IFT287Exception("Le champ date ne doit pas être vide.");
                    }

                    try
                    {
                        id = justiceUpdate.getGestionProces().retourneAll()
                                .get(justiceUpdate.getGestionProces().retourneAll().size() - 1).getId() + 1;
                        juge_id = Integer.parseInt(request.getParameter("selectJuge"));
                        partieD_id = Integer.parseInt(request.getParameter("selectPartieD"));
                        partieP_id = Integer.parseInt(request.getParameter("selectPartieP"));

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        dateFormat.setLenient(false);

                        date = (java.util.Date) dateFormat.parse(request.getParameter("date"));
                        dateSql = new java.sql.Date(date.getTime());

                    }
                    catch (NumberFormatException | ParseException e)
                    {
                        throw new IFT287Exception("Le format de la date est incorrect");
                    }

                    if (request.getParameter("selectDevantJury").equals("Vrai"))
                        devantJury = 1;
                    else
                        devantJury = 0;

                    // Ajout du proces
                    synchronized (justiceUpdate)
                    {
                        justiceUpdate.getGestionProces()
                                .creer(new TupleProces(id, juge_id, dateSql, devantJury, partieD_id, partieP_id));
                    }
                }
                else if (request.getParameter("Terminer") != null)
                {
                    // Si il reste des proces non terminés
                    if (request.getParameter("IdATerminer") != null)
                    {
                        Integer decision;

                        if (request.getParameter("Decision").equals("Poursuite gagne"))
                            decision = 1;
                        else
                            decision = 0;

                        synchronized (justiceUpdate)
                        {
                            justiceUpdate.getGestionProces().terminer(
                                    new TupleProces(Integer.parseInt(request.getParameter("IdATerminer"))), decision);
                        }
                    }
                }
                else if (request.getParameter("param") != null)
                {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/accueil.jsp");
                    dispatcher.forward(request, response);
                }

                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/proces.jsp");
                dispatcher.forward(request, response);
            }
            catch (IFT287Exception e)
            {
                List<String> listeMessageErreur = new LinkedList<String>();
                listeMessageErreur.add(e.getMessage());
                request.setAttribute("listeMessageErreur", listeMessageErreur);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/proces.jsp");
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