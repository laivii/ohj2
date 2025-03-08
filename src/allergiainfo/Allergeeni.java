package allergiainfo;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Viivi
 * @version 26.2.2025
 *
 */
public class Allergeeni {
    
    private        int      id            = 0;
    private        String   nimi          = "";
    private        int      yksiloivaNro  = 0;
    
    private static int      seuraavaNro   = 1;
    
    
    /**
     * Alustetaan allergeeni tyhjäksi
     */
    public Allergeeni() {
        //Ei tarvitsisi tätä
    }
    
    
    /**
     * Rekisteröidään allergeeni, eli annetaan sille id
     * @example
     * <pre name="test">
     *  Allergeeni a1 = new Allergeeni();
     *  a1.rekisteroi(); a1.haeId() === 1;
     *  
     *  Allergeeni a2 = new Allergeeni();
     *  a2.rekisteroi(); a2.haeId() === 2;
     *  
     *  Allergeeni a3 = new Allergeeni();
     *  a3.rekisteroi(); a3.haeId() === 3;
     * </pre>
     */
    public void rekisteroi() {
        this.id = seuraavaNro;
        seuraavaNro++;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot allergeenille
     */
    public void taytaAllergeeniTiedoilla() {
        this.nimi = "Laktoosi";
        this.yksiloivaNro = (int)(Math.random() * 9999);
    }
    
    
    /**
     * @return allergeenin id
     */
    public int haeId() {
        return this.id;
    }
    
    
    /**
     * Tulostetaan allergeenin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%02d", this.id) + " " + this.nimi + " " + String.format("%03d", this.yksiloivaNro));
    }
    
    
    /**
     * Turha, tehty valmiiksi jos tarvetta myöhemmin
     * Tulostetaan ravintolan tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta( new PrintStream(os));
    }
    
    
    /**
     * Allergeeni luokan testaus
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Allergeeni a1 = new Allergeeni();
        Allergeeni a2 = new Allergeeni();
        Allergeeni a3 = new Allergeeni();
        
        a1.rekisteroi();
        a1.taytaAllergeeniTiedoilla();
        a2.rekisteroi();
        a2.taytaAllergeeniTiedoilla();
        a3.rekisteroi();
        a3.taytaAllergeeniTiedoilla();
        
        a1.tulosta(System.out);
        a2.tulosta(System.out);
        a3.tulosta(System.out);
    }
}
