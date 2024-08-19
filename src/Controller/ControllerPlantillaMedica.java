/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Conductor;
import Model.Persona;
import Model.PersonalMedico;
import ViewPlantillaMedica.AgregarConductor;
import ViewPlantillaMedica.ListaEmpleados;
import ViewPlantillaMedica.MenuRegistrar;
import ViewPlantillaMedica.AgregarPersonalMedico;
import ViewPlantillaMedica.OpcionMedico;
import ViewPlantillaMedica.OpcionParamedico;
import ViewPrincipal.MenuPrincipal;
import ViewPrincipal.PlantillaMedica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego
 */
public class ControllerPlantillaMedica implements ActionListener {
    int contador = 0;
    Conductor conductor;
    PersonalMedico personalmedico;
    private PlantillaMedica plantillamedica;
    Persona persona = new Persona();
    MenuRegistrar mr = new MenuRegistrar();
    AgregarPersonalMedico apm = new AgregarPersonalMedico();
    AgregarConductor ac = new AgregarConductor();
    ListaEmpleados le = new ListaEmpleados();
    OpcionMedico m1 = new OpcionMedico();
    OpcionParamedico m2 = new OpcionParamedico();
    String RadioBotonLugar;
    String RadioBotonProfesion;
    int flag;
    private DefaultTableModel modelo;
    private DefaultTableModel modelo1;
    FileOutputStream fichero = null;
    
    String stringhorario,horario1,horario2;
    int ihorario1;
      
   public static  ArrayList<Conductor> listaConductor = new ArrayList<Conductor>();
   public static  ArrayList<PersonalMedico> listaPersonalMedico = new ArrayList<PersonalMedico>();
    
    public ControllerPlantillaMedica(PlantillaMedica plantillamedica){
        this.plantillamedica=plantillamedica;
        this.plantillamedica.BotonRegistrarPersonal.addActionListener(this);
        this.plantillamedica.BotonListaEmpleados.addActionListener(this);
        this.plantillamedica.BotonRegresar.addActionListener(this);
        mr.BotonRegistrarConductor.addActionListener(this);
        mr.BotonRegistrarPersonal.addActionListener(this);
        mr.BotonRegresar.addActionListener(this);
        ac.BotonAgregar.addActionListener(this);
        ac.BotonRegresar.addActionListener(this);
        le.BotonRegresar.addActionListener(this);
        le.ModificarDatoPersonalMedico.addActionListener(this);
        le.EliminarPersonalMedico.addActionListener(this);
        le.ModificarDatoConductor.addActionListener(this);
        le.EliminarConductor.addActionListener(this);
        apm.BotonAgregar.addActionListener(this);
        apm.BotonRegresar.addActionListener(this);
        apm.BotonMedico.addActionListener(this);
        apm.BotonParamedico.addActionListener(this);
    }
    
    public void iniciarPlantillaMedica(){
       plantillamedica.setTitle("PLANTILLA MEDICA");
       plantillamedica.setLocationRelativeTo(null);
       plantillamedica.setResizable(false);
    }
    public void guardarConductor() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosConductor.txt");
        ObjectOutputStream canal = new ObjectOutputStream(fichero);
        canal.writeObject(listaConductor);
        canal.close();
    }
    public void guardarPersonalMedico() throws FileNotFoundException, IOException{
        fichero = new FileOutputStream("datosPersonalMedico.txt");
        ObjectOutputStream canal2 = new ObjectOutputStream(fichero);
        canal2.writeObject(listaPersonalMedico);
        canal2.close();
    }    
    public void borrarDatosPersonalMedico(){
        apm.Nombre.setText("");
        apm.Cedula.setText("");
        apm.Edad.setText("");
        apm.HorarioEntrada.setText("");
        apm.HorarioSalida.setText("");
        apm.Profesion.clearSelection();
        m1.OpcionTrabajo.clearSelection();
        
        horario1=numerorandom();
        ihorario1=Integer.parseInt(horario1);
        horario2=horasalida(ihorario1);
            
        apm.HorarioEntrada.setText(horario1);
        apm.HorarioSalida.setText(horario2);         
    }
    
    public void borrarDatosConductor(){
        ac.Nombre.setText("");
        ac.Cedula.setText("");
        ac.Edad.setText("");
        ac.HorarioEntrada.setText("");
        ac.HorarioSalida.setText("");
        
        horario1=numerorandom();
        ihorario1=Integer.parseInt(horario1);
        horario2=horasalida(ihorario1);
            
        ac.HorarioEntrada.setText(horario1);
        ac.HorarioSalida.setText(horario2);         
    }
   
  
    public void CargarTabla(){
            String datos [][] = {};
            String columna [] = {"Nombre","Cédula","Edad","Tipo de Sangre","Horario"};
            String columna1 []= {"Nombre","Cédula","Edad","Tipo de Sangre","Horario","Profesion","Localización"};
            modelo= new DefaultTableModel(datos,columna){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            modelo1=new DefaultTableModel(datos,columna1){
                public boolean isCellEditable(int rowIndex,int columnIndex){return false;}
            };
            le.tablaconductor.setModel(modelo);
            le.tablapersonalmedico.setModel(modelo1);
    }
    
    public void CargarDatos(){
    
        modelo.setRowCount(0);
        modelo1.setRowCount(0);
       
        for (int i = 0; i < listaConductor.size() ; i++) {
           
                conductor = (Conductor)listaConductor.get(i);
                modelo.insertRow(contador, new Object[]{});
                modelo.setValueAt(conductor.getNombre(), contador, 0);
                modelo.setValueAt(conductor.getCedula(), contador, 1);
                modelo.setValueAt(conductor.getEdad(), contador, 2);
                modelo.setValueAt(conductor.getTipoSangre(), contador, 3); 
                modelo.setValueAt(conductor.getHorario(),contador,4);
      
        }
               for (int i = 0; i < listaPersonalMedico.size() ; i++) {
           
                personalmedico = (PersonalMedico)listaPersonalMedico.get(i);
                modelo1.insertRow(contador, new Object[]{});
                modelo1.setValueAt(personalmedico.getNombre(), contador, 0);
                modelo1.setValueAt(personalmedico.getCedula(), contador, 1);
                modelo1.setValueAt(personalmedico.getEdad(), contador, 2);
                modelo1.setValueAt(personalmedico.getTipoSangre(), contador, 3); 
                modelo1.setValueAt(personalmedico.getHorario(),contador,4);
                modelo1.setValueAt(personalmedico.getProfesion(),contador,5);
                modelo1.setValueAt(personalmedico.getLocalizacion(),contador,6);
      
        }
    }
    
    
        public int validarAgregar(){
        if(apm.Nombre.getText().isEmpty() || apm.Cedula.getText().isEmpty() || apm.Edad.getText().isEmpty() || apm.HorarioEntrada.getText().isEmpty() || apm.HorarioSalida.getText().isEmpty()){    
        return 0;
        }else{
            return 1;
        }
    }
        public int validarAgregarConductor(){
                if(ac.Nombre.getText().isEmpty() || ac.Cedula.getText().isEmpty() || ac.Edad.getText().isEmpty() || ac.HorarioEntrada.getText().isEmpty() || ac.HorarioSalida.getText().isEmpty()){    
        return 0;
        }else{
            return 1;
        }
        }
        
        public void guardarP(){
            try{
               guardarPersonalMedico();
            }
            catch(IOException ex){}
        }
        public void guardarC(){
             try{
               guardarConductor();
            }
            catch(IOException ex){}       
        }

    public void modificarColumnaPersonalMedico(int columna, int fila){
        if(columna == 0){
            try{
            Collections.reverse(listaPersonalMedico);
            String nombre = JOptionPane.showInputDialog(null,"Introduzca el nuevo nombre");
            if(persona.verificarNombre(nombre)==true){
                listaPersonalMedico.get(fila).setNombre(nombre);Collections.reverse(listaPersonalMedico);
                guardarP();
            }else{
                JOptionPane.showMessageDialog(null,"Nombre inválido");Collections.reverse(listaPersonalMedico);
            }
            }catch(NullPointerException NE){Collections.reverse(listaPersonalMedico);
            }
         //   finally{Collections.reverse(listaPersonalMedico);}
            
        }if(columna == 1){
            try{
            Collections.reverse(listaPersonalMedico);
            String cedula = JOptionPane.showInputDialog(null,"Introduzca la nueva cédula");
            if(persona.verificarCedula(cedula)==true && (repetir(cedula)==0)){
                listaPersonalMedico.get(fila).setCedula(cedula);
                Collections.reverse(listaPersonalMedico);
                guardarP();
            }else{
                if(persona.verificarCedula(cedula)==false){
                   JOptionPane.showMessageDialog(null,"Cédula invalida");
                }else {
                    JOptionPane.showMessageDialog(null,"Cédula repetida");
                }
                
                Collections.reverse(listaPersonalMedico);
            }
            }catch(NullPointerException NE){Collections.reverse(listaPersonalMedico);
            }
           // finally{Collections.reverse(listaPersonalMedico);}
            
        }if(columna == 2){
            try{
            Collections.reverse(listaPersonalMedico);
            String edad = JOptionPane.showInputDialog(null,"Introduzca la edad otra vez");
            if(persona.verificarEdad(edad)==true){
                listaPersonalMedico.get(fila).setEdad(edad);
                Collections.reverse(listaPersonalMedico);
                guardarP();
            }else{
                JOptionPane.showMessageDialog(null,"Edad inválida");
                Collections.reverse(listaPersonalMedico);
            }
            }catch(NullPointerException NE){Collections.reverse(listaPersonalMedico);
            }
        //    finally{Collections.reverse(listaPersonalMedico);}
            
        }if(columna == 3){
            try{
               Collections.reverse(listaPersonalMedico);
               int tipoSangre = Integer.parseInt(JOptionPane.showInputDialog(null,"Seleccione su nuevo tipo de sangre\n\n"
                        + "1. A+\n"
                        + "2. A-\n"
                        + "3. B+\n"
                        + "4. B-\n"
                        + "5. AB+\n"
                        + "6. AB-\n"
                        + "7. O+\n"
                        + "8. O-\n\n")); 
                listaPersonalMedico.get(fila).setTipoSangre(tipoSangre);
                Collections.reverse(listaPersonalMedico);
                guardarP();
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opcion");Collections.reverse(listaPersonalMedico);
            }   
            
        }if(columna == 4){
           JOptionPane.showMessageDialog(null,"Los turnos son predefinidos y no se pueden cambiar."); 
        }if(columna == 5){
            try{
               Collections.reverse(listaPersonalMedico);
               int opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"Seleccione profesión\n\n"
                        + "1. Médico\n"
                        + "2. Paramédico\n")); 
                listaPersonalMedico.get(fila).setProfesion(opcion);
                if(listaPersonalMedico.get(fila).getProfesion()=="Paramédico"){
                   JOptionPane.showMessageDialog(null,"Todo paramédico será asignado a una ambulancia"); 
                   listaPersonalMedico.get(fila).setLocalizacion(3); // asignado a ambulance
                }
                
                if(listaPersonalMedico.get(fila).getProfesion()!="Paramédico" && listaPersonalMedico.get(fila).getLocalizacion()=="Ambulancia"){
                    listaPersonalMedico.get(fila).setLocalizacion(1);
                }
        
                Collections.reverse(listaPersonalMedico);guardarP();
               
            }
            catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opcion");
                Collections.reverse(listaPersonalMedico);
            }
        }if(columna==6){
            if((listaPersonalMedico.get(fila).getProfesion()=="Paramédico")){
                JOptionPane.showMessageDialog(null,"Los paramédicos solo van en ambulancias.");
            }else{
                try{
                    Collections.reverse(listaPersonalMedico);
                    int opcion;
                    do{
                    opcion = Integer.parseInt(JOptionPane.showInputDialog(null,"Seleccione localización\n\n"
                        + "1. Clínica\n"
                        + "2. Ambulatorio\n"));
                        if(opcion!= 1 && opcion !=2) JOptionPane.showMessageDialog(null,"Un médico o enfermero solo puede ser asignado a una clínica o ambulatorio.");
                    }while(opcion != 1 && opcion !=2);
                        listaPersonalMedico.get(fila).setLocalizacion(opcion);
                        Collections.reverse(listaPersonalMedico);guardarP();
                    }
                 catch(NumberFormatException ex ){
                    JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opcion");
                    Collections.reverse(listaPersonalMedico);
                }
            }          
        
        }
    }
    
    public void modificarColumnaConductor(int columna, int fila){
        if(columna == 0){
            Collections.reverse(listaConductor);
            try{
            String nombre = JOptionPane.showInputDialog(null,"introduzca el nuevo nombre");
            if(persona.verificarNombre(nombre)==true){
                listaConductor.get(fila).setNombre(nombre);
                Collections.reverse(listaConductor);
                guardarC();
            }else{
                JOptionPane.showMessageDialog(null,"Nombre invalido");Collections.reverse(listaConductor);
            } 
            }catch(NullPointerException ne){Collections.reverse(listaConductor);
            }
        }if(columna == 1){
            Collections.reverse(listaConductor);
            try{
            String cedula = JOptionPane.showInputDialog(null,"introduzca la nueva cedula");
                if(persona.verificarCedula(cedula)==true && (repetir(cedula)==0)){
                listaConductor.get(fila).setCedula(cedula);
                Collections.reverse(listaConductor);
                guardarC();
                }else{
                    if(persona.verificarCedula(cedula)==false){
                        JOptionPane.showMessageDialog(null,"Cedula invalida");
                    }else{
                        JOptionPane.showMessageDialog(null,"Cedula repetida");
                    }
                    Collections.reverse(listaConductor);
                }
                }catch(NullPointerException ne){Collections.reverse(listaConductor);
                }
        }if(columna == 2){
            Collections.reverse(listaConductor);
            try{
                String edad = JOptionPane.showInputDialog(null,"Introduzca la edad otra vez");
                if(persona.verificarEdad(edad)==true){
                    listaConductor.get(fila).setEdad(edad);
                    Collections.reverse(listaConductor);
                    guardarC();
                    }else{
                        JOptionPane.showMessageDialog(null,"Edad inválida");Collections.reverse(listaConductor);
                    }
                    }catch(NullPointerException ne){Collections.reverse(listaConductor);
                    }
        }if(columna == 3){
            String tipoSangre;
            int sangreint=0;
            try{
               Collections.reverse(listaConductor);
               tipoSangre = JOptionPane.showInputDialog(null,"Seleccione su nuevo tipo de sangre\n\n"
                        + "1. A+\n"
                        + "2. A-\n"
                        + "3. B+\n"
                        + "4. B-\n"
                        + "5. AB+\n"
                        + "6. AB-\n"
                        + "7. O+\n"
                        + "8. O-\n\n");
                 sangreint = Integer.parseInt(tipoSangre);
                listaConductor.get(fila).setTipoSangre(Integer.parseInt(tipoSangre));
                Collections.reverse(listaConductor);
                guardarC();
            }
            catch(NullPointerException ex){
                
            }
            catch(NumberFormatException NE){
               JOptionPane.showMessageDialog(null,"Error, no se pueden escribir caracteres en esta opción");Collections.reverse(listaConductor);
           }
 
            
        }if(columna == 4){
            JOptionPane.showMessageDialog(null,"Los turnos son predefinidos y no se pueden cambiar.");
        }
    }
    
        public String numerorandom(){
      int min = 00;
      int max = 23;
      int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
      int n=random_int;
      String resultado;
      if(n==0) return resultado="000"+n;
      if(n==1) return resultado="0"+n+"00";
      if(n>1 && n<=9){
          n=n*100;
          return resultado="0"+n;
      }
      else{
          n=n*100;
          return resultado=String.valueOf(n);
        }
      
    }
    
    public String horasalida(int horaentrada){
        int n;
        String resultado;
        if(horaentrada==0)     {n=800;return resultado="0"+n;}
        if(horaentrada==100)  {n=900;return resultado="0"+n;}
        if(horaentrada==1600)  {n=0;return resultado="000"+n;}
        if(horaentrada==1700)  {n=100;return resultado="0"+n;}
        if(horaentrada==1800)  {n=200;return resultado="0"+n;}
        if(horaentrada==1900)  {n=300;return resultado="0"+n;}
        if(horaentrada==2000)  {n=400;return resultado="0"+n;}
        if(horaentrada==2100)  {n=500;return resultado="0"+n;}
        if(horaentrada==2200)  {n=600;return resultado="0"+n;}
        if(horaentrada==2300)  {n=700;return resultado="0"+n;}
        
        if(horaentrada<=1500){
            n=horaentrada+800;
            return resultado=String.valueOf(n);
        }
        return resultado="0";
       
    }
   public int repetir(String nr){
        int i;
        int a=0;
        
        for (i=0; i<le.tablaconductor.getRowCount(); i++){
            if(le.tablaconductor.getValueAt(i, 1).toString().equals(nr)){
              a=1;
            } 
        }
        
         for (i=0; i<le.tablapersonalmedico.getRowCount(); i++){
            if(le.tablapersonalmedico.getValueAt(i, 1).toString().equals(nr)){
              a=1;
            } 
        } 
         if(a>0){
                JOptionPane.showMessageDialog(null,"Error,cédula repetida");
            } 
        return a;
    }
           
    @Override
    public void actionPerformed(ActionEvent ae) {
  //----------------------MENU DE PLANTILLA MEDICA PRINCIPAL ----------------
        if(ae.getSource()==plantillamedica.BotonRegresar){
            MenuPrincipal mp = new MenuPrincipal();
            ControllerMenuPrincipal ctrl = new ControllerMenuPrincipal(mp);
            ctrl.iniciar();
            mp.setVisible(true);
            plantillamedica.setVisible(false);   
        }
        if(ae.getSource()==plantillamedica.BotonRegistrarPersonal){
            mr.setVisible(true);mr.setResizable(false);
            mr.setLocationRelativeTo(null);
            mr.setTitle("REGISTRAR PERSONAL O CONDUCTOR");
            plantillamedica.setVisible(false);     
        }

        if(ae.getSource()==plantillamedica.BotonListaEmpleados){
            le.setVisible(true);le.setResizable(false);
            le.setLocationRelativeTo(null);
            le.setTitle("LISTA DE EMPLEADOS");
            CargarTabla();
            CargarDatos();
            
            plantillamedica.setVisible(false);
        }
//--------------------------------FIN DE MENU DE PLANTILLA MEDICA PRINCIPAL ---------------------------------        
//----------------------MENU REGISTRAR PERSONALMEDICO O CONDUCTOR ----------------------------------
        if(ae.getSource()==mr.BotonRegresar){
            plantillamedica.setVisible(true);
            iniciarPlantillaMedica();
            mr.setVisible(false);
        }
        if(ae.getSource()==mr.BotonRegistrarConductor){
            ac.setVisible(true);
            ac.setResizable(false);
            ac.setLocationRelativeTo(null);
            ac.setTitle("AGREGAR CONDUCTOR");
            horario1=numerorandom();
            ihorario1=Integer.parseInt(horario1);
            horario2=horasalida(ihorario1);
            
            ac.HorarioEntrada.setText(horario1);
            ac.HorarioSalida.setText(horario2);            
            mr.setVisible(false);
        }
        if(ae.getSource()==mr.BotonRegistrarPersonal){
            apm.setVisible(true);apm.setResizable(false);
            apm.setLocationRelativeTo(null);
            apm.setTitle("AGREGAR PERSONAL MÉDICO");
            
            horario1=numerorandom();
            ihorario1=Integer.parseInt(horario1);
            horario2=horasalida(ihorario1);
            
            apm.HorarioEntrada.setText(horario1);
            apm.HorarioSalida.setText(horario2);            
            mr.setVisible(false);
        }
//----------------------FIN MENU REGISTRAR PERSONALMEDICO O CONDUCTOR --------------------------------
//----------------------MENU REGISTRAR CONDUCTOR -----------------------------------
       if(ae.getSource()==ac.BotonAgregar){
            flag = 0;
            flag = validarAgregarConductor();
            CargarTabla();
            CargarDatos();
            if(0==repetir(ac.Cedula.getText())){
                 if(flag==1){
                    
                String tipoSangre = ac.TipodeSangre.getSelectedItem().toString();
                horario1=ac.HorarioEntrada.getText();
                horario2=ac.HorarioSalida.getText();
                stringhorario=horario1+"-"+horario2;
                Conductor conductor = new Conductor(ac.Nombre.getText(),ac.Cedula.getText(),ac.Edad.getText(),tipoSangre,stringhorario);
                boolean validardatos = conductor.validarDatosComun(ac.Nombre.getText(), ac.Cedula.getText(), ac.Edad.getText()); 
                    if(validardatos==true){
                        try{
                        listaConductor.add(conductor);
                        borrarDatosConductor();
                        guardarConductor();
                        } catch(IOException ex){
                        }
                    }
                }
            else{
                JOptionPane.showMessageDialog(null,"Llena todos los datos antes de agregar");
            }
            }       
       }
       
       if(ae.getSource()==ac.BotonRegresar){
            mr.setVisible(true);
            ac.setVisible(false);
       }
//----------------------FIN REGISTRAR CONDUCTOR ------------------------------------
//----------------------MENU AGREGAR PERSONAL MEDICO------------------------------
        if(ae.getSource()==apm.BotonAgregar){
            flag = 0;
            flag = validarAgregar();
            CargarTabla();
            CargarDatos();
            
            if(0==repetir(ac.Cedula.getText())){
                if(flag==1 && apm.BotonMedico.isSelected()){
                if(m1.Ambulatorio.isSelected()){
                    String tipoSangre = apm.TipodeSangre.getSelectedItem().toString();
                    horario1=apm.HorarioEntrada.getText();
                    horario2=apm.HorarioSalida.getText();                    
                    stringhorario=horario1+"-"+horario2;
                    PersonalMedico personalmedico = new PersonalMedico(apm.Nombre.getText(),apm.Cedula.getText(),apm.Edad.getText(),tipoSangre,stringhorario,apm.BotonMedico.getText(),m1.Ambulatorio.getText());
                    boolean validardatos = persona.validarDatosComun(apm.Nombre.getText(), apm.Cedula.getText(), apm.Edad.getText());
                    if(validardatos==true){
                        try{
                        listaPersonalMedico.add(personalmedico);
                        borrarDatosPersonalMedico();
                        guardarPersonalMedico();
                        } catch(IOException ex){
                        }
                    } 
                }
                else if(m1.Clinica.isSelected()){
                    String tipoSangre = apm.TipodeSangre.getSelectedItem().toString();
                    horario1=apm.HorarioEntrada.getText();
                    horario2=apm.HorarioSalida.getText();
                    stringhorario=horario1+"-"+horario2;
                    PersonalMedico personalmedico = new PersonalMedico(apm.Nombre.getText(),apm.Cedula.getText(),apm.Edad.getText(),tipoSangre,stringhorario,apm.BotonMedico.getText(),m1.Clinica.getText());
                    boolean validardatos = persona.validarDatosComun(apm.Nombre.getText(), apm.Cedula.getText(), apm.Edad.getText());
                    if(validardatos==true){
                        try{
                        listaPersonalMedico.add(personalmedico);
                        borrarDatosPersonalMedico();
                        guardarPersonalMedico();
                        } catch(IOException ex){}
                    } 
                }
                else{
                    JOptionPane.showMessageDialog(null,"Seleccione un lugar de trabajo");
                }
            }
            else if(flag==1 && apm.BotonParamedico.isSelected()){
                String tipoSangre = apm.TipodeSangre.getSelectedItem().toString();
                String stringhorario,horario1,horario2, ambulancia;
                horario1=apm.HorarioEntrada.getText();
                horario2=apm.HorarioSalida.getText();
                stringhorario=horario1+"-"+horario2;
                ambulancia = "Ambulancia";
                PersonalMedico personalmedico = new PersonalMedico(apm.Nombre.getText(),apm.Cedula.getText(),apm.Edad.getText(),tipoSangre,stringhorario,apm.BotonParamedico.getText(),ambulancia);
                boolean validardatos = persona.validarDatosComun(apm.Nombre.getText(), apm.Cedula.getText(), apm.Edad.getText());
                if(validardatos==true){
                    try{
                    listaPersonalMedico.add(personalmedico);
                    borrarDatosPersonalMedico();
                    guardarPersonalMedico();
                    } catch(IOException ex){
                    }
                } 
            }
            else{
                JOptionPane.showMessageDialog(null,"Llene y seleccione todos los datos antes de continuar");
            }
            }         
        }     
        
        
        if(ae.getSource()==apm.BotonRegresar){
            mr.setVisible(true);
            mr.setResizable(false);
            apm.setVisible(false);
            apm.Profesion.clearSelection();
            m1.OpcionTrabajo.clearSelection();
        }
        
        if(apm.BotonMedico.isSelected()){
            m1.setSize(330, 50);
            m1.setLocation(0, 0);
            apm.LugarTrabajo.removeAll();
            apm.LugarTrabajo.add(m1);
            apm.LugarTrabajo.revalidate();
            apm.LugarTrabajo.repaint();
       }
        
        if(apm.BotonParamedico.isSelected()){
            m2.setSize(330, 50);
            m2.setLocation(0, 0);
            apm.LugarTrabajo.removeAll();
            apm.LugarTrabajo.add(m2);
            apm.LugarTrabajo.revalidate();
            apm.LugarTrabajo.repaint();
       }
        
        

//----------------------FIN  AGREGAR PERSONAL MEDICO-------------------------------

//----------------------LISTA EMPLEADOS----------------------------------------------
        if(ae.getSource()==le.BotonRegresar){
           plantillamedica.setVisible(true);
           iniciarPlantillaMedica();
           le.setVisible(false);
        }
        
        if(ae.getSource()==le.ModificarDatoPersonalMedico){
               int fila = le.tablapersonalmedico.getSelectedRow();
               if(fila >= 0){
                   int columna = le.tablapersonalmedico.getSelectedColumn();
                   modificarColumnaPersonalMedico(columna,fila);
                   CargarDatos();
               }
               else{
                   JOptionPane.showMessageDialog(null,"Seleccione una fila");
               }
       }
       if(ae.getSource()==le.EliminarPersonalMedico){             
               int fila = le.tablapersonalmedico.getSelectedRow();
               if(fila >= 0){
                   Collections.reverse(listaPersonalMedico);
                   listaPersonalMedico.remove(fila);
                   Collections.reverse(listaPersonalMedico);
                   CargarDatos();
               }else{
                   JOptionPane.showMessageDialog(null,"Seleccione a un paciente de la lista");
               }
       
       }
       if(ae.getSource()==le.ModificarDatoConductor){
               int fila = le.tablaconductor.getSelectedRow();
               if(fila >= 0){
                   int columna = le.tablaconductor.getSelectedColumn();
                   modificarColumnaConductor(columna,fila);
                   CargarDatos();
               }
               else{
                   JOptionPane.showMessageDialog(null,"Seleccione una fila");
               }
       
       }
       if(ae.getSource()==le.EliminarConductor){
               int fila = le.tablaconductor.getSelectedRow();
               if(fila >= 0){
                   Collections.reverse(listaConductor);
                   listaConductor.remove(fila);
                   Collections.reverse(listaConductor);
                   CargarDatos();
               }else{
                   JOptionPane.showMessageDialog(null,"Seleccione a un paciente de la lista");
               }
       }

//----------------------FIN LISTA EMPLEADOS------------------------------------------

        
    }
    
}