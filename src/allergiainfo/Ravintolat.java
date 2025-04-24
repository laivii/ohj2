package allergiainfo;

/**
 * @author Viivi
 * @version 24.4.2025
 * 
 * Pitää yllä varsinaista ravintolarekisteriä
 * Osaa teoriassa lisätä ravintolan, mutta toiminto ei vielä käytössä
 *
 * TODO 
 * - ravintoloiden taulukon kasvatus
 * - tiedon lukeminen ja tallentaminen tiedostoon
 */
public class Ravintolat {
    
    private static final int MAX_RAVINTOLAT = 3;
    
    private int lkm             = 0;
    private Ravintola[] alkiot;
    
    
    /**
     * Luodaan alustava taulukko
     */
    public Ravintolat() {
        this.alkiot = new Ravintola[MAX_RAVINTOLAT];
    }
    
    /**
     * Alustetaan ravintolat
     * @param ravintolat mihin taulukkoon lisätään
     */
    public void taytaRavintolatTiedoilla(Ravintolat ravintolat) {
        Ravintola r1 = new Ravintola("Taco Bell");
        Ravintola r2 = new Ravintola("McDonald's");
        Ravintola r3 = new Ravintola("Burger King");
        
        r1.rekisteroi();
        r2.rekisteroi();
        r3.rekisteroi();
        
        try {
            ravintolat.lisaa(r1);
            ravintolat.lisaa(r2);
            ravintolat.lisaa(r3);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
    }
    
    
    /**
     * Lisätään uusi ravintola taulukkoon
     * @param ravintola lisättävä ravintola
     * @throws SailoException jos liikaa alkioita
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  Ravintolat ravintolat = new Ravintolat();
     *  Ravintola r = new Ravintola();
     *  Ravintola r2 = new Ravintola();
     *  
     *  ravintolat.haeLkm()         === 0;
     *  
     *  ravintolat.lisaa(r);        ravintolat.haeLkm() === 1;
     *  ravintolat.lisaa(r2);       ravintolat.haeLkm() === 2;
     *  ravintolat.lisaa(r);        ravintolat.haeLkm() === 3;
     *  
     *  ravintolat.anna(0) === r;
     *  ravintolat.anna(1) === r2;
     *  ravintolat.anna(2) === r;
     *  ravintolat.anna(1) == r     === false;
     *  ravintolat.anna(1) == r2    === true;
     *  ravintolat.anna(3) === r;   #THROWS IndexOutOfBoundsException
     *  
     *  ravintolat.lisaa(r);        #THROWS SailoException
     * </pre>
     */
    public void lisaa(Ravintola ravintola) throws SailoException {
        if( this.lkm >= this.alkiot.length ) throw new SailoException("Liikaa alkioita");
        this.alkiot[this.lkm] = ravintola;
        this.lkm++;
    }
    
    
    /**
     * Palauttaa viitteen i:teen ravintolaan
     * @param i monennenko ravintolan viite halutaan
     * @return viite ravintolaan, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Ravintola anna(int i ) throws IndexOutOfBoundsException {
        if ( i < 0 || this.lkm <= i ) {
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i );
        }
        
        return this.alkiot[i];
    }
    
    
    /**
     * Palauttaa ravintolan id:n perusteella
     * @param id ravintolan id
     * @return ravintolan
     * @throws SailoException jos id:llä ei löydy ravintolaa
     */
    public Ravintola haeRavintolaIdlla( int id ) throws SailoException {
        Ravintola ravintola = null;
        
        for( Ravintola r: alkiot ) {
            if( r != null && r.haeId() == id ) {
                ravintola = r;
                break;
            }
        }
        
        if(ravintola == null ) throw new SailoException( "Ravintolaa ei löydetty annetulla id:llä " + id );
        
        return ravintola;
    }
    
    
    /**
     * Palauttaa ravintoloiden lukumäärän
     * @return ravintoloiden lukumäärä
     */
    public int haeLkm() {
        return this.lkm;
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args ) {  
        Ravintolat ravintolat = new Ravintolat();
        
        ravintolat.taytaRavintolatTiedoilla(ravintolat);
        
        System.out.println("=============== Ravintolat testi ===============");
        
        for ( int i = 0; i < ravintolat.haeLkm(); i++ ) {
            Ravintola ravintola = ravintolat.anna( i );
            System.out.println("Ravintola indeksissä: " + i );
            ravintola.tulosta(System.out);
        }
    }
}
