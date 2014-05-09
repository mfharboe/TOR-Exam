package BLL;

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
import GUI.MessageDialog;
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

    /**
     *
     * @return m_instance of BLLTeamLeader
     */
    public static BLLTeamLeader getInstance() {
        if (m_instance == null) {
            m_instance = new BLLTeamLeader();
        }
        return m_instance;
    }

    public void addToIncidentVehicles(BEIncidentVehicle be){
       incidentVehicles.add(be);
    }
     

    

    /**
     *
     * @return ArrayList of IncidentVehicles
     */
    public ArrayList<BEIncidentVehicle> readIncidentVehicles() {
        if (incidentVehicles == null) {
            try {
                incidentVehicles = DALRead.getInstance().readIncidentVehicle();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return incidentVehicles;
    }



  

//    /**
//     * Creates a new Vehicle on an Incident and adds it to the current Array
//     *
//     * @param incidentVehicle
//     */
//    public void createIncidentVehicle(BEIncidentVehicle incidentVehicle) {
//        try {
//            DALCreate.getInstance().createIncidentVehicle(incidentVehicle);
//        } catch (SQLException ex) {
//            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
//            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
//            return;
//        }
//        readIncidentVehicles();
//        incidentVehicles.add(incidentVehicle);
//    }

 
}
