/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controllers;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import registroproductosserializado.Producto;
import registroproductosserializado.Categoria;
import registroproductosserializado.Validaciones;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author nicol
 */
public class ViewsController implements Initializable {
    
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPrecio;
    @FXML
    private ComboBox<Categoria> cmbCategoria; // OJO!!: SIN EL TIPADO <Categoria> ME DABA ERROR getItems().addAll(Categoria.values()); (Linea 58)
    @FXML
    private Button btnEliminar;
    @FXML
    private TableView<Producto> tblProductos;    
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Categoria> colCategoria;
    @FXML
    private Button btnAgregar;
    private List<Producto> productos = new ArrayList<>();
    private Validaciones validaciones = new Validaciones();
    
    /**
     * Initializes the controller class.
     * / **

    * Se ejecuta al abrir la ventana: vincula las columnas con los atributos de Producto,

    * carga las categorias en el ComboBox y lee el archivo si existe.

    * @param url ubicacion del FXML (lo entrega JavaFX)
    * @param rb recursos de la vista (lo entrega JavaFX)
    * @author Nicole Denise Heck - Programacion II - Div. 323
    */
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // aca cargo mi archivo serializado -> cargarArchivo();
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        cmbCategoria.getItems().addAll(Categoria.values());
        cargarArchivo();          
    }
/*--------------------------- Inicio Seccion Metodos Auxiliares -------------------------------------------------------*/        
    /**
    * Arma una ventanita emergente: tipo informativo
    *  @param mensaje  el texto a mostrar 
    */
    public void mostrarMensaje(String mensaje){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Aviso");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait(); // muestra y pausa el codigo hasta que el usuario la cierra
    }
    
    /**
    * Actualiza la tabla de productos
    *  se vacia y se vuelve a llenar con el contenido de productos 
    */
    public void actualizarTabla(){
        tblProductos.getItems().clear();
        tblProductos.getItems().addAll(productos);
    }
    
    /**
    * Limpia el formulario de productos
    *  borra los dos campos de texto y deja el ComboBox sin seleccion
    */
    public void limpiarFormulario(){
        txtNombre.clear();
        txtPrecio.clear();
        cmbCategoria.setValue(null);
    }
      /**
     * Valida los datos del formulario.
     *
     * @return texto vacio si todo es correcto, o el mensaje del primer error encontrado
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public String validarDatos() {
        if (validaciones.esTextoVacio(txtNombre.getText())) {
            return "El nombre no puede estar vacio.";
        }
        if (!validaciones.esPrecioValido(txtPrecio.getText())) {
            return "El precio debe ser un numero mayor a 0 (use punto para los decimales, sin espacios).";
        }
        if (cmbCategoria.getValue() == null) {
            return "Debe seleccionar una categoria.";
        }
        return "";
    }
/*--------------------------- Inicio Seccion Archivos -------------------------------------------------------*/    
    @SuppressWarnings("unchecked")
    public void cargarArchivo() {
        File archivo = new File("productos.dat");
        if(!archivo.exists()){
            return;
        }
        try(ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))){
            productos = (List<Producto>) entrada.readObject();
        } catch (IOException e){
            mostrarMensaje("No se puedo leer el archivo: " + e.getMessage());
        } catch(ClassNotFoundException e){
            mostrarMensaje("El archivo tiene un formato desconocido");
        }
        actualizarTabla();
        
    }
    
    /**
    * Guarda la lista completa de productos (serializada) en productos.dat
    *  
    */
    public void guardarArchivo() {
        File archivo = new File("productos.dat");
        try(ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))){
        
            salida.writeObject(productos);
        } catch (IOException e){
            mostrarMensaje("No se puedo guardar el archivo: " + e.getMessage());
        }
    }
/*--------------------------- Inicio Seccion ABM -------------------------------------------------------*/    
    
    /*
    * Boton Agregar: valida los datos, crea el producto, lo guarda en el archivo
    * y actualiza la tabla. Si hay un error muestra el mensaje y no agrega nada.

    *

    * @param event evento del boton (lo entrega JavaFX)
    @author Nicole Denise Heck - Programacion II - Div. 323
    */
    @FXML
    private void agregar(ActionEvent event){
        String error = validarDatos();
        if(!error.equals("")){
            mostrarMensaje(error);
            return;
        }
        
        String nombre = txtNombre.getText();
        double precio = Double.parseDouble(txtPrecio.getText());
        Categoria categoria = cmbCategoria.getValue();
        
        productos.add(new Producto(nombre,precio,categoria)); // constructor - Alta de producto
        guardarArchivo();
        actualizarTabla();
        limpiarFormulario();       
    }
   
    /**
     * Boton Eliminar: borra el producto seleccionado en la tabla, guarda el archivo
     * y actualiza la tabla. Si no hay nada seleccionado avisa al usuario.
     *
     * @param event evento del boton (lo entrega JavaFX)
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @FXML
    private void eliminar(ActionEvent event) {
        Producto seleccionado = tblProductos.getSelectionModel().getSelectedItem(); // obtiene el item seleccionado con el cursor
        if (seleccionado == null) {
            mostrarMensaje("Debe seleccionar un producto de la tabla para eliminarlo.");
            return;
        }
        productos.remove(seleccionado);
        guardarArchivo();
        actualizarTabla();
    }
    
    
}
