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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
     * Creates new form GUITeamLeader
     */
    private GUITeamLeader() {
        initComponents();
        this.setTitle("TOR - Holdleder");
        initializeSettings();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static GUITeamLeader getInstance() {
        if (m_instance == null) {
            m_instance = new GUITeamLeader();
        }
        return m_instance;
    }

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

    private void addListeners() {
        btnAction btn = new btnAction();
        txtAction txt = new txtAction();
        txtFocus txtFc = new txtFocus();
        txtAmountMen.addKeyListener(txt);
        txtAmountMaterial.addKeyListener(txt);
        
        txtAmountMen.addFocusListener(txtFc);
        txtAmountMaterial.addFocusListener(txtFc);
        txtDetectorNumber.addFocusListener(txtFc);
        txtGroupNumber.addFocusListener(txtFc);
        txtIncidentLeader.addFocusListener(txtFc);
        txtFireReportNumber.addFocusListener(txtFc);
        txtInvolvedAddress.addFocusListener(txtFc);
        txtInvolvedName.addFocusListener(txtFc);
        txtMessage.addFocusListener(txtFc);
        txtRemarks.addFocusListener(txtFc);
   
        btnAddForces.addActionListener(btn);
        btnAddMateriel.addActionListener(btn);
        btnSaveAndFinish.addActionListener(btn);
    }

    private void fillBoxes() {
        fillEmergencyCombo();
        fillMaterialCombo();
        fillAlarmCombo();
        fillVehicleCombo();
    }

    private void fillVehicleCombo() {
        cmbVehicle.addItem(MessageDialog.getInstance().firemanComboVehicle());
        for (BEVehicle bevehicle : BLLFireman.getInstance().readAllVehicles()) {
            cmbVehicle.addItem(bevehicle);
        }
    }

    private void fillEmergencyCombo() {
        cmbEmergency.addItem(MessageDialog.getInstance().teamLeaderComboEmergency());
        for (BEEmergency beemergency : BLLTeamLeader.getInstance().readEmergencies()) {
            cmbEmergency.addItem(beemergency);
        }
    }

    private void fillAlarmCombo() {
        cmbAlarmType.addItem(MessageDialog.getInstance().teamLeaderComboReport());
        for (BEAlarm bealarm : BLLTeamLeader.getInstance().readAlarms()) {
            cmbAlarmType.addItem(bealarm);
        }

    }

    private void fillMaterialCombo() {
        cmbMaterial.addItem(MessageDialog.getInstance().teamLeaderComboMaterial());
        for (BEMaterial bematerial : BLLTeamLeader.getInstance().readMaterials()) {
            cmbMaterial.addItem(bematerial);
        }
    }

    private void clearIncidentDetails() {
        txtIncidentLeader.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtMessage.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtEvaNumber.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtFireReportNumber.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtInvolvedName.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtInvolvedAddress.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtRemarks.setText(MessageDialog.getInstance().EMPTY_TEXT());
        cmbAlarmType.setSelectedIndex(0);
        txtDetectorNumber.setText(MessageDialog.getInstance().teamLeaderTextDetector());
        txtGroupNumber.setText(MessageDialog.getInstance().teamLeaderTextGroupNumber());
    }

    private void clearForces() {
        cmbVehicle.setSelectedIndex(0);
        cmbEmergency.setSelectedIndex(0);
        txtAmountMen.setText(MessageDialog.getInstance().teamLeaderTextAmountMen());
        chkIsDiverged.setSelected(false);
    }

    private void clearMaterials() {
        cmbMaterial.setSelectedIndex(0);
        txtAmountMaterial.setText(MessageDialog.getInstance().teamLeaderTextAmountMaterials());
    }

    public void setIncident(BEIncident beincident) {
        m_incident = beincident;
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
        }
    }

    private BEIncidentVehicle getMyForcesContribution() {
        BEVehicle bevehicle = (BEVehicle) cmbVehicle.getSelectedItem();
        BEEmergency beemergency = (BEEmergency) cmbEmergency.getSelectedItem();
        int amount = Integer.parseInt(txtAmountMen.getText());
        boolean isdiverged = chkIsDiverged.isSelected();
        BEIncidentVehicle beincidentvehicle = new BEIncidentVehicle(m_incident, bevehicle, beemergency, amount, isdiverged);

        return beincidentvehicle;
    }

    private void onClickAddForces() {
        if (isForcesfilled()) {
            BLLTeamLeader.getInstance().createIncidentVehicle(getMyForcesContribution());
            forcesModel.setForceList(BLLTeamLeader.getInstance().incidentToIncidentVehicle(m_incident));
            clearForces();
        }
    }

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

    private BEUsage getMyMaterialContribution() {
        BEMaterial bematerial = (BEMaterial) cmbMaterial.getSelectedItem();
        int amount = Integer.parseInt(txtAmountMaterial.getText());
        BEUsage beusage = new BEUsage(0, bematerial, amount, m_incident);
        return beusage;
    }

    private void onClickAddMaterial() {
        if (isUsageFilled()) {
            BLLTeamLeader.getInstance().createUsage(getMyMaterialContribution());
            usageModel.setUsageList(BLLTeamLeader.getInstance().incidentToUsage(m_incident));
            clearMaterials();
        }
    }

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

    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnAddForces)) {
                onClickAddForces();
            } else if (e.getSource().equals(btnAddMateriel)) {
                onClickAddMaterial();
            }

        }
    }

    private class txtAction extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private class txtFocus extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            if(e.getSource().equals(txtAmountMen)){
                txtAmountMen.setText(MessageDialog.getInstance().EMPTY_TEXT());
            }else{
                txtAmountMen.setText(MessageDialog.getInstance().teamLeaderTextAmountMen());
            }
            if(e.getSource().equals(txtAmountMaterial)){
                txtAmountMaterial.setText(MessageDialog.getInstance().EMPTY_TEXT());
            }else{
                txtAmountMaterial.setText(MessageDialog.getInstance().teamLeaderTextAmountMaterials());
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

        txtMessage = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        cmbVehicle = new javax.swing.JComboBox();
        chkIsDiverged = new javax.swing.JCheckBox();
        cmbEmergency = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblForces = new javax.swing.JTable();
        btnAddForces = new javax.swing.JButton();
        txtAmountMen = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cmbMaterial = new javax.swing.JComboBox();
        txtAmountMaterial = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsage = new javax.swing.JTable();
        btnAddMateriel = new javax.swing.JButton();
        txtEvaNumber = new javax.swing.JTextField();
        btnSaveAndFinish = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtInvolvedName = new javax.swing.JTextField();
        txtInvolvedAddress = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        cmbAlarmType = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        txtDetectorNumber = new javax.swing.JTextField();
        txtGroupNumber = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtRemarks = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIncidentLeader = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFireReportNumber = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtMessage.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Indsatte Styrker", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel1.setLayout(null);

        cmbVehicle.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(cmbVehicle);
        cmbVehicle.setBounds(20, 30, 140, 40);

        chkIsDiverged.setText("Afvigelse i besætning");
        jPanel1.add(chkIsDiverged);
        chkIsDiverged.setBounds(180, 90, 150, 25);

        cmbEmergency.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(cmbEmergency);
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

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(360, 30, 580, 150);

        btnAddForces.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAddForces.setText("Tilføj");
        jPanel1.add(btnAddForces);
        btnAddForces.setBounds(230, 140, 90, 40);

        txtAmountMen.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel1.add(txtAmountMen);
        txtAmountMen.setBounds(20, 80, 140, 40);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forbrug", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel2.setLayout(null);

        cmbMaterial.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel2.add(cmbMaterial);
        cmbMaterial.setBounds(18, 32, 300, 40);

        txtAmountMaterial.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txtAmountMaterial.setToolTipText("");
        txtAmountMaterial.setAutoscrolls(false);
        jPanel2.add(txtAmountMaterial);
        txtAmountMaterial.setBounds(20, 80, 140, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("liter/stk/kg");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(170, 90, 100, 20);

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

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(360, 30, 580, 150);

        btnAddMateriel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnAddMateriel.setText("Tilføj");
        jPanel2.add(btnAddMateriel);
        btnAddMateriel.setBounds(230, 140, 90, 40);

        txtEvaNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        btnSaveAndFinish.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnSaveAndFinish.setText("Gem og Afslut");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Skadeslidte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel3.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Navn");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(10, 40, 120, 19);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel9.setText("Adresse");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(10, 70, 120, 22);

        txtInvolvedName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel3.add(txtInvolvedName);
        txtInvolvedName.setBounds(90, 30, 200, 30);

        txtInvolvedAddress.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel3.add(txtInvolvedAddress);
        txtInvolvedAddress.setBounds(90, 60, 200, 30);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Beretning", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel4.setLayout(null);

        cmbAlarmType.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel4.add(cmbAlarmType);
        cmbAlarmType.setBounds(20, 30, 300, 40);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ved ABA Alarm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 15))); // NOI18N
        jPanel5.setLayout(null);

        txtDetectorNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel5.add(txtDetectorNumber);
        txtDetectorNumber.setBounds(10, 30, 280, 30);

        txtGroupNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel5.add(txtGroupNumber);
        txtGroupNumber.setBounds(10, 70, 280, 30);

        jPanel4.add(jPanel5);
        jPanel5.setBounds(20, 80, 300, 110);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bemærkninger", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel6.setLayout(null);

        txtRemarks.setColumns(20);
        txtRemarks.setFont(new java.awt.Font("Calibri", 0, 15)); // NOI18N
        txtRemarks.setRows(5);
        jScrollPane3.setViewportView(txtRemarks);

        jPanel6.add(jScrollPane3);
        jScrollPane3.setBounds(20, 30, 560, 100);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Holdleder", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel7.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Evarapport nr.");
        jPanel7.add(jLabel1);
        jLabel1.setBounds(360, 40, 94, 19);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel6.setText("Indsatsleder");
        jPanel7.add(jLabel6);
        jLabel6.setBounds(10, 70, 120, 19);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel7.setText("Melding lød");
        jPanel7.add(jLabel7);
        jLabel7.setBounds(10, 40, 120, 22);

        txtIncidentLeader.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel7.add(txtIncidentLeader);
        txtIncidentLeader.setBounds(120, 60, 200, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Brandrapport nr.");
        jPanel7.add(jLabel5);
        jLabel5.setBounds(360, 70, 150, 19);

        txtFireReportNumber.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel7.add(txtFireReportNumber);
        txtFireReportNumber.setBounds(500, 60, 130, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(500, 500, 500)
                                .addComponent(txtEvaNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 960, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSaveAndFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtEvaNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveAndFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddForces;
    private javax.swing.JButton btnAddMateriel;
    private javax.swing.JButton btnSaveAndFinish;
    private javax.swing.JCheckBox chkIsDiverged;
    private javax.swing.JComboBox cmbAlarmType;
    private javax.swing.JComboBox cmbEmergency;
    private javax.swing.JComboBox cmbMaterial;
    private javax.swing.JComboBox cmbVehicle;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
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
