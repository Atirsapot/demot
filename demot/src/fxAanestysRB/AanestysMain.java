package fxAanestysRB;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/**
 * PÃ¤Ã¤ohjelma Ã¤Ã¤nestykselle. KÃ¤ytetÃ¤Ã¤n RadioButtonChooser-komponenttia 
 * @author vesal
 * @version 31.1.2016
 *
 */
public class AanestysMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AanestysGUIView.fxml"));
			Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("aanestys.css").toExternalForm());
			primaryStage.setScene(scene);
            primaryStage.setTitle("Äänestys");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
     * KÃ¤ynnistetÃ¤Ã¤n Ã¤Ã¤nestys
     * @param args ei kÃ¤ytÃ¶ssÃ¤
	 */
	public static void main(String[] args) {
		launch(args);
	}
}