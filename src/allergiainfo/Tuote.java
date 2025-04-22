package allergiainfo;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * Vastuut:
 * Tietää tuotteen kentät (id, nimi, ravintolaId)
 * Osaa tarkistaa tietyn kentän oikeellisuuden
 * Osaa muuttaa merkkijonon tuotteen tiedoiksi
 * Osaa antaa merkkijonona tietyn kentän
 * Osaa laittaa merkkijonon tietyksi kentäksi
 * 
 * @author Viivi
 * @version 31.3.2025
 *
 */
public class Tuote {
    
    private         int      id               = 0;
    private         int      ravintolaId      = 0;
    private         String   nimi             = "";
    
    private static  int      seuraavaNro      = 1;
    
    
    /**
     * Alustaa tuotteen tiedot tyhjiksi
     */
    public Tuote() {
        //
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
        out.println(String.format("%03d", this.id) + " " + this.nimi + " " + String.format("%03d", this.ravintolaId));
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
        this.ravintolaId = 1;
    }
    
    
    /**
     * Asetetaan tunnusnumero
     * @param tunnusNro tuotteen tunnusnumero
     */
    public void asetaId(int tunnusNro) {
        this.id = tunnusNro;
        if( tunnusNro >= seuraavaNro ) seuraavaNro = tunnusNro + 1;
    }
    
    
    /**
     * Asetetaan nimi
     * @param n tuotteen nimi
     */
    public void asetaNimi( String n ) {
        this.nimi = n;
    }
    
    
    /**
     * Asetetaan ravintola id, eli yhdistetään tuote ravintolaan
     * @param ravintola ravintolan tunnusnumero
     */
    public void asetaRavintola(int ravintola) {
        this.ravintolaId = ravintola;
    }
    
    
    @Override
    public String toString() {
        return haeId() + "|" + haeRavintolaId() + "|" + haeNimi();
    }
    
    
    /**
     * Parsitaan tuotteen tiedot
     * @param rivi josta tuotteen tiedot otetaan
     * @example
     * <pre name="test">
     *  Tuote tuote = new Tuote();
     *  tuote.parse( "4|1|Quesadilla" );
     *  tuote.haeId() === 4;
     *  tuote.toString().startsWith( "4|1|Quesadilla" ) === true;
     *  
     *  tuote.rekisteroi();
     *  int n = tuote.haeId();
     *  tuote.parse( "" + ( n + 20 )); 
     *  tuote.rekisteroi();
     *  tuote.haeId() === n + 20 + 1;
     * </pre>
     */
    public void parse( String rivi ) {
        StringBuilder sb = new StringBuilder( rivi );
        asetaId( Mjonot.erota( sb, '|', haeId() ));
        asetaRavintola( Mjonot.erota( sb, '|', haeRavintolaId() ));
        asetaNimi( Mjonot.erota( sb, '|', this.nimi ));
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
