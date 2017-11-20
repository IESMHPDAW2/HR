/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.job;

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
 * Prueba del método BorrarJob de la clase HR
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
     * @throws hr.ExcepcionHR
     */
    @Test
    public void testBorrarJobOK() throws ExcepcionHR {
        System.out.println("BorrarJob - Caso de éxito");
        HR instance = new HR();
        int expResult = 0;
        int result = instance.borrarJob("AAA");
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la FK del método
     * @throws hr.ExcepcionHR
     */
    @Test
    public void testBorrarJobFK() throws ExcepcionHR {
        System.out.println("BorrarJob - Caso de violación de FK");
        try {
            HR instance = new HR();
            instance.borrarJob("AC_MGR");
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2292);
        }
    }
    
    
}
