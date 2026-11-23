/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registroproductosserializado;
import java.io.Serializable;
/**
 *
 * @author nicol
 */
public class Producto implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private String nombre;
    private double precio;
    private Categoria categoria;
    
    
    public Producto(String nombre, double precio, Categoria categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return super.toString(); 
    }
    
    
}
