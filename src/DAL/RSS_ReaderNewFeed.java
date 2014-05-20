/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import BE.BERSS;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RSS_ReaderNewFeed {

    private static RSS_ReaderNewFeed m_instance;
    private final String URL_STRING = "http://www.odin.dk/RSS/RSS.aspx?beredskabsID=2d58cb9b-3219-42f7-885d-3905cec3c40e";
    private final String NODE_DESCRIPTION = "description";
    private final String NODE_ITEM = "item";
    private final String NODE_COMMENT = "comments";
    private final String NODE_DATE = "pubDate";
    private final String NODE_START = "Førstemelding: ";
    private final String NODE_ESBJERG = " Station: Esbjerg";
    private final String EMPTY = "";
    private final URL url;
    private BERSS currentFeed;
    int count = 0;

    private RSS_ReaderNewFeed() throws MalformedURLException {
        url = new URL(URL_STRING);
        currentFeed = null;
    }

    public static RSS_ReaderNewFeed getInstance() throws MalformedURLException {
        if (m_instance == null) {
            m_instance = new RSS_ReaderNewFeed();
        }
        return m_instance;
    }

    public BERSS readRSS() throws IOException, ParserConfigurationException, SAXException {
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
                if (message.contains(NODE_ESBJERG)) {
                    message = message.replace(NODE_START, EMPTY);
                    message = message.replace(NODE_ESBJERG, EMPTY);
                    if (currentFeed == null) {
                        currentFeed = new BERSS(count, message, comment, date);
                    } else {
                        if (!currentFeed.getM_date().equals(date)) {
                            currentFeed = new BERSS(count, message, comment, date);
                        }
                    }
                    count++;
                    return currentFeed;
                }
            }
        }
        return currentFeed;
    }
}
