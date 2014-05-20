package GUI;

import BLL.BLLError;
import javax.swing.JFrame;

/**
 * Project TOR - Tid & Registreringssytem, part 1/3 Create by Morten Harboe,
 * Michael Pedersen and Andreas Mørch CS2013 2nd semester final exam: "Brand og
 * Redning, Esbjerg"
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BLLError.getInstance().register(MessageDialog.getInstance());
        JFrame main = GUIFireman.getInstance();
        main.setVisible(true);
        //new Thread(new RSSThread()).start();
    }

}
