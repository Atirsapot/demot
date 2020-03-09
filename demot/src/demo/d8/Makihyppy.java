package demo.d8;



/**
 * Luokkia mäkihyppyävarten
 * Poista rivit @SuppressWarnings("unused")
 * @author Topias
 * @version 1.0, 28.02.2020
 */
public class Makihyppy {

    /**
     * Luokka yhtä mäkihypyn kierrosta varten
     */
    @SuppressWarnings("unused")
    public static class Kierros {
        private final static int TUOMAREITA = 5;
        private double pituus; // hyppyjen pituudet metreinä
        private final double tuomarit[] = new double[TUOMAREITA];// tuomaripisteet

        /** Kierroksen alustaminen   */
        public Kierros() {
            this.pituus = 0;
            for(int i = 0; i < TUOMAREITA; i++) {
            this.tuomarit[i] = 0;
            }
            
        }

		public void setPituus(int pituus2) {
			this.pituus = pituus2;
			
		}

		public void setTuomari(int tuomari, double pisteet) {
			tuomarit[tuomari-1] = pisteet;
			
		}

		public double getPituus() {
			double pituus = this.pituus;
			return pituus;
		}

		public double getPisteet(int tuomari) {
			double pisteet = this.tuomarit[tuomari];
			return pisteet;
		}
    }

    /**
     * Luokka mäkihypyn yhden kilpailijan tulosta varten.
     * Sisältää monta kierosta.
     */
    @SuppressWarnings("unused")
    public static class Tulos {
        private final static int KIERROKSIA = 2;
        private final Kierros kierros[] = new Kierros[KIERROKSIA];

        /** Tuloksen alustaminen */
        public Tulos() {
        	this.kierros[0] = new Kierros();
        	this.kierros[1] = new Kierros();
        }

		public void setPituus(int kierros1, int pituus) {
			if (kierros1 == 1) {
				kierros[0].setPituus(pituus);
			}
			else kierros[1].setPituus(pituus);
		}

		public void setTuomari(int kierros1, int tuomari, double pisteet) {
			if (kierros1 == 1) {
				kierros[0].setTuomari(tuomari,pisteet);
			}
			else
				kierros[1].setTuomari(tuomari,pisteet);
			
		}

		public double getPituus(int kierros) {
			double pituus = this.kierros[kierros].getPituus();
			return pituus;
		}

		public double getPisteet(int kierros, int tuomari) {
			double pisteet = this.kierros[kierros].getPisteet(tuomari);
			return pisteet;
		}
    }


    /**
     * Luokka yhtä mäkihypyn kilpailijaa varten.
     * Sisältää mm. tuloksen.
     */
    @SuppressWarnings("unused")
    public static class Kilpailija {
        private String nimi;
        private int nro;
        private final Tulos tulos = new Tulos();

        /**
         * Kilpailijan alustaminen
         * @param nimi kilpailijan nimi
         * @param nro kilpailijan kilpailunumero
         */
        public Kilpailija(String nimi,int nro) {
            this.nimi = nimi;
            this.nro = nro;
        }

        /** Tulostaa kilpailijan tiedot */
        @SuppressWarnings("static-method")
        public void tulosta() {
        	
        	double tp1[] = 			   {this.tulos.getPisteet(0,0), 
        							    this.tulos.getPisteet(0,1),
        							    this.tulos.getPisteet(0,2),
        							    this.tulos.getPisteet(0,3),
        							    this.tulos.getPisteet(0,4),
        							   };
        	
        	double tp2[] = 			   {this.tulos.getPisteet(1,0), 
   										this.tulos.getPisteet(1,1),
   										this.tulos.getPisteet(1,2),
   										this.tulos.getPisteet(1,3),
   										this.tulos.getPisteet(1,4) 
        								};
        	
        	
        	System.out.println(this.nro + ": " + this.nimi);
        	
        	System.out.println("Kierros 1:   " + this.tulos.getPituus(0)+ "m." + "Tuomarit: " + tp1[0]+" " + tp1[1]+" " +tp1[2]+" "+ tp1[3]+" "+ tp1[4] + " = " + laskeyhteen(tp1, this.tulos.getPituus(0)) +  "p." );
        	System.out.println("Kierros 2:   " + this.tulos.getPituus(1)+ "m." + "Tuomarit: " + tp2[0]+" " + tp2[1]+" " +tp2[2]+" "+ tp2[3]+" "+ tp2[4] + " = " + laskeyhteen(tp1, this.tulos.getPituus(0)) +  "p." );
        	
        }
        	
            
        
        
        
        

		private double laskeyhteen(double pisteet[], double pituus) {
			double yhteensa = 0;
			double K_PISTE = 60.0;
			int METRIKERROIN = 1;
			//double summa = demo.d7.Rajat.summaHuonoinJaParasPois(pisteet);
//			yhteensa = summa > 0 ? METRIKERROIN * pituus - K_PISTE + summa : 0;
			
			return yhteensa;
		}

		public void setPituus(int kierros, int pituus) {
			tulos.setPituus(kierros,pituus);
			
		}

		public void setTuomari(int kierros, int tuomari, double pisteet) {
			tulos.setTuomari(kierros, tuomari, pisteet);
			
		}

		

    }

    /**
     * Tyhjä aliohjelma kilpailua varten
     */
    @SuppressWarnings("static-method")
    public void kisa() { 
        Kilpailija toni = new Kilpailija("Toni",3); 
        Kilpailija matti = new Kilpailija("Matti",7); 
        toni.tulosta(); 
        matti.tulosta(); 

        toni.setPituus(1,107);       // 1. kierros, 107 m
        toni.setPituus(2,100);       // 2. kierros, 100 m
        toni.setTuomari(2,1,19.0);   // 2. kierros, 1. tuomari, 19 p 
        toni.setTuomari(2,2,18.0);   // 2. kierros, 2. tuomari, 18 p
        toni.setTuomari(2,3,19.5); 
        toni.setTuomari(2,4,18.0); 
        toni.setTuomari(2,5,20.0); 

        matti.setPituus(1,125); 
        matti.setTuomari(1,1,20.0); 
        matti.setTuomari(1,2,20.0); 
        matti.setTuomari(1,3,20.0); 
        matti.setTuomari(1,4,20.0); 
        matti.setPituus(2,109); 
        matti.setTuomari(2,1,20.0); 
        matti.setTuomari(2,2,20.0); 
        matti.setTuomari(2,3,20.0); 
        matti.setTuomari(2,4,20.0); 

        toni.tulosta(); 
        matti.tulosta(); 

      } 

      public static void main(String[] args)  { 
        Makihyppy kisa = new Makihyppy(); 
        kisa.kisa(); 
      }


}