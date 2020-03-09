package demo.d4.fx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author topiasrita
 * @version 27.1.2020
 *
 */
public class IkaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("IkaGUIView.fxml"));
            final Pane root = ldr.load();
            //final IkaGUIController ikaCtrl = (IkaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("ika.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ika");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}