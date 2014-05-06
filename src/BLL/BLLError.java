/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL;

import BE.BEError;
import DAL.DALCreate;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Morten
 */
public class BLLError {
    
    private static  BLLError m_instance;
    
    private BLLError(){
        
    }
    
    public static BLLError getInstance(){
        if(m_instance == null)
            m_instance = new BLLError();
        return m_instance;
    }
    
    public void createErrorReport(BEError be){
        try {
            DALCreate.getInstance().createErrorReport(be);
        } catch (SQLException ex) {
            Logger.getLogger(BLLError.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
