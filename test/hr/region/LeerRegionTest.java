package hr.region;

import hr.ExcepcionHR;
import hr.HR;
import hr.Region;
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
public class LeerRegionTest {
    
    public LeerRegionTest() {
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
     * Test of insertarRegion method, of class HR.
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    @Test
    public void testleerRegion() throws ExcepcionHR {
        System.out.println("leerRegion");
        int regionId = 0;
        HR instance = new HR();
        Region expResult = null;
        Region result = instance.leerRegion(regionId);
        assertEquals(expResult, result);
    }
}
