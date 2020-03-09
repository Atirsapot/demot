package demo.autolaskuri.basic;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Yksinkertainen autolaskuri, jossa painkikkeita painamalla 
 * voidaan kasvattaa laskureiden arvoja
 * @author vesal
 * @version 5.3.2016
 */
public class AutolaskuriController {
    @FXML private Label laskuriHA;
    @FXML private Label laskuriKA;
    @FXML private Label laskuriP;
    private int ha = 0;
    private int ka = 0;
    private int p = 0;

    @FXML void handleHA() {
        laskuriHA.setText("" + ++ha);
    }

    @FXML void handleKA() {
        laskuriKA.setText("" + ++ka);
    }
    @FXML void handleP() {
    	laskuriP.setText("" + ++p);
    }

    @FXML void handleNollaa() {
        laskuriHA.setText("" + (ha=0));
        laskuriKA.setText("" + (ka=0));
    }
}