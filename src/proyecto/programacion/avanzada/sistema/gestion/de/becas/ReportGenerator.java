/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package proyecto.programacion.avanzada.sistema.gestion.de.becas;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.nio.charset.StandardCharsets;
import javax.swing.JOptionPane;

/**
 * @archivo: ReportGenerator.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase para generar reportes en formato TXT del sistema
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 2.0.0
 * @Fecha: 27-11-25
 */

public class ReportGenerator {
    private final Maps maps;
    private final DateTimeFormatter dateFormatter;
    
    public ReportGenerator(Maps maps) {
        this.maps = maps;
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    
    /**
     * Genera un reporte completo del sistema en archivo TXT
     * @param nombreArchivo Ruta y nombre del archivo donde se guardará el reporte
     */
    public void generarReporteCompleto(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, StandardCharsets.UTF_8))) {
            // Encabezado del reporte
            escribirEncabezado(writer);
            
            // Sección de estadísticas generales
            escribirEstadisticasGenerales(writer);
            
            // Sección de estudiantes
            escribirReporteEstudiantes(writer);
            
            // Sección de becas
            escribirReporteBecas(writer);
            
            // Sección de postulaciones
            escribirReportePostulaciones(writer);
            
            // Sección de postulaciones por estado
            escribirPostulacionesPorEstado(writer);
            
            // Pie del reporte
            escribirPie(writer);
            
            System.out.println("Reporte generado exitosamente: " + nombreArchivo);
            
        } catch (IOException e) {
            System.err.println("Error al generar reporte: " + e.getMessage());
        }
    }
    
    /**
     * Genera un reporte específico de postulaciones
     * @param nombreArchivo Ruta y nombre del archivo donde se guardará el reporte
     */
    public void generarReportePostulaciones(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, StandardCharsets.UTF_8))) {
            escribirEncabezado(writer);
            writer.println("=== REPORTE DETALLADO DE POSTULACIONES ===");
            writer.println();
            
            escribirReportePostulaciones(writer);
            escribirPostulacionesPorEstado(writer);
            escribirPie(writer);
            
        } catch (IOException e) {
            System.err.println("Error al generar reporte de postulaciones: " + e.getMessage());
        }
    }
    
    /**
     * Genera un reporte específico de becas y cupos
     * @param nombreArchivo Ruta y nombre del archivo donde se guardará el reporte
     */
    public void generarReporteBecas(String nombreArchivo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, StandardCharsets.UTF_8))) {
            escribirEncabezado(writer);
            writer.println("=== REPORTE DE BECAS Y CUPOS ===");
            writer.println();
            
            escribirReporteBecas(writer);
            escribirEstadisticasBecas(writer);
            escribirPie(writer);
            
        } catch (IOException e) {
            System.err.println("Error al generar reporte de becas: " + e.getMessage());
        }
    }
    
    private void escribirEncabezado(PrintWriter writer) {
        writer.println("=".repeat(80));
        writer.println("SISTEMA DE GESTIÓN DE BECAS - REPORTE DEL SISTEMA");
        writer.println("Generado: " + LocalDateTime.now().format(dateFormatter));
        writer.println("=".repeat(80));
        writer.println();
    }
    //Metodo que escribe estadisticas generales del programa
    private void escribirEstadisticasGenerales(PrintWriter writer) {
        writer.println("ESTADÍSTICAS GENERALES DEL SISTEMA");
        writer.println("-".repeat(50));
        
        int totalEstudiantes = maps.getMapStudent().size();
        int totalBecas = maps.getMapBeca().size();
        int totalPostulaciones = calcularTotalPostulaciones();
        
        writer.printf("Total de Estudiantes registrados: %d%n", totalEstudiantes);
        writer.printf("Total de Becas disponibles: %d%n", totalBecas);
        writer.printf("Total de Postulaciones realizadas: %d%n", totalPostulaciones);
        writer.println();
    }
    //Escribe reporte con todos los estudiantes 
    private void escribirReporteEstudiantes(PrintWriter writer) {
        writer.println("LISTADO DE ESTUDIANTES REGISTRADOS");
        writer.println("-".repeat(50));
        
        if (maps.getMapStudent().isEmpty()) {
            writer.println("No hay estudiantes registrados en el sistema.");
            writer.println();
            return;
        }
        
        int contador = 1;
        for (Student estudiante : maps.getMapStudent().values()) {
            writer.printf("%d. RUT: %s%n", contador, estudiante.getRut());
            writer.printf("   Nombre: %s%n", estudiante.getName());
            writer.printf("   Carrera: %s%n", estudiante.getCarrer());
            writer.printf("   Institución: %s%n", estudiante.getInstitution());
            writer.printf("   Postulaciones realizadas: %d%n", estudiante.getListPostulation().size());
            writer.printf("   Aprobación estimada: %.1f%%%n", estudiante.getEstimatedApproval());
            writer.printf("   Tramo socioeconómico: %.1f%n", estudiante.getSocioEconomicSection());
            writer.println();
            contador++;
        }
    }
    //Escribe reporte con todos las Becas del sistema
    private void escribirReporteBecas(PrintWriter writer) {
        writer.println("LISTADO DE BECAS DISPONIBLES");
        writer.println("-".repeat(50));
        
        if (maps.getMapBeca().isEmpty()) {
            writer.println("No hay becas registradas en el sistema.");
            writer.println();
            return;
        }
        
        int contador = 1;
        for (Beca beca : maps.getMapBeca().values()) {
            writer.printf("%d. Código: %s%n", contador, beca.getCodigo());
            writer.printf("   Nombre: %s%n", beca.getNombre());
            writer.printf("   Tipo: %s%n", beca.getTipo());
            writer.printf("   Cupos disponibles: %d%n", beca.getCupos());
            writer.printf("   Postulaciones recibidas: %d%n", beca.getPostulaciones().size());
            
            if (beca.getTipo().equals("Arancel")) {
                BecaArancel becaArancel = (BecaArancel) beca;
                writer.printf("   Porcentaje de descuento: %.1f%%%n", becaArancel.getPorcentajeDescuento());
            } else if (beca.getTipo().equals("Manutención")) {
                BecaManutencion becaManutencion = (BecaManutencion) beca;
                writer.printf("   Monto: $%,d%n", becaManutencion.getMonto());
                writer.printf("   Período: %d meses%n", becaManutencion.getPeriodo());
            }
            
            writer.printf("   Requisitos: %s%n", beca.getRequisitos());
            writer.println();
            contador++;
        }
    }
    //Escribe todas las postulaciones
    private void escribirReportePostulaciones(PrintWriter writer) {
        writer.println("DETALLE DE POSTULACIONES");
        writer.println("-".repeat(50));
        
        int totalPostulaciones = calcularTotalPostulaciones();
        if (totalPostulaciones == 0) {
            writer.println("No hay postulaciones registradas en el sistema.");
            writer.println();
            return;
        }
        
        int contador = 1;
        for (Student estudiante : maps.getMapStudent().values()) {
            for (Postulation postulacion : estudiante.getListPostulation()) {
                Beca beca = maps.getMapBeca().get(postulacion.getIdBeca());
                String nombreBeca = (beca != null) ? beca.getNombre() : "Beca no encontrada";
                
                writer.printf("%d. ID Postulación: %s%n", contador, postulacion.getIdPostulation());
                writer.printf("   Estudiante: %s (%s)%n", estudiante.getName(), estudiante.getRut());
                writer.printf("   Beca: %s (%s)%n", nombreBeca, postulacion.getIdBeca());
                writer.printf("   Estado: %s%n", postulacion.getState());
                writer.printf("   Fecha: %s%n", postulacion.getDatePostulation());
                writer.println();
                contador++;
            }
        }
    }
    //Escribe todas las postulaciones, pero agrupadas por estado
    private void escribirPostulacionesPorEstado(PrintWriter writer) {
        writer.println("RESUMEN DE POSTULACIONES POR ESTADO");
        writer.println("-".repeat(50));
        
        Map<String, Integer> postulacionesPorEstado = new HashMap<>();
        postulacionesPorEstado.put("En espera", 0);
        postulacionesPorEstado.put("Aprobada", 0);
        postulacionesPorEstado.put("Rechazada", 0);
        
        for (Student estudiante : maps.getMapStudent().values()) {
            for (Postulation postulacion : estudiante.getListPostulation()) {
                String estado = postulacion.getState();
                postulacionesPorEstado.put(estado, postulacionesPorEstado.getOrDefault(estado, 0) + 1);
            }
        }
        
        for (Map.Entry<String, Integer> entry : postulacionesPorEstado.entrySet()) {
            writer.printf("%s: %d postulaciones%n", entry.getKey(), entry.getValue());
        }
        writer.println();
    }
    //Metodo que escribe la estadisticas basicas de las becas, como la cantidad de estas, total de vupos
    private void escribirEstadisticasBecas(PrintWriter writer) {
        writer.println("ESTADÍSTICAS DE BECAS");
        writer.println("-".repeat(50));
        
        int becasArancel = 0;
        int becasManutencion = 0;
        int totalCupos = 0;
        int totalPostulacionesBecas = 0;
        
        for (Beca beca : maps.getMapBeca().values()) {
            if (beca.getTipo().equals("Arancel")) {
                becasArancel++;
            } else if (beca.getTipo().equals("Manutención")) {
                becasManutencion++;
            }
            totalCupos += beca.getCupos();
            totalPostulacionesBecas += beca.getPostulaciones().size();
        }
        
        writer.printf("Becas de Arancel: %d%n", becasArancel);
        writer.printf("Becas de Manutención: %d%n", becasManutencion);
        writer.printf("Total de cupos disponibles: %d%n", totalCupos);
        writer.printf("Total de postulaciones a becas: %d%n", totalPostulacionesBecas);
        
        if (totalCupos > 0) {
            double porcentajeOcupacion = (double) totalPostulacionesBecas / totalCupos * 100;
            writer.printf("Porcentaje de ocupación: %.1f%%%n", porcentajeOcupacion);
        }
        writer.println();
    }
    
    private void escribirPie(PrintWriter writer) {
        writer.println("=".repeat(80));
        writer.println("FIN DEL REPORTE");
        writer.println("Sistema desarrollado por: Daniel Monsalve, Ricardo Paez, Vicente Novoa");
        writer.println("=".repeat(80));
    }
    //Metodo que calcula la cantidad total de postulaciones
    private int calcularTotalPostulaciones() {
        int total = 0;
        for (Student estudiante : maps.getMapStudent().values()) {
            total += estudiante.getListPostulation().size();
        }
        return total;
    }
    
    /**
     * Método para generar reporte desde el menú principal
     */
    public static void generarReporteDesdeMenu(Maps maps) {
        ReportGenerator generador = new ReportGenerator(maps);
        
        String[] opciones = {
            "Reporte Completo del Sistema",
            "Reporte de Postulaciones", 
            "Reporte de Becas y Cupos",
            "Cancelar"
        };
        
        String seleccion = (String) JOptionPane.showInputDialog(
            null,
            "Seleccione el tipo de reporte a generar:",
            "Generar Reporte TXT",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if (seleccion == null || seleccion.equals("Cancelar")) {
            return;
        }
        
        // Sugerir nombre de archivo con fecha
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm"));
        String nombreArchivo = "";
        
        switch (seleccion) {
            case "Reporte Completo del Sistema":
                nombreArchivo = "src/resources/reporte_completo_" + fecha + ".txt";
                generador.generarReporteCompleto(nombreArchivo);
                break;
            case "Reporte de Postulaciones":
                nombreArchivo = "src/resources/reporte_postulaciones_" + fecha + ".txt";
                generador.generarReportePostulaciones(nombreArchivo);
                break;
            case "Reporte de Becas y Cupos":
                nombreArchivo = "src/resources/reporte_becas_" + fecha + ".txt";
                generador.generarReporteBecas(nombreArchivo);
                break;
        }
        
        JOptionPane.showMessageDialog(null, 
            "Reporte generado exitosamente:\n" + nombreArchivo, 
            "Reporte Completado", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}
