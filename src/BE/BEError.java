/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

import java.sql.Date;

/**
 *
 * @author Morten
 */
public class BEError {
    private int m_id;
    private BEVehicle m_vehicleOdinNumber;
    private String m_filledBy;
    private Date m_date;
    private boolean m_outOfOrder;
    private boolean m_urgent;
    private String m_description;
    private String m_cause;
    private boolean m_suitWash;
    
    /**
     * read BEError
     * @param id
     * @param vehicleOdinNumber
     * @param filledby
     * @param date
     * @param outoforder
     * @param urgent
     * @param description
     * @param cause
     * @param suitwash 
     */
    public BEError(int id, BEVehicle vehicleOdinNumber, String filledby, Date date, boolean outoforder, 
            boolean urgent, String description, String cause, boolean suitwash ){
        
        m_id = id;
        m_vehicleOdinNumber = vehicleOdinNumber;
        m_filledBy = filledby;
        m_date = date;
        m_outOfOrder = outoforder;
        m_urgent = urgent;
        m_description = description;
        m_cause = cause;
        m_suitWash = suitwash;
        
    }
    /**
     * Create BEError
     * @param vehicleOdinNumber
     * @param filledby
     * @param date
     * @param outoforder
     * @param urgent
     * @param description
     * @param cause
     * @param suitwash 
     */
    public BEError(BEVehicle vehicleOdinNumber, String filledby, Date date, boolean outoforder, 
            boolean urgent, String description, String cause, boolean suitwash ){
        
        
        m_vehicleOdinNumber = vehicleOdinNumber;
        m_filledBy = filledby;
        m_date = date;
        m_outOfOrder = outoforder;
        m_urgent = urgent;
        m_description = description;
        m_cause = cause;
        m_suitWash = suitwash;
        
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

    /**
     * @return the m_suitWash
     */
    public boolean isM_suitWash() {
        return m_suitWash;
    }

    /**
     * @param m_suitWash the m_suitWash to set
     */
    public void setM_suitWash(boolean m_suitWash) {
        this.m_suitWash = m_suitWash;
    }

    /**
     * @return the m_vehicleOdinNumber
     */
    public BEVehicle getM_vehicleOdinNumber() {
        return m_vehicleOdinNumber;
    }

    /**
     * @param m_vehicleOdinNumber the m_vehicleOdinNumber to set
     */
    public void setM_vehicleOdinNumber(BEVehicle m_vehicleOdinNumber) {
        this.m_vehicleOdinNumber = m_vehicleOdinNumber;
    }
    
}
