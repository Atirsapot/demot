package demo.d3;

public class Esiintymat {
	
    public static void main(String[] args)
    {
    	
        System.out.println(poistaEsiintymat("kissaaa", "ss"));
        System.out.println(poistaEsiintymat("kissa", "se"));
        System.out.println(poistaEsiintymat("Huttunen", "tt"));
    }
    
    /**
     * Aliohjelma joka poistaa jonon esiintymät
     * @param jono josta poistettavaa etsitään ja poistetaan
     * @param poistettava on jono, jota etsitään, ja joka pooistetaan
     * @return uusi jono, josta poistettava on poistettu
     * @example
     * <pre name="test">
     *  poistaEsiintymat("iik", "ii") === "k";
     *  poistaEsiintymat("kakka", "kk") === "kaa";
     *  poistaEsiintymat("eclipse", "ec") === "lipse";
     * </pre>
     */
    public static String poistaEsiintymat(String jono, String poistettava)
    {
    	StringBuilder uusi = new StringBuilder();
        for(int i = 0; i < jono.length(); i++) {
        	
        	if(poistettava.charAt(0)==jono.charAt(i)) {
        		
        		if(poistettava.charAt(1)== jono.charAt(i+1)) {
        			
        			for(int j=0; j < i;j++) {
        				uusi.append(jono.charAt(j));
        			}
        			for(int k = i+2; k < (jono.length()); k++) {
        				uusi.append(jono.charAt(k));
        			}
        			String tulos = uusi.toString();
        			return (tulos);
        			
        			
        		}
        	}
        }
    	
        		return("ei");
    	
        
        
    }
}


    
