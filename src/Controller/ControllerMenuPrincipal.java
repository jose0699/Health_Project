
package Controller;


import static Controller.ControllerAtencionCliente.*;
import static Controller.ControllerDiagnosticoVehiculo.*;
import static Controller.ControllerPlantillaMedica.listaConductor;
import static Controller.ControllerPlantillaMedica.listaPersonalMedico;
import static Controller.ControllerTransporteEmergencia.listaActivoAereo;
import static Controller.ControllerTransporteEmergencia.listaActivoConductorAereo;
import static Controller.ControllerTransporteEmergencia.listaActivoConductorTerrestre;
import static Controller.ControllerTransporteEmergencia.listaActivoTerrestre;

import ViewPrincipal.AtencionCliente;
import ViewPrincipal.DiagnosticoVehiculo;
import ViewPrincipal.MenuPrincipal;
import ViewPrincipal.PlantillaMedica;
import ViewPrincipal.TransporteEmergencia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class ControllerMenuPrincipal implements ActionListener{
    private MenuPrincipal menuprincipal;
    
    FileInputStream ficheroEntrada = null;

    public ControllerMenuPrincipal(MenuPrincipal menuprincipal) {
        this.menuprincipal = menuprincipal;
        this.menuprincipal.BotonSalir.addActionListener(this);
        this.menuprincipal.botonDiagnosticoVehiculo.addActionListener(this);
        this.menuprincipal.botonTransporteEmergencia.addActionListener(this);
        this.menuprincipal.botonAtencionCliente.addActionListener(this);     
        this.menuprincipal.botonPlantillaMedica.addActionListener(this);
        try {
            
            cargarPacienteMayor();
            cargarPacienteMenor();
            cargarMaterialMedico();
            cargarVehiculoTerrestre();
            cargarActivoTerrestre();
            cargarActivoConductorTerrestre();
            cargarVehiculoAereo();
            cargarActivoAereo();
            cargarActivoConductorTerrestre();
            cargarMaterialUnidad();
            cargarPersonalMedico();
            cargarConductor();
            
        } catch (IOException ex) {
            Logger.getLogger(ControllerMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllerMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void cargarConductor() throws FileNotFoundException,IOException,ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosConductor.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaConductor = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();    
    }
    public void cargarPersonalMedico() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosPersonalMedico.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaPersonalMedico = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarPacienteMayor() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosPacienteMayor.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaPacienteMayor = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarPacienteMenor() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosPacienteMenor.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaPacienteMenor = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarVehiculoTerrestre() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosVehiculoTerrestre.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaVehiculoTerrestre = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarVehiculoAereo() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosVehiculoAereo.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaVehiculoAereo = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarActivoTerrestre() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosActivoTerrestre.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaActivoTerrestre = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarActivoAereo() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosActivoAereo.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaActivoAereo = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarActivoConductorTerrestre() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosActivoConductorTerrestre.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaActivoConductorTerrestre = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarActivoConductorAereo() throws FileNotFoundException, IOException, ClassNotFoundException{
        ficheroEntrada = new FileInputStream("datosActivoConductorAereo.txt");
        ObjectInputStream canalEntrada = new ObjectInputStream(ficheroEntrada);
        listaActivoConductorAereo = (ArrayList)canalEntrada.readObject();
        canalEntrada.close();
    }
    
    public void cargarMaterialMedico() throws IOException{
        
        File archivo;
        FileReader leer = null;
        BufferedReader almacenamiento = null;
        String cadena = "";
        
        try {
            leer = new FileReader("materialMedico.txt");
            almacenamiento = new BufferedReader(leer);
            
            while(cadena != null){
                try{
                    cadena = almacenamiento.readLine();
                    oxigenoT = Integer.parseInt(cadena);
                    cadena = almacenamiento.readLine();
                    esterilizadoresT = Integer.parseInt(cadena);
                    cadena = almacenamiento.readLine();
                    estetoscopioT = Integer.parseInt(cadena);
                    cadena = almacenamiento.readLine();
                    inyeccionesT = Integer.parseInt(cadena);
                    cadena = almacenamiento.readLine();
                    gasaT = Integer.parseInt(cadena);
                    cadena = almacenamiento.readLine();
                    
                }catch(FileNotFoundException ex){
                    Logger.getLogger(ControllerMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            almacenamiento.close();
            leer.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ControllerMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cargarMaterialUnidad() throws IOException{

       File archivo;
       FileReader leer = null;
       BufferedReader almacenamiento = null;
       String cadena = "";

       try {
           leer = new FileReader("materialUnidad.txt");
           almacenamiento = new BufferedReader(leer);

           while(cadena != null){
               try{
                   cadena = almacenamiento.readLine();
                   sueroT = Integer.parseInt(cadena);
                   cadena = almacenamiento.readLine();
                   botiquinesT = Integer.parseInt(cadena);
                   cadena = almacenamiento.readLine();
                   respiradorT = Integer.parseInt(cadena);
                   cadena = almacenamiento.readLine();
                   desfibriladorT = Integer.parseInt(cadena);
                   cadena = almacenamiento.readLine();

               }catch(FileNotFoundException ex){
                   Logger.getLogger(ControllerMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
               }

           }
           almacenamiento.close();
           leer.close();

       } catch (FileNotFoundException ex) {
           Logger.getLogger(ControllerMenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    
    public void iniciar(){
        menuprincipal.setTitle("MENU PRINCIPAL"); //El titulo que tendra en la ventana
        menuprincipal.setLocationRelativeTo(null); //Para que se inicie la ventana en el centro
        menuprincipal.setResizable(false);
    }

   
    @Override
   public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==menuprincipal.botonAtencionCliente){
        AtencionCliente ac = new AtencionCliente();
        ControllerAtencionCliente ctrac = new ControllerAtencionCliente(ac);
        ctrac.iniciarAtencionCliente();
        ac.setVisible(true);
        menuprincipal.setVisible(false);
    }
    
    if(ae.getSource()==menuprincipal.botonTransporteEmergencia){
        TransporteEmergencia te = new TransporteEmergencia();
        ControllerTransporteEmergencia ctrte = new ControllerTransporteEmergencia(te);
        ctrte.iniciarTransporte();
        te.setVisible(true);
        menuprincipal.setVisible(false);
    }

    if(ae.getSource()==menuprincipal.botonPlantillaMedica){
        PlantillaMedica pm = new PlantillaMedica();
        ControllerPlantillaMedica ctrpm = new ControllerPlantillaMedica(pm);
        ctrpm.iniciarPlantillaMedica();
        pm.setVisible(true);
        menuprincipal.setVisible(false);
    }
    
    if(ae.getSource()==menuprincipal.botonDiagnosticoVehiculo){
        DiagnosticoVehiculo dv = new DiagnosticoVehiculo();
        ControllerDiagnosticoVehiculo ctrdv = new ControllerDiagnosticoVehiculo(dv);
        dv.setLocationRelativeTo(null);
        dv.setVisible(true);
        menuprincipal.setVisible(false);
    }
    
    if(ae.getSource()==menuprincipal.BotonSalir){
        menuprincipal.setVisible(false);
    }
    
    }
}
