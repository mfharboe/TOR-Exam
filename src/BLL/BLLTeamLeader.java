package BLL;

import BE.BEABA;
import BE.BEAlarm;
import BE.BEEmergency;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentVehicle;
import BE.BEMaterial;
import BE.BEUsage;
import DAL.DALCreate;
import DAL.DALRead;
import DAL.DALUpdate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLLTeamLeader {

    private static BLLTeamLeader m_instance;
    ArrayList<BEUsage> usages;
    ArrayList<BEMaterial> materials;
    ArrayList<BEIncidentVehicle> incidentVehicles;
    ArrayList<BEEmergency> emergencies;
    ArrayList<BEAlarm> alarms;
    ArrayList<BEIncidentDetails> incidentDetails;

    private BLLTeamLeader() {

    }

    public static BLLTeamLeader getInstance() {
        if (m_instance == null) {
            m_instance = new BLLTeamLeader();
        }
        return m_instance;
    }

    public ArrayList<BEUsage> readUsage() {
        if (usages == null) {
            try {
                usages = DALRead.getInstance().readUsage();
            } catch (SQLException ex) {
                Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usages;
    }

    public ArrayList<BEMaterial> readMaterials() {
        if (materials == null) {
            try {
                materials = DALRead.getInstance().readMaterial();
            } catch (SQLException ex) {
                Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return materials;
    }

    public ArrayList<BEIncidentVehicle> readIncidentVehicles() {
        if (incidentVehicles == null) {
            try {
                incidentVehicles = DALRead.getInstance().readIncidentVehicle();
            } catch (SQLException ex) {
                Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return incidentVehicles;
    }

    public ArrayList<BEEmergency> readEmergencies() {
        if (emergencies == null) {
            try {
                emergencies = DALRead.getInstance().readEmergencies();
            } catch (SQLException ex) {
                Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return emergencies;
    }

    public ArrayList<BEAlarm> readAlarms() {
        if (alarms == null) {
            try {
                alarms = DALRead.getInstance().readAlarms();
            } catch (SQLException ex) {
                Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return alarms;

    }

    public ArrayList<BEIncidentDetails> readIncidentDetails() {
        if (incidentDetails == null) {
            try {
                incidentDetails = DALRead.getInstance().readIncidentDetails();
            } catch (SQLException ex) {
                Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return incidentDetails;
    }

    public void createIncidentVehicle(BEIncidentVehicle be) {
        try {
            DALCreate.getInstance().createIncidentVehicle(be);
        } catch (SQLException ex) {
            return;
        }
        readIncidentVehicles();
        incidentVehicles.add(be);
    }

    public void createUsage(BEUsage be) {
        try {
            DALCreate.getInstance().createUsage(be);
        } catch (SQLException ex) {
            return;
        }
        usages.add(be);
    }
    
    public void updateIncidentDetails(BEIncidentDetails be){
        try {
            DALUpdate.getInstance().updateIncidentDetails(be);
        } catch (SQLException ex) {
            Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
//    public void updateABA(BEABA be){
//        try {
//            DALUpdate.getInstance().updateABA(be);
//        } catch (SQLException ex) {
//            Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public ArrayList<BEIncidentVehicle> incidentToIncidentVehicle(BEIncident beincident) {
        ArrayList<BEIncidentVehicle> beincidentvehicle = new ArrayList<>();
        for (BEIncidentVehicle be : readIncidentVehicles()) {
            if (be.getM_incident().getM_id() == beincident.getM_id()) {
                beincidentvehicle.add(be);
            }
        }
        return beincidentvehicle;
    }

    public ArrayList<BEUsage> incidentToUsage(BEIncident beincident) {
        ArrayList<BEUsage> beusage = new ArrayList<>();
        for (BEUsage be : readUsage()) {
            if (be.getM_incident().getM_id() == beincident.getM_id()) {
                beusage.add(be);
            }
        }
        return beusage;
    }
    
    public BEIncidentDetails incidentToIncidentDetails(BEIncident beincident) {
        for(BEIncidentDetails be : readIncidentDetails()) {
            if(be.getM_incident().getM_id() == beincident.getM_id()){
                return be;
            }
        }
        return null;
    }

}
