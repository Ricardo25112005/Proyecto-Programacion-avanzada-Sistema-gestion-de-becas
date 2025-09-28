/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 * @archivo: BecaArancel.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Clase que representa a una BecaArancel (Subclase de Beca)
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 2.0.0
 * @Fecha: 27-09-25
 */
public class BecaArancel extends Beca {
    //Atributos
    private float porcentajeDescuento;
    
    //Constructor
    public BecaArancel(String codeBeca, String nomBeca, int cupos, String requisitos, float porcentajeDescuento){
        super(codeBeca, nomBeca, cupos, requisitos);
        this.porcentajeDescuento = porcentajeDescuento;
    }
    //sobreescritura del metodo en la clase beca, para mostrar todos los elementos de este tipo de beca
    @Override
    public void mostrarBeca(){
        String mensaje = "=== Beca de Arancel ===\n"
            + "Código: " + getCodigo() + "\n"
            + "Nombre: " + getNombre() + "\n"
            + "Cupos: " + getCupos() + "\n"
            + "Requisitos: " + getRequisitos() + "\n"
            + "Porcentaje de Descuento: " + porcentajeDescuento + "%";
        javax.swing.JOptionPane.showMessageDialog(null, mensaje, "Información de la Beca", javax.swing.JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public String getTipo(){return "Arancel";}
    
    //Getter y Setters
    public float getPorcentajeDescuento() { return porcentajeDescuento; }
    public void setPorcentajeDescuento(float porcentajeDescuento) { this.porcentajeDescuento = porcentajeDescuento; }
}
