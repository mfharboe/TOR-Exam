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

    /**
     *
     * @return ArrayList of Usage
     */
    public ArrayList<BEUsage> readUsage() {
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

    /**
     * Creates a new Vehicle on an Incident and adds it to the current Array
     *
     * @param incidentVehicle
     */
    public void createIncidentVehicle(BEIncidentVehicle incidentVehicle) {
        try {
            DALCreate.getInstance().createIncidentVehicle(incidentVehicle);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        readIncidentVehicles();
        incidentVehicles.add(incidentVehicle);
    }

    /**
     * Creates a new Usage on an Incident and adds it to the current Array
     *
     * @param usage
     */
    public void createUsage(BEUsage usage) {
        try {
            DALCreate.getInstance().createUsage(usage);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        usages.add(usage);
    }

    /**
     * Creates a new IncidentDetails on an Incident and adds it to the current
     * Array, it is filled with null until updatet later by the TeamLeader
     *
     * @param incident
     */
    public void createInitialIncidentDetails(BEIncident incident) {
        BEIncidentDetails details = new BEIncidentDetails(null, null, null, null, incident, null, null, null, null, null, null);
        try {
            BLLTeamLeader.getInstance().readIncidentDetails();
            DALCreate.getInstance().createInitialIncidentDetails(details);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        incidentDetails.add(details);
    }

    /**
     * Updates IncidentDetails for an Incident and sets new values in
     * BEIncidentDetails
     *
     * @param incidentDetails
     */
    public void updateIncidentDetails(BEIncidentDetails incidentDetails) {
        try {
            DALUpdate.getInstance().updateIncidentDetails(incidentDetails);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        for (BEIncidentDetails details : readIncidentDetails()) {
            if (details.getM_incident().getM_id() == incidentDetails.getM_incident().getM_id()) {
                details.setM_alarm(incidentDetails.getM_alarm());
                details.setM_detectorNumber(incidentDetails.getM_detectorNumber());
                details.setM_evaNumber(incidentDetails.getM_evaNumber());
                details.setM_fireReport(incidentDetails.getM_fireReport());
                details.setM_groupNumber(incidentDetails.getM_groupNumber());
                details.setM_incidentLeader(incidentDetails.getM_incidentLeader());
                details.setM_involvedAddress(incidentDetails.getM_involvedAddress());
                details.setM_involvedName(incidentDetails.getM_involvedName());
                details.setM_message(incidentDetails.getM_message());
                details.setM_remark(incidentDetails.getM_remark());
                break;
            }
        }
        MessageDialog.getInstance().teamLeaderSaveDialog(); //MÅ IKKE VÆRE HER
    }

    /**
     * Checks and adds IncidentVehicles for a given Incident
     *
     * @param incident
     * @return ArrayList of BEIncidentVehicles
     */
    public ArrayList<BEIncidentVehicle> incidentToIncidentVehicle(BEIncident incident) {
        ArrayList<BEIncidentVehicle> beincidentvehicle = new ArrayList<>();
        for (BEIncidentVehicle be : readIncidentVehicles()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                beincidentvehicle.add(be);
            }
        }
        return beincidentvehicle;
    }

    /**
     * Checks and adds Usage for a given Incident
     *
     * @param incident
     * @return ArrayList of BEUsage
     */
    public ArrayList<BEUsage> incidentToUsage(BEIncident incident) {
        ArrayList<BEUsage> beusage = new ArrayList<>();
        for (BEUsage be : readUsage()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                beusage.add(be);
            }
        }
        return beusage;
    }

    /**
     * Checks for IncidentDetails for a given Incident
     *
     * @param incident
     * @return The BEIncidentDetails or null if none is there
     */
    public BEIncidentDetails incidentToIncidentDetails(BEIncident incident) {
        for (BEIncidentDetails be : readIncidentDetails()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                return be;
            }
        }
        return null;
    }
}
