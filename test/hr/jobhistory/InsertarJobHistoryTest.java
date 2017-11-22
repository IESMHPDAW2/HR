package hr.jobhistory;

import hr.Department;
import hr.Employee;
import hr.ExcepcionHR;
import hr.HR;
import hr.Job;
import hr.JobHistory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del método insertarJobHistory de la clase HR
 * @author Pilar Sánchez Sausa
 */
public class InsertarJobHistoryTest {

    public InsertarJobHistoryTest() {
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
    public void testInsertarJobHistoryOK() throws ExcepcionHR, ParseException {
        System.out.println("insertarJobHistory - Caso de éxito");

        Employee e = new Employee();
        e.setEmployeeId(149);
        Job j = new Job();
        j.setJobId("AD_PRES");
        Department d = new Department();
        d.setDepartmentId(70);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = sdf.parse("2017-11-06");
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        java.util.Date fechaUtilf = sdf.parse("2017-11-07");
        java.sql.Date fechaSqlf = new java.sql.Date(fechaUtilf.getTime());
        JobHistory jh = new JobHistory(e, fechaSql, fechaSqlf, j, d);
        HR instance = new HR();
        int expResult = 1;
        int result = instance.insertarJobHistory(jh);
        assertEquals(expResult, result);
    }

    /**
     * Prueba la violación de la PK por el método
     */
    @Test
    public void testInsertarRegionViolacionPK() throws ParseException {
        System.out.println("insertarRegion - Caso de violación de PK");
        Employee e = new Employee();
        e.setEmployeeId(200);
        Job j = new Job();
        j.setJobId("AD_PRES");
        Department d = new Department();
        d.setDepartmentId(70);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = sdf.parse("1995-09-17");
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        java.util.Date fechaUtilf = sdf.parse("2017-11-07");
        java.sql.Date fechaSqlf = new java.sql.Date(fechaUtilf.getTime());
        JobHistory jh = new JobHistory(e, fechaSql, fechaSqlf, j, d);
        try {
            HR instance = new HR();
            instance.insertarJobHistory(jh);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(), 1);
        }
    }
    
     /**
     * Prueba la violación de la NN por el método
     */
    @Test
    public void testInsertarRegionViolacionNN() throws ParseException {
        System.out.println("insertarRegion - Caso de violación de NN");
        Employee e = new Employee();
        e.setEmployeeId(147);
        Job j = new Job();
        j.setJobId("AD_PRES");
        Department d = new Department();
        d.setDepartmentId(70);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = sdf.parse("1995-09-17");
        java.sql.Date fechaSqlf=null;
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        JobHistory jh = new JobHistory(e, fechaSql, fechaSqlf, j, d);
        try {
            HR instance = new HR();
            instance.insertarJobHistory(jh);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(), 1400);
        }
    }

}
