package BLL;

import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BERole;
import BE.BERoleTime;
import BE.BEUsage;
import DAL.DALCreate;
import GUI.MessageDialog;
import java.sql.SQLException;

public class BLLCreate {
    private static BLLCreate m_instance;
    
    private BLLCreate(){}
    
    public static BLLCreate getInstance(){
        if(m_instance == null)
            m_instance = new BLLCreate();
        return m_instance;
    }
    
    /**
     * Creates a new Incident
     *
     * @param incident
     * @return
     */
    public boolean createIncident(BEIncident incident) {
        try {
            DALCreate.getInstance().createIncident(incident);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return false;
        }
        return true;
    }
    
    /**
     * Creates the RoleTime in the DB with the given role
     * @param roleTime
     * @param roleNumber 
     */
    public void createRoleOnIncident(BERoleTime roleTime, int roleNumber) {
        BERole tmpPrevRole = null;
        
        for (BERole role : BLLRead.getInstance().readAllRoles()) {
            tmpPrevRole = roleTime.getM_role();
            if (role.getM_id() == roleNumber) {
                roleTime.setM_role(role);
                try {
                    DALCreate.getInstance().createRoleTime(roleTime);
                } catch (SQLException ex) {
                    //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    roleTime.setM_role(tmpPrevRole);
                    MessageDialog.getInstance().dialogFunction(); //MÅ IKKE VÆRE HER
                    return;
                }
                BLLRead.getInstance().addToRoleTime(roleTime);
                return;
            }
        }
    }
    
    /**
     * Creates a new Usage on an Incident and adds it to the current Array
     *
     * @param usage
     */
    public void createUsage(BEUsage usage) {
        try {
            DALCreate.getInstance().createUsage(usage);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        BLLRead.getInstance().addToUsage(usage);
    }
    
    /**
     * Creates a new IncidentDetails on an Incident and adds it to the current
     * Array, it is filled with null until updatet later by the TeamLeader
     *
     * @param incident
     */
    public void createInitialIncidentDetails(BEIncident incident) {
        BEIncidentDetails details = new BEIncidentDetails(null, null, null, null, incident, null, null, null, null, null, null);
        try {
            BLLRead.getInstance().readIncidentDetails();
            DALCreate.getInstance().createInitialIncidentDetails(details);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        BLLRead.getInstance().addToIncidentDetails(details);
    }

}
