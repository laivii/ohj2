package allergiainfo;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Vastuut:
 * - tietää tuoteAllergeenin kentät
 * - osaa tarkistaa kentän oikeellisuuden
 * @author Viivi
 * @version 7.3.2025
 *
 */
public class TuoteAllergeeni {
    
    private int tuoteId         = 0;
    private int allergeeniId    = 0;
    private int yksiloivaNro    = 0;

    
    /**
     * Alustetaan TuoteAllergeeni
     */
    public TuoteAllergeeni() {
        //Ei tarvita vielä mitään
    }
    
    
    /**
     * Alustetaan tietyn tuotteen allegeeni
     * @param tuoteId tuotteen id
     * @param allergeeniId allergeenin id
     */
    public TuoteAllergeeni(int tuoteId, int allergeeniId) {
        this.tuoteId        = tuoteId;
        this.allergeeniId   = allergeeniId;
    }
    
    
    /**
     * Apumetodi, jolla saadaan täytettyä testiarvot TuoteAllergeenille
     * @param tuote tuotteen id
     * @param allergeeni allergeenin id
     */
    public void taytaTuoteAllergeeniTiedoilla(int tuote, int allergeeni) {
        this.tuoteId      = tuote;
        this.allergeeniId = allergeeni;
        this.yksiloivaNro = (int)(Math.random() * 9999);
    }
    
    
    /**
     * Tulostetaan TuoteAllergeenin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta( PrintStream out ) {
        out.println(String.format( "%03d", this.tuoteId ) + " " + String.format( "%03d", this.allergeeniId ) + " " + String.format( "%03d",this.yksiloivaNro ));
    }
    
    
    /**
     * Tulostetaan TuoteAllergeenin tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * Palautetaan mille tuotteelle allergeeni kuuluu
     * @return tuotteen id:n
     */
    public int haeTuoteID() {
        return this.tuoteId;
    }
    
    
    /**
     * Palautetaan mikä allergeeni kyseessä
     * @return allergeenin id
     */
    public int haeAllergeeniID() {
        return this.allergeeniId;
    }
    
    
    /**
     * Testataan TuoteAllergeeni luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        TuoteAllergeeni ta = new TuoteAllergeeni();
        ta.taytaTuoteAllergeeniTiedoilla(1,1);
        ta.tulosta(System.out);
    }

}
