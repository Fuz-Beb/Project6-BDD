<%@ page import="java.util.*,java.text.*,Justice.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
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

<H3>Création</H3>
<TABLE style="border: 1px solid #A4A4A4">
  <tr>
    <td>Id</td>
		<td>Prenom</td>
		<td>Nom</td>
		<td>Num. Avocat</td>
	</tr>
  <tr>
    <form action="Partie" method="POST">
<%    if (request.getParameter("id") != null)
    { %>
      <td> <input type="text" name="id" value='<%= request.getParameter("id") %>' /> </td>
    <%}
    else
    { %>
      <td> <input type="text" name="id" value="" /> </td>
    <%}

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

    if (request.getParameter("avocat_id") != null)
    { %>
      <td>
        <SELECT name = "type" size="1">
        <%
        	GestionJustice gestionInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
        	ArrayList<TupleAvocat> list = gestionInterrogation.getGestionAvocat().affichage();

          for (int i = 0; i < list.size(); i++)
        	{
            %>
            <OPTION> <%= list.get(i).getId() %> </OPTION>
        <% } %>
        </SELECT>
      </td>
    <%}
    else
    { %>
      <td>
        <SELECT name = "type" size="1">
          <OPTION> 0 </OPTION>
          <OPTION> 1 </OPTION>
        </SELECT>
      </td>
    <%}
    %>
    <td><input type="submit" name="Valider" value="Ajouter"></td>
    </form>
  </tr>
</table>


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
