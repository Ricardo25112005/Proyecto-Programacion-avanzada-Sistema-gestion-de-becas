package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 * @archivo: ProyectoProgramacionAvanzadaSistemaGestionDeBecas.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: archivo main del programa
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 2.0.0
 * @Fecha: 20-08-25
 */

public class ProyectoProgramacionAvanzadaSistemaGestionDeBecas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Crear los objetos base
        Maps maps = new Maps();

        // Cargar datos automáticamente al iniciar (batch)
        DataLoader.cargarEstudiantes("src/resources/estudiantes.csv", maps);
        DataLoader.cargarBecas("src/resources/becas.csv", maps);
        DataLoader.cargarPostulaciones("src/resources/postulaciones.csv", maps);

        // Ejecutar GUI en el hilo de Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            Servicios gui = new Servicios(maps);
            gui.setVisible(true);
        });

        // Registrar hook para guardar datos al cerrar (batch)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            DataLoader.guardarBecas("src/resources/becas.csv",maps);
            DataLoader.guardarEstudiantes("src/resources/estudiantes.csv",maps);
            DataLoader.guardarPostulaciones("src/resources/postulaciones.csv",maps);
        }));
    }
}
