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
import DAL.DB_Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morten
 */
public class BLLFireman {
    
    private static BLLFireman m_instance;
    
    public static BLLFireman getInstance() {
        if (m_instance == null) {
            m_instance = new BLLFireman();
        }
        return m_instance;
    }
    
    public ArrayList<BEFireman> readAllFiremen(){
        ArrayList<BEFireman> befireman = null;
        try {
            befireman = DALRead.getInstance().readFiremen();
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return befireman;
        
    }
    
    public ArrayList<BEVehicle> readAllVehicles(){
            ArrayList<BEVehicle> bevehicle = null;
        try {
            bevehicle = DALRead.getInstance().readVehicles();
            
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bevehicle;
    }
    
    public ArrayList<BEIncidentType> readAllIncidentTypes(){
            ArrayList<BEIncidentType> beincidenttype = null;
        try {
            beincidenttype = DALRead.getInstance().readIncidentTypes();
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        return beincidenttype;
    }
    
    public ArrayList<BEIncident> readAllIncidents(){
        ArrayList<BEIncident> beincidentTemp = null;
        ArrayList<BEIncident>beincident = new ArrayList<>();
        try {
            beincidentTemp = DALRead.getInstance().readIncidents();
        } catch (SQLException ex) {
            Logger.getLogger(BLLFireman.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(BEIncident c : beincidentTemp)
            if(!c.isM_isDone())
                beincident.add(c);
        return beincident;
        
    }
   
    
}
