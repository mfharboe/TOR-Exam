package DAL;
 
import DAL.Intefaces.IDALCreate;
import BE.BEError;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentType;
import BE.BERoleTime;
import BE.BEUsage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DALCreate implements IDALCreate{

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
     * Creates a new Inincidentident row in the DB
     *
     * @param incident
     * @throws SQLException
     */
    @Override
        public void createIncident(BEIncident incident) throws SQLException {
        String sql = "insert into Incident values (?,?,?,?,?); ";
        PreparedStatement ps = m_connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, incident.getM_incidentName());
        ps.setDate(2, incident.getM_date());
        ps.setTime(3, incident.getM_time());
        ps.setInt(4, incident.getM_incidentType().getM_id());
        ps.setBoolean(5, incident.isM_isDone());
        ps.executeUpdate();
        ResultSet result = ps.getGeneratedKeys();
        while (result.next()) {
            incident.setM_id(result.getInt(1));
        }
    }

    /**
     * Creates a new Role/Time row in the DB
     *
     * @param roletime
     * @throws SQLException
     */
    @Override
        public void createRoleTime(BERoleTime roletime) throws SQLException {
        String sql = "insert into [Role/Time] values (?,?,?,?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setInt(1, roletime.getM_incident().getM_id());
        ps.setInt(2, roletime.getM_fireman().getM_id());
        ps.setBoolean(3, roletime.isM_isOnStation());
        ps.setInt(4, roletime.getM_role().getM_id());

        if (roletime.getM_vehicle() == null) {
            ps.setString(5, null);
        } else {
            ps.setInt(5, roletime.getM_vehicle().getM_odinNumber());
        }
        ps.setInt(6, roletime.getM_hours());
        ps.executeUpdate();
    }

    /**
     * Creates a new Usage row in the DB
     *
     * @param usage
     * @throws SQLException
     */
    @Override
        public void createUsage(BEUsage usage) throws SQLException {
        String sql = "insert into Usage values (?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, usage.getM_material().getM_id());
        ps.setInt(2, usage.getM_amount());
        ps.setInt(3, usage.getM_incident().getM_id());
        ps.executeUpdate();
        ResultSet result = ps.getGeneratedKeys();
        while (result.next()) {
            usage.setM_id(result.getInt(1));
        }
    }

    /**
     * Creates a new ErrorReport row in the DB
     *
     * @param errorReport
     * @throws SQLException
     */
    @Override
        public void createErrorReport(BEError errorReport) throws SQLException {
        String sql = " insert into Error values (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        if (errorReport.getM_vehicleOdinNumber() == null) {
            ps.setString(1, null);
        } else {
            ps.setInt(1, errorReport.getM_vehicleOdinNumber().getM_odinNumber());
        }
        ps.setString(2, errorReport.getM_filledBy());
        ps.setDate(3, errorReport.getM_date());
        ps.setBoolean(4, errorReport.isM_outOfOrder());
        ps.setBoolean(5, errorReport.isM_urgent());
        ps.setString(6, errorReport.getM_description());
        ps.setString(7, errorReport.getM_cause());
        ps.setBoolean(8, errorReport.isM_suitWash());
        ps.executeUpdate();

    }

    /**
     * Creates a new IncidentDetails row in the DB
     *
     * @param initialIncidentDetails
     * @throws SQLException
     */
    @Override
        public void createInitialIncidentDetails(BEIncidentDetails initialIncidentDetails) throws SQLException {
        String sql = "insert into IncidentDetails values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = m_connection.prepareStatement(sql);
        ps.setString(1, null);
        ps.setString(2, null);
        ps.setString(3, null);
        ps.setInt(4, initialIncidentDetails.getM_incident().getM_id());
        ps.setString(5, null);
        ps.setString(6, null);
        ps.setString(7, null);
        ps.setString(8, null);
        ps.setString(9, null);
        ps.setString(10, null);
        ps.executeUpdate();
    }
}
