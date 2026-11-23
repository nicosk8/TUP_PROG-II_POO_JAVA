/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package registroproductosserializado;

/**
 *
 * @author nicol
 */
public enum Categoria {
    ALIMENTOS("Alimentos"),
    BEBIDAS("Bebidas"),
    LIMPIEZA("Limpieza"),
    ELECTRONICA("Electronica"),
    OTROS("Otros");
    
    private String categoria;
    
    Categoria(String categoria) {
        this.categoria = categoria;
    }
    
    public String getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return categoria;
    }
    
    
}
