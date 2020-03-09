package fxAanestysRB;


import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.Chooser;
import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


/**
 * Kontrolleri Ã¯Â¿Å“Ã¯Â¿Å“nestykselle
 * @author vesal
 * @version 4.2.2017
 *
 */
public class AanestysGUIController implements Initializable  {
    @FXML private Label labelValittu;
    @FXML private Chooser<String> valinta;

    @FXML void handleAanesta() { aanesta(); }

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        valinta.addSelectionListener((e) -> nayta());
        labelValittu.setText("");
    }
    //					mies,nainen,tyttö,poika
    public int[] tulokset = {0,0,0,0};
    
    private void nayta() {
        String s = valinta.getSelectedText();
        if ( s == null ) return;
        labelValittu.setText("Valittu: " + s);
    }


    private void aanesta() {
        String kohde = valinta.getSelectedText();
        int i = valinta.getSelectedIndex();
        tulokset[i] += 1;
        
        Dialogs.showMessageDialog("Olet siis: " + kohde + "\n" + "Mies: " + tulokset[0] + "\n" + "Nainen: " + tulokset[1] + "\n" + "Tyttö: " + tulokset[2] + "\n" + "Poika: " + tulokset[3]);
     }

}