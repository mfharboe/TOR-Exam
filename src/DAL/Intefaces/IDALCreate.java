
package DAL.Intefaces;

import BE.BEError;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BERoleTime;
import BE.BEUsage;
import java.sql.SQLException;

public interface IDALCreate {

    /**
     * Creates a new ErrorReport row in the DB
     *
     * @param errorReport
     * @throws SQLException
     */
    void createErrorReport(BEError errorReport) throws SQLException;

    /**
     * Creates a new Inincidentident row in the DB
     *
     * @param incident
     * @throws SQLException
     */
    void createIncident(BEIncident incident) throws SQLException;

    /**
     * Creates a new IncidentDetails row in the DB
     *
     * @param initialIncidentDetails
     * @throws SQLException
     */
    void createInitialIncidentDetails(BEIncidentDetails initialIncidentDetails) throws SQLException;

    /**
     * Creates a new Role/Time row in the DB
     *
     * @param roletime
     * @throws SQLException
     */
    void createRoleTime(BERoleTime roletime) throws SQLException;

    /**
     * Creates a new Usage row in the DB
     *
     * @param usage
     * @throws SQLException
     */
    void createUsage(BEUsage usage) throws SQLException;
    
}
