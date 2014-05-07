package BE;

public class BEIncidentVehicle {

    private BEIncident m_incident;
    private BEVehicle m_vehicle;
    private BEEmergency m_emergency;
    private int m_amountCrew;
    private boolean m_isDiverged;

    /**
     * Read and Create IncidentVehicle
     *
     * @param incident
     * @param vehicle
     * @param emergency
     * @param amoutCrew
     * @param isDiverged
     */
    public BEIncidentVehicle(BEIncident incident, BEVehicle vehicle, BEEmergency emergency, int amoutCrew, boolean isDiverged) {
        m_incident = incident;
        m_vehicle = vehicle;
        m_emergency = emergency;
        m_amountCrew = amoutCrew;
        m_isDiverged = isDiverged;
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
     * @return the m_emergency
     */
    public BEEmergency getM_emergency() {
        return m_emergency;
    }

    /**
     * @param m_emergency the m_emergency to set
     */
    public void setM_emergency(BEEmergency m_emergency) {
        this.m_emergency = m_emergency;
    }

    /**
     * @return the m_amountCrew
     */
    public int getM_amountCrew() {
        return m_amountCrew;
    }

    /**
     * @param m_amountCrew the m_amountCrew to set
     */
    public void setM_amountCrew(int m_amountCrew) {
        this.m_amountCrew = m_amountCrew;
    }

    /**
     * @return the m_isDiverged
     */
    public boolean isM_isDiverged() {
        return m_isDiverged;
    }

    /**
     * @param m_isDiverged the m_isDiverged to set
     */
    public void setM_isDiverged(boolean m_isDiverged) {
        this.m_isDiverged = m_isDiverged;
    }
}
