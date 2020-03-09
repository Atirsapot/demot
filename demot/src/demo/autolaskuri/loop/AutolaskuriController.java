package demo.autolaskuri.loop;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static fi.jyu.mit.fxgui.Functions.*;

/**
 * Yksinkertainen autolaskuri, jossa painkikkeita painamalla 
 * voidaan kasvattaa laskureiden arvoja.
 * Laskurit ja painikkeet tunnistetaan sill�, ett� niill� on sama id.
 * Laskurit tunnistetaan sill� ett� niiss� on luokka "laskuri".
 * Laskureita etsit��n samasta alueesta kuin buttonNollaa on.
 * @author vesal
 * @version 6.3.2016
 */
public class AutolaskuriController implements Initializable {
    
    @FXML private Button buttonNollaa;
    private Map<String,SimpleIntegerProperty> laskettavat = new HashMap<>();
    private List<Label> laskurit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        laskurit = getNodes(buttonNollaa.getParent(), Label.class, n -> n.getStyleClass().contains("laskuri"), true);
        
        for (Label laskuri: laskurit) {
            SimpleIntegerProperty laskettava = new SimpleIntegerProperty(0);
            laskuri.textProperty().bind(laskettava.asString());
            laskettavat.put(laskuri.getId(),laskettava); 
        }
    }
        
    
    @FXML void handleLaske(ActionEvent event) {
        Node source = (Node)event.getSource();
        String id = source.getId();
        if ( id == null || id.length() < 1 ) return;
        SimpleIntegerProperty laskettava = laskettavat.get(id);
        if ( laskettava == null ) return;
        laskettava.set(laskettava.get()+1);
    }


    @FXML void handleNollaa() {
        for ( SimpleIntegerProperty laskettava: laskettavat.values())
            laskettava.set(0);
    }
       
}