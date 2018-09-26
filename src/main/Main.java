/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import controllers.ControllerC;
import models.ModelC;
import views.ViewC;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ModelC modelc = new ModelC();
        ViewC viewc = new ViewC();
        ControllerC controllerc = new ControllerC(modelc, viewc); // integra componentes 
        
    }
    
}