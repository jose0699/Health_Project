
package Model;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class Conductor extends Persona implements Serializable {
    private String horario;
    private boolean verificarGasolina;
    private boolean verificarMatricula;
    private boolean verificarMotor;
    

    public Conductor() {
        
    }

    public Conductor(String nombre, String cedula, String edad, String tipoSangre,String horario) {
        super(nombre, cedula, edad, tipoSangre);
        this.horario=horario;
    }
    
    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
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
    
}
