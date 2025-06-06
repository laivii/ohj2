package allergiainfo;

import java.util.List;

/**
 * @author Viivi
 * @version 24.4.2025
 * 
 * Huolehtii Ravintolat, Tuotteet, TuoteAllergeenit, Allergeenit -luokkien välisestä
 * yhteistyöstä ja välittää näytä tietoja pyydettäessä
 * 
 * Lukee ja kirjoittaa Allergiainfon tiedot tiedostoihin avustajien avulla
 */
public class Allergiainfo {
    
    private Tuotteet         tuotteet         = new Tuotteet();
    private Allergeenit      allergeenit      = new Allergeenit();
    private TuoteAllergeenit tuoteAllergeenit = new TuoteAllergeenit();
    private Ravintolat       ravintolat       = new Ravintolat();
    
//  Paikka muokattavan tuotteen "säilyttämiselle"
    private Tuote            muokattava       = null;
    
    
    /**
     * Lisää uusi tuote
     * @param tuote lisättävä tuote
     * @throws SailoException jos lisääminen ei onnistu
     * 
     */
    public void lisaa(Tuote tuote) throws SailoException {
        this.tuotteet.lisaa(tuote);
    }
    
    /**
     * Lisää uusi allergeeni
     * @param allergeeni joka halutaan lisätä
     * @throws SailoException jos lisääminen ei onnistu
     */
    public void lisaa(Allergeeni allergeeni ) throws SailoException {
        this.allergeenit.lisaa(allergeeni);
    }
    
    /**
     * Lisätään uusi tuoteAllergeeni eli yhdistetään allergeeni tuotteeseen
     * @param tuoteID tuotteen id
     * @param allergeeniID allergeenin id
     */
    public void lisaa(int tuoteID, int allergeeniID ) {
        TuoteAllergeeni ta = new TuoteAllergeeni( tuoteID, allergeeniID );
        this.tuoteAllergeenit.lisaa( ta );
    }
    
    
    /**
     * Poistaa tuotteen taulukosta
     * @param id poistettavan tuotteen id 
     */
    public void poistaTiettyTuote(int id) {
        this.tuotteet.poistaTietty(id);
    }
    
    
    /**
     * Poistetaan TuoteAllergeeni
     * @param ta TuoteAllergeeni, joka halutaan poistaa
     */
    public void poistaTuoteAllergeeni( TuoteAllergeeni ta ) {
        this.tuoteAllergeenit.poista( ta );
    }
    
    
    /**
     * Palauttaa tuotteiden määrän
     * @return tuotteiden lukumäärä
     */
    public int haeTuotteita() {
        return this.tuotteet.haeLkm();   
    }
    
    /**
     * Palauttaa allergeenien määrän
     * @return allergeenien lukumäärä
     */
    public int haeAllergeeneja() {
        return this.allergeenit.haeLkm();
    }
    
    
    /**
     * Palauttaa ravintoloiden määrän
     * @return ravintoloiden lukumäärän
     */
    public int haeRavintoloita() {
        return this.ravintolat.haeLkm();
    }
    
    
    /**
     * Palauttaa listana tuotteen allergeenit
     * @param id tuotteen id
     * @return lista tuotteen allergeeneista
     */
    public List<TuoteAllergeeni> haeTuotteenAllergeenit(int id) {
        return this.tuoteAllergeenit.annaTuotteenAllergeenit( id );
    }
    
    
    /**
     * Palauttaa tuotteen id:n perusteella
     * @param id tuotteen id
     * @return Tuote
     * @throws SailoException jos tuotetta ei löydä id:llä
     */
    public Tuote haeTuoteIdlla( int id ) throws SailoException {
        return this.tuotteet.haeTuoteIdlla( id );
    }
    
    
    /**
     * Palauttaa allergeenin id:n persuteella
     * @param id allergeenin id
     * @return Allergeenin
     * @throws SailoException jos allergeenia ei löydy annetulla id:llä
     */
    public Allergeeni haeAllergeeniIdlla( int id ) throws SailoException {
        return this.allergeenit.haeAllergeeniIdlla( id );
    }
    
    
    /**
     * Palauttaa ravintolan id:n perusteella
     * @param id ravintolan id
     * @return ravintolan
     * @throws SailoException jos annetulla id:lla ei löydy ravintolaa
     */
    public Ravintola haeRavintolaIdlla( int id ) throws SailoException {
        return this.ravintolat.haeRavintolaIdlla( id );
    }
    
    
    /**
     * Palauttaa muokattavan tuotteen
     * @return muokattava tuote
     */
    public Tuote haeMuokattava() {
        return this.muokattava;
    }
    
    
    /**
     * Asetetaan tuote "säilöön"
     * @param t asetettava (muokattava) tuote (tai null kun muokkaus valmis)
     */
    public void asetaMuokattava( Tuote t ) {
        this.muokattava = t;
    }
    
    
    /**
     * Palauttaa tietyn tuotteen paikan perusteella
     * @param i monesko tuote
     * @return tuote paikasta i
     */
    public Tuote annaTuote(int i) {
        return this.tuotteet.anna(i);
    }
    
    
    /**
     * Palauttaa allergeenin paikan perusteella
     * @param i monesko allergeeni
     * @return allergeeni paikasta i
     */
    public Allergeeni annaAllergeeni(int i) {
        return this.allergeenit.anna(i);
    }
    
    
    /**
     * Palauttaa i:n ravintolan
     * @param i monesko ravintola
     * @return ravintola paikasta i
     */
    public Ravintola annaRavintola(int i ) {
        return this.ravintolat.anna(i);
    }
    
    
    /**
     * Alustetaan ravintolat
     */
    public void alustaRavintolat() {
        this.ravintolat.taytaRavintolatTiedoilla(this.ravintolat);
    }
    
    
    /**
     * Alustetaan allergeenit
     */
    public void alustaAllergeenit() {
        this.allergeenit.taytaAllergeenitTiedoilla(this.allergeenit);
    }
    
    
    /**
     * 
     * @throws SailoException jos joku menee pieleen
     * @example
     * <pre name="test">
     *  TESTIT
     * </pre>
     */
    public void lueTiedostosta() throws SailoException {
        tuotteet         = new Tuotteet();
        tuoteAllergeenit = new TuoteAllergeenit();
        allergeenit      = new Allergeenit();
        // ravintolat = new Ravintolat(); // Ravintolat ei vielä "käytössä"
        
        tuotteet.lueTiedostosta("tuotteet");
        tuoteAllergeenit.lueTiedostosta("tuoteAllergeenit");
        allergeenit.lueTiedostosta("allergeenit");
        //ravintolat.lueTiedostosta("ravintolat");
    }
    
    
    /**
     * Tallentaa allergiainfon tiedot tiedostoon
     * @throws SailoException jos tallentamisessa ongelmia 
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        
        try {
            tuotteet.tallenna("tuotteet");
        } catch (SailoException e ) {
            virhe = e.getMessage();
        }
        
        try {
            tuoteAllergeenit.tallenna("tuoteAllergeenit");
        } catch (SailoException e ) {
            virhe = e.getMessage();
        }
        
    //  Tämä vähän turha, koska tällähetkellä ei voida lisätä uusia allergeeneja
        try {
            allergeenit.tallenna("allergeenit");
        } catch (SailoException e ) {
            virhe = e.getMessage();
        }
        
    //  Ravintolat ei vielä ns. käytössä voidaan lisätä myöhemmin
//        try {
//            ravintolat.tallenna("ravintolat");
//        } catch (SailoException e ) {
//            virhe = e.getMessage();
//        }
        
        if( !"".equals( virhe ) ) throw new SailoException( virhe );
    }
    
    
    /**
     * Tallennetaan tuotteet tiedostoon
     * @throws SailoException jos tallentamisessa ongelma
     */
    public void tallennaTuotteet() throws SailoException {
        try {
            tuotteet.tallenna("tuotteet");
        } catch (SailoException e ) {
            throw new SailoException("Tuotteiden tallentamisessa ongelma! " + e.getMessage());
        }
    }
    
    
    /**
     * Tallennetaan TuoteAllergeenit tiedostoon
     * @throws SailoException jos tallentamisessa ongelmia
     */
    public void tallennaTuoteAllergeenit() throws SailoException {
        try {
            tuoteAllergeenit.tallenna("tuoteAllergeenit");
        } catch (SailoException e ) {
            throw new SailoException("TuoteAllergeenien tallentamisessa ongelma! " + e.getMessage());
        }
    }
    
    
    /**
     * Testataan Allergiainfo luokkaa
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Allergiainfo ai = new Allergiainfo();
        
        try {
            ai.lueTiedostosta();
        } catch (SailoException e) {
            System.err.println( e.getMessage());
        }
        
        System.out.println("Allergiainfon ravintolat: ");
        
        ai.alustaRavintolat();
        
        for( int i = 0; i < ai.haeRavintoloita(); i++ ) {
            Ravintola r = ai.annaRavintola(i);
            r.tulosta(System.out);
        }
     
        Tuote t = new Tuote();
        
        t.rekisteroi();
        t.taytaTuoteTiedoilla();
        
        try {
            ai.lisaa(t);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        System.out.println("\nAllergiainfon tuotteet:");
        for( int i = 0; i < ai.haeTuotteita(); i++ ) {
            Tuote tuote = ai.annaTuote( i );
            tuote.tulosta(System.out);
        }
        
//        ai.alustaAllergeenit();
        
        System.out.println("\nAllergiainfon allergeenit:");
        for( int i = 0; i < ai.haeAllergeeneja(); i++ ) {
            Allergeeni a = ai.annaAllergeeni(i);
            a.tulosta(System.out);
        }
        
        ai.lisaa(1, 1);
        ai.lisaa(1, 2);
        ai.lisaa(1, 3);
        ai.lisaa(1, 4);
        
        List<TuoteAllergeeni> tuotteenAllergeenit = ai.haeTuotteenAllergeenit(1);
        
        System.out.println("\nTuotteen allergeenit:");
        for(TuoteAllergeeni ta: tuotteenAllergeenit ) {
            ta.tulosta(System.out);
        }
        
        
//        ai.poistaTiettyTuote(1);
//        
//        System.out.println("Poistettu ensimmäinen");
//        
//        for( int i = 0; i < ai.haeTuotteita(); i++) {
//            Tuote tuote = ai.annaTuote(i);
//            tuote.tulosta(System.out);
//        }
    }
}
