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
import DAL.Intefaces.IDALRead;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLLRead {

    private static BLLRead m_instance;
    IDALRead dalRead;
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

    public void setDAL(IDALRead d){
        dalRead = d;
    }
    /**
     * Invokes the method in DAL that reads Firemen
     *
     * @return ArrayList of Firemen
     */
    public ArrayList<BEFireman> readFiremen() {
        if (firemen == null) {
            try {
                firemen = dalRead.readFiremen();
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
    public ArrayList<BEVehicle> readVehicles() {
        if (vehicles == null) {
            try {
                vehicles = dalRead.readVehicles();
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
    public ArrayList<BEIncidentType> readIncidentTypes() {
        if (incidentTypes == null) {
            try {
                incidentTypes = dalRead.readIncidentTypes();
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
    public ArrayList<BEIncident> readIncidents() {
        if (incidents == null) {
            try {
                incidents = dalRead.readIncidents();
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
    public ArrayList<BERole> readRoles() {
        if (roles == null) {
            try {
                roles = dalRead.readRoles();
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
    public ArrayList<BERoleTime> readRoleTimes() {
        if (roletimes == null) {
            try {
                roletimes = dalRead.readRoleTime();
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
        if(roletimes == null){
            readRoleTimes();
        }
        roletimes.add(roleTime);
    }

    /**
     * Removes the given roleTime from the arrayList
     *
     * @param roleTime
     */
    public void removeFromRoleTime(BERoleTime roleTime) {
        roletimes.remove(roleTime);
    }

    /**
     * Invokes the method in DAL that reads Usage
     *
     * @return ArrayList of Usage
     */
    public ArrayList<BEUsage> readUsages() {
        if (usages == null) {
            try {
                usages = dalRead.readUsage();
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
                materials = dalRead.readMaterial();
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
                alarms = dalRead.readAlarms();
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
                incidentDetails = dalRead.readIncidentDetails();
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
