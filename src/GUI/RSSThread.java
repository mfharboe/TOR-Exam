package GUI;

import BE.BERSS;
import BLL.BLLRSS;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RSSThread implements Runnable {
    private ArrayList<BERSS> currentFeed;

    @Override
    public void run() {
        currentFeed = new ArrayList<>();
        while (true) {
            try {
                currentFeed = BLLRSS.getInstance().readFeed();
                printRSS();
            } catch (Exception ex) {
                Logger.getLogger(RSSThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(RSSThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void printRSS() {
        for (BERSS feed : currentFeed) {
            System.out.println(feed.getM_id());
        }
    }

}
