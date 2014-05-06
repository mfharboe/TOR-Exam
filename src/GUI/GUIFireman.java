package GUI;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentType;
import BE.BERoleTime;
import BE.BEVehicle;
import BLL.BLLFireman;
import GUI.TableModel.TableModelRoleTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

public class GUIFireman extends javax.swing.JFrame {

    DefaultListModel<BEFireman> firemanListModel;
    TableRowSorter<TableModelRoleTime> roleTimeSorter;
    private TableModelRoleTime roleTimeModel;
    private static final ArrayList<BERoleTime> EMPTY_ARRAY_LIST = new ArrayList<>();

    public GUIFireman() {
        this.setTitle(MessageDialog.getInstance().firemanTitle());
        initComponents();
        initializeSettings();

    }

    /**
     * THe initial settings for this class
     */
    private void initializeSettings() {
        firemanListModel = new DefaultListModel<>();
        lstManpower.setModel(firemanListModel);
        fillBoxes();
        addListeners();
        setManpowerEnabled(false);
        setMyContributionEnabled(false);
        setAllFunctionsEnabled(false);
        btnNext.setEnabled(false);
        txtManHours.setText(MessageDialog.getInstance().firemanTextHours());
        roleTimeModel = new TableModelRoleTime(EMPTY_ARRAY_LIST);
        tblRoleTime.setModel(roleTimeModel);
        roleTimeSorter = new TableRowSorter<>(roleTimeModel);
        tblRoleTime.setRowSorter(roleTimeSorter);
    }

    /**
     * Adds Listeners to all buttons, comboboxes, etc.
     */
    private void addListeners() {
        cmbAction cmb = new cmbAction();
        btnAction btn = new btnAction();
        lstAction lst = new lstAction();
        txtAction txt = new txtAction();
        txtFocus txtFc = new txtFocus();
        txtManHours.addKeyListener(txt);
        txtManHours.addFocusListener(txtFc);
        lstManpower.addListSelectionListener(lst);
        btnSave.addActionListener(btn);
        btnBM.addActionListener(btn);
        btnCH.addActionListener(btn);
        btnHL.addActionListener(btn);
        btnST.addActionListener(btn);
        btnNext.addActionListener(btn);
        cmbIncident.addItemListener(cmb);
        cmbVehicle.addItemListener(cmb);

    }

    /**
     * Fills the Fireman List ordered by HL-BM and then lastname
     */
    private void fillFiremanList() {
        for (BEFireman befireman : BLLFireman.getInstance().readAllFiremen()) {
            if (befireman.isM_isTeamLeader()) {
                firemanListModel.addElement(befireman);
            }
        }
        for (BEFireman befireman : BLLFireman.getInstance().readAllFiremen()) {
            if (!befireman.isM_isTeamLeader()) {
                firemanListModel.addElement(befireman);
            }
        }
    }

    /**
     * Fills the Vehicle ComboBox
     */
    private void fillVehicleCombo() {
        cmbVehicle.addItem(MessageDialog.getInstance().firemanComboVehicle());
        for (BEVehicle bevehicle : BLLFireman.getInstance().readAllVehicles()) {
            cmbVehicle.addItem(bevehicle);
        }
    }

    /**
     * Fills the IncidentType ComboBox
     */
    private void fillIncidentTypeCombo() {
        cmbIncidentType.addItem(MessageDialog.getInstance().firemanComboIncidentType());
        for (BEIncidentType beincidenttype : BLLFireman.getInstance().readAllIncidentTypes()) {
            cmbIncidentType.addItem(beincidenttype);
        }
    }

    /**
     * Fills the Incident ComboBox
     */
    private void fillIncidentCombo() {
        cmbIncident.addItem(MessageDialog.getInstance().firemanComboIncident());
        for (BEIncident beincident : BLLFireman.getInstance().readIncompleteIncidents()) {
            cmbIncident.addItem(beincident);
        }
    }

    /**
     * Fills all ComboBoxes
     */
    private void fillBoxes() {
        fillFiremanList();
        fillVehicleCombo();
        fillIncidentTypeCombo();
        fillIncidentCombo();
    }

    /**
     * Set the manPower List to be enabled or disabled
     *
     * @param enable
     */
    private void setManpowerEnabled(boolean enable) {
        lstManpower.setEnabled(enable);
    }

    /**
     * Sets the contribution components to be enabled or disabled
     *
     * @param enable
     */
    private void setMyContributionEnabled(boolean enable) {
        cmbVehicle.setEnabled(enable);
        txtManHours.setEnabled(enable);
        tblRoleTime.setEnabled(enable);
    }

    /**
     * Enables or disables the BM and CH button
     *
     * @param enable
     */
    private void setBM_CHFunctionEnabled(boolean enable) {
        btnBM.setEnabled(enable);
        btnCH.setEnabled(enable);
    }

    /**
     * Enables or disables the ST-button
     *
     * @param enable
     */
    private void setSTFunctionEnabled(boolean enable) {
        btnST.setEnabled(enable);
    }

    /**
     * Enables or disables the HL-button
     *
     * @param enable
     */
    private void setHLFunctionEnabled(boolean enable) {
        btnHL.setEnabled(enable);
    }

    /**
     * Enables or disables all functions (CH, BM, HL, ST)
     *
     * @param enable
     */
    private void setAllFunctionsEnabled(boolean enable) {
        setBM_CHFunctionEnabled(enable);
        setSTFunctionEnabled(enable);
        setHLFunctionEnabled(enable);
    }

    /**
     * Clears the Info panel
     */
    private void clearInfoBox() {
        txtIncidentName.setText("");
        txtIncidentTime.setText("");
        ((JTextField) dateChooser.getDateEditor().getUiComponent()).setText("");
        cmbIncidentType.setSelectedIndex(0);
        roleTimeModel.setRoleTimeList(EMPTY_ARRAY_LIST);
        clearMyContribution();
    }

    /**
     * Clears and disables the My Contribution panel
     */
    private void clearMyContribution() {
        lstManpower.clearSelection();
        cmbVehicle.setSelectedIndex(0);
        cmbVehicle.setEnabled(false);
        txtManHours.setText(MessageDialog.getInstance().firemanTextHours());
        txtManHours.setEnabled(false);
        setSTFunctionEnabled(false);
        setBM_CHFunctionEnabled(false);
        setHLFunctionEnabled(false);
    }

    /**
     * Invokes this method when the Incident ComboBox changes values
     */
    private void onComboIncidentChange() {
        setManpowerEnabled(cmbIncident.getSelectedIndex() != 0);
        btnNext.setEnabled(cmbIncident.getSelectedIndex() != 0);
        lstManpower.clearSelection();
        if (cmbIncident.getSelectedIndex() != 0) {
            BEIncident selected = (BEIncident) cmbIncident.getSelectedItem();
            txtIncidentName.setText(selected.getM_incidentName());
            dateChooser.setDate(selected.getM_date());
            txtIncidentTime.setText("" + selected.getM_time());
            txtIncidentTime.setText(txtIncidentTime.getText().substring(0, 5));
            cmbIncidentType.setSelectedItem(selected.getM_incidentType());
            roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime(selected));
            clearMyContribution();

        } else {
            clearInfoBox();
        }

    }

    /**
     * Invokes this method when the Save button is pressed
     */
    private void onClickSave() {
        if (txtIncidentName.getText().isEmpty()
                || cmbIncidentType.getSelectedIndex() == 0
                || ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().isEmpty()
                || txtIncidentTime.getText().isEmpty()) {
            MessageDialog.getInstance().saveDialog();
        } else {
            String incidentname = txtIncidentName.getText();
            java.util.Date utilDate = dateChooser.getDate();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            Time time = java.sql.Time.valueOf(txtIncidentTime.getText() + ":00");
            BEIncidentType incidenttype = (BEIncidentType) cmbIncidentType.getSelectedItem();
            Boolean isdone = false;
            BEIncident selected;
            if (cmbIncident.getSelectedIndex() != 0) {
                selected = (BEIncident) cmbIncident.getSelectedItem();
                updateIncident(selected, incidentname, sqlDate, time, incidenttype);
            } else {
                selected = new BEIncident(incidentname, sqlDate, time, incidenttype, isdone);
                BLLFireman.getInstance().createIncident(selected);
                cmbIncident.addItem(selected);
            }
            cmbIncident.repaint();
            cmbIncident.setSelectedItem(selected);
            setManpowerEnabled(true);
        }

    }

    /**
     * Updates the current selected Incident
     *
     * @param selected
     * @param incidentname
     * @param sqlDate
     * @param time
     * @param incidenttype
     */
    private void updateIncident(BEIncident selected, String incidentname, Date sqlDate, Time time, BEIncidentType incidenttype) {
        selected.setM_incidentName(incidentname);
        selected.setM_date(sqlDate);
        selected.setM_time(time);
        selected.setM_incidentType(incidenttype);
        selected.isM_isDone();
        BLLFireman.getInstance().updateFireman(selected);
    }

    /**
     * @return BERoleTime with links to all the relevant BE classes
     */
    private BERoleTime myContribution(boolean station) {
        BEIncident incident = (BEIncident) cmbIncident.getSelectedItem();
        BEFireman fireman = (BEFireman) lstManpower.getSelectedValue();
        boolean isOnStaion = station;
        BEVehicle vehicle = null;
        if (cmbVehicle.getSelectedIndex() != 0) {
            vehicle = (BEVehicle) cmbVehicle.getSelectedItem();
        }
        int time = Integer.parseInt(txtManHours.getText());
        BERoleTime roletime = new BERoleTime(incident, fireman, isOnStaion, null, vehicle, time);
        return roletime;
    }

    /**
     * Invokes this method when the ST button is pressed
     */
    private void onClickST() {
        BLLFireman.getInstance().createSTOnIncident(myContribution(true));
        roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime((BEIncident) cmbIncident.getSelectedItem()));
    }

    /**
     * Invokes this method when the BM button is pressed
     */
    private void onClickBM() {
        BLLFireman.getInstance().createBMOnIncident(myContribution(false));
        roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime((BEIncident) cmbIncident.getSelectedItem()));
    }

    /**
     * Invokes this method when the CH button is pressed
     */
    private void onClickCH() {
        BLLFireman.getInstance().createCHOnIncident(myContribution(false));
        roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime((BEIncident) cmbIncident.getSelectedItem()));
    }

    /**
     * Invokes this method when the HL button is pressed
     */
    private void onClickHL() {
        BLLFireman.getInstance().createHLOnIncident(myContribution(false));
        roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime((BEIncident) cmbIncident.getSelectedItem()));
    }

    private void onClickNext() {
        JFrame guiteamleader = GUITeamLeader.getInstance();
        GUITeamLeader.getInstance().setIncident((BEIncident) cmbIncident.getSelectedItem());
        guiteamleader.setVisible(true);

    }

    /**
     * Invokes this method when the list of firemen changes the selected value
     */
    private void onListChange() {
        setMyContributionEnabled(lstManpower.getSelectedIndex() != -1);
        CheckHoursAndVehicles();
    }

    /**
     * Enables and disables the buttons depending on conditions
     */
    private void CheckHoursAndVehicles() {
        if (lstManpower.isSelectionEmpty()) {
            setAllFunctionsEnabled(!lstManpower.isSelectionEmpty());
            return;
        }
        setSTFunctionEnabled(isTextIntegers());
        if (isTextIntegers()) {
            setBM_CHFunctionEnabled(cmbVehicle.getSelectedIndex() != 0);
            if (cmbVehicle.getSelectedIndex() != 0) {
                setHLFunctionEnabled(((BEFireman) lstManpower.getSelectedValue()).isM_isTeamLeader());
            }
        } else {
            setBM_CHFunctionEnabled(false);
            setHLFunctionEnabled(false);
        }

    }

    /**
     * Checks if the ManHours text field is empty or contains the premade text
     *
     * @return boolean
     */
    private boolean isTextIntegers() {
        return !(txtManHours.getText().isEmpty() || txtManHours.getText().equals(MessageDialog.getInstance().firemanTextHours()));
    }

    /**
     * Listener for the Incident ComboBox
     */
    private class cmbAction implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getSource().equals(cmbIncident)) {
                onComboIncidentChange();
            } else {
                CheckHoursAndVehicles();
            }
        }
    }

    /**
     * Listener for all the buttons
     */
    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnSave)) {
                onClickSave();
            } else if (e.getSource().equals(btnST)) {
                onClickST();
            } else if (e.getSource().equals(btnBM)) {
                onClickBM();
            } else if (e.getSource().equals(btnCH)) {
                onClickCH();
            } else if (e.getSource().equals(btnHL)) {
                onClickHL();
            } else if (e.getSource().equals(btnNext)) {
                onClickNext();
            }
        }
    }

    /**
     * Listener for the ManPower list
     */
    private class lstAction implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            onListChange();
        }
    }

    /**
     * Listener for the hours text field
     */
    private class txtAction extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            CheckHoursAndVehicles();
        }
    }

    /**
     * focusListener for the text field
     */
    private class txtFocus extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            txtManHours.setText("");
            CheckHoursAndVehicles();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstManpower = new javax.swing.JList();
        jPanel6 = new javax.swing.JPanel();
        cmbVehicle = new javax.swing.JComboBox();
        txtManHours = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        btnCH = new javax.swing.JButton();
        btnST = new javax.swing.JButton();
        btnBM = new javax.swing.JButton();
        btnHL = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIncidentTime = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        txtIncidentName = new javax.swing.JTextField();
        cmbIncidentType = new javax.swing.JComboBox();
        btnSave = new javax.swing.JButton();
        lblLogo = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRoleTime = new javax.swing.JTable();
        btnError = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        cmbIncident = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mandskab", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel3.setLayout(null);

        lstManpower.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        lstManpower.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Michael", "Andreas", "Morten" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lstManpower);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(20, 40, 240, 430);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Min Indsats", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel6.setLayout(null);

        cmbVehicle.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel6.add(cmbVehicle);
        cmbVehicle.setBounds(20, 40, 180, 40);

        txtManHours.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel6.add(txtManHours);
        txtManHours.setBounds(20, 100, 180, 40);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Funktion"));
        jPanel1.setLayout(null);

        btnCH.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnCH.setText("CH");
        jPanel1.add(btnCH);
        btnCH.setBounds(100, 70, 80, 40);

        btnST.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnST.setText("ST");
        jPanel1.add(btnST);
        btnST.setBounds(10, 20, 80, 40);

        btnBM.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnBM.setText("BM");
        jPanel1.add(btnBM);
        btnBM.setBounds(100, 20, 80, 40);

        btnHL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnHL.setText("HL");
        jPanel1.add(btnHL);
        btnHL.setBounds(10, 70, 80, 40);

        jPanel6.add(jPanel1);
        jPanel1.setBounds(10, 160, 190, 120);

        jPanel3.add(jPanel6);
        jPanel6.setBounds(270, 180, 210, 290);

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setText("Foto");
        lblImage.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel3.add(lblImage);
        lblImage.setBounds(270, 40, 210, 130);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel4.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("kl.");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(330, 40, 20, 22);
        jPanel4.add(txtIncidentTime);
        txtIncidentTime.setBounds(360, 30, 110, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("dato");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(150, 40, 40, 22);

        dateChooser.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(dateChooser);
        dateChooser.setBounds(200, 30, 120, 40);

        txtIncidentName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel4.add(txtIncidentName);
        txtIncidentName.setBounds(20, 80, 300, 40);

        cmbIncidentType.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel4.add(cmbIncidentType);
        cmbIncidentType.setBounds(20, 30, 120, 40);

        btnSave.setText("Gem");
        jPanel4.add(btnSave);
        btnSave.setBounds(360, 80, 110, 40);

        lblLogo.setFont(new java.awt.Font("Calibri", 0, 72)); // NOI18N
        lblLogo.setText("LOGO");

        btnNext.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnNext.setText("Næste");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fremmødeliste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel5.setLayout(null);

        tblRoleTime.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        tblRoleTime.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblRoleTime);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 720, 430);

        btnError.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnError.setText("Fejl & Mangler");

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vælg Indsats", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel7.setLayout(null);

        cmbIncident.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jPanel7.add(cmbIncident);
        cmbIncident.setBounds(20, 30, 450, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(286, 286, 286)
                .addComponent(lblLogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnError, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(919, 919, 919)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnError, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBM;
    private javax.swing.JButton btnCH;
    private javax.swing.JButton btnError;
    private javax.swing.JButton btnHL;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnST;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox cmbIncident;
    private javax.swing.JComboBox cmbIncidentType;
    private javax.swing.JComboBox cmbVehicle;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JList lstManpower;
    private javax.swing.JTable tblRoleTime;
    private javax.swing.JTextField txtIncidentName;
    private javax.swing.JTextField txtIncidentTime;
    private javax.swing.JTextField txtManHours;
    // End of variables declaration//GEN-END:variables
}
