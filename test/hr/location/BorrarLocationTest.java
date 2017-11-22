package hr.location;

import hr.ExcepcionHR;
import hr.HR;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del método borrarLocation de la clase HR
 * @author Alberto Martínez Gómez
 */
public class BorrarLocationTest {
    
    public BorrarLocationTest() {
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
    public void testBorrarLocationOK() throws ExcepcionHR {
        System.out.println("borrarLocation - Caso de éxito");
        HR instance = new HR();
        int expResult = 1;
        int result = instance.borrarLocation(6656);
        assertEquals(expResult, result);
    }
    
     /**
     * Prueba la violación de la FK por el método
     */
    @Test
    public void testBorrarLocationViolacionFK() {
        System.out.println("borrarLocation - Caso de violación de FK");
        try {
            HR instance = new HR();
            instance.borrarLocation(1700);
            fail("No se ha lanzado una ExcepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2292);
        }
    }
}
