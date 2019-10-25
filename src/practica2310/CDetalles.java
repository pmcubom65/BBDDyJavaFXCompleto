/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2310;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author tarde
 */
public class CDetalles {

    private final SimpleStringProperty codigoAluDetalle;
    private final SimpleStringProperty nombreAluDetalleEnlace;
    private final SimpleStringProperty moduloAluDetalle;
    private final SimpleStringProperty evaluacionAluDetalle;
    private final SimpleStringProperty notaModuloAluDetalle;

    public CDetalles(String codigoAluDetalle, String nombreAluDetalleEnlace, String moduloAluDetalle, String evaluacionAluDetalle, String notaModuloAluDetalle) {
        //*****************************************************                       //
        //  SETTERS, usados para acceder a los atributos.      
        this.codigoAluDetalle = new SimpleStringProperty(codigoAluDetalle);
        this.nombreAluDetalleEnlace = new SimpleStringProperty(nombreAluDetalleEnlace);
        this.moduloAluDetalle = new SimpleStringProperty(moduloAluDetalle);
        this.evaluacionAluDetalle = new SimpleStringProperty(evaluacionAluDetalle);
        this.notaModuloAluDetalle = new SimpleStringProperty(notaModuloAluDetalle);
    }

    public StringProperty notaModuloAluDetalleProperty() {
        return notaModuloAluDetalle;
    }

    public StringProperty evaluacionAluDetalleProperty() {
        return evaluacionAluDetalle;
    }

    public StringProperty moduloAluDetalleProperty() {
        return moduloAluDetalle;
    }

    public StringProperty nombreAluDetalleEnlaceProperty() {
        return nombreAluDetalleEnlace;
    }

    public StringProperty codigoAluDetalleProperty() {
        return codigoAluDetalle;
    }

    public SimpleStringProperty getCodigoAluDetalle() {
        return codigoAluDetalle;
    }

    public SimpleStringProperty getNombreAluDetalleEnlace() {
        return nombreAluDetalleEnlace;
    }

    public SimpleStringProperty getModuloAluDetalle() {
        return moduloAluDetalle;
    }

    public SimpleStringProperty getEvaluacionAluDetalle() {
        return evaluacionAluDetalle;
    }

    public SimpleStringProperty getNotaModuloAluDetalle() {
        return notaModuloAluDetalle;
    }
    
    
   /*     private final SimpleStringProperty codigoAluDetalle;
    private final SimpleStringProperty nombreAluDetalleEnlace;
    private final SimpleStringProperty moduloAluDetalle;
    private final SimpleStringProperty evaluacionAluDetalle;
    private final SimpleStringProperty notaModuloAluDetalle;*/
    
    
  public String getcodigoAluDetalle() {
        return codigoAluDetalle.get();
    }
  
    public String getnombreAluDetalleEnlace() {
        return nombreAluDetalleEnlace.get();
    }
  public String getmoduloAluDetalle() {
        return moduloAluDetalle.get();
    }
  public String getevaluacionAluDetalle() {
        return evaluacionAluDetalle.get();
    }
   public String getnotaModuloAluDetalle() {
        return notaModuloAluDetalle.get();
    }
  
}
