/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.location;

import hr.Country;
import hr.ExcepcionHR;
import hr.HR;
import hr.Location;
import hr.Region;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tamara
 */
public class InsertarLocationTest {
    
    public InsertarLocationTest() {
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
    public void testInsertarLocationOK() throws ExcepcionHR {
        System.out.println("insertarLocation - Caso de éxito");
        Country c = new Country();
        c.setCountryId("IT");
        Location location = new Location(1004, "Pereda", "39300", "Torrelavega", "Cantabria", c);
        HR instance = new HR();
        int expResult = 1;
        int result = instance.insertarLocation(location);
        assertEquals(expResult, result);
    }

    /**
     * Prueba la violación de la PK del método
     */
    @Test
    public void testInsertarLocationViolacionPK() {
        System.out.println("insertarLocation - Caso de violación de PK");
        Country c = new Country();
        c.setCountryId("IT");
        Location location = new Location(1000, "Pereda", "39590", "Torrelavega", "Cantabria", c);
        try {
            HR instance = new HR();
            instance.insertarLocation(location);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
    /**
     * Prueba la violación de la FK del método
     */
    @Test
    public void testInsertarLocationViolacionFK() {
        System.out.println("insertarLocation - Caso de violación de FK");
        Country c = new Country();
        c.setCountryId("AA");
        Location location = new Location(9999, "Pereda", "39300", "Torre", "Cantabria", c);
        try {
            HR instance = new HR();
            instance.insertarLocation(location);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2291);
        }
    }
    /**
     * Prueba la violación del NN del método
     */
    @Test
    public void testInsertarLocationViolacionNN() {
        System.out.println("insertarLocation - Caso de violación de NN");
        Country c = new Country();
        c.setCountryId("IT");
        Location location = new Location(1000, "Pereda", "39590", "", "Cantabria", c);
        try {
            HR instance = new HR();
            instance.insertarLocation(location);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1400);
        }
    }
}
