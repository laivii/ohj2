package allergiainfo.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * @author Viivi
 * @version 10.3.2025
 *
 */
@Suite
@SelectClasses({ AllergeeniTest.class, AllergeenitTest.class,
        RavintolaTest.class, RavintolatTest.class, TuoteAllergeenitTest.class,
        TuoteTest.class, TuotteetTest.class })
public class AllTests {
 //
}
