/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.1
 * Generated at: 2017-11-26 18:36:33 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.text.*;
import Justice.*;

public final class juge_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("Justice");
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("java.text");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n");
      out.write("<HTML>\r\n");
      out.write("<HEAD>\r\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">\r\n");
      out.write("<TITLE>IFT287 - Syst�me de gestion d'un palais de justice</TITLE>\r\n");
      out.write("<META HTTP-EQUIV=\"Content-Type\" CONTENT=\"text/html; charset=UTF-8\">\r\n");
      out.write("<META NAME=\"author\" CONTENT=\"Pierrick BOBET\">\r\n");
      out.write("<META NAME=\"author\" CONTENT=\"R�my BOUTELOUP\">\r\n");
      out.write("<META NAME=\"description\"\r\n");
      out.write("CONTENT=\"Gestion des juges\">\r\n");
      out.write("</HEAD>\r\n");
      out.write("<BODY>\r\n");
      out.write("<CENTER>\r\n");
      out.write("<H1>Syst�me de gestion d'un palais de justice</H1>\r\n");
      out.write("<H2>Gestion des juges</H2> <br>\r\n");
      out.write("</CENTER>\r\n");
      out.write("<H3>Cr�ation</H3>\r\n");
      out.write("<TABLE style=\"border: 1px solid #A4A4A4\">\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<td>Id</td>\r\n");
      out.write("\t\t<td>Prenom</td>\r\n");
      out.write("\t\t<td>Nom</td>\r\n");
      out.write("\t\t<td>Age</td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t<form action=\"Juge\" method=\"POST\">\r\n");
			if (request.getParameter("id") != null)
			{ 
      out.write("\r\n");
      out.write("\t\t\t\t<td> <input type=\"text\" name=\"id\" value='");
      out.print( request.getParameter("id") );
      out.write("' /> </td>\r\n");
      out.write("\t\t\t");
}
			else
			{ 
      out.write("\r\n");
      out.write("\t\t\t\t<td> <input type=\"text\" name=\"id\" value=\"\" /> </td>\r\n");
      out.write("\t\t\t");
}

			if (request.getParameter("prenom") != null)
			{ 
      out.write("\r\n");
      out.write("\t\t\t\t<td> <input type=\"text\" name=\"prenom\" value='");
      out.print( request.getParameter("prenom") );
      out.write("' /> </td>\r\n");
      out.write("\t\t\t");
}
			else
			{ 
      out.write("\r\n");
      out.write("\t\t\t\t<td> <input type=\"text\" name=\"prenom\" value=\"\" /> </td>\r\n");
      out.write("\t\t\t");
}
			
			if (request.getParameter("nom") != null)
			{ 
      out.write("\r\n");
      out.write("\t\t\t\t<td> <input type=\"text\" name=\"nom\" value='");
      out.print( request.getParameter("nom") );
      out.write("' /> </td>\r\n");
      out.write("\t\t\t");
}
			else
			{ 
      out.write("\r\n");
      out.write("\t\t\t\t<td> <input type=\"text\" name=\"nom\" value=\"\" /> </td>\r\n");
      out.write("\t\t\t");
}

			if (request.getParameter("age") != null)
			{ 
      out.write("\r\n");
      out.write("\t\t\t\t<td> <input type=\"text\" name=\"age\" value='");
      out.print( request.getParameter("age") );
      out.write("' /> </td>\r\n");
      out.write("\t\t\t");
}
			else
			{ 
      out.write("\r\n");
      out.write("\t\t\t\t<td> <input type=\"text\" name=\"age\" value=\"\" /> </td>\r\n");
      out.write("\t\t\t");
}
			
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<td><input type=\"submit\" name=\"Valider\" value=\"Ajouter\"></td>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
      out.write("<H3>Suppression</H3>\r\n");
      out.write("<form action=\"Juge\" method=\"POST\">\r\n");
      out.write("\t<p>\r\n");
      out.write("\t\t<label for=\"IdASupprimer\">Id du juge � supprimer : </label>\r\n");
      out.write("\t\t<SELECT name=\"IdASupprimer\" size=\"1\">\r\n");

	GestionJustice gestionInterrogation = (GestionJustice) session.getAttribute("justiceInterrogation");
	ArrayList<TupleJuge> list = gestionInterrogation.getGestionJuge().affichage();

	for (int i = 0; i < list.size(); i++)
	{

      out.write("\r\n");
      out.write("\t<OPTION> ");
      out.print( list.get(i).getId() );
      out.write(" </OPTION>\r\n");
      out.write("\r\n");
 } 
      out.write("\r\n");
      out.write("\t\t</SELECT>\r\n");
      out.write("\t</p>\r\n");
      out.write("\t<input type=\"submit\" name=\"Supprimer\" value=\"Supprimer\">\r\n");
      out.write("</form>\r\n");
      out.write("<H3>Liste des juges actifs et disponibles</H3>\r\n");
      out.write("\r\n");
      out.write("<TABLE BORDER=1 WIDTH=600>\r\n");
      out.write("\r\n");
      out.write("<TR>\r\n");
      out.write("<TH>Identifiant</TH>\r\n");
      out.write("<TH>Prenom</TH>\r\n");
      out.write("<TH>Nom</TH>\r\n");
      out.write("<TH>Age</TH>\r\n");
      out.write("</TR>\r\n");
      out.write("\r\n");

	for (int i = 0 ; i < list.size(); i++)
	{

      out.write("\r\n");
      out.write("<TR>\r\n");
      out.write("\t\t<TD>");
      out.print( list.get(i).getId() );
      out.write("</TD>\r\n");
      out.write("\t\t<TD>");
      out.print( list.get(i).getPrenom() );
      out.write("</TD>\r\n");
      out.write("\t\t<TD>");
      out.print( list.get(i).getNom() );
      out.write("</TD>\r\n");
      out.write("\t\t<TD>");
      out.print( list.get(i).getAge() );
      out.write("</TD>\r\n");
      out.write("</TR>\r\n");
 } 
      out.write("\r\n");
      out.write("\r\n");
      out.write("</TABLE>\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/messageErreur.jsp", out, false);
      out.write("\r\n");
      out.write("<br><br><a href=\"Logout\">Sortir</a>\r\n");
      out.write("<BR>\r\n");
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("Date et heure normale de l'est: ");
      out.print( DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.CANADA_FRENCH).format(new java.util.Date()) );
      out.write("\r\n");
      out.write("</BODY>\r\n");
      out.write("</HTML>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
