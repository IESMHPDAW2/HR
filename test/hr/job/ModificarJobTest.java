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
 * Prueba del metodo ModificarJob de la clase HR
 * @author Ricardo
 */
public class ModificarJobTest {
    
    public ModificarJobTest() {
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
    public void testMoficarJobOK() throws ExcepcionHR {
        System.out.println("modificarJob - Caso de éxito");
        Job job=new Job("ADIOS","dd" , 0, 1000);
        HR instance=new HR();
        int expResult = 1;
        int result = instance.modificarJob("HOLA", job);
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la PK del método
     */
    @Test
    public void testModificarJobViolacionPK() {
        System.out.println("modificarJob - Caso de violación de PK");
        Job job=new Job("AD_PRES","adios", 0, 1000);
        try {
            HR instance = new HR();
            instance.modificarJob("TEST", job);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
    
    /**
     * Prueba la violación de NN del método
     */
    @Test
    public void testModificarJobViolacionNotNull() {
        System.out.println("modificarJob - Caso de violación de NN");
        Job job=new Job("IT_PROG",null , 0, 1000);
        try {
            HR instance = new HR();
            instance.modificarJob("IT_PROG", job);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1407);
        }
    }
    
    /**
     * Prueba la violación de FK del método
     */
    @Test
    public void testModificarJobViolacionFK() {
        System.out.println("modificarJob - Caso de violación de FK");
        Job job=new Job("eeee","Programmer" , 0, 1000);
        try {
            HR instance = new HR();
            instance.modificarJob("IT_PROG", job);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2292);
        }
    }
    

}
