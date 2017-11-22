package hr.country;

import hr.Country;
import hr.ExcepcionHR;
import hr.HR;
import hr.Region;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Prueba del método modificarCountry de la clase HR
 * @author Jonathan León Lorenzo
 */
public class ModificarCountryTest {
    
    public ModificarCountryTest() {
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
    public void testModificarCountryOK() throws ExcepcionHR {
        System.out.println("ModificarCountry - Caso de éxito");
        Region region=new Region();
        region.setRegionId(1);
        Country cantabria=new Country("CB", "Cantabria", region);
        HR instance = new HR();
        int expResult = 0;
        int result = instance.modificarCountry("CB", cantabria);
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la PK por el método
     */
    @Test
    public void testModificarCountryPK() throws ExcepcionHR {
        System.out.println("ModificarCountry - Caso de violación de PK");
        Region region=new Region();
        region.setRegionId(1);
        Country cantabria=new Country("AU", "Cantabriaaaaaa", region);
        try {
            HR instance = new HR();
            instance.modificarCountry("CB", cantabria);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
    
    /**
     * Prueba la violación de la FK por el método
     */
    @Test
    public void testModificarCountryFK() throws ExcepcionHR {
        System.out.println("ModificarCountry - Caso de violación de FK");
        Region region=new Region();
        region.setRegionId(50);
        Country cantabria=new Country("CB", "Cantabriaaa", region);
        try {
            HR instance = new HR();
            instance.modificarCountry("CB", cantabria);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2291);
        }
    }
}
