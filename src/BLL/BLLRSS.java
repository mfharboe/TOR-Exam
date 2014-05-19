package BLL;

import BE.BEIncident;
import BE.BERSS;
import DAL.RSS_Reader;
import DAL.RSS_Reader1;
import GUI.GUIFireman;
import GUI.MessageError;
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
        return RSS_Reader.getInstance().readRSS();
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
            feed = RSS_Reader1.getInstance().readRSS();
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
            MessageError.getInstance().printError();
            BLLCreate.getInstance().createIncident(incident);
            MessageError.getInstance().printError();
            BLLCreate.getInstance().createInitialIncidentDetails(incident);
            MessageError.getInstance().printError();
            GUIFireman.getInstance().addToIncidentCombo(incident);
        }
        return feed;
    }
}
