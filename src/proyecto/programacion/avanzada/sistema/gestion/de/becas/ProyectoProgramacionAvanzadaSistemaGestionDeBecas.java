package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 * @archivo: ProyectoProgramacionAvanzadaSistemaGestionDeBecas.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: archivo main del programa
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 1.0.0
 * @Fecha: 20-08-25
 */

public class ProyectoProgramacionAvanzadaSistemaGestionDeBecas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Servicios servicios = new Servicios();
        Maps maps = new Maps();
        while (true) {
            System.out.println("\nSistema Gestión Becas");
            System.out.println("=====================");
            Servicios.mostrarMenuAdministrador();
            servicios.leerArgumentos(maps);
        }
    }
}
