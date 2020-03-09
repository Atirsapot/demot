package demo.d8;
import java.awt.Color;

import fi.jyu.mit.graphics.Axis;
import fi.jyu.mit.graphics.DrawableCollection;
import fi.jyu.mit.graphics.FillPolygon;
import fi.jyu.mit.graphics.Polygon;
import fi.jyu.mit.graphics.RPoint;
import fi.jyu.mit.graphics.Window;
import fi.jyu.mit.ohj2.Syotto;

/**
 * Astian graafinen versio.  Toteutettu tyhmästi
 * kopioimalla astian koodia.
 * 
 * @author  Vesa Lappalainen
 * @version 1.0, 09.02.2003
 * @version 1.1, 19.02.2009
 */
public class GraafinenAstia extends DrawableCollection {
    private final String nimi;
    private final double tilavuus;
    private double maara;

    private static final double leveys = 1.0;
    private static final double suhde  = 0.5;
    private final Polygon ulko;
    private final FillPolygon sisa;
    private final RPoint pinta1,pinta2;


    /**
     * Alustaa uuden astian tietyn kokoiseksi ja luonnollisesti tyhjäksi.
     * Luodaan astian graafinen esitys kahdesta suorakulmiosta.
     * Ulkosuorakulmiosta ja sisäsuorakulmiosta, joka esittää astiassa
     * olevaa nestemäärää.
     * @param nimi uuden astian nimi
     * @param tilavuus uuden astian tilavuus
     */
    public GraafinenAstia(String nimi, double tilavuus) {
        pinta1 = new RPoint(0,0);
        pinta2 = new RPoint(leveys,0);
        ulko = new Polygon(new RPoint[]{
                new RPoint(0,0),
                new RPoint(leveys,0),
                new RPoint(leveys,suhde*tilavuus),
                new RPoint(0,suhde*tilavuus)
        });
        sisa = new FillPolygon(new RPoint[]{
                new RPoint(0,0),
                new RPoint(leveys,0),
                pinta2,
                pinta1
        });

        sisa.setColor(Color.BLUE);
        add(ulko);
        add(sisa);
        this.nimi = nimi;
        this.tilavuus = tilavuus; this.maara = 0;
    }


    /**
     * Asettaa astian nestemäärän ja tekee vastaavan graafisen muutoksen.
     * @param maara asetttava nestemäärä
     */
    private void setMaara(double maara) {
        this.maara = maara;
        pinta1.set(Axis.Y, suhde*maara);
        pinta2.set(Axis.Y, suhde*maara);
        redraw();
    }

    /**
     * Täyttää astian piripintaan asti
     */
    public void tayta() { setMaara(tilavuus); }


    /**
     * Kaataa astiaan joko lisää tai vähentää, yli ei saa mennä eikä
     * pakkasen puolelle
     * @param lisa astiaan laitettava tai otettava nestemäärä
     * 
     * @example
     * <pre name="test">
     * GraafinenAstia astia5l = new GraafinenAstia("5l",5);
     * astia5l.getTyhjaa() ~~~ 5.0;
     * astia5l.kaada(3.0);
     * astia5l.getMaara() ~~~ 3.0;    astia5l.getTyhjaa() ~~~ 2.0;
     * astia5l.kaada(3.0);
     * astia5l.getMaara() ~~~ 5.0;    astia5l.getTyhjaa() ~~~ 0.0;
     * astia5l.kaada(-2.0);
     * astia5l.getMaara() ~~~ 3.0;    astia5l.getTyhjaa() ~~~ 2.0;
     * astia5l.kaada(-2.0);
     * astia5l.getMaara() ~~~ 1.0;    astia5l.getTyhjaa() ~~~ 4.0;
     * astia5l.kaada(-2.0);
     * astia5l.getMaara() ~~~ 0.0;    astia5l.getTyhjaa() ~~~ 5.0;
     * </pre>
     */
    public void kaada(double lisa) {
        if ( lisa < 0 ) {
            if ( -lisa > maara ) setMaara(0);
            else setMaara(maara + lisa);
        }
        else
            if ( getTyhjaa() > lisa ) setMaara(maara + lisa);
            else tayta();
    }

    /**
     * Kaataa tästä astiasta (this) toiseen astiaan niin paljon kuin toiseen
     * mahtuu tai tästä astiasta on antaa.
     * @param astia astia johon kaadetaan
     * 
     * @example
     * <pre name="test">
     * GraafinenAstia astia5l = new GraafinenAstia("5l",5);
     * GraafinenAstia astia8l = new GraafinenAstia("8l",8);
     * GraafinenAstia ampari  = new GraafinenAstia("Ämpäri",100);
     * ampari.tayta();
     * ampari.kaada(astia8l);
     * ampari.getMaara()  ~~~ 92.0;
     * astia8l.getMaara() ~~~ 8.0;
     * astia8l.kaada(astia5l);
     * astia5l.getMaara() ~~~ 5.0;
     * astia8l.getMaara() ~~~ 3.0;
     * astia5l.kaada(ampari);
     * astia5l.getMaara() ~~~ 0.0;
     * astia8l.kaada(astia5l);
     * ampari.kaada(astia8l);
     * astia8l.kaada(astia5l);
     * astia8l.getMaara() ~~~ 6.0;
     * astia8l.getTyhjaa() ~~~ 2.0;
     * </pre>
     */
    public void kaada(GraafinenAstia astia) {
        double siirrettava = astia.getTyhjaa();
        if ( getMaara() < siirrettava ) siirrettava = getMaara();
        astia.kaada(siirrettava);
        kaada(-siirrettava);
    }

    /**
     * Palautetaan nsteen määrä astiassa
     * @return nesteen määrä
     */
    public double getMaara()           { return maara; }

    /**
     * Palautetaan astion tilavuus
     * @return astian tilavuus
     */
    public double getTilavuus()        { return tilavuus; }

    /**
     * Palautetaan astiossa olevan vapaan tilan määrä.
     * @return vapaan tilan määrä.
     */
    public double getTyhjaa()          { return getTilavuus()-getMaara(); }

    /**
     * Palautetaan astian nimi
     * @return astian nimi
     */
    public String getNimi()            { return nimi; }

    /**
     * Tutkitaan onko astian nimi etsitty
     * @param mika verrattava nimi
     * @return true jos on samat, false muuten
     * 
     * @example
     * <pre name="test">
     * GraafinenAstia ampari  = new GraafinenAstia("Ämpäri",100);
     * ampari.oletko("Ä") === false;
     * ampari.oletko("ämpäri") === true;
     * ampari.oletko("ÄMPÄRI") === true;
     * 
     * </pre>
     */
    public boolean oletko(String mika) { return getNimi().equalsIgnoreCase(mika);}

    /**
     * Testaan astioita
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        GraafinenAstia astia5l = new GraafinenAstia("5",5);
        GraafinenAstia astia8l = new GraafinenAstia("8",8);
        GraafinenAstia ampari = new GraafinenAstia("ä",13);

        Window window = new Window();
        window.scale(0,0,5,8);
        astia8l.move(2,0,0);
        ampari.move(4,0,0);

        window.add(astia8l);
        window.add(astia5l);
        window.add(ampari);
        window.showWindow();

        Syotto.kysy("Täytetään ämpäri");    ampari.tayta();
        Syotto.kysy("Ämpäri => 8l");        ampari.kaada(astia8l);
        Syotto.kysy("8l => 5l");            astia8l.kaada(astia5l);

    }

}