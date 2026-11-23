/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package registroproductosserializado;

/**
 *
 * @author nicol
 */
public class Validaciones {
     

    /**
     * Indica si un texto esta vacio o tiene solo espacios.
     *
     * @param texto texto a revisar
     * @return true si no tiene ningun caracter distinto de espacio
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public boolean esTextoVacio(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Indica si un texto es un precio valido: solo digitos y a lo sumo un punto,
     * con al menos un digito, y un valor mayor a 0.
     *
     * @param texto texto ingresado por el usuario
     * @return true si es un precio valido
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public boolean esPrecioValido(String texto) {
        int puntos = 0;
        int digitos = 0;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '.') {
                puntos++;
            } else if (Character.isDigit(c)) {
                digitos++;
            } else {
                return false;
            }
        }
        if (puntos > 1 || digitos == 0) {
            return false;
        }
        try {
            double numero = Double.parseDouble(texto);
            return numero > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
