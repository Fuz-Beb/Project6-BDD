<%@ page import="java.util.*,java.text.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<a href="Proces">Gestion proces</a> <br><br>
<a href="Jury">Gestion jury</a> <br><br>
<a href="Juge">Gestion juge</a> <br><br>
<a href="Session">Gestion session</a> <br><br>
<a href="Partie">Gestion partie</a> <br><br>
<a href="Avocat">Gestion avocat</a> <br><br>

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

<H3>Liste des jurys disponibles</H3>

<TABLE BORDER=1 WIDTH=600>

<TR>
<TH>Identifiant</TH>
<TH>Prenom</TH>
<TH>Nom</TH>
<TH>Sexe</TH>
<TH>Age</TH>
</TR>

<TR>
<TD>Un id</TD>
<TD>Un prenom</TD>
<TD>Un nom</TD>
<TD>Un sexe</TD>
<TD>Un age</TD>
</TR>

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