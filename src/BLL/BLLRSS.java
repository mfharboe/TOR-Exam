package BLL;

import BE.BEIncident;
import BE.BERSS;
import DAL.RSS_ReaderAllFeed;
import DAL.RSS_ReaderNewFeed;
import GUI.GUIFireman;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class BLLRSS {

    private static BLLRSS m_instance;
    private final ArrayList<BERSS> feeds;
    
    
    private BLLRSS() {
        feeds = new ArrayList<>();
    }

    /**
     * 
     * @return current m_instance of BLLRSS 
     */
    public static BLLRSS getInstance() {
        if (m_instance == null) {
            m_instance = new BLLRSS();
        }
        return m_instance;
    }

    /**
     * Reads the whole xml file
     * @return
     * @throws MalformedURLException
     * @throws Exception 
     */
    public ArrayList<BERSS> readFeed() throws MalformedURLException, Exception {
        return RSS_ReaderAllFeed.getInstance().readRSS();
    }

    /**
     * Checks if there is a new item in the xml  file. 
     * If the read BERSS is new, it will add it to the arrayList
     * and write it to the DB. Otherwise it will just return the 
     * arrayList without doing anything to it.
     * @return arrayList of BERSSS
     */
    public BERSS getNewFeed() {
        BERSS feed = null;
        try {
            feed = RSS_ReaderNewFeed.getInstance().readRSS();
        } catch (MalformedURLException ex) {
            BLLError.getInstance().dbError();
            return null;
        } catch (IOException | ParserConfigurationException | SAXException ex) {
            BLLError.getInstance().dbError();
            return null;
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
