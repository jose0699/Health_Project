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
 * @author Diego
 */
public class Persona implements Serializable {
    protected String nombre;
    protected String cedula;
    protected String edad;
    protected String tipoSangre;

    public Persona(String nombre, String cedula, String edad, String tipoSangre) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.tipoSangre = tipoSangre;
    }

    public Persona() {
        
    }
    
    public void setNombre(String nombre) {
        this.nombre=nombre;
    }
    
     public void setCedula(String cedula) {
        this.cedula=cedula;
    }
    
    public void setEdad(String edad) {
       this.edad=edad;
    }
    
    public void setTipoSangre(int opcion) {
        if(opcion > 8 || opcion < 1){
            JOptionPane.showMessageDialog(null,"La opcion esta fuera de rango, vuelva a intentarlo");
        }else {
                if(opcion == 1){
                 this.tipoSangre = "A+";
                }
                if(opcion == 2){
                     this.tipoSangre = "A-";
                }
                if(opcion == 3){
                     this.tipoSangre = "B+";
                }
                if(opcion == 4){
                     this.tipoSangre = "B-";
                }
                if(opcion == 5){
                     this.tipoSangre = "AB+";
                }
                if(opcion == 6){
                     this.tipoSangre = "AB-";
                }
                if(opcion == 7){
                     this.tipoSangre = "O+";
                }
                if(opcion == 8){
                     this.tipoSangre = "O-";
                }  
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public String getEdad() {
        return edad;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }
    
    public  boolean verificarNombre(String nombre){
        String texto = nombre;
        return texto.matches("^\\p{L}+[\\p{L}\\p{Pd}\\p{Zs}']*\\p{L}+$|^\\p{L}+$");
    }
    public  boolean verificarCedula(String cedula){
        String texto = cedula;
        return texto.matches("[0-9]{0,10}");
    }
    public  boolean verificarEdad(String edad){
        String texto = edad;
        if(edad.equalsIgnoreCase("0")){
            return false;
        }else{
            return texto.matches("[0-9]{1,2}");
        }
    }
    
    public boolean validarDatosComun(String nombre, String cedula, String edad){
        if(verificarCedula(cedula)==true && verificarNombre(nombre)==true && verificarEdad(edad)==true){
            return true;
        }else if (verificarNombre(nombre)==false){
            int i,as=0;
            Character a;
            for (i=0; nombre.length()>i;i++){
              a= nombre.charAt(i);
              as= (int)a;
              if(((as>=91 && 96>=as) ||  (as>=33 && as<=47) || (as>=58 && 64>= as) || (as>=123 && 126>=as))==true ){
                  i= nombre.length();
                   JOptionPane.showMessageDialog(null,"Nombre invalido.Contiene algún carácter especial.");
               } 
               if((as>=48 && 57>=as)==true){
                    i= nombre.length();
                    JOptionPane.showMessageDialog(null,"Nombre invalido. Contiene algún digito.");
              }       
            }
            return false;
        }else if(verificarCedula(cedula)==false){
            if (cedula.length()>=11){
                JOptionPane.showMessageDialog(null,"Cédula invalida. Número demasiado largo");
            } else {    
             int i,as=0;
             Character a;
            for (i=0; cedula.length()>i;i++){
              a= nombre.charAt(i);
              as= (int)a;
              if(((as>=91 && 96>=as) ||  (as>=33 && as<=47) || (as>=58 && 64>= as) || (as>=123 && 126>=as))==true ){
                  i= cedula.length();
                   JOptionPane.showMessageDialog(null,"Cédula invalida.Contiene algún carácter especial.");
               } 
                if((as>=65 && 90>=as) ||( as>=97 && 122>=as)){
                     i= cedula.length();
                     JOptionPane.showMessageDialog(null,"Cédula invalida.Expresión alfanúmerica."); 
                  }      
            }
            }
            return false;
        }else if(verificarEdad(edad)==false){
            
            if (edad.length()>=3){                
              JOptionPane.showMessageDialog(null,"Edad invalida");            
            }  else {
                int i,as=0;
                Character a;
               for (i=0; edad.length()>i;i++){
                  a= edad.charAt(i);
                  as= (int)a;
                  if(((as>=91 && 96>=as) ||  (as>=33 && as<=47) || (as>=58 && 64>= as) || (as>=123 && 126>=as))==true ){
                     i= edad.length();
                     JOptionPane.showMessageDialog(null,"Edad invalida.Contiene algún carácter especial.");
                   } 
                  if((as>=65 && 90>=as) ||( as>=97 && 122>=as)){
                     i= edad.length();
                     JOptionPane.showMessageDialog(null,"Edad invalida.Expresión alfanúmerica."); 
                  }
                   }
            }
            return false;
        } else{
            return false;
        }
    }
 
}