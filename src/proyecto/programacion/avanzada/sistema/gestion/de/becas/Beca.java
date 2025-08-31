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
public class Beca {
    private String codeBeca;
    private String nomBeca;
    private float porcAprob;
    private int monto;
    private int cupos;
    private String requisitos;
    private List<Postulation> postulaciones;
    
    public Beca(String codeBeca, String nomBeca, float porcAprob, int monto, int cupos, String requisitos){
        this.codeBeca = codeBeca;
        this.nomBeca = nomBeca;
        this.porcAprob = porcAprob;
        this.monto = monto;
        this.cupos = cupos;
        this.requisitos = requisitos;
        this.postulaciones = new ArrayList<>();
    }
    
    public void agregarPostulacion(Postulation postulacion){
        if (postulaciones.size() < cupos){
            postulaciones.add(postulacion);
        } else {
            System.out.println("No hay cupos disponibles para " + nomBeca);
        }
    }
    
    public List<Postulation> getPostulaciones(){
        return postulaciones;
    }
    
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
    
    public String getCodigo() { return codeBeca; }
    public String getNombre() { return nomBeca; }
    public int getCupos() { return cupos; }
    public double getMonto() { return monto; }
    public String requisitos() { return requisitos; }
}
