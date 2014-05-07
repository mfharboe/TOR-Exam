package GUI;

import BE.BEAlarm;
import BE.BEEmergency;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEIncidentVehicle;
import BE.BEMaterial;
import BE.BEUsage;
import BE.BEVehicle;
import BLL.BLLFireman;
import BLL.BLLTeamLeader;
import GUI.TableModel.TableModelForces;
import GUI.TableModel.TableModelUsage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import javax.swing.table.TableRowSorter;

public class GUITeamLeader extends javax.swing.JFrame {

    private static GUITeamLeader m_instance;
    TableRowSorter<TableModelUsage> usageSorter;
    TableRowSorter<TableModelForces> forcesSorter;
    private TableModelUsage usageModel;
    private TableModelForces forcesModel;
    private ArrayList<BEUsage> usageList;
    private ArrayList<BEIncidentVehicle> forcesList;
    private BEIncident m_incident;

    /**
     * Creates new form GUITeamLeader.
     */
    private GUITeamLeader() {
        initComponents();
        this.setTitle(MessageDialog.getInstance().TeamLeaderTitle());
        initializeSettings();
        addColors();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     *
     * @return m_instance of GUITeamLeader
     */
    public static GUITeamLeader getInstance() {
        if (m_instance == null) {
            m_instance = new GUITeamLeader();
        }
        return m_instance;
    }

    /**
     * Sets the initial settings for this class.
     */
    private void initializeSettings() {
        addListeners();
        fillBoxes();
        clearForces();
        clearMaterials();
        usageList = new ArrayList<>();
        forcesList = new ArrayList<>();
        usageModel = new TableModelUsage(usageList);
        forcesModel = new TableModelForces(forcesList);
        tblUsage.setModel(usageModel);
        tblForces.setModel(forcesModel);
        usageSorter = new TableRowSorter<>(usageModel);
        forcesSorter = new TableRowSorter<>(forcesModel);
        tblUsage.setRowSorter(usageSorter);
        tblForces.setRowSorter(forcesSorter);

    }

    /**
     * Adds color.
     */
    private void addColors() {
        this.getContentPane().setBackground(Color.WHITE);
        pnlForces.setBackground(Color.WHITE);
        pnlUsage.setBackground(Color.WHITE);
        pnlInjured.setBackground(Color.WHITE);
        pnlAlarm.setBackground(Color.WHITE);
        jPanel5.setBackground(Color.WHITE);
        pnlRemark.setBackground(Color.WHITE);
        pnlTeamLeader.setBackground(Color.WHITE);
        cmbAlarmType.setBackground(Color.WHITE);
        cmbEmergency.setBackground(Color.WHITE);
        cmbMaterial.setBackground(Color.WHITE);
        cmbVehicle.setBackground(Color.WHITE);
        chkIsDiverged.setBackground(Color.WHITE);
    }

    /**
     * Adds Listeners.
     */
    private void addListeners() {
        btnAction btn = new btnAction();
        txtFocus txtFc = new txtFocus();

        txtAmountMen.addFocusListener(txtFc);
        txtAmountMaterial.addFocusListener(txtFc);

        btnAddForces.addActionListener(btn);
        btnAddMateriel.addActionListener(btn);
        btnSave.addActionListener(btn);
    }

    /**
     * Fills all the comboboxes.
     */
    private void fillBoxes() {
        fillEmergencyCombo();
        fillMaterialCombo();
        fillAlarmCombo();
        fillVehicleCombo();
    }

    /**
     * Fills the Vehicle ComboBox.
     */
    private void fillVehicleCombo() {
        cmbVehicle.addItem(MessageDialog.getInstance().firemanComboVehicle());
        for (BEVehicle bevehicle : BLLFireman.getInstance().readAllVehicles()) {
            cmbVehicle.addItem(bevehicle);
        }
    }

    /**
     * Fills the Emergency (Type of driving) ComboBox.
     */
    private void fillEmergencyCombo() {
        cmbEmergency.addItem(MessageDialog.getInstance().teamLeaderComboEmergency());
        for (BEEmergency beemergency : BLLTeamLeader.getInstance().readEmergencies()) {
            cmbEmergency.addItem(beemergency);
        }
    }

    /**
     * Fills the Alarm (Type of alarm) ComboBox.
     */
    private void fillAlarmCombo() {
        cmbAlarmType.addItem(MessageDialog.getInstance().teamLeaderComboReport());
        for (BEAlarm bealarm : BLLTeamLeader.getInstance().readAlarms()) {
            cmbAlarmType.addItem(bealarm);
        }

    }

    /**
     * Fills the Material ComboBox.
     */
    private void fillMaterialCombo() {
        cmbMaterial.addItem(MessageDialog.getInstance().teamLeaderComboMaterial());
        for (BEMaterial bematerial : BLLTeamLeader.getInstance().readMaterials()) {
            cmbMaterial.addItem(bematerial);
        }
    }

    /**
     * Sets all IncidentDetail fields to empty.
     */
    private void clearIncidentDetails() {
        txtIncidentLeader.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtMessage.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtEvaNumber.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtFireReportNumber.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtInvolvedName.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtInvolvedAddress.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtRemarks.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtDetectorNumber.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtGroupNumber.setText(MessageDialog.getInstance().EMPTY_TEXT());
        cmbAlarmType.setSelectedIndex(0);
    }

    /**
     * Sets all Forces fields to empty and no selection.
     */
    private void clearForces() {
        cmbVehicle.setSelectedIndex(0);
        cmbEmergency.setSelectedIndex(0);
        txtAmountMen.setText(MessageDialog.getInstance().teamLeaderTextAmountMen());
        chkIsDiverged.setSelected(false);
    }

    /**
     * Set all Material fields to empty and no selection.
     */
    private void clearMaterials() {
        cmbMaterial.setSelectedIndex(0);
        txtAmountMaterial.setText(MessageDialog.getInstance().teamLeaderTextAmountMaterials());
    }

    /**
     * Sets the IncidentDetails, Forces and Materials to the given Incident
     *
     * @param incident
     */
    public void setIncident(BEIncident incident) {
        m_incident = incident;
        forcesModel.setForceList(BLLTeamLeader.getInstance().incidentToIncidentVehicle(m_incident));
        usageModel.setUsageList(BLLTeamLeader.getInstance().incidentToUsage(m_incident));
        BEIncidentDetails details = BLLTeamLeader.getInstance().incidentToIncidentDetails(m_incident);
        if (details == null) {
            clearIncidentDetails();
        } else {
            txtIncidentLeader.setText(details.getM_incidentLeader());
            txtMessage.setText(details.getM_message());
            txtEvaNumber.setText(details.getM_evaNumber());
            txtFireReportNumber.setText(details.getM_fireReport());
            txtInvolvedName.setText(details.getM_involvedName());
            txtInvolvedAddress.setText(details.getM_involvedAddress());
            txtRemarks.setText(details.getM_remark());
            cmbAlarmType.setSelectedItem(details.getM_alarm());
            if (details.getM_alarm() == null) {
                cmbAlarmType.setSelectedIndex(0);
            }
            txtDetectorNumber.setText(details.getM_detectorNumber());
            txtGroupNumber.setText(details.getM_groupNumber());
        }
    }

    /**
     * Reads the Forces fields
     *
     * @return BEIncidentVehicles
     */
    private BEIncidentVehicle getMyForces() {
        BEVehicle bevehicle = (BEVehicle) cmbVehicle.getSelectedItem();
        BEEmergency beemergency = (BEEmergency) cmbEmergency.getSelectedItem();
        int amount = Integer.parseInt(txtAmountMen.getText());
        boolean isdiverged = chkIsDiverged.isSelected();
        BEIncidentVehicle beincidentvehicle = new BEIncidentVehicle(m_incident, bevehicle, beemergency, amount, isdiverged);

        return beincidentvehicle;
    }

    /**
     * Invoke this method when the AddForces button is pressed.
     */
    private void onClickAddForces() {
        if (isForcesfilled()) {
            BLLTeamLeader.getInstance().createIncidentVehicle(getMyForces());
            forcesModel.setForceList(BLLTeamLeader.getInstance().incidentToIncidentVehicle(m_incident));
            clearForces();
        }
    }

    /**
     * Checks if all Forces fields is filled
     *
     * @return boolean
     */
    private boolean isForcesfilled() {
        if (cmbVehicle.getSelectedIndex() == 0) {
            MessageDialog.getInstance().addForcesDialog();
            return false;
        }
        if (cmbEmergency.getSelectedIndex() == 0) {
            MessageDialog.getInstance().addForcesDialog();
            return false;
        }
        if (txtAmountMen.getText().isEmpty() || txtAmountMen.getText().equals(MessageDialog.getInstance().teamLeaderTextAmountMen())) {
            MessageDialog.getInstance().addForcesDialog();
            return false;
        }
        return true;
    }

    /**
     * Reads the Material fields
     *
     * @return BEUsage
     */
    private BEUsage getMyMaterials() {
        BEMaterial bematerial = (BEMaterial) cmbMaterial.getSelectedItem();
        int amount = Integer.parseInt(txtAmountMaterial.getText());
        BEUsage beusage = new BEUsage(0, bematerial, amount, m_incident);
        return beusage;
    }

    /**
     * Invoke this method when the AddMaterial button is pressed.
     */
    private void onClickAddMaterial() {
        if (isUsageFilled()) {
            BLLTeamLeader.getInstance().createUsage(getMyMaterials());
            usageModel.setUsageList(BLLTeamLeader.getInstance().incidentToUsage(m_incident));
            clearMaterials();
        }
    }

    /**
     * Checks if all Usage fields are filled
     *
     * @return boolean
     */
    private boolean isUsageFilled() {
        if (cmbMaterial.getSelectedIndex() == 0) {
            MessageDialog.getInstance().addMaterialsDialog();
            return false;
        }
        if (txtAmountMaterial.getText().isEmpty() || txtAmountMaterial.getText().equals(MessageDialog.getInstance().teamLeaderTextAmountMaterials())) {
            MessageDialog.getInstance().addMaterialsDialog();
            return false;
        }
        return true;
    }

    /**
     * Reads the IncidentDetails fields
     *
     * @return BEIncidentDetails
     */
    private BEIncidentDetails getMyDetails() {
        String leader = txtIncidentLeader.getText();
        String evaNr = txtEvaNumber.getText();
        String fireNr = txtFireReportNumber.getText();
        String message = txtMessage.getText();
        String involvedName = txtInvolvedName.getText();
        String involvedAddress = txtInvolvedAddress.getText();
        String remarks = txtRemarks.getText();
        BEAlarm alarm = null;
        if (cmbAlarmType.getSelectedIndex() != 0) {
            alarm = (BEAlarm) cmbAlarmType.getSelectedItem();
        }
        String detectorNr = txtDetectorNumber.getText();
        String groupNr = txtGroupNumber.getText();
        BEIncidentDetails bedetails = new BEIncidentDetails(leader, evaNr, fireNr, message, m_incident, involvedName, involvedAddress, remarks, alarm, detectorNr, groupNr);
        return bedetails;
    }

    /**
     * Invoke this method when the Save button is pressed.
     */
    private void onClickSaveDetails() {
        BLLTeamLeader.getInstance().updateIncidentDetails(getMyDetails());
        this.dispose();
    }

    /**
     * Listeners for the buttons.
     */
    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnAddForces)) {
                onClickAddForces();
            } else if (e.getSource().equals(btnAddMateriel)) {
                onClickAddMaterial();
            } else if (e.getSource().equals(btnSave)) {
                onClickSaveDetails();
            }

        }
    }

    /**
     * FokusListener for Textfield Forces and Material.
     */
    private class txtFocus extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource().equals(txtAmountMen)) {
                txtAmountMen.setText(MessageDialog.getInstance().EMPTY_TEXT());
            }
            if (e.getSource().equals(txtAmountMaterial)) {
                txtAmountMaterial.setText(MessageDialog.getInstance().EMPTY_TEXT());
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (txtAmountMaterial.getText().isEmpty()) {
                txtAmountMaterial.setText(MessageDialog.getInstance().teamLeaderTextAmountMaterials());
            }
            if (txtAmountMen.getText().isEmpty()) {
                txtAmountMen.setText(MessageDialog.getInstance().teamLeaderTextAmountMen());
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlForces = new javax.swing.JPanel();
        cmbVehicle = new javax.swing.JComboBox();
        chkIsDiverged = new javax.swing.JCheckBox();
        cmbEmergency = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblForces = new javax.swing.JTable();
        btnAddForces = new javax.swing.JButton();
        txtAmountMen = new javax.swing.JTextField();
        pnlUsage = new javax.swing.JPanel();
        cmbMaterial = new javax.swing.JComboBox();
        txtAmountMaterial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsage = new javax.swing.JTable();
        btnAddMateriel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        pnlInjured = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtInvolvedName = new javax.swing.JTextField();
        txtInvolvedAddress = new javax.swing.JTextField();
        pnlAlarm = new javax.swing.JPanel();
        cmbAlarmType = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        txtGroupNumber = new javax.swing.JTextField();
        txtDetectorNumber = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlRemark = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRemarks = new javax.swing.JTextArea();
        pnlTeamLeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtEvaNumber = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIncidentLeader = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFireReportNumber = new javax.swing.JTextField();
        txtMessage = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlForces.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Indsatte Styrker", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlForces.setLayout(null);

        cmbVehicle.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlForces.add(cmbVehicle);
        cmbVehicle.setBounds(20, 30, 140, 40);

        chkIsDiverged.setText("Afvigelse i besætning");
        pnlForces.add(chkIsDiverged);
        chkIsDiverged.setBounds(180, 90, 150, 25);

        cmbEmergency.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlForces.add(cmbEmergency);
        cmbEmergency.setBounds(180, 30, 140, 40);

        tblForces.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblForces.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblForces);

        pnlForces.add(jScrollPane2);
        jScrollPane2.setBounds(360, 30, 580, 150);

        btnAddForces.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAddForces.setText("Tilføj");
        pnlForces.add(btnAddForces);
        btnAddForces.setBounds(230, 140, 90, 40);

        txtAmountMen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlForces.add(txtAmountMen);
        txtAmountMen.setBounds(20, 80, 140, 40);

        pnlUsage.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forbrug", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlUsage.setLayout(null);

        cmbMaterial.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlUsage.add(cmbMaterial);
        cmbMaterial.setBounds(18, 32, 300, 40);

        txtAmountMaterial.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAmountMaterial.setToolTipText("");
        txtAmountMaterial.setAutoscrolls(false);
        pnlUsage.add(txtAmountMaterial);
        txtAmountMaterial.setBounds(20, 80, 140, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("liter/stk/kg");
        pnlUsage.add(jLabel3);
        jLabel3.setBounds(180, 90, 100, 20);

        tblUsage.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblUsage.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblUsage);

        pnlUsage.add(jScrollPane1);
        jScrollPane1.setBounds(360, 30, 580, 150);

        btnAddMateriel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAddMateriel.setText("Tilføj");
        pnlUsage.add(btnAddMateriel);
        btnAddMateriel.setBounds(230, 140, 90, 40);

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnSave.setText("Gem");

        pnlInjured.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Skadeslidte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlInjured.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Navn");
        pnlInjured.add(jLabel8);
        jLabel8.setBounds(10, 40, 120, 19);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Adresse");
        pnlInjured.add(jLabel9);
        jLabel9.setBounds(10, 70, 120, 22);

        txtInvolvedName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlInjured.add(txtInvolvedName);
        txtInvolvedName.setBounds(90, 30, 200, 30);

        txtInvolvedAddress.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlInjured.add(txtInvolvedAddress);
        txtInvolvedAddress.setBounds(90, 60, 200, 30);

        pnlAlarm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Beretning", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlAlarm.setLayout(null);

        cmbAlarmType.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlAlarm.add(cmbAlarmType);
        cmbAlarmType.setBounds(20, 30, 300, 40);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ved ABA Alarm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 15))); // NOI18N
        jPanel5.setLayout(null);

        txtGroupNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel5.add(txtGroupNumber);
        txtGroupNumber.setBounds(100, 60, 190, 30);

        txtDetectorNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel5.add(txtDetectorNumber);
        txtDetectorNumber.setBounds(100, 30, 190, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Detektor nr.");
        jPanel5.add(jLabel2);
        jLabel2.setBounds(10, 40, 90, 19);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Gruppe nr.");
        jPanel5.add(jLabel4);
        jLabel4.setBounds(10, 70, 70, 19);

        pnlAlarm.add(jPanel5);
        jPanel5.setBounds(20, 80, 300, 110);

        pnlRemark.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bemærkninger", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlRemark.setLayout(null);

        txtRemarks.setColumns(20);
        txtRemarks.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        txtRemarks.setRows(5);
        jScrollPane3.setViewportView(txtRemarks);

        pnlRemark.add(jScrollPane3);
        jScrollPane3.setBounds(20, 30, 570, 100);

        pnlTeamLeader.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Holdleder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlTeamLeader.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Evarapport nr.");
        pnlTeamLeader.add(jLabel1);
        jLabel1.setBounds(360, 40, 94, 19);

        txtEvaNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTeamLeader.add(txtEvaNumber);
        txtEvaNumber.setBounds(500, 30, 130, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Indsatsleder");
        pnlTeamLeader.add(jLabel6);
        jLabel6.setBounds(10, 70, 120, 19);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Melding lød");
        pnlTeamLeader.add(jLabel7);
        jLabel7.setBounds(10, 40, 120, 22);

        txtIncidentLeader.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTeamLeader.add(txtIncidentLeader);
        txtIncidentLeader.setBounds(120, 60, 200, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Brandrapport nr.");
        pnlTeamLeader.add(jLabel5);
        jLabel5.setBounds(360, 70, 150, 19);

        txtFireReportNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTeamLeader.add(txtFireReportNumber);
        txtFireReportNumber.setBounds(500, 60, 130, 30);

        txtMessage.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTeamLeader.add(txtMessage);
        txtMessage.setBounds(120, 30, 200, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlTeamLeader, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(pnlInjured, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlForces, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlRemark, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTeamLeader, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlInjured, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(pnlForces, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlRemark, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddForces;
    private javax.swing.JButton btnAddMateriel;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox chkIsDiverged;
    private javax.swing.JComboBox cmbAlarmType;
    private javax.swing.JComboBox cmbEmergency;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbVehicle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlAlarm;
    private javax.swing.JPanel pnlForces;
    private javax.swing.JPanel pnlInjured;
    private javax.swing.JPanel pnlRemark;
    private javax.swing.JPanel pnlTeamLeader;
    private javax.swing.JPanel pnlUsage;
    private javax.swing.JTable tblForces;
    private javax.swing.JTable tblUsage;
    private javax.swing.JTextField txtAmountMaterial;
    private javax.swing.JTextField txtAmountMen;
    private javax.swing.JTextField txtDetectorNumber;
    private javax.swing.JTextField txtEvaNumber;
    private javax.swing.JTextField txtFireReportNumber;
    private javax.swing.JTextField txtGroupNumber;
    private javax.swing.JTextField txtIncidentLeader;
    private javax.swing.JTextField txtInvolvedAddress;
    private javax.swing.JTextField txtInvolvedName;
    private javax.swing.JTextField txtMessage;
    private javax.swing.JTextArea txtRemarks;
    // End of variables declaration//GEN-END:variables
}
