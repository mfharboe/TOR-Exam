
package DAL;

import BE.BEIncident;
import BE.BEIncidentType;
import BE.BERoleTime;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DALCreate {
    
    Connection m_connection;
    private static DALCreate m_instance;
    
    ArrayList<BEIncidentType> resIncidentType;
  

    private DALCreate() {
        m_connection = DB_Connection.getInstance().getConnection();
    }

    /**
     * @return current instance of DALCreate
     */
    public static DALCreate getInstance() {
        if (m_instance == null) {
            m_instance = new DALCreate();
        }
        return m_instance;
    }
    /**
     * Creates a new incident in the DB
     * @param c
     * @throws SQLException 
     */
    public void createIncident(BEIncident c) throws SQLException {
        String sql = "insert into Incident values (?,?,?,?,?); ";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, c.getM_incidentName());
        ps.setDate(2, c.getM_date());
        ps.setTime(3, c.getM_time());
        ps.setInt(4, c.getM_incidentType().getM_id());
        ps.setBoolean(5, c.isM_isDone());
        ps.executeUpdate();
        
        String sql2 = "Select * from Incident where incident.id = (Select MAX(id) from Incident);";
        Statement stm = m_connection.createStatement();
        stm.execute(sql2);
        ResultSet result = stm.getResultSet();
        while(result.next())
        c.setM_id(result.getInt("id"));
      
    }
    
    public void createRoleTime(BERoleTime be) throws SQLException {
        String sql = "insert into [Role/Time] values (?,?,?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, be.getM_incident().getM_id());
        ps.setInt(2, be.getM_fireman().getM_id());
        ps.setInt(3, be.getM_role().getM_id());
        ps.setInt(4, be.getM_vehicle().getM_odinNumber());
        ps.setInt(5, be.getM_hours());
        ps.executeUpdate();
    }
    
}
