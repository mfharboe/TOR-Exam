/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.BEAlarm;
import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BEMaterial;
import BE.BERole;
import BE.BERoleTime;
import BE.BESalary;
import BE.BEUsage;
import BE.BEVehicle;
import BE.BEZipcode;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zoracka
 */
public class BLLReadTest {
    
    static ArrayList<BEFireman> array_firemen;
    static ArrayList<BEZipcode> array_zipcodes;
    static BEFireman befiremen = null;
    static BEZipcode bezipcode = null;
    public BLLReadTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        array_firemen = new ArrayList<>();
        array_zipcodes = new ArrayList<>();
        bezipcode = new BEZipcode(6670, "Holsted");
       // befiremen = new BEFireman(1, "Andreas", "Mørch", "Holsted", bezipcode, 1234, 4321, true, "photopath", "cpr");
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of readAllFiremen method, of class BLLRead.
     */
    @Test
    public void testReadAllFiremen() {
     
//        System.out.println("readAllFiremen");
//        BLLRead instance = null;
//        ArrayList<BEFireman> expResult = null;
//        ArrayList<BEFireman> result = instance.readAllFiremen();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of readAllVehicles method, of class BLLRead.
     */
    @Test
    public void testReadAllVehicles() {
        System.out.println("readAllVehicles");
        BLLRead instance = null;
        ArrayList<BEVehicle> expResult = null;
        ArrayList<BEVehicle> result = instance.readAllVehicles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAllIncidentTypes method, of class BLLRead.
     */
    @Test
    public void testReadAllIncidentTypes() {
        System.out.println("readAllIncidentTypes");
        BLLRead instance = null;
        ArrayList<BEIncidentType> expResult = null;
        ArrayList<BEIncidentType> result = instance.readAllIncidentTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAllIncidents method, of class BLLRead.
     */
    @Test
    public void testReadAllIncidents() {
        System.out.println("readAllIncidents");
        BLLRead instance = null;
        ArrayList<BEIncident> expResult = null;
        ArrayList<BEIncident> result = instance.readAllIncidents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToIncident method, of class BLLRead.
     */
    @Test
    public void testAddToIncident() {
        System.out.println("addToIncident");
        BEIncident incident = null;
        BLLRead instance = null;
        instance.addToIncident(incident);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAllRoles method, of class BLLRead.
     */
    @Test
    public void testReadAllRoles() {
        System.out.println("readAllRoles");
        BLLRead instance = null;
        ArrayList<BERole> expResult = null;
        ArrayList<BERole> result = instance.readAllRoles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAllRoleTimes method, of class BLLRead.
     */
    @Test
    public void testReadAllRoleTimes() {
        System.out.println("readAllRoleTimes");
        BLLRead instance = null;
        ArrayList<BERoleTime> expResult = null;
        ArrayList<BERoleTime> result = instance.readAllRoleTimes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToRoleTime method, of class BLLRead.
     */
    @Test
    public void testAddToRoleTime() {
        System.out.println("addToRoleTime");
        BERoleTime roleTime = null;
        BLLRead instance = null;
        instance.addToRoleTime(roleTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromRoleTime method, of class BLLRead.
     */
    @Test
    public void testRemoveFromRoleTime() {
        System.out.println("removeFromRoleTime");
        BERoleTime roleTime = null;
        BLLRead instance = null;
        instance.removeFromRoleTime(roleTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readUsages method, of class BLLRead.
     */
    @Test
    public void testReadUsages() {
        System.out.println("readUsages");
        BLLRead instance = null;
        ArrayList<BEUsage> expResult = null;
        ArrayList<BEUsage> result = instance.readUsages();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToUsage method, of class BLLRead.
     */
    @Test
    public void testAddToUsage() {
        System.out.println("addToUsage");
        BEUsage usage = null;
        BLLRead instance = null;
        instance.addToUsage(usage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFromUsage method, of class BLLRead.
     */
    @Test
    public void testRemoveFromUsage() {
        System.out.println("removeFromUsage");
        BEUsage usage = null;
        BLLRead instance = null;
        instance.removeFromUsage(usage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readMaterials method, of class BLLRead.
     */
    @Test
    public void testReadMaterials() {
        System.out.println("readMaterials");
        BLLRead instance = null;
        ArrayList<BEMaterial> expResult = null;
        ArrayList<BEMaterial> result = instance.readMaterials();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readAlarms method, of class BLLRead.
     */
    @Test
    public void testReadAlarms() {
        System.out.println("readAlarms");
        BLLRead instance = null;
        ArrayList<BEAlarm> expResult = null;
        ArrayList<BEAlarm> result = instance.readAlarms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readIncidentDetails method, of class BLLRead.
     */
    @Test
    public void testReadIncidentDetails() {
        System.out.println("readIncidentDetails");
        BLLRead instance = null;
        ArrayList<BEIncidentDetails> expResult = null;
        ArrayList<BEIncidentDetails> result = instance.readIncidentDetails();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToIncidentDetails method, of class BLLRead.
     */
    @Test
    public void testAddToIncidentDetails() {
        System.out.println("addToIncidentDetails");
        BEIncidentDetails incidentdetail = null;
        BLLRead instance = null;
        instance.addToIncidentDetails(incidentdetail);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readSalary method, of class BLLRead.
     */
    @Test
    public void testReadSalary() {
        System.out.println("readSalary");
        BLLRead instance = null;
        ArrayList<BESalary> expResult = null;
        ArrayList<BESalary> result = instance.readSalary();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
