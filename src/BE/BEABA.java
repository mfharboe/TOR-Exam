
package BE;

public class BEABA {
    private BEIncident m_incident;
    private int m_detectorNumber;
    private int m_groupNumber;
    
    /**
     * Read ABA alarm
     * @param incident
     * @param detectorNumber
     * @param groupNumber 
     */
    public BEABA(BEIncident incident, int detectorNumber, int groupNumber){
        m_incident = incident;
        m_detectorNumber = detectorNumber;
        m_groupNumber = groupNumber;
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
     * @return the m_detectorNumber
     */
    public int getM_detectorNumber() {
        return m_detectorNumber;
    }

    /**
     * @param m_detectorNumber the m_detectorNumber to set
     */
    public void setM_detectorNumber(int m_detectorNumber) {
        this.m_detectorNumber = m_detectorNumber;
    }

    /**
     * @return the m_groupNumber
     */
    public int getM_groupNumber() {
        return m_groupNumber;
    }

    /**
     * @param m_groupNumber the m_groupNumber to set
     */
    public void setM_groupNumber(int m_groupNumber) {
        this.m_groupNumber = m_groupNumber;
    }
}
