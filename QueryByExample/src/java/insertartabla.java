/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException
             {
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
                
                    c.conectar();
                

                    String valuesArray [] = values.split(",");
                    String valuesQuery = "";

                    for(int i = 0; i < valuesArray.length; i++){
                        if(valuesArray.length-1 == i){
                            valuesQuery+= "?";
                        }
                        else{
                            valuesQuery+="?,";
                        }
                    }


                    String query = "INSERT INTO " + tabla + " ("+ into + " ) VALUES (" + valuesQuery + " )" ;

                    PreparedStatement ps = c.getConnectionToDB().prepareStatement(query);

                    for(int i = 0; i < valuesArray.length; i++){
                        ps.setString(i+1, valuesArray[i]);
                    }


                    int respuesta = ps.executeUpdate();



                    out.println(respuesta);
                }
                else {
                out.println(0);
                
            }
            
                
            
            c.desconectar();
            
            
        } catch (IOException ex) {
            Logger.getLogger(insertartabla.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(insertartabla.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(insertartabla.class.getName()).log(Level.SEVERE, null, ex);
        }
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
