package demo.d7;

public class Paikka {
	public static void main(String[] args) {
		etsiIndeksi("tässä/", '/');
		etsiIndeksi("/tossa", '/');
		etsiIndeksi("myös tässä *", '*');
		etsiIndeksi("ei tässä", 'P');
	}

	private static void etsiIndeksi(String jono, char merkki) {
		
		
		System.out.println(jono.indexOf(merkki)); 
		
		
	}
}
