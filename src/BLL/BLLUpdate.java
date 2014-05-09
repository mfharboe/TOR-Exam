package BLL;

import BE.BEIncident;
import BE.BEIncidentDetails;
import DAL.DALUpdate;
import GUI.MessageDialog;
import java.sql.SQLException;

public class BLLUpdate {

    private static BLLUpdate m_instance;

    private BLLUpdate() {
    }

    public static BLLUpdate getInstance() {
        if (m_instance == null) {
            m_instance = new BLLUpdate();
        }
        return m_instance;
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
     * Updates IncidentDetails for an Incident and sets new values in
     * BEIncidentDetails
     *
     * @param incidentDetails
     */

    // OPDATERER BE FRA STARTEN I STEDET FOR AT LAVE NYYY
    public void updateIncidentDetails(BEIncidentDetails incidentDetails) {
        try {
            DALUpdate.getInstance().updateIncidentDetails(incidentDetails);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLTeamLeader.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        for (BEIncidentDetails details : BLLRead.getInstance().readIncidentDetails()) {
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
}
