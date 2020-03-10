package demo.d10;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Esiintymat {

	public static void main(String[] args) {
		char[] aakkoset = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'x', 'y', 'z' };
		int[] maara = new int[aakkoset.length];

		String luettavaTied = args[0];
		lue(luettavaTied, aakkoset, maara);
		tulosta(maara, aakkoset);
	}
	
	private static void lue(String luettavaTied, char[] aakkoset, int[] maara) {
		try (BufferedReader fi = new BufferedReader(new FileReader(luettavaTied))) {
			String s;
			while ((s = fi.readLine()) != null) {
				try {
					for (int i = 0; i < s.length(); i++) {
						for (int j = 0; j < aakkoset.length; j++) {
							char l = s.charAt(i);
							l = Character.toLowerCase(l);
							if (l == aakkoset[j]) {
								maara[j]++;
							}
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
	
	public static void tulosta(int[]maara, char[]aakkoset) {
		System.out.println("Tiedon lauseessa seuraavia kirjaimia: ");
		for (int i = 0; i <= maara.length; i++) {
			if(maara[i] != 0 ) {
				System.out.println(aakkoset[i] + " " + maara[i] + "kpl" + "\n");
			}
		}
	}

}
