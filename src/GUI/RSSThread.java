package GUI;

import BE.BERSS;
import BLL.BLLError;
import BLL.BLLRSS;
import java.util.ArrayList;

public class RSSThread implements Runnable {

    private ArrayList<BERSS> currentFeed;
    private BERSS m_RSS;
    private final int SECONDS_TO_SLEEP = 60 * 5;
    private boolean doRunRSS = true;

    @Override
    public void run() {
        currentFeed = new ArrayList<>();
        m_RSS = null;

        try {
            Thread.sleep(20 * 1000);
        } catch (InterruptedException ex) {
            BLLError.getInstance().RSSError();
            doRunRSS = false;
        }
        while (doRunRSS) {
            try {
                m_RSS = BLLRSS.getInstance().getNewFeed();
            } catch (Exception ex) {
                BLLError.getInstance().RSSError();
                break;
            }
            try {
                Thread.sleep(SECONDS_TO_SLEEP * 1000);
            } catch (InterruptedException ex) {
                BLLError.getInstance().RSSError();
                break;
            }
        }
    }
}
