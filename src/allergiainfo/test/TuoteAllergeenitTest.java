package allergiainfo.test;
// Generated by ComTest BEGIN
import allergiainfo.*;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2025.04.22 14:06:13 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class TuoteAllergeenitTest {



  // Generated by ComTest BEGIN
  /** testPoista38 */
  @Test
  public void testPoista38() {    // TuoteAllergeenit: 38
    TuoteAllergeenit tat = new TuoteAllergeenit(); 
    TuoteAllergeeni laktoosi  = new TuoteAllergeeni(1,1); tat.lisaa(laktoosi); 
    TuoteAllergeeni kananmuna = new TuoteAllergeeni(1,2); tat.lisaa(kananmuna); 
    assertEquals("From: TuoteAllergeenit line: 43", 2, tat.haeTuoteAllergeeneja()); 
    Iterator<TuoteAllergeeni> i3 = tat.iterator(); 
    assertEquals("From: TuoteAllergeenit line: 45", laktoosi, i3.next()); 
    assertEquals("From: TuoteAllergeenit line: 46", kananmuna, i3.next()); 
    tat.poista(laktoosi); 
    assertEquals("From: TuoteAllergeenit line: 50", 1, tat.haeTuoteAllergeeneja()); 
    Iterator<TuoteAllergeeni> i4 = tat.iterator(); 
    assertEquals("From: TuoteAllergeenit line: 52", kananmuna, i4.next()); 
    try {
    assertEquals("From: TuoteAllergeenit line: 53", laktoosi, i4.next()); 
    fail("TuoteAllergeenit: 53 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testIterator75 */
  @Test
  public void testIterator75() {    // TuoteAllergeenit: 75
    TuoteAllergeenit tat = new TuoteAllergeenit(); 
    TuoteAllergeeni laktoosi  = new TuoteAllergeeni(1,1); tat.lisaa(laktoosi); 
    TuoteAllergeeni kananmuna = new TuoteAllergeeni(1,2); tat.lisaa(kananmuna); 
    TuoteAllergeeni pahkinat  = new TuoteAllergeeni(1,3); tat.lisaa(pahkinat); 
    TuoteAllergeeni selleri   = new TuoteAllergeeni(1,4); tat.lisaa(selleri); 
    TuoteAllergeeni sinappi   = new TuoteAllergeeni(1,5); tat.lisaa(sinappi); 
    Iterator<TuoteAllergeeni> i2 = tat.iterator(); 
    assertEquals("From: TuoteAllergeenit line: 87", laktoosi, i2.next()); 
    assertEquals("From: TuoteAllergeenit line: 88", kananmuna, i2.next()); 
    assertEquals("From: TuoteAllergeenit line: 89", pahkinat, i2.next()); 
    assertEquals("From: TuoteAllergeenit line: 90", selleri, i2.next()); 
    assertEquals("From: TuoteAllergeenit line: 91", sinappi, i2.next()); 
    try {
    assertEquals("From: TuoteAllergeenit line: 92", laktoosi, i2.next()); 
    fail("TuoteAllergeenit: 92 Did not throw NoSuchElementException");
    } catch(NoSuchElementException _e_){ _e_.getMessage(); }
    int n = 0; 
    int jnrot[] = { 1,2,3,4,5} ; 
    for ( TuoteAllergeeni ta: tat ) {
    assertEquals("From: TuoteAllergeenit line: 98", jnrot[n], ta.haeAllergeeniID()); n++; 
    }
    assertEquals("From: TuoteAllergeenit line: 101", 5, n); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAnnaTuotteenAllergeenit116 */
  @Test
  public void testAnnaTuotteenAllergeenit116() {    // TuoteAllergeenit: 116
    TuoteAllergeenit tat = new TuoteAllergeenit(); 
    TuoteAllergeeni  ta1 = new TuoteAllergeeni(1,1); tat.lisaa(ta1); 
    TuoteAllergeeni  ta2 = new TuoteAllergeeni(1,2); tat.lisaa(ta2); 
    TuoteAllergeeni  ta3 = new TuoteAllergeeni(2,1); tat.lisaa(ta3); 
    TuoteAllergeeni  ta4 = new TuoteAllergeeni(1,3); tat.lisaa(ta4); 
    TuoteAllergeeni  ta5 = new TuoteAllergeeni(2,2); tat.lisaa(ta5); 
    TuoteAllergeeni  ta6 = new TuoteAllergeeni(3,1); tat.lisaa(ta6); 
    List<TuoteAllergeeni> loytyneet; 
    loytyneet = tat.annaTuotteenAllergeenit(1); 
    assertEquals("From: TuoteAllergeenit line: 129", 3, loytyneet.size()); 
    loytyneet = tat.annaTuotteenAllergeenit(4); 
    assertEquals("From: TuoteAllergeenit line: 131", 0, loytyneet.size()); 
    loytyneet = tat.annaTuotteenAllergeenit(2); 
    assertEquals("From: TuoteAllergeenit line: 133", 2, loytyneet.size()); 
    assertEquals("From: TuoteAllergeenit line: 134", true, loytyneet.get(0) == ta3); 
    assertEquals("From: TuoteAllergeenit line: 135", true, loytyneet.get(1) == ta5); 
    loytyneet = tat.annaTuotteenAllergeenit(3); 
    assertEquals("From: TuoteAllergeenit line: 137", 1, loytyneet.size()); 
    assertEquals("From: TuoteAllergeenit line: 138", true, loytyneet.get(0) == ta6); 
  } // Generated by ComTest END
}