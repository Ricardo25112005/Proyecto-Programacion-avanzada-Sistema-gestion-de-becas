/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @archivo: Maps.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa a una coleccion Maps para hacer eficiente el acceso a los datos.
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 28-08-25
 */

public class Maps {
    private Map<String,Student> mapStudent;
    private Map<String,Beca> mapBeca;
    
    public Maps(){
        this.mapStudent = new HashMap<>();
        this.mapBeca = new HashMap<>();
    }
    
    public Map<String, Student> getMapStudent() {
        return mapStudent;
    }

    public Map<String, Beca> getMapBeca() {
        return mapBeca;
    }

    public void ShowPostulationsEstudiante(){
        try {
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Ingrese el RUT del estudiante: ");
            String rut = r.readLine();  // Lee lo que escribe el usuario
            
            Student s = mapStudent.get(rut);
            
            if (s != null) {
                s.showData();  // imprime los datos + postulaciones
            } else {
                System.out.println("No se encontró un estudiante con el RUT: " + rut);
            }
        } catch (IOException e) {
            System.out.println("Error al leer entrada: " + e.getMessage());
        }
    }
    
    public void buscarPostulacion(String rut, String idPostulation){
        Student estudiante = mapStudent.get(rut);
        if (estudiante != null) {
            // Buscar en su lista de postulaciones
            for (Postulation p : estudiante.getListPostulation()) {
                if (p.getIdPostulation().equals(idPostulation)) {
                    System.out.println("Postulación encontrada: ");
                    p.showPostulation();
                    return;
                }
            }
            System.out.println("No se encontró postulación con ese ID.");
        } else {
            System.out.println("No existe alumno con ese RUT.");
        }
    }
    
    public void buscarPostulacion(String rut) {
        Student estudiante = mapStudent.get(rut);
        if (estudiante != null) {
            if (estudiante.getListPostulation().isEmpty()) {
                System.out.println("El alumno no tiene postulaciones registradas.");
            } else {
                System.out.println("Postulaciones del alumno " + estudiante.getName() + ":");
                for (Postulation p : estudiante.getListPostulation()) {
                    p.showPostulation(); // asumiendo que Postulation tiene este método
                }
            }
        } else {
            System.out.println("No existe alumno con ese RUT.");
        }
    }
}
