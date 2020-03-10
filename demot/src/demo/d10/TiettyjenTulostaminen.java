package demo.d10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import fi.jyu.mit.ohj2.Syotto;

public class TiettyjenTulostaminen {
	
	public static void main(String[] args) {
		
		String luettavaTied = Syotto.kysy("Mikä on luettavan tiedoston nimi?");
		String tulostaTied = "tulos.txt";
		lue(luettavaTied,tulostaTied);
	}

	private static void lue(String luettavaTied,String tulostaTied) {
		try(BufferedReader fi = new BufferedReader(new FileReader(luettavaTied))){
			String s;
			while ((s = fi.readLine()) != null) {
				try {
					//rivin tarkistus HUOM! 
					//Jos "vain" kaksi tähteä, ei kolmea, 
					//niin lisää välilyöntiehto 
					// (s.charAt(2) == ' ')		
					if(s.length() > 2 ) {
				    	if (s.charAt(0) == '*' && s.charAt(1) == '*'){
					    	kirjoita(tulostaTied, s);
				    	}
					}
				} catch (NumberFormatException ex) {
					// Hylätään
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Tiedosto ei aukee" + ex.getMessage());
			return;
			
		} catch (IOException ex) {
			System.err.println("Virhe tiedostoa luettaessa" + ex.getMessage());
		}
		
	}

	private static void kirjoita(String tulostaTied, String kirjoitettava){
		
			try(BufferedWriter fo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tulostaTied,true), "UTF-8"))){
				fo.write(kirjoitettava + "\n");
			} catch (IOException ex) {
				System.err.println("tiedosto ei aukee" + ex.getMessage());
	}
		
		
	}
}
