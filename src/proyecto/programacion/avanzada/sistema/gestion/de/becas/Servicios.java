/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.io.*;
/**
 *
 * @author vicen
 */
 public class Servicios {

    // Mostrar menú (estático, se puede llamar sin objeto)
    public static void mostrarMenuAdministrador() {
        System.out.println("=== Menú Administrador ===");    
        System.out.println("1. Cargar Ajustes");
        System.out.println("2. Mostrar Becas");
        System.out.println("3. Registrar Alumno");
        System.out.println("4. Generar Reporte");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    // Leer opción del usuario y ejecutar acción
    public void leerArgumentos() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String text = r.readLine();
            int opcion = Integer.parseInt(text);

            switch (opcion) {
                case 1:
                    cargarAjustes();
                    break;
                case 2:
                    mostrarBecas();
                    break;
                case 3:
                    registrarAlumno();
                    break;
                case 4:
                    generarReporte();
                    break;
                case 5:
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

    // ===== FUNCIONES DE EJEMPLO =====
    private void cargarAjustes() {
        limpiaPantalla();
        System.out.println(">> Ejecutando función: cargarAjustes()");
    }

    private void mostrarBecas() {
        limpiaPantalla();
        System.out.println(">> Ejecutando función: mostrarBecas()");
    }

    private void registrarAlumno() {
        limpiaPantalla();
        System.out.println(">> Ejecutando función: registrarAlumno()");
    }

    private void generarReporte() {
        limpiaPantalla();
        System.out.println(">> Ejecutando función: generarReporte()");
    }
    public void limpiaPantalla()
    {
        for(int i = 0 ; i < 50 ;i++){System.out.println("");}
    }
}