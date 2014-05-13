package BLL;

import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BERoleTime;
import BE.BESalary;
import BE.BEUsage;
import java.util.ArrayList;

public class BLLAdapter {

    private static BLLAdapter m_instance;

    private BLLAdapter() {

    }

    public static BLLAdapter getInstance() {
        if (m_instance == null) {
            m_instance = new BLLAdapter();
        }
        return m_instance;
    }

    /**
     * Sets the RoleTime for an Incident
     *
     * @param incident
     * @return ArrayList of RoleTime
     */
    public ArrayList<BERoleTime> incidentToRoleTime(BEIncident incident) {
        ArrayList<BERoleTime> beroletime = new ArrayList<>();

        for (BERoleTime be : BLLRead.getInstance().readAllRoleTimes()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                beroletime.add(be);
            }
        }
        return beroletime;
    }

    /**
     * Checks and adds Usage for a given Incident
     *
     * @param incident
     * @return ArrayList of BEUsage
     */
    public ArrayList<BEUsage> incidentToUsage(BEIncident incident) {
        ArrayList<BEUsage> beusage = new ArrayList<>();
        for (BEUsage be : BLLRead.getInstance().readUsages()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                beusage.add(be);
            }
        }
        return beusage;
    }

    /**
     * Checks for IncidentDetails for a given Incident
     *
     * @param incident
     * @return The BEIncidentDetails or null if none is there
     */
    public BEIncidentDetails incidentToIncidentDetails(BEIncident incident) {
        for (BEIncidentDetails be : BLLRead.getInstance().readIncidentDetails()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                return be;
            }
        }
        return null;
    }

    private final int HL = 3;
    
    private final int GUARD = 2;
    private final int GUARD_HL = 443;
    private final int GUARD_BM = 442;
    
    private final int EXECISE = 6;
    private final int EXERCISE_HL = 439;
    private final int EXERCISE_BM = 449;
   
    private final int FIRE_HL = 447;
    private final int FIRE_BM = 446;

    /**
     * Goes through the incidentType and Role Id to get the correct Salary Id
     * @param roleTime
     * @return BESalary for the riven roleTime
     */
    public BESalary roleTimeToSalary(BERoleTime roleTime) {
        int typeId = roleTime.getM_incident().getM_incidentType().getM_id();
        int roleId = roleTime.getM_role().getM_id();

        if (typeId == GUARD) {
            if (roleId == HL) {
                for (BESalary be : BLLRead.getInstance().readSalary()) {
                    if (be.getM_salaryCode() == GUARD_HL) {
                        return be;
                    }
                }
            } else {
                for (BESalary be : BLLRead.getInstance().readSalary()) {
                    if (be.getM_salaryCode() == GUARD_BM) {
                        return be;
                    }
                }
            }

        } else if (typeId == EXECISE) {
            if (roleId == HL) {
                for (BESalary be : BLLRead.getInstance().readSalary()) {
                    if (be.getM_salaryCode() == EXERCISE_HL) {
                        return be;
                    }
                }
            } else {
                for (BESalary be : BLLRead.getInstance().readSalary()) {
                    if (be.getM_salaryCode() == EXERCISE_BM) {
                        return be;
                    }
                }
            }
        } else {
            if (roleId == HL) {
                for (BESalary be : BLLRead.getInstance().readSalary()) {
                    if (be.getM_salaryCode() == FIRE_HL) {
                        return be;
                    }
                }

            } else {
                for (BESalary be : BLLRead.getInstance().readSalary()) {
                    if (be.getM_salaryCode() == FIRE_BM) {
                        return be;
                    }
                }
            }
        }
        return null;
    }
}
