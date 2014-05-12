package BLL;

import BE.BERSS;
import DAL.RSS_Reader;
import java.net.MalformedURLException;
import java.util.ArrayList;

public class BLLRSS {

    private static BLLRSS m_instance;
    private ArrayList<BERSS> newFeed;
    private ArrayList<BERSS> prevFeed;

    private BLLRSS() {

    }

    public static BLLRSS getInstance() {
        if (m_instance == null) {
            m_instance = new BLLRSS();
        }
        return m_instance;
    }

    public ArrayList<BERSS> readFeed() throws MalformedURLException, Exception {

        return RSS_Reader.getInstance().readRSS();
    }

    public ArrayList<BERSS> getNewFeed() {

        return prevFeed;
    }
}
