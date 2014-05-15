package BLL;

import BE.BEIncident;
import BE.BERSS;
import DAL.RSS_Reader;
import DAL.RSS_Reader1;
import GUI.GUIFireman;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BLLRSS {

    private static BLLRSS m_instance;
    private ArrayList<BERSS> feeds;
    
    private BLLRSS() {
        feeds = new ArrayList<>();
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

    public BERSS getNewFeed() {
        BERSS feed = null;
        try {
            feed = RSS_Reader1.getInstance().readRSS();
        } catch (MalformedURLException ex) {
            Logger.getLogger(BLLRSS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(BLLRSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!feeds.contains(feed)){
            feeds.add(feed);
            BEIncident incident = BLLAdapter.getInstance().RSStoIncident(feed);
            BLLCreate.getInstance().createIncident(incident);
            BLLCreate.getInstance().createInitialIncidentDetails(incident);
            GUIFireman.getInstance().addToIncidentCombo(incident);
        }
        return feed;
    }
}
