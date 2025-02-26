package allergiainfo;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author Viivi
 * @version 24.2.2025
 *
 */
public class Ravintola {
    
    private int id      = 0;
    private String nimi = "";
    
    private static int seuraavaNro = 1;
    
    
    /**
     * Alustetaan ravintolan tiedot tyhjiksi
     */
    public Ravintola() {
        //ei edes tarvitsisi tätä
    }
    
    
    /**
     * Tulostetaan ravintolan tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%04d", this.id) + " " + this.nimi);
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
     * Rekisteröi ravintolan, eli 
     * - asettaa ravintolan id:n
     * - lisää seuraavaNro + 1
     * @example
     * <pre name="test">
     *  Ravintola r = new Ravintola();
     *  r.getId() === 0;
     *  r.rekisteroi();
     *  Ravintola r2 = new Ravintola();
     *  r2.getId() === 0;
     *  r2.rekisteroi();
     *  int n1 = r.getId();
     *  int n2 = r2.getId();
     *  n2 === n1+1;
     * </pre>
     */
    public void rekisteroi() {
        this.id = seuraavaNro;
        Ravintola.seuraavaNro++;
    }
    
    
    /**
     * @return palauttaa ravintolan id:n
     */
    public int getId() {
        return this.id;
    }
  
    
    /**
     * Täyttää ravintolan tiedot
     */
    public void taytaRavintolaTiedoilla() {
        this.nimi = "Taco Bell";
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Ravintola r1 = new Ravintola();
        Ravintola r2 = new Ravintola();
        
        r1.rekisteroi();
        r2.rekisteroi();
      
        r1.tulosta(System.out);
        r2.tulosta(System.out);
        
        r1.taytaRavintolaTiedoilla();
        r2.taytaRavintolaTiedoilla();
        
        r1.tulosta(System.out);
        r2.tulosta(System.out);
    }

}
