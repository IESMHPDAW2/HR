package hr.employee;

import hr.ExcepcionHR;
import hr.HR;
import hr.JobHistory;
import hr.Region;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del método borrarEmployee de la clase HR
 * @author usuario
 */
public class BorrarEmployeeTest {

    public BorrarEmployeeTest() {
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
     * Prueba el caso de exito del método
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    @Test
    public void testBorrarEmployeeOK() throws ExcepcionHR {
        System.out.println("borrarEmployee - Caso de exito");
        try {
            HR hr = new HR();
            int expResult = 1;
            int result = hr.borrarEmployee(102);
            assertEquals(expResult, result);
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(), 2292);
        }
    }

    /**
     * Prueba el caso de violacion de FK por el método
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    @Test
    public void testBorrarEmployeeViolacionFK() throws ExcepcionHR {
        System.out.println("borrarEmployee - Caso de Violacion de FK");
        try {
            HR hr = new HR();
            hr.borrarEmployee(102);
            fail("No se ha lanzado una excepcion HR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(), 2292);
        }
    }

    

}
