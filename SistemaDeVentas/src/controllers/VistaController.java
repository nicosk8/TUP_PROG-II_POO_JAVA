package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sistemadeventas.ArchivoTicket;
import sistemadeventas.Carrito;
import sistemadeventas.ItemCarrito;
import sistemadeventas.OperacionInvalidaException;
import sistemadeventas.Producto;

public class VistaController {

    @FXML
    private TableView<Producto> tblProductos;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colStock;
    @FXML
    private TableView<ItemCarrito> tblCarrito;
    @FXML
    private TableColumn<ItemCarrito, String> colItemNombre;
    @FXML
    private TableColumn<ItemCarrito, Double> colItemPrecio;
    @FXML
    private TableColumn<ItemCarrito, Integer> colItemCantidad;
    @FXML
    private TableColumn<ItemCarrito, Double> colItemSubtotal;
    @FXML
    private Label lblTotal;

    private Carrito carrito;
    private ArchivoTicket archivoTicket;

    /**
     * Prepara las columnas, el carrito y los productos al abrir la ventana.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @FXML
    public void initialize() {
        carrito = new Carrito();
        archivoTicket = new ArchivoTicket();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        colItemNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colItemPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colItemCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colItemSubtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));

        cargarProductos();
        actualizarPantalla();
    }

    /**
     * Carga los productos iniciales en la tabla.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private void cargarProductos() {
        try {
            tblProductos.getItems().add(new Producto("Teclado", 15000, 5));
            tblProductos.getItems().add(new Producto("Mouse", 8000, 10));
            tblProductos.getItems().add(new Producto("Monitor", 120000, 1));
        } catch (OperacionInvalidaException e) {
            mostrarAlerta(Alert.AlertType.ERROR, e.getMessage());
        }
    }

    /**
     * Agrega al carrito el producto seleccionado.
     *
     * @param event clic en el boton Agregar al carrito
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @FXML
    private void handleAgregar(ActionEvent event) {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem();

        try {
            carrito.agregarProducto(seleccionado);
            actualizarPantalla();
        } catch (OperacionInvalidaException e) {
            mostrarAlerta(Alert.AlertType.WARNING, e.getMessage());
        }
    }

    /**
     * Guarda el ticket y vacia el carrito solo si se pudo guardar.
     *
     * @param event clic en el boton Confirmar compra
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @FXML
    private void handleConfirmar(ActionEvent event) {
        if (carrito.estaVacio()) {
            mostrarAlerta(Alert.AlertType.WARNING, "El carrito esta vacio.");
            return;
        }

        try {
            archivoTicket.guardar(carrito);
            carrito.vaciar();
            actualizarPantalla();
            mostrarAlerta(Alert.AlertType.INFORMATION, "Compra confirmada. Se guardo ticket.txt.");
        } catch (IOException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "No se pudo guardar el ticket: " + e.getMessage());
        }
    }

    /**
     * Actualiza las dos tablas y el total.
     *
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private void actualizarPantalla() {
        tblCarrito.getItems().clear();
        for (ItemCarrito item : carrito.getItems()) {
            tblCarrito.getItems().add(item);
        }
        tblCarrito.refresh();
        tblProductos.refresh();
        lblTotal.setText("$ " + carrito.calcularTotal());
    }

    /**
     * Muestra un mensaje al usuario.
     *
     * @param tipo tipo de alerta
     * @param mensaje texto a mostrar
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    private void mostrarAlerta(Alert.AlertType tipo, String mensaje) {
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
