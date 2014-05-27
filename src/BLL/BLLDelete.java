package BLL;

import BE.BERoleTime;
import BE.BEUsage;
import DAL.DALDelete;
import DAL.Intefaces.IDALDelete;
import java.sql.SQLException;


public class BLLDelete {
    
    private static BLLDelete m_instance;
    IDALDelete dalDelete;
    
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
    
    public void setDAL(IDALDelete d){
        dalDelete = d;
    }
    
    
    /**
     * Deletes the used material from a incident
     * @param usage 
     */
    public void deleteMaterialFromUsage(BEUsage usage){
        try {
            dalDelete.deleteMaterialFromUsage(usage);
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
           dalDelete.deleteFiremanFromRoleTime(roleTime);
        } catch (SQLException ex) {
            BLLError.getInstance().deleteFiremanFromRoleTimeError();
            return;
        }
        BLLRead.getInstance().removeFromRoleTime(roleTime);
    }
}
