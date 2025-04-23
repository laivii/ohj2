package allergiainfo;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Viivi
 * @version 26.2.2025
 * @version 23.4.2025
 */
public class Allergeeni {
    
    private        int      id            = 0;
    private        String   nimi          = "";
    
    private static int      seuraavaNro   = 0;
    
    
    /**
     * Alustetaan allergeeni tyhjäksi
     */
    public Allergeeni() {
        //Ei tarvitsisi tätä
    }
    
    
    /**
     * Alustetaan allergeeni
     * @param nimi allergeenin nimi
     */
    public Allergeeni(String nimi) {
        this.nimi = nimi;
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
    }
    
    
    /**
     * @return allergeenin id
     */
    public int haeId() {
        return this.id;
    }
    
    
    /**
     * @return allergeenin nimi
     */
    public String haeNimi() {
        return this.nimi;
    }
    
    
    /**
     * Asetetaan allergeenin id
     * @param i allergeenin id
     */
    public void asetaId( int i ) {
        this.id = i;
        if( i >= seuraavaNro ) seuraavaNro = i + 1;
    }
    
    
    /**
     * Asetetaan allergeenin nimi
     * @param n allergeenin nimi
     */
    public void asetaNimi ( String n ) {
        this.nimi = n;
    }
    
    
    /**
     * Tulostetaan allergeenin tiedot
     * @param out tietovirta johon tulostetaan
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%02d", this.id) + " " + this.nimi );
    }
    
    
    /**
     * Turha, tehty valmiiksi jos tarvetta myöhemmin
     * Tulostetaan ravintolan tiedot
     * @param os tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta( new PrintStream(os));
    }
    
    
    @Override
    public String toString() {
        return haeId() + "|" + haeNimi();
    }
    
    
    /**
     * Parsitaan allergeenin tiedot
     * @param rivi josta allergeenin tiedot otetaan
     * @example
     * <pre name="test">
     *  Allergeeni allergeeni = new Allergeeni();
     *  allergeeni.parse( "1|Gluteeni" );
     *  allergeeni.haeId() === 1;
     *  allergeeni.toString().startsWith( "1|Gluteeni" ) === true;
     *  
     *  allergeeni.rekisteroi();
     *  int n = allergeeni.haeId();
     *  allergeeni.parse( "" + ( n + 20 ));
     *  allergeeni.rekisteroi();
     *  allergeeni.haeId() === n + 20 + 1;
     * </pre>
     */
    public void parse( String rivi ) {
        StringBuilder sb = new StringBuilder( rivi );
        asetaId( Mjonot.erota( sb, '|', haeId() ));
        asetaNimi( Mjonot.erota( sb, '|', haeNimi() ));
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
