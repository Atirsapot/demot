package demo.d5;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

public class Astia {

	public String nimi;
	public int tilavuus;
	public int nestetta;
	
    public Astia(String nimi, int tilavuus) {
		this.nimi = nimi;
		this.tilavuus = tilavuus;
		this.nestetta = 0;
	}
//  
//    *  John McLane (Bruce Willis) joutui elokuvassa Die Hard 3 ongelman eteen,
//    *  jossa piti kahdella vesikanisterilla mitata jonkin muun suuruinen
//    *  vesimÃ¤Ã¤rÃ¤ "tarkasti" ja nÃ¤in estÃ¤Ã¤ pommin rÃ¤jÃ¤htÃ¤minen.
//    *  Koska aika meni varsin tiukalle, on vastaavien tilanteiden harjoittelemiseksi
//    *  nyt tehty uusi menestysohjelma "Astia".
//    *
//    *  PelillÃ¤ demonstroidaan/harjoitellaan miten voidaan kahdella astialla,
//    *  5 l ja 8 l,  muodostaa kaikki mahdolliset tilavuudet 1-13 l.
//    *  PelissÃ¤ on kaksi varsinaista astiaa: a5 ja a8, sekÃ¤ kolmas apuastia
//    *  ampari.  NestettÃ¤ voidaan kerrallaan kaataa yhdestÃ¤ astiasta toiseen
//    *  joko siten, ettÃ¤ astian koko sisÃ¤ltÃ¶ tyhjennetÃ¤Ã¤n jos se mahtuu
//    *  tai astista kaadetaan sen verran ettÃ¤ toinen astia tulee tÃ¤yteen.
//    *
//    *  Tee ohjelma jota voitasiin kÃ¤yttÃ¤Ã¤ seuraavasti:
//    *<
    

	public static void main(String[] args) {
        Astia astiat[] = { new Astia("100", 100),
                           new Astia("5", 5), new Astia("3", 3) };
        Astia ampari = astiat[0];
        ampari.tayta();

       // tulostaOhje(astiat);

        while ( true ) {
            for (int i = 1; i < astiat.length; i++)
                System.out.println(astiat[i].getTilavuus()+" litran astiassa on "+
                                   astiat[i].getMaara() + " litraa nestettä");
            String rivi = Syotto.kysy("Mistä kaadetaan ja mihin");
            if ( rivi.length() == 0 ) break;

            StringBuilder sb = new StringBuilder(rivi);
            String mista = Mjonot.erota(sb);
            String mihin = Mjonot.erota(sb);
            
            int imista = etsi(astiat, mista);
            int imihin = etsi(astiat, mihin);
            
            
        
            
            if ( (imista < 0) || (imihin < 0) )
                nimiOhje(astiat, mista, mihin);
            else astiat[imista].kaada(astiat[imihin]);
        }
    }

	private static void tulostaOhje(Astia[] astiat) {
	System.out.println(astiat);
	
}
	private static void nimiOhje(Astia[] astiat, String mista, String mihin) {
	System.out.println(astiat + mista + mihin);
	}

	public void kaada(Astia astia) {
			
			
			
			
			int astiassatilaa = astia.tilavuus - astia.nestetta;
			if (astiassatilaa > this.nestetta) {
				astia.nestetta += this.nestetta;
				this.nestetta = 0;
			}
			if (astiassatilaa < this.nestetta) {
				astia.nestetta += astiassatilaa;
				this.nestetta -= astiassatilaa;
			}
			
}
	private static int etsi(Astia[] astiat, String mista) {
		int arvo = Integer.valueOf(mista);
		
		for (int i = 0; i < astiat.length; i++) {
			if (astiat[i].tilavuus == arvo) {
				return i;
			}
		}
		return -1;
			
		
	
}
	private int getMaara() {
		return this.nestetta;
	}
	
	private String getTilavuus() {
		return this.nimi;
	}
	
	private void tayta() {
		
		this.nestetta = 100;
		
	}


}
