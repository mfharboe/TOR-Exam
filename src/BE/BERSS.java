
package BE;

public class BERSS {
   
    private int m_id;
    private String m_message;
    private String m_comment;
    private String m_date;
    
    
    public BERSS(int id, String message, String comment, String date){
        m_id = id;
        m_message = message;
        m_comment = comment;
        m_date = date;
    }

    /**
     * @return the m_message
     */
    public String getM_message() {
        return m_message;
    }

    /**
     * @param m_message the m_message to set
     */
    public void setM_message(String m_message) {
        this.m_message = m_message;
    }

    /**
     * @return the m_date
     */
    public String getM_date() {
        return m_date;
    }

    /**
     * @param m_date the m_date to set
     */
    public void setM_date(String m_date) {
        this.m_date = m_date;
    }

    /**
     * @return the m_comment
     */
    public String getM_comment() {
        return m_comment;
    }

    /**
     * @param m_comment the m_comment to set
     */
    public void setM_comment(String m_comment) {
        this.m_comment = m_comment;
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

}
