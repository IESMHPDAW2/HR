/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.country;

import hr.ExcepcionHR;
import hr.HR;
import hr.Country;
import hr.Region;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del metodo InsertarCountry de la clase HR
 * @author Ricardo
 */
public class InsertarCountryTest {
    
    public InsertarCountryTest() {
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
     */
    @Test
    public void testInsertarCountryOK() throws ExcepcionHR {
        System.out.println("insertarCountry - Caso de éxito");
        Region region = new Region();
        region.setRegionId(1);
        Country country = new Country("HH","Prueba",region);
        HR instance = new HR();
        int expResult = 1;
        int result = instance.insertarCountry(country);
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la PK del método
     */
    @Test
    public void testInsertarCountryViolacionPK() {
        System.out.println("insertarCountry - Caso de violación de PK");
        Region region = new Region();
        region.setRegionId(1);
        Country country = new Country("US","Prueba",region);
        try {
            HR instance = new HR();
            instance.insertarCountry(country);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
    
    /**
     * Prueba la violación de la FK del método
     */
    @Test
    public void testInsertarCountryViolacionFK() {
        System.out.println("insertarCountry - Caso de violación de FK");
        Region region = new Region();
        region.setRegionId(99);
        Country country = new Country("YY","Prueba",region);
        try {
            HR instance = new HR();
            instance.insertarCountry(country);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2291);
        }
    }
    
    /**
     * Prueba la violación de NN del método
     */
    @Test
    public void testInsertarCountryViolacionNotNull() {
        System.out.println("insertarCountry - Caso de violación de NN");
        Region region = new Region();
        region.setRegionId(1);
        Country country = new Country(null,"Prueba",region);
        try {
            HR instance = new HR();
            instance.insertarCountry(country);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1400);
        }
    }
}
