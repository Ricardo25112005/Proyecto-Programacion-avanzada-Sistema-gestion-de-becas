/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 * @archivo: Maps.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa a una coleccion Maps para hacer eficiente el acceso a los datos.
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 28-08-25
 */

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Maps {
    private Map<String, Student> mapStudent;
    private Map<String, Beca> mapBeca;
    private int cont;

    public Maps() {
        this.mapStudent = new HashMap<>();
        this.mapBeca = new HashMap<>();
        this.cont = 10;
    }

    public Map<String, Student> getMapStudent() {
        return mapStudent;
    }

    public Map<String, Beca> getMapBeca() {
        return mapBeca;
    }

    // Mostrar datos del estudiante y sus postulaciones en ventana
    public void ShowPostulationsEstudiante() {
        try {
            String rut = JOptionPane.showInputDialog("Ingrese el RUT del estudiante:");
            if (rut == null || rut.trim().isEmpty()) return;

            Student s = mapStudent.get(rut.trim());
            if (s != null) {
                // Mostrar datos del estudiante en un JOptionPane
                s.showData();
                // Mostrar cada postulación usando showPostulationGUI()
                if (s.getListPostulation().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El estudiante no tiene postulaciones registradas.");
                } else {
                    for (Postulation p : s.getListPostulation()) {
                        p.showPostulation(); // Llama a la nueva función de Swing
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró un estudiante con el RUT: " + rut);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos: " + e.getMessage());
        }
    }
    //Funcion que muestra solo la postulacion con ese id
    public void buscarPostulacion(String rut, String idPostulation){
        if (rut == null || rut.trim().isEmpty() || idPostulation == null || idPostulation.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "RUT o ID de postulación inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student estudiante = mapStudent.get(rut.trim());
        if (estudiante != null) {
            boolean encontrada = false;
            for (Postulation p : estudiante.getListPostulation()) {
                if (p.getIdPostulation().equals(idPostulation.trim())) {
                    p.showPostulation(); // mostrar en ventana
                    encontrada = true;
                    break;
                }
            }
            if (!encontrada) {
                JOptionPane.showMessageDialog(null, "No se encontró postulación con ese ID.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe alumno con ese RUT.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

// Buscar todas las postulaciones de un estudiante por RUT
    public void buscarPostulacion(String rut) {
        if (rut == null || rut.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "RUT inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Student estudiante = mapStudent.get(rut.trim());
        if (estudiante != null) {
            if (estudiante.getListPostulation().isEmpty()) {
                JOptionPane.showMessageDialog(null, "El alumno no tiene postulaciones registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                for (Postulation p : estudiante.getListPostulation()) {
                    p.showPostulation(); // mostrar cada postulación en ventana
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe alumno con ese RUT.", "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //funcion que crea un alumno desde cero pidiendo su informacion a traves de ventanas
    public void createFromTerminal() {
    String rut;

        // Validación del RUT
        while (true) {
            try {
                rut = JOptionPane.showInputDialog("Ingrese RUT del estudiante (formato XX.XXX.XXX-Y):");
                if (rut == null) return; // usuario canceló
                rut = rut.trim();

                if (!rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9kK]")) {
                    throw new RutInvalidoException("El RUT debe tener el formato XX.XXX.XXX-Y.");
                }
                if (mapStudent.containsKey(rut)) {
                    throw new RutInvalidoException("Ya existe un estudiante con el RUT " + rut);
                }
                break; // RUT válido
            } catch (RutInvalidoException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Nombre
        String nombre = JOptionPane.showInputDialog("Ingrese nombre del estudiante:");
        if (nombre == null || nombre.trim().isEmpty()) return;

        // Dirección
        String direccion = JOptionPane.showInputDialog("Ingrese dirección del estudiante:");
        if (direccion == null || direccion.trim().isEmpty()) return;

        // Validación del correo
        String mail;
        while (true) {
            try {
                mail = JOptionPane.showInputDialog("Ingrese correo del estudiante:");
                if (mail == null) return; // el usuario canceló
                mail = mail.trim();
                if (!mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    throw new CorreoInvalidoException("Formato de correo inválido.");
                }
                break; // correo válido
            } catch (CorreoInvalidoException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Teléfono
        int phone;
        try {
            String phoneStr = JOptionPane.showInputDialog("Ingrese teléfono del estudiante:");
            if (phoneStr == null) return;
            phone = Integer.parseInt(phoneStr.trim());
            if (phone <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Teléfono inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Socioeconómico
        float socioEconomico;
        try {
            String socioStr = JOptionPane.showInputDialog("Ingrese tramo socioeconómico (0.0 a 100.0):");
            if (socioStr == null) return;
            socioEconomico = Float.parseFloat(socioStr.trim());
            if (socioEconomico < 0 || socioEconomico > 100) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Tramo socioeconómico inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Carrera
        String carrera = JOptionPane.showInputDialog("Ingrese carrera del estudiante:");
        if (carrera == null || carrera.trim().isEmpty()) return;

        // Institución
        String institucion = JOptionPane.showInputDialog("Ingrese institución del estudiante:");
        if (institucion == null || institucion.trim().isEmpty()) return;

        // Aprobación
        float aprobacion;
        try {
            String aprStr = JOptionPane.showInputDialog("Ingrese aprobación estimada (0.0 a 100.0):");
            if (aprStr == null) return;
            aprobacion = Float.parseFloat(aprStr.trim());
            if (aprobacion < 0 || aprobacion > 100) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Aprobación estimada inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear el estudiante y agregarlo al mapa
        Student nuevo = new Student(nombre, rut, direccion, mail, phone, socioEconomico, carrera, institucion, aprobacion);
        mapStudent.put(rut,nuevo);

        JOptionPane.showMessageDialog(null, "Estudiante registrado con éxito.");
    }
    //funcion que registra beca desde cero
    public void registrarBeca(){
        String idBeca;
        
        // Validación del id de la beca
        while (true) {
            try {
                idBeca = JOptionPane.showInputDialog("Ingrese ID de la beca (formato ABC-XX):");
                if (idBeca == null) return; // usuario canceló
                idBeca = idBeca.trim();

                if (!idBeca.matches("^[A-Z]{3}-\\d{2}$")) {
                    throw new IdBecaInvalidoException("El ID de la beca debe tener el formato ABC-XX.");
                }
                if (mapBeca.containsKey(idBeca)) {
                    throw new IdBecaInvalidoException("Ya existe una beca con el codigo " + idBeca);
                }
                break; // Id Invalido
            } catch (IdBecaInvalidoException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        // Nombre de la beca
        String nomBeca = JOptionPane.showInputDialog("Ingrese nombre de la beca:");
        if (nomBeca == null || nomBeca.trim().isEmpty()) return;

        int cupos;
        try {
            String cuposStr = JOptionPane.showInputDialog("Ingrese la cantidad de cupos de la beca:");
            if (cuposStr == null) return;
            cupos = Integer.parseInt(cuposStr.trim());
            if (cupos <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: formato de cupo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String requisitos = JOptionPane.showInputDialog("Ingrese los requisitos de la beca:");
        if (requisitos == null || nomBeca.trim().isEmpty()) return;
        
        String tipoBeca = JOptionPane.showInputDialog("¿La beca que desea registrar es de Arancel o Manutencion? (Ingrese Arancel/Manutencion): ");
        
        if (tipoBeca.equals("Manutencion") || tipoBeca.equals("manutencion")){
            int monto;
            try {
                String montoStr = JOptionPane.showInputDialog("Ingrese el monto de la beca: ");
                if (montoStr == null) return;
                monto = Integer.parseInt(montoStr.trim());
                if (monto <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: formato de monto inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int periodo;
            try {
                String periodoStr = JOptionPane.showInputDialog("Ingrese el periodo de la beca: ");
                if (periodoStr == null) return;
                periodo = Integer.parseInt(periodoStr.trim());
                if (periodo <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: formato de periodo inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            BecaManutencion nuevaBeca = new BecaManutencion(idBeca, nomBeca, cupos, requisitos, monto, periodo);
            mapBeca.put(idBeca, nuevaBeca);
        } 
        else if (tipoBeca.equals("Arancel") || tipoBeca.equals("arancel")) {
            int porcentajeDescuento;
            try {
                String porcentajeDescuentoStr = JOptionPane.showInputDialog("Ingrese el monto de la beca: ");
                if (porcentajeDescuentoStr == null) return;
                porcentajeDescuento = Integer.parseInt(porcentajeDescuentoStr.trim());
                if (porcentajeDescuento <= 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: formato de porcentaje inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            BecaArancel nuevaBeca = new BecaArancel(idBeca, nomBeca, cupos, requisitos, porcentajeDescuento);
            mapBeca.put(idBeca, nuevaBeca);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Tipo Invalido");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "Beca registrada con éxito.");
    }

    
    public void agregarAlumno(Student estudiante){mapStudent.put(estudiante.getRut(), estudiante);}
    public void agregarBeca(Beca beca){mapBeca.put(beca.getCodigo(), beca);}
    
    //funcion para eliminar una postulacion a traves del estudiante y su beca
    public void eliminarPostulacionEspecifica() {
        try {
            // 1. Pedir RUT del estudiante
            String rut = JOptionPane.showInputDialog("Ingrese RUT del estudiante:");
            if (rut == null || rut.trim().isEmpty()) return;
        
            rut = rut.trim(); // Limpiar espacios
        
            // 2. Pedir ID de la beca
            String idBeca = JOptionPane.showInputDialog("Ingrese ID de la beca:");
            if (idBeca == null || idBeca.trim().isEmpty()) return;
        
            idBeca = idBeca.trim(); // Limpiar espacios
        
            // 3. Buscar estudiante
            Student estudiante = mapStudent.get(rut);
            if (estudiante == null) {
                JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
                return;
            }
        
            // 4. Buscar beca
            Beca beca = mapBeca.get(idBeca);
            if (beca == null) {
                JOptionPane.showMessageDialog(null, "Beca no encontrada.");
                return;
            }
        
            // 5. ELIMINAR DE AMBOS LADOS
            boolean eliminadoEstudiante = false;
            boolean eliminadoBeca = false;
        
            // Eliminar del estudiante
            Iterator<Postulation> iteratorEst = estudiante.getListPostulation().iterator();
            while (iteratorEst.hasNext()) {
                Postulation p = iteratorEst.next();
                if (p.getIdBeca().equals(idBeca)) {
                    iteratorEst.remove();
                    eliminadoEstudiante = true;
                    break; // Solo debería haber una postulación por beca
                }
            }
        
            // Eliminar de la beca
            Iterator<Postulation> iteratorBeca = beca.getPostulaciones().iterator();
            while (iteratorBeca.hasNext()) {
                Postulation p = iteratorBeca.next();
                if (p.getIdStudent().equals(rut)) {
                    iteratorBeca.remove();
                    eliminadoBeca = true;
                    break;
                }
            }
        
            // 6. Confirmar resultados
            if (eliminadoEstudiante && eliminadoBeca) {
                JOptionPane.showMessageDialog(null, 
                    "Postulación eliminada correctamente de ambos lados.\n" +
                    "Estudiante: " + rut + "\n" +
                    "Beca: " + idBeca);
            } else if (eliminadoEstudiante) {
                JOptionPane.showMessageDialog(null, 
                    "Postulación eliminada solo del estudiante.\n" +
                    "No se encontró en la beca.");
            } else if (eliminadoBeca) {
                JOptionPane.showMessageDialog(null, 
                    "Postulación eliminada solo de la beca.\n" +
                    "No se encontró en el estudiante.");
            } else {
                JOptionPane.showMessageDialog(null, 
                    "No se encontró la postulación.\n" +
                    "Verifique que el estudiante " + rut + 
                    " esté postulando a la beca " + idBeca);
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar postulación: " + e.getMessage());
        }
    }
    //Funcion que elimina un estudiante a traves de su rut
    public void eliminarEstudiantePorRUT() {
        try {
            // 1. Pedir RUT del estudiante
            String rutInput = JOptionPane.showInputDialog("Ingrese RUT del estudiante a eliminar:");
            if (rutInput == null || rutInput.trim().isEmpty()) return;
        
            String rut = rutInput.trim();
        
            // 2. Buscar estudiante
            Student estudiante = mapStudent.get(rut);
            if (estudiante == null) {
                JOptionPane.showMessageDialog(null, "Estudiante no encontrado.");
                return;
            }
        
            // 3. ELIMINAR POSTULACIONES DEL ESTUDIANTE DE TODAS LAS BECAS
            int postulacionesEliminadas = 0;
            for (Postulation postulacion : estudiante.getListPostulation()) {
                Beca beca = mapBeca.get(postulacion.getIdBeca());
                if (beca != null) {
                    // Eliminar la postulación de la beca
                    boolean eliminado = beca.getPostulaciones()
                        .removeIf(p -> p.getIdStudent().equals(rut));
                    if (eliminado) {
                        postulacionesEliminadas++;
                    }
                }
            }
        
            // 4. ELIMINAR AL ESTUDIANTE
            mapStudent.remove(rut);
        
            // 5. Confirmar resultados
            JOptionPane.showMessageDialog(null, 
                "✓ Estudiante eliminado correctamente.\n" +
                "RUT: " + rut + "\n" +
                "Postulaciones eliminadas: " + postulacionesEliminadas);
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar estudiante: " + e.getMessage());
        }
    }
    //Funcion que elimina una Beca a travez de su id
    public void eliminarBecaPorID() {
        try {
            // 1. Pedir ID de la beca
            String idInput = JOptionPane.showInputDialog("Ingrese ID de la beca a eliminar:");
            if (idInput == null || idInput.trim().isEmpty()) return;
        
            String idBeca = idInput.trim();
        
            // 2. Buscar beca
            Beca beca = mapBeca.get(idBeca);
            if (beca == null) {
                JOptionPane.showMessageDialog(null, "Beca no encontrada.");
                return;
            }
        
            // 3. ELIMINAR POSTULACIONES DE LA BECA DE TODOS LOS ESTUDIANTES
            int postulacionesEliminadas = 0;
            for (Student estudiante : mapStudent.values()) {
                // Eliminar postulaciones a esta beca del estudiante
                int eliminadas = estudiante.getListPostulation().size();
                estudiante.getListPostulation().removeIf(p -> p.getIdBeca().equals(idBeca));
                eliminadas = eliminadas - estudiante.getListPostulation().size();
                postulacionesEliminadas += eliminadas;
            }
        
            // 4. ELIMINAR LA BECA
            mapBeca.remove(idBeca);
        
            // 5. Confirmar resultados
            JOptionPane.showMessageDialog(null, 
                "✓ Beca eliminada correctamente.\n" +
                "ID Beca: " + idBeca + "\n" +
                "Nombre: " + beca.getNombre() + "\n" +
                "Postulaciones eliminadas: " + postulacionesEliminadas);
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar beca: " + e.getMessage());
        }
    }
    //Funcion que muestra los datos de una beca
    public void buscarBeca() {
        String codigo = javax.swing.JOptionPane.showInputDialog(
                null,
                "Ingrese el ID de la beca:",
                "Buscar Beca",
                javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (codigo != null && !codigo.trim().isEmpty()) {
            Beca beca = mapBeca.get(codigo);
            if (beca != null) {
                beca.mostrarBeca(); // llama al método polimórfico de la subclase
            } else {
                javax.swing.JOptionPane.showMessageDialog(
                    null,
                    "No se encontró ninguna beca con el código: " + codigo,
                    "Error",
                    javax.swing.JOptionPane.ERROR_MESSAGE
                );
            }
        } else {
            javax.swing.JOptionPane.showMessageDialog(
                null,
                "Debe ingresar un código válido.",
                "Advertencia",
                javax.swing.JOptionPane.WARNING_MESSAGE
            );
        }
    }
    
    public void filtrarPostulacionesPorEstadoYTipoBeca() {
    // Opciones predefinidas para el estado de la postulación
    String[] estados = {"En espera", "Aprobada", "Rechazada"};
    // Opciones predefinidas para el tipo de beca
    String[] tipos = {"Arancel", "Manutención"};
    
    // Solicita al usuario que seleccione un estado mediante un diálogo desplegable
    String estado = (String) JOptionPane.showInputDialog(null, "Seleccione estado:", "Filtrar Postulaciones",
            JOptionPane.QUESTION_MESSAGE, null, estados, estados[0]);
    if (estado == null) return; // Cancela si el usuario cierra el diálogo
    
    // Solicita al usuario que seleccione un tipo de beca mediante un diálogo desplegable
    String tipo = (String) JOptionPane.showInputDialog(null, "Seleccione tipo de beca:", "Filtrar Postulaciones",
            JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
    if (tipo == null) return; // Cancela si el usuario cierra el diálogo

    // Construye el mensaje con los resultados del filtrado
    StringBuilder resultado = new StringBuilder("Postulaciones " + estado + " para becas de tipo " + tipo + ":\n");
    int count = 0; // Contador de postulaciones encontradas
    
    // Itera sobre todos los estudiantes en mapStudent
    for (Student s : mapStudent.values()) {
        // Itera sobre las postulaciones de cada estudiante
        for (Postulation p : s.getListPostulation()) {
            // Obtiene la beca asociada a la postulación
            Beca b = mapBeca.get(p.getIdBeca());
            // Verifica si la postulación coincide con el estado y tipo seleccionados
            if (b != null && p.getState().equalsIgnoreCase(estado) && b.getTipo().equalsIgnoreCase(tipo)) {
                resultado.append("ID: ").append(p.getIdPostulation())
                        .append(", Estudiante: ").append(s.getName())
                        .append(", Beca: ").append(b.getNombre()).append("\n");
                count++;
            }
        }
    }
    
    // Muestra los resultados o un mensaje si no se encontraron postulaciones
    if (count == 0) {
        JOptionPane.showMessageDialog(null, "No se encontraron postulaciones.", "Información", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, resultado.toString(), "Resultados", JOptionPane.INFORMATION_MESSAGE);
    }
}
    //Funcion que registra un postulacion desde cero
    public void registrarPostulacion() {
        try {
            //Pedir RUT del estudiante con validación
            String rut;
            while (true) {
                try {
                    rut = JOptionPane.showInputDialog("Ingrese el RUT del estudiante (formato XX.XXX.XXX-Y):");
                    if (rut == null || rut.trim().isEmpty()) return;
                    rut = rut.trim();

                    // Validar formato con excepción personalizada
                    if (!rut.matches("\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9kK]")) {
                        throw new RutInvalidoException("El RUT debe tener el formato XX.XXX.XXX-Y.");
                    }

                    if (!mapStudent.containsKey(rut)) {
                        throw new RutInvalidoException("No se encontró un estudiante con ese RUT.");
                    }

                    break; // válido → salimos del while
                } catch (RutInvalidoException e) {
                    JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            Student estudiante = mapStudent.get(rut);

            //Pedir ID de la beca
            String idBeca = JOptionPane.showInputDialog("Ingrese el ID de la beca:");
            if (idBeca == null || idBeca.trim().isEmpty()) return;
            idBeca = idBeca.trim();

            Beca beca = mapBeca.get(idBeca);
            if (beca == null) {
                JOptionPane.showMessageDialog(null, "No se encontró una beca con ese ID.");
                return;
            }

            //Validar que el estudiante no tenga ya una postulación a esa beca
            for (Postulation p : estudiante.getListPostulation()) {
                if (p.getIdBeca().equals(idBeca)) {
                    JOptionPane.showMessageDialog(null, "El estudiante ya está postulando a esta beca.");
                    return;
                }
            }

            //Generar ID único para la postulación
            String idPostulation = String.format("PST-%03d", cont);
            cont++;

            //Registrar la fecha en formato dd/MM/yy
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
            String fecha = LocalDate.now().format(formato);

            //Crear la nueva postulación
            Postulation nueva = new Postulation(idPostulation, rut, idBeca, fecha, "En espera");

            //Agregar al estudiante y a la beca
            estudiante.addPostulation(nueva);
            beca.agregarPostulacion(nueva);

            //Confirmar
            JOptionPane.showMessageDialog(null,
                "✓ Postulación registrada con éxito.\n" +
                "ID Postulación: " + idPostulation + "\n" +
                "Estudiante: " + estudiante.getName() + "\n" +
                "Beca: " + beca.getNombre() + "\n" +
                "Fecha: " + fecha);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar postulación: " + e.getMessage());
        }
    }
    //funcion que busca las postulaciones de una beca
    public void buscarPostulacionBeca() {
        try {
            // 1. Pedir ID de la beca
            String idBeca = JOptionPane.showInputDialog("Ingrese ID de la beca:");
            if (idBeca == null || idBeca.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, 
                    "ID de beca no válido.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            idBeca = idBeca.trim();

            // 2. Buscar la beca
            Beca beca = mapBeca.get(idBeca);
            if (beca == null) {
                JOptionPane.showMessageDialog(null, 
                    "Beca no encontrada.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Pedir ID de postulación (opcional)
            String idPost = JOptionPane.showInputDialog("Ingrese ID de la postulación (dejar vacío para mostrar todas):");
            if (idPost == null || idPost.trim().isEmpty()) {
                // Mostrar todas las postulaciones
                beca.mostrarPostulaciones();
            } else {
                // Mostrar postulación específica
                beca.mostrarPostulaciones(idPost.trim());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error al buscar postulación: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void aumentarCont(){cont += 1;}
}
