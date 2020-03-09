package demo.d10;




public class esiintymattesti {
	public static void main(String[] args) {
		char[] aakkoset = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','x','y','z'};
        int[] maara = new int [aakkoset.length];
        String s = "the quick brown fox jumps over the lazy dog";
        
        for (int i =0; i< s.length();i++) {
        	for(int j=0; j<aakkoset.length; j++) {
        		if (s.charAt(i) == aakkoset[j]) {
        			maara[j]++;
        		}
        	}
        }
        for(int i= 0 ; i< maara.length;i++) {
        	System.out.print(maara[i]);
        }
        
	}
}
        	
	

	


