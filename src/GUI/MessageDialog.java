package GUI;

import javax.swing.JOptionPane;

public class MessageDialog {

    private static MessageDialog m_instance;

    private MessageDialog() {

    }

    public static MessageDialog getInstance() {
        if (m_instance == null) {
            m_instance = new MessageDialog();
        }
        return m_instance;
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
    
    public void addForcesDialog(){
        JOptionPane.showMessageDialog(null, "Udfyld venligst alle felter");
    }
    
    public void addMaterialsDialog(){
        JOptionPane.showMessageDialog(null, "Udfyld venligst alle felter");
    }
    
    public void noTextHereDialog(){
        JOptionPane.showMessageDialog(null, "Du kan kun skrive tal her");
    }
    
    public void ErrorConfirmMessageApproved(){
        JOptionPane.showMessageDialog(null, "Din fejl Rapport er registreret");
    }
    public void ErrorConfirmMessageNotApproved(){
        JOptionPane.showMessageDialog(null, "Der opstod en fejl");
    }

    public String firemanTitle() {
        return "Tid og Registreringssystem - TOR";
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
        return "Skriv navnet på indsatsen..";
    }

    public String firemanTextDate() {
        return "YYYY-MM-DD";
    }

    public String firemanTextClock() {
        return "TT:MM";
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
    
    public String teamLeaderComboReport(){
        return "Vælg beretning..";
    }
    
    public String teamLeaderTextDetector(){
        return "Detektor nummer..";
    }
    
    public String teamLeaderTextGroupNumber() {
        return "Gruppe nummer..";
    }
    
    public final String EMPTY_TEXT(){
        return "";
    }
}
