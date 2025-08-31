/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.util.ArrayList;
import java.util.List;
/**
 * @archivo: Beca.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa a una Beca
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 27-08-25
 */

//Construccion de la clase Beca

public class Beca {
    private String codeBeca;                        //Codigo de la beca: Es un String que identifica a la beca y le da unicidad
    private String nomBeca;                         // Nombre de la beca: Un String que contiene el nombre de la beca
    private float porcAprob;                        // Un Flotante que contiene el porcentaje de aprobacion de la beca
    private int monto;                              // Un entero que contiene el monto en pesos de la beca
    private int cupos;                              // Un entero que contiene los cupos disponibles de la beca
    private String requisitos;                      // Requisitos de la beca
    private List<Postulation> postulaciones;        // Contiene una lista de postulaciones mostrando todos los estudiantes que quieren esta beca
    
    
    //Constructor iniciando las variables
    public Beca(String codeBeca, String nomBeca, float porcAprob, int monto, int cupos, String requisitos){
        this.codeBeca = codeBeca;
        this.nomBeca = nomBeca;
        this.porcAprob = porcAprob;
        this.monto = monto;
        this.cupos = cupos;
        this.requisitos = requisitos;
        this.postulaciones = new ArrayList<>();
    }
    
    //Codificacion de metodos:
    
    //Metodo para agrefar una postulacion a la beca
    public void agregarPostulacion(Postulation postulacion){
        if (postulaciones.size() < cupos){
            postulaciones.add(postulacion);
        } else {
            System.out.println("No hay cupos disponibles para " + nomBeca);
        }
    }
    
    //Obtener las postulaciones que se van hecho a la beca
    public List<Postulation> getPostulaciones(){
        return postulaciones;
    }
    
    //Mostrar una postulacion en particular
    public void mostrarPostulaciones(){
        if (postulaciones.isEmpty()){
            System.out.println("No hay postulaciones para " + nomBeca);
        } else {
            System.out.println("Postulaciones para " + nomBeca + ":");
            for (Postulation p : postulaciones) {
                System.out.println(" - " + p);
            }
        }
    }
    
    
    //Getters varios para cada atributo
    public String getCodigo() { return codeBeca; }
    public String getNombre() { return nomBeca; }
    public int getCupos() { return cupos; }
    public double getMonto() { return monto; }
    public String requisitos() { return requisitos; }
}
