
package Controller;


import static Controller.ControllerDiagnosticoVehiculo.*;
import static Controller.ControllerPlantillaMedica.listaConductor;
import Model.Conductor;
import Model.Vehiculo;
import Model.VehiculoAereo;
import Model.VehiculoTerrestre;
import ViewPrincipal.MenuPrincipal;
import ViewPrincipal.TransporteEmergencia;
import ViewTrasporteEmergencia.TablaServicioActivo;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControllerTransporteEmergencia implements ActionListener {
    Conductor conductor;
    Conductor conductorTerrestre;
    Conductor conductorAereo;
    Vehiculo vehiculo;
    VehiculoTerrestre vehiculoterrestre;
    VehiculoAereo vehiculoaereo;
    private DefaultTableModel modelo;
    private DefaultTableModel modelo1;
    private DefaultTableModel modeloM;
    private DefaultTableModel modeloA;
    TransporteEmergencia transporteemergencia;
    TablaServicioActivo sa = new TablaServicioActivo();
    
    public static ArrayList<VehiculoTerrestre> listaActivoTerrestre = new ArrayList<VehiculoTerrestre>();
    public static ArrayList<VehiculoAereo> listaActivoAereo = new ArrayList<VehiculoAereo>();
    public static ArrayList<Conductor> listaActivoConductorTerrestre = new ArrayList<Conductor>();
    public static ArrayList<Conductor> listaActivoConductorAereo = new ArrayList<Conductor>();

     FileOutputStream fichero = null;
    
    public ControllerTransporteEmergencia(TransporteEmergencia transporteemergencia) {
        this.transporteemergencia=transporteemergencia;
        this.transporteemergencia.Enviar.addActionListener(this);
        this.transporteemergencia.Regresar.addActionListener(this);
        this.transporteemergencia.UnidadesActivas.addActionListener(this);
        this.sa.RegresarClinica.addActionListener(this);
        this.sa.BotonRegresar.addActionListener(this);
    }
    
    public void guardarVehiculoTerrestre() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosVehiculoTerrestre.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaVehiculoTerrestre);
        canal.close();
    }
    
    public void guardarVehiculoAereo() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosvehiculoaereo.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaVehiculoAereo);
        canal.close();
    }
    
    public void guardarConductor() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosConductor.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaConductor);
        canal.close();
    }
    
    public void guardarActivoTerrestre() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosActivoTerrestre.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaActivoTerrestre);
        canal.close();
    }
    
    public void guardarActivoAereo() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosActivoAereo.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaActivoAereo);
        canal.close();
    }
    
    public void guardarActivoConductorTerrestre() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosActivoConductorTerrestre.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaActivoConductorTerrestre);
        canal.close();
    }
    
    public void guardarActivoConductorAereo() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosActivoConductorAereo.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaActivoConductorAereo);
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
            String columna1 [] ={"Nombre","Cédula","Edad","Tipo de Sangre","Horario"};
            modelo= new DefaultTableModel(datos,columna){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            modelo1=new DefaultTableModel(datos,columna1){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            transporteemergencia.TablaVehiculos.setModel(modelo);
            transporteemergencia.TablaConductor.setModel(modelo1);
    }
    
    public void CargarTablaActivos(){
            String datos [][] = {};
            String columna [] = {"Conductor","Cédula","Vehículo","Matrícula"};
            modeloA= new DefaultTableModel(datos,columna){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            sa.TablaActivos.setModel(modeloA);
            
    }
    
    public void cargarTablaMaterial(){
            String datos [][] = {};
            String columna [] = {"Suero","Botiquines","Respirador","Desfibrilador"};
            modeloM = new DefaultTableModel(datos,columna){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            transporteemergencia.TablaMateriales.setModel(modeloM);
            modeloM.insertRow(0, new Object[]{});
    }
    
    public void cargarDatosMaterial(){
        modeloM.setValueAt(sueroT, 0, 0);
        modeloM.setValueAt(botiquinesT, 0, 1);
        modeloM.setValueAt(respiradorT, 0, 2);
        modeloM.setValueAt(desfibriladorT,0, 3);
        
    }
    
    public void CargarDatos(){
        
        int i,y,z;
        
        modelo.setRowCount(0);
        modelo1.setRowCount(0);
        
        for (i = 0, z = 0; i < listaVehiculoTerrestre.size() ; i++,z++) {
            vehiculoterrestre = (VehiculoTerrestre)listaVehiculoTerrestre.get(z);
            modelo.insertRow(i, new Object[]{});
            modelo.setValueAt(vehiculoterrestre.getTipo(), i, 0);
          //  modelo.setValueAt(vehiculoterrestre.getGasolina(), i, 1);
            if(vehiculoterrestre.getGasolina()==false) modelo.setValueAt("SIN GASOLINA",i,1);
            if(vehiculoterrestre.getGasolina()==true) modelo.setValueAt("CON GASOLINA",i,1);          
           // modelo.setValueAt(vehiculoterrestre.getFuncionamotor(), i, 2);
            if(vehiculoterrestre.getFuncionamotor()==false) modelo.setValueAt("NECESITA REPARO",i,2);
            if(vehiculoterrestre.getFuncionamotor()==true) modelo.setValueAt("SI FUNCIONA",i,2);           
            modelo.setValueAt(vehiculoterrestre.getMatricula(), i, 3); 
        }
         
       
        for(y = i, z = 0; y < i + listaVehiculoAereo.size(); y++,z++){
            vehiculoaereo = (VehiculoAereo)listaVehiculoAereo.get(z);
            modelo.insertRow(y, new Object[]{});
            modelo.setValueAt(vehiculoaereo.getTipo(), y, 0);
           // modelo.setValueAt(vehiculoaereo.getGasolina(), y, 1);
            if(vehiculoaereo.getGasolina()==false) modelo.setValueAt("SIN GASOLINA", y, 1);
            if(vehiculoaereo.getGasolina()==true) modelo.setValueAt("CON GASOLINA", y, 1);           
           // modelo.setValueAt(vehiculoaereo.getFuncionamotor(), y, 2);
            if(vehiculoaereo.getFuncionamotor()==false) modelo.setValueAt("NECESITA REPARO", y, 2);
            if(vehiculoaereo.getFuncionamotor()==true) modelo.setValueAt("SI FUNCIONA", y, 2);            
            modelo.setValueAt(vehiculoaereo.getMatricula(), y, 3); 
        }
        
        for (i = 0; i < listaConductor.size() ; i++) {
            conductor = (Conductor)listaConductor.get(i);
            modelo1.insertRow(i, new Object[]{});
            modelo1.setValueAt(conductor.getNombre(), i, 0);
            modelo1.setValueAt(conductor.getCedula(), i, 1);
            modelo1.setValueAt(conductor.getEdad(), i, 2);
            modelo1.setValueAt(conductor.getTipoSangre(), i, 3); 
            modelo1.setValueAt(conductor.getHorario(),i,4);
        }  
               
    }
    
    public void CargarDatosActivos(){
        
        int i,y,z;
        
        modeloA.setRowCount(0);
        
        for (i = 0, z = 0; i < listaActivoConductorAereo.size() ; i++,z++) {
            conductorAereo = (Conductor)listaActivoConductorAereo.get(z);
            vehiculoaereo = (VehiculoAereo)listaActivoAereo.get(z);
            modeloA.insertRow(i, new Object[]{});
            modeloA.setValueAt(conductorAereo.getNombre(), i, 0);
            modeloA.setValueAt(conductorAereo.getCedula(), i, 1);
            modeloA.setValueAt(vehiculoaereo.getTipo(), i, 2);
            modeloA.setValueAt(vehiculoaereo.getMatricula(), i, 3);
        }
        
        for (i = 0, z = 0; i < listaActivoConductorTerrestre.size() ; i++,z++) {
            conductorTerrestre = (Conductor)listaActivoConductorTerrestre.get(z);
            vehiculoterrestre = (VehiculoTerrestre)listaActivoTerrestre.get(z);
            modeloA.insertRow(i, new Object[]{});
            modeloA.setValueAt(conductorTerrestre.getNombre(), i, 0);
            modeloA.setValueAt(conductorTerrestre.getCedula(), i, 1);
            modeloA.setValueAt(vehiculoterrestre.getTipo(), i, 2);
            modeloA.setValueAt(vehiculoterrestre.getMatricula(), i, 3);
        }
               
    }
    
    public boolean verificarMaterial(){
        if(sueroT>=1 && botiquinesT>=1 && respiradorT >= 1 && desfibriladorT>=1){
            return true;
        } else{
            JOptionPane.showMessageDialog(null,"Faltan materiales para ingresar al paciente");
            return false;
        }  
    }
    
    public void agregarMaterial(int columna){
        if(columna == 0){
            try{
                int suero = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de suero"));
                if(suero >= 1){
                    sueroT = sueroT + suero;
                    cargarDatosMaterial();
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
                }else{
                    JOptionPane.showMessageDialog(null,"Error, monto inválido");
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");
            }
        }
        
    }
    
    public boolean verificarParamedico(){
        int paramedico = 0;
            for(int i = 0; i<ControllerPlantillaMedica.listaPersonalMedico.size();i++){
                if(ControllerPlantillaMedica.listaPersonalMedico.get(i).getProfesion().equalsIgnoreCase("ParaMédico")){
                    paramedico++;
                }
            }
        if(paramedico>0){
           return true; 
        }
        else{
            JOptionPane.showMessageDialog(null,"Faltan Paramedicos para enviar la unidad");
            return false;
        }
        
    }
    
    public boolean verificarUnidad(int fila){
        if(fila <listaVehiculoTerrestre.size()){
            if(listaVehiculoTerrestre.get(fila).getFuncionamotor()== true && listaVehiculoTerrestre.get(fila).getGasolina()==true){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"El vehiculo no esta en condiciones para recoger al paciente");
                return false;
            }
        }
        else {
            fila = fila - listaVehiculoTerrestre.size();
            if(listaVehiculoAereo.get(fila).getFuncionamotor()== true && listaVehiculoAereo.get(fila).getGasolina()==true){
                return true;
            }
            else{
                JOptionPane.showMessageDialog(null,"El vehiculo no esta en condiciones para recoger al paciente");
                return false;
            }
        }
    
    }
 
    
    public void iniciarTransporte(){
        transporteemergencia.setTitle("TRANSPORTE EMERGENCIA"); //El titulo que tendra en la ventana
        transporteemergencia.setLocationRelativeTo(null); //Para que se inicie la ventana en el centro
        transporteemergencia.setResizable(false);
        CargarTabla();
        cargarTablaMaterial();
        cargarDatosMaterial();
        CargarDatos();
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getSource()==transporteemergencia.Regresar){
            MenuPrincipal mp = new MenuPrincipal();
            ControllerMenuPrincipal ctrl = new ControllerMenuPrincipal(mp);
            ctrl.iniciar();
            mp.setVisible(true);
            transporteemergencia.setVisible(false);          
        }
        
        if(ae.getSource()==transporteemergencia.Enviar){
            
            int filaConductor = transporteemergencia.TablaConductor.getSelectedRow();
            int filaVehiculo = transporteemergencia.TablaVehiculos.getSelectedRow();
            
            if(filaConductor >=0 && filaVehiculo>=0 && verificarMaterial()==true ){
                
               if(filaVehiculo<listaVehiculoTerrestre.size() && verificarParamedico()==true && verificarUnidad(filaVehiculo)==true){
                   listaActivoTerrestre.add(listaVehiculoTerrestre.get(filaVehiculo));
                   listaActivoConductorTerrestre.add(listaConductor.get(filaConductor));
                   listaVehiculoTerrestre.remove(filaVehiculo);
                   listaConductor.remove(filaConductor);
                   sueroT = sueroT -1;
                   botiquinesT = botiquinesT -1;
                   respiradorT = respiradorT - 1;
                   desfibriladorT = desfibriladorT -1;
                   JOptionPane.showMessageDialog(null,"El vehículo terrestre se ha enviado satisfactoriamente");
                   CargarDatos();
                   cargarDatosMaterial();
                   try {
                       guardarVehiculoTerrestre();
                       guardarConductor();
                       guardarActivoTerrestre();
                       guardarActivoConductorTerrestre();
                       guardarMaterialUnidad();
                   } catch (IOException ex) {
                       Logger.getLogger(ControllerTransporteEmergencia.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
               else if (filaVehiculo>=listaVehiculoTerrestre.size() && verificarParamedico()==true && verificarUnidad(filaVehiculo)==true){
                   System.out.println("hola");
                   listaActivoAereo.add(listaVehiculoAereo.get(filaVehiculo-listaVehiculoTerrestre.size()));
                   listaActivoConductorAereo.add(listaConductor.get(filaConductor));
                   listaVehiculoAereo.remove(filaVehiculo-listaVehiculoTerrestre.size());
                   listaConductor.remove(filaConductor);
                   sueroT = sueroT -1;
                   botiquinesT = botiquinesT -1;
                   respiradorT = respiradorT - 1;
                   desfibriladorT = desfibriladorT -1;
                   JOptionPane.showMessageDialog(null,"El vehículo aéreo se ha enviado satisfactoriamente");
                   CargarDatos();
                   cargarDatosMaterial();
                   try {
                       guardarVehiculoAereo();
                       guardarConductor();
                       guardarActivoAereo();
                       guardarActivoConductorAereo();
                       guardarMaterialUnidad();
                   } catch (IOException ex) {
                       Logger.getLogger(ControllerTransporteEmergencia.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }
                
            }
            else if(filaConductor <=0 || filaVehiculo <=0){
                JOptionPane.showMessageDialog(null,"Seleccione un conductor y un vehículo");
            }
            
        }
        
        
        // Frame de Servicio Activo
        if(ae.getSource()==transporteemergencia.UnidadesActivas){
            sa.setVisible(true);
            sa.setResizable(false);
            sa.setLocationRelativeTo(null);
            sa.setTitle("CONDUCTORES EN OPERACIÓN");
            transporteemergencia.setVisible(false);
            CargarTablaActivos();
            CargarDatosActivos();
        }
        
        if(ae.getSource()==sa.BotonRegresar){
            transporteemergencia.setVisible(true);
            sa.setVisible(false);
            CargarDatos();
            cargarDatosMaterial();
            
        }
        
        if(ae.getSource()==sa.RegresarClinica){
            
            int fila = sa.TablaActivos.getSelectedRow();
            
            if(fila>=0){
                
                if(fila<listaActivoTerrestre.size()){
                   listaVehiculoTerrestre.add(listaActivoTerrestre.get(fila));
                   listaConductor.add(listaActivoConductorTerrestre.get(fila));
                   listaActivoTerrestre.remove(fila);
                   listaActivoConductorTerrestre.remove(fila);
                   JOptionPane.showMessageDialog(null,"El vehículo terrestre ha regresado a la clínica");
                   try {
                       guardarVehiculoTerrestre();
                       guardarConductor();
                       guardarActivoTerrestre();
                       guardarActivoConductorTerrestre();
                   } catch (IOException ex) {
                       Logger.getLogger(ControllerTransporteEmergencia.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   CargarDatosActivos();
               }
               else{
                   listaVehiculoAereo.add(listaActivoAereo.get(fila-listaActivoTerrestre.size()));
                   listaConductor.add(listaActivoConductorAereo.get(fila-listaActivoTerrestre.size()));
                   listaActivoAereo.remove(fila-listaActivoTerrestre.size());
                   listaActivoConductorAereo.remove(fila-listaActivoTerrestre.size());
                   JOptionPane.showMessageDialog(null,"El vehiculo Aéreo ha regresado a la clínica");
                   try {
                       guardarVehiculoAereo();
                       guardarConductor();
                       guardarActivoAereo();
                       guardarActivoConductorAereo();
                   } catch (IOException ex) {
                       Logger.getLogger(ControllerTransporteEmergencia.class.getName()).log(Level.SEVERE, null, ex);
                   }
                   CargarDatosActivos();
               }
            }
            else{
                JOptionPane.showMessageDialog(null,"Seleccione un conductor Activo");
            }
            
        }
        
    }
    
    
    
}
