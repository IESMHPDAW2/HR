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
 * Prueba del método modificarDepartment de la clase HR
 * @author Alberto Martínez Gómez
 */
public class ModificarDepartmentTest {
    
    public ModificarDepartmentTest() {
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
    public void testModificarDepartmentOK() throws ExcepcionHR {
        System.out.println("modificarDepartment - Caso de éxito");
        HR instance = new HR();
        Employee e=new Employee();
        e.setEmployeeId(103);
        Location l=new Location();
        l.setLocationId(1700);
        Department d=new Department(223, "Martinez", e, l);
        int expResult = 1;
        int result = instance.modificarDepartment(223, d);
        assertEquals(expResult, result);
    }
    
    /**
     * Prueba la violación de la FK de tabla hija del método
     */
    @Test
    public void testModificarDepartmentViolacionFKHija() {
        System.out.println("modificarDepartment - Caso de violación de FK de tabla hija");
        try {
            HR instance = new HR();
            Employee e=new Employee();
            e.setEmployeeId(103);
            Location l=new Location();
            l.setLocationId(1700);
            Department d=new Department(555, "Martinez", e, l);
            instance.modificarDepartment(90, d);
            fail("No se ha lanzado una ExcepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2292);
        }
    }
    
    /**
     * Prueba la violación de la FK del método
     */
    @Test
    public void testModificarDepartmentViolacionFK() {
        System.out.println("modificarDepartment - Caso de violación de FK");
        try {
            HR instance = new HR();
            Employee e=new Employee();
            e.setEmployeeId(444);
            Location l=new Location();
            l.setLocationId(1700);
            Department d=new Department(223, "Martinez", e, l);
            instance.modificarDepartment(223, d);
            fail("No se ha lanzado una ExcepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),2291);
        }
    }
    
    /**
     * Prueba la violación de la NN del método
     */
    @Test
    public void testModificarDepartmentViolacionNN() {
        System.out.println("modificarDepartment - Caso de violación de NN");
        try {
            HR instance = new HR();
            Employee e=new Employee();
            e.setEmployeeId(100);
            Location l=new Location();
            l.setLocationId(1700);
            Department d=new Department(223, "", e, l);
            instance.modificarDepartment(223, d);
            fail("No se ha lanzado una ExcepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1407);
        }
    }
    
    /**
     * Prueba la violación de la PK del método
     */
    @Test
    public void testModificarDepartmentViolacionPK() {
        System.out.println("modificarDepartment - Caso de violación de PK");
        try {
            HR instance = new HR();
            Employee e=new Employee();
            e.setEmployeeId(100);
            Location l=new Location();
            l.setLocationId(1700);
            Department d=new Department(90, "Alberto", e, l);
            instance.modificarDepartment(223, d);
            fail("No se ha lanzado una ExcepcionHR");
        } catch (ExcepcionHR ex) {
            assertEquals(ex.getCodigoErrorSistema(),1);
        }
    }
}
