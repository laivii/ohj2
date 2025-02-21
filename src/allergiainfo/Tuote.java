package allergiainfo;

import java.io.PrintStream;

/**
 * 
 * @author Viivi
 * @version 21.2.2025
 *
 */
public class Tuote {
    private int     id = 0;
    private String  nimi = "";
    private int     ravintolaId = 0;
    
    
    /**
     * Alustaa tuotteen tiedot tyhjiksi
     */
    public Tuote() {
        // ei edes tarvitsisi
    }
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param out tietovirta johon tulostetaan
     */
    private void tulosta(PrintStream out) {
        out.println(String.format("%04d", id) + " " + nimi + " " + String.format("%04d", ravintolaId));
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuote t = new Tuote();
        Tuote t2 = new Tuote();
        
        //t.rekisteroi();
        //t2.rekisteroi();
        
        t.tulosta(System.out);
        
        //t.taytaTuoteTiedoilla();
        //t2.taytaTuoteTiedoilla();
        
        t.tulosta(System.out);
        t2.tulosta(System.out); 
    }
}
