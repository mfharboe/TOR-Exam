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

    /**
     * @return title for FiremanGUI
     */
    public String firemanTitle() {
        return "TOR - Tid & Registreringssytem";
    }

    /**
     * @return title for teamLeaderGUI
     */
    public String teamLeaderTitle() {
        return "TOR - Holdleder";
    }

    /**
     * @return title for errorGUI
     */
    public String errorTitle() {
        return "TOR - Fejlmeddelse";
    }

    /**
     * @return title for CreateIncidentGUI
     */
    public String createIncidentTitle() {
        return "TOR - Lav ny indsats";
    }

    /**
     * @return text for the incident comboboxes
     */
    public String cmbIncidents() {
        return "Vælg en indsats..";
    }

    /**
     * @return text for the incidentType comboboxes 
     */
    public String cmbIncidentType() {
        return "Vælg type..";
    }
    
    /**
     * @return text for the vehicle comboboxes 
     */
    public String cmbVehicle() {
        return "Vælg et køretøj..";
    }

    /**
     * @return text for the materials comboboxes 
     */
    public String cmbMaterials() {
        return "Vælg materiel..";
    }

    /**
     * @return text for the report comboboxes 
     */
    public String cmbReport() {
        return "Vælg beretning..";
    }

    /**
     * @return text for the hours textFields 
     */
    public String txtHours() {
        return "Skriv timer..";
    }

    /**
     * @return text for the incidentName textFields 
     */
    public String txtIncidentName() {
        return "Skriv et navnet på indsatsen..";
    }

    /**
     * @return text for the date textFields 
     */
    public String txtDate() {
        return "YYYY-MM-DD";
    }

    /**
     * @return text for the time textFields 
     */
    public String txtIncidentTime() {
        return "TT:MM";
    }

    /**
     * @return text for the usage textFields 
     */
    public String txtUsage() {
        return "Forbrug..";
    }
    /**
     * @return text for the create/update buttons 
     */
    public String txtCreate(){
        return "Opret";
    }
    
    /**
     * @return text for the create/update buttons 
     */
    public String txtUpdate(){
        return "Opdatér";
    }
    /**
     * @return text for the HL title 
     */
    public String txtHL() {
        return "(HL) ";
    }

    /**
     * @return text for the BM title 
     */
    public String txtBM() {
        return "(BM) ";
    }

    /**
     * @return an empty String 
     */
    public String EMPTY_TEXT() {
        return "";
    }

    /**
     * Dialog if the car is filled
     */
    public void dialogCarSeatsFilled() {
        JOptionPane.showMessageDialog(null, "Bilen er allerede fyldt op");
    }

    /**
     * Dialog if not all required information is filled
     */
    public void dialogFillAllInformation() {
        JOptionPane.showMessageDialog(null, "Udfyld venligst alle informationer");
    }

    /**
     * Dialog if the fireman is already on the station
     */
    public void dialogStation() {
        JOptionPane.showMessageDialog(null, "Du er allerede stationsvagt på denne indsats");
    }

    /**
     * Dialog if the fireman already has a function as either BM, HL or CH 
     */
    public void dialogFunction() {
        JOptionPane.showMessageDialog(null, "Du har allerede en funktion (BM/HL/CH) på denne indsats");
    }

    /**
     * Dialog if an action is completed
     */
    public void dialogInformationSaved() {
        JOptionPane.showMessageDialog(null, "Oplysningerne blev gemt");
    }

    /**
     * Dialog if an error happened with the connection
     */
    public void DataBaseError() {
        JOptionPane.showMessageDialog(null, "Der er sket en fejl, handlingen afsluttes");
    }
}
