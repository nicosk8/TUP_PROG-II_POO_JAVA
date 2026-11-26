
package sistemadeventas;


public class ItemCarrito {

    private Producto producto;
    private int cantidad;

    /**
     * Crea una linea del carrito con una unidad.
     *
     * @param producto producto seleccionado
     * @throws OperacionInvalidaException si el producto es nulo
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public ItemCarrito(Producto producto) throws OperacionInvalidaException {
        if (producto == null) {
            throw new OperacionInvalidaException("Debe indicar un producto.");
        }
        this.producto = producto;
        this.cantidad = 1;
    }

    /**
     * Suma una unidad a la linea.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public void sumarUnidad() {
        cantidad++;
    }

    /**
     * Devuelve el producto de la linea.
     *
     * @return producto
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Devuelve el nombre del producto.
     *
     * @return nombre
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public String getNombre() {
        return producto.getNombre();
    }

    /**
     * Devuelve el precio unitario.
     *
     * @return precio
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public double getPrecio() {
        return producto.getPrecio();
    }

    /**
     * Devuelve las unidades de la linea.
     *
     * @return cantidad
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Calcula el importe de la linea.
     *
     * @return precio por cantidad
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
