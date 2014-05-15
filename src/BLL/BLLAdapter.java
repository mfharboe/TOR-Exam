package BLL;

import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BERSS;
import BE.BERoleTime;
import BE.BEUsage;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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

    public BEIncident RSStoIncident(BERSS feed) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);

        String incidentName = feed.getM_message();
        String incidentDate = feed.getM_date();
        incidentDate = incidentDate.substring(6, 16);
        java.sql.Date sqlDate = null;
        
        try {
            Date utilDate = null;
            utilDate = formatter.parse(incidentDate);
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {
            BLLError.getInstance().dbError();
        }

        String incidentTime = feed.getM_date();
        incidentTime = incidentTime.substring(18, 25);
        Time time = java.sql.Time.valueOf(incidentTime);
        boolean isDone = false;
        BEIncidentType incidentType = null;
        final int INCIDENT_TYPE = 1; 
        for(BEIncidentType type : BLLRead.getInstance().readAllIncidentTypes()){
            if(type.getM_id() == INCIDENT_TYPE){
                incidentType = type;
                break;
            }
        }
        BEIncident incident = new BEIncident(incidentName, sqlDate, time, incidentType, isDone);
        return incident;

    }
}
