package demo.autolaskuri.bind;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * Autolaskuri
 * @author vesal
 * @version 5.3.2016
 */
public class AutolaskuriMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("AutolaskuriView.fxml"));
            final Pane root = ldr.load();
            //final AutolaskuriController autolaskuriCtrl = (AutolaskuriController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("autolaskuri.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Autolaskuri");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}