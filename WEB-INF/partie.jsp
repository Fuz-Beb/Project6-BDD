<%@ page import="java.util.*,java.text.*,Justice.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
  <link rel="stylesheet" type="text/css" href="style.css">
<TITLE>IFT287 - Système de gestion d'un palais de justice</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META NAME="author" CONTENT="Pierrick BOBET">
<META NAME="author" CONTENT="Rémy BOUTELOUP">
<META NAME="description"
CONTENT="Gestion des parties">
</HEAD>
<BODY>
<CENTER>
<H1>Système de gestion d'un palais de justice</H1>
<H2>Gestion des parties</H2>
</CENTER>

<br><a href="Partie?param=retour">Retour</a><br>

<H3>Création</H3>

<% List<String> listeMessageErreur = new LinkedList<String>();
	Boolean erreur = false;

  GestionJustice gestionInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
  ArrayList<TupleAvocat> listAvocat = gestionInterrogation.getGestionAvocat().affichage();
  ArrayList<TuplePartie> listPartie = gestionInterrogation.getGestionPartie().retourneAll();

  if (listAvocat.size() == 0)
	{
        listeMessageErreur.add("Création impossible : Aucun avocat actif dans la base de données");
        request.setAttribute("listeMessageErreur", listeMessageErreur);
        erreur = true;
	}

  if (!erreur)
	{ %>
    <TABLE style="border: 1px solid #A4A4A4">
      <tr>
        <td>Id</td>
    		<td>Prenom</td>
    		<td>Nom</td>
    		<td>Num. Avocat</td>
    	</tr>
      <tr>
        <form action="Partie" method="POST">
        <%        
        if (listPartie.size() == 0)
        { %>
          <td width="120px"> 0 </td> <%
        }
        else
        { %>
          <td width="120px"> <%= listPartie.get(listPartie.size() - 1).getId() + 1 %> </td><%
        }

        if (request.getParameter("prenom") != null)
        { %>
          <td> <input type="text" name="prenom" value='<%= request.getParameter("prenom") %>' /> </td>
        <%}
        else
        { %>
          <td> <input type="text" name="prenom" value="" /> </td>
        <%}

        if (request.getParameter("nom") != null)
        { %>
          <td> <input type="text" name="nom" value='<%= request.getParameter("nom") %>' /> </td>
        <%}
        else
        { %>
          <td> <input type="text" name="nom" value="" /> </td>
        <%}
        %>
        
        <td>
            <SELECT name = "avocat_id" size="1">
            <%
              for (int i = 0; i < listAvocat.size(); i++)
            	{
                %>
                <OPTION> <%= listAvocat.get(i).getId() %> </OPTION>
            <% } %>
            </SELECT>
          </td>
        <td><input type="submit" name="Valider" value="Ajouter"></td>
        </form>
      </tr>
    </table>
	<%} %>
<H3>Liste des parties</H3>

<TABLE BORDER=1 WIDTH=600>

<TR>
<TH>Identifiant</TH>
<TH>Prenom</TH>
<TH>Nom</TH>
<TH>Num. Avocat</TH>
</TR>

<%
	for (int i = 0 ; i < listPartie.size(); i++)
	{
    %>
    <TR>
    		<TD><%= listPartie.get(i).getId() %></TD>
    		<TD><%= listPartie.get(i).getPrenom() %></TD>
    		<TD><%= listPartie.get(i).getNom() %></TD>
    		<TD><%= listPartie.get(i).getAvocat_id() %></TD>
    </TR>
<% } %>

</TABLE>

<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
<a href="Logout">Sortir</a>
<BR>
<%-- affichage de la date et heure; --%>
<%-- utile pour débogger et verifier si la page a été --%>
<%-- par le fureteur --%>
Date et heure normale de l'est: <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
</BODY>
</HTML>
