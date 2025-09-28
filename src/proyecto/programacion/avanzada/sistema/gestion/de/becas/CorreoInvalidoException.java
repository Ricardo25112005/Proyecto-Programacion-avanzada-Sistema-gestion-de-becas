/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.programacion.avanzada.sistema.gestion.de.becas;

/**
 * @archivo: CorreoInvalidoException.java
 * @Project: Sistema Gestion De Becas
 * @Descripcion: Extencion de una excepcion para capturar errores de formato de correo invalido 
 * @author Daniel Monsalve, Ricardo Paez, Vicente Novoa
 * @Lenguaje: Java
 * @version: 2.0.0
 * @Fecha: 27-09-25
 */
public class CorreoInvalidoException extends Exception{
    public CorreoInvalidoException(String mensaje){super(mensaje);}
}
