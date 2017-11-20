/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.jobhistory;

import hr.ExcepcionHR;
import hr.HR;
import hr.Region;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del método borrarJobHistory de la clase HR
 *
 * @author Adela Verdeja
 */
public class borrarJobHistoryTest {

    public borrarJobHistoryTest() {
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
    public void testBorrarJobHistoryOK() throws ExcepcionHR, ParseException {
        System.out.println("borrarJobHistory - Caso de éxito");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = sdf.parse("2003-07-01");
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        HR instance = new HR();
        int expResult = 0;
        int result = instance.borrarJobHistory(200, fechaSql);
        assertEquals(expResult, result);
    }
}
