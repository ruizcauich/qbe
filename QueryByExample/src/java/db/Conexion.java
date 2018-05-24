/*
* Nombre del archivo: Conexion.java
* Descripción: Clase que representa una conexión a la base de datos
*               del proyecto;
* Autor (es): Augusto Neftalí Ruiz Cauich
* Fecha de realización: 07-Novienbre-2017
*/

package db;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    //Configuracion de los datos de la BD
    private String usuario = "";
    private String pass = "";
    private String host = "";
    private int puerto = 3306;
    private String nombre_BD = "";
    
    private Connection con = null;
    private Statement st;
    public Connection getConnectionToDB(){
        return con;
    }
    public Conexion(String host,int puerto, String us, String pass, String baseDatos) {
        this.host = host;
        this.usuario = us;
        this.pass = pass;
        this.nombre_BD = baseDatos;  
        this.puerto = puerto;
    }
    
     public Conexion(String host,int puerto, String us, String pass) {
        this.host = host;
        this.usuario = us;
        this.pass = pass;
        this.puerto = puerto;
    }
    
    public void desconectar(){
        try {
            if(!con.isClosed()) con.close();
            if(!st.isClosed()) st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance( );
            String servidor = "jdbc:mysql://"+host+":"+puerto+"/" + nombre_BD;
            con = DriverManager.getConnection(servidor,usuario,pass);
            st = con.createStatement();
            
        }catch(Exception e){
            con =null;
            e.printStackTrace();
            String es = e.getMessage();
            System.out.print(es);
        }
    }
    public boolean hayConexion() {
        return (con!=null );
    }
    public ArrayList<ArrayList> ejecutarConsulta( String query){
        ArrayList<ArrayList> datos = new ArrayList<ArrayList>();
         try {
             conectar();
             ResultSet rs = st.executeQuery(query);
             datos.add(this.getNombreCampos(rs));
             while( rs.next() ){
                 ArrayList row = new ArrayList();
                 for(int i = 0; i< datos.get(0).size(); i++){
                     row.add( rs.getString(datos.get(0).get(i).toString()));
                 }
                 datos.add(row);
             }
             
        } catch (Exception e) {
            e.printStackTrace();
            
        }
         return datos;
    }
    
    
    private ArrayList<String> getNombreCampos(ResultSet rs){
        ArrayList<String> fields= new ArrayList<String>();
        
        try {
             
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int numberOfColumns = rsMetaData.getColumnCount();
            for (int i = 1; i < numberOfColumns + 1; i++) {
                String columnName = rsMetaData.getColumnName(i);
                fields.add(columnName);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e){
        }
        return fields;
        
        
    }   
    
    
    
    public boolean ejecutarInstruccion(String query){
        try {
            conectar();
            st.executeUpdate(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public ArrayList<String> getNombreBasesDeDatos(){
        ArrayList<String> bdts= new ArrayList<String>();
        DatabaseMetaData dbmd;
        try {
            dbmd = con.getMetaData();
            ResultSet ctlgs = dbmd.getCatalogs();
            while (ctlgs.next())
            {
                bdts.add( ctlgs.getString(1) );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e){
        }
        return bdts;
        
        
    }
    public ArrayList<String> getNombreTablas(){
        ArrayList<String> bdts= new ArrayList<String>();
        DatabaseMetaData dbmd;
        try {
            dbmd = con.getMetaData();
            ResultSet chms = dbmd.getTables(null, null, "%", null);
            while (chms.next())
            {
                bdts.add( chms.getString(3) );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e){
        }
        return bdts;
        
        
    }
    
}
