
package Model;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class PersonalMedico extends Persona implements Serializable {
    private String especialidad,horario,profesion,localizacion;

    public PersonalMedico() {
        
    }

    public PersonalMedico(String nombre, String cedula, String edad, String tipoSangre,String horario, String profesion,String localizacion) {
        super(nombre, cedula, edad, tipoSangre);
        this.horario = horario;
        this.profesion = profesion;
        this.localizacion=localizacion;
    }

    public String getLocalizacion(){
        return localizacion;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getHorario() {
        return horario;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setHorario(int hora1,int minuto1, int hora2,int minuto2) {
 
        
    }

    public void setLocalizacion(int opcion) {
        if(opcion > 3 || opcion < 1){
            JOptionPane.showMessageDialog(null,"La opcion esta fuera de rango, vuelva a intentarlo");
        }else {
                if(opcion == 1){
                 this.localizacion = "Clínica";
                }
                if(opcion == 2){
                     this.localizacion = "Ambulatorio";
                }
                if(opcion == 3){
                     this.localizacion = "Ambulancia";
                }
               
        }
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
 
  public void setProfesion(int opcion) {
        if(opcion != 1 && opcion != 2){
            JOptionPane.showMessageDialog(null,"La opcion esta fuera de rango, vuelva a intentarlo");
        }else {
                if(opcion == 1){
                 this.profesion = "Médico";
                }
                if(opcion == 2){
                     this.profesion = "Paramédico";
                }
               
        }
    }

}