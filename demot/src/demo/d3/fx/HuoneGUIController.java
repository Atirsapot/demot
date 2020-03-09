package demo.d3.fx;

import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HuoneGUIController {
	
	public static final double OLETUS = 1;

    @FXML
    private TextField textPituus;

    @FXML
    private TextField textLeveys;

    @FXML
    private TextField textKorkeus;

    @FXML
    private TextField textPintaala;

    @FXML
    private TextField textTilavuus;

    @FXML
    void laske() {
    	double pintaala = 0;
    	double tilavuus = 0;
    	
    	pintaala = (haeLuku(textPituus,OLETUS)) * ((haeLuku(textLeveys,OLETUS)));
    	laitaTulos(textPintaala,pintaala);
    	
    	tilavuus = (pintaala * haeLuku(textKorkeus,OLETUS));
    	laitaTulos(textTilavuus,tilavuus);
    }
    
    public static double haeLuku(TextField text,double oletus) {
        double luku = Mjonot.erotaDouble(text.getText(), oletus);
        return luku;
    }


    /**
     * Laittaa tekstikenttään tuloksen
     * @param text kenttä johon luku laitetaan
     * @param luku joka laitetaan kenttään
     */
    public static void laitaTulos(TextField text, double luku) {
        String tulos = String.format("%5.2f",luku);
        tulos = tulos.replace(',', '.');
        text.setText(tulos);
    }


    
}
