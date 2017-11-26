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
<H2>Login</H2>
<FORM ACTION="Login" METHOD="POST">
    <BR>
    <BR>
    User Id : <INPUT TYPE="TEXT" NAME="userIdBD" VALUE="postgres">
    <BR>
    <BR>
    Mot de passe : <INPUT TYPE="PASSWORD" NAME="motDePasseBD" VALUE="ubuntu">
    <BR>
    <BR>
    Serveur : <SELECT NAME="serveur">
                <OPTION VALUE="local">local
                <OPTION VALUE="dinf">dinf
              </SELECT>
    <BR>
    <BR>
    <BR>
    BD : <INPUT TYPE="TEXT" NAME="bd"  VALUE="postgres">
<BR>
<BR>
<INPUT TYPE="SUBMIT" VALUE="Soumettre">
</FORM>
</CENTER>
<BR>
<%-- inclusion d'une autre page pour l'affichage des messages d'erreur--%>
<jsp:include page="/WEB-INF/messageErreur.jsp" />
<BR>
<%-- affichage de la date et heure; --%>
<%-- utile pour débogger et verifier si la page a été --%>
<%-- par le fureteur --%>
Date et heure normale de l'est: <%= DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) %>
</BODY>
</HTML>