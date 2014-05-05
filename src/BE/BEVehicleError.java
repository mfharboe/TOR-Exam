
package BE;

import java.sql.Date;

public class BEVehicleError {
    private BEVehicle m_odinNumber;
    private String m_filledBy;
    private Date m_date;
    private boolean m_outOfOrder;
    private boolean m_urgent;
    private String m_description;
    private String m_cause;
    
    /**
     * Read and Create VehicleError
     * @param odinNumber
     * @param filledBy
     * @param date
     * @param outOfOrder
     * @param urgent
     * @param description
     * @param cause 
     */
    public BEVehicleError(BEVehicle odinNumber, String filledBy, Date date, boolean outOfOrder, boolean urgent,
            String description, String cause){
        m_odinNumber = odinNumber;
        m_filledBy = filledBy;
        m_date = date;
        m_outOfOrder = outOfOrder;
        m_urgent = urgent;
        m_description = description;
        m_cause = cause;
    }

    /**
     * @return the m_odinNumber
     */
    public BEVehicle getM_odinNumber() {
        return m_odinNumber;
    }

    /**
     * @param m_odinNumber the m_odinNumber to set
     */
    public void setM_odinNumber(BEVehicle m_odinNumber) {
        this.m_odinNumber = m_odinNumber;
    }

    /**
     * @return the m_filledBy
     */
    public String getM_filledBy() {
        return m_filledBy;
    }

    /**
     * @param m_filledBy the m_filledBy to set
     */
    public void setM_filledBy(String m_filledBy) {
        this.m_filledBy = m_filledBy;
    }

    /**
     * @return the m_date
     */
    public Date getM_date() {
        return m_date;
    }

    /**
     * @param m_date the m_date to set
     */
    public void setM_date(Date m_date) {
        this.m_date = m_date;
    }

    /**
     * @return the m_outOfOrder
     */
    public boolean isM_outOfOrder() {
        return m_outOfOrder;
    }

    /**
     * @param m_outOfOrder the m_outOfOrder to set
     */
    public void setM_outOfOrder(boolean m_outOfOrder) {
        this.m_outOfOrder = m_outOfOrder;
    }

    /**
     * @return the m_urgent
     */
    public boolean isM_urgent() {
        return m_urgent;
    }

    /**
     * @param m_urgent the m_urgent to set
     */
    public void setM_urgent(boolean m_urgent) {
        this.m_urgent = m_urgent;
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
     * @return the m_cause
     */
    public String getM_cause() {
        return m_cause;
    }

    /**
     * @param m_cause the m_cause to set
     */
    public void setM_cause(String m_cause) {
        this.m_cause = m_cause;
    }
}
