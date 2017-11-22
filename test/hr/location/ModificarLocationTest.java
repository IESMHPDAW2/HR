package hr.location;

import hr.Country;
import hr.ExcepcionHR;
import hr.HR;
import hr.Location;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Prueba del método modificarLocation de la clase HR
 * @author Ruben Argumosa
 */
public class ModificarLocationTest {
    public ModificarLocationTest(){
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
    public void testModificarLocationOK() throws ExcepcionHR {
        System.out.println("modificarLocation - Caso de éxito");
        Country country = new Country();
        country.setCountryId("AU");
        Location location=new Location(1000,"Calle pepin del rio Gatoo","39300","Torrelavega","",country);
        
        HR instance = new HR();
        int expResult = 1;
        int result = instance.modificarLocation(1000, location);
        assertEquals(expResult, result);
    }

    /**
     * Prueba la violación de la PK por el método
     */
    @Test
    public void modificarLocationViolacionPK() {
        System.out.println("ModificarLocation - Caso de violación de PK");
       Country country = new Country();
        country.setCountryId("AU");
        Location location=new Location(1100,"Calle pepin del rio Gatoo","39300","Torrelavega","",country);
        try {
            HR instance = new HR();
            instance.modificarLocation(1000, location);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
    
    /**
     * Prueba la violación de la FKPadre por el método
     */
    @Test
    public void modificarLocationViolacionFKPadre() {
        System.out.println("ModificarLocation - Caso de violación de FKPadre");
       Country country = new Country();
        country.setCountryId("RR");
        Location location=new Location(1000,"Calle pepin del rio Gatoo","39300","Torrelavega","",country);
        try {
            HR instance = new HR();
            instance.modificarLocation(1000, location);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2291);
        }
    }
    
    /**
     * Prueba la violación de la FKHija por el método
     */
    @Test
    public void modificarLocationViolacionFKHija() {
        System.out.println("ModificarLocation - Caso de violación de FKHija");
       Country country = new Country();
        country.setCountryId("AU");
        Location location=new Location(1701,"Calle pepin del rio Gatoo","39300","Torrelavega","",country);
        try {
            HR instance = new HR();
            instance.modificarLocation(1700, location);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2292);
        }
    }
    
    /**
     * Prueba la violación de la NN por el método
     */
    @Test
    public void modificarLocationViolacionNN() {
        System.out.println("ModificarLocation - Caso de violación de NN");
       Country country = new Country();
        country.setCountryId("AU");
        Location location=new Location(1000,"Calle pepin del rio Gatoo","39300","","",country);
        try {
            HR instance = new HR();
            instance.modificarLocation(1000, location);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1407);
        }
    }
}
