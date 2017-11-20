package hr.department;

import hr.ExcepcionHR;
import hr.HR;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del método borrarDepartment de la clase HR
 * @author Pilar Sánchez Sausa
 */
public class BorrarDepartmentTest {
    
    public BorrarDepartmentTest() {
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
     * @throws hr.ExcepcionHR
     */
    @Test
    public void testBorrarDepartmentOK() throws ExcepcionHR {
        System.out.println("borrarDepartment - Caso de éxito");
        HR instance = new HR();
        int expResult = 1;
        int result = instance.borrarDepartment(230);
        assertEquals(expResult, result);
    }

    /**
     * Prueba la violación de la PK del método
     */
    @Test
    public void testBorrarDepartmentViolacionFK() {
        System.out.println("borrarDepartment- Caso de violación de FK de tabla hija");
        try {
            HR instance = new HR();
            instance.borrarDepartment(60);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2292);
        }
    }
}
