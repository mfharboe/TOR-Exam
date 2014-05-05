
package BE;

import java.sql.Date;

public class BEClothesWash {
    private BEFireman m_fireman;
    private Date m_date;
    
    /**
     * Create and Read Clothes Wash
     * @param fireman
     * @param date 
     */
    public BEClothesWash(BEFireman fireman, Date date){
        m_fireman = fireman;
        m_date = date;
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
}
