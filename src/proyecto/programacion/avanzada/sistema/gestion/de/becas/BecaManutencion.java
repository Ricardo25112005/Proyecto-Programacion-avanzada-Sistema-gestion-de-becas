/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 * @archivo: BecaManutencion.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa a una BecaManutención (Subclase de Beca)
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 2.0.0
 * @Fecha: 27-09-25
 */
public class BecaManutencion extends Beca{
    //Atributos
    private int monto;
    private int periodo;
    
    //Constructor
    public BecaManutencion(String codeBeca, String nomBeca, int cupos, String requisitos, int monto, int periodo){
        super(codeBeca, nomBeca, cupos, requisitos);
        this.monto = monto;
        this.periodo = periodo;
    }
    //sobreescritura de la funcion en la clase beca, para mostrar todos los elementos de este tipo de beca
    @Override
    public void mostrarBeca(){
        String mensaje = "=== Beca de Manuntención ===\n"
            + "Código: " + getCodigo() + "\n"
            + "Nombre: " + getNombre() + "\n"
            + "Cupos: " + getCupos() + "\n"
            + "Requisitos: " + getRequisitos() + "\n"
            + "Monto Manutención: " + monto + "\n"
            + "Cantidad de Meses: " + periodo;
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Información de la Beca", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    @Override
    public String getTipo(){return "Manutención";}
    
    //Getters y Setters
    public int getMonto() { return monto; }
    public void setMonto(int monto) { this.monto = monto; }
    public int getPeriodo() { return periodo; }
    public void setPeriodo(int periodo) { this.periodo = periodo; }
}
