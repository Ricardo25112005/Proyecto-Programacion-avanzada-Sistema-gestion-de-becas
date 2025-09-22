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

    public Servicios(Maps maps) {
        this.maps = maps;
        setTitle("Sistema de Gestión de Becas");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        JLabel titulo = new JLabel("Menú Principal", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titulo);

        JButton btnRegistrar = new JButton("Registrar Estudiante");
        btnRegistrar.addActionListener(e -> {
            dispose(); // Cierra menú
            maps.createFromTerminal();
            volverAlMenu(); // Reabre menú
        });
        panel.add(btnRegistrar);
        
        JButton btnRegistrarBeca = new JButton("Registrar Beca");
        btnRegistrarBeca.addActionListener(e -> {
            dispose(); // Cierra menú
            //maps.RegistrarBeca();
            volverAlMenu(); // Reabre menú
        });
        panel.add(btnRegistrarBeca);

        JButton btnMostrarPostulaciones = new JButton("Mostrar Datos Estudiante");
        btnMostrarPostulaciones.addActionListener(e -> {
            dispose();
            maps.ShowPostulationsEstudiante();
            volverAlMenu();
        });
        panel.add(btnMostrarPostulaciones);

        JButton btnBuscar = new JButton("Buscar Postulación");
        btnBuscar.addActionListener(e -> {
            dispose();
            buscarPostulacion();
            volverAlMenu();
        });
        panel.add(btnBuscar);

        JButton btnEliminarDatos = new JButton("Eliminar Datos");
        btnEliminarDatos.addActionListener(e -> {
            dispose();
            mostrarSubmenuEliminacion();
            volverAlMenu();
        });
        panel.add(btnEliminarDatos);

        JButton btnSalir = new JButton("Salir");
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

    private void buscarPostulacion() {
        String rut = JOptionPane.showInputDialog("Ingrese RUT del estudiante:");
        if (rut == null || rut.trim().isEmpty()) return;

        String idPost = JOptionPane.showInputDialog("Ingrese ID de la postulación (dejar vacío para mostrar todas):");

        if (idPost == null || idPost.trim().isEmpty()) {
            maps.buscarPostulacion(rut);
        } else {
            maps.buscarPostulacion(rut, idPost);
        }
    }
    
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
            null,
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