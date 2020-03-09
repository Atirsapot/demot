package demo.d7;

import java.util.ArrayList;
import java.util.List;

/**
 * Luokka Esiintymat lukujen määrän laskemiseksi
 * @author Vesa Lappalainen
 * @version 1.0, 14.02.2003
 */
public class Esiintymat {

    private int ala, yla;
    private int esiintymatLaskuri[];


    /**
     * Muuttaa väillä [ala,yla] olevan indeksin välille [0,yla-ala]
     * @param i indeksi joka muutetaan
     * @return 0:sta alkava indeksi
     */
    private int ind(int i) {
        return i - ala;
    }


    /**
     * Alustetaan esiintymien laskeminen.
     * @param ala pienin laskettava
     * @param yla suurin laskettava 
     */
    public Esiintymat(int ala, int yla) {
        this.ala = ala;
        this.yla = yla;
        if (ala > yla) {
            this.ala = yla;
            this.yla = ala;
        }
        esiintymatLaskuri = new int[this.yla - this.ala + 1];
        for (int i = this.ala; i <= this.yla; i++)
            // turha
            esiintymatLaskuri[ind(i)] = 0;
    }


    /**
     * Lisää esiintymätaulukkoon luvun n esiintymää yhdellä mikäli
     * n on sallitussa välissä [ala,yla] oleva luku.
     * @param n luku, jonka esiintymää lisätään yhdellä
     * 
     * @example
     * <pre name="test">
     *   Esiintymat esiintymat = new Esiintymat(1,13);
     *   esiintymat.getLoydettyja() === 0;
     *   esiintymat.lisaa(0);  esiintymat.loydetyt() === "";
     *   esiintymat.lisaa(1);  esiintymat.loydetyt() === "1";
     *   esiintymat.lisaa(1);  esiintymat.loydetyt() === "1";
     *   esiintymat.lisaa(8);  esiintymat.loydetyt() === "1 8";
     *   esiintymat.lisaa(5);  esiintymat.loydetyt() === "1 5 8";
     *   esiintymat.lisaa(13); esiintymat.loydetyt() === "1 5 8 13";
     *   esiintymat.lisaa(14); esiintymat.loydetyt() === "1 5 8 13";
     *   esiintymat.eiLoydetyt() === "2 3 4 6 7 9 10 11 12";
     *   esiintymat.getLoydettyja() === 4;
     * </pre>
     */
    public void lisaa(int n) {
        if ((ala <= n) && (n <= yla)) esiintymatLaskuri[ind(n)]++;
    }


    /**
     * Palauttaa merkkijonona ne luvut, joita on löytynyt
     * @return löytyneet merkkijonona
     */
    public String loydetyt() {
        StringBuilder sb = new StringBuilder();
        String erotin = "";
        for (int i = ala; i <= yla; i++)
            if (onko(i)) {
                sb.append(erotin + i);
                erotin = " ";
            }
        return sb.toString();
    }


    /**
     * Palautetaan löydettyjen lista.
     * @return löydetyjen lukujen lista
     * @example
     * <pre name="test">
     *   Esiintymat esiintymat = new Esiintymat(1,13);
     *   esiintymat.getLoydetyt().toString() === "[]";
     *   esiintymat.lisaa(1);  esiintymat.getLoydetyt().toString() === "[1]";
     *   esiintymat.lisaa(8);  esiintymat.getLoydetyt().toString() === "[1, 8]";
     * </pre>
     */
    public List<Integer> getLoydetyt() {
        List<Integer> loydetyt = new ArrayList<Integer>();
        for (int i = ala; i <= yla; i++)
            if (onko(i)) loydetyt.add(i);
        return loydetyt;
    }


    /**
     * Palautetaan löydettyjen lista.
     * @return löydetyjen lukujen lista
     * @example
     * <pre name="test">
     *   Esiintymat esiintymat = new Esiintymat(2,4);
     *   esiintymat.getEiLoydetyt().toString() === "[2, 3, 4]";
     *   esiintymat.lisaa(2);  esiintymat.getEiLoydetyt().toString() === "[3, 4]";
     *   esiintymat.lisaa(4);  esiintymat.getEiLoydetyt().toString() === "[3]";
     * </pre>
     */
    public List<Integer> getEiLoydetyt() {
        List<Integer> loydetyt = new ArrayList<Integer>();
        for (int i = ala; i <= yla; i++)
            if (!onko(i)) loydetyt.add(i);
        return loydetyt;
    }


    /**
     * Palautetaan tieto siitä, onko esiintymä paikassa i
     * löydetty vai ei. Jos i välin ulkopuolelta, palautetaan false.  
     * @param i paikka väiltä [ala,yla]
     * @return onko paikassa i oleva esiintymä löydetty
     * @example
     * <pre name="test">
     *   Esiintymat esiintymat = new Esiintymat(1,13);
     *   esiintymat.getLoydettyja() === 0;
     *   esiintymat.lisaa(0);  esiintymat.onko(0) === false;
     *   esiintymat.lisaa(1);  esiintymat.onko(1) === true; esiintymat.onko(2) === false;
     *   esiintymat.lisaa(1);  esiintymat.onko(1) === true; esiintymat.onko(8) === false;
     *   esiintymat.lisaa(8);  esiintymat.onko(8) === true; esiintymat.onko(5) === false;
     *   esiintymat.lisaa(5);  esiintymat.onko(5) === true; esiintymat.onko(13) === false;
     *   esiintymat.lisaa(13); esiintymat.onko(5) === true; esiintymat.onko(14) === false;
     *   esiintymat.lisaa(14); esiintymat.onko(14) === false;
     *   esiintymat.getLoydettyja() === 4;
     * </pre>
     */
    public boolean onko(int i) {
        if (i < ala || yla < i) return false;
        return esiintymatLaskuri[ind(i)] != 0;
    }


    /**
     * Palauttaa merkkijonona ne luvut, joita ei ole löytynyt
     * @return ei-löytyneet merkkijonona
     */
    public String eiLoydetyt() {
        StringBuilder sb = new StringBuilder();
        String erotin = "";
        for (int i = ala; i <= yla; i++)
            if (!onko(i)) {
                sb.append(erotin + i);
                erotin = " ";
            }
        return sb.toString();
    }


    /**
     * Palautta niiden lukujen lukumäärän, joita on löytynyt
     * @return kuinka montaa lukua on esiintynyt
     */
    public int getLoydettyja() {
        int n = 0;
        for (int i = ala; i <= yla; i++)
            if (onko(i)) n++;
        return n;
    }


    /**
     * Palauttaa laskettavien esiintymien ylärajan
     * @return yläraja
     */
    public int getAla() {
        return ala;
    }


    /**
     * Palauttaa laskettavien esiintyminen alarajan
     * @return alaraja
     */
    public int getYla() {
        return yla;
    }


    /**
     * Tieto siitä, onko kaikki löydetty
     * @return true jos kaikki on löydetty
     * @example
     * <pre name="test">
     *   Esiintymat esiintymat = new Esiintymat(2,5);
     *   esiintymat.onkoKaikki() === false;
     *   esiintymat.lisaa(2); esiintymat.onkoKaikki() === false;
     *   esiintymat.lisaa(3); esiintymat.onkoKaikki() === false;
     *   esiintymat.lisaa(4); esiintymat.onkoKaikki() === false;
     *   esiintymat.lisaa(4); esiintymat.onkoKaikki() === false;
     *   esiintymat.lisaa(5); esiintymat.onkoKaikki() === true;
     *   esiintymat.lisaa(2); esiintymat.onkoKaikki() === true;
     * </pre>
     */
    public boolean onkoKaikki() {
        return getLoydettyja() == getYla() - getAla() + 1;
    }


    /**
     * Testiohjelma Esiintymat-luokalle
     * @param args - ei käytössä
     */
    public static void main(String[] args) {
        Esiintymat esiintymat = new Esiintymat(1, 13); // laskee lukujen 1-13
                                                       // esiintymiä
        esiintymat.lisaa(0); // ei vaikuta, koska 0 ei ole välillä [1,13]
        esiintymat.lisaa(1); //
        esiintymat.lisaa(8); // lisää yhden esiintymän luvun 8 kohdalle.
        esiintymat.lisaa(5); // lisää yhden esiintymän luvun 5 kohdalle.
        esiintymat.lisaa(13); //
        System.out.println(esiintymat.loydetyt()); // 1 5 8 13
        System.out.println(esiintymat.eiLoydetyt()); // 2 3 4 6 7 9 10 11 12
        int loydettyja = esiintymat.getLoydettyja();
        System.out.println("Loydettyja on " + loydettyja); // Löydettyjä on 4
    }

}