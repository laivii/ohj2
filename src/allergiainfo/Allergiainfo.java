package allergiainfo;

import java.util.List;

/**
 * @author Viivi
 * @version 8.3.2025
 */
public class Allergiainfo {
    
    private Tuotteet         tuotteet         = new Tuotteet();
    private Allergeenit      allergeenit      = new Allergeenit();
    private TuoteAllergeenit tuoteAllergeenit = new TuoteAllergeenit();
    
    
    /**
     * Lisää uusi tuote
     * @param tuote lisättävä tuote
     * @throws SailoException jos lisääminen ei onnistu
     * 
     */
    public void lisaaTuote(Tuote tuote) throws SailoException {
        this.tuotteet.lisaa(tuote);
    }
    
    /**
     * Lisää uusi allergeeni
     * @param allergeeni joka halutaan lisätä
     * @throws SailoException jos lisääminen ei onnistu
     */
    public void lisaaAllergeeni(Allergeeni allergeeni ) throws SailoException {
        this.allergeenit.lisaa(allergeeni);
    }
    
    
    /**
     * Poistaa tuotteen taulukosta
     * @param id poistettavan tuotteen id 
     */
    public void poistaTiettyTuote(int id) {
        this.tuotteet.poistaTietty(id);
    }
    
    
    /**
     * Poistaa taulukon viimeisen tuotteen
     */
    public void poistaViimeinenTuote() {
        this.tuotteet.poistaViimeinen();
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
     * Palauttaa tietyn tuotteen id:n perusteella
     * @param id monesko tuote
     * @return tuote paikasta i
     */
    public Tuote annaTuote(int id) {
        return this.tuotteet.anna(id);
    }
    
    
    /**
     * Palauttaa allergeenin id:n perusteella
     * @param id allergeenin id
     * @return allergeeni paikasta i
     */
    public Allergeeni annaAllergeeni(int id) {
        return this.allergeenit.anna(id);
    }
    
    
    /**
     * Palauttaa listan tuotteen allergeeneista
     * @param id tuotteen id
     * @return palauttaa tuotteen allergeenit listana
     */
    public List<TuoteAllergeeni> annaTuotteenAllergeenit(int id ) {
        return this.tuoteAllergeenit.annaTuotteenAllergeenit(id);
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Allergiainfo ai = new Allergiainfo();
        
        Tuote t = new Tuote();
        Tuote t2 = new Tuote();
        Tuote t3 = new Tuote();
        Tuote t4 = new Tuote();
        
        t.rekisteroi();
        t.taytaTuoteTiedoilla();
        t2.rekisteroi();
        t2.taytaTuoteTiedoilla();
        t3.rekisteroi();
        t3.taytaTuoteTiedoilla();
        t4.rekisteroi();
        t4.taytaTuoteTiedoilla();
        
        try {
            ai.lisaaTuote(t);
            ai.lisaaTuote(t2);
            ai.lisaaTuote(t3);
            ai.lisaaTuote(t4);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        for( int i = 0; i < ai.haeTuotteita(); i++) {
            Tuote tuote = ai.annaTuote(i);
            tuote.tulosta(System.out);
        }
        
        ai.poistaTiettyTuote(1);
        
        System.out.println("Poistettu ensimmäinen");
        
        for( int i = 0; i < ai.haeTuotteita(); i++) {
            Tuote tuote = ai.annaTuote(i);
            tuote.tulosta(System.out);
        }
    }
}
