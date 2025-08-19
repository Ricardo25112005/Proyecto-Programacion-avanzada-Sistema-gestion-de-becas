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
    private String correo;
    private int telefono;
    private float tramoSocioEconomico;
    private String carrera;
    
    public Persona(){
        this.nombre = "";
        this.rut = "";
        this.direccion = "";
        this.correo = "";
        this.telefono = 0;
        this.tramoSocioEconomico = 0.0f;
        this.carrera = "";
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
    
    public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public int getTelefono(){
        return telefono;
    }
    
    public void setTelefono(int telefono){
        this.telefono = telefono;
    }
    
    public float getTramoSocio(){
        return tramoSocioEconomico;
    }
    
    public void setTramoSocioEconomico(float tramoSocioEconomico){
        this.tramoSocioEconomico = tramoSocioEconomico;
    }
    
    public String getCarrera(){
        return carrera;
    }
    
    public void setCarrera(String carrera){
        this.carrera = carrera;
    }
}
