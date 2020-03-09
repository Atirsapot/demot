package dynaaminen;

/**
 * Esimerkki dynaamisesta taulukosta
 * @author Vesa Lappalainen @version 1.0, 02.03.2002
 * @author Santtu Viitanen @version 1.1, 21.07.2011
 * @example
 * <pre name="test">
 *  #THROWS Taulukko.TaulukkoTaysiException
 *  Taulukko luvut = new Taulukko();
 *  luvut.lisaa(0); luvut.lisaa(2); luvut.lisaa(99);
 *  luvut.toString() === "0,2,99"; 
 *  luvut.set(1,4); luvut.toString() === "0,4,99"; 
 *  int luku = luvut.get(2);
 *  luku === 99;
 *  luvut.get(2) === 99;
 *  luvut.set(21, 4); #THROWS IndexOutOfBoundsException 
 *  luvut.lisaa(0); luvut.lisaa(0); //taulukko täyteen
 *  luvut.lisaa(0);  #THROWS Taulukko.TaulukkoTaysiException
 * </pre>
 */
public class Taulukko {
    /** Luokka täyden taulukon poikkeusta varten  */
    public static class TaulukkoTaysiException extends Exception {
        private static final long serialVersionUID = 1L;


        TaulukkoTaysiException(String viesti) {
            super(viesti);
        }
    }

    private int alkiot[];
    private int lkm;
    
    public static void main(String args[]) throws TaulukkoTaysiException {
    	Taulukko luvut = new Taulukko(7);
    	luvut.lisaa(0); luvut.lisaa(2);
    	System.out.println(luvut); // Tulostaa "0,2" 
    	Taulukko taul = luvut.clone();
    	luvut.lisaa(77);   
    	System.out.println(taul);  // tulostaa saman kuin edellä "0,2"
    }
    /**
     *  <pre name="test">
     *  
     *  </pre>
     */
    public Taulukko clone() {
    	Taulukko x = new Taulukko();
    	x.alkiot = this.alkiot;
    	x.lkm = this.lkm;
    	return x;
    }
    
    /**
     * Alustetaan 5 kokoinen taulukko 
     */
    public Taulukko() {
        alkiot = new int[5];
    }


    /**
     * Alutetaan valitun kokoinen taulukko
     * @param koko taulukon koko
     */
    public Taulukko(int koko) {
        alkiot = new int[koko];
    }


    /**
     * Lisätään taulukkoon yksi alkio
     * @param luku lisättävä alkio
     * @throws TaulukkoTaysiException jos taulukko täysi
     */
    public void lisaa(int luku) throws TaulukkoTaysiException {
        if (lkm >= alkiot.length) throw new TaulukkoTaysiException("Tila loppu");
        alkiot[lkm++] = luku;
    }


    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        String erotin = "";
        for (int i = 0; i < lkm; i++) {
            s.append(erotin + alkiot[i]);
            erotin = ",";
        }
        return s.toString();
    }


    /**
     * Asetetaan taulukon i's alkio
     * @param i mihin paikkaan astetaan
     * @param luku mikä arvo laitetaan
     * @throws IndexOutOfBoundsException jos väärä indeksi
     */
    public void set(int i, int luku) throws IndexOutOfBoundsException {
        if ((i < 0) || (lkm <= i)) throw new IndexOutOfBoundsException("i = " + i);
        alkiot[i] = luku;
    }


    /**
     * Palautetana paikasssa i oleva luku
     * @param i mistä paikasta luku otetaan
     * @return paikassa i olevan luvun arvo
     * @throws IndexOutOfBoundsException jos indeksi väärin
     */
    public int get(int i) throws IndexOutOfBoundsException {
        if ((i < 0) || (lkm <= i)) throw new IndexOutOfBoundsException("i = " + i);
        return alkiot[i];
    }

}