package allergiainfo;

import java.io.*;
import java.util.*;

/**
 * @author Viivi
 * @version 24.4.2025
 * 
 * - Pitää ylä varsinaista TuoteAllergeenistoa
 * - Lukee ja kirjoittaa tuotteen allergeenit tiedostoon
 * - Osaa poistaa TuoteAllergeenin tietorakenteesta
 * - Osaa hakea kaikki tietyn tuotteen allergeenit
 *
 */
public class TuoteAllergeenit implements Iterable<TuoteAllergeeni> {
    
    /**  Taulukko TuoteAllergeeneista  */
    private final Collection<TuoteAllergeeni> alkiot = new ArrayList<TuoteAllergeeni>();
    
    
    /**
     * TuoteAllergeenien alustaminen
     */
    public TuoteAllergeenit() {
        // toistaiseksi ei tarvitse tehdä mitään
    }
    
    
    /**
     * Lisätään uusi TuoteAllergeeni tietorakenteeseen. Ottaa TuoteAllergeenin omistukseensa.
     * @param ta TuoteAllergeeni joka halutaan lisätä taulukkoon
     */
    public void lisaa( TuoteAllergeeni ta ) {
        alkiot.add( ta );
    } 
    
    
    /**
     * Poistetaan TuoteAllergeeni tietorakenteesta.
     * @param ta poistettava TuoteAllergeeni
     * @example
     * <pre name="test">
     *  TuoteAllergeenit tat = new TuoteAllergeenit();
     *  TuoteAllergeeni laktoosi  = new TuoteAllergeeni(1,1); tat.lisaa(laktoosi);
     *  TuoteAllergeeni kananmuna = new TuoteAllergeeni(1,2); tat.lisaa(kananmuna);
     *  
     *  tat.haeTuoteAllergeeneja() === 2;
     *  Iterator<TuoteAllergeeni> i3 = tat.iterator();
     *  i3.next() === laktoosi;
     *  i3.next() === kananmuna;
     *  
     *  tat.poista(laktoosi);
     *  
     *  tat.haeTuoteAllergeeneja() === 1;
     *  Iterator<TuoteAllergeeni> i4 = tat.iterator();
     *  i4.next() === kananmuna;
     *  i4.next() === laktoosi;  #THROWS NoSuchElementException
     * </pre>
     */
    public void poista( TuoteAllergeeni ta ) {
        alkiot.remove( ta );
        
        try {
            tallenna("tuoteAllergeenit");
        } catch (SailoException e) {
            System.err.println( e.getMessage() );
        }
    }
    
    
    /**
     * Palautetaan listan alkioiden määrä
     * @return alkioiden määrä listalla
     */
    public int haeTuoteAllergeeneja() {
        return alkiot.size();
    }
    

    /**
     * Iteraattori kaikkien TuoteAllergeenien läpikäymiseen
     * @return TuoteAllergeeni-iteraattori
     * 
     * @example
     * <pre name="test">
     * #PACKAGEIMPORT
     * #import java.util.*;
     * 
     *  TuoteAllergeenit tat = new TuoteAllergeenit();
     *  TuoteAllergeeni laktoosi  = new TuoteAllergeeni(1,1); tat.lisaa(laktoosi);
     *  TuoteAllergeeni kananmuna = new TuoteAllergeeni(1,2); tat.lisaa(kananmuna);
     *  TuoteAllergeeni pahkinat  = new TuoteAllergeeni(1,3); tat.lisaa(pahkinat);
     *  TuoteAllergeeni selleri   = new TuoteAllergeeni(1,4); tat.lisaa(selleri);
     *  TuoteAllergeeni sinappi   = new TuoteAllergeeni(1,5); tat.lisaa(sinappi);
     *  
     *  Iterator<TuoteAllergeeni> i2 = tat.iterator();
     *  i2.next() === laktoosi;
     *  i2.next() === kananmuna;
     *  i2.next() === pahkinat;
     *  i2.next() === selleri;
     *  i2.next() === sinappi;
     *  i2.next() === laktoosi;  #THROWS NoSuchElementException  
     *  
     *  int n = 0;
     *  int jnrot[] = {1,2,3,4,5};
     *  
     *  for ( TuoteAllergeeni ta: tat ) { 
     *    ta.haeAllergeeniID() === jnrot[n]; n++;  
     *  }
     *  
     *  n === 5;
     *  
     * </pre>
     */
    @Override
    public Iterator<TuoteAllergeeni> iterator() {
        return alkiot.iterator();
    }
    
    
    /**
     * Haetaan kaikki jäsen harrastukset
     * @param tunnusnro jäsenen tunnusnumero jolle harrastuksia haetaan
     * @return tietorakenne jossa viiteet löydetteyihin harrastuksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * 
     *  TuoteAllergeenit tat = new TuoteAllergeenit();
     *  TuoteAllergeeni  ta1 = new TuoteAllergeeni(1,1); tat.lisaa(ta1);
     *  TuoteAllergeeni  ta2 = new TuoteAllergeeni(1,2); tat.lisaa(ta2);
     *  TuoteAllergeeni  ta3 = new TuoteAllergeeni(2,1); tat.lisaa(ta3);
     *  TuoteAllergeeni  ta4 = new TuoteAllergeeni(1,3); tat.lisaa(ta4);
     *  TuoteAllergeeni  ta5 = new TuoteAllergeeni(2,2); tat.lisaa(ta5);
     *  TuoteAllergeeni  ta6 = new TuoteAllergeeni(3,1); tat.lisaa(ta6);
     *  
     *  List<TuoteAllergeeni> loytyneet;
     *  loytyneet = tat.annaTuotteenAllergeenit(1);
     *  loytyneet.size() === 3; 
     *  loytyneet = tat.annaTuotteenAllergeenit(4);
     *  loytyneet.size() === 0;
     *  loytyneet = tat.annaTuotteenAllergeenit(2);
     *  loytyneet.size() === 2; 
     *  loytyneet.get(0) == ta3 === true;
     *  loytyneet.get(1) == ta5 === true;
     *  loytyneet = tat.annaTuotteenAllergeenit(3);
     *  loytyneet.size() === 1; 
     *  loytyneet.get(0) == ta6 === true;
     * </pre> 
     */
    public List<TuoteAllergeeni> annaTuotteenAllergeenit( int tunnusnro ) {
        
        List<TuoteAllergeeni> loydetyt = new ArrayList<TuoteAllergeeni>();
        
        for (TuoteAllergeeni ta : alkiot)
            if (ta.haeTuoteID() == tunnusnro) loydetyt.add(ta);
        
        return loydetyt;
    }
    
    
    /**
     * Tallennetaan tuotteiden allergiatiedot tiedostoon
     * @param tiednimi tiedoston nimi ( mihin tallennetaan )
     * @throws SailoException jos tallennus epäonnistuu
     */
    public void tallenna( String tiednimi ) throws SailoException {
        File file = new File( tiednimi + ".dat" );
        
        try ( PrintStream fo = new PrintStream( new FileOutputStream( file, false ))) {
            
            for( TuoteAllergeeni ta: alkiot ) {
                fo.println( ta );
            }
            
        } catch ( FileNotFoundException e ) {
            throw new SailoException( "Tiedosto " + file.getAbsolutePath() + " ei aukea " + e.getMessage() );
        }
    }
    
    
    /**
     * @param tiednimi tiedoston nimi
     * @throws SailoException jos tiedostoa ei löydy
     */
    public void lueTiedostosta( String tiednimi ) throws SailoException {
        File file = new File( tiednimi + ".dat" );
        
        try( Scanner fi = new Scanner( new FileInputStream( file ))) {
            while ( fi.hasNext() ) {
                String s = fi.nextLine();
                TuoteAllergeeni ta = new TuoteAllergeeni();
                
                ta.parse( s );
                lisaa( ta );
            }
        } catch ( FileNotFoundException e ) {
            throw new SailoException( "Tiedoston avaaminen ei onnistunut " + file.getAbsolutePath());
        }
        
    }

 
    /**
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        TuoteAllergeenit tat = new TuoteAllergeenit();
        
        try {
            tat.lueTiedostosta("tuoteAllergeenit");
        } catch ( SailoException e ) {
            System.err.println( e.getMessage() );
        }
        
        TuoteAllergeeni ta1 = new TuoteAllergeeni();
        TuoteAllergeeni ta2 = new TuoteAllergeeni();
        TuoteAllergeeni ta3 = new TuoteAllergeeni();
        TuoteAllergeeni ta4 = new TuoteAllergeeni();
        
        ta1.taytaTuoteAllergeeniTiedoilla(1,1);
        ta2.taytaTuoteAllergeeniTiedoilla(2,2);
        ta3.taytaTuoteAllergeeniTiedoilla(3,3);
        ta4.taytaTuoteAllergeeniTiedoilla(4,4);

        tat.lisaa(ta1);
        tat.lisaa(ta2);
        tat.lisaa(ta3);
        tat.lisaa(ta2);
        tat.lisaa(ta4);

        System.out.println("============= TuoteAllergeenit testi =================");

        List<TuoteAllergeeni> tuoteAllergeenit = tat.annaTuotteenAllergeenit(1);

        for (TuoteAllergeeni ta : tuoteAllergeenit) {
            ta.tulosta(System.out);
        }
        
        try {
            tat.tallenna( "tuoteAllergeenit" );
        } catch ( SailoException e ) {
            System.err.println( e.getMessage());
        }

    }
}
