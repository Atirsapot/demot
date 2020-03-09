package demo.d3;

import fi.jyu.mit.ohj2.Mjonot;
import fi.jyu.mit.ohj2.Syotto;

public class Jarjestelija {

	public static void main(String[] args) {
		String syöte = Syotto.kysy("Anna jono");
		Jarjestele1ja2(syöte);
		System.out.println(syöte);

	}
	
	public static String Jarjestele1ja2 (String rivi) {
		StringBuilder jarjestys = new StringBuilder(rivi);
	
		String eka = Mjonot.erota(jarjestys, ' ', true);
		
		
		String toka = Mjonot.erota(jarjestys, ' ', false);
		
		int jarkka= 0;
		jarkka = eka.compareToIgnoreCase(toka);
		
		if(jarkka > 0) {
			System.out.print(toka + " " + eka);
			
			
		}
		if(jarkka < 0) {
			System.out.println(eka + " " + toka);
			
		}
		return "";
		
		
	}
	
	
}
