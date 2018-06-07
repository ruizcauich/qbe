/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gerar
 */
public class insertartabla extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String host,  us, pass, baseDatos;
            int puerto = 0;
            host = request.getSession().getAttribute("host").toString();
            us = request.getSession().getAttribute("usuario").toString();
            pass = request.getSession().getAttribute("contrasena").toString();
            baseDatos = request.getSession().getAttribute("basedatos").toString();
            puerto = Integer.valueOf(request.getSession().getAttribute("puerto").toString());
            
            Conexion c = new Conexion(host, puerto, us, pass, baseDatos);
            
            //recoleccion de datos
            String tabla = request.getParameter("tabla");
            String into = request.getParameter("into");
            String values = request.getParameter("values");
            
            int sizeCol = into.split(",").length;
            int sizeVal = values.split(",").length;
            
            if(!values.equals("") && sizeCol == sizeVal){
                String query = "INSERT INTO " + tabla + " ("+ into + " ) VALUES (" + values + " )" ;
                
                PreparedStatement ps = c.getConnectionToDB().prepareStatement(query);
                
                
                
                boolean respuesta = c.ejecutarInstruccion(query);
                
                out.println(respuesta);
                
            } else {
                out.println("false");
                
            }
            
            
            
            c.desconectar();
            
            
        }
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
