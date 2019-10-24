/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2310;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModeloAccesoDatos {

    public Connection conexion;

    public Connection obtenerConexion() {
        String basededatos = "educacion";
        String usuario = "root";
        String password = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + basededatos, usuario, password
         );         }catch (Exception e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public void desconectar(Connection conexion) {
        try {
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
