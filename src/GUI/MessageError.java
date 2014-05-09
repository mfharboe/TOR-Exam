package GUI;
public class MessageError {
    private static MessageError m_instance;
    
    private MessageError(){}
    
    public static MessageError getInstance(){
        if(m_instance == null)
            m_instance = new MessageError();
        return m_instance;
    }
    
}
