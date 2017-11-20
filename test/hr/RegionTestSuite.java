package hr;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author ifontecha
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({hr.region.InsertarRegionTest.class, 
    hr.region.ModificarRegionTest.class, 
    hr.region.LeerRegionTest.class, 
    hr.region.LeerRegionsTest.class})
public class RegionTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
