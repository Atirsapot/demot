package demo.d7;

import java.util.*;

/**
 * Kombinaatiot-luokka erilaisten summakombinaatioiden
 * määrittelemiseksi.
 * Esimerkki iteraattorista
 * <pre>
 * Esimerkiksi luvuista 3 5 9
 * saadaan aikaiseksi summat: 3 5 8 9 12 14 17
 * </pre>
 * @author Vesa Lappalainen
 * @version 1.0, 14.02.2003
 * @version 1.1, 24.02.2007/vl
 * Lisätty taulukon järjestäminen.
 */
public class Kombinaatiot implements Iterable<Integer> {

    private static final int MAXESIINTYMALUKUJA = 32; // Tälläkin tulee jo 4 G
                                                      // kombinaatiota!

    private int lkm;
    private int luvut[] = new int[MAXESIINTYMALUKUJA];
    private int kombinaationNro;


    /**
     * Alustetaan kombinaatioiden laskuri
     */
    public Kombinaatiot() {
        Arrays.fill(luvut, Integer.MAX_VALUE);
    }


    /**
     * Lisää uuden luvun jonka kombinaatiota voidaan käyttää summassa.
     * @param luku lisättävä luku
     * 
     * @example
     * <pre name="test">
     *  Kombinaatiot kombi = new Kombinaatiot();
     *  kombi.lisaa($luku);                               
     *  kombi.summaKombinaatiot() === $kombinaatiot;
     *  
     *    $luku     |  $kombinaatiot
     *  --------------------------------------------------------
     *     ---      | ""                                          
     *     3        | "3"
     *     5        | "3 5 8"
     *     9        | "3 5 8 9 12 14 17"
     *     1        | "1 3 4 5 6 8 9 9 10 12 13 14 15 17 18"
     *       
     * </pre>
     */
    public void lisaa(int luku) {
        if ( luku == 0 ) return;
        if (lkm < MAXESIINTYMALUKUJA) {
            luvut[lkm] = luku;
            lkm++;
        }
        Arrays.sort(luvut);
    }


    /**
     * "Iteraattorin loppuarvo"
     * @return loppuarvo
     */
    @SuppressWarnings("static-method")
    public int loppu() {
        return -10000;
    }


    /**
     * Siirtyy ensimmäiseen summakombinaatioon ja palauttaa sen.
     * Tämän huono puoli on se, että voidaan mennä kerralla
     * vain yhtä silmukkaa.
     * @return ensimmäinen summakombinaatio
     * 
     * @example
     * <pre name="test">
     *  Kombinaatiot kombi = new Kombinaatiot();
     *  kombi.eka() === kombi.loppu();
     *  kombi.lisaa(3);  kombi.eka() === 3;   
     *  kombi.lisaa(1);  kombi.eka() === 1;
     */
    public int eka() {
        kombinaationNro = 0;
        return seuraava();
    }


    /**
     * Otetaan käyttöön seuraava summakombinaatio luvuista.
     * kombinaation_nro on kombinaation numero.
     * Jos kombinaation_nro ajatellaan binääriluvuksi, niin
     * käytetään summassa kaikkia niitä lukuja, joita vastaavissa
     * kohdissa kombinaation_nro:ssa on bitti päällä
     * Kun käydään läpi kaikki kombinaatioiden numerot
     * 1..n, niin tulee käytyä läpi kaikki binääriluvut
     * (jos lkm=3 => 001 010 011 100 101 110 111)
     * 1..2^lkm, eli kaikki kombinaatiot, joissa on lkm-bittiä
     * ja ainakin yksi bitti päällä.  Kun kullakin kombinaation
     * numerolla summataan ne luvut, joiden kohdalla bitti
     * on päällä, tulee siis käytyä läpi kaikki summakombinaatiot
     * @return seuraava kombinaatiosumma
     * @example
     * <pre name="test">
     *  Kombinaatiot kombi = new Kombinaatiot();
     *  kombi.eka() === kombi.loppu();
     *  kombi.lisaa(3);  kombi.eka() === 3; 
     *  kombi.seuraava() === kombi.loppu();
     *     
     *  kombi.lisaa(1);  kombi.eka() === 1;
     *  kombi.seuraava() === 3;  
     *  kombi.seuraava() === 4;  
     *  kombi.seuraava() === kombi.loppu();  
     */
    public int seuraava() {
        kombinaationNro++;
        return summa(kombinaationNro);
    }


    /**
     * Palautetaan tietty kombination numeroa vastaava summakombinaatio.
     * Kutakin summakombinaatiota vastaa binääriluku, jossa on bitti päällä
     * vastaavia summattavia kohti.
     * @param nro minkä kombinaation numero palautetaan
     * @return kombinaatiota vastaava summa tai loppu() jos nro on liian suuri 
     * @example
     * <pre name="test">
     *  Kombinaatiot kombi = new Kombinaatiot();
     *  kombi.summa(1) === kombi.loppu();
     *  kombi.lisaa(3);  kombi.summa(1) === 3; 
     *  kombi.summa(2) === kombi.loppu();
     *     
     *  kombi.lisaa(1);  kombi.summa(1) === 1;
     *  kombi.summa(2) === 3;  
     *  kombi.summa(3) === 4;  
     *  kombi.summa(4) === kombi.loppu();  
     */
    public int summa(int nro) {
        int summa = 0, n = 0;
        int mask = nro;
        for (int i = 0; i < lkm; i++) {
            int bittiKohdalla = mask & 1;
            mask >>= 1;
            if (bittiKohdalla != 0) {
                summa += luvut[i];
                n++;
            }
            // int b = (mask & 1); mask >>= 1; summa += luvut[i]*b; n += b;  
        }
        if (n != 0) return summa;
        return loppu();
    }


    /**
     * @return kombinaatioiden lukumäärä
     * @example
     * <pre name="test">
     *  Kombinaatiot kombi = new Kombinaatiot();
     *  kombi.getMaara() === 0;
     *  kombi.lisaa(3);  kombi.getMaara() === 1; 
     *  kombi.lisaa(1);  kombi.getMaara() === 3;
     *  kombi.lisaa(9);  kombi.getMaara() === 7;
     * </pre>
     */
    public int getMaara() {
        return (1 << (lkm)) - 1;
    }


    /**
     * Palauttaa merkkijonona ne summakombinaatiot jotka toistaiseksi 
     * saadaan aikaiseksi.
     * @return summakombinaatiot merkkijonona
     * 
     * @example
     * <pre name="test">
     *  Kombinaatiot kombi = new Kombinaatiot();
     *  kombi.summaKombinaatiot() === "";
     *  kombi.lisaa(3); kombi.summaKombinaatiot() === "3";
     *  kombi.lisaa(1); kombi.summaKombinaatiot() === "1 3 4";
     * </pre>
     */
    public String summaKombinaatiot() {
        StringBuilder sb = new StringBuilder();
        String erotin = "";
        for (int s : this) {
            sb.append(erotin + s);
            erotin = " ";
        }
        return sb.toString();
    }


    /**
     * Palautetaan iteraattori, jolla voidaan käydä läpi kaikki
     * summakombinaatiot
     * @return iteraattori läpikäymiseen
         * <pre name="test">
         *  Kombinaatiot kombi = new Kombinaatiot();
         *  kombi.lisaa(3); 
         *  kombi.lisaa(1);  
         *  int[] summat = {1,3,4};
         *  int i=0;
         *  for (int s:kombi) 
         *    s === summat[i++];
         * </pre>
     */
    @Override
    public Iterator<Integer> iterator() {
        return new KombinaatiotIterator();
    }
    

    /**
     * Iteraattori-luokka kaikkien kombinaatioiden läpikäymiseen
     */
    private class KombinaatiotIterator implements Iterator<Integer> {
        private int nro;


        public KombinaatiotIterator() {
            nro = 1;
        }


        /**
         * Onko olemassa seuraavata alkiota 
         * <pre name="test">
         *  #import java.util.*;
         *  Kombinaatiot kombi = new Kombinaatiot();
         *  kombi.summaKombinaatiot() === "";
         *  Iterator<Integer> iter = kombi.iterator();
         *  iter.hasNext() === false;
         *  kombi.lisaa(3);
         *  iter = kombi.iterator();
         *  iter.hasNext() === true;
         *  int i = iter.next();
         *  i === 3;
         *  iter.hasNext() === false;
         * </pre>
         */
        @Override
        public boolean hasNext() {
            return nro <= getMaara();
        }


        /**
         * Seuraava summakobinaatio 
         * <pre name="test">
         *  Kombinaatiot kombi = new Kombinaatiot();
         *  kombi.summaKombinaatiot() === "";
         *  Iterator<Integer> iter = kombi.iterator();
         *  iter.next() === null; #THROWS NoSuchElementException
         *  kombi.lisaa(3);  iter = kombi.iterator();
         *  iter.next().intValue() === 3;
         *  kombi.lisaa(1);  iter = kombi.iterator();
         *  iter.next().intValue() === 1;
         *  iter.next().intValue() === 3;
         *  iter.next().intValue() === 4;
         *  iter.next() === null; #THROWS NoSuchElementException
         * </pre>
         */
        @Override
        public Integer next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("Ei oo enää");
            return Integer.valueOf(summa(nro++));
        }


        /**
         * Kombinaatioista poistaminen ei ole mielekästä
         * joten metodia ei ole toteutettu
         */
        @Override
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException("Ei voi poistaa");
        }
    }
    
    
    /**
     * Testiohjelma summakombinaatioille
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Kombinaatiot kombi = new Kombinaatiot();
        kombi.lisaa(3);
        kombi.lisaa(5);
        kombi.lisaa(9);

        System.out.print("Summat: ");
        for (int s = kombi.eka(); s != kombi.loppu(); s = kombi.seuraava()) {
            System.out.print(s + " ");
        }
        System.out.println(); // Summat: 3 5 8 9 12 14 17
        
        System.out.print("Summat: ");
        for (int nro = 1; nro<=kombi.getMaara(); nro++) {
            System.out.print(kombi.summa(nro) + " ");
        }
        System.out.println(); // Summat: 3 5 8 9 12 14 17
        
        System.out.print("Summat: ");
        for (Iterator<Integer> iter = kombi.iterator(); iter.hasNext();) {
            int s = iter.next().intValue();
            System.out.print(s + " ");
        }
        
        System.out.println(); // Summat: 3 5 8 9 12 14 17
        System.out.print("Summat: ");
        for (int s : kombi) {
            System.out.print(s + " ");
        }
        System.out.println(); // Summat: 3 5 8 9 12 14 17
    }
}
