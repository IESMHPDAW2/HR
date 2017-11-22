package hr.department;

import hr.Department;
import hr.Employee;
import hr.ExcepcionHR;
import hr.HR;
import hr.Location;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Prueba del método insertarDepartment de la clase HR
 * @author David Fernandez Garcia
 */
public class InsertarDepartmentTest {
    
    public InsertarDepartmentTest() {
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
     * @throws ExcepcionHR si se produce cualquier excepcion
     */
    @Test
    public void testInsertarDepartmentOK() throws ExcepcionHR {
        System.out.println("insertarDepartment - Caso de éxito");
        Employee manager = new Employee();
        manager.setEmployeeId(108);
        
        Location location = new Location();
        location.setLocationId(1700);
        
        Department department = new Department(300,"Prueba",manager,location);
        
        HR instance = new HR();
        int expResult = 1;
        int result = instance.insertarDepartment(department);
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la PK por el método
     */
    @Test
    public void testInsertarDeparmentViolacionPK() {
        System.out.println("insertarDepartment - Caso de violación de PK");
        Employee manager = new Employee();
        manager.setEmployeeId(108);
        Location location = new Location();
        location.setLocationId(1700);
        Department department = new Department(110,"Prueba",manager,location);
        try {
            HR instance = new HR();
            instance.insertarDepartment(department);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
    
    
    /**
     * Prueba la violacion de NN por el método
     */
    @Test
    public void testInsertarDeparmentViolacionNN() {
        System.out.println("insertarDepartment - Caso de violación de NN");
        Employee manager = new Employee();
        manager.setEmployeeId(108);
        Location location = new Location();
        location.setLocationId(1700);
        Department department = new Department(300,"",manager,location);
        try {
            HR instance = new HR();
            instance.insertarDepartment(department);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1400);
        }
    }
    
    /**
     * Prueba la violacion de FK por el método
     */
    @Test
    public void testInsertarDeparmentViolacionFK() {
        System.out.println("insertarDepartment - Caso de violación de FK");
        Employee manager = new Employee();
        manager.setEmployeeId(300);
        Location location = new Location();
        location.setLocationId(2900);
        Department department = new Department(300,"Prueba",manager,location);
        try {
            HR instance = new HR();
            instance.insertarDepartment(department);
            fail("No se ha lanzado una ExccepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2291);
        }
    }
}
