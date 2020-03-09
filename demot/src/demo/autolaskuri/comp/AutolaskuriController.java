package demo.autolaskuri.comp;

import static fi.jyu.mit.fxgui.Functions.getNodes;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Yksinkertainen autolaskuri, jossa painkikkeita painamalla 
 * voidaan kasvattaa laskureiden arvoja.
 * Laskurit tehty omalle komponentilla.
 * @author vesal
 * @version 6.3.2016
 */
public class AutolaskuriController implements Initializable {
    
    @FXML private Button buttonNollaa;
    private Laskuri.Laskurit laskettavat = new Laskuri.Laskurit();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node parent = buttonNollaa.getParent(); 
        List<Laskuri> laskurit = getNodes(parent, Laskuri.class, n->true, true);
        
        for (Laskuri laskuri: laskurit) {
            laskettavat.add(laskuri); 
        }
    }
        
    
    @FXML void handleNollaa() { laskettavat.reset(); }
}