package fxAanestysRB;


import fi.jyu.mit.fxgui.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * PÃ¤Ã¤ohjelma Ã¤Ã¤nestykselle.  Tehty pelkÃ¤llÃ¤ Javalla
 * ja FXGuin komponentilla.
 * @author vesal
 * @version 7.2.2016
 *
 */
public class AanestysMain5 extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            Pane root = luoNaytto();
            Scene scene = new Scene(root);
            // scene.getStylesheets().add(getClass().getResource("aanestys.css").toExternalForm());
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


    private Pane luoNaytto() {
        BorderPane pane = new BorderPane();
        Label labelAihe = new Label("Oletko?");
        BorderPane.setAlignment(labelAihe, Pos.CENTER);
        Font font = new Font("Arial Bold", 18);
        labelAihe.setFont(font);
        labelAihe.setPadding(new Insets(10, 10, 0, 10));
        pane.setTop(labelAihe);

        Button buttonAanesta = new Button("Äänestä"); 
        BorderPane.setAlignment(buttonAanesta, Pos.CENTER);
        BorderPane.setMargin(buttonAanesta, new Insets(0, 0, 20, 0));
        pane.setBottom(buttonAanesta);
        buttonAanesta.setDefaultButton(true);

        VBox vbox = new VBox();
        pane.setCenter(vbox);
        BorderPane.setMargin(vbox, new Insets(20, 0, 0, 0));

        labelValittu = new Label("Valittu: KyllÃ¤");
        labelValittu.setMaxWidth(1000);
        labelValittu.setAlignment(Pos.CENTER);
        vbox.getChildren().add(labelValittu);
        
        ScrollBorder sb = new ScrollBorder();
        vbox.getChildren().add(sb);
        valinta = new RadioButtonChooser<String>();
        // valinta = new ListChooser<String>();
        //valinta = new CheckBoxChooser<String>();
        // valinta = new ComboBoxChooser<String>();
        sb.getChildren().add((Node)valinta);
        
        valinta.setRivit(new String[] {"_Mies", "_Nainen", "_Tyttö", "_Poika"});
        valinta.setSelectedIndex(0);

        buttonAanesta.setOnAction( (e) -> handleAanesta() );
        
        initialize();
        return pane;
    }

    private Chooser<String> valinta;
    private Label labelValittu;

    void handleAanesta() {
        aanesta();
    }


    private void initialize() {
        valinta.addSelectionListener((e) -> nayta());
        labelValittu.setText("");
    }


    private void nayta() {
        String s = valinta.getSelectedText();
        if ( s == null ) return;
        labelValittu.setText("Valittu: " + s);
    }
    public int[] tulokset = {0,0,0,0};

    private void aanesta() {
        String kohde =  valinta.getSelectedText();
        int i = valinta.getSelectedIndex();
        tulokset[i] += 1;
        Dialogs.showMessageDialog("Olet siis: " + kohde + "\n" + "Mies: " + tulokset[0] + "\n" + "Nainen: " + tulokset[1] + "\n" + "Tyttö: " + tulokset[2] + "\n" + "Poika: " + tulokset[3]);
    }
}