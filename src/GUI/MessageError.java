package GUI;

import BLL.BLLError;
import ObserverPattern.IObserver;
import javax.swing.JOptionPane;

public class MessageError implements IObserver {

    private static MessageError m_instance;

    private MessageError() {
    }

    public static MessageError getInstance() {
        if (m_instance == null) {
            m_instance = new MessageError();
        }
        return m_instance;
    }

    @Override
    public void update() {
        JOptionPane.showMessageDialog(null, BLLError.getInstance().getError());
        BLLError.getInstance().resetError();
    }
}
