/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEAlarm;
import BE.BEError;
import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BEMaterial;
import BE.BERole;
import BE.BERoleTime;
import BE.BEUsage;
import BE.BEVehicle;
import BE.BEZipcode;
import DAL.Intefaces.IDALCreate;
import DAL.Intefaces.IDALRead;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zoracka
 */
public class BLLCreateTest {

    static BLLCreate m_bllCreate;
    static IDALCreate m_dalCreate;
    static BLLRead m_bllRead;
    static IDALRead m_dalRead;
    static BEError m_beerror;
    static ArrayList<BEIncident> arrayIncidents;
    static ArrayList<BERoleTime> arrayRoleTime;

    public BLLCreateTest() {

    }

    @BeforeClass
    public static void setUpClass() {
        arrayIncidents = new ArrayList<>();
        arrayRoleTime = new ArrayList<>();
        m_bllCreate = BLLCreate.getInstance();
        m_dalCreate = new IDALCreate() {

            @Override
            public void createErrorReport(BEError errorReport) throws SQLException {

            }

            @Override
            public void createIncident(BEIncident incident) throws SQLException {
                if (incident.getM_date() == null) {
                    throw new SQLException();
                }
                if (incident.getM_incidentName() == null) {
                    throw new SQLException();
                }

                if (incident.getM_time() == null) {
                    throw new SQLException();
                }
                arrayIncidents.add(incident);
            }

            @Override
            public void createInitialIncidentDetails(BEIncidentDetails initialIncidentDetails) throws SQLException {

            }

            @Override
            public void createRoleTime(BERoleTime roletime) throws SQLException {
                if (roletime.getM_fireman() == null) {
                    throw new SQLException();
                }
                if (roletime.getM_incident() == null) {
                    throw new SQLException();
                }
                if (roletime.getM_role() == null) {
                    throw new SQLException();
                }

                arrayRoleTime.add(roletime);
            }

            @Override
            public void createUsage(BEUsage usage) throws SQLException {

            }
        };
        m_bllCreate.setDAL(m_dalCreate);
        m_bllRead = BLLRead.getInstance();
        m_dalRead = new IDALRead() {

            @Override
            public ArrayList<BEAlarm> readAlarms() throws SQLException {
                return null;
            }

            @Override
            public ArrayList<BEFireman> readFiremen() throws SQLException {
                return null;
            }

            @Override
            public ArrayList<BEIncidentDetails> readIncidentDetails() throws SQLException {
                return null;
            }

            @Override
            public ArrayList<BEIncidentType> readIncidentTypes() throws SQLException {
                return null;
            }

            @Override
            public ArrayList<BEIncident> readIncidents() throws SQLException {
                return null;
            }

            @Override
            public ArrayList<BEMaterial> readMaterial() throws SQLException {
                return null;
            }

            @Override
            public ArrayList<BERoleTime> readRoleTime() throws SQLException {
                return new ArrayList<>();
            }

            @Override
            public ArrayList<BERole> readRoles() throws SQLException {
                ArrayList<BERole> res = new ArrayList<>();
                BERole role = new BERole(1, "Fireman");
                res.add(role);
                role = new BERole(2, "Stationsvagt");
                res.add(role);
                return res;
            }

            @Override
            public ArrayList<BEUsage> readUsage() throws SQLException {
                return null;
            }

            @Override
            public ArrayList<BEVehicle> readVehicles() throws SQLException {
                return null;
            }

            @Override
            public ArrayList<BEZipcode> readZipcodes() throws SQLException {
                return null;
            }
        };
        m_bllRead.setDAL(m_dalRead);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Tests if the DAL adds the incident to the array
     */
    //@Test
    public void testCreateIncident() {
        System.out.println("createIncident");
        BEIncidentType incidentType = new BEIncidentType(1, "indsats");
        BEIncident incident = new BEIncident("TestCreateIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, false);
        boolean expResult = true;
        boolean result = m_bllCreate.createIncident(incident);
        assertEquals(expResult, result);
        arrayIncidents.clear();

    }

    /**
     * Tests if the BEIncident is null
     */
    //@Test
    public void testCreateIncidentNull() {
        System.out.println("createIncidentNull");
        BEIncident incident = null;
        boolean expResult = false;
        boolean result = m_bllCreate.createIncident(incident);
        assertEquals(expResult, result);
        arrayIncidents.clear();
    }

    /**
     * Tests when a SQLException is thrown
     */
    //@Test
    public void testCreateIncidentSqlException() {
        System.out.println("createIncidentSqlException");
        BEIncident incident = new BEIncident(null, null, null, null, false);
        boolean expResult = false;
        boolean result = m_bllCreate.createIncident(incident);
        assertEquals(expResult, result);
        arrayIncidents.clear();
    }

    /**
     * Tests if the DAL adds the roleTime to the array
     */
  //  @Test
    public void testCreateRoleOnIncident() {
        System.out.println("createRoleOnIncident");
        int roleNumber = 1;

        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "Ford", "Mundeo", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        zip = new BEZipcode(6670, "Holsted");
        fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        vehicle = new BEVehicle(1000, "AA12345", "Ford", "Mundeo", "Sprøjte");
        incidentType = new BEIncidentType(1, "Indsats");
        incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);

        for (int i = 0; i < roleTimes.size(); i++) {
            BERoleTime expResult = roleTimes.get(i);
            BERoleTime result = arrayRoleTime.get(i);
            assertEquals(expResult, result);
        }
        arrayRoleTime.clear();
    }

    @Test
    public void testCreateRoleOnIncidentSqlException() {
        System.out.println("createRoleOnIncidentSqlException");
        int roleNumber = 1;
        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BERoleTime roletime = new BERoleTime(null, null, true, null, null, 1);
        roleTimes.add(roletime);
        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
        BERole expResult = null;
        BERole result = roletime.getM_role();
        assertEquals(expResult, result);
        arrayRoleTime.clear();
    }

    /**
     * Test if the role ID is less that 1
     */
    @Test
    public void testCreateRoleOnIncidentRoleLessThanOne() {
        int roleNumber = 0;
        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "Ford", "Mundeo", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
        BERole expResult = null;
        BERole result = roletime.getM_role();
        assertEquals(expResult, result);
        arrayRoleTime.clear();
    }

    /**
     * Test if the role ID is more that 4
     */
    @Test
    public void testCreateRoleOnIncidentRoleMoreThanFour() {
        int roleNumber = 5;
        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "Ford", "Mundeo", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
        BERole expResult = null;
        BERole result = roletime.getM_role();
        assertEquals(expResult, result);
        arrayRoleTime.clear();
    }

    /**
     * Tests if the array contains a BERoleTime of the value null
     */
    @Test
    public void testCreateRoleOnIncidentArrayContainsNull() {
        int roleNumber = 1;
        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "Ford", "Mundeo", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);
        roletime = null;
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
        BERole expResult = null;
        BERole result = roleTimes.get(0).getM_role();
        assertEquals(expResult, result);
        arrayRoleTime.clear();
    }

    /**
     * Test if a single roleTime changes it's BERole object accordingly.
     */
    @Test
    public void testCreateRoleOnIncidentSingle() {
        System.out.println("createRoleOnIncident");
        int roleNumber = 1;

        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "Ford", "Mundeo", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
          for (int i = 0; i < arrayRoleTime.size(); i++) {
            int result = arrayRoleTime.get(0).getM_role().getM_id();
            assertEquals(roleNumber, result);
        }
        arrayRoleTime.clear();
    }

    /**
     * Test if multiple roleTime changes it's BERole object accordingly.
     */
    @Test
    public void testCreateRoleOnIncidentMultiple() {
        System.out.println("createRoleOnIncidentMultiple");
        int roleNumber = 1;

        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "Ford", "Mundeo", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        zip = new BEZipcode(6670, "Holsted");
        fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        vehicle = new BEVehicle(1000, "AA12345", "Ford", "Mundeo", "Sprøjte");
        incidentType = new BEIncidentType(1, "Indsats");
        incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
        for (int i = 0; i < arrayRoleTime.size(); i++) {
            int result = arrayRoleTime.get(0).getM_role().getM_id();
            assertEquals(roleNumber, result);
        }
       
        arrayRoleTime.clear();
    }

}
