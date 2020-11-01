package demo.d12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author topiasrita & joonahuttunen
 * @versio 0.1
 *
 */
public class RitariAlgo {

	private static boolean ratkaisuLoydetty = false;

	// shakkilaudan koko
	private static final int n = 5;
	private static final int m = 7;
	
	public static void main(String [] args) {
		int alkupiste[] = new int[2];
		alkupiste[0] = 3;
		alkupiste[1] = 3;
		
		tulosta(getKierros(alkupiste[0], alkupiste[1]));
	}
	
	private static void tulosta(int[][] tulokset) {
		System.out.println(Arrays.deepToString(tulokset));
		
	}

	// palauttaa matriisin joka kuvastaa shakkilautaa
	// joksinen indeksi sisältää numeron, joka kuvastsaa ritarin siirtoa
	public static int[][] getKierros(int x, int y) {
		Ruutu alkuRuutu = new Ruutu(x, y);

		List<Ruutu> kierros = new ArrayList<Ruutu>();
		kierros.add(alkuRuutu);

		// kirjataan kuljettu reitti matriisiin

		boolean[][] kuljettu = new boolean[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				kuljettu[i][j] = false;
			}
		}

		// alkuruutu kuljetuksi
		kuljettu[x][y] = true;

		// Lähdetään etsimaan reittiä
		ratkaisuLoydetty = false;
		backtrack(kierros, kuljettu);

		// Luodaan tulosmatriisi, jossa alkiot sialtavat kutakin
		// askelkta edustavan numeron
		int tulos[][] = new int[n][m];
		int koko = kierros.size();
		for (int i = 0; i < koko; i++) {
			Ruutu nykyinenRuutu = kierros.get(i);
			tulos[nykyinenRuutu.getX()][nykyinenRuutu.getY()] = i + 1;
		}
		return tulos;
	}

	// backtrack algoritmi
	private static void backtrack(List<Ruutu> ratkaisu, boolean[][] kuljettu) {
		if (onRatkaisu(ratkaisu)) {
			ratkaisuLoydetty = true;
			return;
		} else {

			//
			List<Ruutu> ehdokkaat = getEhdokkaat(ratkaisu, kuljettu);

			for (Ruutu p : ehdokkaat) {
				ratkaisu.add(p);
				kuljettu[p.getX()][p.getY()] = true;

				backtrack(ratkaisu, kuljettu);

				if (ratkaisuLoydetty) {
					return;
				} else {
					// kokeillaan perua
					ratkaisu.remove(p);
					kuljettu[p.getX()][p.getY()] = false;
				}
			}

		}
	}

	private static boolean onRatkaisu(List<Ruutu> ratkaisu) {
		return (ratkaisu.size() >= n * m);
	}

	private static List<Ruutu> getEhdokkaat(List<Ruutu> ratkaisu, boolean[][] kuljettu) {
		List<Ruutu> ehdokkaat = new ArrayList<Ruutu>();

		Ruutu edellinenRuutu = ratkaisu.get(ratkaisu.size() - 1);
		int x = edellinenRuutu.getX();
		int y = edellinenRuutu.getY();

		if (x - 1 >= 0 && y - 2 >= 0 && !kuljettu[x - 1][y - 2])
			ehdokkaat.add(new Ruutu(x - 1, y - 2));

		if (x + 1 < n && y - 2 >= 0 && !kuljettu[x + 1][y - 2])
			ehdokkaat.add(new Ruutu(x + 1, y - 2));

		if (x + 2 < n && y - 1 >= 0 && !kuljettu[x + 2][y - 1])
			ehdokkaat.add(new Ruutu(x + 2, y - 1));

		if (x + 2 < n && y + 1 < m && !kuljettu[x + 2][y + 1])
			ehdokkaat.add(new Ruutu(x + 2, y + 1));

		if (x + 1 < n && y + 2 < m && !kuljettu[x + 1][y + 2])
			ehdokkaat.add(new Ruutu(x + 1, y + 2));

		if (x - 1 >= 0 && y + 2 < m && !kuljettu[x - 1][y + 2])
			ehdokkaat.add(new Ruutu(x - 1, y + 2));

		if (x - 2 >= 0 && y + 1 < m && !kuljettu[x - 2][y + 1])
			ehdokkaat.add(new Ruutu(x - 2, y + 1));

		if (x - 2 >= 0 && y - 1 >= 0 && !kuljettu[x - 2][y - 1])
			ehdokkaat.add(new Ruutu(x - 2, y - 1));

		return ehdokkaat;
	}
}

class Ruutu {
	private int x;
	private int y;

	public Ruutu(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
