/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.BEIncident;
import BE.BEIncidentType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Morten
 */
public class DALUpdate {

    Connection m_connection;
    private static DALUpdate m_instance;
    ArrayList<BEIncidentType> resIncidentType;

    private DALUpdate() {
        m_connection = DB_Connection.getInstance().getConnection();
    }

    /**
     * @return current instance of DALRead
     */
    public static DALUpdate getInstance() {
        if (m_instance == null) {
            m_instance = new DALUpdate();
        }
        return m_instance;
    }

    public void updateIncident(BEIncident c) throws SQLException {
        String sql = "update Incident Set incidentName = ?, "
                + "Incident.[date] = ?, "
                + "startTime = ?, "
                + "incidentTypeId = ?, "
                + "isDone = ? "
                + "where Incident.id = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, c.getM_incidentName());
        ps.setDate(2, c.getM_date());
        ps.setTime(3, c.getM_time());
        ps.setInt(4, c.getM_incidentType().getM_id());
        ps.setBoolean(5, c.isM_isDone());
        ps.setInt(6, c.getM_id());

        ps.execute();


    }
}
