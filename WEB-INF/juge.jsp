<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<td> <input type="text" name="id"/> </td>
			<td> <input type="text" name="prenom"/></td>
			<td><input type="text" name="nom"/></td>
			<td><input type="text" name="sexe"/></td>
			<td><input type="submit" name="Valider" value="Ajouter"></td>
		</form>
	</tr>
</table>
<H3>Suppression</H3>
<form action="Juge" method="POST">
	<p>
		<label for="id">Id du juge à supprimer : </label>
		<SELECT name="id" size="1">
			<OPTION>lundi
			<OPTION>mardi
			<OPTION>mercredi
			<OPTION>jeudi
			<OPTION>vendredi
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

<TR>
<TD>Un id</TD>
<TD>Un prenom</TD>
<TD>Un nom</TD>
<TD>Un age</TD>
</TR>

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