package DAL;

import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
        String sql = "Update Incident set incidentName = ?, "
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
        ps.executeUpdate();
    }

    public void updateIncidentDetails(BEIncidentDetails c) throws SQLException {
        String sql = "Update IncidentDetails set incidentLeader = ?, "
                + "evaNumber = ?, "
                + "fireReport = ?, "
                + "message = ?, "
                + "involvedName = ?, "
                + "involvedAddress = ?, "
                + "remark = ?, "
                + "alarmId = ?,"
                + "detectorNumber = ?,"
                + "groupNumber = ? "
                + "where incidentId = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, c.getM_incidentLeader());
        ps.setString(2, c.getM_evaNumber());
        ps.setString(3, c.getM_fireReport());
        ps.setString(4, c.getM_message());
        ps.setString(5, c.getM_involvedName());
        ps.setString(6, c.getM_involvedAddress());
        ps.setString(7, c.getM_remark());
        if (c.getM_alarm() == null) {
            ps.setString(8, null);
        } else {
            ps.setInt(8, c.getM_alarm().getM_id());
        }
        ps.setString(9, c.getM_detectorNumber());
        ps.setString(10, c.getM_groupNumber());
        ps.setInt(11, c.getM_incident().getM_id());
        ps.executeUpdate();

    }

}
