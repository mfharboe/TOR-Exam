package BLL;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentType;
import BE.BEIncidentVehicle;
import BE.BERole;
import BE.BERoleTime;
import BE.BEVehicle;
import DAL.DALCreate;
import DAL.DALRead;
import DAL.DALUpdate;
import GUI.MessageDialog;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLLFireman {

    private static BLLFireman m_instance;
    ArrayList<BEIncidentType> incidentTypes;
    ArrayList<BEIncident> incidents;
    ArrayList<BEIncident> incompleteIncidents;
    ArrayList<BEFireman> firemen;
    ArrayList<BEVehicle> vehicles;
    ArrayList<BERole> roles;
    ArrayList<BERoleTime> roletimes;

    private BLLFireman() {
    }

    /**
     * @return m_instance of BLLFireman
     */
    public static BLLFireman getInstance() {
        if (m_instance == null) {
            m_instance = new BLLFireman();
        }
        return m_instance;
    }

    /**
     * @return ArrayList of Firemen
     */
    public ArrayList<BEFireman> readAllFiremen() {
        if (firemen == null) {
            try {
                firemen = DALRead.getInstance().readFiremen();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return firemen;

    }

    /**
     * @return ArrayList of Vehicles
     */
    public ArrayList<BEVehicle> readAllVehicles() {
        if (vehicles == null) {
            try {
                vehicles = DALRead.getInstance().readVehicles();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return vehicles;
    }

    /**
     * @return ArrayList of IncidentTypes
     */
    public ArrayList<BEIncidentType> readAllIncidentTypes() {
        if (incidentTypes == null) {
            try {
                incidentTypes = DALRead.getInstance().readIncidentTypes();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return incidentTypes;
    }

    /**
     * @return ArrayList of Incidents
     */
    public ArrayList<BEIncident> readAllIncidents() {
        if (incidents == null) {
            try {
                incidents = DALRead.getInstance().readIncidents();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return incidents;
    }

    /**
     *
     * @return ArrayList of Roles
     */
    public ArrayList<BERole> readAllRoles() {
        if (roles == null) {
            try {
                roles = DALRead.getInstance().readRoles();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return roles;
    }

    /**
     *
     * @return ArrayList of RoleTimes
     */
    public ArrayList<BERoleTime> readAllRoleTimes() {
        if (roletimes == null) {
            try {
                roletimes = DALRead.getInstance().readRoleTime();
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return null;
            }
        }
        return roletimes;
    }

    /**
     * Creates a new Incident
     *
     * @param incident
     */
    public void createIncident(BEIncident incident) {
        try {
            DALCreate.getInstance().createIncident(incident);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
        }
    }

    /**
     * Creates a new BM on an Incident and adds it to the currenct Array
     *
     * @param roleTime
     */
    public void createBMOnIncident(BERoleTime roleTime) {
        int chk = 0;
        for (BERole role : readAllRoles()) {
            if (role.isM_isFireman()) {
                try {
                    roleTime.setM_role(role);
                    DALCreate.getInstance().createRoleTime(roleTime);
                    chk = 1;
                } catch (SQLException ex) {
                    //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().functionDialog(); //MÅ IKKE VÆRE HER
                    chk = 0;
                }
                if(chk == 1)
                    updateIncidentVehicleAmount(roleTime);
                
            }
        }
    }

    /**
     * Creates a new CH on an Incident and adds it to the currenct Array
     *
     * @param roleTime
     */
    public void createCHOnIncident(BERoleTime roleTime) {
        for (BERole role : readAllRoles()) {
            if (role.isM_isDriver()) {
                try {
                    roleTime.setM_role(role);
                    DALCreate.getInstance().createRoleTime(roleTime);
                } catch (SQLException ex) {
                    //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().functionDialog(); //MÅ IKKE VÆRE HER
                    return;
                }
                updateIncidentVehicleAmount(roleTime);
                break;
            }
        }
    }

    /**
     * Creates a new ST on an Incident and adds it to the currenct Array
     *
     * @param roleTime
     */
    public void createSTOnIncident(BERoleTime roleTime) {
        for (BERole role : readAllRoles()) {
            if (role.isM_isStation()) {
                try {
                    roleTime.setM_role(role);
                    DALCreate.getInstance().createRoleTime(roleTime);
                } catch (SQLException ex) {
                    //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().stationDialog(); //MÅ IKKE VÆRE HER
                    return;
                }
                roletimes.add(roleTime);
            }
        }
    }

    /**
     * Creates an new HL on an Incident and adds it to the currenct Array
     *
     * @param roleTime
     */
    public void createHLOnIncident(BERoleTime roleTime) {
        for (BERole role : readAllRoles()) {
            if (role.isM_isLeader()) {
                try {
                    roleTime.setM_role(role);
                    DALCreate.getInstance().createRoleTime(roleTime);
                } catch (SQLException ex) {
                    //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().functionDialog(); //MÅ IKKE VÆRE HER
                    return;
                }
                updateIncidentVehicleAmount(roleTime);
                break;
            }
        }
    }

    public void updateIncidentVehicleAmount(BERoleTime roletime) {
        int tmp;
        int chk = 0;
        ArrayList<BEIncidentVehicle> arrayIncidentVehicle = BLLTeamLeader.getInstance().readIncidentVehicles();
        if (arrayIncidentVehicle.isEmpty()) {
            BEIncidentVehicle incidentvehicle = new BEIncidentVehicle(roletime.getM_incident(), roletime.getM_vehicle(), null, 1, true);
            try {
                DALCreate.getInstance().createIncidentVehicle(incidentvehicle);
            } catch (SQLException ex) {
                //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                return;
            }
            roletimes.add(roletime);
            BLLTeamLeader.getInstance().addToIncidentVehicles(incidentvehicle);
            return;
        }

        for (BEIncidentVehicle beincidentvehicle : arrayIncidentVehicle) {
            if (roletime.getM_incident().getM_id() == beincidentvehicle.getM_incident().getM_id()
                    && roletime.getM_vehicle().getM_odinNumber() == beincidentvehicle.getM_vehicle().getM_odinNumber()) {

                tmp = beincidentvehicle.getM_amountCrew();
                tmp++;
                beincidentvehicle.setM_amountCrew(tmp);
                if (beincidentvehicle.getM_amountCrew() == beincidentvehicle.getM_vehicle().getM_seats()) {
                    beincidentvehicle.setM_isDiverged(false);

                } else if (beincidentvehicle.getM_amountCrew() > beincidentvehicle.getM_vehicle().getM_seats()) {
                    MessageDialog.getInstance().ErrorCarSeatsFilled();
                    chk = 1;
                    return;
                }
                try {
                    DALUpdate.getInstance().updateIncidentVehicle(beincidentvehicle);
                } catch (SQLException ex) {
//                    Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                    MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
                }
                roletimes.add(roletime);
                chk = 1;

            }
        }
        if (chk == 0) {
            BEIncidentVehicle incidentvehicle = new BEIncidentVehicle(roletime.getM_incident(), roletime.getM_vehicle(), null, 1, true);
            try {
                DALCreate.getInstance().createIncidentVehicle(incidentvehicle);
            } catch (SQLException ex) {
//                Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
                MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            }
            roletimes.add(roletime);
            BLLTeamLeader.getInstance().addToIncidentVehicles(incidentvehicle);
        }

    }

    /**
     * Updates an Incident
     *
     * @param incident
     */
    public void updateIncident(BEIncident incident) {
        try {
            DALUpdate.getInstance().updateIncident(incident);
        } catch (SQLException ex) {
            //Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        MessageDialog.getInstance().incidentUpdate(); //MÅ IKKE VÆRE HER
    }

    /**
     * Sets the RoleTime for an Incident
     *
     * @param incident
     * @return ArrayList of RoleTime
     */
    public ArrayList<BERoleTime> incidentToRoleTime(BEIncident incident) {
        ArrayList<BERoleTime> beroletime = new ArrayList<>();

        for (BERoleTime be : readAllRoleTimes()) {
            if (be.getM_incident().getM_id() == incident.getM_id()) {
                beroletime.add(be);
            }
        }

        return beroletime;
    }

}
