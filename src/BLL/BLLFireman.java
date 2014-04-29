/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentType;
import BE.BEVehicle;
import DAL.DALRead;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLLFireman {
    
    private static BLLFireman m_instance;
    
    /**
     * @return current instance of BLLFireman 
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
    public ArrayList<BEFireman> readAllFiremen(){
        ArrayList<BEFireman> befireman = null;
        try {
            befireman = DALRead.getInstance().getFiremen();
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return befireman;
        
    }
    
    /**
     * @return ArrayList of Vehicles 
     */
    public ArrayList<BEVehicle> readAllVehicles(){
            ArrayList<BEVehicle> bevehicle = null;
        try {
            bevehicle = DALRead.getInstance().getVehicles();
            
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bevehicle;
    }
    
    /**
     * @return ArrayList of IncidentTypes 
     */
    public ArrayList<BEIncidentType> readAllIncidentTypes(){
            ArrayList<BEIncidentType> beincidenttype = null;
        try {
            beincidenttype = DALRead.getInstance().getIncidentTypes();
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beincidenttype;
    }
    
    /**
     * @return all incidents that are not done (not completed by a teamLeader) 
     */
    public ArrayList<BEIncident> readAllIncidents(){
        ArrayList<BEIncident> beincidentTemp = null;
        ArrayList<BEIncident>beincident = new ArrayList<>();
        try {
            beincidentTemp = DALRead.getInstance().getIncidents();
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(BEIncident c : beincidentTemp)
            if(!c.isM_isDone())
                beincident.add(c);
        return beincident;
        
    }
   
    
}
