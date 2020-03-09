package demo.d2;


public class Pisteet
{
    public static void main(String[] args)
    {
        System.out.println("joU");
        tarkasta("(T1-2:1.5]");
        tarkasta("[T1-2,1.5]");
        tarkasta("(T1-2:1.5)");
    }
    public static void tarkasta(String tarkastettava) {
    	String malli = "[T1-2:1.5]";
    	if(tarkastettava.charAt(0) != '[') {
    		System.out.println("väärä [ merkki");
    	}
    	if (tarkastettava.charAt(5) != ':') {
			System.out.println("väärä : merkki");
		}
    	if (tarkastettava.charAt(0) != '[' && tarkastettava.charAt(9) != ']'){
    		System.out.println("väärät sulut");
    	}
    }
}
