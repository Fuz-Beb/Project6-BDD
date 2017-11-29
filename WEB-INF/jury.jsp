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
CONTENT="Gestion des jurys">
</HEAD>
<BODY>
<CENTER>
<H1>Système de gestion d'un palais de justice</H1>
<H1>Gestion des jurys</H1>
</CENTER>

<br><a href="Jury?param=retour">Retour</a><br>

<H3>Création</H3>
<TABLE style="border: 1px solid #A4A4A4">
  <tr>
    <td>Nas</td>
		<td>Prenom</td>
		<td>Nom</td>
		<td>Sexe</td>
		<td>Age</td>
	</tr>
  <tr>
    <form action="Jury" method="POST">
    <% if (request.getParameter("nas") != null)
    { %>
      <td> <input type="text" name="nas" value='<%= request.getParameter("nas") %>' /> </td>
    <%}
    else
    { %>
      <td> <input type="text" name="nas" value="" /> </td>
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
    <%}%>

    <td>
      <SELECT name = "sexe" size="1">
        <OPTION> M </OPTION>
        <OPTION> F </OPTION>
      </SELECT>
    </td>

    <%
    if (request.getParameter("age") != null)
    { %>
      <td> <input type="text" name="age" value='<%= request.getParameter("age") %>' /> </td>
    <%}
    else
    { %>
      <td> <input type="text" name="age" value="" /> </td>
    <%}
    %>
    <td><input type="submit" name="Valider" value="Ajouter"></td>
    </form>
  </tr>
</table>

<H3>Assigner</H3>
<form action="Jury" method="POST">
  <p>
    <label for="NasAssigner">NAS du jury à assigner : </label>
    <SELECT name = "NasAssigner" size="1">
    <%
    	GestionJustice gestionInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
    	ArrayList<TupleJury> list = gestionInterrogation.getGestionJury().affichage();

    	for (int i = 0; i < list.size(); i++)
    	{
        %>
        <OPTION> <%= list.get(i).getNas() %> </OPTION>

    <% } %>
		</SELECT>

    <label for="ProcesAssigner">au procès numéro</label>
    <SELECT name = "ProcesAssigner" size="1">
    <%
    	ArrayList<TupleProces> listProces = gestionInterrogation.getGestionProces().retourneAllDecisionNull();

    	for (int i = 0; i < listProces.size(); i++)
    	{
    %>
    	<OPTION> <%= listProces.get(i).getId() %> </OPTION>

    <% } %>
		</SELECT>
	</p>
  	<input type="submit" name="Assigner" value="Assigner">
</form>



<H3>Liste des jurys disponibles</H3>

<TABLE BORDER=1 WIDTH=600>

<TR>
<TH>NAS</TH>
<TH>Prenom</TH>
<TH>Nom</TH>
<TH>Sexe</TH>
<TH>Age</TH>
<TH>Num. Procès</TH>
</TR>

<%
	for (int i = 0 ; i < list.size(); i++)
	{
%>
<TR>
		<TD><%= list.get(i).getNas() %></TD>
		<TD><%= list.get(i).getPrenom() %></TD>
		<TD><%= list.get(i).getNom() %></TD>
    <TD><%= list.get(i).getSexe() %></TD>
		<TD><%= list.get(i).getAge() %></TD>
    <TD><%= list.get(i).getProces_id() %></TD>
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
