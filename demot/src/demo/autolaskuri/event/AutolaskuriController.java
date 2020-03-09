package demo.autolaskuri.event;

import static fi.jyu.mit.fxgui.Functions.getNode;
import static fi.jyu.mit.fxgui.Functions.getNodes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

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
    @SuppressWarnings("javadoc")
    public static class Laskettava extends SimpleIntegerProperty {
        public Laskettava(int value) { super(value); }
        public int inc() { set(get()+1); return get(); }
        public int reset() { set(0); return get(); }
    }
    
    @SuppressWarnings("javadoc")
    public static class Laskettavat {
        private List<Laskettava> alkiot = new ArrayList<>();
        public void add(Laskettava alkio) { alkiot.add(alkio); }
        public void reset() { alkiot.forEach(l -> l.reset()); }
    }
    
    
    @FXML private Button buttonNollaa;
    private Laskettavat laskettavat = new Laskettavat();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node parent = buttonNollaa.getParent(); 
        List<Label> laskurit = getNodes(parent, Label.class, 
                                n -> n.getStyleClass().contains("laskuri"), true);
        
        for (Label laskuri: laskurit) {
            String id = laskuri.getId();
            if ( id == null || id.length() < 1 ) continue;
            Laskettava laskettava = new Laskettava(0);
            laskuri.textProperty().bind(laskettava.asString());
            laskettavat.add(laskettava); 
            laskuri.setOnMouseClicked(e -> laskettava.inc());
            laskuri.setOnTouchPressed(e -> laskettava.inc());
            Button button = getNode(parent, Button.class, n -> id.equals(n.getId()), true);
            if ( button != null ) button.setOnAction(e -> laskettava.inc());
            ProgressBar bar = getNode(parent, ProgressBar.class, n -> id.equals(n.getId()), true);
            if ( bar != null ) bar.progressProperty().bind(laskettava.divide(20.0));
        }
    }
        
    
    @FXML void handleNollaa() { laskettavat.reset(); }
}