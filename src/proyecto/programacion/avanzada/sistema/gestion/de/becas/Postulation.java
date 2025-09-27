/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 * @archivo: Postulation.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa una Postulacion de un Estudiante a una Beca
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 27-08-25
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Postulation {
    private String idPostulation; // Identificador único de la postulación
    private String idStudent;     // RUT del estudiante que postula
    private String idBeca;       // Identificador de la beca a la que se postula
    private String state;        // Estado de la postulación (e.g., "En espera", "Aprobada", "Rechazada")
    private String datePostulation; // Fecha de la postulación
    
    //Constructor que inicializa los atributos con valores vacíos.
    public Postulation(){
        this.idPostulation = "";
        this.idStudent = "";
        this.idBeca = "";
        this.state = "";
        this.datePostulation = "";
    }
    //Constructor que inicializa una postulación con los datos proporcionados, estableciendo el estado por defecto como "En espera"
    public Postulation(String idPostulation, String idStudent, String idBeca, String datePostulation, String state){
        this.idPostulation = idPostulation;
        this.idStudent = idStudent;
        this.idBeca = idBeca;
        this.state = state;
        this.datePostulation = datePostulation;
    }
    
    public String getIdPostulation() { return idPostulation; } // Obtiene el identificador de la postulación.
    public String getIdStudent() { return idStudent; } // Retorna El ID de la postulación.
    public String getIdBeca() { return idBeca; } //Obtiene el RUT del estudiante
    public String getState() { return state; }// Retorna el RUT del estudiante
    public String getDatePostulation() {return datePostulation;} //Obtiene el identificador de la beca.
    public void setIdPostulation(String idPostulation) {this.idPostulation = idPostulation;} // retorna el identificador de la beca
    public void setIdStudent(String idStudent) {this.idStudent = idStudent;}// Obtiene el estado de la postulación
    public void setIdBeca(String idBeca) {this.idBeca = idBeca;} // Retorna el estado de la postulación.
    public void setState(String state) {this.state = state;} //Obtiene la fecha de la postulación.
    public void setDatePostulation(String datePostulation) {this.datePostulation = datePostulation;} // Retorna La fecha de postulación.
    
    
    /**
     * Muestra los detalles de la postulación en la consola, incluyendo ID, RUT del estudiante, 
     * ID de la beca, estado y fecha.
     */
    public void showPostulation() {
        String mensaje = "ID Postulación: " + idPostulation +
                         "\nID Estudiante: " + idStudent +
                         "\nID Beca: " + idBeca +
                         "\nEstado: " + state +
                         "\nFecha de Postulación: " + datePostulation;
        JOptionPane.showMessageDialog(null, mensaje, "Detalles de la Postulación", JOptionPane.INFORMATION_MESSAGE);
    }
}
