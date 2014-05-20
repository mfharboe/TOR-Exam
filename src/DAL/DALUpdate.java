package DAL;

import DAL.Intefaces.IDALUpdate;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DALUpdate implements IDALUpdate {

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

    /**
     * Updates an Incident row in the DB
     *
     * @param incident
     * @throws SQLException
     */
    @Override
    public void updateIncident(BEIncident incident) throws SQLException {
        String sql = "Update Incident set incidentName = ?, "
                + "Incident.[date] = ?, "
                + "startTime = ?, "
                + "incidentTypeId = ?, "
                + "isDone = ? "
                + "where Incident.id = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, incident.getM_incidentName());
        ps.setDate(2, incident.getM_date());
        ps.setTime(3, incident.getM_time());
        ps.setInt(4, incident.getM_incidentType().getM_id());
        ps.setBoolean(5, incident.isM_isDone());
        ps.setInt(6, incident.getM_id());
        ps.executeUpdate();
    }

    /**
     * Updates an InincidentDetailsidentDetails row in the DB
     *
     * @param incidentDetails
     * @throws SQLException
     */
    @Override
    public void updateIncidentDetails(BEIncidentDetails incidentDetails) throws SQLException {
        String sql = "Update IncidentDetails set incidentLeader = ?, "
                + "evaNumber = ?, "
                + "fireReport = ?, "
                + "involvedName = ?, "
                + "involvedAddress = ?, "
                + "remark = ?, "
                + "alarmId = ?,"
                + "detectorNumber = ?,"
                + "groupNumber = ? "
                + "where incidentId = ?";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, incidentDetails.getM_incidentLeader());
        ps.setString(2, incidentDetails.getM_evaNumber());
        ps.setString(3, incidentDetails.getM_fireReport());
        ps.setString(4, incidentDetails.getM_involvedName());
        ps.setString(5, incidentDetails.getM_involvedAddress());
        ps.setString(6, incidentDetails.getM_remark());
        if (incidentDetails.getM_alarm() == null) {
            ps.setString(7, null);
        } else {
            ps.setInt(7, incidentDetails.getM_alarm().getM_id());
        }
        ps.setString(8, incidentDetails.getM_detectorNumber());
        ps.setString(9, incidentDetails.getM_groupNumber());
        ps.setInt(10, incidentDetails.getM_incident().getM_id());
        ps.executeUpdate();

    }
 

}
