package BLL;

import BE.BERoleTime;
import BE.BEUsage;
import DAL.DALDelete;
import java.sql.SQLException;


public class BLLDelete {
    
    private static BLLDelete m_instance;
    
    private BLLDelete(){}
    
    /**
     * 
     * @return current m_instance of BLLDelete
     */
    public static BLLDelete getInstance(){
        if(m_instance == null)
            m_instance = new BLLDelete();
        return m_instance;
    }
    
    
    /**
     * Deletes the used material from a incident
     * @param usage 
     */
    public void deleteMaterialFromUsage(BEUsage usage){
        try {
            DALDelete.getInstance().deleteMaterialFromUsage(usage);
        } catch (SQLException ex) {
            BLLError.getInstance().deleteMaterialFromUsageError();
            return;
        }
        BLLRead.getInstance().removeFromUsage(usage);
    }
    
    /**
     * Deletes the given fireman from the RoleTime table
     * @param roleTime 
     */
    public void deleteFiremanFromRoleTime(BERoleTime roleTime){
        try {
            DALDelete.getInstance().deleteFiremanFromRoleTime(roleTime);
        } catch (SQLException ex) {
            BLLError.getInstance().deleteFiremanFromRoleTimeError();
            return;
        }
        BLLRead.getInstance().removeFromRoleTime(roleTime);
    }
}
