package hr.employee;

import hr.Department;
import hr.Employee;
import hr.ExcepcionHR;
import hr.HR;
import hr.Job;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del metodo InsertarEmployee de la clase HR
 * @author Carlos y Ricardo
 */
public class InsertarEmployeeTest {
    
    public InsertarEmployeeTest() {
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
    public void testInsertarEmployeeOK() throws ExcepcionHR, ParseException {
        System.out.println("insertarEmployee - Caso de éxito");
        Employee manager = new Employee();
        manager.setEmployeeId(100);
        Department department = new Department();
        department.setDepartmentId(50);
        Job job = new Job();
        job.setJobId("IT_PROG");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = null;
        try {
            fechaUtil = sdf.parse("2017-10-19");
        } catch (ParseException ex) {
           System.out.println(ex);
        }
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        Employee employee = new Employee(549,null,"Ruiz","fghgfhgf","696121212",fechaSql, job,4500,0,manager,department);
        HR instance = new HR();
        int expResult = 1;
        int result = instance.insertarEmployee(employee);
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba de la violacion de PK por el metodo
     * @throws java.text.ParseException si se produce una excapción relacionada 
     * con el formato de fechas 
     * @throws ExcepcionHR si se produce cualquier otra excepcion
     */
    @Test
    public void testInsertarEmployeeViolacionPK() throws ExcepcionHR, ParseException {
        System.out.println("insertarEmployee -  Caso de violación de PK");
        Employee manager = new Employee();
        manager.setEmployeeId(100);
        Department department = new Department();
        department.setDepartmentId(50);
        Job job = new Job();
        job.setJobId("IT_PROG");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = null;
        try {
            fechaUtil = sdf.parse("2017-10-19");
        } catch (ParseException ex) {
           System.out.println(ex);
        }
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        Employee employee = new Employee(100,"Eustaquio","RUIZ","SKING","696121212",fechaSql, job,4500,0,manager,department);
        try {
            HR instance = new HR();
            instance.insertarEmployee(employee);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }    
    
    /**
     * Prueba de la violacion de la FK por el metodo
     * @throws java.text.ParseException si se produce una excapción relacionada 
     * con el formato de fechas 
     * @throws ExcepcionHR si se produce cualquier otra excepcion
     */
    @Test
    public void testInsertarEmployeeViolacionFK() throws ExcepcionHR, ParseException {
        System.out.println("insertarEmployee - Caso de violación de FK");
        Employee manager = new Employee();
        manager.setEmployeeId(100);
        Department department = new Department();
        department.setDepartmentId(999);
        Job job = new Job();
        job.setJobId("IT_PROG");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = null;
        try {
            fechaUtil = sdf.parse("2017-10-19");
        } catch (ParseException ex) {
           System.out.println(ex);
        }
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        Employee employee = new Employee(874,null,"Ruiz","fghgfhgf","696121212",fechaSql, job,4500,0,manager,department);
        try {
            HR instance = new HR();
            instance.insertarEmployee(employee);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2291);
        }
    }
    
    /**
     * Prueba de la violacion de NN por el metodo
     * @throws java.text.ParseException si se produce una excapción relacionada 
     * con el formato de fechas 
     * @throws ExcepcionHR si se produce cualquier otra excepcion
     */
    @Test
    public void testInsertarEmployeeViolacionNN() throws ExcepcionHR, ParseException {
        System.out.println("insertarEmployee -  Caso de violación de NN");
        Employee manager = new Employee();
        manager.setEmployeeId(100);
        Department department = new Department();
        department.setDepartmentId(50);
        Job job = new Job();
        job.setJobId("IT_PROG");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = null;
        try {
            fechaUtil = sdf.parse("2017-10-19");
        } catch (ParseException ex) {
           System.out.println(ex);
        }
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        Employee employee = new Employee(874,null,null,"fghgfhgf","696121212",fechaSql, job,4500,0,manager,department);
        try {
            HR instance = new HR();
            instance.insertarEmployee(employee);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1400);
        }
    }
    
    /**
     * Prueba de la violacion de CK por el metodo
     * @throws java.text.ParseException si se produce una excapción relacionada 
     * con el formato de fechas 
     * @throws ExcepcionHR si se produce cualquier otra excepcion
     */
    @Test
    public void testInsertarEmployeeViolacionCK() throws ExcepcionHR, ParseException {
        System.out.println("insertarEmployee -  Caso de violación de CK");
        Employee manager = new Employee();
        manager.setEmployeeId(100);
        Department department = new Department();
        department.setDepartmentId(50);
        Job job = new Job();
        job.setJobId("IT_PROG");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date fechaUtil = null;
        try {
            fechaUtil = sdf.parse("2017-10-19");
        } catch (ParseException ex) {
           System.out.println(ex);
        }
        java.sql.Date fechaSql = new java.sql.Date(fechaUtil.getTime());
        Employee employee = new Employee(560,"Eustaquio","RUIZ","fghgfhgf","696121212",fechaSql, job,-4500,0,manager,department);
        try {
            HR instance = new HR();
            instance.insertarEmployee(employee);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2290);
        }
    }
}
