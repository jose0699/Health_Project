/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import Model.Persona;
import java.util.Collections;
import javax.swing.JOptionPane;
import Model.Vehiculo;
import Model.VehiculoAereo;
import Model.VehiculoTerrestre;
import ViewDiagnosticoVehiculo.AbastecerUnidad;
import ViewDiagnosticoVehiculo.AgregarVehiculo;
import ViewDiagnosticoVehiculo.TablaVehiculos;
import ViewPrincipal.DiagnosticoVehiculo;
import ViewPrincipal.MenuPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ControllerDiagnosticoVehiculo implements ActionListener {
    int flag;
    int cont=0;
    int cont2=0;
    int m=0,comprobar;
    public static int sueroT = 0;
    public static int botiquinesT = 0;
    public static int respiradorT = 0;
    public static int desfibriladorT = 0;
    private DiagnosticoVehiculo diagnosticovehiculo;
    Vehiculo vehiculo;
    VehiculoTerrestre vehiculoterrestre;
    VehiculoAereo vehiculoAereo;
    Persona per;
    AgregarVehiculo av = new AgregarVehiculo();
    TablaVehiculos tv = new TablaVehiculos();
    AbastecerUnidad au = new AbastecerUnidad();
    private DefaultTableModel modelo;
    private DefaultTableModel modeloM;
    
    
    public static  ArrayList<VehiculoTerrestre> listaVehiculoTerrestre = new ArrayList<VehiculoTerrestre>();
    public static  ArrayList<VehiculoAereo> listaVehiculoAereo = new ArrayList<VehiculoAereo>();
    
    FileOutputStream fichero = null;
    
public ControllerDiagnosticoVehiculo(DiagnosticoVehiculo diagnosticovehiculo) {
    
    this.diagnosticovehiculo=diagnosticovehiculo;
    this.diagnosticovehiculo.BotonAgregarVehiculo.addActionListener(this);
    this.diagnosticovehiculo.BotonListaVehiculos.addActionListener(this);
    this.diagnosticovehiculo.Regresar.addActionListener(this);
    this.diagnosticovehiculo.abastecerunidad.addActionListener(this);
    this.av.BotonAgregar.addActionListener(this);
    this.av.BotonRegresar.addActionListener(this);
    this.av.RadioBotonAmbulancia.addActionListener(this);
    this.av.RadioBotonCarro.addActionListener(this);
    this.av.RadioBotonHelicoptero.addActionListener(this);
    this.tv.BotonRegresar.addActionListener(this);
    this.tv.Mantenimiento.addActionListener(this);
    this.au.Regresar.addActionListener(this);
    this.au.AgregarMaterial.addActionListener(this);
    
    }

    public void guardarVehiculoTerrestre() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosVehiculoTerrestre.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaVehiculoTerrestre);
        canal.close();
    }
    
    public void guardarVehiculoAereo() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosVehiculoAereo.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaVehiculoAereo);
        canal.close();
    }
    
    public void guardarMaterialUnidad(){
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        
        archivo =  new File("materialUnidad.txt");
        
        if(!archivo.exists()){
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo,false);
                linea = new PrintWriter(archivo);
                linea.println(sueroT);
                linea.println(botiquinesT);
                linea.println(respiradorT);
                linea.println(desfibriladorT);
                linea.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
           try {
                escribir = new FileWriter(archivo,false);
                linea = new PrintWriter(archivo);
                linea.println(sueroT);
                linea.println(botiquinesT);
                linea.println(respiradorT);
                linea.println(desfibriladorT);
                linea.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }

    public void CargarTabla(){
        String datos [][] = {};
        String columna [] = {"Tipo de vehículo","Gasolina","Motor Check","Matrícula"};
        modelo= new DefaultTableModel(datos,columna){
            public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
        };
        tv.TablaVehiculo.setModel(modelo);
    }
    
    public void cargarTablaMaterial(){
            String datos [][] = {};
            String columna [] = {"Suero","Botiquines","Respirador","Desfibrilador"};
            modeloM = new DefaultTableModel(datos,columna){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            au.TablaMateriales.setModel(modeloM);
            modeloM.insertRow(0, new Object[]{});
    }
    
    public void CargarDatos(){
        
        int i, y, z;   
        modelo.setRowCount(0);
       
        for (i = 0, z = 0; i < listaVehiculoTerrestre.size() ; i++,z++) {
            vehiculoterrestre = (VehiculoTerrestre)listaVehiculoTerrestre.get(z);
            modelo.insertRow(i, new Object[]{});
            modelo.setValueAt(vehiculoterrestre.getTipo(), i, 0);
           // modelo.setValueAt(vehiculoterrestre.getGasolina(), i, 1);
            if(vehiculoterrestre.getGasolina()==false) modelo.setValueAt("TANQUE VACIO",i,1);
            if(vehiculoterrestre.getGasolina()==true) modelo.setValueAt("TANQUE LLENO",i,1);
           // modelo.setValueAt(vehiculoterrestre.getFuncionamotor(), i, 2);
            if(vehiculoterrestre.getFuncionamotor()==false) modelo.setValueAt("NO FUNCIONA",i,2);
            if(vehiculoterrestre.getFuncionamotor()==true) modelo.setValueAt("SI FUNCIONA",i,2);
            modelo.setValueAt(vehiculoterrestre.getMatricula(), i, 3); 
        }
       
        for(y = i, z = 0; y < i + listaVehiculoAereo.size(); y++,z++){
            vehiculoAereo = (VehiculoAereo)listaVehiculoAereo.get(z);
            modelo.insertRow(y, new Object[]{});
            modelo.setValueAt(vehiculoAereo.getTipo(), y, 0);
          //  modelo.setValueAt(vehiculoAereo.getGasolina(), y, 1);
            if(vehiculoAereo.getGasolina()==false) modelo.setValueAt("TANQUE VACIO", y, 1);
            if(vehiculoAereo.getGasolina()==true) modelo.setValueAt("TANQUE LLENO", y, 1);
            //modelo.setValueAt(vehiculoAereo.getFuncionamotor(), y, 2);
            if(vehiculoAereo.getFuncionamotor()==false) modelo.setValueAt("NO FUNCIONA", y, 2);
            if(vehiculoAereo.getFuncionamotor()==true) modelo.setValueAt("SI FUNCIONA", y, 2);
            modelo.setValueAt(vehiculoAereo.getMatricula(), y, 3);
        }
            
    }
    
    public void cargarDatosMaterial(){
        modeloM.setValueAt(sueroT, 0, 0);
        modeloM.setValueAt(botiquinesT, 0, 1);
        modeloM.setValueAt(respiradorT, 0, 2);
        modeloM.setValueAt(desfibriladorT,0, 3);
    }
        

     public void iniciarDiagnosticoVehiculo(){
       diagnosticovehiculo.setTitle("DIAGNOSTICO VEHICULO");
       diagnosticovehiculo.setLocationRelativeTo(null);
       diagnosticovehiculo.setResizable(false);
    }
     
     public void borrardatos(){
        av.Matricula.setText("");  
    }
         public  boolean verificarnombre(String nombre){
        String texto = nombre;
        return texto.matches("^\\p{L}+[\\p{L}\\p{Pd}\\p{Zs}']*\\p{L}+$|^\\p{L}+$");
    }
    public  boolean verificarCedula(String cedula){
        String texto = cedula;
        return texto.matches("[0-9]{0,10}");
    }
    
    public void agregarMaterial(int columna){
        if(columna == 0){
            try{
                int suero = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de suero"));
                if(suero >= 1){
                    sueroT = sueroT + suero;
                    cargarDatosMaterial();
                    guardarMaterialUnidad();
                }else{
                    JOptionPane.showMessageDialog(null,"Error, monto invalido");
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");
            }
        }
        if(columna == 1){
            try{
                int botiquines = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de botiquines"));
                if(botiquines >= 1){
                    botiquinesT = botiquinesT + botiquines;
                    cargarDatosMaterial();
                    guardarMaterialUnidad();
                }else{
                    JOptionPane.showMessageDialog(null,"Error, monto inválido");
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");
            }
        }
        if(columna == 2){
            try{
                int respirador = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de estetoscopio"));
                if(respirador >= 1){
                    respiradorT = respiradorT + respirador;
                    cargarDatosMaterial();
                    guardarMaterialUnidad();
                }else{
                    JOptionPane.showMessageDialog(null,"Error, monto inválido");
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");
            }
        }
        if(columna == 3){
            try{
                int desfibrilador = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de desfribiladores"));
                if(desfibrilador >= 1){
                    desfibriladorT = desfibriladorT + desfibrilador;
                    cargarDatosMaterial();
                    guardarMaterialUnidad();
                }else{
                    JOptionPane.showMessageDialog(null,"Error, monto inválido");
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");
            }
        }
        
    }
    
    public void modificarTerrestre(int fila, int columna){
        
        if(columna == 0){
            JOptionPane.showMessageDialog(null, "No se puede modificar el tipo de Vehiculo");
        }
        if(columna == 1){
            listaVehiculoTerrestre.get(fila).setGasolina(true);
            JOptionPane.showMessageDialog(null,"El tanque se ha llenado Correctamente");
        }
        if(columna == 2){
            listaVehiculoTerrestre.get(fila).setFuncionamotor(true);
            JOptionPane.showMessageDialog(null,"El motor se ha arreglado correctamente");
        }
        if(columna == 3){
            String Matricula = JOptionPane.showInputDialog(null,"Introduzca una nueva matricula");
            listaVehiculoTerrestre.get(fila).setMatricula(Matricula);
        }
    }
    
    public void modificarAereo(int fila, int columna){
        fila = fila - listaVehiculoTerrestre.size();
        if(columna == 0){
            JOptionPane.showMessageDialog(null, "No se puede modificar el tipo de Vehiculo"); 
        }
        if(columna == 1){
            listaVehiculoAereo.get(fila).setGasolina(true);
            JOptionPane.showMessageDialog(null,"El motor se ha arreglado correctamente");
            
        }
        if(columna == 2){
            listaVehiculoAereo.get(fila).setFuncionamotor(true);
            JOptionPane.showMessageDialog(null,"El motor se ha arreglado correctamente");
            
        }
        if(columna == 3){
            String Matricula = JOptionPane.showInputDialog(null,"Introduzca una nueva matricula");
            listaVehiculoAereo.get(fila).setMatricula(Matricula);
        }
    }
 public boolean repetir(String nr){
        int i;
        boolean a=false;
      
        for (i=0; i<tv.TablaVehiculo.getRowCount(); i++){
            if(tv.TablaVehiculo.getValueAt(i, 3).toString().equals(nr)){
              a=true;
            } 
        }
        return a;
    }  
 
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==diagnosticovehiculo.Regresar){
            MenuPrincipal mp = new MenuPrincipal();
            ControllerMenuPrincipal ctrl = new ControllerMenuPrincipal(mp);
            ctrl.iniciar();
            mp.setVisible(true);
            diagnosticovehiculo.setVisible(false);              
        }
        if(ae.getSource()==diagnosticovehiculo.BotonAgregarVehiculo){
            av.setVisible(true);
            av.setResizable(false);
            av.setLocationRelativeTo(null);
            av.setTitle("AGREGAR UN VEHICULO");
            diagnosticovehiculo.setVisible(false);   
        }
        
        if(ae.getSource()==diagnosticovehiculo.BotonListaVehiculos){
            tv.setVisible(true);
            tv.setResizable(false);
            tv.setLocationRelativeTo(null);
            tv.setTitle("LISTA DE VEHICULOS");
            CargarTabla();
            CargarDatos();
            diagnosticovehiculo.setVisible(false);
        }
//////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////MENU AGREGAR VEHICULO//////////////////////////////////
        if(ae.getSource()==av.BotonRegresar){
            diagnosticovehiculo.setVisible(true);
            iniciarDiagnosticoVehiculo();
            av.setVisible(false);        
        }
        if(ae.getSource()==av.BotonAgregar){
            
            String matricula;
            String tipoVehiculo="";
            if(av.RadioBotonAmbulancia.isSelected()) tipoVehiculo="Ambulancia";
            if(av.RadioBotonCarro.isSelected()) tipoVehiculo="Carro";
            if(av.RadioBotonHelicoptero.isSelected()) tipoVehiculo="Helicóptero";
            matricula=av.Matricula.getText();
            CargarTabla();
            CargarDatos();
            
            if(repetir(matricula)==false){
                if(tipoVehiculo.equalsIgnoreCase("Ambulancia") || tipoVehiculo.equalsIgnoreCase("Carro")){ try {
                // significa que el transporte es terrestre y es o una ambulancia o un carro
                VehiculoTerrestre vehiculoTerrestre = new VehiculoTerrestre(false,false,matricula,tipoVehiculo);
                listaVehiculoTerrestre.add(vehiculoTerrestre);
                borrardatos();
                guardarVehiculoTerrestre();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerDiagnosticoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(tipoVehiculo.equalsIgnoreCase("Helicóptero")){
                try {
                    VehiculoAereo vehiculoAereo = new VehiculoAereo(false,false,matricula,tipoVehiculo);
                    listaVehiculoAereo.add(vehiculoAereo);
                    borrardatos();
                    guardarVehiculoAereo();
                } catch (IOException ex) {
                    Logger.getLogger(ControllerDiagnosticoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            }else {
                JOptionPane.showMessageDialog(null, "Error, matricula repetida"); 
            }
            
        }
/////////////////////////////////////////FIN MENU AGREGAR VEHICULO//////////////////////////////////
/////////////////////////////////////////LISTA VEHICULOS//////////////////////////////////

        if(ae.getSource()==tv.BotonRegresar){
            diagnosticovehiculo.setVisible(true);
            tv.setVisible(false);
        }
    
        if(ae.getSource()==tv.Mantenimiento){
            
            int fila = tv.TablaVehiculo.getSelectedRow();
            int columna = tv.TablaVehiculo.getSelectedColumn();
            
            if(fila>=0){
                
                if(fila<listaVehiculoTerrestre.size()){
                    try {
                        modificarTerrestre(fila, columna);
                        CargarDatos();
                        guardarVehiculoTerrestre();
                    } catch (IOException ex) {
                        Logger.getLogger(ControllerDiagnosticoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if (fila>=listaVehiculoTerrestre.size()){
                    try {
                        modificarAereo(fila, columna);
                        CargarDatos();
                        guardarVehiculoAereo();
                    } catch (IOException ex) {
                        Logger.getLogger(ControllerDiagnosticoVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Seleccione una fila y columna para realizar el mantenimiento respectivo");
            }
              
            
        }
        /////////////////////////////////////////FIN LISTA VEHICULO//////////////////////////////////
/////////////////////////////////////////INICIO ABASTECER VEHICULO//////////////////////////////////
        if(ae.getSource()==diagnosticovehiculo.abastecerunidad){
            au.setVisible(true);
            au.setResizable(false);
            au.setLocationRelativeTo(null);
            au.setTitle("ABASTECER UNIDAD");
            diagnosticovehiculo.setVisible(false);
            cargarTablaMaterial();
            cargarDatosMaterial();
        }
        
        if(ae.getSource()==au.Regresar){
            diagnosticovehiculo.setVisible(true);
            au.setVisible(false);
        }
        
        if(ae.getSource()==au.AgregarMaterial){
           int columna = au.TablaMateriales.getSelectedColumn();
           if(columna>=0){
               agregarMaterial(columna);
           }else{
               JOptionPane.showMessageDialog(null,"Seleccione una columna para añadir un material");
           }
       }

        
    }
    
}
