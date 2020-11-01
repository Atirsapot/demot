package demo.d11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fi.jyu.mit.ohj2.Mjonot;

public class Valuutat {
	private static final double oletus = 0;
	public static Raha[] valuutat;
	public static double rahamaara = 0;
	public static String valuutta = "";
	public static boolean tee = true;
	
	
	
	public static void main(String[] args) {

		Raha[] valuutat = new Raha[4];
		alustaRahat(valuutat);
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

	public static Raha[] alustaRahat(Raha[] valuutat) {

		valuutat[0] = new Raha("Mk", 1);
		valuutat[1] = new Raha("$", 5.7);
		valuutat[2] = new Raha("€", 5.94573);
		valuutat[3] = new Raha("SKr", 0.6);
		
		return valuutat;
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

}
