/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * @archivo: Student.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa a un Estudiante
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 2.0.0
 * @Fecha: 27-08-25
 */

public class Student {
    //Variables De Instancia
    private String name;
    private String rut;
    private String address;
    private String mail;
    private int phone;
    private float socioEconomicSection;
    private String carrer;
    private String institution;
    private float estimatedApproval;
    private LinkedList<Postulation> listPostulation;
    //Constructores
    public Student() {
        this.name = "";
        this.rut = "";
        this.address = "";
        this.mail = "";
        this.phone = 0;
        this.socioEconomicSection = 0.0f;
        this.carrer = "";
        this.institution = "";
        this.estimatedApproval = 0.0f;
        this.listPostulation = new LinkedList<>();
    }
    
    public Student(String name, String rut, String address, String mail, int phone, 
                   float socioEconomicSection, String carrer, String institution, 
                   float estimatedApproval) {
        this.name = name;
        this.rut = rut;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.socioEconomicSection = socioEconomicSection;
        this.carrer = carrer;
        this.institution = institution;
        this.estimatedApproval = estimatedApproval;
        this.listPostulation = new LinkedList<>();
    }
    //Metodos Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRut() { return rut; }
    public void setRut(String rut) { this.rut = rut; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }
    public int getPhone() { return phone; }
    public void setPhone(int phone) { this.phone = phone; }
    public float getSocioEconomicSection() { return socioEconomicSection; }
    public void setSocioEconomicSection(float socioEconomicSection) { this.socioEconomicSection = socioEconomicSection; }
    public String getCarrer() { return carrer; }
    public void setCarrer(String carrer) { this.carrer = carrer; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getInstitution() { return institution; }
    public void setEstimatedApproval(float estimatedApproval) { this.estimatedApproval = estimatedApproval; }
    public float getEstimatedApproval() { return estimatedApproval; }
    public LinkedList<Postulation> getListPostulation(){return listPostulation; }
    //Metodo que muestra los datos del estudiante y su lista de postulaciones
    public void showData() {
        String datosEstudiante = "Nombre: " + name +
                                         "\nRUT: " + rut +
                                         "\nDirección: " + address +
                                         "\nCorreo: " + mail +
                                         "\nTeléfono: " + phone +
                                         "\nTramo Socioeconómico: " + socioEconomicSection +
                                         "\nCarrera: " + carrer +
                                         "\nInstitución: " + institution +
                                         "\nAprobación Estimada: " + estimatedApproval;
                JOptionPane.showMessageDialog(null, datosEstudiante, "Datos del Estudiante", JOptionPane.INFORMATION_MESSAGE);
    }
    //Metodo para añadir una postulación si se tiene el objeto
    public void addPostulation(Postulation p) {
        listPostulation.add(p);
    }
    //Metodo que elimina una postulación de la lista
    public void removePostulation(Postulation p) {
        listPostulation.remove(p);
    }

}