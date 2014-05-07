package DAL;

import BE.BEError;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BEIncidentVehicle;
import BE.BERoleTime;
import BE.BEUsage;
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
        while (result.next()) {
            c.setM_id(result.getInt("id"));
        }

    }

    /**
     * Creates a new RoleTime in the DB
     * @param be
     * @throws SQLException 
     */
    public void createRoleTime(BERoleTime be) throws SQLException {
        String sql = "insert into [Role/Time] values (?,?,?,?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, be.getM_incident().getM_id());
        ps.setInt(2, be.getM_fireman().getM_id());
        ps.setBoolean(3, be.isM_isOnStation());
        ps.setInt(4, be.getM_role().getM_id());

        if (be.getM_vehicle() == null) {
            ps.setString(5, null);
        } else {
            ps.setInt(5, be.getM_vehicle().getM_odinNumber());
        }
        ps.setInt(6, be.getM_hours());
        ps.executeUpdate();
    }

    /**
     * Creates a new IncidentVehicle
     * @param be
     * @throws SQLException 
     */
    public void createIncidentVehicle(BEIncidentVehicle be) throws SQLException {
        String sql = " insert into [Incident/Vehicle] values (?,?,?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, be.getM_incident().getM_id());
        ps.setInt(2, be.getM_vehicle().getM_odinNumber());
        ps.setInt(3, be.getM_emergency().getM_id());
        ps.setInt(4, be.getM_amountCrew());
        ps.setBoolean(5, be.isM_isDiverged());
        ps.executeUpdate();

    }

    public void createUsage(BEUsage be) throws SQLException {
        String sql = "insert into Usage values (?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, be.getM_material().getM_id());
        ps.setInt(2, be.getM_amount());
        ps.setInt(3, be.getM_incident().getM_id());
        ps.executeUpdate();
    }

    public void createErrorReport(BEError be) throws SQLException {
        String sql = " insert into Error values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        if (be.getM_vehicleOdinNumber() == null) {
            ps.setString(1, null);
        } else {
            ps.setInt(1, be.getM_vehicleOdinNumber().getM_odinNumber());
        }
        ps.setString(2, be.getM_filledBy());
        ps.setDate(3, be.getM_date());
        ps.setBoolean(4, be.isM_outOfOrder());
        ps.setBoolean(5, be.isM_urgent());
        ps.setString(6, be.getM_description());
        ps.setString(7, be.getM_cause());
        ps.setBoolean(8, be.isM_suitWash());
        ps.executeUpdate();

    }

    public void createInitialIncidentDetails(BEIncidentDetails be) throws SQLException {
        String sql = "insert into IncidentDetails values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, null);
        ps.setString(2, null);
        ps.setString(3, null);
        ps.setString(4, null);
        ps.setInt(5, be.getM_incident().getM_id());
        ps.setString(6, null);
        ps.setString(7, null);
        ps.setString(8, null);
        ps.setString(9, null);
        ps.setString(10, null);
        ps.setString(11, null);
        ps.executeUpdate();
    }
}
