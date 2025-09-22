/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @archivo: DataLoader.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa a una Carga de archivos (en este caso .csv). Aqui lo que hacemos es usar los datos
 * de los archivos para poblar las colecciones de mapas.
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 30-08-25
 */

public class DataLoader {

    // ====================
    // ESTUDIANTES
    // ====================
    public static void cargarEstudiantes(String archivo, Maps maps) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea = br.readLine(); // saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",", -1); // -1 = no perder columnas vacías

                //Aqui se representa cada columna del estudiantes.csv (por ejemplo d[1] significa tomar los valores de la columna)
                Student e = new Student(
                        d[1],                                  // nombre
                        d[0],                                  // rut
                        d[6],                                  // direccion
                        d[2],                                  // correo
                        Integer.parseInt(d[3]),                // telefono
                        Float.parseFloat(d[4]),                // tramo socioeconomico
                        d[5],                                   // carrera
                        d[7],                                // institucion
                        Float.parseFloat(d[8])               // aprobacion estimada
                );
                maps.getMapStudent().put(d[0], e);
            }
        } catch (IOException e) {
            System.err.println("Error al leer estudiantes: " + e.getMessage());
        }
    }
    public static void guardarEstudiantes(String archivo, Maps maps) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            bw.write("rut,nombre,correo,telefono,tramo_socioeconomico,carrera,direccion,institucion,aprobacion_estimada\n");
            for (Student s : maps.getMapStudent().values()) {
                // Escapar comas y convertir , a ; en dirección para formato CSV
                String direccion = s.getAddress().replace(",", ";");
                // Usar punto (.) como separador decimal para float
                String line = String.format(Locale.US, "%s,%s,%s,%d,%.1f,%s,%s,%s,%.1f\n",
                        s.getRut(), s.getName().replace(",", "\\,"), s.getMail(), s.getPhone(), s.getSocioEconomicSection(),
                        s.getCarrer().replace(",", "\\,"), direccion, s.getInstitution().replace(",", "\\,"), s.getEstimatedApproval());
                bw.write(line);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar estudiantes: " + e.getMessage());
        }
    }

    // ====================
    // BECAS
    // ====================
    public static void cargarBecas(String archivo, Maps maps) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

            String linea = br.readLine(); // saltar encabezado

            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",", -1); // -1 para que no se pierdan vacíos

                String tipo = d[0].trim();             // tipo: Arancel o Manutención
                String codigo = d[1].trim();           // codigo_beca
                String nombre = d[2].trim();           // nombre_beca
                String requisito = d[4].trim();        // requisito
                int cupos = Integer.parseInt(d[6].isEmpty() ? "0" : d[6]); // cupos

                Beca b = null;

                if (tipo.equalsIgnoreCase("Arancel")) {
                    // columna d[3] → porcentaje_descuento
                    float porcentaje = Float.parseFloat(d[3].isEmpty() ? "0" : d[3]);
                    b = new BecaArancel(codigo, nombre, cupos, requisito, porcentaje);
                } 
                else if (tipo.equalsIgnoreCase("Manutención")) {
                    // columna d[5] → monto_Manutencion
                    int monto = Integer.parseInt(d[5].isEmpty() ? "0" : d[5]);
                    // columna d[7] → periodo
                    int periodo = Integer.parseInt(d[7].isEmpty() ? "0" : d[7]);
                    b = new BecaManutencion(codigo, nombre, cupos, requisito, monto, periodo);
                }

                if (b != null) {
                    maps.getMapBeca().put(codigo, b);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer becas: " + e.getMessage());
        }
    }
    // Método para guardar becas en CSV
    public static void guardarBecas(String archivo, Maps maps) {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {

            // Escribir encabezado
            bw.write("tipo,codigo_beca,nombre_beca,porcentaje_descuento,requisito,monto_Manutencion,cupos,Periodo\n");

            for (Beca b : maps.getMapBeca().values()) {
                String tipo = "";
                String porcentaje = "";
                String monto = "";
                String periodo = "";

                if (b instanceof BecaArancel) {
                    tipo = "Arancel";
                    porcentaje = String.format(Locale.US, "%.1f", ((BecaArancel) b).getPorcentajeDescuento());
                } else if (b instanceof BecaManutencion) {
                    tipo = "Manutención";
                    monto = String.valueOf(((BecaManutencion) b).getMonto());
                    periodo = String.valueOf(((BecaManutencion) b).getPeriodo());
                }

                String linea = String.join(",",
                        tipo,
                        b.getCodigo(),
                        b.getNombre(),
                        porcentaje,
                        b.getRequisitos(),
                        monto,
                        String.valueOf(b.getCupos()),
                        periodo
                ) + "\n";

                bw.write(linea);
            }

            bw.flush(); // asegurar que se escriba todo
            System.out.println("Becas guardadas correctamente en " + archivo);

        } catch (IOException e) {
            System.err.println("Error al guardar becas: " + e.getMessage());
        }
    }

    // ====================
    // POSTULACIONES
    // ====================
    public static void cargarPostulaciones(String archivo, Maps maps) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea = br.readLine(); // saltar encabezado
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",", -1);
                //Aqui se representa cada columna del postulaciones.csv (por ejemplo d[1] significa tomar los valores de la columna)
                Postulation p = new Postulation(
                        d[0],  // codigo postulacion
                        d[2],  // rut estudiante
                        d[1],  // codigo beca
                        //d[3],  // estado
                        d[4]   // fecha
                );
                
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
    }
    
    public static void guardarPostulaciones(String archivo, Maps maps) {
        Set<String> uniquePostulaciones = new HashSet<>();
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            bw.write("codigo_postulacion,codigo_beca,rut_estudiante,estado_postulacion,fecha_postulacion\n");
            for (Student s : maps.getMapStudent().values()) {
                for (Postulation p : s.getListPostulation()) {
                    String key = p.getIdPostulation();
                    if (!uniquePostulaciones.contains(key)) {
                        String line = String.format("%s,%s,%s,%s,%s\n",
                                p.getIdPostulation(), p.getIdBeca(), p.getIdStudent(), p.getState(), p.getDatePostulation());
                        bw.write(line);
                        uniquePostulaciones.add(key);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al guardar postulaciones: " + e.getMessage());
        }
    }
}