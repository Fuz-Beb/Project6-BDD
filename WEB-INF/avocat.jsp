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
CONTENT="Gestion des avocats">
</HEAD>
<BODY>
<CENTER>
<H1>Système de gestion d'un palais de justice</H1>
<H2>Gestion des avocats</H2>
</CENTER>

<br><a href="Avocat?param=retour">Retour</a><br>

<H3>Création</H3>
<TABLE style="border: 1px solid #A4A4A4">
  <tr>
    <td>Id</td>
		<td>Prenom</td>
		<td>Nom</td>
		<td>Type</td>
	</tr>
  <tr>
    <form action="Avocat" method="POST">
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

    if (request.getParameter("type") != null)
    { %>
      <td>
        <SELECT name = "type" size="1">
          <OPTION> 0 </OPTION>
          <OPTION> 1 </OPTION>
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

<H3>Liste des avocats</H3>

<TABLE BORDER=1 WIDTH=600>

<TR>
<TH>Identifiant</TH>
<TH>Prenom</TH>
<TH>Nom</TH>
<TH>Type</TH>
</TR>

<%
GestionJustice gestionInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
ArrayList<TupleAvocat> list = gestionInterrogation.getGestionAvocat().affichage();
	for (int i = 0 ; i < list.size(); i++)
	{
    %>
    <TR>
    		<TD><%= list.get(i).getId() %></TD>
    		<TD><%= list.get(i).getPrenom() %></TD>
    		<TD><%= list.get(i).getNom() %></TD>
    		<TD><%= list.get(i).getType() %></TD>
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
