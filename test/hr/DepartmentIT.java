/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class DepartmentIT {
    
    public DepartmentIT() {
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
     * Test of getDepartmentId method, of class Department.
     */
    @Test
    public void testGetDepartmentId() {
        System.out.println("getDepartmentId");
        Department instance = new Department();
        int expResult = 0;
        int result = instance.getDepartmentId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDepartmentId method, of class Department.
     */
    @Test
    public void testSetDepartmentId() {
        System.out.println("setDepartmentId");
        int departmentId = 0;
        Department instance = new Department();
        instance.setDepartmentId(departmentId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDepartmentName method, of class Department.
     */
    @Test
    public void testGetDepartmentName() {
        System.out.println("getDepartmentName");
        Department instance = new Department();
        String expResult = "";
        String result = instance.getDepartmentName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDepartmentName method, of class Department.
     */
    @Test
    public void testSetDepartmentName() {
        System.out.println("setDepartmentName");
        String departmentName = "";
        Department instance = new Department();
        instance.setDepartmentName(departmentName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getManager method, of class Department.
     */
    @Test
    public void testGetManager() {
        System.out.println("getManager");
        Department instance = new Department();
        Employee expResult = null;
        Employee result = instance.getManager();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setManager method, of class Department.
     */
    @Test
    public void testSetManager() {
        System.out.println("setManager");
        Employee manager = null;
        Department instance = new Department();
        instance.setManager(manager);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLocation method, of class Department.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Department instance = new Department();
        Location expResult = null;
        Location result = instance.getLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLocation method, of class Department.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        Location location = null;
        Department instance = new Department();
        instance.setLocation(location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Department.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Department instance = new Department();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
