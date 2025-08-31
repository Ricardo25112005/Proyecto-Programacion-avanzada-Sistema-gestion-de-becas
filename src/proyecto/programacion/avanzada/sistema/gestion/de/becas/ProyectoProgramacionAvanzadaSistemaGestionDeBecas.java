package proyecto.programacion.avanzada.sistema.gestion.de.becas;

import java.util.HashMap;
import java.util.Map;

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
