package sistemadeventas;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Clase principal: abre la ventana cargando la vista "vista.fxml"
 *
 * @author Nicole Denise Heck - Programacion II - Div. 323
 */
public class SistemaDeVentas extends Application{
       /**
     * Arma la ventana: carga la vista FXML, la mete en una Scene y la muestra en el Stage.
     *
     * @param stage ventana principal que entrega JavaFX
     * @author Nicole Denise Heck - Programacion II - Div. 323
     */
    @Override
    public void start(Stage stage) {
        try {
            
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/views/vista.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("Sistema de ventas");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("No se pudo cargar la vista: " + e.getMessage());
            e.printStackTrace();
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