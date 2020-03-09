package demo.d6;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Astiasta versio, joka ilmoittaa aina määrän muuttuessa.
 * 
 * @author  Vesa Lappalainen
 * @version 1.0, 09.02.2003
 * @version 1.1, 19.02.2009
 * @version 1.2, 26.02.2009
 */
public class Astia {
    /**
     * Rajapinta olioille, jotka kuuntelevat astian määrän muutoksia
     * @author vesal
     * @version 21.3.2009
     */
    public interface MaaraMuuttuu {
        /**
         * Tätä kutsutaan kun astian sisällön määrä muuttuu
         * @param astia astia jonka sisältö muuttui
         */
        public void muutos(Astia astia);
    }

    private final String nimi;
    private final double tilavuus;
    private double maara;
    private final Collection<MaaraMuuttuu> kenelleIlmoitetaan = new ArrayList<MaaraMuuttuu>();


    /**
     * Alustaa uuden astian tietyn kokoiseksi ja luonnollisesti tyhjäksi.
     * Luodaan astian graafinen esitys pylväästä, jonka alaosa voi olla eri värillä
     * @param nimi uuden astian nimi
     * @param tilavuus uuden astian tilavuus
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getNimi()     === "5l";
     * astia5l.getMaara()    ~~~ 0;
     * astia5l.getTilavuus() ~~~ 5;
     * </pre>
     */
    public Astia(String nimi, double tilavuus) {
        this.nimi = nimi;
        this.tilavuus = tilavuus;
        this.maara = 0;
    }


    /**
     * Alustaa uuden astian tietyn kokoiseksi ja luonnollisesti tyhjäksi.
     * Luodaan astian graafinen esitys pylväästä, jonka alaosa voi olla eri värillä
     * @param nimi uuden astian nimi
     * @param tilavuus uuden astian tilavuus
     * @param kenelle kenelle ilmoitetaan määrän muutoksista
     * @example
     * <pre name="testJAVA">
     *   public static class KuuntelijaMalli implements Astia.MaaraMuuttuu {
     *      double maara = 0;
     *      public double getMaara() { return maara; }
     *      public void muutos(Astia astia) { maara = astia.getMaara(); }
     *   }
     * </pre>
     * @example
     * <pre name="test">
     * KuuntelijaMalli kuuntelija = new KuuntelijaMalli();
     * Astia astia5l = new Astia("5l",5,kuuntelija);
     * kuuntelija.getMaara() ~~~ 0;
     * astia5l.tayta();
     * kuuntelija.getMaara() ~~~ 5;
     * </pre>
     */
    public Astia(String nimi, double tilavuus, MaaraMuuttuu kenelle) {
        this(nimi, tilavuus);
        addMaaraMuuttuuListener(kenelle);
    }


    /**
     * Lisätään uusi kuuntelija muutostapahtumille.
     * Ilmoitetaan samalla oliolle tilanne.
     * @param kenelle olio, jolle ilmoitetaan määrän muutoksesta
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * KuuntelijaMalli kuuntelija = new KuuntelijaMalli();
     * astia5l.addMaaraMuuttuuListener(kuuntelija);
     * kuuntelija.getMaara() ~~~ 0;
     * astia5l.tayta();
     * kuuntelija.getMaara() ~~~ 5;
     * </pre>
     */
    public void addMaaraMuuttuuListener(MaaraMuuttuu kenelle) {
        kenelleIlmoitetaan.add(kenelle);
        kenelle.muutos(this);
    }


    /**
     * Asettaa astian nestemäärän ja ilmoittaa kuuntelijoille että määrä on muuttunut
     * @param maara
     */
    private void setMaara(double maara) {
        this.maara = maara;
        for (MaaraMuuttuu kenelle : kenelleIlmoitetaan)
            kenelle.muutos(this);
    }


    /**
     * Täyttää astian piripintaan asti
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getMaara()    ~~~ 0;
     * astia5l.tayta();
     * astia5l.getMaara()    ~~~ 5;
     * </pre>
     */
    public void tayta() {
        setMaara(tilavuus);
    }


    /**
     * Kaataa astiaan joko lisää tai vähentää, yli ei saa mennä eikä
     * pakkasen puolelle
     * @param lisa paljonko nestettä laitetaan
     * @return paljonko jäi mahtumatta
     * @example
     * <pre name="test">
     *    Astia a5 = new Astia("5",5);
     *    a5.kaada(3) ~~~ 0.0;     a5.getMaara() ~~~ 3.0;
     *    a5.kaada(5) ~~~ 3.0;     a5.getMaara() ~~~ 5.0;
     *    a5.kaada(-3) ~~~ 0.0;    a5.getMaara() ~~~ 2.0;
     *    a5.kaada(-3) ~~~ -1.0;   a5.getMaara() ~~~ 0.0;
     * </pre>
     */
    public double kaada(double lisa) {
        if ( lisa < 0 ) {
            double eiRiita = getMaara() + lisa;
            if ( eiRiita < 0 ) setMaara(0);
            else { setMaara(maara + lisa); eiRiita = 0; }
            return eiRiita;
        }
        
        double eiMahu = lisa - getTyhjaa();
        if ( eiMahu < 0 ) { setMaara(maara+lisa); eiMahu = 0; }
        else { setMaara(tilavuus); }
        return eiMahu;
        
    }


    /**
     * Kaataa tästä astiasta (this) toiseen astiaan niin paljon kuin toiseen
     * mahtuu tai tästä astiasta on antaa.
     * @param astia astia johon kaadetaan
     * 
     * @example
     * <pre name="test">
     *  Astia ampari = new Astia("ä",100);
     *  Astia a8     = new Astia("8",8);
     *  Astia a5     = new Astia("5",5);
     *  
     *  ampari.kaada(a8);  a8.getMaara() ~~~ 0;     ampari.getMaara() ~~~ 0.0;
     *  ampari.tayta();
     *  ampari.kaada(a8);  a8.getMaara() ~~~ 8.0;   ampari.getMaara() ~~~ 92.0;
     *  
     *  a8.kaada(a5);      a8.getMaara() ~~~ 3.0;   a5.getMaara() ~~~ 5.0;
     *  a5.kaada(ampari);  a5.getMaara() ~~~ 0.0;   ampari.getMaara() ~~~ 97.0;
     *  a8.kaada(a5);      a8.getMaara() ~~~ 0.0;   a5.getMaara() ~~~ 3.0;
     *  ampari.kaada(a8);
     *  a8.kaada(a5);      a8.getMaara() ~~~ 6.0;   a5.getMaara() ~~~ 5.0;
     * </pre>
     */
    public void kaada(Astia astia) {
        double siirrettava = astia.getTyhjaa();
        if ( getMaara() < siirrettava ) siirrettava = getMaara();
        astia.kaada(siirrettava);
        kaada(-siirrettava);
    }


    /**
     * Palautetaan nsteen määrä astiassa
     * @return nesteen määrä
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getMaara()    ~~~ 0;
     * astia5l.tayta();
     * astia5l.getMaara()    ~~~ 5;
     * </pre>
     */
    public double getMaara() {
        return maara;
    }


    /**
     * Palautetaan astion tilavuus
     * @return astian tilavuus
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getTilavuus() ~~~ 5;
     * </pre>
     */
    public double getTilavuus() {
        return tilavuus;
    }


    /**
     * Palautetaan astiossa olevan vapaan tilan määrä.
     * @return vapaan tilan määrä.
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getTyhjaa()   ~~~ 5;
     * astia5l.tayta();
     * astia5l.getTyhjaa()   ~~~ 0;
     * </pre>
     */
    public double getTyhjaa() {
        return getTilavuus() - getMaara();
    }


    /**
     * Palautetaan astian nimi
     * @return astian nimi
     * @example
     * <pre name="test">
     * Astia astia5l = new Astia("5l",5);
     * astia5l.getNimi()     === "5l";
     * </pre>
     */
    public String getNimi() {
        return nimi;
    }


    /**
     * Tutkitaan onko astian nimi etsitty
     * @param mika verrattava nimi
     * @return true jos on samat, false muuten
     * 
     * @example
     * <pre name="test">
     * Astia ampari  = new Astia("Ämpäri",100);
     * ampari.oletko("Ä") === false;
     * ampari.oletko("ämpäri") === true;
     * ampari.oletko("ÄMPÄRI") === true;
     * </pre>
     */
    public boolean oletko(String mika) {
        return getNimi().equalsIgnoreCase(mika);
    }

}