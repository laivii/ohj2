package allergiainfo;

import java.util.*;

/**
 * @author Viivi
 * @version 8.3.2025
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
     * @param args ei käytössä
     */
    public static void main(String[] args) {
        TuoteAllergeenit tat = new TuoteAllergeenit();
        
        TuoteAllergeeni ta1 = new TuoteAllergeeni();
        TuoteAllergeeni ta2 = new TuoteAllergeeni();
        TuoteAllergeeni ta3 = new TuoteAllergeeni();
        TuoteAllergeeni ta4 = new TuoteAllergeeni();
        
        ta1.taytaTuoteAllergeeniTiedoilla(1,1);
        ta2.taytaTuoteAllergeeniTiedoilla(1,2);
        ta3.taytaTuoteAllergeeniTiedoilla(1,3);
        ta4.taytaTuoteAllergeeniTiedoilla(1,4);

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

    }
}
