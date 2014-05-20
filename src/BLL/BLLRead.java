package BLL;

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
import DAL.DALRead;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLLRead {

    private static BLLRead m_instance;
    ArrayList<BEIncidentType> incidentTypes;
    ArrayList<BEIncident> incidents;
    ArrayList<BEIncident> incompleteIncidents;
    ArrayList<BEFireman> firemen;
    ArrayList<BEVehicle> vehicles;
    ArrayList<BERole> roles;
    ArrayList<BERoleTime> roletimes;
    ArrayList<BEUsage> usages;
    ArrayList<BEMaterial> materials;
    ArrayList<BEAlarm> alarms;
    ArrayList<BEIncidentDetails> incidentDetails;

    private BLLRead() {
    }

    /**
     * Creates or returns the current instance of BLLRead
     *
     * @return current m_instance of BLLRead
     */
    public static BLLRead getInstance() {
        if (m_instance == null) {
            m_instance = new BLLRead();
        }
        return m_instance;
    }

    /**
     * Invokes the method in DAL that reads Firemen
     *
     * @return ArrayList of Firemen
     */
    public ArrayList<BEFireman> readAllFiremen() {
        if (firemen == null) {
            try {
                firemen = DALRead.getInstance().readFiremen();
            } catch (SQLException ex) {
                BLLError.getInstance().readFiremanError();
                return null;
            }
        }
        return firemen;

    }

    /**
     * Invokes the method in DAL that reads Vehicles
     *
     * @return ArrayList of Vehicles
     */
    public ArrayList<BEVehicle> readAllVehicles() {
        if (vehicles == null) {
            try {
                vehicles = DALRead.getInstance().readVehicles();
            } catch (SQLException ex) {
                BLLError.getInstance().readVehicleError();
                return null;
            }
        }
        return vehicles;
    }

    /**
     * Invokes the method in DAL that reads IncidentTypes
     *
     * @return ArrayList of IncidentTypes
     */
    public ArrayList<BEIncidentType> readAllIncidentTypes() {
        if (incidentTypes == null) {
            try {
                incidentTypes = DALRead.getInstance().readIncidentTypes();
            } catch (SQLException ex) {
                BLLError.getInstance().readIncidentTypesError();
                return null;
            }
        }
        return incidentTypes;
    }

    /**
     * Invokes the method in DAL that reads Incidents
     *
     * @return ArrayList of Incidents
     */
    public ArrayList<BEIncident> readAllIncidents() {
        if (incidents == null) {
            try {
                incidents = DALRead.getInstance().readIncidents();
            } catch (SQLException ex) {
                BLLError.getInstance().readIncidentError();
                return null;
            }
        }
        return incidents;
    }

    /**
     * Adds the given incident to the arrayList
     *
     * @param incident
     */
    public void addToIncident(BEIncident incident) {
        incidents.add(incident);
    }

    /**
     * Invokes the method in DAL that reads Roles
     *
     * @return ArrayList of Roles
     */
    public ArrayList<BERole> readAllRoles() {
        if (roles == null) {
            try {
                roles = DALRead.getInstance().readRoles();
            } catch (SQLException ex) {
                BLLError.getInstance().readRoleError();
                return null;
            }
        }
        return roles;
    }

    /**
     * Invokes the method in DAL that reads RoleTimes
     *
     * @return ArrayList of RoleTimes
     */
    public ArrayList<BERoleTime> readAllRoleTimes() {
        if (roletimes == null) {
            try {
                roletimes = DALRead.getInstance().readRoleTime();
            } catch (SQLException ex) {
                BLLError.getInstance().readRoleTimeError();
                return null;
            }
        }
        return roletimes;
    }

    /**
     * Adds the given roleTime to the arrayList
     *
     * @param roleTime
     */
    public void addToRoleTime(BERoleTime roleTime) {
        roletimes.add(roleTime);
    }

    /**
     * Removes the given roleTime from the arrayList
     *
     * @param roleTime
     */
    public void removeFromRoleTime(BERoleTime roleTime) {
        roletimes.remove(roleTime);
        //notifyObservers();
    }

    /**
     * Invokes the method in DAL that reads Usage
     *
     * @return ArrayList of Usage
     */
    public ArrayList<BEUsage> readUsages() {
        if (usages == null) {
            try {
                usages = DALRead.getInstance().readUsage();
            } catch (SQLException ex) {
                BLLError.getInstance().readUsageError();
                return null;
            }
        }
        return usages;
    }

    /**
     * Adds a new BEUsage to the arrayList
     *
     * @param usage
     */
    public void addToUsage(BEUsage usage) {
        usages.add(usage);
    }

    /**
     * Removes the given BEUsage from the arrayList
     *
     * @param usage
     */
    public void removeFromUsage(BEUsage usage) {
        usages.remove(usage);
    }

    /**
     * Invokes the method in DAL that reads Materials
     *
     * @return ArrayList of Materials
     */
    public ArrayList<BEMaterial> readMaterials() {
        if (materials == null) {
            try {
                materials = DALRead.getInstance().readMaterial();
            } catch (SQLException ex) {
                BLLError.getInstance().readMaterialError();
                return null;
            }
        }
        return materials;
    }

    /**
     * Read Alarms from the DB
     *
     * @return ArrayList of Alarms
     */
    public ArrayList<BEAlarm> readAlarms() {
        if (alarms == null) {
            try {
                alarms = DALRead.getInstance().readAlarms();
            } catch (SQLException ex) {
                BLLError.getInstance().readAlarmError();
                return null;
            }
        }
        return alarms;

    }

    /**
     * Invokes the method in DAL that reads IncidentDetails
     *
     * @return ArrayList of IncidentDetails
     */
    public ArrayList<BEIncidentDetails> readIncidentDetails() {
        if (incidentDetails == null) {
            try {
                incidentDetails = DALRead.getInstance().readIncidentDetails();
            } catch (SQLException ex) {
                BLLError.getInstance().readIncidentDetailsError();
                return null;
            }
        }
        return incidentDetails;
    }

    public void addToIncidentDetails(BEIncidentDetails incidentdetail) {
        incidentDetails.add(incidentdetail);
    }

}
