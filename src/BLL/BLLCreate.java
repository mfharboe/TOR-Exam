package BLL;

import BE.BEError;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BERole;
import BE.BERoleTime;
import BE.BEUsage;
import DAL.Intefaces.IDALCreate;
import java.sql.SQLException;
import java.util.ArrayList;

public class BLLCreate {

    private static BLLCreate m_instance;
    private IDALCreate dalCreate;

    private BLLCreate() {

    }

    /**
     *
     * @return current m_instance of BLLCreate
     */
    public static BLLCreate getInstance() {
        if (m_instance == null) {
            m_instance = new BLLCreate();
        }
        return m_instance;
    }

    public void setDAL(IDALCreate d) {
        dalCreate = d;
    }

    /**
     * Creates a new Incident
     *
     * @param incident
     * @return
     */
    public boolean createIncident(BEIncident incident) {   
        if (incident == null) {
            BLLError.getInstance().createIncidentError();
            return false;
        }

        try {
            dalCreate.createIncident(incident);
        } catch (SQLException ex) {
            BLLError.getInstance().createIncidentError();
            return false;
        }
        return true;
    }

    /**
     * Creates the RoleTime in the DB with the given role
     *
     * @param roleTime
     * @param roleNumber
     */
    public void createRoleOnIncident(ArrayList<BERoleTime> roleTime, int roleNumber) {
        if (roleTime == null) {
            BLLError.getInstance().functionError();
            return;
        }

        if (roleNumber > 4 || roleNumber < 1) {
            BLLError.getInstance().functionError();
            return;
        }

        for (BERoleTime tmpRoleTimes : roleTime) {
            if (tmpRoleTimes == null) {
                BLLError.getInstance().functionError();
                return;
            }
        }
        for (BERoleTime tmpRoleTimes : roleTime) {
            BERole prevRole = tmpRoleTimes.getM_role();
            for (BERole role : BLLRead.getInstance().readRoles()) {
                if (role.getM_id() == roleNumber) {
                    try {
                        tmpRoleTimes.setM_role(role);
                        dalCreate.createRoleTime(tmpRoleTimes);
                    } catch (SQLException ex) {
                        tmpRoleTimes.setM_role(prevRole);
                        BLLError.getInstance().functionError();
                        return;
                    }
                    BLLRead.getInstance().addToRoleTime(tmpRoleTimes);
                    break;
                }
            }
        }
    }

    /**
     * Creates a new Usage on an Incident and adds it to the current Array
     *
     * @param usage
     */
    public void createUsage(BEUsage usage) {
        if (usage == null) {
            BLLError.getInstance().createUsageError();
            return;
        }

        try {
            dalCreate.createUsage(usage);
        } catch (SQLException ex) {
            BLLError.getInstance().createUsageError();
            return;
        }
        BLLRead.getInstance().addToUsage(usage);
    }

    /**
     * Creates a new IncidentDetails on an Incident and adds it to the current
     * Array, it is filled with null until updatet later by the TeamLeader
     *
     * @param incident
     */
    public void createInitialIncidentDetails(BEIncident incident) {
        if (incident == null) {
            BLLError.getInstance().dbError();
            return;
        }

        BEIncidentDetails details = new BEIncidentDetails(null, null, null, incident, null, null, null, null, null, null);

        try {
            BLLRead.getInstance().readIncidentDetails();
            dalCreate.createInitialIncidentDetails(details);
        } catch (SQLException ex) {
            BLLError.getInstance().dbError();
            return;
        }
        BLLRead.getInstance().addToIncidentDetails(details);
    }

    /**
     * Creates an ErrorReport
     *
     * @param error
     */
    public void createErrorReport(BEError error) {
        if (error == null) {
            BLLError.getInstance().createErrorReportError();
            return;
        }

        try {
            dalCreate.createErrorReport(error);
        } catch (SQLException ex) {
            BLLError.getInstance().createErrorReportError();
        }
    }

}
