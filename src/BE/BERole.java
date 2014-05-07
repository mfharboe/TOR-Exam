package BE;

public class BERole {

    private int m_id;
    private String m_description;
    private boolean m_isFireman;
    private boolean m_isDriver;
    private boolean m_isLeader;
    private boolean m_isStation;

    /**
     * Read Role
     *
     * @param id
     * @param description
     * @param isFireman
     * @param isDriver
     * @param isLeader
     * @param isStation
     */
    public BERole(int id, String description, boolean isFireman, boolean isDriver,
            boolean isLeader, boolean isStation) {

        m_id = id;
        m_description = description;
        m_isFireman = isFireman;
        m_isDriver = isDriver;
        m_isLeader = isLeader;
        m_isStation = isStation;
    }

    /**
     * @return the m_id
     */
    public int getM_id() {
        return m_id;
    }

    /**
     * @param m_id the m_id to set
     */
    public void setM_id(int m_id) {
        this.m_id = m_id;
    }

    /**
     * @return the m_description
     */
    public String getM_description() {
        return m_description;
    }

    /**
     * @param m_description the m_description to set
     */
    public void setM_description(String m_description) {
        this.m_description = m_description;
    }

    /**
     * @return the m_isFireman
     */
    public boolean isM_isFireman() {
        return m_isFireman;
    }

    /**
     * @param m_isFireman the m_isFireman to set
     */
    public void setM_isFireman(boolean m_isFireman) {
        this.m_isFireman = m_isFireman;
    }

    /**
     * @return the m_isDriver
     */
    public boolean isM_isDriver() {
        return m_isDriver;
    }

    /**
     * @param m_isDriver the m_isDriver to set
     */
    public void setM_isDriver(boolean m_isDriver) {
        this.m_isDriver = m_isDriver;
    }

    /**
     * @return the m_isLeader
     */
    public boolean isM_isLeader() {
        return m_isLeader;
    }

    /**
     * @param m_isLeader the m_isLeader to set
     */
    public void setM_isLeader(boolean m_isLeader) {
        this.m_isLeader = m_isLeader;
    }

    /**
     * @return the m_isStation
     */
    public boolean isM_isStation() {
        return m_isStation;
    }

    /**
     * @param m_isStation the m_isStation to set
     */
    public void setM_isStation(boolean m_isStation) {
        this.m_isStation = m_isStation;
    }

}
