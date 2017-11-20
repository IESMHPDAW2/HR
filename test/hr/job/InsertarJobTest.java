/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.job;

import hr.ExcepcionHR;
import hr.HR;
import hr.Job;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del método insertarJob de la clase HR
 * @author Carlos Labrador Amieva
 */
public class InsertarJobTest {
    
    public InsertarJobTest() {
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
    public void testInsertarJobOK() throws ExcepcionHR {
        System.out.println("insertarJob - Caso de éxito");
        Job job = new Job("PRUEBA", "PRUEBA", 1000, 1000);
        HR instance = new HR();
        int expResult = 1;
        int result = instance.insertarJob(job);
        assertEquals(expResult, result);
    }

    /**
     * Prueba la violación de la PK del método
     */
    @Test
    public void testInsertarJobViolacionPK() {
        System.out.println("insertarJob - Caso de violación de PK");
        Job job = new Job("AD_PRES", "PRUEBA", 1000, 1000);
        try {
            HR instance = new HR();
            instance.insertarJob(job);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
    
    /**
     * Prueba la restrición del NN del método
     */
    @Test
    public void testInsertarJobViolacionNN() {
        System.out.println("insertarJob - Caso de violación de NN");
        Job job = new Job("PRUEBA1",null, 1000, 10000);
        try {
            HR instance = new HR();
            instance.insertarJob(job);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1400);
        }
    }
}
