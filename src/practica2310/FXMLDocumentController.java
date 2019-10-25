/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2310;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    
 
    
    
    @FXML
    private TextField codigoAlumno;
    @FXML
    private TextField nombreAlumno;
    @FXML
    private TextField apellidosAlumno;
    @FXML
    private TextField dniAlumno;
    @FXML
    private TextField direccionAlumno;
    @FXML
    private Button btnMostrar;
    @FXML
    private Button insertarAlumnos;
    @FXML
    private TableView<CAlumnos> dataGridView1; //IMPORTANTE: Asocia la clase CAlumnos con la TableView, cuando se
//crea con Scene Builder pone private TableView<?> dataGridView1, y hay que cambiar el ? por el nombre de la clase
//CAlumnos que se ha creado por nosotros.
    @FXML
    private Button btnRellenar;
    @FXML
    private Button actualizarAlumnos;
    @FXML
    private Button eliminarAlumnos;
    @FXML
    private Button btnVaciarCajas;

    @FXML
    private Button detalle;

    @FXML
    void irVentanaDetalle(ActionEvent event) {

        if (dataGridView1.getSelectionModel().getSelectedItem() != null) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("VentanaDeDetalles.fxml"));
            Parent nuevoparent = null;
            try {
                nuevoparent = loader.load();
            } catch (Exception e) {

            }
            Scene nuevaescena = new Scene(nuevoparent, 700, 800);

            VentanaDeDetallesController micontrolador = loader.getController();

            CAlumnos c = dataGridView1.getSelectionModel().getSelectedItem();

            micontrolador.iniciarVentana(c);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(nuevaescena);

            window.show();

        } else {
            JOptionPane.showMessageDialog(null, "Seleccionar un registro de la Tabla.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombreAlumno.requestFocus(); //poner el foco en la caja de texto inicada. 

        TableColumn<CAlumnos, String> codigoAlumno = new TableColumn<>("Código");
        codigoAlumno.setCellValueFactory(new PropertyValueFactory<CAlumnos, String>("codigoAlu"));
        TableColumn<CAlumnos, String> nombreAlumno = new TableColumn<>("Nombre");
        nombreAlumno.setCellValueFactory(new PropertyValueFactory<CAlumnos, String>("nombreAlu"));

        //***********************************************************************************************    
        //Apellidos del alumno   
        TableColumn<CAlumnos, String> apellidosAlumno = new TableColumn<>("Apellidos");
        apellidosAlumno.setCellValueFactory(new PropertyValueFactory<CAlumnos, String>("apellidosAlu"));
        //***********************************************************************************************     
        //DNI del alumno       
        TableColumn<CAlumnos, String> dniAlumno = new TableColumn<>("DNI");
        dniAlumno.setCellValueFactory(new PropertyValueFactory<CAlumnos, String>("dniAlu"));
        //*********************************************************************************************** 
//Dirección del alumno
        TableColumn<CAlumnos, String> direccionAlumno = new TableColumn<>("Dirección");
        direccionAlumno.setCellValueFactory(new PropertyValueFactory<CAlumnos, String>("direccionAlu"));
//***********************************************************************************************     
//Añadir las columnas a la TableView    
        dataGridView1.getColumns().addAll(codigoAlumno, nombreAlumno, apellidosAlumno, dniAlumno, direccionAlumno);
    }

    @FXML
    private void seleccionarDatos(ActionEvent event) {
        //******************************************************************************   
        //Datos por SELECT de SQL        
        final ObservableList<CAlumnos> datos = FXCollections.observableArrayList();
        try {
            ModeloAccesoDatos conectabd = new ModeloAccesoDatos();

            Connection conectado = conectabd.obtenerConexion();
            String sql = "SELECT * FROM talumnos ORDER BY codigoalumno DESC";
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Se borran los datos de la TableView        
            dataGridView1.getItems().clear();
            while (rs.next()) {
                String codigoA = rs.getString(1);
                String nombreA = rs.getString(2);
                String apellidosA = rs.getString(3);
                String dniA = rs.getString(4);
                String direccionA = rs.getString(5);
                //Añade cada registro a la TableView   
                dataGridView1.getItems().addAll(new CAlumnos(codigoA, nombreA, apellidosA, dniA, direccionA));
            }                     //Visualizar la TableView.     
            dataGridView1.setVisible(true);
//Redimensiona cada columna al tamaño de sus datos.  
            dataGridView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean devuelveEstadoCajas() {
        //Comprueba que las cajas de texto estén rellenas de datos.  
        boolean estado = true;
        String aux;
        aux = nombreAlumno.getText().trim();
//se eliminan los blancos de la caja.       
        if (aux.length() == 0) {
            JOptionPane.showMessageDialog(null, "Introduzca un valor en el Nombre del alumno.", "Operación", JOptionPane.INFORMATION_MESSAGE);
            estado = false;
            nombreAlumno.requestFocus();
        }
        if (estado == true) {
            aux = apellidosAlumno.getText().trim();

            if (aux.length() == 0) {
                JOptionPane.showMessageDialog(null, "Introduzca un valor en los Apellidos del alumno.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                estado = false;
                apellidosAlumno.requestFocus();

            }

        }

        if (estado == true) //evaluamos el campo. 
        {
            aux = dniAlumno.getText().trim();

            if (aux.length() == 0) {
                JOptionPane.showMessageDialog(null, "Introduzca un valor en el DNI del alumno.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                estado = false;
                dniAlumno.requestFocus();
            }

        }

        if (estado == true) //evaluamos el campo. 
        {
            aux = direccionAlumno.getText().trim();
//se eliminan los blancos de la caja.      
            if (aux.length() == 0) {
                JOptionPane.showMessageDialog(null, "Introduzca un valor en la Dirección del alumno.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                estado = false;
                direccionAlumno.requestFocus();

            }
        }

        return estado;
    }

    private void limpiarCajasAlumno() {
        //Limpia de datos a todas las cajas.    
        codigoAlumno.setText("");
        nombreAlumno.setText("");
        apellidosAlumno.setText("");
        dniAlumno.setText("");
        direccionAlumno.setText("");
        nombreAlumno.requestFocus();

    }

    @FXML
    private void vaciarCajas(ActionEvent event) {
        limpiarCajasAlumno();
    }

    @FXML
    private void insercionDatos(ActionEvent event) {
        try {
            boolean estado = devuelveEstadoCajas();

            if (estado == true) {
                ModeloAccesoDatos conectabd = new ModeloAccesoDatos();

                Connection conectado = conectabd.obtenerConexion();

                String sql = "insert into talumnos values(0,'" + nombreAlumno.getText() + "','" + apellidosAlumno.getText() + "','" + dniAlumno.getText() + "','" + direccionAlumno.getText()
                        + "')";

                Statement stm = conectado.createStatement();

                stm.executeUpdate(sql);

                conectabd.desconectar(conectado);
//Desconecta la conexión actual a la BBDD.         

                JOptionPane.showMessageDialog(
                        null, "Registro insertado correctamente. Hacer clic en Mostrar Datos de los Alumnos para ver el registro insertado.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajasAlumno();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void rellenarDatos(ActionEvent event) {
//Si se ha seleccionado un registro de la tableView 
        if (dataGridView1.getSelectionModel().getSelectedItem() != null) {
            CAlumnos alumno = dataGridView1.getSelectionModel().getSelectedItem();
//Se rellenan las cajas de texto de la pantalla. 
            codigoAlumno.setText(alumno.getcodigoAlu());
//se recuperan los datos de la clase CAlumnos         
            nombreAlumno.setText(alumno.getnombreAlu());
            apellidosAlumno.setText(alumno.getapellidosAlu());
            dniAlumno.setText(alumno.getdniAlu());
            direccionAlumno.setText(alumno.getdireccionAlu());
        } else {
            JOptionPane.showMessageDialog(null, "Seleccionar un registro de la Tabla.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @FXML
    private void actualizacionDatos(ActionEvent event) {
        try {

            String codigoalumnovalor = codigoAlumno.getText().trim();

            //se eliminan los blancos de la caja.
            if (codigoalumnovalor.length() == 0) {
                JOptionPane.showMessageDialog(null, "Seleccionar un registro de la Tabla para poder modificarlo.", "Operación", JOptionPane.INFORMATION_MESSAGE);
            } else { //se seleccionó un registro y se puede actualizar.
                boolean estado = devuelveEstadoCajas();

                if (estado == true) //se pueden actualizar los datos del alumno.     
                {
                    ModeloAccesoDatos conectabd = new ModeloAccesoDatos();

                    Connection conectado = conectabd.obtenerConexion();

                    String sql = "update talumnos set nombrealumno='" + nombreAlumno.getText() + "', apellidosalumno='" + apellidosAlumno.getText() + "',dnialumno='" + dniAlumno.getText() + "',direccionalumno='" + direccionAlumno.getText() + "' where codigoalumno=" + codigoalumnovalor;
                    Statement stm = conectado.createStatement();
                    stm.executeUpdate(sql);

                    conectabd.desconectar(conectado);
//Desconecta la conexión actual a la BBDD.           
                    JOptionPane.showMessageDialog(null, "Registro actualizado correctamente. Hacer clic en Mostrar Datos de los Alumnos para ver el registro actualizado.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCajasAlumno();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void eliminacionDatos(ActionEvent event) {
        try {
            String codigoalumnovalor = codigoAlumno.getText().trim();
            //se eliminan los blancos de la caja.       
            if (codigoalumnovalor.length() == 0) {
                JOptionPane.showMessageDialog(null, "Seleccionar un registro de la Tabla para poder eliminarlo.", "Operación", JOptionPane.INFORMATION_MESSAGE);
            } else {
//se seleccionó un registro y se puede eliminar. 
//Mensaje Box para avisar si efectuar definitivamente la acción de borrado del registro.          
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Borrar Registro");
                alert.setHeaderText("Borrar Registro");
                alert.setContentText("¿Seguro de eliminar el registro?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    ModeloAccesoDatos conectabd = new ModeloAccesoDatos();

                    Connection conectado = conectabd.obtenerConexion();

                    String sql = "delete from talumnos where codigoalumno=" + codigoalumnovalor;

                    Statement stm = conectado.createStatement();
                    stm.executeUpdate(sql);
                    conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD. 

                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente. Hacer clic en Mostrar Datos de los Alumnos para actualizar la Tabla de datos.", "Operación", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCajasAlumno();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    

}
