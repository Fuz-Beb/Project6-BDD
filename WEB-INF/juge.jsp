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
CONTENT="Gestion des juges">
</HEAD>
<BODY>
<CENTER>
<H1>Système de gestion d'un palais de justice</H1>
<H2>Gestion des juges</H2> <br>
</CENTER>

<br><a href="Juge?param=retour">Retour</a><br>

<% 	GestionJustice justiceInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
	ArrayList<TupleJuge> listJuge = justiceInterrogation.getGestionJuge().affichageAllAll();
%>

<H3>Création</H3>
<TABLE style="border: 1px solid #A4A4A4">
	<tr>
		<td>Id</td>
		<td>Prenom</td>
		<td>Nom</td>
		<td>Age</td>
	</tr>
	<tr>
		<form action="Juge" method="POST">
			<%				
			if (listJuge.size() == 0)
			{ %>
				<td width="120px"> 0 </td> <%
			}
			else
			{ %>
				<td width="120px"> <%= listJuge.get(listJuge.size() - 1).getId() + 1 %> </td>
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
<H3>Rendre indisponible un juge</H3>
<form action="Juge" method="POST">
	<p>
		<label for="IdASupprimer">Id du juge à changer : </label>
		<SELECT name="IdASupprimer" size="1">
<%
	ArrayList<TupleJuge> list = justiceInterrogation.getGestionJuge().affichage();

	for (int i = 0; i < list.size(); i++)
	{
%>
	<OPTION> <%= list.get(i).getId() %> </OPTION>

<% } %>
		</SELECT>
	</p>
	<input type="submit" name="Supprimer" value="Supprimer">
</form>
<H3>Liste des juges actifs et disponibles</H3>

<TABLE BORDER=1 WIDTH=600>

<TR>
<TH>Identifiant</TH>
<TH>Prenom</TH>
<TH>Nom</TH>
<TH>Age</TH>
</TR>

<%
	for (int i = 0 ; i < list.size(); i++)
	{
%>
<TR>
		<TD><%= list.get(i).getId() %></TD>
		<TD><%= list.get(i).getPrenom() %></TD>
		<TD><%= list.get(i).getNom() %></TD>
		<TD><%= list.get(i).getAge() %></TD>
</TR>
<% } %>

</TABLE>

<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<br><br><a href="Logout">Sortir</a>
<BR>
<%-- affichage de la date et heure; --%>
<%-- utile pour débogger et verifier si la page a été --%>
<%-- par le fureteur --%>
Date et heure normale de l'est: <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
</BODY>
</HTML>
