/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

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
}
