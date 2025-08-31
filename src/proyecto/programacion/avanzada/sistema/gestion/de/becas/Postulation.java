/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 *
 * @author rpaez
 */
public class Postulation {
    private String idPostulation;
    private String idStudent;
    private String idBeca;
    private String state;
    private String datePostulation;
    
    public Postulation(){
        this.idPostulation = "";
        this.idStudent = "";
        this.idBeca = "";
        this.state = "";
        this.datePostulation = "";
    }
    public Postulation(String idPostulation, String idStudent, String idBeca, String datePostulation){
        this.idPostulation = idPostulation;
        this.idStudent = idStudent;
        this.idBeca = idBeca;
        this.state = "En espera";
        this.datePostulation = datePostulation;
    }
    
    public String getIdPostulation() { return idPostulation; }
    public String getIdStudent() { return idStudent; }
    public String getIdBeca() { return idBeca; }
    public String getState() { return state; }
    public String getDatePostulation() {return datePostulation;}
    public void setIdPostulation(String idPostulation) {this.idPostulation = idPostulation;}
    public void setIdStudent(String idStudent) {this.idStudent = idStudent;}
    public void setIdBeca(String idBeca) {this.idBeca = idBeca;}
    public void setState(String state) {this.state = state;}
    public void setDatePostulation(String datePostulation) {this.datePostulation = datePostulation;}
    
    public void showPostulation() {
    System.out.println("ID Postulación: " + idPostulation);
    System.out.println("ID Estudiante: " + idStudent);
    System.out.println("ID Beca: " + idBeca);
    System.out.println("Estado: " + state);
    System.out.println("Fecha de Postulación: " + datePostulation);
    }
}
