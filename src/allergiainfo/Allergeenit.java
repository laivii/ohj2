package allergiainfo;

/**
 * @author Viivi
 * @version 6.3.2025
 *
 */
public class Allergeenit {
    
    private static final int MAX_ALLERGEENEJA = 15;
    
    private int             lkm     = 0;
    private Allergeeni[]    alkiot;
    
    
    /**
     * Luodaan alustava taulukko
     */
    public Allergeenit() {
        this.alkiot = new Allergeeni[MAX_ALLERGEENEJA];
    }
    
    
    /**
     * Lisäätään uusi allergeeni taulukkoon
     * @param a lisättävä allergeeni
     * @throws SailoException jos liikaa allergeeneja
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * 
     *  Allergeenit allergeenit = new Allergeenit();
     *  
     *  Allergeeni a1 = new Allergeeni();
     *  Allergeeni a2 = new Allergeeni();
     *  Allergeeni a3 = new Allergeeni();
     *  
     *  allergeenit.lisaa(a1); allergeenit.haeLkm() === 1;
     *  allergeenit.lisaa(a2); allergeenit.haeLkm() === 2;
     *  allergeenit.lisaa(a3); allergeenit.haeLkm() === 3;
     *  
     *  allergeenit.anna(0) === a1;
     *  allergeenit.anna(1) === a2;
     *  allergeenit.anna(2) === a3;
     *  allergeenit.anna(3); #THROWS IndexOutOfBoundsException
     *  
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a1); #THROWS SailoException
     * </pre>
     */
    public void lisaa(Allergeeni a) throws SailoException {
        if( this.lkm >= alkiot.length ) throw new SailoException("Liikaa allergeeneja");
        alkiot[ lkm ] = a;
        lkm++;        
    }
    
    
    /**
     * Palauttaa tietyn allergeenin id:n perusteella
     * @param id allergeenin id
     * @return id:n perusteella rajatun allergeenin
     * @throws SailoException jos allergeenia ei löydy annetulla id:llä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * 
     *  Allergeenit allergeenit = new Allergeenit();
     *  
     *  Allergeeni a1 = new Allergeeni();
     *  Allergeeni a2 = new Allergeeni();
     *  Allergeeni a3 = new Allergeeni();
     *  
     *  a1.rekisteroi(); a1.haeId() === 1;
     *  a2.rekisteroi(); a2.haeId() === 2;
     *  a3.rekisteroi(); a3.haeId() === 3;
     *  
     *  allergeenit.lisaa(a1);
     *  allergeenit.lisaa(a2);
     *  allergeenit.lisaa(a3);
     *  
     *  allergeenit.haeAllergeeniIdlla(1) === a1;
     *  allergeenit.haeAllergeeniIdlla(2) === a2;
     *  allergeenit.haeAllergeeniIdlla(3) === a3;
     *  allergeenit.haeAllergeeniIdlla(4); #THROWS SailoException
     * </pre>
     */
    public Allergeeni haeAllergeeniIdlla( int id ) throws SailoException {
        Allergeeni allergeeni = null;
        
        for(Allergeeni a: this.alkiot ) {
            if(a != null && a.haeId() == id ) {
                allergeeni = a;
                break;
            }
        }
        
        if( allergeeni == null ) throw new SailoException("Allergeenia ei löytynyt id:llä " + id );
        
        return allergeeni;
    }
        
    /**
     * Palauttaa viitteen i:teen allergeeniin
     * @param i kohta josta haetaan
     * @return viite allergeeniin, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Allergeeni anna(int i) throws IndexOutOfBoundsException {
        if( i < 0 || this.lkm <= i ) throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        return this.alkiot[ i ];
    }
    
    
    /**
     * @return allergeenien lukumäärä taulukossa
     */
    public int haeLkm() {
        return this.lkm;
    }
    
    
    /**
     * Allergeenit luokan testaus
     * @param args ei käytössä
     */
    public static void main(String[] args ) {
        Allergeenit allergeenit = new Allergeenit();
        
        Allergeeni a1 = new Allergeeni();
        Allergeeni a2 = new Allergeeni();
        Allergeeni a3 = new Allergeeni();
        
        a1.rekisteroi();
        a1.taytaAllergeeniTiedoilla();
        a2.rekisteroi();
        a2.taytaAllergeeniTiedoilla();
        a3.rekisteroi();
        a3.taytaAllergeeniTiedoilla();
        
        try {
            allergeenit.lisaa(a1);
            allergeenit.lisaa(a2);
            allergeenit.lisaa(a3);
        } catch (SailoException e) {
            System.err.println( e.getMessage());
        }
        
        System.out.println("=============== Allergeenit testi ===============");
        
        for( int i = 0; i < allergeenit.haeLkm(); i++ ) {
            Allergeeni allergeeni = allergeenit.anna( i );
            allergeeni.tulosta(System.out);
        }
    }
}
