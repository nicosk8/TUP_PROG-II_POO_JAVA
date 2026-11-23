/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package registroproductosserializado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal: abre la ventana cargando la vista Vista.fxml.
 *
 * @author Nicole Denise Heck - Programacion II - Div. 323
 */
public class RegistroProductosSerializado extends Application{


       /**
     * Arma la ventana: carga la vista FXML, la mete en una Scene y la muestra en el Stage.
     *
     * @param stage ventana principal que entrega JavaFX
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @Override
    public void start(Stage stage) {
        try {
            
            Parent root = FXMLLoader.load(getClass().getResource("/views/views.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Registro de Productos");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("No se pudo cargar la vista: " + e.getMessage());
        }
    }

    /**
     * Punto de entrada: lanza la aplicacion JavaFX.
     *
     * @param args argumentos de consola
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    public static void main(String[] args) {
        launch(args);
    }
}   