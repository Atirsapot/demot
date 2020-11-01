package demo.d11;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fi.jyu.mit.ohj2.Mjonot;

public class ValuutatTXT {
	private static final double oletus = 0;
	public static Raha[] valuutat;
	public static double rahamaara = 0;
	public static String valuutta = "";
	public static boolean tee = true;
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		String luettavaTied = args[0];
		Raha[] valuutat = new Raha[4];
		alustaRahat(luettavaTied);
		String s = kysyValuutta();
		jonoArvoiksi(s);
		double summa = 0;
		summa = vaihdaValuutta(summa, rahamaara, valuutta, valuutat);
		System.out.println(rahamaara + " " + valuutta + " on " + summa + " mk.");
	}
	
	private static void jonoArvoiksi(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		String maara = Mjonot.erota(sb);
	    rahamaara = Double.parseDouble(maara);
	    valuutta = sb.toString();
		
		
	}

	public static class Raha {
		public final String nimi;
		public final double arvo;

		public Raha(String nimi, double arvo) {
			this.nimi = nimi;
			this.arvo = arvo;
		}
	}

	/**
	 * @example
	 * 
	 *          <pre name="test">
	 * #STATICIMPORT 
	 * 
	 * rahamaara = 100;
	 * valuutta = "$";
	 * double summa = 0;
	 * Raha[] valuutat = new Raha [4];
	 * alustaRahat(valuutat);
	 * vaihdaValuutta(summa,rahamaara,valuutta,valuutat) ~~~ 570.0;
	 *
	 * rahamaara = 35;
	 * valuutta = "SKr";
	 * vaihdaValuutta(summa,rahamaara,valuutta,valuutat) ~~~ 21.0;
	 * 
	 *          </pre>
	 */
	public static double vaihdaValuutta(double summa, double rahamaara, String valuutta, Raha[] valuutat) {
		for (int i = 0; i < valuutat.length; i++) {
			if (valuutat[i].nimi.equals(valuutta)) {
				summa = rahamaara * valuutat[i].arvo;
			}
		}
		return summa;
		

	}

	public static String kysyValuutta() {
		// Syöttöpyyntö ja vastauksen lukeminen
		System.out.println("Määrä ja Valuutta>");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			s = in.readLine();
		} catch (IOException ex) {
		}
		if (s == null)
			System.out.println("rivi null");
			
		if (s.equals(""))
			System.out.println("rivi tyhjä");
		if (s.equals("loppu")) {
			System.out.println("kiitos!");
			System.exit(0);
		}

		return s;
	}



	public static Raha[] alustaRahat(String tied) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader fi = new BufferedReader(new FileReader(tied))) {
			String s;
			while ((s = fi.readLine()) != null) {
				try {
	
					sb.append(s);
					

				} catch (NumberFormatException ex) {
					// Hylätään
				}
			}
		} catch (FileNotFoundException ex) {
			System.err.println("Tiedosto ei aukea! " + ex.getMessage());

		} catch (IOException ex) {
			System.err.println("Virhe tiedostoa luettaessa! " + ex.getMessage());
		}
		
		valuutat[0] = new Raha((Mjonot.erota(sb, ' ')), (Mjonot.erotaDouble(sb, oletus)));
		Mjonot.erotaChar(sb, '|');
		valuutat[1] = new Raha((Mjonot.erota(sb, ' ')), (Mjonot.erotaDouble(sb, oletus)));
		Mjonot.erotaChar(sb, '|');
		valuutat[2] = new Raha((Mjonot.erota(sb, ' ')), (Mjonot.erotaDouble(sb, oletus)));
		Mjonot.erotaChar(sb, '|');
		valuutat[3] = new Raha((Mjonot.erota(sb, ' ')), (Mjonot.erotaDouble(sb, oletus)));
		for (int i = 0; i < valuutat.length; i++) {
			System.out.println(valuutat[i].nimi + " " + valuutat[i].arvo);
		}
		return valuutat;
	}

}
