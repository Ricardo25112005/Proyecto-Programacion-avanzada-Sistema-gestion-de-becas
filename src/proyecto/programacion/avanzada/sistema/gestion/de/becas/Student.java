/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.io.*;
import java.util.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Ricardo Paez 
 * @author Vicente Novoa
 */
public class Student {
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
    
    public void showData() {
        System.out.println("=== Datos del Postulante ===");
        System.out.println("Nombre: " + name);
        System.out.println("RUT: " + rut);
        System.out.println("Dirección: " + address);
        System.out.println("Correo: " + mail);
        System.out.println("Teléfono: " + phone);
        System.out.println("Tramo Socioeconómico: " + socioEconomicSection);
        System.out.println("Carrera: " + carrer);
        System.out.println("\n=== Lista de Postulaciones ===");
        if (listPostulation.isEmpty()) {
            System.out.println("No tiene postulaciones registradas.");
        } else {
            for (Postulation p : listPostulation) {
                p.showPostulation();
            }
        }
    }
    
    public void addPostulation(Postulation p) {
        listPostulation.add(p);
    }
    
    public void addPostulation(String idPostulation, String idStudent, String idBeca, String datePostulation) {
        Postulation p = new Postulation(idPostulation, this.rut, idBeca, datePostulation);
        listPostulation.add(p);
    }
    
    public void removePostulation(Postulation p) {
        listPostulation.remove(p);
    }

    // Método para crear un estudiante desde la terminal
    public static Student createFromTerminal(Maps maps) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Ingrese RUT del estudiante (formato XX.XXX.XXX-Y): ");
            String rut = r.readLine().trim();
            if (rut.isEmpty() || !rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9kK]")) {
                System.out.println("Error: El RUT debe tener el formato XX.XXX.XXX-Y.");
                return null;
            }
            if (maps.getMapStudent().containsKey(rut)) {
                System.out.println("Error: Ya existe un estudiante con el RUT " + rut);
                return null;
            }

            System.out.print("Ingrese nombre del estudiante: ");
            String nombre = r.readLine().trim();
            if (nombre.isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
                return null;
            }

            System.out.print("Ingrese correo del estudiante: ");
            String correo = r.readLine().trim();
            if (!correo.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                System.out.println("Error: Formato de correo inválido.");
                return null;
            }

            System.out.print("Ingrese teléfono del estudiante: ");
            String telefonoStr = r.readLine().trim();
            int telefono;
            try {
                telefono = Integer.parseInt(telefonoStr);
                if (telefono <= 0) {
                    System.out.println("Error: El teléfono debe ser un número positivo.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El teléfono debe ser un número válido.");
                return null;
            }

            System.out.print("Ingrese tramo socioeconómico (0.0 a 100.0): ");
            String tramoStr = r.readLine().trim();
            float tramoSocioeconomico;
            try {
                tramoSocioeconomico = Float.parseFloat(tramoStr);
                if (tramoSocioeconomico < 0 || tramoSocioeconomico > 100) {
                    System.out.println("Error: El tramo socioeconómico debe estar entre 0.0 y 100.0.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: El tramo socioeconómico debe ser un número válido.");
                return null;
            }

            System.out.print("Ingrese carrera del estudiante: ");
            String carrera = r.readLine().trim();
            if (carrera.isEmpty()) {
                System.out.println("Error: La carrera no puede estar vacía.");
                return null;
            }

            System.out.print("Ingrese dirección del estudiante (formato Región: X; Ciudad: Y; Calle: Z): ");
            String direccion = r.readLine().trim();

            System.out.print("Ingrese institución del estudiante: ");
            String institucion = r.readLine().trim();
            if (institucion.isEmpty()) {
                System.out.println("Error: La institución no puede estar vacía.");
                return null;
            }

            System.out.print("Ingrese aprobación estimada (0.0 a 100.0): ");
            String aprobacionStr = r.readLine().trim();
            float aprobacionEstimada;
            try {
                aprobacionEstimada = Float.parseFloat(aprobacionStr);
                if (aprobacionEstimada < 0 || aprobacionEstimada > 100) {
                    System.out.println("Error: La aprobación estimada debe estar entre 0.0 y 100.0.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: La aprobación estimada debe ser un número válido.");
                return null;
            }

            return new Student(nombre, rut, direccion, correo, telefono, tramoSocioeconomico, carrera, institucion, aprobacionEstimada);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
            return null;
        }
    }

}