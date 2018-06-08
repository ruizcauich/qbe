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
 * @author Eyden Villanueva
 */
public class editar extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
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
            
            //recolecciona de datos
            String tabla = request.getParameter("tabla");
            String columnas = request.getParameter("columnas");
            String valores = request.getParameter("valores");
            String valoresOriginales = request.getParameter("valoresoriginales");
            
            //out.println(request.getParameter("tabla") + "  " + request.getParameter("columnas")  + "  " + request.getParameter("valores"));
            c.conectar();
            
            String columnasArray[] = columnas.split(",");
            String valoresArray[] = valores.split(",");
            String valoresOriginalesArray[] = valoresOriginales.split(",");
            
            String setCadena = "";
            //armamos cadena set
            for(int i = 0; i < valoresArray.length; i++){
                if(valoresArray.length-1 == i){
                    setCadena+=" "+columnasArray[i]+" = ? ";
                }
                else{setCadena+=" "+columnasArray[i]+" = ?, ";}
            }
            
            String whereCadena = "";
            //armamos cadena del where
            for(int i = 0; i < valoresOriginalesArray.length; i++){
                if(valoresOriginalesArray.length-1 == i){
                    whereCadena+= " "+columnasArray[i]+" = ? ";
                }
                else{whereCadena+=" "+columnasArray[i]+" = ? AND ";}
            }
            
            String query = "UPDATE "+tabla+" SET "+setCadena+" WHERE "+whereCadena;
            
            PreparedStatement ps = c.getConnectionToDB().prepareStatement(query);
            
            int cont_ps = 1;
            
            for(int i = 0; i < valoresArray.length; i++){
                ps.setString(cont_ps, valoresArray[i]);
                cont_ps++;
            }
            
            for(int i = 0; i < valoresOriginalesArray.length; i++){
                ps.setString(cont_ps, valoresOriginalesArray[i]);
                cont_ps++;
            }
            
            int respuesta = ps.executeUpdate();
            out.println(respuesta);
            
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
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(editar.class.getName()).log(Level.SEVERE, null, ex);
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
