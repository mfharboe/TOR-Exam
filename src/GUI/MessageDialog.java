package GUI;

import javax.swing.JOptionPane;

public class MessageDialog {

    private static MessageDialog m_instance;

    private MessageDialog() {

    }

    /**
     *
     * @return m_instance of MessageDialog
     */
    public static MessageDialog getInstance() {
        if (m_instance == null) {
            m_instance = new MessageDialog();
        }
        return m_instance;
    }

    //FIREMAN MESSAGES----------------------------------------------------------
   public void ErrorCarSeatsFilled(){
        JOptionPane.showMessageDialog(null, "Bilen er allerede fyldt op");
    
    }
    
    public void incidentUpdate() {
        JOptionPane.showMessageDialog(null, "Dine ændringer blev registreret");
    }

    public void saveDialog() {
        JOptionPane.showMessageDialog(null, "Udfyld venligst alle informationer");
    }

    public void stationDialog() {
        JOptionPane.showMessageDialog(null, "Du er allerede stationsvagt på denne indsats");
    }

    public void functionDialog() {
        JOptionPane.showMessageDialog(null, "Du har allerede en funktion (BM/HL/CH) på denne indsats");
    }

    public String firemanTitle() {
        return "TOR - Tid & Registreringssytem";
    }

    public String firemanComboIncident() {
        return "Vælg anden indsats / ny indsats..";
    }

    public String firemanComboVehicle() {
        return "Vælg et køretøj..";
    }

    public String firemanComboIncidentType() {
        return "Vælg type..";
    }

    public String firemanTextHours() {
        return "Skriv timer..";
    }

    public String firemanTextIncident() {
        return "Skriv et navnet på indsatsen..";
    }

    public String firemanTextDate() {
        return "YYYY-MM-DD";
    }

    public String firemanTextClock() {
        return "TT:MM";
    }

    public String firemanListHL() {
        return "(HL) ";
    }

    public String firemanListBM() {
        return "(BM) ";
    }

    //TEAMLEADER MESSAGES-------------------------------------------------------
    public void teamLeaderSaveDialog() {
        JOptionPane.showMessageDialog(null, "Oplysningerne blev gemt");
    }

    public void addForcesDialog() {
        JOptionPane.showMessageDialog(null, "Udfyld venligst alle felter");
    }

    public void addMaterialsDialog() {
        JOptionPane.showMessageDialog(null, "Udfyld venligst alle felter");
    }

    public String TeamLeaderTitle() {
        return "TOR - Holdleder";
    }

    public String teamLeaderComboEmergency() {
        return "Kørsels type..";
    }

    public String teamLeaderTextAmountMen() {
        return "Bemanding..";
    }

    public String teamLeaderComboMaterial() {
        return "Vælg materiel..";
    }

    public String teamLeaderTextAmountMaterials() {
        return "Forbrug..";
    }

    public String teamLeaderComboReport() {
        return "Vælg beretning..";
    }

    //ERRORREPORT MESSAGES------------------------------------------------------
    public void ErrorConfirmMessageApproved() {
        JOptionPane.showMessageDialog(null, "Din fejl rapport er registreret");
    }

    public String ErrorTitle() {
        return "TOR - Fejlmeddelse";
    }

    //MISC----------------------------------------------------------------------
    public final String EMPTY_TEXT() {
        return "";
    }

    //BLL ERROR MESSAGES--------------------------------------------------------
    
    public void DataBaseError(){
        JOptionPane.showMessageDialog(null, "Der er sket en fejl, handlingen afsluttes");
    }
}
