package allergiainfo;

/**
 * @author Viivi
 * @version 24.2.2025
 *
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
    public void setRavintolat(Ravintolat ravintolat) {
        Ravintola r1 = new Ravintola();
        Ravintola r2 = new Ravintola();
        Ravintola r3 = new Ravintola();
        
        r1.rekisteroi();
        r1.setNimi("Taco Bell");
        
        r2.rekisteroi();
        r2.setNimi("McDonald's");
        
        r3.rekisteroi();
        r3.setNimi("Burger King");
        
        try {
            ravintolat.lisaa(r1);
            ravintolat.lisaa(r2);
            ravintolat.lisaa(r3);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
    }
    
    
    /**
     * @param ravintola lisättävä ravintola
     * @throws SailoException jos liikaa alkioita
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  Ravintolat ravintolat = new Ravintolat();
     *  Ravintola r = new Ravintola();
     *  Ravintola r2 = new Ravintola();
     *  
     *  ravintolat.getLkm()         === 0;
     *  
     *  ravintolat.lisaa(r);        ravintolat.getLkm() === 1;
     *  ravintolat.lisaa(r2);       ravintolat.getLkm() === 2;
     *  ravintolat.lisaa(r);        ravintolat.getLkm() === 3;
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
     * Palauttaa ravintoloiden lukumäärän
     * @return ravintoloiden lukumäärä
     */
    public int getLkm() {
        return this.lkm;
    }
    

    /**
     * @param args ei käytössä
     */
    public static void main(String[] args ) {  
        Ravintolat ravintolat = new Ravintolat();
        
        ravintolat.setRavintolat(ravintolat);
        
        System.out.println("=============== Ravintolat testi ===============");
        
        for ( int i = 0; i < ravintolat.getLkm(); i++ ) {
            Ravintola ravintola = ravintolat.anna( i );
            System.out.println("Ravintola indeksissä: " + i );
            ravintola.tulosta(System.out);
        }
    }
}
