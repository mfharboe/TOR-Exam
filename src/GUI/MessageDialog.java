package GUI;

import javax.swing.JOptionPane;

public class MessageDialog {

    private static MessageDialog m_instance;

    private MessageDialog() {

    }

    /**
     *
     * @return current m_instance of MessageDialog
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
    public String txtCreate() {
        return "Opret";
    }

    /**
     * @return text for the create/update buttons
     */
    public String txtUpdate() {
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

    public String txtIntChecker() {
        return "[0-9]+";
    }

    /**
     * @return an empty String
     */
    public String EMPTY_TEXT() {
        return "";
    }


    /**
     * Dialog if not all required information is filled
     */
    public void dialogFillAllInformation() {
        JOptionPane.showMessageDialog(null, "Udfyld venligst alle informationer");
    }

 
    /**
     * Dialog if the fireman already has a function as either BM, HL or CH
     */
    public void dialogFunction() {
        JOptionPane.showMessageDialog(null, "Du har allerede en funktion (BM/HL/CH) på denne indsats");
    }

    public void dialogChooseClothesOrVehicle() {
        JOptionPane.showMessageDialog(null, "Du skal enten vælge et køretøj eller sende en branddragt til vask");
    }

    /**
     * Dialog if an action is completed
     */
    public void dialogInformationSaved() {
        JOptionPane.showMessageDialog(null, "Oplysningerne blev gemt");
    }

    /**
     * Dialog if the user input is not an integer
     */
    public void dialogIntegerError() {
        JOptionPane.showMessageDialog(null, "Du skal skrive tal (0-9) i dette felt");
    }

    public void dialogChooseMaterial() {
        JOptionPane.showMessageDialog(null, "Du skal skrive vælge et materiale at fjerne");
    }
    
    public void dialogChooseFireman(){
        JOptionPane.showMessageDialog(null, "Du skal vælge en brandmand at fjerne");
    }

    /**
     * Dialog if an error happened with the connection
     */
    public void DataBaseError() {
        JOptionPane.showMessageDialog(null, "Der er sket en fejl, handlingen afsluttes");
    }
}
