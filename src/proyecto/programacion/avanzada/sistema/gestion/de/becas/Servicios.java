/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.io.*;

/**
 * @archivo: Servicios.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa al menu del sistema
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 26-08-25
 */

public class Servicios {
    // Mostrar menú (estático, se puede llamar sin objeto)
    public static void mostrarMenuAdministrador() {
        System.out.println("=== Menú Administrador ===");
        System.out.println("1. Cargar Ajustes");
        System.out.println("2. Mostrar Estudiante");
        System.out.println("3. Registrar Alumno");
        System.out.println("4. Busqueda de postulación");
        System.out.println("5. Generar Reporte");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Leer opción del usuario y ejecutar acción
    public void leerArgumentos(Maps maps) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String text = r.readLine();
            if (text == null || text.trim().isEmpty()) {
                System.out.println("Entrada vacía. Debe ingresar un número válido.");
                return;
            }
            int opcion = Integer.parseInt(text.trim());
            switch (opcion) {
                case 1:
                    cargarAjustes(maps);
                    break;
                case 2:
                    mostrarEstudiante(maps);
                    break;
                case 3:
                    registrarAlumno(maps);
                    break;
                case 4:
                    busquedaPostulacion(maps);
                    break;
                case 5:
                    generarReporte();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número válido.");
        }
    }

    // Registrar alumno desde la terminal
    private void registrarAlumno(Maps maps) {
        limpiaPantalla();
        System.out.println(">> Ejecutando función: registrarAlumno() - Ingreso por terminal");
        
        Student estudiante = Student.createFromTerminal(maps);
        if (estudiante != null) {
            maps.getMapStudent().put(estudiante.getRut(), estudiante);
            System.out.println("Estudiante registrado exitosamente: " + estudiante.getName());
        } else {
            System.out.println("No se pudo registrar el estudiante.");
        }
    }

    

    // ===== OTRAS FUNCIONES =====
    private void cargarAjustes(Maps maps) {
        limpiaPantalla();
        System.out.println(">> Ejecutando función: cargarAjustes()");
        
        DataLoader.cargarEstudiantes("src/resources/estudiantes.csv", maps);
        DataLoader.cargarBecas("src/resources/becas.csv", maps);
        DataLoader.cargarPostulaciones("src/resources/postulaciones.csv", maps);
    
        System.out.println("Datos cargados correctamente");
    }

    private void mostrarEstudiante(Maps maps) {
        limpiaPantalla();
        System.out.println(">> Ejecutando función: mostrarEstudiante()");
        maps.ShowPostulationsEstudiante();
    }
    
    private void busquedaPostulacion(Maps maps){
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            limpiaPantalla();
            System.out.println("=== Búsqueda de Postulación ===");
            System.out.println("1. Buscar con ID de postulación y RUT del alumno");
            System.out.println("2. Buscar solo con RUT del alumno");
            System.out.print("Seleccione una opción: ");

            String opcionStr = r.readLine();
            if (opcionStr == null || opcionStr.trim().isEmpty()) {
                System.out.println("Entrada vacía. Debe ingresar un número válido.");
                return;
            }

            int opcion = Integer.parseInt(opcionStr.trim());
            switch (opcion) {
                case 1: {
                    limpiaPantalla();
                    System.out.print("Ingrese el RUT del alumno: ");
                    String rut = r.readLine().trim();
                    System.out.print("Ingrese el ID de la postulación: ");
                    String idPostulacion = r.readLine().trim();

                    // Llamada a la función de otra clase (ejemplo en Maps)
                    maps.buscarPostulacion(rut, idPostulacion);
                    break;
                }
                case 2: {
                    limpiaPantalla();
                    System.out.print("Ingrese el RUT del alumno: ");
                    String rut = r.readLine().trim();

                    // Llamada a otra función (ejemplo en Maps)
                    maps.buscarPostulacion(rut);
                    break;
                }
                default:
                    System.out.println("Opción no válida.");
            }

        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número válido.");
        }
    }

    private void generarReporte() {
        limpiaPantalla();
        System.out.println(">> Ejecutando función: generarReporte()");
    }

    public void limpiaPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
}