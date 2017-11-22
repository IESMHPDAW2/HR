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
 * Prueba el metodo borrarRegion de la HR
 * @author Byron Morales
 */
public class BorrarRegionTest {
    
    public BorrarRegionTest() {
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
     * Prueba el caso de éxito del método
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    @Test
    public void testBorrarRegionOK() throws ExcepcionHR{
        System.out.println("borrarRegion - Caso de éxito");
        HR instance = new HR();
        int expResult = 1;
        int result = instance.borrarRegion(5);
        assertEquals(expResult, result);
    }
    
    
    /**
     * Prueba la violación de la FK por el método
     */
    @Test
    public void testBorrarRegionViolacionFK(){    
        System.out.println("borrarRegion - Caso de violación de FK");
        try {
            HR hr = new HR();
            hr.borrarRegion(2);     
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(), 2292);
        }
    }
}
