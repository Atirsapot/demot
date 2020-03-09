package demo.d3;

public class Jokerimerkki {
	


	    public static void main(String[] args)
	    {
	        if ( onkoSamatKysymysmerkilla("Kissa","K?ss?") )
	            System.out.println("On samat");
	        else
	        	System.out.println("ei oo samat");
	        if ( onkoSamatKysymysmerkilla("Kasssa","K?ss?") )
	        	System.out.println("On samat");
	        else
	        	System.out.println("ei oo samat");
	    }

	    public static boolean onkoSamatKysymysmerkilla(String jono, String maski)
	    {
	    	
	    	char jokeri ='?';
	    	
	    	if ( jono.length()!= maski.length())
	    		return false;
	    	
	        for(int i=0; i<jono.length(); i++) {
	        	char x = jono.charAt(i);
	        	char y = maski.charAt(i);
	        	if(y==x){
	        		continue;
	        		}
	        		else {
	        			if (y == jokeri) {
	        				continue;
	        			}
	        			else {
	        			return false;
	        			}
	        	}
	        	
	        
	        }
	        return true;
	    }
}

	  



