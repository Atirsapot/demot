package demo.autolaskuri.bind;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * Yksinkertainen autolaskuri, jossa painkikkeita painamalla 
 * voidaan kasvattaa laskureiden arvoja
 * @author vesal
 * @version 5.3.2016
 */
public class AutolaskuriController implements Initializable {
    @FXML private Label laskuriHA;
    @FXML private Label laskuriKA;
    private SimpleIntegerProperty ha = new SimpleIntegerProperty(0);
    private SimpleIntegerProperty ka = new SimpleIntegerProperty(0);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        laskuriHA.textProperty().bind(ha.asString());
        laskuriKA.textProperty().bind(ka.asString());
    }
        
    @FXML void handleHA() {
        ha.set(ha.get()+1);
    }

    @FXML void handleKA() {
        ka.set(ka.get()+1);
    }

    @FXML void handleNollaa() {
        ha.set(0);
        ka.set(0);
    }
       
}