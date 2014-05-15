package GUI;

import BE.BERSS;
import BLL.BLLRSS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSSThread implements Runnable {

    private ArrayList<BERSS> currentFeed;
    private BERSS m_RSS;
    private final int SECONDS_TO_SLEEP = 5;
    @Override
    public void run() {
        currentFeed = new ArrayList<>();
        m_RSS = null;
        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(RSSThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                //currentFeed = BLLRSS.getInstance().readFeed();
               // printArray();
                if (m_RSS != null) {
                    printRSSFør();
                }
                m_RSS = BLLRSS.getInstance().getNewFeed();
                printRSSAfter();
            } catch (Exception ex) {
                Logger.getLogger(RSSThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(SECONDS_TO_SLEEP * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RSSThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void printRSSFør() {
        System.out.println("BEFORE");
        System.out.println(m_RSS.getM_id() + "  " + m_RSS.getM_date());
    }

    private void printRSSAfter() {
        System.out.println("AFTER");
        System.out.println(m_RSS.getM_id() + "  " + m_RSS.getM_date());
    }

    private void printArray(){
        for(BERSS be : currentFeed){
            System.out.println(be.getM_comment());
        }
    }
}
