
package sistemadeventas;

public class Producto {

    private String nombre;
    private double precio;
    private int stock;

    /**
     * Crea un producto validando sus datos.
     *
     * @param nombre nombre del producto
     * @param precio precio unitario, mayor a 0
     * @param stock unidades disponibles, 0 o mas
     * @throws OperacionInvalidaException si algun dato es invalido
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public Producto(String nombre, double precio, int stock) throws OperacionInvalidaException {
        if (esTextoVacio(nombre)) {
            throw new OperacionInvalidaException("El nombre no puede estar vacio.");
        }
        if (precio <= 0) {
            throw new OperacionInvalidaException("El precio debe ser mayor a 0.");
        }
        if (stock < 0) {
            throw new OperacionInvalidaException("El stock no puede ser negativo.");
        }
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    /**
     * Indica si un texto es nulo, no tiene caracteres o solo tiene espacios.
     *
     * @param texto texto a revisar
     * @return true si esta vacio
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private boolean esTextoVacio(String texto) {
        if (texto == null) {
            return true;
        }
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Descuenta una unidad del stock si hay disponibles.
     *
     * @throws OperacionInvalidaException si no hay stock
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public void descontarUnidad() throws OperacionInvalidaException {
        if (stock <= 0) {
            throw new OperacionInvalidaException("No hay stock de " + nombre + ".");
        }
        stock--;
    }

    /**
     * Devuelve el nombre.
     *
     * @return nombre del producto
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio.
     *
     * @return precio unitario
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Devuelve el stock.
     *
     * @return unidades disponibles
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public int getStock() {
        return stock;
    }
}
