package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import db.Conexion;

public final class bddisponibles_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write('\n');
      out.write('\n');
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    if( session.getAttribute("usuario")==null ){
        response.sendRedirect("index.html");
    }
    
    Conexion c = new Conexion(
            request.getSession().getAttribute("host").toString(),
            Integer.valueOf(request.getSession().getAttribute("puerto").toString()),
            request.getSession().getAttribute("usuario").toString(),
            request.getSession().getAttribute("contrasena").toString()
    );
    
    c.conectar();
    ArrayList<String> bds = c.getNombreBasesDeDatos();
    c.desconectar();

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Expression Language</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/css/botones.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/css/estilos.css\">\n");
      out.write("        </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <div class=\"menu-usuario\">\n");
      out.write("                <ul>\n");
      out.write("                    <li >\n");
      out.write("                                <img src=\"assets/img/avatar.png\">\n");
      out.write("                                <span>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.usuario}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("</span>\n");
      out.write("                       \n");
      out.write("                        \n");
      out.write("                    </li>\n");
      out.write("                    <li>\n");
      out.write("                        <a href=\"logout\"> Cerrar Sesion</a>\n");
      out.write("                    </li>\n");
      out.write("                </ul>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                \n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("        <main>\n");
      out.write("            \n");
      out.write("            <h1>Bienvenido ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.usuario}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" </h1>\n");
      out.write("            <p>\n");
      out.write("                SELECCIONE LA BASE DE DATOS CON LA QUE TRABAJARÁ\n");
      out.write("            </p>\n");
      out.write("           ");

              for(int i=0; i<bds.size();i++){
                 
               
           
      out.write(" \n");
      out.write("           <div class=\"tarjeta\">\n");
      out.write("               <div class=\"cuerpo-tarjeta\">\n");
      out.write("                    <h3>");
      out.print(bds.get(i));
      out.write("</h3>\n");
      out.write("                    \n");
      out.write("                    <a class=\"boton boton-exito\" href=\"usarbd?bd=");
      out.print(bds.get(i));
      out.write("\">Usar</a>\n");
      out.write("               </div>\n");
      out.write("               \n");
      out.write("           </div>\n");
      out.write("           ");
}
      out.write("\n");
      out.write("           \n");
      out.write("        </main>\n");
      out.write("        <footer class=\"page-footer\">\n");
      out.write("            <p>Usted se encuentra conectado a ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${sessionScope.host}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("               \n");
      out.write("            </p>\n");
      out.write("        </footer>\n");
      out.write("        \n");
      out.write("                \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
