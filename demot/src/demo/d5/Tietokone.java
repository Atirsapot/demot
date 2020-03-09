package demo.d5;

public class Tietokone {

	public int ram;
	public int levytila;
	public String merkki;
	public String tyyppi;
	
	
	//konsturktori
	public Tietokone(int ram, int levytila, String merkki, String tyyppi){
		this.ram = ram;
		this.levytila = levytila;
		this.merkki = merkki;
		this.tyyppi = tyyppi;
	}
	// parametriton konstruktori
	public Tietokone () {
		this.ram = 0;
		this.levytila = 0;
		this.merkki = "";
		this.tyyppi = "";
	}
	
	public static void main(String[] args) {
		Tietokone mac = new Tietokone(8, 512, "Apple", "Läppäri");
		Tietokone oletus = new Tietokone();
	}
}
