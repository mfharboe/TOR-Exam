package DAL.Intefaces;

import BE.BEAlarm;
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
import java.sql.SQLException;
import java.util.ArrayList;

public interface IDALRead {

    /**
     * Creates an ArrayList of Alarms
     *
     * @return ArrayList of Alarms
     * @throws SQLException
     */
    ArrayList<BEAlarm> readAlarms() throws SQLException;

    /**
     * Creates an ArrayList of Firemen
     *
     * @return ArrayList of Firemen
     * @throws SQLException
     */
    ArrayList<BEFireman> readFiremen() throws SQLException;

    /**
     * Creates an ArrayList of IncidentDetails
     *
     * @return ArrayList of IncidentDetails
     * @throws SQLException
     */
    ArrayList<BEIncidentDetails> readIncidentDetails() throws SQLException;

    /**
     * Creates an ArrayList of IncidentTypes
     *
     * @return ArrayList of Incident Types
     * @throws SQLException
     */
    ArrayList<BEIncidentType> readIncidentTypes() throws SQLException;

    /**
     * Creates an ArrayList of Incidents
     *
     * @return ArrayList of Incidents
     * @throws SQLException
     */
    ArrayList<BEIncident> readIncidents() throws SQLException;

    /**
     * Creates an ArrayList of Material
     *
     * @return ArrayList of Material
     * @throws SQLException
     */
    ArrayList<BEMaterial> readMaterial() throws SQLException;

    /**
     * Creates an ArrayList of RoleTimes
     *
     * @return ArrayList of RoleTimes
     * @throws SQLException
     */
    ArrayList<BERoleTime> readRoleTime() throws SQLException;

    /**
     * Creates an ArrayList of Roles
     *
     * @return ArrayList of Roles
     * @throws SQLException
     */
    ArrayList<BERole> readRoles() throws SQLException;

    /**
     * Creates an ArrayList of Usage
     *
     * @return ArrayList of Usage
     * @throws SQLException
     */
    ArrayList<BEUsage> readUsage() throws SQLException;

    /**
     * Creates an ArrayList of Vehicles
     *
     * @return an ArrayList of Vehicles
     * @throws SQLException
     */
    ArrayList<BEVehicle> readVehicles() throws SQLException;

    /**
     * Creates an ArrayList of Zipcodes
     *
     * @return ArrayList of Zipcodes
     * @throws SQLException
     */
    ArrayList<BEZipcode> readZipcodes() throws SQLException;
    
}
