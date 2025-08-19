/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 *
 * @author rpaez
 */
public class Persona {
    private String nombre;
    private String rut;
    private String direccion;
    
    public Persona(){
        this.nombre = "";
        this.rut = "";
        this.direccion = "";
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getRut(){
        return rut;
    }
    
    public void setRut(String rut){
        this.rut = rut;
    }
    
    public String getDireccion(){
        return direccion;
    }
    
    public void setDireccion(String direccion){
        this.direccion = direccion;
    }
}
