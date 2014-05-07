package BLL;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentType;
import BE.BERole;
import BE.BERoleTime;
import BE.BEVehicle;
import DAL.DALCreate;
import DAL.DALRead;
import DAL.DALUpdate;
import GUI.MessageDialog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLLFireman {

    private static BLLFireman m_instance;
    ArrayList<BEIncidentType> incidentTypes;
    ArrayList<BEIncident> incidents;
    ArrayList<BEIncident> incompleteIncidents;
    ArrayList<BEFireman> firemen;
    ArrayList<BEVehicle> vehicles;
    ArrayList<BERole> roles;
    ArrayList<BERoleTime> roletimes;

    private BLLFireman() {
    }

    /**
     * @return m_instance of BLLFireman
     */
    public static BLLFireman getInstance() {
        if (m_instance == null) {
            m_instance = new BLLFireman();
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

    /**
     * Creates a new Incident
     *
     * @param incident
     */
    public void createIncident(BEIncident incident) {
        try {
            DALCreate.getInstance().createIncident(incident);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
        }
    }

    /**
     * Creates a new BM on an Incident and adds it to the currenct Array
     *
     * @param roleTime
     */
    public void createBMOnIncident(BERoleTime roleTime) {

        for (BERole role : readAllRoles()) {
            if (role.isM_isFireman()) {
                try {
                    roleTime.setM_role(role);
                    DALCreate.getInstance().createRoleTime(roleTime);
                } catch (SQLException ex) {
                    //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().functionDialog(); //MÅ IKKE VÆRE HER
                    break;
                }
                roletimes.add(roleTime);
            }
        }
    }

    /**
     * Creates a new CH on an Incident and adds it to the currenct Array
     *
     * @param roleTime
     */
    public void createCHOnIncident(BERoleTime roleTime) {
        for (BERole role : readAllRoles()) {
            if (role.isM_isDriver()) {
                try {
                    roleTime.setM_role(role);
                    DALCreate.getInstance().createRoleTime(roleTime);
                } catch (SQLException ex) {
                    //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().functionDialog(); //MÅ IKKE VÆRE HER
                    break;
                }
                roletimes.add(roleTime);
            }
        }
    }

    /**
     * Creates a new ST on an Incident and adds it to the currenct Array
     *
     * @param roleTime
     */
    public void createSTOnIncident(BERoleTime roleTime) {
        for (BERole role : readAllRoles()) {
            if (role.isM_isStation()) {
                try {
                    roleTime.setM_role(role);
                    DALCreate.getInstance().createRoleTime(roleTime);
                } catch (SQLException ex) {
                    //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().stationDialog(); //MÅ IKKE VÆRE HER
                    break;
                }
                roletimes.add(roleTime);
            }
        }
    }

    /**
     * Creates an new HL on an Incident and adds it to the currenct Array
     *
     * @param roleTime
     */
    public void createHLOnIncident(BERoleTime roleTime) {
        for (BERole role : readAllRoles()) {
            if (role.isM_isLeader()) {
                try {
                    roleTime.setM_role(role);
                    DALCreate.getInstance().createRoleTime(roleTime);
                } catch (SQLException ex) {
                   //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().functionDialog(); //MÅ IKKE VÆRE HER
                    break;
                }
                roletimes.add(roleTime);
            }
        }
    }

    /**
     * Updates an Incident
     *
     * @param incident
     */
    public void updateIncident(BEIncident incident) {
        try {
            DALUpdate.getInstance().updateIncident(incident);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        MessageDialog.getInstance().incidentUpdate(); //MÅ IKKE VÆRE HER
    }

    /**
     * Sets the RoleTime for an Incident
     *
     * @param incident
     * @return ArrayList of RoleTime
     */
    public ArrayList<BERoleTime> incidentToRoleTime(BEIncident incident) {
        ArrayList<BERoleTime> beroletime = new ArrayList<>();

        for (BERoleTime be : readAllRoleTimes()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                beroletime.add(be);
            }
        }

        return beroletime;
    }

}
