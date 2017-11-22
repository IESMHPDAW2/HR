package hr.job;

import hr.ExcepcionHR;
import hr.HR;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 * Prueba del método borrarJob de la clase HR
 * @author Jonathan León Lorenzo
 */
public class BorrarJobTest {
    
    public BorrarJobTest() {
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
    public void testBorrarJobOK() throws ExcepcionHR {
        System.out.println("borrarJob - Caso de éxito");
        HR instance = new HR();
        int expResult = 0;
        int result = instance.borrarJob("AAA");
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la FK por el método
     * @throws hr.ExcepcionHR
     */
    @Test
    public void testBorrarJobFK() throws ExcepcionHR {
        System.out.println("borrarJob - Caso de violación de FK");
        try {
            HR instance = new HR();
            instance.borrarJob("AC_MGR");
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2292);
        }
    }
    
    
}
