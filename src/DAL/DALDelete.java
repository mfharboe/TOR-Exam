package DAL;

import BE.BERoleTime;
import BE.BEUsage;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DALDelete {

    private static DALDelete m_instance;

    private DALDelete() {
    }

    public static DALDelete getInstance() {
        if (m_instance == null) {
            m_instance = new DALDelete();
        }
        return m_instance;
    }

    /**
     * Deletes the material entry from the Usage table
     * @param usage
     * @throws SQLException 
     */
    public void deleteMaterialFromUsage(BEUsage usage) throws SQLException {
        String sql = "Delete from [Usage] where [usage].id = ?";
        PreparedStatement ps = DB_Connection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1, usage.getM_id());
        ps.executeUpdate();
    }

    /**
     * Deletes a fireman entry in the RoleTime table
     * @param roleTime
     * @throws SQLException 
     */
    public void deleteFiremanFromRoleTime(BERoleTime roleTime) throws SQLException {
        String sql = "Delete from [Role/Time] where firemanId = ? and incidentId= ?";
        PreparedStatement ps = DB_Connection.getInstance().getConnection().prepareStatement(sql);
        ps.setInt(1, roleTime.getM_fireman().getM_id());
        ps.setInt(2, roleTime.getM_incident().getM_id());
        ps.executeUpdate();
    }
}
