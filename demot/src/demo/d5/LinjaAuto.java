package demo.d5;

import java.io.OutputStream;
import java.io.PrintStream;

public class LinjaAuto {
	private int kkLKM;
	private int vpLKM;
	public static void main(String[] args) {
        LinjaAuto pikkubussi = new LinjaAuto(10);
        LinjaAuto isobussi = new LinjaAuto(45);
        pikkubussi.lisaa(4);    pikkubussi.tulosta(System.out); // 10,4,6
        isobussi.lisaa(30);     isobussi.tulosta(System.out);   // 45,30,15
        System.out.println();
        int yli = pikkubussi.lisaa(15);
        isobussi.lisaa(yli);
        pikkubussi.tulosta(System.out); 	                 // 10,10,0
        isobussi.tulosta(System.out);                           // 45,39,6
        if ( pikkubussi.getTilaa() > 0 )
            System.out.println("Pieneen bussiin mahtuu!");   // ei tulosta
        if ( isobussi.tilaa() )
            System.out.println("Isoon bussiin mahtuu!");     // tulostaa
        int vajaa = pikkubussi.vahenna(12); 				   // vajaa = -2		
        if ( vajaa < 0 )
            System.out.println("Pikkubussissa ei edes ole näin montaa!");
        pikkubussi.tulosta(System.out);                         // 10,0,10
   }
	
	public LinjaAuto(int kokonaispaikat) {
		this.kkLKM = kokonaispaikat;
		this.vpLKM = kkLKM;
		
	}
	
	 private boolean tilaa() {
		if (this.vpLKM > 0) {
		return true;
		}
		else return false;
	}
	private int getTilaa() {
		return this.vpLKM;
		
	}
	public void tulosta(OutputStream os) {
	        PrintStream out = new PrintStream(os);
	        int kk = this.kkLKM;
	        int vp = this.vpLKM;
	        int matkustajat = (kk - vp);	  
	        out.println("kokonaispaikat : " + kk );
	        out.println("matkustajien määrä: " + matkustajat );
	        out.println("vapaiden paikkojen määrä: " + vp);
	        out.println();
	    }
	
	public int lisaa(int matkustajat) {
		
		
		this.vpLKM -= matkustajat;
		if(vpLKM < 0) {			
			int yli = (0 + vpLKM);
			vpLKM = 0;
		}
		
		return vpLKM;
	}
	
	public int vahenna(int matkustajat) {
		this.vpLKM += matkustajat;
		if(vpLKM > kkLKM) {
			vpLKM = kkLKM;
		}
		return this.vpLKM;
	}
}


