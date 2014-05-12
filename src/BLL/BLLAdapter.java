package BLL;

import BE.BEAlarm;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BERoleTime;
import BE.BESalary;
import BE.BEUsage;
import java.util.ArrayList;

public class BLLAdapter {

    private static BLLAdapter m_instance;

    private BLLAdapter() {

    }

    public static BLLAdapter getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAdapter();
        }
        return m_instance;
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

    /**
     * Checks and adds Usage for a given Incident
     *
     * @param incident
     * @return ArrayList of BEUsage
     */
    public ArrayList<BEUsage> incidentToUsage(BEIncident incident) {
        ArrayList<BEUsage> beusage = new ArrayList<>();
        for (BEUsage be : BLLRead.getInstance().readUsages()) {
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
        for (BEIncidentDetails be : BLLRead.getInstance().readIncidentDetails()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                return be;
            }
        }
        return null;
    }

 
}
