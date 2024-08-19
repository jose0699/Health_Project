/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose
 */
public class Paciente extends Persona implements Serializable{
    
    private String descripcion;
    private String tipoEmergencia;
    private String ubicacion;
    private String cita;
    private String couta;


    public Paciente(String nombre, String cedula, String edad, String tipoSangre, String descripcion, String tipoEmergencia, String ubicacion, String couta) {
        super(nombre, cedula, edad, tipoSangre);
        this.descripcion = descripcion;
        this.tipoEmergencia = tipoEmergencia;
        this.ubicacion = ubicacion;
        this.couta = couta;
    }
    
    public Paciente(String nombre, String cedula, String edad, String tipoSangre, String descripcion, String tipoEmergencia, String cita){
        super(nombre,cedula, edad, tipoSangre);
        this.descripcion = descripcion;
        this.tipoEmergencia = tipoEmergencia;
        this.cita = cita;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setCita(int cita) {
         if(cita <1 | cita > 2){
           JOptionPane.showMessageDialog(null,"La opcion esta fuera de rango, vuelva a intentarlo");
       }else{
           if(cita == 1){
               this.cita = "Sí";
           }if(cita == 2){
               this.cita = "No";
           }
       }
    }

    public void setUbicacion(int  ubicacion) {
        if(ubicacion <1 | ubicacion > 5){
           JOptionPane.showMessageDialog(null,"La opcion esta fuera de rango, vuelva a intentarlo");
       }else{
           if(ubicacion == 1){
               this.ubicacion = "Baruta";
           }if(ubicacion == 2){
               this.ubicacion = "Chacao";
           }if(ubicacion == 3){
               this.ubicacion = "El Hatillo";
           }if(ubicacion == 4){
               this.ubicacion = "Libertador";
           }if(ubicacion == 5){
               this.ubicacion = "Sucre";
           }
       }
    }
    
    public void setCouta(int couta){
       if(couta <1 | couta > 2){
           JOptionPane.showMessageDialog(null,"La opcion esta fuera de rango, vuelva a intentarlo");
       }else{
           if(couta == 1){
               this.couta = "Sí";
           }if(couta == 2){
               this.couta = "No";
           }
       }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getCita() {
        return cita;
    }

    public String getCouta() {
        return couta;
    }

    public String getTipoEmergencia() {
        return tipoEmergencia;
    }
  
    
    
}