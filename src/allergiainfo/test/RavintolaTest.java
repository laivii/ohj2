package allergiainfo.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import allergiainfo.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2025.02.24 11:10:19 // Generated by ComTest
 *
 */
@SuppressWarnings("all")
public class RavintolaTest {


  // Generated by ComTest BEGIN
  /** testRekisteroi49 */
  @Test
  public void testRekisteroi49() {    // Ravintola: 49
    Ravintola r = new Ravintola(); 
    assertEquals("From: Ravintola line: 51", 0, r.getId()); 
    r.rekisteroi(); 
    Ravintola r2 = new Ravintola(); 
    assertEquals("From: Ravintola line: 54", 0, r2.getId()); 
    r2.rekisteroi(); 
    int n1 = r.getId(); 
    int n2 = r2.getId(); 
    assertEquals("From: Ravintola line: 58", n1+1, n2); 
  } // Generated by ComTest END
}