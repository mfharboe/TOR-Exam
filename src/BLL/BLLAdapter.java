package BLL;

import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BERSS;
import BE.BERoleTime;
import BE.BEUsage;
import ObserverPattern.IObserver;
import ObserverPattern.ISubject;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class BLLAdapter implements ISubject{

    private static BLLAdapter m_instance;
    private final ArrayList<IObserver> observers;
    private final ArrayList<BERoleTime> roletimes;
    private final ArrayList<BEUsage> usages;
    
    private BLLAdapter() {
        observers = new ArrayList<>();
        roletimes = new ArrayList<>();
        usages = new ArrayList<>();
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
     */
    public void incidentToRoleTime(BEIncident incident) {
        roletimes.clear();
        for (BERoleTime be : BLLRead.getInstance().readAllRoleTimes()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                roletimes.add(be);
            }
        }
        notifyObservers();
    }
    
    public ArrayList<BERoleTime> getEmptyIncidentRoleTime(){
        roletimes.clear();
        notifyObservers();
        return roletimes;
    }

    /**
     * Checks and adds Usage for a given Incident
     *
     * @param incident
     */
    public void incidentToUsage(BEIncident incident) {
        usages.clear();
        for (BEUsage be : BLLRead.getInstance().readUsages()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                usages.add(be);
            }
        }
        notifyObservers();
    }
    
    public ArrayList<BEUsage> getEmptyUsage(){
        usages.clear();
        notifyObservers();
        return usages;
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

    @Override
    public void register(IObserver o) {
        observers.add(o);
    }

    @Override
    public void unregister(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(IObserver observer : observers)
            observer.update();
    }
}
