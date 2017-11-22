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
 * Prueba del método modificarRegion de la clase HR
 * @author Adela Verdeja
 */
public class ModificarRegionTest {
    
    public ModificarRegionTest() {
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
    public void testModificarRegionOK() throws ExcepcionHR {
        System.out.println("modificarRegion - Caso de éxito");
        Region region = new Region(1,"Europa");
        HR instance = new HR();
        int expResult = 1;
        int result = instance.modificarRegion(1, region);
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba el caso de Violacion de Primary Key por el método
     * @throws hr.ExcepcionHR
     */
    @Test
    public void testModificarRegionViolacionPK() throws ExcepcionHR {
        System.out.println("modificarRegion - Caso de violacion de PK");
        Region region = new Region(3,"Singapur");
        HR instance = new HR();
        int expResult = 1;
        int result = instance.modificarRegion(3, region);
        assertEquals(expResult, result);
    }
    
      /**
     * Prueba el caso de Violacion de FK por el método
     * @throws hr.ExcepcionHR
     */
    
    @Test
    public void testModificarRegionViolacionFK() throws ExcepcionHR {
        System.out.println("modificarRegion - Caso de violacion de PK");
        Region region = new Region(1,"Europa");
        HR instance = new HR();
        int expResult = 1;
        int result = instance.modificarRegion(1, region);
        assertEquals(expResult, result);
    }
}
