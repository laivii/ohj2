package allergiainfo;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Viivi
 * @version 24.4.2025
 * 
 * - Tietää TuoteAllergeenin kentät
 * - Osaa antaa tietyn kentät tiedot
 * - Osaa asettaa tietyn kentän arvon
 * - Osaa muuttaa TuoteAllergeenin tiedot merkkijonoksi
 * - Osaa muuttaa merkkijonon TuoteAllergeeniksi
 */
public class TuoteAllergeeni {
    
    private int tuoteId         = 0;
    private int allergeeniId    = 0;

    
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
    }
    
    
    /**
     * Tulostetaan TuoteAllergeenin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta( PrintStream out ) {
        out.println(String.format( "%03d", this.tuoteId ) + " " + String.format( "%03d", this.allergeeniId ));
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
     * Asetetaan tuotteen id
     * @param id tuotteen id
     */
    public void asetaTuoteID(int id) {
        this.tuoteId = id;
    }
    
    
    /**
     * Asetetaan allergeenin id
     * @param id allergeenin id
     */
    public void asetaAllergeeniID( int id ) {
        this.allergeeniId = id;
    }
    
    
    @Override
    public String toString() {
        return haeTuoteID() + "|" + haeAllergeeniID();
    }
    
    
    /**
     * Parsitaan TuoteAllergeenin tiedot
     * @param rivi josta TuoteAllergeenin tiedot otetaan
     * @example
     * <pre name="test">
     *  TuoteAllergeeni ta = new TuoteAllergeeni();
     *  ta.parse( "1|1|1234" );
     *  ta.haeTuoteID() === 1;
     *  ta.toString().startsWith( "1|1|1234" ) === true;
     * </pre>
     */
    public void parse( String rivi ) {
        StringBuilder sb = new StringBuilder( rivi );
        asetaTuoteID( Mjonot.erota( sb, '|', haeTuoteID()) );
        asetaAllergeeniID( Mjonot.erota( sb, '|', haeAllergeeniID() ));
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
