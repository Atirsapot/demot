package demo.d11;

import java.io.*;
import fi.jyu.mit.ohj2.*;
import java.net.URL;
import java.util.TreeMap;

/**
 * Ohjelmalla vaihdetaan valuuttoja
 * Versioon 1.1 lisätty Valuutat -luokan rajapintaa
 * uusia metodeja, mm. getVaihdettuMaara, getValuutat
 * @author Vesa Lappalainen
 * @version 1.0, 15.03.2003
 * @version 1.1, 09.04.2003
 */
public class ValuuttaMuunnos {
    // #DYNAMICIMPORT    

    /**
     * Muunnetaan valuuttaa
     * @param args ei käytössä
     * @throws IOException jos jokin menee pieleen tiedoston luvussa
     */
    public static void main(String[] args) throws IOException {
        Valuutat valuutat = new Valuutat();
        
            valuutat.lisaa(1.0,"mk");
            valuutat.lisaa(5.7,"$");
            valuutat.lisaa(5.9,"EUROA");
            valuutat.lisaa(0.6,"SKr");
        
        if (!valuutat.lue())
            return;

        ValuuttaNaytto naytto = new ValuuttaNaytto(valuutat);

        while (naytto.kysy()) {
            naytto.tulosta();
        }

    }

    /**
     * Luokka yhdelle valuutalle ja sen muunnoskertoimelle
     * @author Vesa Lappalainen
     * @version 1.0, 15.03.2003
     */
    public static class Valuutta {
        private double maara;
        private String valuutannimi;


        /**
         * Alustetaan valuutta
         * @param maara valuutan määrä
         * @param valuutannimi yksikkö
         * @example
         * <pre name="test">
         * Valuutta val = new Valuutta(2,"lati");
         * val.toString() === "2.00 lati";
         * </pre>
         */
        public Valuutta(double maara, String valuutannimi) {
            this.maara = maara;
            this.valuutannimi = valuutannimi;
        }


        /**
         * Alustetaan jonolla joka on muotoa 10  SKr
         * @param jono josta valuutan tiedot etsitään.
         * @example
         * <pre name="test">
         * Valuutta val = new Valuutta("10 SKr"); 
         * val.toString() === "10.00 SKr";
         * </pre>
         */
        public Valuutta(String jono) {
            parse(jono);
        }


        /**
         * Otetaan valuutan tiedot jonosta jonka muoto on 10 SKr
         * @param jono josta valuutan tiedot etsitään. 
         * <pre name="test">
         * Valuutta val = new Valuutta("");
         * val.parse("10     SKr");
         * val.toString() === "10.00 SKr";
         * </pre>
         */
        public final void parse(String jono) {
            StringBuilder sb = new StringBuilder(jono);
            maara = Mjonot.erotaDouble(sb, maara);
            valuutannimi = Mjonot.erota(sb, '|', valuutannimi);
        }
 

        /**
         * @return valuutan tiedot muodossa 10.00 SKr 
         */
        @Override
        public String toString() {
            return Mjonot.fmt(maara, 4, 2) + " " + valuutannimi;
        }


        /**
         * Verrataan vastaako valuutta hakumaskia.
         * Hakumaskin alkuosan pitää täsmätä. 
         * @param maski Hakumaski
         * @return true jos vastaa
         * <pre name="test">
         * Valuutta val = new Valuutta("10 SKr");
         * val.onko("SKr") === true;              
         * val.onko("s")   === true;
         * val.onko("Dkr") === false;
         * val.onko("kr")  === false;
         * val.onko(".kr") === true;
         * val.onko("$")   === false;
         * Valuutta val2 = new Valuutta("1.6 $");
         * val2.onko("$") === true;
         * val2.onko("s") === false;
         * </pre>
         */
        public boolean onko(String maski) {
            String umaski = maski.toUpperCase().replaceAll("\\$", "\\\\\\$") + ".*";
            String val = valuutannimi.toUpperCase();
            return val.matches(umaski);
        }


        /**
         * Vertaa onko parametrina tuodussa valuutassa sama yksikkö
         * @param obj verrattava valuutta 
         * @return true jos sama valuuttajono 
         * <pre name="test">
         * Valuutta val = new Valuutta("10 SKr");
         * Valuutta val2 = new Valuutta(0,"s");
         * Valuutta val3 = new Valuutta(0,"$");
         * val.onko(val2)  === true;
         * val.onko(val3)  === false;
         * </pre>
         */
        public boolean onko(Object obj) {
            if (obj instanceof String)
                return onko((String) obj);
            if (obj instanceof StringBuilder)
                return onko(((StringBuilder) obj).toString());
            if (obj instanceof StringBuffer)
                return onko(((StringBuffer) obj).toString());
            if (!(obj instanceof Valuutta))
                return false;
            return onko(((Valuutta) obj).valuutannimi);
        }


        /** @return valuutan kerroin (sama kuin määrä) */
        public double getKerroin() {
            return maara;
        }


        /** @return valuutan määrä                     */
        public double getMaara() {
            return maara;
        }


        /** @return Valuutan yksikkö                   */
        public String getValuutannimi() {
            return valuutannimi;
        }


        /**
         * Asetetaan valuutan yksikkö
         * @param valuutannimi asetettava yksikkö
         */
        public void setValuutta(String valuutannimi) {
            this.valuutannimi = valuutannimi;
        }

    }

    /**
     * Luokka valuuttojen tallentamiselle
     * @author Vesa Lappalainen
     * @version 1.0, 15.03.2003
     */
    public static class Valuutat {
        private int lkm = 0;
        private Valuutta alkiot[] = new Valuutta[20];

        // Creating an empty TreeMap 
        private TreeMap<Integer, Valuutta> v_treemap 
            = new TreeMap<Integer, Valuutta>(); 

        /**
         * Lisätään tietorakenteeseen uusi valuutta.
         * @param val lisättävä valuutta.
         * @example
         * <pre name="test">
         * Valuutat valuutat = new Valuutat();
         * Valuutta skr = new Valuutta("10 SKr");
         * valuutat.lisaa(new Valuutta("1 e"));
         * valuutat.lisaa(skr);
         * valuutat.lisaa(new Valuutta("1.6 $"));
         * valuutat.ekanValuutannimi() === "e";
         * valuutat.getKerroin("$") ~~~ 1.6;
         * valuutat.get("s") === skr;
         * valuutat.get("$") == skr === false;
         * </pre>
         */
        public void lisaa(double maara, String valuutannimi) {
        	Valuutta lisattava = new Valuutta(maara, valuutannimi);
            v_treemap.put(lkm,lisattava);
            lkm++;
        }


        


        /**
         * Palautetaan ensimmäisen tietorakenteessa olevan
         * valuutan yksikkö.
         * @return ensimmäisen valuutan yksikkö
         */
        public String ekanValuutannimi() {
            if (lkm <= 0)
                return "";
            int firstkey =  v_treemap.firstKey();
            return v_treemap.get(firstkey).getValuutannimi();
        }


        /**
         * Etsitään hakujonoa vastaava valuutta
         * @param valuutannimi etsittävän valuutan yksikkö
         * @return null jos ei löydy, muuten löytynyt valuutta.
         */
        public Valuutta get(String valuutannimi) {
            for (int i = 0; i < lkm; i++)
                if (v_treemap.get(i).onko(valuutannimi))
                    return v_treemap.get(i);
            return null;
        }


        /**
         * Etsitään hakujonoa vastaavan valuutan kerroin.
         * @param valuutannimi etsittävän valuutan yksikkö
         * @return valuutan kerroin tai 1.0 jo ei löydy.
         */
        public double getKerroin(String valuutannimi) {
            Valuutta val = get(valuutannimi);
            if (val == null)
                return 1.0;
            return val.getKerroin();
        }


        /**
         * Etsitään hakujonoa vastaavan valuutan kerroin.
         * @param valuutta etsittävä valuutan hakujono valuutassa
         * @return valuutan kerroin tai 1.0 jo ei löydy.
         */
        public double getKerroin(Valuutta valuutta) {
            return getKerroin(valuutta.getValuutannimi());
        }


        /**
         * Etsitään hakujonoa vastaavan valuutan yksikkö
         * @param valuutannimi etsittävän valuutan yksikkö
         * @return valuutan nimi tai "" jos ei löydy
         */
        public String getValuutannimi(String valuutannimi) {
            Valuutta val = get(valuutannimi);
            if (val == null)
                return "";
            return val.getValuutannimi();
        }


        /**
         * Etsitään hakujonoa vastaavan valuutan yksikkö
         * @param valuutta etsittävä valuutan hakujono valuutassa
         * @return valuutan kerroin tai 1.0 jo ei löydy.
         */
        public String getValuutta(Valuutta valuutta) {
            return getValuutannimi(valuutta.getValuutannimi());
        }


        /**
         * Luetaan valutuutat tietovirrasta.  
         * @param fi tietovirta  josta luetaan
         * @return true jos lukeminen onnistui
         * @throws IOException jos jokin menee pieleen tiedoston käsittelyssä. 
         */
        public boolean lue(BufferedReader fi) throws IOException {
            if (fi == null)
                return false;

            String jono;
            while ((jono = fi.readLine()) != null) {
                if ("".equals(jono))
                    continue;
               // lisaa(new Valuutta(jono)); 
            }

            return true;
        }


        /**
         * Luetaan valuutat tiedostosta tai www:stä.
         * Jos tiedoston nimi alkaa http-luetaan www:stä
         * @param nimi luettavan valuuttatiedoston nimi
         * @return true jos lukeminen onnistuu.
         * @throws IOException jos jokin menee pieleen tiedoston käsittelyssä.
         */
        @SuppressWarnings("resource") // suljetaan finallyssä
        public boolean lue(String nimi) throws IOException {
            BufferedReader fi = null;
            try {
                if (nimi.startsWith("http")) {
                    URL sivu = new URL(nimi);
                    InputStream in = sivu.openStream();
                    Reader reader = new InputStreamReader(in);
                    fi = new BufferedReader(reader);
                    return lue(fi);
                }
                fi = Tiedosto.avaa_lukemista_varten(nimi);
                return lue(fi);
            } finally {
                if (fi != null)
                    fi.close();
            }
        }


        /**
         * Luetaan valuutat.dat nimisestä tiedostosta valuutat.
         * @return true jos lukeminen onnistui false jos tiedosto ei aukea.
         * @throws IOException  jos jokin menee pieleen tiedoston käsittelyssä.
         */
        public boolean lue() throws IOException {
            return lue("valuutat.dat");
        }


        /**
         * Palautetaan haettavaa valuuttaa vastaava summa kantavaluutassa.
         * @param nimi  haettavan valuutan hakujono
         * @param maara rahamäärä joka muutetaan kantavaluuttaan.
         * @return rahamäärä muutettuna kantavaluuttaan.
         */
        public double getVaihdettuMaara(String nimi, double maara) {
            return getKerroin(nimi) * maara;
        }


        /**
         * Palautetaan valuutassa olevaa hakujonoa vastaava valuutta-olio
         * kantavaluuttana.
         * @param val valuutta jossa on hakujono ja määrä joka muutetaan kantavaluuttaan.
         * @return tiedot kantavaluuttana
         * @example
         * <pre name="test">
         * Valuutat valuutat = new Valuutat();
         * valuutat.lisaa(1,"e");
         * valuutat.lisaa(10,"SKr");
         * valuutat.lisaa(1.6,"$");
         * valuutat.getVaihdettuMaara("s",3.0) ~~~ 30.0;
         * valuutat.getVaihdettu(new Valuutta(3.0,"s")).toString() === "30.00 e";
         * valuutat.getVaihdettu(new Valuutta(3.0,"$")).toString() === "4.80 e";
         * </pre>
         */
        public Valuutta getVaihdettu(Valuutta val) {
            double vaihdettu_maara = getVaihdettuMaara(val.getValuutannimi(), val.getMaara());
            return new Valuutta(vaihdettu_maara, ekanValuutannimi());
        }


        /**
         * Palautetaan merkkijonotaulukkona kaikkien valuuttojen nimet
         * @return valuuttojen nimet merkkijonotaulukossa.
         * @example
         * <pre name="test">
         * Valuutat valuutat = new Valuutat();
         * valuutat.lisaa(1,"e");
         * valuutat.lisaa(10,"SKr");
         * valuutat.lisaa(1.6,"$");
         * String nimet[] = valuutat.getValuuttojenNimet();
         * nimet.length === 3;
         * nimet[0] === "e";
         * nimet[1] === "SKr";
         * nimet[2] === "$";
         * </pre>
         */
        public String[] getValuuttojenNimet() {
            String nimet[] = new String[lkm];
            for (int i = 0; i < lkm; i++)
                nimet[i] = alkiot[i].getValuutannimi();
            return nimet;
        }

    }

    /**
     * Näyttöluokka valuuttojen käyttämiseksi konsolisovelluksesta.
     * @author Vesa Lappalainen
     * @version 1.0, 15.03.2003
     * 
     * @example
     * <pre name="test">
     * @example
     * <pre name="test">
     * #import fi.jyu.mit.ohj2.Suuntaaja;
     *     
     * Valuutat valuutat = new Valuutat();
     * valuutat.lisaa(1,"e");
     * valuutat.lisaa(10,"SKr");
     * valuutat.lisaa(1.6,"$");
     * ValuuttaNaytto naytto = new ValuuttaNaytto(valuutat);
     * 
     * Suuntaaja.StringInput si = new Suuntaaja.StringInput("");  
     * Suuntaaja.StringOutput so = new Suuntaaja.StringOutput();
     *
     * si.input("");       naytto.kysy() === false; 
     * si.input("loppu");  naytto.kysy() === false;
     * so.reset(); 
     * si.input("3 s");    naytto.kysy() === true;
     * so.reset(); naytto.tulosta(); so.ero("3.00 SKr on 30.00 e\n") === null; 
     * si.input("3 $");    naytto.kysy() === true;
     * so.reset(); naytto.tulosta(); so.ero("3.00 $ on 4.80 e\n") === null; 
     * si.input("2");      naytto.kysy() === true;
     * so.reset(); naytto.tulosta(); so.ero("2.00 $ on 3.20 e\n") === null; 
     * si.input("k lati"); naytto.kysy() === true; // Yksikköä ei löydy
     * so.reset(); naytto.tulosta(); so.ero("2.00  on 2.00 e\n") === null; 
     * si.input("");       naytto.kysy() === false; 
     * 
     * si.palauta(); so.palauta();
     * 
     * </pre>
     * </pre>
     */
    public static class ValuuttaNaytto {
        private final Valuutat valuutat;
        private final Valuutta valuutta = new Valuutta("");


        /**
         * Alustetaan näyttö käyttämään valuuttataulukkoa.
         * @param valuutat valuuttataulukko jota käytetään.
         */
        public ValuuttaNaytto(Valuutat valuutat) {
            this.valuutat = valuutat;
        }


        /**
         * Kysytään valuutan tietoja.
         * @return true jos kysely onnistui.
         */
        public boolean kysy() {
            String jono = Syotto.kysy("Määrä ja valuutta");

            if ("".equals(jono))
                return false;
            if ("loppu".equals(jono))
                return false;

            valuutta.parse(jono);
            String val = valuutat.getValuutta(valuutta);
            valuutta.setValuutta(val);

            return true;
        }


        /**
         * Tulostetaan kysytyn valuutan tiedot.
         */
        public void tulosta() {
            Valuutta vaihdettu = valuutat.getVaihdettu(valuutta);
            System.out.println(valuutta + " on " + vaihdettu);
        }

    }

}