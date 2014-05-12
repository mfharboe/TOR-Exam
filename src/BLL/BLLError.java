package BLL;

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

  
}
