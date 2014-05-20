package DAL.Intefaces;

import BE.BERoleTime;
import BE.BEUsage;
import java.sql.SQLException;

public interface IDALDelete {

    /**
     * Deletes a fireman entry in the RoleTime table
     * @param roleTime
     * @throws SQLException
     */
    void deleteFiremanFromRoleTime(BERoleTime roleTime) throws SQLException;

    /**
     * Deletes the material entry from the Usage table
     * @param usage
     * @throws SQLException
     */
    void deleteMaterialFromUsage(BEUsage usage) throws SQLException;
    
}
