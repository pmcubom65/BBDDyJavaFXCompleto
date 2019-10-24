/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2310;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CAlumnos {

    private final SimpleStringProperty codigoAlu;
    private final SimpleStringProperty nombreAlu;
    private final SimpleStringProperty apellidosAlu;
    private final SimpleStringProperty dniAlu;
    private final SimpleStringProperty direccionAlu;

    public CAlumnos(String codigoAlu, String nombreAlu, String apellidosAlu, String dniAlu, String direccionAlu) {
        //*****************************************************                       //
        //  SETTERS, usados para acceder a los atributos.      
        this.codigoAlu = new SimpleStringProperty(codigoAlu);
        this.nombreAlu = new SimpleStringProperty(nombreAlu);
        this.apellidosAlu = new SimpleStringProperty(apellidosAlu);
        this.dniAlu = new SimpleStringProperty(dniAlu);
        this.direccionAlu = new SimpleStringProperty(direccionAlu);
    }             //*****************************************************
    //GETTERS, usados para acceder a los atributos. 

    public String getcodigoAlu() {
        return codigoAlu.get();
    }

    public String getnombreAlu() {
        return nombreAlu.get();
    }

    public String getapellidosAlu() {
        return apellidosAlu.get();
    }

    public String getdniAlu() {
        return dniAlu.get();
    }

    public String getdireccionAlu() {
        return direccionAlu.get();
    }

    public StringProperty codigoAluProperty() {
        return codigoAlu;
    }

    public StringProperty nombreAluProperty() {
        return nombreAlu;
    }

    public StringProperty apellidosAluProperty() {
        return apellidosAlu;
    }

    public StringProperty dniAluProperty() {
        return dniAlu;
    }

    public StringProperty direccionAluProperty() {
        return direccionAlu;
    }
}
