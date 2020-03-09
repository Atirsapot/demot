package demo.d7;
public class Merkkijonot {

   public static void main(String[] args)
   {
	    OnkoMuita("kissa", "aik");
	    OnkoMuita("kissa", "aiks");
	    etsiIndeksi("pöllö", 'ö');
	    onko("kissa", "ibm");
	    onko("kissa", "pc");
    }
        // jono="kissa" k="aik" -> on, k="aiks" -> ei ol
        public static boolean OnkoMuita(String sana, String k) {
        	
        	for(int i = 0; i < k.length(); i++) {
        		
        		System.out.println(k.charAt(i));
        		if(sana.indexOf(k.charAt(i)) == -1) {
        			System.out.println("on muita");
        			return true;
        			
        		}
        		
        		
        	}
        	
        	return false;
        }
        	
        	
        

             // "C:\mytemp\ohj2\vesal\Koe.java" viimeinen '\'-merkki
  	    
		private static int etsiIndeksi(String jono, char merkki) {
		    int vIndeksi = -1;
		
		    for (int i = 0; i < jono.length(); i++) {
			if(jono.charAt(i) == merkki) {
				vIndeksi = i;
			}
		    }
		
		        if (vIndeksi < 0) {
			    System.out.println("ei oo merkkiä");
		    }
		   return vIndeksi;
	    }

    // jono="kissa" k="ibm" -> on , k="pc"  -> ei ole
    public static boolean onko(String sana, String etsitty) {
    
       if(sana.contains(etsitty))
           return true;
    return false;
  
      
    }
   


}

