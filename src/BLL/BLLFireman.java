/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentDetails;
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
     * @return current instance of BLLFireman
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
                Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return incidents;
    }

    /**
     * @return ArrayList of incomplete Incidents
     */
    public ArrayList<BEIncident> readIncompleteIncidents() {
        if (incompleteIncidents == null) {
            incompleteIncidents = new ArrayList<>();
            for (BEIncident c : readAllIncidents()) {
                if (!c.isM_isDone()) {
                    incompleteIncidents.add(c);
                }
            }
        }
        return incompleteIncidents;
    }

    public ArrayList<BERole> readRoles() {
        if (roles == null) {
            try {
                roles = DALRead.getInstance().readRoles();
            } catch (SQLException ex) {
                Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    public ArrayList<BERoleTime> readRoleTimes() {
        if (roletimes == null) {
            try {
                roletimes = DALRead.getInstance().readRoleTime();
            } catch (SQLException ex) {
                Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roletimes;
    }
    
    

    public void createIncident(BEIncident be) {
        try {
            DALCreate.getInstance().createIncident(be);
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createBMOnIncident(BERoleTime be) {

        for (BERole role : readRoles()) {
            if (role.isM_isFireman()) {
                try {
                    be.setM_role(role);
                    DALCreate.getInstance().createRoleTime(be);
                } catch (SQLException ex) {
                    MessageDialog.getInstance().functionDialog(); //MÅ IKKE VÆRE HER
                    break;
                }
                roletimes.add(be);
            }
        }
    }

    public void createCHOnIncident(BERoleTime be) {
        for (BERole role : readRoles()) {
            if (role.isM_isDriver()) {
                try {
                    be.setM_role(role);
                    DALCreate.getInstance().createRoleTime(be);
                } catch (SQLException ex) {
                    break;
                }
                roletimes.add(be);
            }
        }
    }

    public void createSTOnIncident(BERoleTime be) {
        for (BERole role : readRoles()) {
            if (role.isM_isStation()) {
                try {
                    be.setM_role(role);
                    DALCreate.getInstance().createRoleTime(be);
                } catch (SQLException ex) {
                    MessageDialog.getInstance().stationDialog(); //MÅ IKKE VÆRE HER
                    break;
                }
                roletimes.add(be);
            }
        }
    }

    public void createHLOnIncident(BERoleTime be) {
        for (BERole role : readRoles()) {
            if (role.isM_isLeader()) {
                try {
                    be.setM_role(role);
                    DALCreate.getInstance().createRoleTime(be);
                } catch (SQLException ex) {
                    break;
                }
                roletimes.add(be);
            }
        }
    }
    
    public void createInitialIncidentDetails(BEIncident be){
        try {
            DALCreate.getInstance().createInitialIncidentDetails(be);
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFireman(BEIncident be) {
        try {
            DALUpdate.getInstance().updateIncident(be);
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<BERoleTime> incidentToRoleTime(BEIncident beincident) {
        ArrayList<BERoleTime> beroletime = new ArrayList<>();

        for (BERoleTime be : readRoleTimes()) {
            if (be.getM_incident().getM_id() == beincident.getM_id()) {
                beroletime.add(be);
            }
        }

        return beroletime;
    }
    
  
}
