package allergiainfo;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Vastuut:
 * Tietää tuotteen kentät (id, nimi, ravintolaId)
 * Osaa tarkistaa tietyn kentän oikeellisuuden
 * Osaa muuttaa merkkijonon tuotteen tiedoiksi
 * Osaa antaa merkkijonona tietyn kentän
 * Osaa laittaa merkkijonon tietyksi kentäksi
 * 
 * @author Viivi
 * @version 6.3.2025
 *
 */
public class Tuote {
    
    private         int      id               = 0;
    private         int      ravintolaId      = 0;
    private         String   nimi             = "";
    private         int      yksiloivaNumero  = -1;
    
    private static  int      seuraavaNro      = 1;
    
    
    /**
     * Alustaa tuotteen tiedot tyhjiksi
     */
    public Tuote() {
        // ei edes tarvitsisi
    }
    
    
    /**
     * Alustetaan tietyn ravintolan tuote
     * @param ravintolaNro ravintolan viitenumero
     */
    public Tuote( int ravintolaNro ) {
        this.ravintolaId = ravintolaNro;
    }
    
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", this.id) + " " + this.nimi + " " + String.format("%03d", this.ravintolaId) + " " + String.format("%03d", this.yksiloivaNumero));
    }
    
    
    /**
     * Tulostetaan tuotteen tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta( new PrintStream(os));
    }
    
    
    /**
     * Rekisteröidään tuote (eli annetaan sille yksilöllinen id)
     * @return palauttaa tuotteen id:n
     * @example
     * <pre name="test">
     *  Tuote t = new Tuote();
     *  t.haeId() === 0;
     *  t.rekisteroi();
     *  Tuote t2 = new Tuote();
     *  t2.rekisteroi();
     *  int n1 = t.haeId();
     *  int n2 = t2.haeId();
     *  n1 === n2-1;
     * </pre>
     */
    public int rekisteroi() {
        this.id = seuraavaNro;
        Tuote.seuraavaNro++;
        return this.id;
    }
    
    
    /**
     * @return palauttaa tuotteen id:n
     */
    public int haeId() {
        return this.id;
    }
    
    
    /**
     * @return tuotteen nimi
     */
    public String haeNimi() {
        return this.nimi;
    }
    
   
    /**
     * @return palauttaa tuotteen ravintolan id:n
     */
    public int haeRavintolaId() {
        return this.ravintolaId;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot tuotteelle
     */
    public void taytaTuoteTiedoilla() {
        this.nimi = "Quesadilla";
        this.yksiloivaNumero = (int)(Math.random()*9999);
        this.ravintolaId = 1;
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuote t = new Tuote();
        Tuote t2 = new Tuote();
        
        t.rekisteroi();
        t2.rekisteroi();
        
        t.tulosta(System.out);
        
        t.taytaTuoteTiedoilla();
        t2.taytaTuoteTiedoilla();
        
        t.tulosta(System.out);
        t2.tulosta(System.out); 
    }
}
