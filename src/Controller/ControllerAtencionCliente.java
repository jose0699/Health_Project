/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Paciente;
import Model.Persona;
import Controller.ControllerPlantillaMedica;
import ViewPlantillaMedica.ListaEmpleados;
import ViewAtencionCliente.AgregarPaciente;
import ViewAtencionCliente.ConsultarListaPaciente;
import ViewAtencionCliente.IngresarPaciente;
import ViewAtencionCliente.MaterialMedico;
import ViewAtencionCliente.OpcionMayor;
import ViewAtencionCliente.OpcionMenor;
import ViewAtencionCliente.TablaMayor;
import ViewAtencionCliente.TablaMenor;

import ViewPrincipal.AtencionCliente;

import ViewPrincipal.MenuPrincipal;
import Controller.ControllerPlantillaMedica;
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
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class ControllerAtencionCliente implements ActionListener {
    int contador = 0;
    
    public static int oxigenoT = 0;
    public static int esterilizadoresT = 0;
    public static int estetoscopioT = 0;
    public static int inyeccionesT = 0;
    public static int gasaT = 0;
    
    private AtencionCliente atencioncliente;
    
    private DefaultTableModel modelo1;
    private DefaultTableModel modelo2;
    private DefaultTableModel modelo3;
    
    Persona persona = new Persona();
    Paciente paciente = new Paciente();
    
    //Vistas
    ListaEmpleados le= new ListaEmpleados();
    AgregarPaciente ap = new AgregarPaciente();
    ConsultarListaPaciente lp = new ConsultarListaPaciente();
    IngresarPaciente ia = new IngresarPaciente();
    MaterialMedico mm = new MaterialMedico();
    //vistas
    
    OpcionMayor p1 = new OpcionMayor();
    OpcionMenor p2 = new OpcionMenor();
    TablaMayor m1 = new TablaMayor();
    TablaMenor m2 = new TablaMenor();
    FileOutputStream fichero = null;
    
    
     public static ArrayList<Paciente> listaPacienteMayor = new ArrayList<Paciente>();
     public static ArrayList<Paciente> listaPacienteMenor = new ArrayList<Paciente>();

    public ControllerAtencionCliente(AtencionCliente atencioncliente){
        this.atencioncliente = atencioncliente;
        this.atencioncliente.AgregarPaciente.addActionListener(this);
        this.atencioncliente.ListaPaciente.addActionListener(this);
        this.atencioncliente.IngresarPaciente.addActionListener(this);
        this.atencioncliente.MaterialMedico.addActionListener(this);
        this.atencioncliente.Regresar.addActionListener(this);
        this.ap.Agregar.addActionListener(this);
        this.ap.Mayor.addActionListener(this);
        this.ap.Menor.addActionListener(this);
        this.ap.Regresar.addActionListener(this);
        this.lp.Mayor.addActionListener(this);
        this.lp.Menor.addActionListener(this);
        this.lp.ModificarDato.addActionListener(this);
        this.lp.EliminarPaciente.addActionListener(this);
        this.lp.cargarDescripcion.addActionListener(this);
        this.lp.EditarDescripcion.addActionListener(this);
        this.lp.Regresar.addActionListener(this);
        this.ia.IngresarPaciente.addActionListener(this);
        this.ia.VerificarMaterial.addActionListener(this);
        this.ia.VerificarPersonal.addActionListener(this);
        this.ia.Ambulatorio.addActionListener(this);
        this.ia.Clinica.addActionListener(this);
        this.ia.Regresar.addActionListener(this);
        this.mm.AgregarMaterial.addActionListener(this);
        this.mm.Regresar.addActionListener(this);
    }
    
    public void guardarPacienteMayor() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosPacienteMayor.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaPacienteMayor);
        canal.close();
    }
    
    public void guardarPM(){
    try{
        guardarPacienteMayor();
    }catch(IOException ioe){}
    }
    
    public void guardarPacienteMenor() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosPacienteMenor.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaPacienteMenor);
        canal.close();
    }
    
    public void guardarPMen(){
    try{
        guardarPacienteMenor();
    }catch(IOException ioe){}    
    }
    
    public void guardarMaterialMedico(){
        File archivo;
        FileWriter escribir;
        PrintWriter linea;
        
        archivo =  new File("materialMedico.txt");
        
        if(!archivo.exists()){
            try {
                archivo.createNewFile();
                escribir = new FileWriter(archivo,false);
                linea = new PrintWriter(archivo);
                linea.println(oxigenoT);
                linea.println(esterilizadoresT);
                linea.println(estetoscopioT);
                linea.println(inyeccionesT);
                linea.println(gasaT);
                linea.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
           try {
                escribir = new FileWriter(archivo,false);
                linea = new PrintWriter(archivo);
                linea.println(oxigenoT);
                linea.println(esterilizadoresT);
                linea.println(estetoscopioT);
                linea.println(inyeccionesT);
                linea.println(gasaT);
                linea.close();
                
            } catch (IOException ex) {
                Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    public void iniciarAtencionCliente(){
       atencioncliente.setTitle("ATENCION CLIENTE");
       atencioncliente.setLocationRelativeTo(null);
       atencioncliente.setResizable(false);
    }
    
    public void cargarTablaMayor(){
            String datos [][] = {};
            String columna [] = {"Nombre","Cédula","Edad","Tipo de Sangre","Ubicación","Cuota pagada"};
            modelo1 = new DefaultTableModel(datos,columna){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            m1.TablaMayor.setModel(modelo1);
    }
    
    public void cargarTablaMenor(){
            String datos [][] = {};
            String columna [] = {"Nombre","Cédula","Edad","Tipo de Sangre","Cita Médica"};
            modelo2 = new DefaultTableModel(datos,columna){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            m2.TablaMenor.setModel(modelo2);  
    }
    
    public void cargarTablaMaterial(){
            String datos [][] = {};
            String columna [] = {"Bombona de Oxígeno","Esterilizadores","Estetoscopio","Gasa","Inyectadora"};
            modelo3 = new DefaultTableModel(datos,columna){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            mm.TablaMateriales.setModel(modelo3);
            modelo3.insertRow(contador, new Object[]{});
    }
    
    public void cargarDatosMayor(){
        for (int i = 0; i < listaPacienteMayor.size() ; i++) {
                paciente = (Paciente)listaPacienteMayor.get(i);
                modelo1.insertRow(contador, new Object[]{});
                modelo1.setValueAt(paciente.getNombre(), contador, 0);
                modelo1.setValueAt(paciente.getCedula(), contador, 1);
                modelo1.setValueAt(paciente.getEdad(), contador, 2);
                modelo1.setValueAt(paciente.getTipoSangre(), contador, 3); 
                modelo1.setValueAt(paciente.getUbicacion(), contador, 4);
                modelo1.setValueAt(paciente.getCouta(), contador, 5);
        }
    }
    
    public void cargarDatosMenor(){
        
        for (int i = 0; i < listaPacienteMenor.size() ; i++) {
                paciente = (Paciente)listaPacienteMenor.get(i);
                modelo2.insertRow(contador, new Object[]{});
                modelo2.setValueAt(paciente.getNombre(), contador, 0);
                modelo2.setValueAt(paciente.getCedula(), contador, 1);
                modelo2.setValueAt(paciente.getEdad(), contador, 2);
                modelo2.setValueAt(paciente.getTipoSangre(), contador, 3); 
                modelo2.setValueAt(paciente.getCita(), contador, 4);
        }
    }
    
    public void cargarDatosMaterial(){
        modelo3.setValueAt(oxigenoT, contador, 0);
        modelo3.setValueAt(esterilizadoresT, contador, 1);
        modelo3.setValueAt(estetoscopioT, contador, 2);
        modelo3.setValueAt(gasaT, contador, 3);
        modelo3.setValueAt(inyeccionesT, contador, 4);
    }
    
    public int validarAgregar(){
        if(ap.Nombre.getText().isEmpty() || ap.Cedula.getText().isEmpty() || ap.Edad.getText().isEmpty() || ap.Descripcion.getText().isEmpty()){    
        return 0;
        }else{
            return 1;
        }
    }

     
    public void agregarMaterial(int columna){
        if(columna == 0){
            try{
                int oxigeno = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de bombona de oxígeno"));
                if(oxigeno >= 1){
                    oxigenoT = oxigenoT + oxigeno;
                    cargarDatosMaterial();
                }else{
                    JOptionPane.showMessageDialog(null,"Error, monto inválido");
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");
            }
        }
        if(columna == 1){
            try{
                int esterilizadores = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de esterilizadores"));
                if(esterilizadores >= 1){
                    esterilizadoresT = esterilizadoresT + esterilizadores;
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
                int estetoscopio = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de estetoscopio"));
                if(estetoscopio >= 1){
                    estetoscopioT = estetoscopioT + estetoscopio;
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
                int gasa = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de gasa"));
                if(gasa >= 1){
                    gasaT = gasaT + gasa;
                    cargarDatosMaterial();
                }else{
                    JOptionPane.showMessageDialog(null,"Error, monto inválido");
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");
            }
        }
        if(columna == 4){
            try{
                int inyecciones = Integer.parseInt(JOptionPane.showInputDialog(null,"Introduzca la cantidad de inyecciones"));
                if(inyecciones >= 1){
                    inyeccionesT = inyeccionesT + inyecciones;
                    cargarDatosMaterial();
                }else{
                    JOptionPane.showMessageDialog(null,"Error, monto inválido");
                }
            }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");
            }
        }
    }
    
    public boolean verificarMaterial(){
        if(oxigenoT>=1 && esterilizadoresT>=1 && estetoscopioT >= 1 && gasaT>=1 && inyeccionesT >= 1){
            return true;
        } else{
            JOptionPane.showMessageDialog(null,"Faltan materiales para ingresar al paciente");
            return false;
        }  
    }
    
    public boolean verificarMedico(){
        int clinica = 0, ambulatorio = 0;
            for(int i = 0; i<ControllerPlantillaMedica.listaPersonalMedico.size();i++){
                if(ControllerPlantillaMedica.listaPersonalMedico.get(i).getLocalizacion().equalsIgnoreCase("Clínica")){
                    clinica++;
                }else if(ControllerPlantillaMedica.listaPersonalMedico.get(i).getLocalizacion().equalsIgnoreCase("Ambulatorio")){
                    ambulatorio++;
                }
            } 
        if(ambulatorio>0){
            return true;
        }
        else if(clinica>0){
            return true;
        }
        else{
            JOptionPane.showMessageDialog(null,"Falta personal medico para ingresar al paciente");
            return false;
        }
        
    }
    
    public void modificarColumnaMayor(int columna, int fila){
        if(columna == 0){
            try{
            Collections.reverse(listaPacienteMayor);
            String nombre = JOptionPane.showInputDialog(null,"Introduzca el nuevo nombre");
            if(persona.verificarNombre(nombre)==true){
                    listaPacienteMayor.get(fila).setNombre(nombre);
                    Collections.reverse(listaPacienteMayor);
                    guardarPM();
                
            }else{
                JOptionPane.showMessageDialog(null,"Nombre inválido");Collections.reverse(listaPacienteMayor);
            }
            }catch(NullPointerException ne){Collections.reverse(listaPacienteMayor);}
            
            
        }if(columna == 1){
            try{
            Collections.reverse(listaPacienteMayor);
            String cedula = JOptionPane.showInputDialog(null,"Introduzca la nueva cédula");
            
            if(persona.verificarCedula(cedula)==true && (repetir(cedula)==0)){
                    listaPacienteMayor.get(fila).setCedula(cedula);
                    Collections.reverse(listaPacienteMayor);
                    guardarPM();
            }else{
                if(persona.verificarCedula(cedula)==false){
                    JOptionPane.showMessageDialog(null,"Cédula inválida");
                }else {
                    JOptionPane.showMessageDialog(null,"Cédula repetida");
                }
                Collections.reverse(listaPacienteMayor);
            }
            }catch(NullPointerException NE){Collections.reverse(listaPacienteMenor);}
            
        }if(columna == 2){
            try{
            Collections.reverse(listaPacienteMayor);
            String edad = JOptionPane.showInputDialog(null,"Introduzca su edad otra vez");
            if(persona.verificarEdad(edad)==true){ 
                    listaPacienteMayor.get(fila).setEdad(edad);
                    Collections.reverse(listaPacienteMayor);
                    guardarPM();
            }else{
                JOptionPane.showMessageDialog(null,"Edad inválida");Collections.reverse(listaPacienteMayor);
            }
            }catch(NullPointerException NE){Collections.reverse(listaPacienteMayor);}
            
        }if(columna == 3){
            try{
               Collections.reverse(listaPacienteMayor);
               int tipoSangre = Integer.parseInt(JOptionPane.showInputDialog(null,"Seleccione su nuevo tipo de sangre\n\n"
                        + "1. A+\n"
                        + "2. A-\n"
                        + "3. B+\n"
                        + "4. B-\n"
                        + "5. AB+\n"
                        + "6. AB-\n"
                        + "7. O+\n"
                        + "8. O-\n\n")); 
                listaPacienteMayor.get(fila).setTipoSangre(tipoSangre);
                Collections.reverse(listaPacienteMayor);
                guardarPM();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");Collections.reverse(listaPacienteMayor);
            }   
            
        }if(columna == 4){
            try{
               Collections.reverse(listaPacienteMayor);
               int ubicacion = Integer.parseInt(JOptionPane.showInputDialog(null,"Seleccione su nueva ubicación\n\n"
                        + "1. Baruta\n"
                        + "2. Chacao\n"
                        + "3. El Hatillo\n"
                        + "4. Libertador\n"
                        + "5. Sucre\n\n")); 
                listaPacienteMayor.get(fila).setUbicacion(ubicacion);
                Collections.reverse(listaPacienteMayor);
                guardarPM();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");Collections.reverse(listaPacienteMayor);
            }   
        }if(columna == 5){
            try{
               Collections.reverse(listaPacienteMayor);
               int couta = Integer.parseInt(JOptionPane.showInputDialog(null,"Seleccione una opción de couta\n\n"
                        + "1. Sí\n"
                        + "2. No\n\n")); 
                listaPacienteMayor.get(fila).setCouta(couta);
                Collections.reverse(listaPacienteMayor);
                guardarPM();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");Collections.reverse(listaPacienteMayor);
            }  
        }
    }
    
    public void modificarColumnaMenor(int columna, int fila){
         if(columna == 0){
            try{
            Collections.reverse(listaPacienteMenor);
            String nombre = JOptionPane.showInputDialog(null,"Introduzca el nuevo nombre");
            if(persona.verificarNombre(nombre)==true){
                    listaPacienteMenor.get(fila).setNombre(nombre);
                    Collections.reverse(listaPacienteMenor);
                    guardarPMen();
            }else{
                JOptionPane.showMessageDialog(null,"Nombre inválido");Collections.reverse(listaPacienteMenor);
            }
            }catch(NullPointerException NE){Collections.reverse(listaPacienteMenor);}
            
        }if(columna == 1){
            try{
            Collections.reverse(listaPacienteMenor);
            String cedula = JOptionPane.showInputDialog(null,"Introduzca la nueva cédula");
            if(persona.verificarCedula(cedula)==true){
                    listaPacienteMenor.get(fila).setCedula(cedula);
                    Collections.reverse(listaPacienteMenor);
                    guardarPMen();
            }else{
                JOptionPane.showMessageDialog(null,"Cédula inválida");Collections.reverse(listaPacienteMenor);
            }
            }catch(NullPointerException NE){Collections.reverse(listaPacienteMenor);}
            
        }if(columna == 2){
            try{
            Collections.reverse(listaPacienteMenor);
            String edad = JOptionPane.showInputDialog(null,"Introduza la edad de nuevo");
            if(persona.verificarEdad(edad)==true){
                    listaPacienteMenor.get(fila).setEdad(edad);
                    Collections.reverse(listaPacienteMenor);
                    guardarPMen();
            }else{
                JOptionPane.showMessageDialog(null,"Edad inválida");Collections.reverse(listaPacienteMenor);
            }
            }catch(NullPointerException NE){Collections.reverse(listaPacienteMenor);}
            
        }if(columna == 3){
            try{
               Collections.reverse(listaPacienteMenor);
               int tipoSangre = Integer.parseInt(JOptionPane.showInputDialog(null,"Seleccione su nuevo tipo de sangre\n\n"
                        + "1. A+\n"
                        + "2. A-\n"
                        + "3. B+\n"
                        + "4. B-\n"
                        + "5. AB+\n"
                        + "6. AB-\n"
                        + "7. O+\n"
                        + "8. O-\n\n")); 
                listaPacienteMenor.get(fila).setTipoSangre(tipoSangre);
                Collections.reverse(listaPacienteMenor);
                guardarPMen();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");Collections.reverse(listaPacienteMenor);
            }   
        }if(columna == 4){
            try{
               Collections.reverse(listaPacienteMenor);
               int cita = Integer.parseInt(JOptionPane.showInputDialog(null,"Seleccione una opcion de cita\n\n"
                        + "1. Sí\n"
                        + "2. No\n\n")); 
                listaPacienteMenor.get(fila).setCita(cita);
                Collections.reverse(listaPacienteMenor);
                guardarPMen();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");Collections.reverse(listaPacienteMenor);
            }  
        }
    }
    
    public void borrarDatos(){
        ap.Nombre.setText("");
        ap.Cedula.setText("");
        ap.Edad.setText("");
        ap.Descripcion.setText("");
        if(ap.Mayor.isSelected()){
            p1.Coutas.clearSelection();
        }
        else if(ap.Menor.isSelected()){
            p2.Cita.clearSelection();
        }
    }
         public int repetir(String nr){
        int i;
        int a=0;
        
        for (i=0; i<m1.TablaMayor.getRowCount(); i++){
            if(m1.TablaMayor.getValueAt(i, 1).toString().equals(nr)){
              a=1;
            } 
        }
        
         for (i=0; i<m2.TablaMenor.getRowCount(); i++){
            if(m2.TablaMenor.getValueAt(i, 1).toString().equals(nr)){
              a=1;
            } 
        } 
        
        return a;
    }
         
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        
       //Configura el boton para regresar al menu principal
       if(ae.getSource()==atencioncliente.Regresar){
            MenuPrincipal mp = new MenuPrincipal();
            ControllerMenuPrincipal ctrl = new ControllerMenuPrincipal(mp);
            ctrl.iniciar();
            mp.setVisible(true);
            atencioncliente.setVisible(false);
       }
       
       //Metodos del boton agregarPaciente
       if(ae.getSource()==atencioncliente.AgregarPaciente){
           ap.Mayor.setSelected(true);
           ap.setVisible(true);
           ap.setTitle("AGREGAR PACIENTE");
           ap.setLocationRelativeTo(null);
           ap.setResizable(false);
           atencioncliente.setVisible(false);
       }
       
       if(ae.getSource()==ap.Regresar){
            atencioncliente.setVisible(true);
            ap.setVisible(false);
            ap.TipoEmergencia.clearSelection();
            ap.PanelEmergencia.removeAll();
        }
       
       if(ae.getSource()==ap.Agregar){
           int flag = 0;
           flag = validarAgregar();
             cargarTablaMenor();
             cargarDatosMenor();
             cargarTablaMayor();
             cargarDatosMayor();
          
            
           if(repetir(ap.Cedula.getText())==0){
                if(ap.Mayor.isSelected() && flag == 1){
               String tipoSangre = ap.TipoSangre.getSelectedItem().toString();
               String ubicacion = p1.Ubicacion.getSelectedItem().toString();
               if(p1.Si.isSelected()){
                   String couta = p1.Si.getText();
                   if(persona.validarDatosComun(ap.Nombre.getText(), ap.Cedula.getText(), ap.Edad.getText())==true){
                       try {
                           Paciente paciente = new Paciente(ap.Nombre.getText(),ap.Cedula.getText(),ap.Edad.getText(),tipoSangre,ap.Descripcion.getText() ,ap.Mayor.getText(), ubicacion,couta);
                           listaPacienteMayor.add(paciente);
                           borrarDatos();
                           guardarPacienteMayor();
                       } catch (IOException ex) {
                           
                       }
                   }
                   
               } else if(p1.No.isSelected()){
                   String couta = p1.No.getText();
                   if(persona.validarDatosComun(ap.Nombre.getText(), ap.Cedula.getText(), ap.Edad.getText())==true){
                       try {
                           Paciente paciente = new Paciente(ap.Nombre.getText(),ap.Cedula.getText(),ap.Edad.getText(),tipoSangre,ap.Descripcion.getText() ,ap.Mayor.getText(), ubicacion,couta);
                           listaPacienteMayor.add(paciente);
                           borrarDatos();
                           guardarPacienteMayor();
                       } catch (IOException ex) {
                           Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
               } else{
                   JOptionPane.showMessageDialog(null,"Llena todos los datos antes de agregar");
               }
               
           }
           else if(ap.Menor.isSelected() && flag == 1){
               if(p2.Si.isSelected()){
                   String tipoSangre = ap.TipoSangre.getSelectedItem().toString();
                   String cita = p2.Si.getText();
                   if(persona.validarDatosComun(ap.Nombre.getText(), ap.Cedula.getText(), ap.Edad.getText())==true){
                       try {
                           Paciente paciente = new Paciente(ap.Nombre.getText(),ap.Cedula.getText(),ap.Edad.getText(),tipoSangre,ap.Descripcion.getText(),ap.Menor.getText(),cita);
                           listaPacienteMenor.add(paciente);
                           borrarDatos();
                           guardarPacienteMenor();
                       } catch (IOException ex) {
                           Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                  
               }
               else if(p2.No.isSelected()){
                   String tipoSangre = ap.TipoSangre.getSelectedItem().toString();
                   String cita = p2.No.getText();
                   if(persona.validarDatosComun(ap.Nombre.getText(), ap.Cedula.getText(), ap.Edad.getText())==true){
                       try {
                           Paciente paciente = new Paciente(ap.Nombre.getText(),ap.Cedula.getText(),ap.Edad.getText(),tipoSangre,ap.Descripcion.getText(),ap.Menor.getText(),cita);
                           listaPacienteMenor.add(paciente);
                           borrarDatos();
                           guardarPacienteMenor();
                       } catch (IOException ex) {
                           Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                   
               }else{
                   JOptionPane.showMessageDialog(null,"Llena todos los datos antes de agregar");
               }
                
           }
           else{
               JOptionPane.showMessageDialog(null,"Llena todos los datos antes de agregar");
           }
           } else {
               if(repetir(ap.Cedula.getText())==1){
                   JOptionPane.showMessageDialog(null,"Error, cédula de paciente repetido");
               }
               if(repetir(ap.Cedula.getText())==2){
                   JOptionPane.showMessageDialog(null,"Error, cédula de conductor repetido");
               } 
               if(repetir(ap.Cedula.getText())==3){
                   JOptionPane.showMessageDialog(null,"Error, cédula de médico repetido");
               } 
           }    
       }
       
       if(ap.Mayor.isSelected()){
            p1.setSize(370, 206);
            p1.setLocation(0, 0);
            ap.PanelEmergencia.removeAll();
            ap.PanelEmergencia.add(p1);
            ap.PanelEmergencia.revalidate();
            ap.PanelEmergencia.repaint();
        }
       
       if(ap.Menor.isSelected()){
            p2.setSize(370, 206);
            p2.setLocation(0, 0);
            ap.PanelEmergencia.removeAll();
            ap.PanelEmergencia.add(p2);
            ap.PanelEmergencia.revalidate();
            ap.PanelEmergencia.repaint();
       }
       
       //Metodos del boton Material Medico
       
       if(ae.getSource()==atencioncliente.MaterialMedico){
           
           mm.setVisible(true);
           mm.setTitle("Material Médico");
           mm.setLocationRelativeTo(null);
           mm.setResizable(false);
           cargarTablaMaterial();
           cargarDatosMaterial();
           atencioncliente.setVisible(false);
       }
       
       if(ae.getSource()==mm.Regresar){
            atencioncliente.setVisible(true);
            mm.setVisible(false);
        }
       
       if(ae.getSource()==mm.AgregarMaterial){
           int columna = mm.TablaMateriales.getSelectedColumn();
           if(columna>=0){
               agregarMaterial(columna);
               guardarMaterialMedico();
           }else{
               JOptionPane.showMessageDialog(null,"Seleccione una columna para añadir un material");
           }
       }
       
       //Metodos del boton Consultar Lista paciente
       if(ae.getSource()==atencioncliente.ListaPaciente){
           lp.setVisible(true);
           lp.setTitle("CONSULTAR TABLA PACIENTE");
           lp.setLocationRelativeTo(null);
           lp.setResizable(false);
           lp.Mayor.setSelected(true);
           atencioncliente.setVisible(false);
       }
       
       if(ae.getSource()==lp.Regresar){
            atencioncliente.setVisible(true);
            lp.setVisible(false);
            lp.OpcionEmergencia.clearSelection();
            lp.TablaEmergencia.removeAll();
        }
       
       if(ae.getSource()==lp.cargarDescripcion){
           
           if(lp.Mayor.isSelected()){
               int fila = m1.TablaMayor.getSelectedRow();
               
               if(fila >= 0){
                   String descripcion;
                   Collections.reverse(listaPacienteMayor);
                   descripcion = listaPacienteMayor.get(fila).getDescripcion();
                   lp.txtDescripcion.setText(descripcion);
                   Collections.reverse(listaPacienteMayor);
               }else{
                   JOptionPane.showMessageDialog(null,"Seleccione a un paciente de la lista");
               }
                
           }
           else if(lp.Menor.isSelected()){
               int fila = m2.TablaMenor.getSelectedRow();
               
               if(fila >= 0){
                   String descripcion;
                   Collections.reverse(listaPacienteMenor);
                   descripcion = listaPacienteMenor.get(fila).getDescripcion();
                   lp.txtDescripcion.setText(descripcion);
                   Collections.reverse(listaPacienteMenor);
               }else{
                   JOptionPane.showMessageDialog(null,"Selecciona a un paciente de la lista");
               }
           }
           
       }
       
       if(ae.getSource()==lp.EditarDescripcion){
           if(lp.Mayor.isSelected()){
               int fila = m1.TablaMayor.getSelectedRow();
               if(fila>=0){
                   try {
                       Collections.reverse(listaPacienteMayor);
                       String descripcion = JOptionPane.showInputDialog(null,"Introduzca la nueva descripción");
                       listaPacienteMayor.get(fila).setDescripcion(descripcion);
                       Collections.reverse(listaPacienteMayor);
                       guardarPacienteMayor();
                   } catch (IOException ex) {
                       
                   }
               }else{
                   JOptionPane.showMessageDialog(null,"Seleccione a un paciente de la lista");
               }
           }
           else if(lp.Menor.isSelected()){
               int fila = m2.TablaMenor.getSelectedRow();
               if(fila>=0){
                   try {
                       Collections.reverse(listaPacienteMenor);
                       String descripcion = JOptionPane.showInputDialog(null,"Introduzca la nueva descripción");
                       listaPacienteMenor.get(fila).setDescripcion(descripcion);
                       Collections.reverse(listaPacienteMenor);
                       guardarPacienteMenor();
                   } catch (IOException ex) {
                       
                   }
               }else{
                   JOptionPane.showMessageDialog(null,"Selecciona a un paciente de la lista");
               }
           }
       }
       
       if(ae.getSource()==lp.ModificarDato){
           if(lp.Mayor.isSelected()){
               int fila = m1.TablaMayor.getSelectedRow();
               if(fila >= 0){
                   int columna = m1.TablaMayor.getSelectedColumn();
                   modificarColumnaMayor(columna,fila); 
               }
               else{
                   JOptionPane.showMessageDialog(null,"Seleccione una fila");
               }
           }
           else if(lp.Menor.isSelected()){
               int fila = m2.TablaMenor.getSelectedRow();
               if(fila >= 0){
                   int columna = m2.TablaMenor.getSelectedColumn();
                   modificarColumnaMenor(columna, fila); 
               }
               else{
                   JOptionPane.showMessageDialog(null,"Seleccione a un paciente de la lista");
               }
           }
       }
    
       if(ae.getSource()==lp.EliminarPaciente){
           if(lp.Mayor.isSelected()){
               int fila = m1.TablaMayor.getSelectedRow();
               if(fila >= 0){
                   try {
                       Collections.reverse(listaPacienteMayor);
                       listaPacienteMayor.remove(fila);
                       Collections.reverse(listaPacienteMayor);
                       guardarPacienteMayor();
                   } catch (IOException ex) {
                       
                   }
               }else{
                   JOptionPane.showMessageDialog(null,"Seleccione a un paciente de la lista");
               }
           }else if(lp.Menor.isSelected()){
               int fila = m2.TablaMenor.getSelectedRow();
               if(fila >= 0){
                   try {
                       Collections.reverse(listaPacienteMenor);
                       listaPacienteMenor.remove(fila);
                       Collections.reverse(listaPacienteMenor);
                       guardarPacienteMenor();
                   } catch (IOException ex) {
                       Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }else{
                   JOptionPane.showMessageDialog(null,"Seleccione a un paciente de la lista");
               }
           }
       }
       
       if(lp.Mayor.isSelected()){
            m1.setSize(600, 450);
            m1.setLocation(0, 0);
            lp.TablaEmergencia.removeAll();
            lp.TablaEmergencia.add(m1);
            lp.TablaEmergencia.revalidate();
            lp.TablaEmergencia.repaint();
            cargarTablaMayor();
            cargarDatosMayor();
       }
      
       if(lp.Menor.isSelected()){
            m2.setSize(600, 450);
            m2.setLocation(0, 0);
            lp.TablaEmergencia.removeAll();
            lp.TablaEmergencia.add(m2);
            lp.TablaEmergencia.revalidate();
            lp.TablaEmergencia.repaint();
            cargarTablaMenor();
            cargarDatosMenor(); 
       }
       
      //Metodos del boton ingresar paciente
      if(ae.getSource()==atencioncliente.IngresarPaciente){
           ia.setVisible(true);
           ia.setTitle("INGRESAR AMBULATORIO");
           ia.setLocationRelativeTo(null);
           ia.setResizable(false);
           ia.Ambulatorio.setSelected(true);
           atencioncliente.setVisible(false);
       }
      
      if(ae.getSource()==ia.Regresar){
          atencioncliente.setVisible(true);
          ia.setVisible(false);
          ia.OpcionIngreso.clearSelection();
          ia.TablaEmergencia.removeAll();
      }
       
       if(ae.getSource()==ia.IngresarPaciente){
           if(ia.Ambulatorio.isSelected()){
               int fila = m2.TablaMenor.getSelectedRow();
               if(fila >= 0){
                   Collections.reverse(listaPacienteMenor);
                   if(listaPacienteMenor.get(fila).getCita().equalsIgnoreCase("Sí") && verificarMaterial()==true && verificarMedico()==true){
                       try {
                           oxigenoT = oxigenoT -1;
                           esterilizadoresT = esterilizadoresT -1;
                           estetoscopioT = estetoscopioT - 1;
                           gasaT = gasaT -1;
                           inyeccionesT = inyeccionesT -1;
                           listaPacienteMenor.remove(fila);
                           JOptionPane.showMessageDialog(null,"El paciente ha ingresado satisfactoriamente");
                           Collections.reverse(listaPacienteMenor);
                           guardarPacienteMenor();
                       } catch (IOException ex) {
                           Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }else if(listaPacienteMenor.get(fila).getCita().equalsIgnoreCase("No")){
                       JOptionPane.showMessageDialog(null,"El paciente no tiene citia previa, por lo tanto no puede ingresar al ambulatorio");
                   }
                   
               } else {
                   JOptionPane.showMessageDialog(null,"Seleccione un paciente antes de ingresar");
               }
               
           }
           else if(ia.Clinica.isSelected()){
               int fila = m1.TablaMayor.getSelectedRow();
               if(fila>=0){
                   Collections.reverse(listaPacienteMayor);
                   if(listaPacienteMayor.get(fila).getCouta().equalsIgnoreCase("Sí") && verificarMaterial()==true && verificarMedico()==true){
                       try {
                           oxigenoT = oxigenoT -1;
                           esterilizadoresT = esterilizadoresT -1;
                           estetoscopioT = estetoscopioT - 1;
                           gasaT = gasaT -1;
                           inyeccionesT = inyeccionesT -1;
                           listaPacienteMayor.remove(fila);
                           JOptionPane.showMessageDialog(null,"El paciente ha ingresado satisfactoriamente");
                           Collections.reverse(listaPacienteMayor);
                           guardarPacienteMayor();
                       } catch (IOException ex) {
                           Logger.getLogger(ControllerAtencionCliente.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }else if(listaPacienteMayor.get(fila).getCouta().equalsIgnoreCase("No")){
                       JOptionPane.showMessageDialog(null,"El paciente no tiene sus coutas al dia, por lo tanto no puede ingresar a la clínica");
                   }
               }
               else{
                   JOptionPane.showMessageDialog(null,"Seleccione un paciente antes de ingresar");
               }
                
           }
       }
       
       if(ae.getSource()==ia.VerificarMaterial){
           if(oxigenoT>=1 && esterilizadoresT>=1 && estetoscopioT >= 1 && gasaT>=1 && inyeccionesT >= 1){
               JOptionPane.showMessageDialog(null,"Hay suficiente material para ingresar a un paciente\n\n"
                       + "Lista de Materiales: \n"
                       + "\nBombona de Oxígeno = "+oxigenoT
                       + "\nEsterilizadores = "+esterilizadoresT
                       + "\nEstetoscopio = "+estetoscopioT
                       + "\nGasa = "+gasaT
                       + "\nInyecciones = "+inyeccionesT);
            }else{
              JOptionPane.showMessageDialog(null,"Faltan materiales para ingresar a un paciente, se necesita un material de cada tipo para ingresarlo\n\n"
                       + "Lista de Materiales: \n"
                       + "\nBombona de Oxígeno = "+oxigenoT
                       + "\nEsterilizadores = "+esterilizadoresT
                       + "\nEstetoscopio = "+estetoscopioT
                       + "\nGasa = "+gasaT
                       + "\nInyecciones = "+inyeccionesT
                       +"\n\nLas opciones que tienen cero, son las que se necesitan para ingresar al paciente"
                       + "\nPara agregarlos, vaya al apartado de Agregar Materiales en Atencion al cliente");
           }  
       }
       
       if(ae.getSource()==ia.VerificarPersonal){
           if(ControllerPlantillaMedica.listaPersonalMedico.size()==0){
               JOptionPane.showMessageDialog(null,"No existe personal medico para atendder al paciente\n\n"
                       + "Para agregar un personal vaya al menu de plantilla medica y agrega un medico");
           }else{
              int clinica = 0, ambulatorio = 0;
              for(int i = 0; i<ControllerPlantillaMedica.listaPersonalMedico.size();i++){
                  if(ControllerPlantillaMedica.listaPersonalMedico.get(i).getLocalizacion().equalsIgnoreCase("Clínica")){
                      clinica++;
                  }else if(ControllerPlantillaMedica.listaPersonalMedico.get(i).getLocalizacion().equalsIgnoreCase("Ambulatorio")){
                      ambulatorio++;
                  }
              } 
              JOptionPane.showMessageDialog(null,"Actualmente estan registrado el siguiente personal: \n\n"
                      + "Médicos en Clínica: "+clinica
                      + "\nMédicos en Ambulatorio: "+ambulatorio); 
           }
           
       }
       
       if(ia.Clinica.isSelected()){
            m1.setSize(600, 450);
            m1.setLocation(0, 0);
            ia.TablaEmergencia.removeAll();
            ia.TablaEmergencia.add(m1);
            ia.TablaEmergencia.revalidate();
            ia.TablaEmergencia.repaint();
            cargarTablaMayor();
            cargarDatosMayor();
       }
      
       if(ia.Ambulatorio.isSelected()){
            m2.setSize(600, 450);
            m2.setLocation(0, 0);
            ia.TablaEmergencia.removeAll();
            ia.TablaEmergencia.add(m2);
            ia.TablaEmergencia.revalidate();
            ia.TablaEmergencia.repaint();
            cargarTablaMenor();
            cargarDatosMenor(); 
       }
   
    }
    
}
