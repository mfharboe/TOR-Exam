/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Morten
 */
public class BERoleTime {

    private BEFireman m_fireman;
    private BEIncident m_incident;
    private BERole m_role;
    private BEVehicle m_vehicle;
    private int m_hours;

    public BERoleTime(BEFireman fireman, BEIncident incident, BERole role, BEVehicle vehicle, int hours) {
        m_fireman = fireman;
        m_incident = incident;
        m_role = role;
        m_vehicle = vehicle;
        m_hours = hours;

    }

    /**
     * @return the m_fireman
     */
    public BEFireman getM_fireman() {
        return m_fireman;
    }

    /**
     * @param m_fireman the m_fireman to set
     */
    public void setM_fireman(BEFireman m_fireman) {
        this.m_fireman = m_fireman;
    }

    /**
     * @return the m_incident
     */
    public BEIncident getM_incident() {
        return m_incident;
    }

    /**
     * @param m_incident the m_incident to set
     */
    public void setM_incident(BEIncident m_incident) {
        this.m_incident = m_incident;
    }

    /**
     * @return the m_role
     */
    public BERole getM_role() {
        return m_role;
    }

    /**
     * @param m_role the m_role to set
     */
    public void setM_role(BERole m_role) {
        this.m_role = m_role;
    }

    /**
     * @return the m_vehicle
     */
    public BEVehicle getM_vehicle() {
        return m_vehicle;
    }

    /**
     * @param m_vehicle the m_vehicle to set
     */
    public void setM_vehicle(BEVehicle m_vehicle) {
        this.m_vehicle = m_vehicle;
    }

    /**
     * @return the m_hours
     */
    public int getM_hours() {
        return m_hours;
    }

    /**
     * @param m_hours the m_hours to set
     */
    public void setM_hours(int m_hours) {
        this.m_hours = m_hours;
    }
}
