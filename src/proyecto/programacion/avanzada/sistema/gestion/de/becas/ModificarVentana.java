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
 * @version: 2.0.0
 * @Fecha: 22-09-25
 */
public class ModificarVentana extends JFrame {
    private Maps maps;
    private static final Pattern RUT_PATTERN = Pattern.compile("^\\d{1,2}\\.\\d{3}\\.\\d{3}-[0-9K]$"); // Formato XX.XXX.XXX-Y
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{2}/\\d{2}/\\d{2}$"); // Formato dd/MM/yy
    //se muestra la ventana especifica para la modificacion de elementos
    public ModificarVentana(Maps maps) {
        this.maps = maps;
        setTitle("Modificar Datos");
        setSize(400, 300); // Aumentado para más botones
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
        JButton btnModificarAlumno = new JButton("Modificar Estudiante");
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

        JButton btnModificarBeca = new JButton("Modificar Beca");
        btnModificarBeca.setMaximumSize(new Dimension(250, 40));
        btnModificarBeca.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificarBeca.addActionListener(e -> modificarBeca());
        panel.add(btnModificarBeca);
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
    //funcion que modifica los elementos de un estudiante
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
            dialog.add(new JLabel("Tramo Socioeconómico:"));
            dialog.add(tramoField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Carrera:"));
            dialog.add(carreraField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Institución:"));
            dialog.add(institucionField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Aprobación Estimada:"));
            dialog.add(aprobacionField);
            dialog.add(Box.createRigidArea(new Dimension(0, 20)));

            JButton btnGuardar = new JButton("Guardar");
            btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnGuardar.addActionListener(e -> {
                try {
                    // Validar entradas
                    String nombre = nombreField.getText().trim();
                    if (nombre.isEmpty()) {
                        throw new IllegalArgumentException("El nombre no puede estar vacío.");
                    }
                    String rut = rutField.getText().trim();
                    if (!RUT_PATTERN.matcher(rut).matches()) {
                        throw new IllegalArgumentException("RUT no válido. Use formato XX.XXX.XXX-Y.");
                    }
                    if (!rut.equals(rutOriginal) && maps.getMapStudent().containsKey(rut)) {
                        throw new IllegalArgumentException("El nuevo RUT ya existe.");
                    }
                    String direccion = direccionField.getText().trim();
                    if (direccion.isEmpty()) {
                        throw new IllegalArgumentException("La dirección no puede estar vacía.");
                    }
                    String correo = correoField.getText().trim();
                    if (correo.isEmpty() || !correo.contains("@")) {
                        throw new IllegalArgumentException("Correo no válido.");
                    }
                    int telefono;
                    try {
                        telefono = Integer.parseInt(telefonoField.getText().trim());
                        if (telefono < 0) {
                            throw new IllegalArgumentException("Teléfono no puede ser negativo.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Teléfono debe ser un número válido.");
                    }
                    float tramo;
                    try {
                        tramo = Float.parseFloat(tramoField.getText().trim());
                        if (tramo < 0) {
                            throw new IllegalArgumentException("Tramo socioeconómico no puede ser negativo.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Tramo socioeconómico debe ser un número válido.");
                    }
                    String carrera = carreraField.getText().trim();
                    if (carrera.isEmpty()) {
                        throw new IllegalArgumentException("La carrera no puede estar vacía.");
                    }
                    String institucion = institucionField.getText().trim();
                    if (institucion.isEmpty()) {
                        throw new IllegalArgumentException("La institución no puede estar vacía.");
                    }
                    float aprobacion;
                    try {
                        aprobacion = Float.parseFloat(aprobacionField.getText().trim());
                        if (aprobacion < 0 || aprobacion > 100) {
                            throw new IllegalArgumentException("Aprobación estimada debe estar entre 0 y 100.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Aprobación estimada debe ser un número válido.");
                    }

                    // Confirmar cambios
                    String confirmacion = String.format(
                            "Confirmar cambios:\nNombre: %s\nRUT: %s\nDirección: %s\nCorreo: %s\nTeléfono: %d\nTramo: %.1f\nCarrera: %s\nInstitución: %s\nAprobación: %.1f",
                            nombre, rut, direccion, correo, telefono, tramo, carrera, institucion, aprobacion);
                    int confirm = JOptionPane.showConfirmDialog(dialog, confirmacion, "Confirmar Cambios", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm != JOptionPane.OK_OPTION) return;

                    // Actualizar estudiante
                    if (!rut.equals(rutOriginal)) {
                        maps.getMapStudent().remove(rutOriginal);
                        estudiante.setRut(rut);
                        for (Postulation p : estudiante.getListPostulation()) {
                            p.setIdStudent(rut);
                        }
                    }
                    estudiante.setName(nombre);
                    estudiante.setAddress(direccion);
                    estudiante.setMail(correo);
                    estudiante.setPhone(telefono);
                    estudiante.setSocioEconomicSection(tramo);
                    estudiante.setCarrer(carrera);
                    estudiante.setInstitution(institucion);
                    estudiante.setEstimatedApproval(aprobacion);
                    maps.getMapStudent().put(rut, estudiante);

                    // Guardar en CSV
                    DataLoader.guardarEstudiantes("src/resources/estudiantes.csv", maps);
                    JOptionPane.showMessageDialog(dialog, "Estudiante modificado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
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

    //Funcion que modifica una postulación
    private void modificarPostulacion() {
        String rut = JOptionPane.showInputDialog(this, "Ingrese RUT del estudiante (formato XX.XXX.XXX-Y):");
        if (rut == null || rut.trim().isEmpty() || !RUT_PATTERN.matcher(rut.trim()).matches()) {
            JOptionPane.showMessageDialog(this, "RUT no válido. Use formato XX.XXX.XXX-Y.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        final Student estudiante = maps.getMapStudent().get(rut.trim());
        final String rutOriginal = rut.trim();

        if (estudiante == null) {
            JOptionPane.showMessageDialog(this, "No se encontró un estudiante con el RUT: " + rut, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si tiene postulaciones
        java.util.List<Postulation> postulaciones = estudiante.getListPostulation();
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
            // Crear un panel para los campos de la postulación
            JDialog dialog = new JDialog(this, "Modificar Postulación", true);
            dialog.setSize(400, 220);
            dialog.setLocationRelativeTo(this);
            dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            // Campos de entrada
            JTextField idField = new JTextField(postSeleccionada.getIdPostulation(), 20);
            idField.setEditable(false); // ID no editable
            JTextField estadoField = new JTextField(postSeleccionada.getState(), 20);
            JTextField fechaField = new JTextField(postSeleccionada.getDatePostulation(), 20);

            dialog.add(new JLabel("ID Postulación (no editable):"));
            dialog.add(idField);
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
                    String estado = estadoField.getText().trim();
                    if (!estado.equalsIgnoreCase("En espera") && !estado.equalsIgnoreCase("Aprobada") && !estado.equalsIgnoreCase("Rechazada")) {
                        throw new IllegalArgumentException("Estado no válido. Use: En espera, Aprobada o Rechazada.");
                    }
                    String fecha = fechaField.getText().trim();
                    if (!DATE_PATTERN.matcher(fecha).matches()) {
                        throw new IllegalArgumentException("Fecha no válida. Use formato dd/MM/yy.");
                    }

                    // Validar cupos si se cambia a "Aprobada"
                    if (estado.equalsIgnoreCase("Aprobada") && !postSeleccionada.getState().equalsIgnoreCase("Aprobada")) {
                        Beca beca = maps.getMapBeca().get(postSeleccionada.getIdBeca());
                        if (beca != null) {
                            long aprobadas = beca.getPostulaciones().stream()
                                    .filter(p -> p.getState().equalsIgnoreCase("Aprobada"))
                                    .count();
                            if (aprobadas >= beca.getCupos()) {
                                throw new IllegalArgumentException("No hay cupos disponibles para aprobar esta postulación en la beca seleccionada.");
                            }
                        }
                    }

                    // Confirmar cambios
                    String confirmacion = String.format(
                            "Confirmar cambios:\nID Postulación: %s\nEstado: %s\nFecha: %s",
                            postSeleccionada.getIdPostulation(), estado, fecha);
                    int confirm = JOptionPane.showConfirmDialog(dialog, confirmacion, "Confirmar Cambios", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm != JOptionPane.OK_OPTION) return;

                    // Actualizar postulación
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
    //Funcion que modifica una beca
    private void modificarBeca() {
        // Pedir el ID de la beca
        String codigo = JOptionPane.showInputDialog(this, "Ingrese el Codigo de la beca a modificar:");
        if (codigo == null || codigo.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Codigo no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Buscar beca por ID
        final Beca beca = maps.getMapBeca().get(codigo.trim());
        final String codigoOriginal = codigo.trim();

        if (beca == null) {
            JOptionPane.showMessageDialog(this, "No se encontró una beca con el Codigo: " + codigo, "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear formulario según tipo de beca
        JDialog dialog = new JDialog(this, "Modificar Beca (" + beca.getTipo() + ")", true);
        dialog.setSize(400, beca.getTipo().equals("Arancel") ? 320 : 360); // Más espacio para Manutención
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Campos comunes
        final JTextField codigoField = new JTextField(codigoOriginal, 20);
        codigoField.setEditable(false); // Código no editable
        final JTextField nombreField = new JTextField(beca.getNombre(), 20);
        final JTextField cuposField = new JTextField(String.valueOf(beca.getCupos()), 20);
        final JTextField requisitosField = new JTextField(beca.getRequisitos(), 20);

        dialog.add(new JLabel("Código Beca (no editable):"));
        dialog.add(codigoField);
        dialog.add(Box.createRigidArea(new Dimension(0, 10)));
        dialog.add(new JLabel("Nombre:"));
        dialog.add(nombreField);
        dialog.add(Box.createRigidArea(new Dimension(0, 10)));
        dialog.add(new JLabel("Cupos:"));
        dialog.add(cuposField);
        dialog.add(Box.createRigidArea(new Dimension(0, 10)));
        dialog.add(new JLabel("Requisitos:"));
        dialog.add(requisitosField);
        dialog.add(Box.createRigidArea(new Dimension(0, 10)));

        // Campos específicos según tipo
        final JTextField porcentajeField;
        final JTextField montoField;
        final JTextField periodoField;

        if (beca.getTipo().equals("Arancel")) {
            porcentajeField = new JTextField(String.valueOf(((BecaArancel) beca).getPorcentajeDescuento()), 20);
            montoField = null; // No se usa
            periodoField = null; // No se usa
            dialog.add(new JLabel("Porcentaje de Descuento (%):"));
            dialog.add(porcentajeField);
            dialog.add(Box.createRigidArea(new Dimension(0, 20)));
        } else {
            porcentajeField = null; // No se usa
            montoField = new JTextField(String.valueOf(((BecaManutencion) beca).getMonto()), 20);
            periodoField = new JTextField(String.valueOf(((BecaManutencion) beca).getPeriodo()), 20);
            dialog.add(new JLabel("Monto Manutención:"));
            dialog.add(montoField);
            dialog.add(Box.createRigidArea(new Dimension(0, 10)));
            dialog.add(new JLabel("Período (meses):"));
            dialog.add(periodoField);
            dialog.add(Box.createRigidArea(new Dimension(0, 20)));
        }

        // Botón Guardar
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGuardar.addActionListener(e -> {
            try {
                // Validar entradas comunes
                String nombre = nombreField.getText().trim();
                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío.");
                }
                int cupos;
                try {
                    cupos = Integer.parseInt(cuposField.getText().trim());
                    if (cupos < 0) {
                        throw new IllegalArgumentException("Los cupos no pueden ser negativos.");
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Cupos debe ser un número entero válido.");
                }
                String requisitos = requisitosField.getText().trim();
                if (requisitos.isEmpty()) {
                    throw new IllegalArgumentException("Los requisitos no pueden estar vacíos.");
                }

                // Validar entradas específicas
                if (beca.getTipo().equals("Arancel")) {
                    float porcentaje;
                    try {
                        porcentaje = Float.parseFloat(porcentajeField.getText().trim());
                        if (porcentaje < 0 || porcentaje > 100) {
                            throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Porcentaje debe ser un número válido.");
                    }
                    // Confirmar cambios
                    String confirmacion = String.format(
                            "Confirmar cambios:\nCódigo: %s\nNombre: %s\nCupos: %d\nRequisitos: %s\nPorcentaje: %.1f%%",
                            codigoOriginal, nombre, cupos, requisitos, porcentaje);
                    int confirm = JOptionPane.showConfirmDialog(dialog, confirmacion, "Confirmar Cambios", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm != JOptionPane.OK_OPTION) return;

                    // Actualizar beca
                    beca.setNombre(nombre);
                    beca.setCupos(cupos);
                    beca.setRequisitos(requisitos);
                    ((BecaArancel) beca).setPorcentajeDescuento(porcentaje);
                } else if (beca.getTipo().equals("Manutención")) {
                    int monto;
                    try {
                        monto = Integer.parseInt(montoField.getText().trim());
                        if (monto < 0) {
                            throw new IllegalArgumentException("El monto no puede ser negativo.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Monto debe ser un número entero válido.");
                    }
                    int periodo;
                    try {
                        periodo = Integer.parseInt(periodoField.getText().trim());
                        if (periodo < 0) {
                            throw new IllegalArgumentException("El período no puede ser negativo.");
                        }
                    } catch (NumberFormatException ex) {
                        throw new IllegalArgumentException("Período debe ser un número entero válido.");
                    }
                    // Confirmar cambios
                    String confirmacion = String.format(
                            "Confirmar cambios:\nCódigo: %s\nNombre: %s\nCupos: %d\nRequisitos: %s\nMonto: %d\nPeríodo: %d",
                            codigoOriginal, nombre, cupos, requisitos, monto, periodo);
                    int confirm = JOptionPane.showConfirmDialog(dialog, confirmacion, "Confirmar Cambios", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm != JOptionPane.OK_OPTION) return;

                    // Actualizar beca
                    beca.setNombre(nombre);
                    beca.setCupos(cupos);
                    beca.setRequisitos(requisitos);
                    ((BecaManutencion) beca).setMonto(monto);
                    ((BecaManutencion) beca).setPeriodo(periodo);
                }

                // Guardar en CSV
                DataLoader.guardarBecas("src/resources/becas.csv", maps);
                JOptionPane.showMessageDialog(dialog, "Beca modificada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        dialog.add(btnGuardar);
        dialog.setVisible(true);
    }
}