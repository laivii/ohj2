package allergiainfo;

/**
 * @author Viivi
 * @version 21.2.2025
 */
public class Tuotteet {
    private static final int MAX_TUOTTEITA  = 5;
    
    private int lkm                         = 0;
    private Tuote[] alkiot;
    
    
    /**
     * Luodaan alustava taulukko
     */
    public Tuotteet() {
        alkiot = new Tuote[MAX_TUOTTEITA];
    }
    
    
    /**
     * @param tuote joka halutaan lisätä taulukkoon
     * @throws SailoException poikkeus
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     *  Tuotteet tuotteet = new Tuotteet();
     *  Tuote t = new Tuote();
     *  Tuote t2 = new Tuote();
     *  tuotteet.getLkm() === 0;
     *  tuotteet.lisaa(t);  tuotteet.getLkm() === 1;
     *  tuotteet.lisaa(t2); tuotteet.getLkm() === 2;
     *  tuotteet.lisaa(t);  tuotteet.getLkm() === 3;
     *  tuotteet.anna(0) === t;
     *  tuotteet.anna(1) === t2;
     *  tuotteet.anna(2) === t;
     *  tuotteet.anna(1) == t   === false;
     *  tuotteet.anna(1) == t2  === true;
     *  tuotteet.anna(3) === t; #THROWS IndexOutOfBoundsException
     *  tuotteet.lisaa(t);  tuotteet.getLkm() === 4;
     *  tuotteet.lisaa(t2); tuotteet.getLkm() === 5;
     *  tuotteet.lisaa(t); #THROWS SailoException
     * </pre> 
     */
    public void lisaa(Tuote tuote) throws SailoException {
        if( lkm >= alkiot.length ) throw new SailoException("Liikaa tuotteita");
        alkiot[lkm] = tuote;
        lkm++;
    }
    
    
    /**
     * Palauttaa viitteen i:teen tuotteeseen
     * @param i monennenko tuotteen viite halutaan
     * @return viite tuotteeseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Tuote anna(int i) throws IndexOutOfBoundsException {
        if( i < 0 || this.lkm <= i ) {
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        }
        return alkiot[i];
    }
    
    
    /**
     * Palauttaa tuotteiden lukumäärän
     * @return tuotteiden lukumäärä
     */
    public int getLkm() {
        return lkm;
    }
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuotteet tuotteet = new Tuotteet();
        
        Tuote t = new Tuote();
        Tuote t2 = new Tuote();
        
        t.rekisteroi();
        t.taytaTuoteTiedoilla();
        t2.rekisteroi();
        t2.taytaTuoteTiedoilla();
        
        try {
            tuotteet.lisaa(t);
            tuotteet.lisaa(t2);
        } catch (SailoException e) {
             System.err.println(e.getMessage());
        }
        
        System.out.println("=============== Tuotteet testi ===============");
        
        for( int i = 0; i < tuotteet.getLkm(); i++) {
            Tuote tuote = tuotteet.anna(i);
            System.out.println("Tuote indeksi: " + i);
            tuote.tulosta(System.out);
        }
    }
}
