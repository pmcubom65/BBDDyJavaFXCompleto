/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2310;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.LinkedList;
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

public class VentanaDeDetallesController implements Initializable {

    @FXML
    private Label t1;

    @FXML
    private Label t2;

    @FXML
    private Label t3;

    @FXML
    private Label t4;

    @FXML
    private Button volver;

    @FXML
    private TextField codigoDetalle;

    @FXML
    private TextField codigoDetalleEnlace;

    @FXML
    private TextField moduloAlumno;

    @FXML
    private TextField evaluacionAlumno;

    @FXML
    private TextField notaAlumno;

    CAlumnos alumnodetallado;

    @FXML
    private Button eliminarDatos;

    @FXML
    private Button btnRellenar;

    @FXML
    private Button insertarDatos;

    @FXML
    private Button actualizarDatos;

    @FXML
    private Button btnVaciarCajas;

    @FXML
    private TableView<CDetalles> dataGridView1;

    @FXML
    void actualizacionDatos(ActionEvent event) {
        try {
            if (codigoDetalle.getText().toString().equals("") || codigoDetalle.getText().toString().isEmpty()) {
                 JOptionPane.showMessageDialog(null, "Seleccionar un registro de la Tabla y pinchar en rellenar datos.", "Operación", JOptionPane.INFORMATION_MESSAGE);
            }else {
            if (dataGridView1.getSelectionModel().getSelectedItem() == null ) {
                JOptionPane.showMessageDialog(null, "Seleccionar un registro de la Tabla y pinchar en rellenar datos.", "Operación", JOptionPane.INFORMATION_MESSAGE);
            } else { //se seleccionó un registro y se puede actualizar.
                
                LinkedList<String> lista = new LinkedList<>();
                lista.add(moduloAlumno.getText());
                lista.add(evaluacionAlumno.getText());
                lista.add(notaAlumno.getText());
                CDetalles registro = dataGridView1.getSelectionModel().getSelectedItem();
                String modulo = (!lista.get(0).equals(registro.getModuloAluDetalle())) ? lista.get(0) : registro.getmoduloAluDetalle().toString();
                String evaluacion = (!lista.get(1).equals(registro.getEvaluacionAluDetalle())) ? lista.get(1) : registro.getevaluacionAluDetalle().toString();
                String nota = (!lista.get(2).equals(registro.getNotaModuloAluDetalle())) ? lista.get(2) : registro.getnotaModuloAluDetalle().toString();

                ModeloAccesoDatos conectabd = new ModeloAccesoDatos();

                Connection conectado = conectabd.obtenerConexion();
                String sql = "update talumnosdetalles set moduloalumnodetalle=?, evaluacionalumnodetalle=?, notamoduloalumnodetalle=? where codigoalumnodetalle=?";
                PreparedStatement ps = conectado.prepareStatement(sql);
                ps.setString(1, modulo);
                ps.setString(2, evaluacion);
                ps.setString(3, nota);
                ps.setString(4, registro.getcodigoAluDetalle().toString());

                ps.executeUpdate();

                conectabd.desconectar(conectado);

                JOptionPane.showMessageDialog(null, "Registro actualizado correctamente", "Operación", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajasDetalles();
                cogerDatos();
            }
}
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    @FXML
    void eliminacionDatos(ActionEvent event) {
        try {
            if (dataGridView1.getSelectionModel().getSelectedItem() != null) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmar Borrar Registro");
                alert.setHeaderText("Borrar Registro");
                alert.setContentText("¿Seguro de eliminar el registro?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    ModeloAccesoDatos conectabd = new ModeloAccesoDatos();

                    Connection conectado = conectabd.obtenerConexion();

                    String sql = "delete from talumnosdetalles where codigoalumnodetalle=" + dataGridView1.getSelectionModel().getSelectedItem().getcodigoAluDetalle();

                    Statement stm = conectado.createStatement();
                    stm.executeUpdate(sql);
                    conectabd.desconectar(conectado); //Desconecta la conexión actual a la BBDD. 

                    JOptionPane.showMessageDialog(null, "Registro eliminado correctamente", "Operación", JOptionPane.INFORMATION_MESSAGE);
                    limpiarCajasDetalles();
                    cogerDatos();

                }
            } else {
                JOptionPane.showMessageDialog(null, "Seleccionar un registro de la Tabla.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void insercionDatos(ActionEvent event) {

        try {

            if (moduloAlumno.getText().length() == 0 || evaluacionAlumno.getText().length() == 0 || notaAlumno.getText().length() == 0) {
                JOptionPane.showMessageDialog(
                        null, "Rellene los campos, por favor", "Operación", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajasDetalles();
            } else {
                ModeloAccesoDatos conectabd = new ModeloAccesoDatos();

                Connection conectado = conectabd.obtenerConexion();

                String sql = "insert into talumnosdetalles (codigoalumnodetalleenlace, evaluacionalumnodetalle,"
                        + "moduloalumnodetalle, notamoduloalumnodetalle) values(?,?,?,?)";
                PreparedStatement ps = conectado.prepareStatement(sql);
                ps.setString(1, alumnodetallado.getcodigoAlu());
                ps.setString(2, evaluacionAlumno.getText().toString());
                ps.setString(3, moduloAlumno.getText().toString());
                ps.setString(4, notaAlumno.getText().toString());

                ps.executeUpdate();

                conectabd.desconectar(conectado);
                JOptionPane.showMessageDialog(
                        null, "Registro insertado correctamente", "Operación", JOptionPane.INFORMATION_MESSAGE);
                limpiarCajasDetalles();
                cogerDatos();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de SQL.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void rellenarDatos(ActionEvent event) {

            rellenarCajas();
    }

    
    public void rellenarCajas() {
               if (dataGridView1.getSelectionModel().getSelectedItem() != null) {
            CDetalles detalle = dataGridView1.getSelectionModel().getSelectedItem();

            codigoDetalle.setText(detalle.getcodigoAluDetalle());
            codigoDetalleEnlace.setText(detalle.getnombreAluDetalleEnlace());
            moduloAlumno.setText(detalle.getmoduloAluDetalle());
            evaluacionAlumno.setText(detalle.getevaluacionAluDetalle());
            notaAlumno.setText(detalle.getnotaModuloAluDetalle());

        } else {
            JOptionPane.showMessageDialog(null, "Seleccionar un registro de la Tabla.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    
    public void cogerDatos() {
        final ObservableList<CDetalles> datos = FXCollections.observableArrayList();
        try {
            ModeloAccesoDatos conectabd = new ModeloAccesoDatos();

            Connection conectado = conectabd.obtenerConexion();
            String sql = "SELECT * FROM talumnosdetalles where codigoalumnodetalleenlace=" + alumnodetallado.getcodigoAlu();
            Statement stm = conectado.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            //Se borran los datos de la TableView        
            dataGridView1.getItems().clear();
            while (rs.next()) {
                String codigo_alumno = rs.getString(1);
                String codigo_alumno_detalleenlace = rs.getString(2);
                String evaluacionalumnodetalleenlace = rs.getString(3);
                String moduloalumnodetalle = rs.getString(4);
                String notamoduloalumnodetalle = rs.getString(5);
                //Añade cada registro a la TableView   
                dataGridView1.getItems().addAll(new CDetalles(codigo_alumno, codigo_alumno_detalleenlace, evaluacionalumnodetalleenlace, moduloalumnodetalle, notamoduloalumnodetalle));
            }                     //Visualizar la TableView.     
            dataGridView1.setVisible(true);
//Redimensiona cada columna al tamaño de sus datos.  
            dataGridView1.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error de Select.", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    void vaciarCajas(ActionEvent event) {
        limpiarCajasDetalles();

    }

    private void limpiarCajasDetalles() {
        codigoDetalle.setText("");
        codigoDetalleEnlace.setText("");
        moduloAlumno.setText("");
        evaluacionAlumno.setText("");
        notaAlumno.setText("");

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        codigoDetalle.requestFocus(); //poner el foco en la caja de texto inicada. 

        TableColumn<CDetalles, String> codigoDetalle = new TableColumn<>("Código");
        codigoDetalle.setCellValueFactory(new PropertyValueFactory<CDetalles, String>("codigoAluDetalle"));
        TableColumn<CDetalles, String> codigoDetalleEnlace = new TableColumn<>("CodigoDetalleEnlace");
        codigoDetalleEnlace.setCellValueFactory(new PropertyValueFactory<CDetalles, String>("nombreAluDetalleEnlace"));

        //***********************************************************************************************    
        //Apellidos del alumno   
        TableColumn<CDetalles, String> moduloAlumno = new TableColumn<>("Modulo");
        moduloAlumno.setCellValueFactory(new PropertyValueFactory<CDetalles, String>("moduloAluDetalle"));
        //***********************************************************************************************     
        //DNI del alumno       
        TableColumn<CDetalles, String> evaluacionAlumno = new TableColumn<>("Evaluacion");
        evaluacionAlumno.setCellValueFactory(new PropertyValueFactory<CDetalles, String>("evaluacionAluDetalle"));
        //*********************************************************************************************** 
//Dirección del alumno
        TableColumn<CDetalles, String> notaAlumno = new TableColumn<>("Nota");
        notaAlumno.setCellValueFactory(new PropertyValueFactory<CDetalles, String>("notaModuloAluDetalle"));
//***********************************************************************************************     
//Añadir las columnas a la TableView    
        dataGridView1.getColumns().addAll(codigoDetalle, codigoDetalleEnlace, moduloAlumno, evaluacionAlumno, notaAlumno);

    }

    public void iniciarVentana(CAlumnos a) {
        alumnodetallado = a;
        t1.setText("Código: " + a.getcodigoAlu());
        t2.setText(a.getnombreAlu());
        t3.setText(a.getapellidosAlu());
        t4.setText(a.getdniAlu());
        cogerDatos();
    }

    @FXML
    void volverAtras(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent nuevoparent = null;
        try {
            nuevoparent = loader.load();
        } catch (Exception e) {

        }
        Scene nuevaescena = new Scene(nuevoparent, 800, 800);

        //        VentanaDeDetallesController micontrolador = loader.getController();
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(nuevaescena);

        window.show();

    }
}
