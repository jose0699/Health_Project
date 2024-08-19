/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Jose El Asmar
 */
public abstract class Vehiculo implements java.io.Serializable {
    
    protected boolean gasolina;
    protected boolean funcionamotor;
    protected String matricula;
    protected String Tipo;

    public Vehiculo(boolean gasolina, boolean funcionamotor, String matricula,String Tipo) {
        this.gasolina = gasolina;
        this.funcionamotor = funcionamotor;
        this.matricula = matricula;
        this.Tipo=Tipo;
    }

    public void setGasolina(boolean gasolina) {
        this.gasolina = gasolina;
    }

    public void setFuncionamotor(boolean funcionamotor) {
        this.funcionamotor = funcionamotor;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
    public abstract void verificarGasolina();
    public abstract void verificarFuncionaMotor();
    public abstract String matricula();
    
}
