/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2310;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import practica2310.*;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRDefaultScriptlet;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRScriptletException;
import net.sf.jasperreports.engine.JRVariable;
import net.sf.jasperreports.engine.scriptlets.ScriptletFactoryContext;

//import com.jaspersoft.studio.utils.Misc;
//import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImprimirInforme {

    public void mostrarInforme() {
        try {
//Creación de un map para guardar la estructura de los datos del registro de la tabla alumnos.
            HashMap map = null;
//Creación de una lista o Array de map para guardar los maps de datos.
            ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
//Acceso a la base de datos
            ModeloAccesoDatos conectabd = new ModeloAccesoDatos();
            Connection conectado = conectabd.obtenerConexion();
            String sql = "SELECT * FROM talumnos ORDER BY codigoalumno DESC";
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
//Se llena el array de map con los valores de la tabla de alumnos de la base de datos.
            while (rs.next()) {
                map = new HashMap();
//Codigo, Nombre, etc son los campos que se han definido en el informe
//Se canjearán por datos que se extraen con rs.getString(1) siendo el valor del campo Codigo
                map.put("codigo", rs.getString(1));
                map.put("nombre", rs.getString(2));
                map.put("apellidos", rs.getString(3));
                map.put("dni", rs.getString(4));
                map.put("direccion", rs.getString(5));
                list.add(map); //se añade al map a la lista de map.
            }
            conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD.
//Se convierte la lista de datos al formato que acepta Jasper.
            JRBeanCollectionDataSource fuenteDeDatos = new JRBeanCollectionDataSource(list);
//Se crea un objeto Jasper de impresión, buscando el archivo donde inyectar los datos, el formato
//del map es decir de la estructura de datos y el conjunto de datos obtenidos a visualizar.
            JasperPrint print
                    = JasperFillManager.fillReport(ImprimirInforme.class.getResourceAsStream("informe/Alumnos2.jasper"),
                            map, fuenteDeDatos);
//Visualizamos el visor con el informe y los datos formateados.
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
         JOptionPane.showMessageDialog(null, ex, "Error.", JOptionPane.ERROR_MESSAGE);
    //    System.out.println(ex.getStackTrace().toString());
        }
    }
}
