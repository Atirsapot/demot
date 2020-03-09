package demo.d9;
import java.io.*;

public class Tiedostostakopiointi {

    public static void main(String[] args) throws FileNotFoundException {
        
        String luettavaTied = args[0];
        String kirjTied = args[1];
        
        lue(luettavaTied, kirjTied);      
    }   
    public static void lue(String tiedosto, String tiedosto2){
        try (BufferedReader fi = new BufferedReader(new FileReader(tiedosto))) {
            String s;
            while ((s = fi.readLine()) != null) {
                try {
                    String rivi = s;
                    //LUVUN TARKISTUS
                    int valilyonti = rivi.indexOf(' ');
                    int luku = (Integer.parseInt(rivi.substring(0,valilyonti)));
                    
                    if(luku >= 30) {
                       kirjoita(tiedosto2, s);
                    }
                    
                } catch (NumberFormatException ex) {
                       // Hylätään
                    }
            }
            }   catch (FileNotFoundException ex) {
                System.err.println("Tiedosto ei aukea! " + ex.getMessage());
                return;
                
        
            }  catch (IOException ex) {
                System.err.println("Virhe tiedostoa luettaessa! " + ex.getMessage());       
            }
    }
   public static void kirjoita(String tiedosto, String kirjoitettava){

                        try (BufferedWriter fo = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tiedosto,true), "UTF-8"))){
                            fo.write(kirjoitettava + "\n");
                        }  catch (IOException ex) {
                            System.err.println("Tiedosto ei aukea: " + ex.getMessage());
                        }
   }

        
        
}


	

	
				
			