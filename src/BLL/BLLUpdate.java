package BLL;

import BE.BEIncidentDetails;
import DAL.Intefaces.IDALUpdate;
import java.sql.SQLException;

public class BLLUpdate {

    private static BLLUpdate m_instance;
    IDALUpdate dalUpdate;

    private BLLUpdate() {
    }

    /**
     * 
     * @return current m_instance of BLLUpdate 
     */
    public static BLLUpdate getInstance() {
        if (m_instance == null) {
            m_instance = new BLLUpdate();
        }
        return m_instance;
    }

    public void setDAL(IDALUpdate d){
        dalUpdate = d;
    }
    /**
     * Updates IncidentDetails for an Incident and sets new values in
     * BEIncidentDetails
     *
     * @param incidentDetails
     */
    public void updateIncidentDetails(BEIncidentDetails incidentDetails) {
        try {
            dalUpdate.updateIncidentDetails(incidentDetails);
        } catch (SQLException ex) {
            BLLError.getInstance().updateIncidentDetailsError();
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
                details.setM_remark(incidentDetails.getM_remark());
                break;
            }
        }
    }
}
