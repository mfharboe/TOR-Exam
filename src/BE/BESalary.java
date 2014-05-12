
package BE;

public class BESalary {
    private int m_salaryCode;
    private String m_description;
    
    public BESalary(int salaryCode, String description){
        m_salaryCode = salaryCode;
        m_description = description;
    }

    /**
     * @return the m_salaryCode
     */
    public int getM_salaryCode() {
        return m_salaryCode;
    }

    /**
     * @param m_salaryCode the m_salaryCode to set
     */
    public void setM_salaryCode(int m_salaryCode) {
        this.m_salaryCode = m_salaryCode;
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
    
}
