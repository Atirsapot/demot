package demo.d9;

public class pisinNouseva {
  /**
   * Testataan pisinNouseva-aliohjelmaa
   * @param args ei käytössä
   */	
	public static void main(String[] args) {
		System.out.println("pisin jono: " + pisinNouseva("abajiuxac"));
		System.out.println("pisin jono: " + pisinNouseva("kissa"));
		
		
		
	}
	/**
	 * 
	 * @param jono
	 * @return
	 * @example
     * <pre name="test">
     *   String jono = "joona"
     *   String jono2 = "mikko"; 
     *   pisinNouseva(jono) === 3;
     *   pisinNouseva(jono2) === 4;
     * </pre>
	 */
    private static int pisinNouseva(String jono) {
	    int pisinJono =0;
	    int jononPituus = 0;
    	for (int i = 1; i < jono.length(); i++ ) {
    		if (jono.charAt(i-1) <= jono.charAt(i)) {
    			jononPituus += 1;
    			if(jononPituus > pisinJono) {
    				pisinJono = jononPituus;
    			}    			
    			
    		}
    		else {
    			jononPituus = 0;
    		}
	    }
	    return pisinJono+1;
}

	

}