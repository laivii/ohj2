package allergiainfo;

/**
 * @author Viivi
 * @version 23.2.2025
 *
 */
public class Allergiainfo {
    private Tuotteet tuotteet = new Tuotteet();
    
    /**
     * Lisää uusi tuote
     * @param tuote lisättävä tuote
     * @throws SailoException jos lisääminen ei onnistu
     * 
     */
    public void lisaa(Tuote tuote) throws SailoException {
        tuotteet.lisaa(tuote);
    }
    
    /**
     * Palauttaa tuotteiden määrät
     * @return tuotteiden lukumäärä
     */
    public int getTuotteita() {
        return tuotteet.getLkm();   
    }
    
    /**
     * Palauttaa allergiainfon i:n tuotteen
     * @param i monesko tuote
     * @return tuote paikasta i
     */
    public Tuote annaTuote(int i) {
        return tuotteet.anna(i);
    }

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Allergiainfo ai = new Allergiainfo();
        
        Tuote t = new Tuote();
        Tuote t2 = new Tuote();
        
        t.rekisteroi();
        t.taytaTuoteTiedoilla();
        t2.rekisteroi();
        t2.taytaTuoteTiedoilla();
        
        try {
            ai.lisaa(t);
            ai.lisaa(t2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        for( int i = 0; i < ai.getTuotteita(); i++) {
            Tuote tuote = ai.annaTuote(i);
            tuote.tulosta(System.out);
        }
    }
}
