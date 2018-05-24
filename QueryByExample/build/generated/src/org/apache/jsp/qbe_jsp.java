package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import db.Conexion;

public final class qbe_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    if( session.getAttribute("usuario")==null ){
        response.sendRedirect("index.html");
    }
    else if( session.getAttribute("basedatos")==null){
        response.sendRedirect("bddisponibles.jsp");
    }
    try{
    Conexion c = new Conexion(
            request.getSession().getAttribute("host").toString(),
            Integer.valueOf(request.getSession().getAttribute("puerto").toString()),
            request.getSession().getAttribute("usuario").toString(),
            request.getSession().getAttribute("contrasena").toString(),
            request.getSession().getAttribute("basedatos").toString()
    );
    
    c.conectar();
    ArrayList<String> bds = c.getNombreTablas();
    c.desconectar();

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/css/estilos.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"assets/css/botones.css\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/icon?family=Material+Icons\" rel=\"stylesheet\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <ul class=\"show-tables\">\n");
      out.write("            <h1>Tablas:</h1>\n");
      out.write("            ");
 for(int i=0; i<bds.size(); i++){
      out.write("\n");
      out.write("            <li><button class=\"table-button\">");
      out.print( bds.get(i));
      out.write("</button></li>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        </ul>\n");
      out.write("        <div id=\"area-principal\">\n");
      out.write("            <div class=\"toolbar\">\n");
      out.write("            <button class=\"control\" style=\"font-size:24px\"><i class=\"material-icons\">done</i></button>\n");
      out.write("            </div>\n");
      out.write("            <div id=\"trabajo\">\n");
      out.write("        \n");
      out.write("                </div>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("\n");
      out.write("\n");
      out.write("        <script src=\"assets/js/qbe.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
}catch(Exception e){}
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
