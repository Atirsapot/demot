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
 * Astian graafinen versio.  Toteutettu tyhmÃ¤sti
 * kopioimalla astian koodia.
 * 
 * @author  Vesa Lappalainen
 * @version 1.0, 09.02.2003
 * @version 1.1, 19.02.2009
 */
    public class Pylvas extends DrawableCollection{
        //pylväs luokka
        //private final String nimi;
        //private final double tilavuus;
        
        private static final double leveys = 1.0;
        private static final double suhde  = 0.5;
        private final Polygon ulko;
        private final FillPolygon sisa;
        private final RPoint pinta1,pinta2;
		private int maara;
        
        public Pylvas(int korkeus, int leveys, int varimaara) {
            
            pinta1 = new RPoint(0,leveys);
            pinta2 = new RPoint(korkeus,0);
            ulko = new Polygon(new RPoint[]{
                    new RPoint(0,leveys),
                    new RPoint(korkeus,korkeus),
              
            });
            sisa = new FillPolygon(new RPoint[]{
                    new RPoint(leveys,leveys),
                    new RPoint(korkeus,0),
                    pinta2,
                    pinta1
            });

            sisa.setColor(Color.BLUE);
            add(ulko);
            add(sisa);
        
           
            this.maara = 0;
        }
        private void setMaara(int maara) {
            this.maara = maara;
            pinta1.set(Axis.Y, suhde*maara);
            pinta2.set(Axis.Y, suhde*maara);
            redraw();
        }
        
        public static void main(String[] args) {
            Pylvas p5 = new Pylvas(1,5,0); 
            Pylvas p8 = new Pylvas(1,8,2);
            // muodostajalle pylvään leveys, korkeus ja väritetyn osan määrä
            // väritetyn osan korkeudesta käytetään nimeä väli

            Window window = new Window();
            window.scale(0,0,5,8);
            p8.move(2,0,0);
            p8.setValiColor(Color.RED);

            window.add(p8);
            window.add(p5);
            window.showWindow();

            //Syotto.kysy("Lisää 5 pylvään väliä");         p5.GraafinenAstia.setVali(4);
           // Syotto.kysy("Pienennä 8 pylvään korkeutta");  p8.setKorkeus(6);
    }
        public void setValiColor(Color vari) {
       
        this.sisa.setColor(vari);
    }
}