package demo.autolaskuri.comp;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Laskuri-komponentti
 * @author vesal
 * @version 6.3.2016
 *
 */
public class Laskuri  extends VBox implements Initializable {
    @SuppressWarnings("javadoc")
    public static class Laskettava extends SimpleIntegerProperty {
        public Laskettava(int value) { super(value); }
        public int inc() { set(get()+1); return get(); }
        public int reset() { set(0); return get(); }
    }
    
    @SuppressWarnings("javadoc")
    public static class Laskurit {
        private List<Laskuri> alkiot = new ArrayList<>();
        public void add(Laskuri alkio) { alkiot.add(alkio); }
        public void reset() { alkiot.forEach(l -> l.reset()); }
    }
    
    @FXML private Label laskuri;
    @FXML private Button button;
    
    private Laskettava laskettava = new Laskettava(0);
    
    /** Luodaan laskuri */
    public Laskuri() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LaskuriView.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }           
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        laskuri.textProperty().bind(laskettava.asString());
        laskuri.setOnMouseClicked(e -> laskettava.inc());
        laskuri.setOnTouchPressed(e -> laskettava.inc());
        button.setOnAction(e -> laskettava.inc());
    }

    
    /** Nollataan laskuri */
    public void reset() { laskettava.reset(); }
    
    /** @return painikkeen teksti */
    public String getCaption() { return button.getText(); }
    
    /** @param caption painikkeelle asetettava teksti */
    public void setCaption(String caption) { button.setText(caption); }
}