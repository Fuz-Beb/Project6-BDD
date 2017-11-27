<%@ page import="java.util.*,java.text.*,Justice.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>IFT287 - Système de gestion d'un palais de justice</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
<META NAME="author" CONTENT="Pierrick BOBET">
<META NAME="author" CONTENT="Rémy BOUTELOUP">
<META NAME="description"
CONTENT="Page d'accueil système de gestion d'un palais de justice.">
</HEAD>
<BODY>
<CENTER>
<H1>Système de gestion d'un palais de justice</H1>
<H2>Accueil</H2><br><br>
<FORM ACTION="Accueil" METHOD="POST">
    <INPUT TYPE="SUBMIT" NAME="proces"  VALUE="Gestion des proces"> </INPUT>
    <INPUT TYPE="SUBMIT" NAME="jury"  VALUE="Gestion des jurys"> </INPUT>
    <INPUT TYPE="SUBMIT" NAME="juge"  VALUE="Gestion des juges"> </INPUT>
    <INPUT TYPE="SUBMIT" NAME="session"  VALUE="Gestion des sessions"> </INPUT>
    <INPUT TYPE="SUBMIT" NAME="partie"  VALUE="Gestion des parties"> </INPUT>
    <INPUT TYPE="SUBMIT" NAME="avocat"  VALUE="Gestion des avocats"> </INPUT>
</FORM>
<br><br>
<H3>Liste des juges actifs et disponibles</H3>

<TABLE BORDER=1 WIDTH=600>
<TR>
<TH>Identifiant</TH>
<TH>Prenom</TH>
<TH>Nom</TH>
<TH>Age</TH>
</TR>

<%
  GestionJustice gestionInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
  ArrayList<TupleJuge> listJuge = gestionInterrogation.getGestionJuge().affichage();

	for (int i = 0 ; i < listJuge.size(); i++)
	{
%>
<TR>
		<TD><%= listJuge.get(i).getId() %></TD>
		<TD><%= listJuge.get(i).getPrenom() %></TD>
		<TD><%= listJuge.get(i).getNom() %></TD>
		<TD><%= listJuge.get(i).getAge() %></TD>
</TR>
<% } %>

</TABLE>

<H3>Liste des jurys disponibles</H3>

<TABLE BORDER=1 WIDTH=600>

<TR>
<TH>Identifiant</TH>
<TH>Prenom</TH>
<TH>Nom</TH>
<TH>Sexe</TH>
<TH>Age</TH>
<TH>Num. Procès</TH>
</TR>

<%
ArrayList<TupleJury> listJury = gestionInterrogation.getGestionJury().affichage();

	for (int i = 0 ; i < listJury.size(); i++)
	{
%>
<TR>
		<TD><%= listJury.get(i).getNas() %></TD>
		<TD><%= listJury.get(i).getPrenom() %></TD>
		<TD><%= listJury.get(i).getNom() %></TD>
    <TD><%= listJury.get(i).getSexe() %></TD>
		<TD><%= listJury.get(i).getAge() %></TD>
    <TD><%= listJury.get(i).getProces_id() %></TD>
</TR>
<% } %>

</TABLE>
</CENTER>
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
