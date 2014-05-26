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
        arrayIncidents.clear();
        arrayRoleTime.clear();
    }

    /**
     * Test that the call returns true, with a given BEIncident.
     */
    @Test
    public void testCreateIncident() {
        System.out.println("create-Incident");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("TestCreateIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, false);
        boolean expResult = true;
        boolean result = m_bllCreate.createIncident(incident);
        assertEquals(expResult, result);
        arrayIncidents.clear();
    }

    /**
     * Test that the call returns false, if the given BEIncident is null.
     */
    @Test
    public void testCreateIncidentIfNull() {
        System.out.println("create-Incident-If-Null");
        BEIncident incident = null;
        boolean expResult = false;
        boolean result = m_bllCreate.createIncident(incident);
        assertEquals(expResult, result);
        arrayIncidents.clear();
    }

    /**
     * Test that the call returns false, if a SQLException is catched.
     */
    @Test
    public void testCreateIncidentWithSqlException() {
        System.out.println("create-Incident-With-SqlException");
        BEIncident incident = new BEIncident(null, null, null, null, false);
        boolean expResult = false;
        boolean result = m_bllCreate.createIncident(incident);
        assertEquals(expResult, result);
        arrayIncidents.clear();
    }

    /**
     * Tests that a given array of BERoleTime is added to the array.
     */
    @Test
    public void testCreateRoleOnIncident() {
        System.out.println("create-Role-On-Incident");
        int roleNumber = 1;

        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        zip = new BEZipcode(6670, "Holsted");
        fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
        incidentType = new BEIncidentType(1, "Indsats");
        incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);

        for (int i = 0; i < arrayRoleTime.size(); i++) {
            BERoleTime expResult = roleTimes.get(i);
            BERoleTime result = arrayRoleTime.get(i);
            assertEquals(expResult, result);
        }
        arrayRoleTime.clear();
    }

    /**
     * Test that the call returns, if a SQLExeption is catched.
     */
    @Test
    public void testCreateRoleOnIncidentWithSqlException() {
        System.out.println("create-Role-On-Incident-With-SqlException");
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
     * Test that the call returns, if the given roleNumber is less than 1.
     */
    @Test
    public void testCreateRoleOnIncidentIfRoleLessThanOne() {
        System.out.println("create-Role-On-Incident-If-Role-Less-Than-One");
        int roleNumber = 0;
        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
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
     * Test that the call returns, if the given roleNumber is more than 4.
     */
    @Test
    public void testCreateRoleOnIncidentIfRoleMoreThanFour() {
        System.out.println("create-Role-On-Incident-If-Role-More-Than-Four");
        int roleNumber = 5;
        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
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
     * Test that the call returns, if the given array of BERoleTime contains
     * null.
     */
    @Test
    public void testCreateRoleOnIncidentIfArrayContainsNull() {
        System.out.println("create-Role-On-Incident-If-Array-Contains-Null");
        int roleNumber = 1;
        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
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
     * Test that a single BERoleTime changes it's BERole object.
     */
    @Test
    public void testCreateRoleOnIncidentSingle() {
        System.out.println("create-Role-On-Incident-Single");
        int roleNumber = 1;

        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
        for (int i = 0; i < arrayRoleTime.size(); i++) {
            int result = arrayRoleTime.get(i).getM_role().getM_id();
            assertEquals(roleNumber, result);
        }
        arrayRoleTime.clear();
    }

    /**
     * Test if multiple BERoleTime changes it's BERole object.
     */
    @Test
    public void testCreateRoleOnIncidentMultiple() {
        System.out.println("create-Role-On-Incident-Multiple");
        int roleNumber = 1;

        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEZipcode zip = new BEZipcode(6670, "Holsted");
        BEFireman fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
        BEIncidentType incidentType = new BEIncidentType(1, "Indsats");
        BEIncident incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        zip = new BEZipcode(6670, "Holsted");
        fireman = new BEFireman(1, Date.valueOf("2014-01-01"), "Andreas", "Mørch", "Holsted", zip, 1234, 5678, true, null);
        vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
        incidentType = new BEIncidentType(1, "Indsats");
        incident = new BEIncident("createRoleOnIncident", Date.valueOf("2014-01-01"), Time.valueOf("15:00:00"), incidentType, true);
        roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);

        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
        for (int i = 0; i < arrayRoleTime.size(); i++) {
            int result = arrayRoleTime.get(i).getM_role().getM_id();
            assertEquals(roleNumber, result);
        }

        arrayRoleTime.clear();
    }

    /**
     * Tests if the BERoleTime's BERole changes back if there is caught an exception
     */
    @Test
    public void testCreateRoleOnIncidentSqlException() {
        System.out.println("create-Role-On-Incident-Sql-Exception");
        int roleNumber = 1;
        ArrayList<BERoleTime> roleTimes = new ArrayList<>();
        BEFireman fireman = null;
        BEVehicle vehicle = new BEVehicle(1000, "AA12345", "MAN", "27", "Sprøjte");
        BEIncident incident = null;
        BERoleTime roletime = new BERoleTime(incident, fireman, true, null, vehicle, 6);
        roleTimes.add(roletime);
        m_bllCreate.createRoleOnIncident(roleTimes, roleNumber);
       
        BERole expResult = null;
        BERole result = null;
        assertEquals(expResult, result);

        arrayRoleTime.clear();
    }

}
