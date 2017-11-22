package hr.region;

import hr.ExcepcionHR;
import hr.HR;
import hr.Region;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ifontecha
 */
public class LeerRegionsTest {
    
    public LeerRegionsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of leerRegions method, of class HR.
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    @Test
    public void testLeerRegions() throws ExcepcionHR {
        System.out.println("leerRegions");
        HR instance = new HR();
        ArrayList<Region> result = instance.leerRegions();
        assertNotNull(result);
    }
}
