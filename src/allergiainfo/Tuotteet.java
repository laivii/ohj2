package allergiainfo;

/**
 * Vastuut:
 * Pitää yllä varsinaista tuoterekisteriä, eli osaa lisätä ja poistaa tuotteen
 * Lukee ja kirjoittaa tuotteiston tiedostoon(?)
 * Osaa etsiä ja lajitella
 * 
 * Avustajat:
 * Tuote-luokka
 * 
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
        this.alkiot = new Tuote[MAX_TUOTTEITA];
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
        if( this.lkm >= this.alkiot.length ) throw new SailoException("Liikaa tuotteita");
        this.alkiot[lkm] = tuote;
        this.lkm++;
    }
    
    
    
    /**
     * Poistaa valitun tuotteen
     * @param id poistettavan tuotteen id
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *   Tuotteet tuotteet = new Tuotteet();
     *   Tuote t1 = new Tuote();
     *   Tuote t2 = new Tuote();
     *   Tuote t3 = new Tuote();
     *   
     *   t1.rekisteroi(); t1.getId() === 1;
     *   t2.rekisteroi(); t1.getId() === 1;
     *   t3.rekisteroi(); t1.getId() === 1;
     *   
     *   tuotteet.lisaa(t1); tuotteet.getLkm() === 1;
     *   tuotteet.lisaa(t2); tuotteet.getLkm() === 2;
     *   tuotteet.lisaa(t3); tuotteet.getLkm() === 3;
     *   
     *   tuotteet.poista(1); tuotteet.getLkm() === 2;
     *   tuotteet.poista(4); tuotteet.getLkm() === 2;
     *   tuotteet.poista(2); tuotteet.getLkm() === 1;
     *   tuotteet.poista(3); tuotteet.getLkm() === 0;
     * </pre>
     */
    public void poista(int id) {
        int index = -1;
        
        for( int i = 0; i < this.alkiot.length; i++ ) {
            if( this.alkiot[i] != null && this.alkiot[i].getId() == id ) {
                index = i;
            }
        }
        
        if( index != -1 ) {
        
            Tuote[] uusiLista = new Tuote[MAX_TUOTTEITA];
        
            for( int i = 0, j = 0; i < this.alkiot.length; i++ ) {
                if( i != index ) {
                    uusiLista[j] = this.alkiot[i];
                    j++;
                }
            }
            
            this.alkiot = uusiLista;
            this.lkm -= 1;
        }
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
        return this.alkiot[i];
    }
    
    
    /**
     * Palauttaa tuotteiden lukumäärän
     * @return tuotteiden lukumäärä
     */
    public int getLkm() {
        return this.lkm;
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
