/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentType;
import BE.BEVehicle;
import DAL.DALCreate;
import DAL.DALRead;
import DAL.DALUpdate;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
    
    public void createIncident(BEIncident be){
        try {
            DALCreate.getInstance().createIncident(be);
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateFireman(BEIncident be){
        try {
            DALUpdate.getInstance().updateIncident(be);
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
