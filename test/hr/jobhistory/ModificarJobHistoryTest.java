package hr.jobhistory;

import hr.Department;
import hr.Employee;
import hr.ExcepcionHR;
import hr.HR;
import hr.Job;
import hr.JobHistory;
import hr.Location;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author David Fernandez Garcia
 */
public class ModificarJobHistoryTest {
    
     public ModificarJobHistoryTest() {
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
    public void testModificarJobHistoryOK() throws ExcepcionHR, ParseException {
        System.out.println("modificarJobHistory - Caso de éxito");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        
        Employee empleado= new Employee();
        empleado.setEmployeeId(198);
                  
        java.util.Date fechaUtil = sdf.parse("01/10/12");
        java.sql.Date fechaPK = new java.sql.Date(fechaUtil.getTime());
        
        java.util.Date fechasUtil1 = sdf.parse("01/01/12");
        java.sql.Date startDate = new java.sql.Date(fechasUtil1.getTime());

        java.util.Date fechaeUtil2 = sdf.parse("24/07/15");
        java.sql.Date endDate = new java.sql.Date(fechaeUtil2.getTime());
        
        Department department= new Department();
        department.setDepartmentId(80);
        
        Job job= new Job();
        job.setJobId("SA_MAN");
        
        JobHistory jobhistory = new JobHistory(empleado,startDate,endDate,job,department);
        
        HR instance = new HR();
        int expResult = 0;
        int result = instance.modificarJobHistory(198,fechaPK,jobhistory);
        
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la PK del método
     */
    @Test
    public void testModificarJobHistoryViolacionPK() throws ParseException {
        System.out.println("modificarJobHistory - Caso de violación de PK");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        
        Employee empleado= new Employee();
        empleado.setEmployeeId(102);
           
            
        java.util.Date fechaUtil = sdf.parse("17/02/04");
        java.sql.Date fechaPK = new java.sql.Date(fechaUtil.getTime());

        java.util.Date fechasUtil1 = sdf.parse("13/01/02");
        java.sql.Date startDate = new java.sql.Date(fechasUtil1.getTime());

        java.util.Date fechaeUtil2 = sdf.parse("24/07/08");
        java.sql.Date endDate = new java.sql.Date(fechaeUtil2.getTime());
        
        Department department= new Department();
        department.setDepartmentId(90);
        
        Job job= new Job();
        job.setJobId("AC_ACCOUNT");
        
        JobHistory jobhistory = new JobHistory(empleado,startDate,endDate,job,department);
        
        try {
            HR instance = new HR();
            instance.modificarJobHistory(201,fechaPK,jobhistory);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }

     /**
     * Prueba la violacion de FK del metodo
     */
    @Test
    public void testModificarJobHistoryViolacionFK() throws ParseException {
        System.out.println("modificarJobHistory - Caso de violación de FK");
        Employee empleado= new Employee();
        empleado.setEmployeeId(300);
        
        Department department= new Department();
        department.setDepartmentId(300);
        
        Job job= new Job();
        job.setJobId("AC_ACCOUNT");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        java.util.Date fechaUtil = sdf.parse("13/01/02");
        java.sql.Date fechaPK = new java.sql.Date(fechaUtil.getTime());

        java.util.Date fechasUtil1 = sdf.parse("13/01/02");
        java.sql.Date startDate = new java.sql.Date(fechasUtil1.getTime());

        java.util.Date fechaeUtil2 = sdf.parse("24/07/08");
        java.sql.Date endDate = new java.sql.Date(fechaeUtil2.getTime());
        
        JobHistory jobhistory = new JobHistory(empleado,startDate,endDate,job,department);
        
        try {
            HR instance = new HR();
            instance.modificarJobHistory(102,fechaPK,jobhistory);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2291);
        }
    }
    
    /**
     * Prueba la violacion de fecha del metodo
     */
    @Test
    public void testModificarJobHistoryViolacionFecha() throws ParseException {
        System.out.println("modificarJobHistory - Caso de violación de Fecha");
        Employee empleado= new Employee();
        empleado.setEmployeeId(102);
        
        Department department= new Department();
        department.setDepartmentId(90);
        
        Job job= new Job();
        job.setJobId("AC_ACCOUNT");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        java.util.Date fechaUtil = sdf.parse("13/01/02");
        java.sql.Date fechaPK = new java.sql.Date(fechaUtil.getTime());

        java.util.Date fechasUtil1 = sdf.parse("13/01/08");
        java.sql.Date startDate = new java.sql.Date(fechasUtil1.getTime());

        java.util.Date fechaeUtil2 = sdf.parse("24/07/05");
        java.sql.Date endDate = new java.sql.Date(fechaeUtil2.getTime());
        
        JobHistory jobhistory = new JobHistory(empleado,startDate,endDate,job,department);
        
        try {
            HR instance = new HR();
            instance.modificarJobHistory(102,fechaPK,jobhistory);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2290);
        }
    }
}
