/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
        }
    };
       public ControllerC(ModelC modelc, ViewC viewc) {
        this.modelc = modelc;
        this.viewc = viewc;
        
        this.viewc.jb_guardar.addActionListener(actionlistener);
        this.viewc.jb_nuevo.addActionListener(actionlistener);
        initComponents();
    }
       public void writeFile (String path, String message) {
           try {
            File archivo = new File(path); // Abre el archivo de la ruta especificada, si no existe, lo crea (según el path o ruta).
            FileWriter filewriter = new FileWriter(archivo, true); // Permite subreescribir (parámetro 'true') en el archivo especificado.
            
            try (PrintWriter printwriter = new PrintWriter(filewriter) ) { // Permite guardar el nuevo contenido en del archivo especificado.
                printwriter.println(message);
                printwriter.close();
            }
        }
        catch (FileNotFoundException err) { // Detecta un error en caso de no encontrar el archivo en el path indicado.
            System.err.println("Archivo no encontrado: " + err.getMessage());
        }
        catch (IOException err) { // Marca error en caso de no contar con los permisos para acceder al archivo indicado.
            System.err.println("Error de entrada y salida " + err.getMessage());
        }
    
       }
       public void jb_nuevo_action_performed() {
        viewc.jtf_nombre.setText("");
        viewc.jtf_email.setText("");
    }
 public void jb_guardar_action_performed() {
     String nombre = viewc.jtf_nombre.getText();
     String email = viewc.jtf_email.getText();
     String registro = nombre + "," + email;
     modelc.setMessage(registro);
     this.writeFile(modelc.getPath(), modelc.getMessage());
     
        
    }
public void initComponents() {
        viewc.setVisible(true);
    }
}