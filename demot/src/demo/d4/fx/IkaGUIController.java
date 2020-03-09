package demo.d4.fx;

import fi.jyu.mit.ohj2.Mjonot;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class IkaGUIController {
	
	public static final double OLETUS = 2020;
    @FXML
    private TextField textSV;

    @FXML
    private TextField textIka;
    @FXML
    void laske() {
    	
    	double syntymavuosi = 0;
    	double ika = 0;
    	
    	
    	syntymavuosi = (haeLuku(textSV, OLETUS));
    	ika =(2020-syntymavuosi);
    	
    	laitaTulos(textIka,ika);
    }

    
    
    public static double haeLuku(TextField text,double oletus) {
        double luku = Mjonot.erotaDouble(text.getText(), oletus);
        return luku;
    }
    
    public static void laitaTulos(TextField text, double luku) {
        String tulos = String.format("%5.2f",luku);
        tulos = tulos.replace(',', '.');
        text.setText(tulos);
    }
    
}
