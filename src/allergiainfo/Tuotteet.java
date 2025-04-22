package allergiainfo;

import java.io.*;
import java.util.*;

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
 * @version 31.3.2025
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
     * @example
     * <pre name="test">  
     *  Tuotteet tuotteet = new Tuotteet();
     *  
     *  Tuote t = new Tuote();
     *  Tuote t2 = new Tuote();
     *  
     *  tuotteet.haeLkm() === 0;
     *  
     *  tuotteet.lisaa(t);  tuotteet.haeLkm() === 1;
     *  tuotteet.lisaa(t2); tuotteet.haeLkm() === 2;
     *  tuotteet.lisaa(t);  tuotteet.haeLkm() === 3;
     *  
     *  tuotteet.anna(0) === t;
     *  tuotteet.anna(1) === t2;
     *  tuotteet.anna(2) === t;
     *  tuotteet.anna(1) == t   === false;
     *  tuotteet.anna(1) == t2  === true;
     *  tuotteet.anna(3) === t; #THROWS IndexOutOfBoundsException
     *  
     *  tuotteet.lisaa(t);  tuotteet.haeLkm() === 4;
     *  tuotteet.lisaa(t2); tuotteet.haeLkm() === 5;
     * </pre> 
     */
    public void lisaa(Tuote tuote) {
        if( this.lkm >= this.alkiot.length ) {
            Tuote[] uusilista = new Tuote[ lkm * 2 ];
            
            for( int i = 0; i < this.alkiot.length; i++ ) {
                uusilista[ i ] = this.alkiot[ i ];
            }
            
            this.alkiot = uusilista;
        }
        
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
     *   t1.rekisteroi(); t1.haeId() === 1;
     *   t2.rekisteroi(); t2.haeId() === 2;
     *   t3.rekisteroi(); t3.haeId() === 3;
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
     *   t1.rekisteroi(); t1.haeId() === 7;
     *   t2.rekisteroi(); t2.haeId() === 8;
     *   t3.rekisteroi(); t3.haeId() === 9;
     *   
     *   tuotteet.lisaa(t1); tuotteet.haeLkm() === 1;
     *   tuotteet.lisaa(t2); tuotteet.haeLkm() === 2;
     *   tuotteet.lisaa(t3); tuotteet.haeLkm() === 3;
     *   
     *   tuotteet.poistaTietty(7);  tuotteet.haeLkm() === 2;
     *   tuotteet.poistaTietty(99); tuotteet.haeLkm() === 2;
     *   tuotteet.poistaTietty(8);  tuotteet.haeLkm() === 1;
     *   tuotteet.poistaTietty(9);  tuotteet.haeLkm() === 0;
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
        
        Tuote[] uusiLista = new Tuote[alkiot.length];
        
        for( int i = 0, j = 0; i < this.alkiot.length; i++ ) {
            if( i != index ) {
                uusiLista[j] = this.alkiot[i];
                j++;
            }
        }
        
        this.alkiot = uusiLista;
        this.lkm -= 1;
        
        try {
            tallenna("tuotteet");
        } catch (SailoException e) {
            System.err.println( e.getMessage() );
        }
    }
    
    
    /**
     * Palauttaa tietyn tuotteen id:n perusteella
     * @param id tuotteen id
     * @return id:n perusteella rajatun tuotteen
     * @throws SailoException jos tuotetta ei löydy annetulla id:llä
     * @example
     * <pre name="test">
     * #THROWS SailoException
     *  Tuotteet tuotteet = new Tuotteet();
     *  
     *  Tuote t1 = new Tuote();
     *  Tuote t2 = new Tuote();
     *  Tuote t3 = new Tuote();
     *  
     *  t1.rekisteroi(); t1.haeId() === 4;
     *  t2.rekisteroi(); t2.haeId() === 5;
     *  t3.rekisteroi(); t3.haeId() === 6;
     *  
     *  tuotteet.lisaa(t1);
     *  tuotteet.lisaa(t2);
     *  tuotteet.lisaa(t3);
     *  
     *  tuotteet.haeTuoteIdlla(4) === t1;
     *  tuotteet.haeTuoteIdlla(6) === t3;
     *  tuotteet.haeTuoteIdlla(5) === t2;
     *  tuotteet.haeTuoteIdlla(55); #THROWS SailoException
     * </pre>
     */
    public Tuote haeTuoteIdlla( int id ) throws SailoException {
        Tuote tuote = null;
        
        for(Tuote t: this.alkiot ) {
            if(t != null && t.haeId() == id ) {
                tuote = t;
                break;
            }
        }
        
        if( tuote == null ) throw new SailoException("Tuotetta ei löytynyt id:llä " + id );
        
        return tuote;
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
     * Tallentaa tuotteiston tiedostoon
     * @param tiednimi tiedoston nimi (mihin tallennetaan)
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna(String tiednimi) throws SailoException {
        File file = new File( tiednimi  + ".dat" );
        
        try(PrintStream fo = new PrintStream( new FileOutputStream( file, false ))) {
            for( int i = 0; i < this.lkm; i++ ) {
                Tuote tuote = this.alkiot[i];
                fo.println( tuote );
            }
        } catch ( FileNotFoundException ex ) {
            throw new SailoException("Tiedosto " + file.getAbsolutePath() + " ei aukea");
        }
    }
    
    
    /**
     * @param tiednimi tiedoston nimi
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void lueTiedostosta(String tiednimi) throws SailoException {
        File file = new File( tiednimi + ".dat");
        
        try ( Scanner fi = new Scanner( new FileInputStream( file ))) {
            while( fi.hasNext() ) {
                String s = fi.nextLine();
                Tuote tuote = new Tuote();
                
                tuote.parse( s );
                lisaa( tuote );
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException("Tiedoston avaaminen ei onnistunut " + file.getAbsolutePath());
        }
    }
    
    
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        Tuotteet tuotteet = new Tuotteet();
        
        try {
            tuotteet.lueTiedostosta("tuotteet");
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        Tuote t = new Tuote();
        Tuote t2 = new Tuote(3);
        
        t.rekisteroi();
        t.taytaTuoteTiedoilla();
        t2.rekisteroi();
        t2.taytaTuoteTiedoilla();
        
        tuotteet.lisaa(t);
        tuotteet.lisaa(t2);
        
        System.out.println("=============== Tuotteet testi ===============");
        
        t.tulosta(System.out);
        t2.tulosta(System.out);
        
        tuotteet.poistaTietty(1);
        System.out.println("Poistetaan tuote 1");
        
        
        for( int i = 0; i < tuotteet.haeLkm(); i++) {
            Tuote tuote = tuotteet.anna(i);
            tuote.tulosta(System.out);
        }
        
        try {
            tuotteet.tallenna("tuotteet");
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
    }
}
