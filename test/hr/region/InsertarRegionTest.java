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
 * Prueba del método insertarRegion de la clase HR
 * @author Ignacio Fontecha Hernández
 */
public class InsertarRegionTest {
    
    public InsertarRegionTest() {
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
    public void testInsertarRegionOK() throws ExcepcionHR {
        System.out.println("insertarRegion - Caso de éxito");
        Region region = new Region(100,"Groenlandia");
        HR instance = new HR();
        int expResult = 1;
        int result = instance.insertarRegion(region);
        assertEquals(expResult, result);
    }

    /**
     * Prueba la violación de la PK del método
     */
    @Test
    public void testInsertarRegionViolacionPK() {
        System.out.println("insertarRegion - Caso de violación de PK");
        Region region = new Region(1,"Groenlandia");
        try {
            HR instance = new HR();
            instance.insertarRegion(region);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
}
