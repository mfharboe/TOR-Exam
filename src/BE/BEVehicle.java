package BE;

public class BEVehicle {

    private int m_odinNumber;
    private String m_registrationNumber;
    private String m_brand;
    private String m_model;
    private String m_description;
    private boolean m_isReady;
    private int m_seats;

    /**
     * Read Vehicle
     *
     * @param odinNumber
     * @param registrationNumber
     * @param brand
     * @param model
     * @param description
     * @param isReady
     * @param seats
     */
    public BEVehicle(int odinNumber, String registrationNumber, String brand, String model,
            String description, boolean isReady, int seats) {

        m_odinNumber = odinNumber;
        m_registrationNumber = registrationNumber;
        m_brand = brand;
        m_model = model;
        m_description = description;
        m_isReady = isReady;
        m_seats = seats;

    }

    /**
     * @return the m_odinNumber
     */
    public int getM_odinNumber() {
        return m_odinNumber;
    }

    /**
     * @param m_odinNumber the m_odinNumber to set
     */
    public void setM_odinNumber(int m_odinNumber) {
        this.m_odinNumber = m_odinNumber;
    }

    /**
     * @return the m_registrationNumber
     */
    public String getM_registrationNumber() {
        return m_registrationNumber;
    }

    /**
     * @param m_registrationNumber the m_registrationNumber to set
     */
    public void setM_registrationNumber(String m_registrationNumber) {
        this.m_registrationNumber = m_registrationNumber;
    }

    /**
     * @return the m_brand
     */
    public String getM_brand() {
        return m_brand;
    }

    /**
     * @param m_brand the m_brand to set
     */
    public void setM_brand(String m_brand) {
        this.m_brand = m_brand;
    }

    /**
     * @return the m_model
     */
    public String getM_model() {
        return m_model;
    }

    /**
     * @param m_model the m_model to set
     */
    public void setM_model(String m_model) {
        this.m_model = m_model;
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
     * @return the m_isReady
     */
    public boolean isM_isReady() {
        return m_isReady;
    }

    /**
     * @param m_isReady the m_isReady to set
     */
    public void setM_isReady(boolean m_isReady) {
        this.m_isReady = m_isReady;
    }

    @Override
    public String toString() {
        return "" + m_odinNumber;
    }

    /**
     * @return the m_seats
     */
    public int getM_seats() {
        return m_seats;
    }

    /**
     * @param m_seats the m_seats to set
     */
    public void setM_seats(int m_seats) {
        this.m_seats = m_seats;
    }

}
