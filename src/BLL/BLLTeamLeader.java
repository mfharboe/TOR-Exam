/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEAlarm;
import BE.BEEmergency;
import BE.BEIncident;
import BE.BEIncidentVehicle;
import BE.BEMaterial;
import BE.BEUsage;
import DAL.DALCreate;
import DAL.DALRead;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morten
 */
public class BLLTeamLeader {

    private static BLLTeamLeader m_instance;
    ArrayList<BEUsage> usages;
    ArrayList<BEMaterial> materials;
    ArrayList<BEIncidentVehicle> incidentVehicles;
    ArrayList<BEEmergency> emergencies;
    ArrayList<BEAlarm> alarms;

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
    
    public void createIncidentVehicle(BEIncidentVehicle be){
        try {
            DALCreate.getInstance().createIncidentVehicle(be);
        } catch (SQLException ex) {
           return;
        }
        readIncidentVehicles();
        incidentVehicles.add(be);
    }
    public ArrayList<BEIncidentVehicle> incidentToIncidentVehicle(BEIncident beincident){
        ArrayList<BEIncidentVehicle> beincidentvehicle = new ArrayList<>();
        for(BEIncidentVehicle be : readIncidentVehicles())
            if(be.getM_incident().getM_id() == beincident.getM_id())
                beincidentvehicle.add(be);
        return beincidentvehicle;
    }
    
}
