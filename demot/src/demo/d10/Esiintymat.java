package demo.d10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;



public class Esiintymat {
	
	public static void main(String[] args) {
        String tiedNimi = "Koe.txt";
        if (args.length > 0 ) tiedNimi = args[0];

        try (Scanner fi = new Scanner(new FileInputStream(new File(tiedNimi)))) { // Jotta UTF8/ISO-8859 toimii
            while ( fi.hasNext() ) {
                try {
                    String s = fi.nextLine();
                    int x = 1;
                    System.out.println("/*"+ x + "*/" + s);
                    x++;                  	
                    }
                 catch (NumberFormatException ex) {
                    // Hylätään
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Tiedosto ei aukea! " + ex.getMessage());
            return;
        }
        
	}

}
