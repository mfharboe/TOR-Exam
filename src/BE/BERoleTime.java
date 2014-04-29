/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BE;

/**
 *
 * @author Morten
 */
public class BERoleTime {
    
    private BEFireman m_fireman;
    private BEIncident m_incident;
    private BERole m_role;
    private BEVehicle m_vehicle;
    private int m_hours;
    private boolean m_isDone:
    
    
    public BERoleTime(BEFireman fireman, BEIncident incident, BERole role, BEVehicle vehicle, int hours, boolean isdone){
        m_fireman = fireman;
        m_incident = incident;
        m_role = role;
        m_vehicle = vehicle;
        m_hours = hours;
        m_isDone = isdone;
        
    }
    
}
