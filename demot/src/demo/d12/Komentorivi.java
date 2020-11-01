package demo.d12;

import java.util.ArrayList;
import java.util.List;

import fi.jyu.mit.ohj2.Help;
import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

/**
 * 
 */
public class Komentorivi {

	/**
	 * Suorittaa yhden komennon.
	 */
	public interface KomentoRajapinta {
		String suorita(String parametrit);

	}

	public static class Ynnaa implements KomentoRajapinta {
		public String suorita(String parametrit) {

			StringBuilder sb = new StringBuilder();
			sb.append(parametrit);
			int summa = 0;
			while (sb.length() != 0) {
				int next = Mjonot.erotaInt(sb, 0);
				summa += next;
			}
			String tulos = Integer.toString(summa);
			return tulos;

		}
	}

//	public static class Apua implements KomentoRajapinta {
//
//		public Apua(String aputied) {
//			String aputiedosto = aputied;
//			
//		}
//		
//		
		
//	}

	public static class Palindromiko implements KomentoRajapinta {
		public String suorita(String parametrit) {
			StringBuilder sb = new StringBuilder();
			sb.append(parametrit);
			StringBuilder tulos = new StringBuilder();
			sb.reverse();
			if (sb.toString().equalsIgnoreCase(parametrit)) {
				tulos.append("Sana: " + parametrit + " on palindromi!");
			} else {
				tulos.append("Sana ei oo palindromi");
			}

			return tulos.toString();

		}
	}

	public static class Isoksi implements KomentoRajapinta {
		public String suorita(String parametrit) {
			parametrit = parametrit.toUpperCase();
			return parametrit;
		}
	}

	/**
	 * Komennon nimi ja vastaava "funktio".
	 */
	public static class Komento {

		private String nimi;
		private KomentoRajapinta komento;

		public Komento(String nimi, KomentoRajapinta komento) {
			this.nimi = nimi;
			this.komento = komento;
		}

	}

	/**
	 * Lista komennoista ja metodit etsimiseksi ja suorittamiseksi.
	 */
	public static class Komennot {
		private static List<Komento> komennot = new ArrayList<Komento>();

		public void add(Komento komento) {
			komennot.add(komento);
		}

		public String tulkitse(String s) {
			StringBuilder sc = new StringBuilder();
			sc.append(s);
			while (sc.charAt(0) == ' ') {
				sc.deleteCharAt(0);
			}
			String komento = Mjonot.erota(sc);
			String parametrit = sc.toString();
			String tulos = "";
			for (int i = 0; i < komennot.size(); i++) {
				if (komento.equalsIgnoreCase(komennot.get(i).nimi)) {
					tulos = komennot.get(i).komento.suorita(parametrit);
				}

			}
			return tulos;

		}

	}

	/**
	 * Testipääohjelma
	 * 
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		Komennot komennot = new Komennot();
//		Apua apua = new Apua("komento.txt");
//		komennot.add(new Komento("?", apua));
//		komennot.add(new Komento("apua", apua));
		komennot.add(new Komento("+", new Ynnaa()));
		komennot.add(new Komento("ynnää", new Ynnaa()));
		komennot.add(new Komento("isoksi", new Isoksi()));
		komennot.add(new Komento("palindromiko", new Palindromiko()));
		System.out.println(komennot);
		String s;

		while (true) {
			s = Syotto.kysy("Anna komento");
			if ("".equals(s))
				break;
			String tulos = komennot.tulkitse(s);
			System.out.println(tulos);
		}
	}

}