package demo.d4;
public class Henkilo {
	
	String nimi = "";
	String sukunimi = "";
	int syntymavuosi = 0;

 
public static void main(String[] args) {
     Henkilo aku = new Henkilo("Aku", "Ankka", 1934);
     Henkilo joona = new Henkilo("joona", "huttunen", 1997);
     System.out.println(aku.getNimi());  // tulostaa Aku Ankka
     System.out.println(aku.getSyntymavuosi());  // tulostaa 1934     
     System.out.println(aku.toString()); // tulostaa Aku|Ankka|1934
     System.out.println(joona.getNimi() + joona.getSyntymavuosi());
 
     }
public Henkilo ( String nimi, String sukunimi, int syntymavuosi) {
	this.nimi = nimi;
	this.sukunimi = sukunimi;
	this.syntymavuosi = syntymavuosi;
}
public String getNimi() {
	String kokonimi = nimi + sukunimi;
	return kokonimi;
}
public int getSyntymavuosi() {
	return syntymavuosi;
  	}
@Override
public String toString() {
	   return String.format("%s|%s|%02d", nimi,sukunimi, syntymavuosi);
}


}