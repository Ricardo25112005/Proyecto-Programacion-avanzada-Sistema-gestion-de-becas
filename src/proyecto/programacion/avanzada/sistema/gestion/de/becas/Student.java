/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.util.LinkedList;
/**
 *
 * @author rpaez
 */
public class Student {
    private String name;
    private String rut;
    private String address;
    private String mail;
    private int phone;
    private float socioEconomicSection;
    private String carrer;
    private LinkedList<Postulation> listPostulation;
    
    public Student(){
        this.name = "";
        this.rut = "";
        this.address = "";
        this.mail = "";
        this.phone = 0;
        this.socioEconomicSection = 0.0f;
        this.carrer = "";
        this.listPostulation = new LinkedList<>();
    }
    
    public Student(String name, String rut, String address, String mail, int phone, float socioEconomicSection, String carrer){
        this.name = name;
        this.rut = rut;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.socioEconomicSection = socioEconomicSection;
        this.carrer = carrer;
        this.listPostulation = new LinkedList<>();;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getRut(){
        return rut;
    }
    
    public void setRut(String rut){
        this.rut = rut;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setAddress(String address){
        this.address = address;
    }
    
    public String getMail(){
        return mail;
    }
    
    public void setMail(String mail){
        this.mail = mail;
    }
    
    public int getPhone(){
        return phone;
    }
    
    public void setPhone(int phone){
        this.phone = phone;
    }
    
    public float getSocioEconomicSection(){
        return socioEconomicSection;
    }
    
    public void setSocioEconomicSection(float socioEconomicSection){
        this.socioEconomicSection = socioEconomicSection;
    }
    
    public String getCarrer(){
        return carrer;
    }
    
    public void setCarrer(String carrer){
        this.carrer = carrer;
    }
    
    public void showData(){
        System.out.println("=== Datos del Postulante ===");
        System.out.println("Nombre: " + name);
        System.out.println("RUT: " + rut);
        System.out.println("Dirección: " + address);
        System.out.println("Correo: " + mail);
        System.out.println("Teléfono: " + phone);
        System.out.println("Tramo Socioeconómico: " + socioEconomicSection);
        System.out.println("Carrera: " + carrer);
        System.out.println("\n=== Lista de Postulaciones ===");
        if (listPostulation.isEmpty()){
            System.out.println("No tiene postulaciones registradas.");
        }
        else{
            for(Postulation p : listPostulation){
                p.showPostulation();
            }
        }
    }
    
    public void addPostulation(Postulation p){
        listPostulation.add(p);
    }
    
    public void removePostulation(Postulation p){
        listPostulation.remove(p);
    }
}
