package allergiainfo;

import java.io.*;
import java.util.*;

/**
 * @author Viivi
 * @version 10.3.2025
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
     * Alustetaan allergeenit
     * @param allergeenit allergeeni lista johon lisätään
     */
    public void taytaAllergeenitTiedoilla(Allergeenit allergeenit) {
        Allergeeni a1  = new Allergeeni("Gluteeni");
        Allergeeni a2  = new Allergeeni("Maitotuotteet");
        Allergeeni a3  = new Allergeeni("Laktoosi");
        Allergeeni a4  = new Allergeeni("Kananmuna");
        Allergeeni a5  = new Allergeeni("Soija");
        Allergeeni a6  = new Allergeeni("Seesami");
        Allergeeni a7  = new Allergeeni("Sinappi");
        Allergeeni a8  = new Allergeeni("Selleri");
        Allergeeni a9  = new Allergeeni("Kala");
        Allergeeni a10 = new Allergeeni("Äyriäiset");
        Allergeeni a11 = new Allergeeni("Pähkinät");
        Allergeeni a12 = new Allergeeni("Maapähkinät");
        Allergeeni a13 = new Allergeeni("Nilviäiset");
        Allergeeni a14 = new Allergeeni("Lupiini");
        Allergeeni a15 = new Allergeeni("Sulfiitit");
        
        a1.rekisteroi();
        a2.rekisteroi();
        a3.rekisteroi();
        a4.rekisteroi();
        a5.rekisteroi();
        a6.rekisteroi();
        a7.rekisteroi();
        a8.rekisteroi();
        a9.rekisteroi();
        a10.rekisteroi();
        a11.rekisteroi();
        a12.rekisteroi();
        a13.rekisteroi();
        a14.rekisteroi();
        a15.rekisteroi();
        
        try {
            allergeenit.lisaa(a1);
            allergeenit.lisaa(a2);
            allergeenit.lisaa(a3);
            allergeenit.lisaa(a4);
            allergeenit.lisaa(a5);
            allergeenit.lisaa(a6);
            allergeenit.lisaa(a7);
            allergeenit.lisaa(a8);
            allergeenit.lisaa(a9);
            allergeenit.lisaa(a10);
            allergeenit.lisaa(a11);
            allergeenit.lisaa(a12);
            allergeenit.lisaa(a13);
            allergeenit.lisaa(a14);
            allergeenit.lisaa(a15);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
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
     * @param tiednimi tiedoston nimi
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void lueTiedostosta( String tiednimi ) throws SailoException {
        File file = new File( tiednimi + ".dat" );
        
        try( Scanner fi = new Scanner( new FileInputStream( file ))){
            while( fi.hasNext()) {
                String s = fi.nextLine();
                Allergeeni allergeeni = new Allergeeni();
                
                allergeeni.parse( s );
                lisaa( allergeeni );
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException( "Tiedoston avaaminen ei onnistunut " + file.getAbsolutePath() );
        }
    }
    
    
    /**
     * @param tiednimi tiedoston nimi
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna( String tiednimi ) throws SailoException {
        File file = new File( tiednimi + ".dat" );
        
        try( PrintStream fo = new PrintStream( new FileOutputStream( file, false))) {
            for( int i = 0; i < this.lkm; i++ ) {
                Allergeeni allergeeni = this.alkiot[ i ];
                fo.println( allergeeni );
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + file.getAbsolutePath() + " ei aukea");
        }
    }
    
    
    /**
     * Allergeenit luokan testaus
     * @param args ei käytössä
     */
    public static void main(String[] args ) {
        Allergeenit allergeenit = new Allergeenit();
        
        try {
            allergeenit.lueTiedostosta("allergeenit");
        } catch ( SailoException e ) {
            System.err.println( e.getMessage() );
        }
        
        System.out.println("=============== Allergeenit testi ===============");
        
        for( int i = 0; i < allergeenit.haeLkm(); i++ ) {
            Allergeeni allergeeni = allergeenit.anna( i );
            allergeeni.tulosta(System.out);
        }
        
        try {
            allergeenit.tallenna("allergeenit");
        } catch ( SailoException e ) {
            System.err.println( e.getMessage() );
        }
    }
}
