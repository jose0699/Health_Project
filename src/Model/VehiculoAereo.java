/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Jose El Asmar
 */
public class VehiculoAereo extends Vehiculo implements Serializable {

    public VehiculoAereo(boolean gasolina, boolean funcionamotor, String matricula, String Tipo) {
        super(gasolina, funcionamotor, matricula, Tipo);
    }

    public boolean getGasolina() {
        return gasolina;
    }

    public boolean getFuncionamotor() {
        return funcionamotor;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTipo() {
        return Tipo;
    }

    
    @Override
    public void verificarGasolina() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void verificarFuncionaMotor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String matricula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
