package GUI;

import BLL.BLLError;
import javax.swing.JOptionPane;

public class MessageError {

    private static MessageError m_instance;
    private MessageError() {}
    
    public static MessageError getInstance(){
        if(m_instance == null)
            m_instance = new MessageError();
        return m_instance;
    }
 
    /**
     * Checks if there is an error and if true, it prints out the error
     */
    public void printError() {
        if (BLLError.getInstance().isError()) {
            JOptionPane.showMessageDialog(null, BLLError.getInstance().getError());
            BLLError.getInstance().resetError();
        }
    }
}
