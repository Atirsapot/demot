package demo.d9;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Tiedostonkirjoitus {

	public static void main(String[] args) {
		
		String tiedNimi = "tiedosto.txt";
		int toisto = 100;
		String lause = "Tiedosto kirjoitettu puolestasi";
		kirjoita(tiedNimi, toisto, lause);
	}
	
	public static void kirjoita(String tiednimi, int toisto, String lause) {
		try (PrintStream fo = new PrintStream(new FileOutputStream(tiednimi, true))) {
			for(int i = 0; i < toisto; i++) {
            fo.print(lause);
            fo.print('\n');
			}
    } catch (FileNotFoundException ex) {
        System.err.println("Tiedosto ei aukea: " + ex.getMessage());
    }
	}
}
