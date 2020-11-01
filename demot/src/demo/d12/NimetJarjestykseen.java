package demo.d12;

import java.util.Arrays;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

public class NimetJarjestykseen {

	public static void main(String[] args) {
		String s = "";
		String[] nimet = new String[25];
		int i = 0;
		while (true) {
			s = Syotto.kysy("Anna nimi");
			if (s != "")
				System.out.println("Nimi \"" + s + "\" lisätty");
			if ("".equals(s)) {
				nimet = lajittele(nimet);
				tulosta(nimet);
				break;
			}
			nimet[i] = tulkitse(s);
			i++;

		}
	}

	private static String[] lajittele(String[] nimet) {
		int i = 0;
		int count = 0;

		while (nimet[i++] != null) {
			count++;
		}
		String[] karsittu = new String[count];
		for (int j = 0; j < count; j++) {
			karsittu[j] = nimet[j];
		}
		Arrays.sort(karsittu);
		for (int k = 0; k < count; k++) {
			karsittu[k] = tulkitse(karsittu[k]);
		}
		return karsittu;
	}

	private static void tulosta(String[] nimet) {
		for (int i = 0; i < nimet.length; i++) {
			System.out.println(nimet[i]);
		}

	}

	/**
	 * tulkitsee syotteesta etu ja sukunimen, ja vaihtaa niiden jarjestysta voidaan
	 * myos kayttaa vain etu- ja sukunimien jarjestyksen vaihtamiseen
	 * 
	 * @return nimi sukunimi ennen etunimea
	 * @param s kayttajan syote
	 * <pre name="test">
	 * String s = "Aku Ankka";
	 * tulkitse(s) === Ankka Aku;
     * </pre>
	 */
	private static String tulkitse(String s) {
		StringBuilder sc = new StringBuilder();
		sc.append(s);
		String etunimi = "";
		etunimi = Mjonot.erota(sc, ' ');
		sc.append(" " + etunimi);
		return sc.toString();
	}

}
