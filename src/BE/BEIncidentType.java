/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

public class BEIncidentType {
    
    private int m_id;
    private String m_description;
    
    /**
     * Read incident types
     * @param id
     * @param description 
     */
    public BEIncidentType(int id, String description){
        m_id = id;
        m_description = description;
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
    
    public String toString(){
        return m_description;
    }
}
