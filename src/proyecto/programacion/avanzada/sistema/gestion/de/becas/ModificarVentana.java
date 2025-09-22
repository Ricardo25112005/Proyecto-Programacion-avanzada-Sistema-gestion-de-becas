/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @archivo: ModificarVentana.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa la ventana para modificar datos (alumno o postulación)
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 22-09-25
 */
public class ModificarVentana extends JFrame {
    private Maps maps;
    private static final Pattern RUT_PATTERN = Pattern.compile("^\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9K]$"); // Formato XX.XXX.XXX-Y
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{2}$"); // Formato dd/MM/yy

    public ModificarVentana(Maps maps) {
        this.maps = maps;
        setTitle("Modificar Datos");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Usar BoxLayout vertical para formato de lista
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen

        // Título centrado
        JLabel titulo = new JLabel("Menú de Modificación", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio después del título

        // Botones en formato lista
        JButton btnModificarAlumno = new JButton("Modificar Alumno");
        btnModificarAlumno.setMaximumSize(new Dimension(250, 40));
        btnModificarAlumno.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarAlumno.addActionListener(e -> modificarAlumno());
        panel.add(btnModificarAlumno);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnModificarPostulacion = new JButton("Modificar Postulación");
        btnModificarPostulacion.setMaximumSize(new Dimension(250, 40));
        btnModificarPostulacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarPostulacion.addActionListener(e -> modificarPostulacion());
        panel.add(btnModificarPostulacion);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnVolver = new JButton("Volver al Menú");
        btnVolver.setMaximumSize(new Dimension(250, 40));
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(e -> {
            dispose(); // Cierra ventana
            new Servicios(maps).setVisible(true); // Vuelve al menú principal
        });
        panel.add(btnVolver);

        add(panel);
        setVisible(true);
    }

    private void modificarAlumno() {
        // Pedir el RUT del estudiante
        String rut = JOptionPane.showInputDialog(this, "Ingrese el RUT del estudiante a modificar (formato XX.XXX.XXX-Y):");
        if (rut == null || rut.trim().isEmpty() || !RUT_PATTERN.matcher(rut.trim()).matches()) {
            JOptionPane.showMessageDialog(this, "RUT no válido. Use formato XX.XXX.XXX-Y.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar estudiante por RUT
        final Student estudiante = maps.getMapStudent().get(rut.trim());
        final String rutOriginal = rut.trim();

        if (estudiante == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un estudiante con el RUT: " + rut, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar directamente a modificarCompleto
        modificarCompleto(estudiante, rutOriginal);
    }

    private void modificarCompleto(final Student estudiante, final String rutOriginal) {
        try {
            // Crear un panel para todos los campos
            JDialog dialog = new JDialog(this, "Modificar Estudiante Completo", true);
            dialog.setSize(400, 520); 
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            // Campos de entrada
            JTextField nombreField = new JTextField(estudiante.getName(), 20);
            JTextField rutField = new JTextField(rutOriginal, 20);
            JTextField direccionField = new JTextField(estudiante.getAddress(), 20);
            JTextField correoField = new JTextField(estudiante.getMail(), 20);
            JTextField telefonoField = new JTextField(String.valueOf(estudiante.getPhone()), 20);
            JTextField tramoField = new JTextField(String.valueOf(estudiante.getSocioEconomicSection()), 20);
            JTextField carreraField = new JTextField(estudiante.getCarrer(), 20);
            JTextField institucionField = new JTextField(estudiante.getInstitution(), 20);
            JTextField aprobacionField = new JTextField(String.valueOf(estudiante.getEstimatedApproval()), 20);

            dialog.add(new JLabel("Nombre:"));
            dialog.add(nombreField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("RUT (XX.XXX.XXX-Y):"));
            dialog.add(rutField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Dirección:"));
            dialog.add(direccionField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Correo:"));
            dialog.add(correoField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Teléfono:"));
            dialog.add(telefonoField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Tramo Socioeconómico (0.0 a 100.0):"));
            dialog.add(tramoField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Carrera:"));
            dialog.add(carreraField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Institución:"));
            dialog.add(institucionField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Aprobación Estimada (0.0 a 100.0):"));
            dialog.add(aprobacionField);
            dialog.add(Box.createRigidArea(new Dimension(0, 20)));

            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnGuardar.addActionListener(e -> {
                try {
                    // Validar entradas
                    String nombre = nombreField.getText().trim();
                    if (nombre.isEmpty()) throw new IllegalArgumentException("Nombre no válido.");
                    String nuevoRut = rutField.getText().trim();
                    if (!RUT_PATTERN.matcher(nuevoRut).matches()) throw new IllegalArgumentException("RUT no válido. Use formato XX.XXX.XXX-Y.");
                    String direccion = direccionField.getText().trim();
                    if (direccion.isEmpty()) throw new IllegalArgumentException("Dirección no válida.");
                    String correo = correoField.getText().trim();
                    if (correo.isEmpty() || !correo.contains("@")) throw new IllegalArgumentException("Correo no válido.");
                    int telefono = Integer.parseInt(telefonoField.getText().trim());
                    if (telefono <= 0) throw new NumberFormatException("Teléfono inválido.");
                    float tramo = Float.parseFloat(tramoField.getText().trim());
                    if (tramo < 0 || tramo > 100) throw new NumberFormatException("Tramo socioeconómico inválido.");
                    String carrera = carreraField.getText().trim();
                    if (carrera.isEmpty()) throw new IllegalArgumentException("Carrera no válida.");
                    String institucion = institucionField.getText().trim();
                    if (institucion.isEmpty()) throw new IllegalArgumentException("Institución no válida.");
                    float aprobacion = Float.parseFloat(aprobacionField.getText().trim());
                    if (aprobacion < 0 || aprobacion > 100) throw new NumberFormatException("Aprobación estimada inválida.");

                    // Confirmar cambios
                    String confirmacion = String.format(
                            "Confirmar cambios:\nNombre: %s\nRUT: %s\nDirección: %s\nCorreo: %s\nTeléfono: %d\nTramo Socioeconómico: %.1f\nCarrera: %s\nInstitución: %s\nAprobación Estimada: %.1f",
                            nombre, nuevoRut, direccion, correo, telefono, tramo, carrera, institucion, aprobacion
                    );
                    int confirm = JOptionPane.showConfirmDialog(dialog, confirmacion, "Confirmar Cambios", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm != JOptionPane.OK_OPTION) return;

                    // Actualizar estudiante
                    Student nuevoEstudiante = new Student(nombre, nuevoRut, direccion, correo, telefono, tramo, carrera, institucion, aprobacion);
                    nuevoEstudiante.getListPostulation().addAll(estudiante.getListPostulation()); // Conservar postulaciones
                    maps.getMapStudent().remove(rutOriginal); // Eliminar antiguo
                    maps.getMapStudent().put(nuevoRut, nuevoEstudiante); // Añadir nuevo

                    // Actualizar RUT en postulaciones si cambió
                    if (!nuevoRut.equals(rutOriginal)) {
                        for (Postulation post : nuevoEstudiante.getListPostulation()) {
                            post.setIdStudent(nuevoRut);
                        }
                    }

                    // Guardar en CSV
                    DataLoader.guardarEstudiantes("src/resources/estudiantes.csv", maps);
                    DataLoader.guardarPostulaciones("src/resources/postulaciones.csv", maps);
                    JOptionPane.showMessageDialog(dialog, "Estudiante modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            dialog.add(btnGuardar);
            dialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir formulario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void modificarPostulacion() {
        // Pedir el RUT del estudiante
        String rut = JOptionPane.showInputDialog(this, "Ingrese el RUT del estudiante (formato XX.XXX.XXX-Y):");
        if (rut == null || rut.trim().isEmpty() || !RUT_PATTERN.matcher(rut.trim()).matches()) {
            JOptionPane.showMessageDialog(this, "RUT no válido. Use formato XX.XXX.XXX-Y.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar estudiante por RUT
        final Student estudiante = maps.getMapStudent().get(rut.trim());
        final String rutOriginal = rut.trim();

        if (estudiante == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un estudiante con el RUT: " + rut, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si tiene postulaciones
        List<Postulation> postulaciones = estudiante.getListPostulation();
        if (postulaciones == null || postulaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El estudiante no tiene postulaciones registradas.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Mostrar cantidad de postulaciones y permitir seleccionar una
        String mensaje = "El estudiante tiene " + postulaciones.size() + " postulación(es). Seleccione el ID de la postulación a modificar:";
        String[] idPostulaciones = postulaciones.stream().map(Postulation::getIdPostulation).toArray(String[]::new);
        String idSeleccionado = (String) JOptionPane.showInputDialog(
                this,
                mensaje,
                "Seleccionar Postulación",
                JOptionPane.QUESTION_MESSAGE,
                null,
                idPostulaciones,
                idPostulaciones[0]
        );

        if (idSeleccionado == null) return;

        // Buscar la postulación seleccionada
        Postulation postSeleccionada = null;
        for (Postulation post : postulaciones) {
            if (post.getIdPostulation().equals(idSeleccionado)) {
                postSeleccionada = post;
                break;
            }
        }

        if (postSeleccionada == null) {
            JOptionPane.showMessageDialog(this, "Error: No se encontró la postulación seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Modificar la postulación seleccionada
        modificarPostulacionCompleta(estudiante, rutOriginal, postSeleccionada);
    }

    private void modificarPostulacionCompleta(final Student estudiante, final String rutOriginal, final Postulation postSeleccionada) {
        try {
            // Crear un panel para todos los campos de la postulación
            JDialog dialog = new JDialog(this, "Modificar Postulación", true);
            dialog.setSize(400, 280); // Tamaño ajustado para los campos de postulación
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            // Campos de entrada
            JTextField idField = new JTextField(postSeleccionada.getIdPostulation(), 20);
            idField.setEditable(false); // ID no editable
            JTextField idBecaField = new JTextField(postSeleccionada.getIdBeca(), 20);
            JTextField estadoField = new JTextField(postSeleccionada.getState(), 20);
            JTextField fechaField = new JTextField(postSeleccionada.getDatePostulation(), 20);

            dialog.add(new JLabel("ID Postulación (no editable):"));
            dialog.add(idField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("ID Beca:"));
            dialog.add(idBecaField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Estado (En espera/Aprobada/Rechazada):"));
            dialog.add(estadoField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Fecha de Postulación (dd/MM/yy):"));
            dialog.add(fechaField);
            dialog.add(Box.createRigidArea(new Dimension(0, 20)));

            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnGuardar.addActionListener(e -> {
                try {
                    // Validar entradas
                    String idBeca = idBecaField.getText().trim();
                    if (idBeca.isEmpty()) throw new IllegalArgumentException("ID Beca no válido.");
                    String estado = estadoField.getText().trim();
                    if (!estado.equalsIgnoreCase("En espera") && !estado.equalsIgnoreCase("Aprobada") && !estado.equalsIgnoreCase("Rechazada")) {
                        throw new IllegalArgumentException("Estado no válido. Use: En espera, Aprobada o Rechazada.");
                    }
                    String fecha = fechaField.getText().trim();
                    if (!DATE_PATTERN.matcher(fecha).matches()) {
                        throw new IllegalArgumentException("Fecha no válida. Use formato dd/MM/yy.");
                    }

                    // Confirmar cambios
                    String confirmacion = String.format(
                            "Confirmar cambios:\nID Postulación: %s\nID Beca: %s\nEstado: %s\nFecha: %s",
                            postSeleccionada.getIdPostulation(), idBeca, estado, fecha
                    );
                    int confirm = JOptionPane.showConfirmDialog(dialog, confirmacion, "Confirmar Cambios", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm != JOptionPane.OK_OPTION) return;

                    // Actualizar postulación
                    postSeleccionada.setIdBeca(idBeca);
                    postSeleccionada.setState(estado);
                    postSeleccionada.setDatePostulation(fecha);

                    // Guardar en CSV
                    DataLoader.guardarPostulaciones("src/resources/postulaciones.csv", maps);
                    JOptionPane.showMessageDialog(dialog, "Postulación modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            dialog.add(btnGuardar);
            dialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al abrir formulario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
