package demo.d10;

import java.io.OutputStream;
import java.io.PrintStream;

import demo.d10.Koulu.Oppilas.ListanAlkio;
import fi.jyu.mit.ohj2.*;

/**
 * Esimerkki linkitetystÃ¤ listasta. Kun tÃ¤ydennÃ¤t,
 * ota @SuppressWarnings("unused") rivit pois
 * 
 * @author Vesa Lappalainen
 * @version 1.0, 15.03.2003
 */
@SuppressWarnings("unused")
public class Koulu {

	/**
	 * Luokan yksi oppilas
	 */
	public static class Oppilas {
		private final String nimi;
		private final double keskiarvo;
		private Oppilas seuraava;

		public static class ListanAlkio {
			private static int counter = 0;
			private static Oppilas head;

			public ListanAlkio() {

			}

			public static void add(Oppilas oppilas) {

				if (head == null) {
					head = new Oppilas(oppilas.nimi, oppilas.keskiarvo);
				}
				else {
					
				
				Oppilas AlkioTemp = new Oppilas(oppilas.nimi, oppilas.keskiarvo);
				Oppilas AlkioNykyinen = head;

				if (AlkioNykyinen != null) {

					while (AlkioNykyinen.seuraava != null) {
						AlkioNykyinen = AlkioNykyinen.seuraava;
					}

					AlkioNykyinen.seuraava = AlkioTemp;
				}
				}
				counter++;
				
			}

			public boolean remove(int indeksi) {
				if (indeksi < 1 || indeksi > counter)
					return false;
				Oppilas AlkioNykyinen = head;
				if (head != null)
					for (int i = 0; i < indeksi; i++) {
						if (AlkioNykyinen.seuraava == null) {
							return false;
						}
						AlkioNykyinen.seuraava = AlkioNykyinen.seuraava.seuraava;
						counter--;
						return true;

					}
				return false;

			}

		}

		/**
		 * Alustetaan oppilaan tiedot
		 * 
		 * @param nimi      oppilaan nimi
		 * @param keskiarvo oppilaan keskiarvo
		 */
		public Oppilas(String nimi, double keskiarvo) {
			this.nimi = nimi;
			this.keskiarvo = keskiarvo;
		}

		/**
		 * Palautetaan oppilaan tiedot merkkijonona
		 * 
		 * @return oppilaan tiedot merkkijonona
		 */
		@Override
		public String toString() {
			return Mjonot.fmt(nimi, -22) + " keskiarvo: " + Mjonot.fmt(keskiarvo, 5, 2);
		}
	}

	private final String luokka;
	private int oppilaita;
	private Oppilas ensimmainen;
	private Oppilas viimeinen;

	/**
	 * Alustetaan luokka
	 * 
	 * @param luokka luokannimi
	 */
	public Koulu(String luokka) {
		this.luokka = luokka;
	}

	/**
	 * LisÃ¤tÃ¤Ã¤n uusi oppilas
	 * 
	 * @param oppilas lisÃ¤ttÃ¤vÃ¤ oppilas
	 * @example
	 * 
	 *          <pre name="test">
	 * #import java.io.ByteArrayOutputStream;
	 * #CLASSIMPORT
	 *  ByteArrayOutputStream bs  = new ByteArrayOutputStream();
	 *  String alku =  "%n%nLuokka: 1b oppilaita: %d%n" +
	 *                 "==========================================%n";
	 *  String loppu = "==========================================%n";
	 *  String t = "";
	 *  
	 *  Koulu luokka = new Koulu("1b");
	 *  
	 *  luokka.lisaa(new Koulu.Oppilas($nimi,$ka)); 
	 *  luokka.tulosta(bs);
	 *  bs.toString() === String.format(alku+($oppilaat)+loppu,$maara); bs.reset();
	 *  
	 *    $nimi       | $ka | $maara | $oppilaat
	 *  -------------------------------------------------------------------------------
	 *    ---         | --- |   0    | t=""                    
	 *  "Ankka Aku"   | 5.0 |   1    | t+="Ankka Aku              keskiarvo:  5.00%n"
	 *  "Ankka Tupu"  | 7.0 |   2    | t+="Ankka Tupu             keskiarvo:  7.00%n"
	 *  "Hiiri Mikki" | 9.0 |   3    | t+="Hiiri Mikki            keskiarvo:  9.00%n"
	 *                 
	 *  
	 *  luokka.poistaKaikki();
	 *                
	 *  luokka.lisaa(new Koulu.Oppilas($nimi,$ka)); 
	 *  luokka.tulosta(bs);
	 *  bs.toString() === String.format(alku+($oppilaat)+loppu,$maara);  bs.reset();
	
	 *    $nimi       | $ka | $maara | $oppilaat
	 *  -------------------------------------------------------------------------------
	 *    ---         | --- |   0    | t=""                   
	 *  "Hiiri Mikki" | 9.0 |   1    | t+="Hiiri Mikki            keskiarvo:  9.00%n"
	 * 
	 *          </pre>
	 */
	public void lisaa(Oppilas oppilas) {
		
		ListanAlkio.add(oppilas);
		
	}

	/**
	 * Tulostetaan luokan tiedot tietovirtaan.
	 * 
	 * @param os tietovirta, johon tulostetaan
	 */

    public void tulosta(OutputStream os)  {
  PrintStream out = new PrintStream(os);
  out.println();
  
  out.println();
  out.println("Luokka: " + luokka + " oppilaita: " + ListanAlkio.counter);
  out.println("==========================================");
  Oppilas x = ListanAlkio.head;
  for(int i= 0; i < ListanAlkio.counter; i++) {
      if (x != null){
  	out.println(x.toString());
          }
      if ( x.seuraava != null) {
  	    x = x.seuraava;
      }
  	}
  out.println("==========================================");
}


	/**
	 * Poistetaan kaikki luokan oppilaat
	 */

	public void poistaKaikki() {
		// TODO: Poista kaikki oppilaat.
		ListanAlkio.counter = 0;
		ListanAlkio.head = null;

	}

	/**
	 * Testataan luokkaa
	 * 
	 * @param args ei kÃ¤ytÃ¶ssÃ¤
	 */
	public static void main(String[] args) {
		Koulu luokka = new Koulu("1b");
		luokka.lisaa(new Oppilas("Ankka Aku", 5.0));
		luokka.lisaa(new Oppilas("Ankka Tupu", 7.0));
		luokka.lisaa(new Oppilas("Hiiri Mikki", 9.0));

		luokka.tulosta(System.out);
		luokka.poistaKaikki();
		luokka.tulosta(System.out);
	}
}
