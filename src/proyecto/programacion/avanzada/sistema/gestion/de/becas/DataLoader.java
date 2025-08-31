/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *
 * @author Daniel Monsalve
 */

public class DataLoader {

    // ====================
    // ESTUDIANTES
    // ====================
    public static List<Student> cargarEstudiantes(String archivo, Maps maps) {
        List<Student> estudiantes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea = br.readLine(); // saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",", -1); // -1 = no perder columnas vacías

                Student e = new Student(
                        d[1],                                  // nombre
                        d[0],                                  // rut
                        d[6],                                  // direccion
                        d[2],                                  // correo
                        Integer.parseInt(d[3]),                // telefono
                        Float.parseFloat(d[4]),                // tramo socioeconomico
                        d[5]                                   // carrera
                        //d[7],                                // institucion
                        //Float.parseFloat(d[8])               // aprobacion estimada
                );
                estudiantes.add(e);
                maps.getMapStudent().put(d[0], e);
            }
        } catch (IOException e) {
            System.err.println("Error al leer estudiantes: " + e.getMessage());
        }
        return estudiantes;
    }

    // ====================
    // BECAS
    // ====================
    public static List<Beca> cargarBecas(String archivo, Maps maps) {
        List<Beca> becas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea = br.readLine(); // saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",", -1);

                Beca b = new Beca(
                        d[0],                                   // codigo
                        d[1],                                   // nombre
                        Float.parseFloat(d[2]),                 // porcentaje acceso
                        Integer.parseInt(d[4]),                 // monto
                        Integer.parseInt(d[5]),                  // cupos
                        d[3]                                   //requisitos
                );
                becas.add(b);
                maps.getMapBeca().put(d[0], b);
            }
        } catch (IOException e) {
            System.err.println("Error al leer becas: " + e.getMessage());
        }
        return becas;
    }

    // ====================
    // POSTULACIONES
    // ====================
    public static List<Postulation> cargarPostulaciones(String archivo, Maps maps) {
        List<Postulation> postulaciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea = br.readLine(); // saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",", -1);

                Postulation p = new Postulation(
                        d[0],  // codigo postulacion
                        d[1],  // codigo beca
                        d[2],  // rut estudiante
                        //d[3],  // estado
                        d[4]   // fecha
                );
                postulaciones.add(p);
                
                // ====================
                // Agregar postulación al estudiante correspondiente
                // ====================
                Student s = maps.getMapStudent().get(d[2]);
                if (s != null) {
                    s.addPostulation(p);
                } else {
                    System.out.println("No se encontró estudiante con RUT: " + d[2]);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer postulaciones: " + e.getMessage());
        }
        return postulaciones;
    }
}

