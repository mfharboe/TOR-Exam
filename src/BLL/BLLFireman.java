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

  
    
    private void isSeatsAvailable(BERoleTime roleTime){
        
    }

 

    /**
     * Sets the RoleTime for an Incident
     *
     * @param incident
     * @return ArrayList of RoleTime
     */
    public ArrayList<BERoleTime> incidentToRoleTime(BEIncident incident) {
        ArrayList<BERoleTime> beroletime = new ArrayList<>();

        for (BERoleTime be : BLLRead.getInstance().readAllRoleTimes()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                beroletime.add(be);
            }
        }
        return beroletime;
    }

}
