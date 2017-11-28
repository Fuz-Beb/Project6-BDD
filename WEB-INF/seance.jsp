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
CONTENT="Gestion des séances">
</HEAD>
<BODY>
<CENTER>
<H1>Système de gestion d'un palais de justice</H1>
<H2>Gestion des séances</H2><br>
</CENTER>

<br><a href="Seance?param=retour">Retour</a><br>

<H3>Création</H3>
<%	GestionJustice justiceInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
	ArrayList<TupleProces> listProces = justiceInterrogation.getGestionProces().retourneAllDecisionNull();
	ArrayList<TupleSeance> listSeance = justiceInterrogation.getGestionSeance().retourneAllFutur();
	ArrayList<TupleSeance> listSeance2 = justiceInterrogation.getGestionSeance().retourneAll();


	List<String> listeMessageErreur = new LinkedList<String>();
	Boolean erreur = false;

	if (listProces.size() == 0)
	{
		listeMessageErreur.add("Création impossible : Aucun proces non terminé dans la base de données");
        request.setAttribute("listeMessageErreur", listeMessageErreur);
        erreur = true;
	}

	if (!erreur)
	{ %>
		<TABLE style="border: 1px solid #A4A4A4">
			<tr>
				<td>Id</td>
				<td>Proces</td>
				<td>Date</td>
			</tr>
			<tr>
				<form action="Seance" method="POST">
					<%				
					if (listSeance2.size() == 0)
					{ %>
						<td width="120px"> 0 </td> <%
					}
					else
					{ %>
						<td width="120px"> <%= listSeance2.get(listSeance2.size() - 1).getId() + 1 %> </td><%
					}%>
					
					<td> <SELECT name="selectProces" size="1">
		<%				for (int i = 0; i < listProces.size(); i++)
						{ %>
							<OPTION> <%= listProces.get(i).getId() %> </OPTION>
						<% } %>
					</SELECT></td>

					<td> <input type="text" name="date" placeholder="2017/12/25"/> </td>
					<td><input type="submit" name="Valider" value="Ajouter"></td>
				</form>
			</tr>
		</TABLE>
	<%}%>

<H3>Suppression</H3>
<form action="Seance" method="POST">
	<p>
		<label for="IdASupprimer">Id de la séance à supprimer : </label>
		<SELECT name="IdASupprimer" size="1">
<%
		for (int i = 0; i < listSeance.size(); i++)
		{ %>
			<OPTION> <%= listSeance.get(i).getId() %> </OPTION>
		<% } %>

		</SELECT>
	</p>
	<input type="submit" name="Supprimer" value="Supprimer">
</form>

<H3>Affichage de l'ensemble des séances</H3>
<form action="Seance" method="POST">
	<p>
		<TABLE BORDER=1 WIDTH=600>
			<TR>
				<TH>Identifiant</TH>
				<TH>Proces_id</TH>
				<TH>Date</TH>
			<%
			for (int i = 0 ; i < listSeance2.size(); i++)
			{ %>
			<TR>
				<TD><%= listSeance2.get(i).getId() %></TD>
				<TD><%= listSeance2.get(i).getProces_id() %></TD>
				<TD><%= listSeance2.get(i).getDate().toString() %></TD>
			</TR>
			<% } %>
		</TABLE>
	</p>
</form>

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
