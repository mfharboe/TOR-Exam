package DAL.Intefaces;

import BE.BEIncident;
import BE.BEIncidentDetails;
import java.sql.SQLException;

public interface IDALUpdate {

    /**
     * Updates an Incident row in the DB
     *
     * @param incident
     * @throws SQLException
     */
    void updateIncident(BEIncident incident) throws SQLException;

    /**
     * Updates an InincidentDetailsidentDetails row in the DB
     *
     * @param incidentDetails
     * @throws SQLException
     */
    void updateIncidentDetails(BEIncidentDetails incidentDetails) throws SQLException;
    
}
