package BLL;

import BE.BEError;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BERole;
import BE.BERoleTime;
import BE.BEUsage;
import DAL.DALCreate;
import DAL.Intefaces.IDALCreate;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLLCreate {

    private static BLLCreate m_instance;
    private final IDALCreate dalCreate;

    private BLLCreate() {
        dalCreate = DALCreate.getInstance();
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

    /**
     * Creates a new Incident
     *
     * @param incident
     * @return
     */
    public boolean createIncident(BEIncident incident) {
        try {
            dalCreate.createIncident(incident);
            DALCreate.getInstance().createIncident(incident);
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
        BERole tmpPrevRole = null;
        for (BERoleTime tmpRoleTimes : roleTime) {
            for (BERole role : BLLRead.getInstance().readAllRoles()) {
                tmpPrevRole = tmpRoleTimes.getM_role();
                if (role.getM_id() == roleNumber) {
                    tmpRoleTimes.setM_role(role);
                    try {
                        DALCreate.getInstance().createRoleTime(tmpRoleTimes);
                    } catch (SQLException ex) {
                        tmpRoleTimes.setM_role(tmpPrevRole);
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
        try {
            DALCreate.getInstance().createUsage(usage);
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
        BEIncidentDetails details = new BEIncidentDetails(null, null, null, incident, null, null, null, null, null, null);
        try {
            BLLRead.getInstance().readIncidentDetails();
            DALCreate.getInstance().createInitialIncidentDetails(details);
        } catch (SQLException ex) {
            Logger.getLogger(BLLCreate.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            DALCreate.getInstance().createErrorReport(error);
        } catch (SQLException ex) {
            BLLError.getInstance().createErrorReportError();
        }
    }

}
