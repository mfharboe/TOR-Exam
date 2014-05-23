package GUI;

import BE.BEAlarm;
import BE.BEIncident;
import BE.BEIncidentDetails;
import BE.BEMaterial;
import BE.BEUsage;
import BLL.BLLAdapter;
import BLL.BLLCreate;
import BLL.BLLDelete;
import BLL.BLLRead;
import BLL.BLLUpdate;
import GUI.TableModel.TableModelUsage;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.table.TableRowSorter;

public class GUITeamLeader extends javax.swing.JFrame {

    private static GUITeamLeader m_instance;
    TableRowSorter<TableModelUsage> usageSorter;
    private TableModelUsage usageModel;
    private BEIncident m_incident;
    private BEIncidentDetails m_incidentDetails;

    /**
     * Creates new form GUITeamLeader.
     */
    private GUITeamLeader() {
        initComponents();
        this.setTitle(MessageDialog.getInstance().teamLeaderTitle());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initialSettings();
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
    private void initialSettings() {
        addListeners();
        fillBoxes();
        clearMaterials();
        addColors();
        setTable();
    }

    /**
     * Adds color.
     */
    private void addColors() {
        this.getContentPane().setBackground(Color.WHITE);
        pnlUsage.setBackground(Color.WHITE);
        pnlInjured.setBackground(Color.WHITE);
        pnlAlarm.setBackground(Color.WHITE);
        pnlABAAlarm.setBackground(Color.WHITE);
        pnlRemark.setBackground(Color.WHITE);
        pnlTeamLeader.setBackground(Color.WHITE);
        cmbAlarmType.setBackground(Color.WHITE);
        cmbMaterial.setBackground(Color.WHITE);
    }

    /**
     * Adds Listeners.
     */
    private void addListeners() {
        btnAction btn = new btnAction();
        txtFocus txtFc = new txtFocus();
        txtAction txt = new txtAction();
        btnAddMateriel.addActionListener(btn);
        btnSave.addActionListener(btn);
        btnRemoveMateriel.addActionListener(btn);
        txtAmountMaterial.addFocusListener(txtFc);
        txtAmountMaterial.addKeyListener(txt);
    }

    /**
     * Sets the table with the initial settings
     */
    private void setTable() {
        usageModel = new TableModelUsage(BLLAdapter.getInstance().getEmptyUsage());
        tblUsage.setModel(usageModel);
        BLLAdapter.getInstance().register(usageModel);
    }

    /**
     * Fills all the comboboxes.
     */
    private void fillBoxes() {
        fillMaterialCombo();
        fillAlarmCombo();
    }

    /**
     * Fills the Alarm (Type of alarm) ComboBox.
     */
    private void fillAlarmCombo() {
        cmbAlarmType.addItem(MessageDialog.getInstance().cmbReport());
        for (BEAlarm bealarm : BLLRead.getInstance().readAlarms()) {
            cmbAlarmType.addItem(bealarm);
        }

    }

    /**
     * Fills the Material ComboBox.
     */
    private void fillMaterialCombo() {
        cmbMaterial.addItem(MessageDialog.getInstance().cmbMaterials());
        for (BEMaterial bematerial : BLLRead.getInstance().readMaterials()) {
            cmbMaterial.addItem(bematerial);
        }
    }

    /**
     * Set all Material fields to empty and no selection.
     */
    private void clearMaterials() {
        cmbMaterial.setSelectedIndex(0);
        txtAmountMaterial.setText(MessageDialog.getInstance().txtUsage());
    }

    /**
     * Sets the IncidentDetails, Forces and Materials to the given Incident
     *
     * @param incident
     */
    public void setIncident(BEIncident incident) {
        m_incident = incident;
        BLLAdapter.getInstance().incidentToUsage(m_incident);
        m_incidentDetails = BLLAdapter.getInstance().incidentToIncidentDetails(m_incident);
        txtIncidentLeader.setText(m_incidentDetails.getM_incidentLeader());
        txtEvaNumber.setText(m_incidentDetails.getM_evaNumber());
        txtFireReportNumber.setText(m_incidentDetails.getM_fireReport());
        txtInvolvedName.setText(m_incidentDetails.getM_involvedName());
        txtInvolvedAddress.setText(m_incidentDetails.getM_involvedAddress());
        txtRemarks.setText(m_incidentDetails.getM_remark());
        cmbAlarmType.setSelectedItem(m_incidentDetails.getM_alarm());
        if (m_incidentDetails.getM_alarm() == null) {
            cmbAlarmType.setSelectedIndex(0);
        }
        txtDetectorNumber.setText(m_incidentDetails.getM_detectorNumber());
        txtGroupNumber.setText(m_incidentDetails.getM_groupNumber());
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
            BLLCreate.getInstance().createUsage(getMyMaterials());
            BLLAdapter.getInstance().incidentToUsage(m_incident);
            clearMaterials();
        }
    }

    /**
     * Invoke this method when the removeMaterial button is pressed
     */
    private void onClickRemoveMaterial() {
        if (tblUsage.getSelectedRow() == -1) {
            MessageDialog.getInstance().dialogChooseMaterial();
            return;
        }
        int[] rows = tblUsage.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            BEUsage usage = usageModel.getUsageByRow(rows[i]);
            BLLDelete.getInstance().deleteMaterialFromUsage(usage);
        }
        BLLAdapter.getInstance().incidentToUsage(m_incident);
    }

    /**
     * Checks if all Usage fields are filled
     *
     * @return true / false if usage is filled
     */
    private boolean isUsageFilled() {
        if (cmbMaterial.getSelectedIndex() == 0) {
            MessageDialog.getInstance().dialogFillAllInformation();
            return false;
        }
        if (txtAmountMaterial.getText().isEmpty() || txtAmountMaterial.getText().equals(MessageDialog.getInstance().txtUsage())) {
            MessageDialog.getInstance().dialogFillAllInformation();
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
        m_incidentDetails.setM_incidentLeader(txtIncidentLeader.getText());
        m_incidentDetails.setM_evaNumber(txtEvaNumber.getText());
        m_incidentDetails.setM_fireReport(txtFireReportNumber.getText());
        m_incidentDetails.setM_involvedName(txtInvolvedName.getText());
        m_incidentDetails.setM_involvedAddress(txtInvolvedAddress.getText());
        m_incidentDetails.setM_remark(txtRemarks.getText());
        m_incidentDetails.setM_detectorNumber(txtDetectorNumber.getText());
        m_incidentDetails.setM_groupNumber(txtGroupNumber.getText());
        BEAlarm alarm = null;
        if (cmbAlarmType.getSelectedIndex() != 0) {
            alarm = (BEAlarm) cmbAlarmType.getSelectedItem();
        }
        m_incidentDetails.setM_alarm(alarm);
        return m_incidentDetails;
    }

    /**
     * Invoke this method when the Save button is pressed.
     */
    private void onClickSaveDetails() {
        BLLUpdate.getInstance().updateIncidentDetails(getMyDetails());
        this.dispose();
    }

    /**
     * Invoke this method when a key
     */
    private void onKeyPress() {
        if (!(txtAmountMaterial.getText().isEmpty() || txtAmountMaterial.getText().equals(MessageDialog.getInstance().txtUsage()))) {
            if (!checkForIntegers(txtAmountMaterial.getText())) {
                txtAmountMaterial.setText(MessageDialog.getInstance().EMPTY_TEXT());
                MessageDialog.getInstance().dialogIntegerError();
            }
        }
    }

    /**
     * Checks if the txt input contains integers
     *
     * @return true if the txtfield contains integers, false if it doesn't
     */
    private boolean checkForIntegers(String input) {
        return input.matches(MessageDialog.getInstance().txtIntChecker());
    }

    /**
     * Listeners for the buttons.
     */
    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource().equals(btnAddMateriel)) {
                onClickAddMaterial();
            } else if (e.getSource().equals(btnSave)) {
                onClickSaveDetails();
            } else if (e.getSource().equals(btnRemoveMateriel)) {
                onClickRemoveMaterial();
            }

        }
    }

    /**
     * FokusListener for Textfield Forces and Material.
     */
    private class txtFocus extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {

            if (e.getSource().equals(txtAmountMaterial)) {
                txtAmountMaterial.setText(MessageDialog.getInstance().EMPTY_TEXT());
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (txtAmountMaterial.getText().isEmpty()) {
                txtAmountMaterial.setText(MessageDialog.getInstance().txtUsage());
            }

        }

    }

    /**
     * KeyListener for txtAmountMaterial
     */
    private class txtAction extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            onKeyPress();

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

        pnlUsage = new javax.swing.JPanel();
        cmbMaterial = new javax.swing.JComboBox();
        txtAmountMaterial = new javax.swing.JTextField();
        lblUsageAmountDesc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsage = new javax.swing.JTable();
        btnAddMateriel = new javax.swing.JButton();
        btnRemoveMateriel = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        pnlInjured = new javax.swing.JPanel();
        lblInjuredName = new javax.swing.JLabel();
        lblInjuredAddress = new javax.swing.JLabel();
        txtInvolvedName = new javax.swing.JTextField();
        txtInvolvedAddress = new javax.swing.JTextField();
        pnlAlarm = new javax.swing.JPanel();
        cmbAlarmType = new javax.swing.JComboBox();
        pnlABAAlarm = new javax.swing.JPanel();
        txtGroupNumber = new javax.swing.JTextField();
        txtDetectorNumber = new javax.swing.JTextField();
        lblDetector = new javax.swing.JLabel();
        lblGroupNumber = new javax.swing.JLabel();
        pnlRemark = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRemarks = new javax.swing.JTextArea();
        pnlTeamLeader = new javax.swing.JPanel();
        lblEvaNumber = new javax.swing.JLabel();
        txtEvaNumber = new javax.swing.JTextField();
        lblIncidentLeader = new javax.swing.JLabel();
        txtIncidentLeader = new javax.swing.JTextField();
        lblFireReportNumber = new javax.swing.JLabel();
        txtFireReportNumber = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlUsage.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forbrug", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlUsage.setLayout(null);

        cmbMaterial.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlUsage.add(cmbMaterial);
        cmbMaterial.setBounds(18, 32, 300, 40);

        txtAmountMaterial.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAmountMaterial.setToolTipText("");
        txtAmountMaterial.setAutoscrolls(false);
        pnlUsage.add(txtAmountMaterial);
        txtAmountMaterial.setBounds(20, 80, 90, 40);

        lblUsageAmountDesc.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblUsageAmountDesc.setText("liter/stk/kg");
        pnlUsage.add(lblUsageAmountDesc);
        lblUsageAmountDesc.setBounds(120, 90, 100, 20);

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
        jScrollPane1.setBounds(370, 30, 570, 140);

        btnAddMateriel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAddMateriel.setText("Tilføj");
        pnlUsage.add(btnAddMateriel);
        btnAddMateriel.setBounds(230, 80, 90, 40);

        btnRemoveMateriel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnRemoveMateriel.setText("Fjern");
        pnlUsage.add(btnRemoveMateriel);
        btnRemoveMateriel.setBounds(230, 130, 90, 40);

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnSave.setText("Gem");

        pnlInjured.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Skadeslidte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlInjured.setLayout(null);

        lblInjuredName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblInjuredName.setText("Navn");
        pnlInjured.add(lblInjuredName);
        lblInjuredName.setBounds(10, 40, 120, 19);

        lblInjuredAddress.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblInjuredAddress.setText("Adresse");
        pnlInjured.add(lblInjuredAddress);
        lblInjuredAddress.setBounds(10, 70, 120, 22);

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

        pnlABAAlarm.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ved ABA Alarm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 15))); // NOI18N
        pnlABAAlarm.setLayout(null);

        txtGroupNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlABAAlarm.add(txtGroupNumber);
        txtGroupNumber.setBounds(100, 60, 190, 30);

        txtDetectorNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlABAAlarm.add(txtDetectorNumber);
        txtDetectorNumber.setBounds(100, 30, 190, 30);

        lblDetector.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblDetector.setText("Detektor nr.");
        pnlABAAlarm.add(lblDetector);
        lblDetector.setBounds(10, 40, 90, 19);

        lblGroupNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblGroupNumber.setText("Gruppe nr.");
        pnlABAAlarm.add(lblGroupNumber);
        lblGroupNumber.setBounds(10, 70, 70, 19);

        pnlAlarm.add(pnlABAAlarm);
        pnlABAAlarm.setBounds(20, 80, 300, 110);

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

        lblEvaNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblEvaNumber.setText("Evarapport nr.");
        pnlTeamLeader.add(lblEvaNumber);
        lblEvaNumber.setBounds(360, 40, 94, 19);

        txtEvaNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTeamLeader.add(txtEvaNumber);
        txtEvaNumber.setBounds(500, 30, 130, 30);

        lblIncidentLeader.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblIncidentLeader.setText("Indsatsleder");
        pnlTeamLeader.add(lblIncidentLeader);
        lblIncidentLeader.setBounds(10, 40, 120, 19);

        txtIncidentLeader.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTeamLeader.add(txtIncidentLeader);
        txtIncidentLeader.setBounds(120, 30, 200, 30);

        lblFireReportNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lblFireReportNumber.setText("Brandrapport nr.");
        pnlTeamLeader.add(lblFireReportNumber);
        lblFireReportNumber.setBounds(360, 70, 150, 19);

        txtFireReportNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlTeamLeader.add(txtFireReportNumber);
        txtFireReportNumber.setBounds(500, 60, 130, 30);

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
                    .addComponent(pnlUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(pnlAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pnlRemark, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlTeamLeader, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlInjured, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(pnlUsage, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlRemark, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnlAlarm, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddMateriel;
    private javax.swing.JButton btnRemoveMateriel;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbAlarmType;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblDetector;
    private javax.swing.JLabel lblEvaNumber;
    private javax.swing.JLabel lblFireReportNumber;
    private javax.swing.JLabel lblGroupNumber;
    private javax.swing.JLabel lblIncidentLeader;
    private javax.swing.JLabel lblInjuredAddress;
    private javax.swing.JLabel lblInjuredName;
    private javax.swing.JLabel lblUsageAmountDesc;
    private javax.swing.JPanel pnlABAAlarm;
    private javax.swing.JPanel pnlAlarm;
    private javax.swing.JPanel pnlInjured;
    private javax.swing.JPanel pnlRemark;
    private javax.swing.JPanel pnlTeamLeader;
    private javax.swing.JPanel pnlUsage;
    private javax.swing.JTable tblUsage;
    private javax.swing.JTextField txtAmountMaterial;
    private javax.swing.JTextField txtDetectorNumber;
    private javax.swing.JTextField txtEvaNumber;
    private javax.swing.JTextField txtFireReportNumber;
    private javax.swing.JTextField txtGroupNumber;
    private javax.swing.JTextField txtIncidentLeader;
    private javax.swing.JTextField txtInvolvedAddress;
    private javax.swing.JTextField txtInvolvedName;
    private javax.swing.JTextArea txtRemarks;
    // End of variables declaration//GEN-END:variables
}
