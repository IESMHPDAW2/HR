/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.country;

import hr.Country;
import hr.ExcepcionHR;
import hr.HR;
import hr.region.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del método BorrarCountry de la clase HR
 * @author Carlos Labrador Amieva
 */
public class BorrarCountryTest {
    
    public BorrarCountryTest() {
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
    public void testBorrarCountryOK() throws ExcepcionHR {
        System.out.println("borrarCountry - Caso de éxito");
        HR instance = new HR();
        int expResult = 0; //Al llamar a un procedimiento he tenido que poner por defecto 0
        int result = instance.borrarCountry("QI");  //Será necesario añadir un país con ID "QI"
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la FK del método
     */
    @Test
    public void testBorrarCountryViolacionFK() {
        System.out.println("BorrarCountry - Caso de violación de FK");
        Country country = new Country();
        country.setCountryId("US");
        try {
            HR instance = new HR();
            instance.borrarCountry(country.getCountryId());
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2292);
        }
    }
}
