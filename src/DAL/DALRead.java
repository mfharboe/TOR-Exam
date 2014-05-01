package DAL;

import BE.BEAlarm;
import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentType;
import BE.BERole;
import BE.BERoleTime;
import BE.BEVehicle;
import BE.BEZipcode;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

public class DALRead {

    Connection m_connection;
    private static DALRead m_instance;

    ArrayList<BEIncidentType> resIncidentType;

    private DALRead() {
        m_connection = DB_Connection.getInstance().getConnection();
    }

    /**
     * @return current instance of DALRead
     */
    public static DALRead getInstance() {
        if (m_instance == null) {
            m_instance = new DALRead();
        }
        return m_instance;
    }

    /**
     * Creates an ArrayList of Incident types
     *
     * @return ArrayList of Incident Types
     * @throws SQLException
     */
    public ArrayList<BEIncidentType> readIncidentTypes() throws SQLException {
        if (resIncidentType == null) {
            resIncidentType = new ArrayList<>();
            Statement stm = m_connection.createStatement();
            stm.execute("select * from IncidentType");
            ResultSet result = stm.getResultSet();
            while (result.next()) {
                int id = result.getInt("id");
                String description = result.getString("description");
                BEIncidentType be = new BEIncidentType(id, description);
                resIncidentType.add(be);
            }
        }
        return resIncidentType;
    }

    /**
     * Creates an ArrayList of Alarms
     *
     * @return ArrayList of Alarms
     * @throws SQLException
     */
    public ArrayList<BEAlarm> readAlarms() throws SQLException {
        ArrayList<BEAlarm> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Alarm");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String description = result.getString("description");
            BEAlarm be = new BEAlarm(id, description);
            res.add(be);
        }
        return res;
    }

    /**
     * Creates an ArrayList of Incidents
     *
     * @return ArrayList of Incidents
     * @throws SQLException
     */
    public ArrayList<BEIncident> readIncidents() throws SQLException {
        ArrayList<BEIncident> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Incident");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String incidentName = result.getString("incidentName");
            Date date = result.getDate("date");
            Time time = result.getTime("startTime");
            int incidentTypeId = result.getInt("incidentTypeId");
            BEIncidentType refIncidentType = null;
            for (BEIncidentType beincidenttype : readIncidentTypes()) {
                if (beincidenttype.getM_id() == incidentTypeId) {
                    refIncidentType = beincidenttype;
                }
            }
            boolean isDone = result.getBoolean("isDone");
            BEIncident be = new BEIncident(id, incidentName, date, time, refIncidentType, isDone);
            res.add(be);
        }
        return res;
    }

    /**
     * Creates an ArrayList of Vehicles
     *
     * @return an ArrayList of Vehicles
     * @throws SQLException
     */
    public ArrayList<BEVehicle> readVehicles() throws SQLException {
        ArrayList<BEVehicle> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Vehicle");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int odinNumber = result.getInt("odinNumber");
            String registrationNumber = result.getString("registrationNumber");
            String brand = result.getString("brand");
            String model = result.getString("model");
            String description = result.getString("description");
            boolean isReady = result.getBoolean("isReady");
            BEVehicle be = new BEVehicle(odinNumber, registrationNumber, brand, model, description, isReady);
            res.add(be);
        }
        return res;
    }

    /**
     * Creates an ArrayList of Firemen
     *
     * @return ArrayList of Firemen
     * @throws SQLException
     */
    public ArrayList<BEFireman> readFiremen() throws SQLException {
        ArrayList<BEFireman> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Fireman order by lastName, firstname");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String address = result.getString("address");
            int zip = result.getInt("zipCode");
            BEZipcode refZipCode = null;
            for (BEZipcode bezipcode : readZipcodes()) {
                if (bezipcode.getM_zipCode() == zip) {
                    refZipCode = bezipcode;
                }
            }
            int phone = result.getInt("phone");
            int paymentNumber = result.getInt("paymentNumber");
            boolean isTeamLeader = result.getBoolean("isTeamLeader");
            String photoPath = result.getString("photoPath");
            String cpr = result.getString("cpr");
            BEFireman be = new BEFireman(id, firstName, lastName, address, refZipCode, phone, paymentNumber, isTeamLeader, photoPath, cpr);
            res.add(be);
        }
        return res;
    }

    /**
     * Creates an ArrayList of Zipcodes
     *
     * @return ArrayList of Zipcodes
     * @throws SQLException
     */
    public ArrayList<BEZipcode> readZipcodes() throws SQLException {
        ArrayList<BEZipcode> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from [zip/city]");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int zipcode = result.getInt("zipcode");
            String city = result.getString("city");
            BEZipcode be = new BEZipcode(zipcode, city);
            res.add(be);
        }
        return res;
    }

    public ArrayList<BERole> readRoles() throws SQLException {
        ArrayList<BERole> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from Role");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int id = result.getInt("id");
            String description = result.getString("description");
            boolean isFireman = result.getBoolean("isFireman");
            boolean isDriver = result.getBoolean("isDriver");
            boolean isLeader = result.getBoolean("isLeader");
            boolean isStation = result.getBoolean("isStation");
            BERole be = new BERole(id, description, isFireman, isDriver, isLeader, isStation);
            res.add(be);
        }
        return res;
    }
    public ArrayList<BERoleTime> readRoleTime() throws SQLException {
        ArrayList<BERoleTime> res = new ArrayList<>();
        Statement stm = m_connection.createStatement();
        stm.execute("select * from [Role/Time]");
        ResultSet result = stm.getResultSet();
        while (result.next()) {
            int incidentid = result.getInt("incidentId");
            BEIncident refIncident = null;
            for(BEIncident be : readIncidents())
                if(be.getM_id() == incidentid)
                    refIncident = be;
            int firemanid = result.getInt("firemanId");
            BEFireman refFireman = null;
            for(BEFireman be : readFiremen())
                if(be.getM_id() == firemanid)
                    refFireman = be;
            int roleid = result.getInt("roleId");
            BERole refRole = null;
            for(BERole be : readRoles())
                if(be.getM_id() == roleid)
                    refRole = be;
            int vehicleodinnumber = result.getInt("vehicleOdinNumber");
            BEVehicle refVehicle = null;
            for(BEVehicle be : readVehicles())
                if(be.getM_odinNumber() == vehicleodinnumber)
                    refVehicle = be;
            int hours = result.getInt("hours");
            BERoleTime be = new BERoleTime(refFireman, refIncident, refRole, refVehicle, hours);
            
            
            res.add(be);
        }
        return res;
    }
}
