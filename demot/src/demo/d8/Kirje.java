package demo.d8;

/**
 * Ohjelmalla tutkitaan postimaksun suuruutta
 * @author Topias ja joona
 * @version 1.0, 27.02.2020
 */
public class Kirje {
    private String nimi;
    
    
    
    public static class Hinta{
        private int paino;
        private double hinta;
        
        public Hinta(int i, double d) {
         this.paino = i; 
         this.hinta = d;
             
        }
    }


    /**
     * 
     * @param args ei käytössä
     */    
    public static void main(String[] args) {
        
        
    
        
        
        Hinta[] painot = {
                new Hinta(0,0), 
                new Hinta(50,0.60),
                new Hinta(100,0.90),
                new Hinta(250,1.30),
                new Hinta(500,2.10),
                new Hinta(1000,3.50),
                new Hinta(2000,5.50)
                
             };
        
       

        
        
       
        
        double hintapaino[][] = {
                {0, 50, 100, 250, 500, 1000, 2000,10000},
                {0, 0.60, 0.90, 1.30, 2.10, 3.50, 5.50,10}
        };        
        
        int paino = suurinKirjeenPaino(5.50, painot); 
        System.out.println("Tällä voi lähettää " + paino + " g"); 
        double hinta = postimaksu(1200,painot);
        System.out.println("Lähetys maksaa " + hinta + " e"); 
        double matPaino = taulukkoKirjeenPaino(5.60, hintapaino);
        System.out.println("Tällä voi lähettää " + matPaino + " g");
        System.out.println(postimaksu(2, painot));
        
    }
    

    private static double taulukkoKirjeenPaino(double rahaa, double[][] hintapaino) {
        for ( int i = 0; i < hintapaino[1].length; i++ ) {
        
            if ( hintapaino[1][i] >= rahaa) {
                return hintapaino[0][i-1];
            }
        }
            
        return 0;
    }
    /**
     * *
     * @param paino = paketin paino
     * @param painot = taulukko
     * @return kuinka paljon maksaa
     */
    private static double postimaksu(int paino, Hinta[] painot) {
        for (int i = 0; i < painot.length; i++) {
            if(painot[i].paino == paino) {
                return painot[i].hinta;
            }
            if(painot[i].paino >= paino) {
                return painot[i].hinta;
            }
        }
                
        return 0;
    }
    
    
    public Hinta lisaa(int paino, double hinta) {
        Hinta Hinta = new Hinta (paino, hinta);
        return Hinta;
    }

       /**
     * *
     * @param paino = paketin paino
     * @param painot = taulukko
     * @return kuinka paljon maksaa
     * @example <pre name="test">
     * Hinta[] painot = {
                new Hinta(0,0), 
                new Hinta(50,0.60),
                new Hinta(100,0.90),
                new Hinta(250,1.30),
                new Hinta(500,2.10),
                new Hinta(1000,3.50),
                new Hinta(2000,5.50)
                
             };
     * int k = suurinKirjeenPaino(5.50,painot);
     * k === 2000;
     * </pre>
     */
    public static int suurinKirjeenPaino(double rahaa, Hinta painot[]) {
        int paino = 0;
        

        for (int i = 0; i < painot.length; i++) {
            if(painot[i].hinta == rahaa) {
                return painot[i].paino;
            }
            if(painot[i].hinta >= rahaa) {
                return painot[i-1].paino;
            }
        }
                
        return paino;
    }


   
}