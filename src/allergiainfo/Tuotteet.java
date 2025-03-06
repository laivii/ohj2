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
 * @version 6.3.2025
 */
public class Tuotteet {
    
    private static final int   MAX_TUOTTEITA   = 5;
    
    private int       lkm      = 0;
    private Tuote[]   alkiot;
    
    
    /**
     * Luodaan alustava taulukko
     */
    public Tuotteet() {
        this.alkiot = new Tuote[MAX_TUOTTEITA];
    }
    
    
    /**
     * Lisätään uusi tuote taulukkoon
     * @param tuote joka halutaan lisätä taulukkoon
     * @throws SailoException poikkeus
     * @example
     * <pre name="test">
     *  #THROWS SailoException
     *  Tuotteet tuotteet = new Tuotteet();
     *  Tuote t = new Tuote();
     *  Tuote t2 = new Tuote();
     *  tuotteet.haeLkm() === 0;
     *  tuotteet.lisaa(t);  tuotteet.haeLkm() === 1;
     *  tuotteet.lisaa(t2); tuotteet.haeLkm() === 2;
     *  tuotteet.lisaa(t);  tuotteet.haeLkm() === 3;
     *  tuotteet.anna(0) === t;
     *  tuotteet.anna(1) === t2;
     *  tuotteet.anna(2) === t;
     *  tuotteet.anna(1) == t   === false;
     *  tuotteet.anna(1) == t2  === true;
     *  tuotteet.anna(3) === t; #THROWS IndexOutOfBoundsException
     *  tuotteet.lisaa(t);  tuotteet.haeLkm() === 4;
     *  tuotteet.lisaa(t2); tuotteet.haeLkm() === 5;
     *  tuotteet.lisaa(t); #THROWS SailoException
     * </pre> 
     */
    public void lisaa(Tuote tuote) throws SailoException {
        if( this.lkm >= this.alkiot.length ) throw new SailoException("Liikaa tuotteita");
        this.alkiot[lkm] = tuote;
        this.lkm++;
    }
    
    
    /**
     * Poistetaan taulukon viimeinen alkio
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *   Tuotteet tuotteet = new Tuotteet();
     *   Tuote t1 = new Tuote();
     *   Tuote t2 = new Tuote();
     *   Tuote t3 = new Tuote();
     *   
     *   t1.rekisteroi(); t1.getId() === 1;
     *   t2.rekisteroi(); t2.getId() === 2;
     *   t3.rekisteroi(); t3.getId() === 3;
     *   
     *   tuotteet.lisaa(t1); tuotteet.haeLkm() === 1;
     *   tuotteet.lisaa(t2); tuotteet.haeLkm() === 2;
     *   tuotteet.lisaa(t3); tuotteet.haeLkm() === 3;
     *   
     *   tuotteet.poistaViimeinen(); tuotteet.haeLkm() === 2;
     *   tuotteet.poistaViimeinen(); tuotteet.haeLkm() === 1;
     *   tuotteet.poistaViimeinen(); tuotteet.haeLkm() === 0;
     *   tuotteet.poistaViimeinen(); tuotteet.haeLkm() === 0;
     * </pre>
     */
    public void poistaViimeinen() {
        if( this.lkm == 0 ) return;
        
        Tuote[] uusiLista = new Tuote[MAX_TUOTTEITA];
        
        for( int i = 0; i < this.alkiot.length - 1; i++ ) {
                uusiLista[i] = this.alkiot[i];
        }
        
        this.alkiot = uusiLista;
        this.lkm -= 1;
    }
    
    
    /**
     * Poistaa valitun tuotteen id:n perusteella
     * @param id poistettavan tuotteen id
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *   Tuotteet tuotteet = new Tuotteet();
     *   Tuote t1 = new Tuote();
     *   Tuote t2 = new Tuote();
     *   Tuote t3 = new Tuote();
     *   
     *   t1.rekisteroi(); t1.getId() === 4;
     *   t2.rekisteroi(); t2.getId() === 5;
     *   t3.rekisteroi(); t3.getId() === 6;
     *   
     *   tuotteet.lisaa(t1); tuotteet.haeLkm() === 1;
     *   tuotteet.lisaa(t2); tuotteet.haeLkm() === 2;
     *   tuotteet.lisaa(t3); tuotteet.haeLkm() === 3;
     *   
     *   tuotteet.poistaTietty(4); tuotteet.haeLkm() === 2;
     *   tuotteet.poistaTietty(7); tuotteet.haeLkm() === 2;
     *   tuotteet.poistaTietty(5); tuotteet.haeLkm() === 1;
     *   tuotteet.poistaTietty(6); tuotteet.haeLkm() === 0;
     * </pre>
     */
    public void poistaTietty(int id) {
        if( this.lkm == 0 ) return;
        
        int index = -1;
        
        for( int i = 0; i < this.alkiot.length; i++ ) {
            if( this.alkiot[i] != null && this.alkiot[i].haeId() == id ) {
                index = i;
            }
        }
        
        if( index == -1 ) return;
        
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
     * @return tuotteiden lukumäärä
     */
    public int haeLkm() {
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
        
        t.tulosta(System.out);
        t2.tulosta(System.out);
        
        tuotteet.poistaTietty(1);
        System.out.println("Poistetaan tuote 1");
        
        
        for( int i = 0; i < tuotteet.haeLkm(); i++) {
            Tuote tuote = tuotteet.anna(i);
            tuote.tulosta(System.out);
        }
    }
}
