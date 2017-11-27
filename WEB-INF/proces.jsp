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
CONTENT="Gestion des proces">
</HEAD>
<BODY>
<CENTER>
<H1>Système de gestion d'un palais de justice</H1>
<H2>Gestion des proces</H2> <br>
</CENTER>

<br><a href="Proces?param=retour">Retour</a><br>

<H3>Création</H3>

<%	List<String> listeMessageErreur = new LinkedList<String>();
	Boolean erreur = false;
	
	GestionJustice justiceInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
	ArrayList<TupleJuge> listJuge = justiceInterrogation.getGestionJuge().affichageAll();
	ArrayList<TuplePartie> listPartie = justiceInterrogation.getGestionPartie().retourneAll();
	ArrayList<TupleProces> listProces = justiceInterrogation.getGestionProces().retourneAll();


	if (listJuge.size() == 0)
	{
        listeMessageErreur.add("Création impossible : Aucun juge actif dans la base de données");
        request.setAttribute("listeMessageErreur", listeMessageErreur);
        erreur = true;
	}
	
	if (listPartie.size() == 0 || listPartie.size() == 1)
	{
		listeMessageErreur.add("Création impossible : La base de données doit contenir au moins deux parties");
        request.setAttribute("listeMessageErreur", listeMessageErreur);
        erreur = true;
	}

	if (!erreur)
	{ %>

		<TABLE style="border: 1px solid #A4A4A4">
		<tr>
			<td>Id</td>
			<td>Juge</td>
			<td>Date</td>
			<td>DevantJury</td>
			<td>PartieDefenderesse</td>
			<td>PartiePoursuivant</td>
		</tr>
		<tr>
			<form action="Proces" method="POST">
<%				
			if (listProces.size() == 0)
			{ %>
				<td width="120px"> 0 </td> <%
			}
			else
			{ %>
				<td width="120px"> <%= listProces.get(listProces.size() - 1).getId() + 1 %> </td><%
			}%>
				<td> <SELECT name="selectJuge" size="1">
<%					for (int i = 0; i < listJuge.size(); i++)
					{ %>
						<OPTION> <%= listJuge.get(i).getId() %> </OPTION>
					<% } %>
				</SELECT></td>

			<td> <input type="text" name="date" placeholder="2017/12/25"/> </td>
			<td> <SELECT name="selectDevantJury" size="1">
				<OPTION>Vrai</OPTION>
				<OPTION>Faux</OPTION>
			</SELECT></td>

			<td> <SELECT name="selectPartieD" size="1">
<%					for (int i = 0; i < listPartie.size(); i++)
					{ %>
						<OPTION> <%= listPartie.get(i).getId() %> </OPTION>
					<% } %>
			</SELECT></td>

			<td> <SELECT name="selectPartieP" size="1">
<%					for (int i = 0; i < listPartie.size(); i++)
					{ %>
						<OPTION> <%= listPartie.get(i).getId() %> </OPTION>
					<% } %>
				</SELECT></td>
				<td><input type="submit" name="Valider" value="Ajouter"></td>
			</form>
		</tr>
	</TABLE>
	<%} %>

<H3>Terminer un proces</H3>
<form action="Proces" method="POST">
	<p>
		<label for="IdATerminer">Id du proces à terminer : </label>
		<SELECT name="IdATerminer" size="1">
<%
	ArrayList<TupleProces> list = justiceInterrogation.getGestionProces().retourneAllNonTermine();

	for (int i = 0; i < list.size(); i++)
	{
%>
	<OPTION> <%= list.get(i).getId() %> </OPTION>

<% } %>
		</SELECT>
	</p>
	<p> <label for="Decision">Decision :</label>
  		<SELECT name="Decision" size="1"> 
  			<OPTION>Poursuite gagne</OPTION>
  			<OPTION>Poursuite perd</OPTION>
  		</SELECT>
    </p>

	<input type="submit" name="Terminer" value="Terminer">
</form>

<H3>Affichage de l'ensemble des proces</H3>
	<%list = justiceInterrogation.getGestionProces().retourneAll();%>
<form action="Proces" method="POST">
	<p>
		<TABLE BORDER=1 WIDTH=600>
			<TR>
				<TH>Identifiant</TH>
				<TH>Juge</TH>
				<TH>Date</TH>
				<TH>DevantJury</TH>
				<TH>PartieDefenderesse</TH>
				<TH>PartiePoursuivante</TH>
				<TH>Decision</TH>
			<%
			for (int i = 0 ; i < list.size(); i++)
			{ %>
			<TR>
				<TD><%= list.get(i).getId() %></TD>
				<TD><%= list.get(i).getJuge_id() %></TD>
				<TD><%= list.get(i).getDate().toString() %></TD>
				<TD><%= list.get(i).getDevantJury() %></TD>
				<TD><%= list.get(i).getPartieDefenderesse_id() %></TD>
				<TD><%= list.get(i).getPartiePoursuivant_id() %></TD>
				<TD><%= list.get(i).getDecision() %></TD>
			</TR>
			<% } %>
		</TABLE>
	</p>
</form>

<br><br>
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