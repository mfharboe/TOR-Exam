package DAL;

import BE.BEUsage;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DALDelete {
    
    private static DALDelete m_instance;
    
    private DALDelete(){}
    
    public static DALDelete getInstance(){
        if(m_instance == null)
            m_instance = new DALDelete();
        return m_instance;
    }
    
    public void deleteMaterialFromUsage(BEUsage usage) throws SQLException{
        String sql = "Delete from [Usage] where [usage].id = ?";
        PreparedStatement ps = DB_Connection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1, usage.getM_id());
        ps.executeUpdate();
     
    }
}
