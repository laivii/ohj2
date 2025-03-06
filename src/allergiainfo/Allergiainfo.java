package allergiainfo;


/**
 * @author Viivi
 * @version 6.3.2025
 */
public class Allergiainfo {
    
    private Tuotteet tuotteet = new Tuotteet();
    
    
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
     * Palauttaa tuotteiden määrät
     * @return tuotteiden lukumäärä
     */
    public int haeTuotteita() {
        return this.tuotteet.haeLkm();   
    }
    
    
    /**
     * Palauttaa allergiainfon i:n tuotteen
     * @param i monesko tuote
     * @return tuote paikasta i
     */
    public Tuote annaTuote(int i) {
        return this.tuotteet.anna(i);
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
