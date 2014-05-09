package BLL;

import BE.BEAlarm;
import BE.BEEmergency;
import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BEIncidentVehicle;
import BE.BEMaterial;
import BE.BERole;
import BE.BERoleTime;
import BE.BEUsage;
import BE.BEVehicle;
import DAL.DALRead;
import GUI.MessageDialog;
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
    ArrayList<BEIncidentVehicle> incidentVehicles;
    ArrayList<BEEmergency> emergencies;
    ArrayList<BEAlarm> alarms;
    ArrayList<BEIncidentDetails> incidentDetails;

    private BLLRead() {
    }

    public static BLLRead getInstance() {
        if (m_instance == null) {
            m_instance = new BLLRead();
        }
        return m_instance;
    }

    /**
     * @return ArrayList of Firemen
     */
    public ArrayList<BEFireman> readAllFiremen() {
        if (firemen == null) {
            try {
                firemen = DALRead.getInstance().readFiremen();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return firemen;

    }

    /**
     * @return ArrayList of Vehicles
     */
    public ArrayList<BEVehicle> readAllVehicles() {
        if (vehicles == null) {
            try {
                vehicles = DALRead.getInstance().readVehicles();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return vehicles;
    }

    /**
     * @return ArrayList of IncidentTypes
     */
    public ArrayList<BEIncidentType> readAllIncidentTypes() {
        if (incidentTypes == null) {
            try {
                incidentTypes = DALRead.getInstance().readIncidentTypes();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return incidentTypes;
    }

    /**
     * @return ArrayList of Incidents
     */
    public ArrayList<BEIncident> readAllIncidents() {
        if (incidents == null) {
            try {
                incidents = DALRead.getInstance().readIncidents();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return incidents;
    }

    public void addToIncident(BEIncident incident) {
        incidents.add(incident);
    }

    /**
     *
     * @return ArrayList of Roles
     */
    public ArrayList<BERole> readAllRoles() {
        if (roles == null) {
            try {
                roles = DALRead.getInstance().readRoles();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return roles;
    }

    /**
     *
     * @return ArrayList of RoleTimes
     */
    public ArrayList<BERoleTime> readAllRoleTimes() {
        if (roletimes == null) {
            try {
                roletimes = DALRead.getInstance().readRoleTime();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return roletimes;
    }

    public void addToRoleTime(BERoleTime roleTime) {
        roletimes.add(roleTime);
    }

    /**
     *
     * @return ArrayList of Usage
     */
    public ArrayList<BEUsage> readUsages() {
        if (usages == null) {
            try {
                usages = DALRead.getInstance().readUsage();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return usages;
    }

    public void addToUsage(BEUsage usage) {
        usages.add(usage);
    }

    /**
     *
     * @return ArrayList of Materials
     */
    public ArrayList<BEMaterial> readMaterials() {
        if (materials == null) {
            try {
                materials = DALRead.getInstance().readMaterial();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return materials;
    }

    /**
     *
     * @return ArrayList of Emergencies
     */
    public ArrayList<BEEmergency> readEmergencies() {
        if (emergencies == null) {
            try {
                emergencies = DALRead.getInstance().readEmergencies();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return emergencies;
    }

    /**
     *
     * @return ArrayList of Alarms
     */
    public ArrayList<BEAlarm> readAlarms() {
        if (alarms == null) {
            try {
                alarms = DALRead.getInstance().readAlarms();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return alarms;

    }

    /**
     *
     * @return ArrayList of IncidentDetails
     */
    public ArrayList<BEIncidentDetails> readIncidentDetails() {
        if (incidentDetails == null) {
            try {
                incidentDetails = DALRead.getInstance().readIncidentDetails();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return incidentDetails;
    }
    
    public void addToIncidentDetails(BEIncidentDetails incidentdetail){
        incidentDetails.add(incidentdetail);
    }
}
