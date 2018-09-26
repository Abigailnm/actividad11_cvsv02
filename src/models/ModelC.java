/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


public class ModelC {
    
    private String path = "C:\\archivos\\base.csv"; // Variable que almacena la ruta del archivo.

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    
    private String message = ""; // Variable que almacena los datos de los campos de texto de "ViewArchCSV".

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    
}
