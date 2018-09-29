/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

import models.ModelC;
import views.ViewC;
/**
 *
 * @author Abi Montes|
 */
public class ControllerC {
     ModelC modelc; // Crea un objeto para acceder al contenido del Model.
    ViewC viewc;
    
    
      ActionListener actionlistener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewc.jb_guardar) { // Comprueba si se selecciona el botón "Guardar".
                jb_guardar_action_performed();
                
            }
            else if (e.getSource() == viewc.jb_nuevo) { // Comprueba si se selecciona el botón "Nuevo".
                jb_nuevo_action_performed();
            }
            else if (e.getSource() == viewc.jb_primero) { // Comprueba si se selecciona el botón "Primer registro".
                jb_primero_action_performed();
            }
            else if (e.getSource() == viewc.jb_anterior) { // Comprueba si se selecciona el botón "registro Anterior".
                jb_anterior_action_performed();
            }
            else if (e.getSource() == viewc.jb_siguiente) { // Comprueba si se selecciona el botón "registro Siguiente".
                jb_siguiente_action_performed();
            }
            else if (e.getSource() == viewc.jb_ultimo) { // Comprueba si se selecciona el botón "Último registro".
                jb_ultimo_action_performed();
            }
        }
    };
       public ControllerC(ModelC modelc, ViewC viewc) {
       this.modelc = modelc;
        this.viewc = viewc;
        
        this.viewc.jb_primero.addActionListener(actionlistener);
        this.viewc.jb_anterior.addActionListener(actionlistener);
        this.viewc.jb_siguiente.addActionListener(actionlistener);
        this.viewc.jb_ultimo.addActionListener(actionlistener);
        this.viewc.jb_guardar.addActionListener(actionlistener);
        this.viewc.jb_nuevo.addActionListener(actionlistener);
        initComponents();
        
        this.readFirst(modelc.getPath()); // muestra el primer registro ejecutandose
    
    }
       public void writeFile (String path, String message) {
           try {
            File archivo = new File(path); // Abre el archivo en ruta o la crea
            FileWriter filewriter = new FileWriter(archivo, true); 
            
            try (PrintWriter printwriter = new PrintWriter(filewriter) ) { // Guarda el contenido
                printwriter.println(message);
                printwriter.close();
            }
        }
        catch (FileNotFoundException err) { //solo si no se encuentra el archivo
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { //si no se puede acceder al archivo
            System.err.println("Error de entrada y salida " + err.getMessage());
        }
    
       }
       
           public String readFirst (String path) {
        try {
            String row; 
            String acumulador_texto = ""; // acumula egistros del .csv
            try (FileReader archivo = new FileReader(path)) { // Permite leer el contenido del archivo.
                BufferedReader bufferedreader = new BufferedReader(archivo); // Permite almacenar el contenido del archivo .csv de forma temporal.
                
//                int num_lineas = 0;
                while ((row = bufferedreader.readLine()) != null ) {// Contador de lineas CSV.
                    acumulador_texto += row + "$"; // Acumulador de lineas CSV.
                }
                String registros[] = acumulador_texto.split("$"); // Separa los registros
                modelc.setNumero(0); //posicion del registro 
                String registro[] = registros[modelc.getNumero()].split(","); // Separa el primer registro por ,
                viewc.jtf_nombre.setText(registro[0]);
                viewc.jtf_email.setText(registro[1]);
            }
        }
        catch (FileNotFoundException err) { // Detecta un error en caso de no encontrar el archivo en el path indicado.
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { // Marca error en caso de no contar con los permisos para acceder al archivo indicado.
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    };
 public void jb_guardar_action_performed() {
     String nombre = viewc.jtf_nombre.getText();
     String email = viewc.jtf_email.getText();
     String registro = nombre + "," + email;
     modelc.setMessage(registro);
     this.writeFile(modelc.getPath(), modelc.getMessage());
     modelc.setMessage(registro);
     this.writeFile(modelc.getPath(), modelc.getMessage()); // Llama al método para guardar nuevo registro.
     this.readLast(modelc.getPath());
        
    }
 public void jb_nuevo_action_performed() {
        viewc.jtf_nombre.setText("");
        viewc.jtf_email.setText("");
    }
  public String readLast (String path) {
        try {
            String row; // Variable que indica una "fila".
            String acumulador_texto = ""; // Variable para acumular todos los registros del .csv.
            try (FileReader archivo = new FileReader(path)) { //  lee el contenido del archivo.
                BufferedReader bufferedreader = new BufferedReader(archivo); // almacena el contenido del archivo .csv temporalmente 
                
                int num_lineas = 0;
                while ((row = bufferedreader.readLine()) != null ) {
                    num_lineas = num_lineas + 1; // Contador de lineas CSV.
                    acumulador_texto += row + "$"; // Acumulador de lineas CSV.
                }
                String registros[] = acumulador_texto.split("$"); // Separa  registros por  ($).
                modelc.setNumero(num_lineas - 1); // Asigna el valor de la posición deseada del registro.
                String registro[] = registros[modelc.getNumero()].split(","); // Separa el último registro por ,
                viewc.jtf_nombre.setText(registro[0]);
                viewc.jtf_email.setText(registro[1]);
            }
        }
        catch (FileNotFoundException err) { // marca error en caso de no encontrar la ruta dada 
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { // error en caso de no contar con los permisos 
            System.err.println("Error en operación entrada/O: " + err.getMessage());
        }
        return null;
    };
  public String readPrevious (String path) {
        try {
            String row; 
            String acumulador_texto = ""; //acumula  los registros del .csv.
            try (FileReader archivo = new FileReader(path)) { // Permite leer el contenido del archivo.
                BufferedReader bufferedreader = new BufferedReader(archivo); // almacena de forma temporal 
                
//                int num_lineas = 0;
                while ((row = bufferedreader.readLine()) != null ) {
//                    num_lineas = num_lineas + 1; // Contador de lineas CSV.
                    acumulador_texto += row + "$"; // Acumulador de lineas CSV.
                }
                String registros[] = acumulador_texto.split("$"); // Separa registros por $
                modelc.setNumero(modelc.getNumero() - 1); // Asigna el valor de la posición deseada 
                if (modelc.getNumero() >= 0) {
                    String registro[] = registros[modelc.getNumero()].split(","); // Separa el siguiente registro por ,
                    viewc.jtf_nombre.setText(registro[0]);
                    viewc.jtf_email.setText(registro[1]);
                }
                else {
                    modelc.setNumero(modelc.getNumero() + 1); 
                    JOptionPane.showMessageDialog(null, "No es el primer registro.");
                }
            }
        }
        catch (FileNotFoundException err) { // Detecta un error la ruta indicada 
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { // Marca error en caso de no contar con los permisos 
            System.err.println("Error en operación entrada/O: " + err.getMessage());
        }
        return null;
    };
   public String readNext (String path) {
        try {
            String row;
            String acumulador_texto = "";
            try (FileReader archivo = new FileReader(path)) { 
                BufferedReader bufferedreader = new BufferedReader(archivo); 
                
                int num_lineas = 0;
                while ((row = bufferedreader.readLine()) != null ) {
                    num_lineas = num_lineas + 1; 
                    acumulador_texto += row + "$"; 
                }
                String registros[] = acumulador_texto.split("$"); 
                modelc.setNumero(modelc.getNumero() + 1); 
                if (modelc.getNumero() < num_lineas) {
                    String registro[] = registros[modelc.getNumero()].split(",");
                    viewc.jtf_nombre.setText(registro[0]);
                    viewc.jtf_email.setText(registro[1]);
                }
                else {
                    modelc.setNumero(modelc.getNumero() - 1); 
                    JOptionPane.showMessageDialog(null, " Este no es el ultimo registro.");
                }
            }
        }
        catch (FileNotFoundException err) { 
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { 
            System.err.println("Error en operación I/O: " + err.getMessage());
        }
        return null;
    };
    
 public void jb_primero_action_performed() {
        this.readFirst(modelc.getPath());
    }
    
    public void jb_anterior_action_performed() {
        this.readPrevious(modelc.getPath());
    }
    
    public void jb_siguiente_action_performed() {
        this.readNext(modelc.getPath());
    }
    
    public void jb_ultimo_action_performed() {
        this.readLast(modelc.getPath());
    }
     
    
public void initComponents() {
        viewc.setVisible(true);
    }
}