/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.BERSS;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RSS_Reader {

    private static RSS_Reader m_instance;
    private final String URL_STRING = "http://www.odin.dk/RSS/RSS.aspx?beredskabsID=2d58cb9b-3219-42f7-885d-3905cec3c40e";
    private final String NODE_DESCRIPTION = "description";
    private final String NODE_ITEM = "item";
    private final String NODE_COMMENT = "comments";
    private final String NODE_DATE = "pubDate";
    private final String NODE_START = "Førstemelding:";
    private final String NODE_END = "Station: Esbjerg";
    private final String EMPTY = "";
    private final ArrayList<BERSS> currentFeed;
    private final URL url;
    int count = 0;

    private RSS_Reader() throws MalformedURLException {
        url = new URL(URL_STRING);
        currentFeed = new ArrayList<>();

    }

    public static RSS_Reader getInstance() throws MalformedURLException {
        if (m_instance == null) {
            m_instance = new RSS_Reader();
        }
        return m_instance;
    }

    public ArrayList<BERSS> readRSS() throws Exception {
        InputStream is = url.openStream();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName(NODE_ITEM);
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String date = element.getElementsByTagName(NODE_DATE).item(0).getTextContent();
                String comment = element.getElementsByTagName(NODE_COMMENT).item(0).getTextContent();
                String message = element.getElementsByTagName(NODE_DESCRIPTION).item(0).getTextContent();
                if (message.contains(NODE_END)) {
                    message = message.replace(NODE_START, EMPTY);
                    message = message.replace(NODE_END, EMPTY);
                    if (isFeedAlreadyRead(date)) {
                        return currentFeed;
                    }
                    count++;
                    BERSS berss = new BERSS(count, message, comment, date);
                    currentFeed.add(berss);
                    if(currentFeed.size() > 20){
                        currentFeed.remove(0);
                    }
                }
            }
        }
        return currentFeed;
    }

    private boolean isFeedAlreadyRead(String date) {
        if (currentFeed.isEmpty()) {
            return false;
        }
        
        for (BERSS rss : currentFeed) {
            if (rss.getM_date().equals(date)) {
                return true;
            }
        }
        return false;
    }
}
