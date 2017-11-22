package hr.jobhistory;

import hr.ExcepcionHR;
import hr.HR;
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
 * @author Adela Verdeja
 */
public class BorrarJobHistoryTest {

    public BorrarJobHistoryTest() {
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
     * @throws java.text.ParseException si se produce una excapción relacionada 
     * con el formato de fechas 
     * @throws ExcepcionHR si se produce cualquier otra excepcion
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
