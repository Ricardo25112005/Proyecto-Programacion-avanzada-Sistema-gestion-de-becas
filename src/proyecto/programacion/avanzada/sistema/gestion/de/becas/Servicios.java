/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;
/**
 * @archivo: Servicios.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa al menu del sistema
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 26-08-25
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
    
public class Servicios extends JFrame {
    private Maps maps;
    //Se genera el menú principal del cual se llaman las funciones segun el boton que presione el usuario
    public Servicios(Maps maps) {
        this.maps = maps;
        setTitle("Sistema de Gestión de Becas");
        setSize(700, 600); // Tamaño para formato de lista
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Usar BoxLayout vertical para formato de lista
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Margen

        // Título centrado
        JLabel titulo = new JLabel("Sistema de Gestión de Becas", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titulo);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio después del título

        // Botones en formato lista
        JButton btnRegistrar = new JButton("Registrar Estudiante");
        btnRegistrar.setMaximumSize(new Dimension(300, 40));
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrar.addActionListener(e -> {
            dispose(); // Cierra menú
            maps.createFromTerminal();
            volverAlMenu(); // Reabre menú
        });
        panel.add(btnRegistrar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnRegistrarBeca = new JButton("Registrar Beca");
        btnRegistrarBeca.setMaximumSize(new Dimension(300, 40));
        btnRegistrarBeca.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarBeca.addActionListener(e -> {
            dispose(); // Cierra menú
            maps.registrarBeca(); // Descomentar cuando se implemente
            volverAlMenu(); // Reabre menú
        });
        panel.add(btnRegistrarBeca);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton btnRegistrarPostulacion = new JButton("Registrar Postulacion");
        btnRegistrarPostulacion.setMaximumSize(new Dimension(300, 40));
        btnRegistrarPostulacion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRegistrarPostulacion.addActionListener(e -> {
            dispose(); // Cierra menú
            maps.registrarPostulacion();
            volverAlMenu(); // Reabre menú
        });
        panel.add(btnRegistrarPostulacion);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnMostrarPostulaciones = new JButton("Mostrar Datos Estudiante");
        btnMostrarPostulaciones.setMaximumSize(new Dimension(300, 40));
        btnMostrarPostulaciones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnMostrarPostulaciones.addActionListener(e -> {
            dispose();
            maps.ShowPostulationsEstudiante();
            volverAlMenu();
        });
        // Botón para filtrar postulaciones por estado y tipo de beca
        JButton btnFiltrarPostulaciones = new JButton("Filtrar Postulaciones por Estado/Tipo");
        btnFiltrarPostulaciones.setMaximumSize(new Dimension(300, 40)); // Establece el tamaño máximo del botón
        btnFiltrarPostulaciones.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el botón en el panel
        // Agrega un ActionListener para ejecutar el filtrado y volver al menú principal
        btnFiltrarPostulaciones.addActionListener(e -> {
            dispose(); // Cierra la ventana actual
            maps.filtrarPostulacionesPorEstadoYTipoBeca(); // Llama al método de filtrado en Maps
            volverAlMenu(); // Reabre el menú principal
        });
        panel.add(btnFiltrarPostulaciones); // Añade el botón al panel
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio vertical entre botones
        
        panel.add(btnMostrarPostulaciones);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnBuscar = new JButton("Buscar Postulación Estudiante");
        btnBuscar.setMaximumSize(new Dimension(300, 40));
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscar.addActionListener(e -> {
            dispose();
            buscarPostulacion();
            volverAlMenu();
        });
        panel.add(btnBuscar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton btnBuscarPostBeca = new JButton("Buscar Postulación Beca");
        btnBuscarPostBeca.setMaximumSize(new Dimension(300, 40));
        btnBuscarPostBeca.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscarPostBeca.addActionListener(e -> {
            dispose();
            maps.buscarPostulacionBeca();
            volverAlMenu();
        });
        panel.add(btnBuscarPostBeca);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton btnBuscarBeca = new JButton("Buscar Beca");
        btnBuscarBeca.setMaximumSize(new Dimension(300, 40));
        btnBuscarBeca.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBuscarBeca.addActionListener(e -> {
            dispose();
            maps.buscarBeca();
            volverAlMenu();
        });
        panel.add(btnBuscarBeca);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setMaximumSize(new Dimension(300, 40));
        btnModificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnModificar.addActionListener(e -> {
            dispose(); // Cierra menú
            new ModificarVentana(maps); // Abre ventana de modificación
        });
        panel.add(btnModificar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnEliminarDatos = new JButton("Eliminar Datos");
        btnEliminarDatos.setMaximumSize(new Dimension(300, 40));
        btnEliminarDatos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEliminarDatos.addActionListener(e -> {
            dispose();
            mostrarSubmenuEliminacion();
            volverAlMenu();
        });
        panel.add(btnEliminarDatos);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // En la clase Servicios.java, dentro del constructor, después de los otros botones:

        JButton btnGenerarReporte = new JButton("Generar Reporte TXT");
        btnGenerarReporte.setMaximumSize(new Dimension(300, 40));
        btnGenerarReporte.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGenerarReporte.addActionListener(e -> {
            dispose();
            ReportGenerator.generarReporteDesdeMenu(maps);
            volverAlMenu();
        });
        panel.add(btnGenerarReporte);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JButton btnSalir = new JButton("Salir");
        btnSalir.setMaximumSize(new Dimension(300, 40));
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.addActionListener(e -> System.exit(0));
        panel.add(btnSalir);

        add(panel);
    }

    // Función que vuelve a abrir el menú principal
    private void volverAlMenu() {
        SwingUtilities.invokeLater(() -> {
            new Servicios(maps).setVisible(true);
        });
    }
    //Funcion para Buscar una postulacion de un estudiante
    private void buscarPostulacion() {
        String rut = JOptionPane.showInputDialog(this, "Ingrese RUT del estudiante (formato XX.XXX.XXX-Y):");
        if (rut == null || rut.trim().isEmpty()) return;

        String idPost = JOptionPane.showInputDialog(this, "Ingrese ID de la postulación (dejar vacío para mostrar todas):");

        if (idPost == null || idPost.trim().isEmpty()) {
            maps.buscarPostulacion(rut);
        } else {
            maps.buscarPostulacion(rut, idPost);
        }
    }
    //Funcion que muestra un submenu con las opciones de eliminar Estudiante, beca y postulacion, ademas de ejecutar las funciones
    private void mostrarSubmenuEliminacion() {
        // Crear opciones para el submenú
        String[] opciones = {
            "Eliminar Postulación por RUT y Beca",
            "Eliminar Estudiante",
            "Eliminar Beca",
            "Volver al Menú Principal"
        };

        // Mostrar diálogo de selección
        String seleccion = (String) JOptionPane.showInputDialog(
            this,
            "Seleccione tipo de eliminación:",
            "Submenú - Eliminar Datos",
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );

        // Procesar selección
        if (seleccion != null) {
            switch (seleccion) {
                case "Eliminar Postulación por RUT y Beca":
                    maps.eliminarPostulacionEspecifica();
                    break;
                case "Eliminar Estudiante":
                    maps.eliminarEstudiantePorRUT();
                    break;
                case "Eliminar Beca":
                    maps.eliminarBecaPorID();
                    break;
                case "Volver al Menú Principal":
                    // Simplemente vuelve al menú
                    break;
            }
        }
    }
}