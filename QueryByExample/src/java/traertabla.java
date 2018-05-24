/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author augusto
 */
public class traertabla extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String host,  us, pass, baseDatos;
            int puerto = 0;
            host = request.getSession().getAttribute("host").toString();
            us = request.getSession().getAttribute("usuario").toString();
            pass = request.getSession().getAttribute("contrasena").toString();
            baseDatos = request.getSession().getAttribute("basedatos").toString();
            puerto = Integer.valueOf(request.getSession().getAttribute("puerto").toString());
            
            Conexion c = new Conexion(host, puerto, us, pass, baseDatos);
            ArrayList<ArrayList> datos = c.ejecutarConsulta("SELECT * FROM " + request.getParameter("tabla"));
            c.desconectar();
            
            //out.println("{\""+request.getParameter("tabla")+"\":"+datos.size()+"}");
            out.println(this.convertirAJSON(datos));
            
        }
    }
    private String convertirAJSON(ArrayList<ArrayList> ar){
        String  json= "[";
        for(int row = 0; row<ar.size(); row++){
            String columnas = "[";
            for(int i=0; i<ar.get(row).size(); i++){
                columnas+= "\""+ar.get(row).get(i)+"\"";
                if(!((i+1)== ar.get(row).size()))
                    columnas+=",";
            }
            columnas += "]";
            json+=columnas;
            if( !((row+1)== ar.size()) )
                json+=",";
        }
        
        
        return json+"]";
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
