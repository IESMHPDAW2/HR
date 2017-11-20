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
public class LocationTest {
    
    public LocationTest() {
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
     * Test of getLocationId method, of class Location.
     */
    @Test
    public void testGetLocationId() {
        System.out.println("getLocationId");
        Location instance = new Location();
        int expResult = 0;
        int result = instance.getLocationId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLocationId method, of class Location.
     */
    @Test
    public void testSetLocationId() {
        System.out.println("setLocationId");
        int locationId = 0;
        Location instance = new Location();
        instance.setLocationId(locationId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStreetAddress method, of class Location.
     */
    @Test
    public void testGetStreetAddress() {
        System.out.println("getStreetAddress");
        Location instance = new Location();
        String expResult = "";
        String result = instance.getStreetAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStreetAddress method, of class Location.
     */
    @Test
    public void testSetStreetAddress() {
        System.out.println("setStreetAddress");
        String streetAddress = "";
        Location instance = new Location();
        instance.setStreetAddress(streetAddress);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostalCode method, of class Location.
     */
    @Test
    public void testGetPostalCode() {
        System.out.println("getPostalCode");
        Location instance = new Location();
        String expResult = "";
        String result = instance.getPostalCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPostalCode method, of class Location.
     */
    @Test
    public void testSetPostalCode() {
        System.out.println("setPostalCode");
        String postalCode = "";
        Location instance = new Location();
        instance.setPostalCode(postalCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCity method, of class Location.
     */
    @Test
    public void testGetCity() {
        System.out.println("getCity");
        Location instance = new Location();
        String expResult = "";
        String result = instance.getCity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCity method, of class Location.
     */
    @Test
    public void testSetCity() {
        System.out.println("setCity");
        String city = "";
        Location instance = new Location();
        instance.setCity(city);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStateProvince method, of class Location.
     */
    @Test
    public void testGetStateProvince() {
        System.out.println("getStateProvince");
        Location instance = new Location();
        String expResult = "";
        String result = instance.getStateProvince();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStateProvince method, of class Location.
     */
    @Test
    public void testSetStateProvince() {
        System.out.println("setStateProvince");
        String stateProvince = "";
        Location instance = new Location();
        instance.setStateProvince(stateProvince);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountry method, of class Location.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Location instance = new Location();
        Country expResult = null;
        Country result = instance.getCountry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCountry method, of class Location.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        Country country = null;
        Location instance = new Location();
        instance.setCountry(country);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Location.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Location instance = new Location();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
