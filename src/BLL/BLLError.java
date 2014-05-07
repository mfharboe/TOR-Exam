package BLL;

import BE.BEError;
import DAL.DALCreate;
import GUI.MessageDialog;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLLError {

    private static BLLError m_instance;

    private BLLError() {

    }

    /**
     *
     * @return m_instance of BLLError
     */
    public static BLLError getInstance() {
        if (m_instance == null) {
            m_instance = new BLLError();
        }
        return m_instance;
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
            //Logger.getLogger(BLLError.class.getName()).log(Level.SEVERE, null, ex);
            MessageDialog.getInstance().DataBaseError(); //MÅ IKKE VÆRE HER
            return;
        }
        MessageDialog.getInstance().ErrorConfirmMessageApproved(); //MÅ IKKE VÆRE HER
    }
}
