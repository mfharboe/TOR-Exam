package BLL;

import BE.BERoleTime;
import BE.BEUsage;
import DAL.DALDelete;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BLLDelete {
    
    private static BLLDelete m_instance;
    
    private BLLDelete(){}
    
    public static BLLDelete getInstance(){
        if(m_instance == null)
            m_instance = new BLLDelete();
        return m_instance;
    }
    
    public void deleteMaterialFromUsage(BEUsage usage){
        try {
            DALDelete.getInstance().deleteMaterialFromUsage(usage);
        } catch (SQLException ex) {
            Logger.getLogger(BLLDelete.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        BLLRead.getInstance().removeFromUsage(usage);
    }
    
    public void deleteFiremanFromRoleTime(BERoleTime roleTime){
        try {
            DALDelete.getInstance().deleteFiremanFromRoleTime(roleTime);
        } catch (SQLException ex) {
            Logger.getLogger(BLLDelete.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        BLLRead.getInstance().removeFromRoleTime(roleTime);
    }
}
