package GUI;

import BE.BEFireman;
import BE.BEIncident;
import BE.BERoleTime;
import BE.BEVehicle;
import BLL.BLLCreate;
import BLL.BLLFireman;
import BLL.BLLRead;
import GUI.TableModel.TableModelRoleTime;
import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableRowSorter;

public class GUIFireman extends javax.swing.JFrame {

    private static GUIFireman m_instance;

    DefaultListModel<BEFireman> firemanListModel;
    TableRowSorter<TableModelRoleTime> roleTimeSorter;
    private TableModelRoleTime roleTimeModel;
    private final ArrayList<BERoleTime> EMPTY_ARRAY_LIST = new ArrayList<>();

    ImageIcon image;
    ImageIcon imageLogo;

    private final int BM = 1;
    private final int CH = 2;
    private final int HL = 3;
    private final int ST = 4;

    /**
     * Creates new form GUIFireman.
     */
    private GUIFireman() {
        this.setTitle(MessageDialog.getInstance().firemanTitle());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        initialSettings();

    }

    /**
     * @return m_instance of GUIFireman
     */
    public static GUIFireman getInstance() {
        if (m_instance == null) {
            m_instance = new GUIFireman();
        }
        return m_instance;
    }

    /**
     * THe initial settings for this class.
     */
    private void initialSettings() {
        addColors();
        setManpowerEnabled(false);
        setMyContributionEnabled(false);
        setAllFunctionsEnabled(false);
        addListeners();
        setTable();
        fillBoxes();
    }

    /**
     * Adds color and the logo.
     */
    private void addColors() {
        this.getContentPane().setBackground(Color.WHITE);
        pnlFunctions.setBackground(Color.WHITE);
        pnlManPower.setBackground(Color.WHITE);
        pnlAttendance.setBackground(Color.WHITE);
        pnlMyContribution.setBackground(Color.WHITE);
        pnlIncident.setBackground(Color.WHITE);
        cmbIncident.setBackground(Color.WHITE);
        cmbVehicle.setBackground(Color.WHITE);
        tblRoleTime.setBackground(Color.WHITE);
        imageLogo = new ImageIcon("ebr.jpg");
        lblLogo.setIcon(imageLogo);
    }

    /**
     * Adds Listeners.
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
        btnBM.addActionListener(btn);
        btnCH.addActionListener(btn);
        btnHL.addActionListener(btn);
        btnST.addActionListener(btn);
        btnTeamLeader.addActionListener(btn);
        btnError.addActionListener(btn);
        btnCreate.addActionListener(btn);
        cmbIncident.addItemListener(cmb);
        cmbVehicle.addItemListener(cmb);

    }

    
    /**
     * Fills all Comboboxes and Lists.
     */
    private void fillBoxes() {
        fillFiremanList();
        fillVehicleCombo();
        fillIncidentCombo();
    }

    /**
     * Sets the table with the initial settings
     */
    private void setTable(){
        btnTeamLeader.setEnabled(false);
        txtManHours.setText(MessageDialog.getInstance().txtHours());

        firemanListModel = new DefaultListModel<>();
        lstManpower.setModel(firemanListModel);
       
        roleTimeModel = new TableModelRoleTime(EMPTY_ARRAY_LIST);
        tblRoleTime.setModel(roleTimeModel);
        roleTimeSorter = new TableRowSorter<>(roleTimeModel);
        tblRoleTime.setRowSorter(roleTimeSorter);
    
    }
    /**
     * Fills the Fireman List ordered by HL-BM and then lastname.
     */
    private void fillFiremanList() {
        for (BEFireman befireman : BLLRead.getInstance().readAllFiremen()) {
            if (befireman.isM_isTeamLeader()) {
                firemanListModel.addElement(befireman);
            }
        }
        for (BEFireman befireman : BLLRead.getInstance().readAllFiremen()) {
            if (!befireman.isM_isTeamLeader()) {
                firemanListModel.addElement(befireman);
            }
        }
    }

    /**
     * Fills the Vehicle ComboBox.
     */
    private void fillVehicleCombo() {
        cmbVehicle.addItem(MessageDialog.getInstance().cmbVehicle());
        for (BEVehicle bevehicle : BLLRead.getInstance().readAllVehicles()) {
            cmbVehicle.addItem(bevehicle);
        }
    }

    /**
     * Fills the Incident ComboBox.
     */
    private void fillIncidentCombo() {
        cmbIncident.addItem(MessageDialog.getInstance().cmbIncidents());
        for (BEIncident beincident : BLLRead.getInstance().readAllIncidents()) {
            cmbIncident.addItem(beincident);
        }
    }

    /**
     * Set the ManPower List to be enabled or disabled
     *
     * @param enable
     */
    private void setManpowerEnabled(boolean enable) {
        lstManpower.setEnabled(enable);
    }

    /**
     * Sets the my contribution components to be enabled or disabled
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

    public void addToIncidentCombo(BEIncident incident) {
        cmbIncident.addItem(incident);
        BLLRead.getInstance().addToIncident(incident);
        cmbIncident.setSelectedItem(incident);
    }

    /**
     * Clears the Info panel.
     */
    private void clearInfoBox() {
        roleTimeModel.setRoleTimeList(EMPTY_ARRAY_LIST);
        clearMyContribution();
    }

    /**
     * Clears and disables the my contribution panel.
     */
    private void clearMyContribution() {
        lstManpower.clearSelection();
        cmbVehicle.setSelectedIndex(0);
        cmbVehicle.setEnabled(false);
        txtManHours.setText(MessageDialog.getInstance().txtHours());
        txtManHours.setEnabled(false);
        setSTFunctionEnabled(false);
        setBM_CHFunctionEnabled(false);
        setHLFunctionEnabled(false);
    }

    /**
     * Invokes this method when the Incident ComboBox changes values.
     */
    private void onComboIncidentChange() {
        setManpowerEnabled(cmbIncident.getSelectedIndex() != 0);
        btnTeamLeader.setEnabled(cmbIncident.getSelectedIndex() != 0);
        lstManpower.clearSelection();
        lblImage.setIcon(null);
        if (cmbIncident.getSelectedIndex() != 0) {
            BEIncident selected = (BEIncident) cmbIncident.getSelectedItem();
            roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime(selected));
            clearMyContribution();
        } else {
            clearInfoBox();
        }
    }

    /**
     * @return BERoleTime with links to all the relevant BE classes
     */
    private ArrayList<BERoleTime> myContribution(boolean station) {
        ArrayList<BERoleTime> tmpRoleTimes = new ArrayList<>();
        BEIncident incident = (BEIncident) cmbIncident.getSelectedItem();
        //BEFireman fireman = (BEFireman) lstManpower.getSelectedValue();
        ArrayList<BEFireman> fireman = (ArrayList<BEFireman>) lstManpower.getSelectedValuesList();
        boolean isOnStaion = station;
        BEVehicle vehicle = null;
        if (cmbVehicle.getSelectedIndex() != 0 && station == false) {
            vehicle = (BEVehicle) cmbVehicle.getSelectedItem();
        }
        int time = Integer.parseInt(txtManHours.getText());
        for(BEFireman selectedMen : fireman){
            BERoleTime roletime = new BERoleTime(incident, selectedMen, isOnStaion, null, vehicle, time);
            tmpRoleTimes.add(roletime);
        }
        return tmpRoleTimes;
    }
    /**
     * Invokes this method when the ST button is pressed.
     */
    private void onClickST() {
        BLLCreate.getInstance().createRoleOnIncident(myContribution(true), ST);
        roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime((BEIncident) cmbIncident.getSelectedItem()));
    }
    
      /**
     * Invokes this method when the BM button is pressed.
     */
    private void onClickBM() {
        BLLCreate.getInstance().createRoleOnIncident(myContribution(false), BM);
        roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime((BEIncident) cmbIncident.getSelectedItem()));
    }

    /**
     * Invokes this method when the CH button is pressed.
     */
    private void onClickCH() {
        BLLCreate.getInstance().createRoleOnIncident(myContribution(false), CH);
        roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime((BEIncident) cmbIncident.getSelectedItem()));
    }

    /**
     * Invokes this method when the HL button is pressed.
     */
    private void onClickHL() {
        BLLCreate.getInstance().createRoleOnIncident(myContribution(false), HL);
        roleTimeModel.setRoleTimeList(BLLFireman.getInstance().incidentToRoleTime((BEIncident) cmbIncident.getSelectedItem()));
    }

    /**
     * Starts the TeamLeader GUI.
     */
    private void onClickTeamLeader() {
        JFrame guiteamleader = GUITeamLeader.getInstance();
        GUITeamLeader.getInstance().setIncident((BEIncident) cmbIncident.getSelectedItem());
        guiteamleader.setVisible(true);

    }

    /**
     * Starts the ErrorReport GUI.
     */
    private void onClickErrorReport() {
        JFrame guierror = new GUIError();
        guierror.setVisible(true);
    }

    private void onClickCreate() {
        JFrame guicreateincident = GUICreateIncident.getInstance();
        guicreateincident.setVisible(true);
    }

    /**
     * Invokes this method when the list of firemen changes value.
     */
    private void onListChange() {
        if (!lstManpower.isSelectionEmpty()) {
            setMyContributionEnabled(lstManpower.getSelectedIndex() != -1);

            if (((BEFireman) lstManpower.getSelectedValue()).getM_photoPath() == null) {
                lblImage.setIcon(null);
            } else {
                image = new ImageIcon(((BEFireman) lstManpower.getSelectedValue()).getM_photoPath());
                lblImage.setIcon(image);
            }
            CheckHoursAndVehicles();
        }
    }

    /**
     * Enables and disables buttons depending on conditions.
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
        return !(txtManHours.getText().isEmpty() || txtManHours.getText().equals(MessageDialog.getInstance().txtHours()));
    }

    /**
     * Listener for the Incident ComboBox.
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
     * Listener for the buttons.
     */
    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnST)) {
                onClickST();
            } else if (e.getSource().equals(btnBM)) {
                onClickBM();
            } else if (e.getSource().equals(btnCH)) {
                onClickCH();
            } else if (e.getSource().equals(btnHL)) {
                onClickHL();
            } else if (e.getSource().equals(btnTeamLeader)) {
                onClickTeamLeader();
            } else if (e.getSource().equals(btnError)) {
                onClickErrorReport();
            } else if (e.getSource().equals(btnCreate)) {
                onClickCreate();
            }
        }
    }

    /**
     * Listener for the ManPower List.
     */
    private class lstAction implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            onListChange();
        }
    }

    /**
     * Listener for the hours Textfield.
     */
    private class txtAction extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            CheckHoursAndVehicles();
        }
    }

    /**
     * focusListener for the Textfield.
     */
    private class txtFocus extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            txtManHours.setText(MessageDialog.getInstance().EMPTY_TEXT());
            CheckHoursAndVehicles();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlManPower = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstManpower = new javax.swing.JList();
        pnlMyContribution = new javax.swing.JPanel();
        cmbVehicle = new javax.swing.JComboBox();
        txtManHours = new javax.swing.JTextField();
        pnlFunctions = new javax.swing.JPanel();
        btnCH = new javax.swing.JButton();
        btnST = new javax.swing.JButton();
        btnBM = new javax.swing.JButton();
        btnHL = new javax.swing.JButton();
        lblImage = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        btnTeamLeader = new javax.swing.JButton();
        pnlAttendance = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblRoleTime = new javax.swing.JTable();
        btnError = new javax.swing.JButton();
        pnlIncident = new javax.swing.JPanel();
        cmbIncident = new javax.swing.JComboBox();
        btnCreate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(null);

        pnlManPower.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mandskab", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlManPower.setLayout(null);

        lstManpower.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jScrollPane2.setViewportView(lstManpower);

        pnlManPower.add(jScrollPane2);
        jScrollPane2.setBounds(20, 40, 240, 430);

        pnlMyContribution.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Min Indsats", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        pnlMyContribution.setLayout(null);

        cmbVehicle.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlMyContribution.add(cmbVehicle);
        cmbVehicle.setBounds(20, 40, 180, 40);

        txtManHours.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlMyContribution.add(txtManHours);
        txtManHours.setBounds(20, 100, 180, 40);

        pnlFunctions.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Funktion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 15), java.awt.Color.black)); // NOI18N
        pnlFunctions.setLayout(null);

        btnCH.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnCH.setText("CH");
        pnlFunctions.add(btnCH);
        btnCH.setBounds(100, 70, 80, 40);

        btnST.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnST.setText("ST");
        pnlFunctions.add(btnST);
        btnST.setBounds(10, 20, 80, 40);

        btnBM.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnBM.setText("BM");
        pnlFunctions.add(btnBM);
        btnBM.setBounds(100, 20, 80, 40);

        btnHL.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnHL.setText("HL");
        pnlFunctions.add(btnHL);
        btnHL.setBounds(10, 70, 80, 40);

        pnlMyContribution.add(pnlFunctions);
        pnlFunctions.setBounds(10, 160, 190, 120);

        pnlManPower.add(pnlMyContribution);
        pnlMyContribution.setBounds(270, 180, 210, 290);

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pnlManPower.add(lblImage);
        lblImage.setBounds(270, 40, 210, 130);

        lblLogo.setFont(new java.awt.Font("Calibri", 0, 72)); // NOI18N
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnTeamLeader.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnTeamLeader.setText("Holdleder");

        pnlAttendance.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fremmødeliste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlAttendance.setLayout(null);

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

        pnlAttendance.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 720, 430);

        btnError.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnError.setText("Fejl & Mangler");

        pnlIncident.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vælg Indsats", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        pnlIncident.setLayout(null);

        cmbIncident.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        pnlIncident.add(cmbIncident);
        cmbIncident.setBounds(20, 30, 450, 40);

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnCreate.setText("Opret");
        pnlIncident.add(btnCreate);
        btnCreate.setBounds(360, 90, 110, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlIncident, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 730, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(pnlManPower, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnError, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(930, 930, 930)
                .addComponent(btnTeamLeader, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlIncident, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlManPower, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlAttendance, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnError, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTeamLeader, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBM;
    private javax.swing.JButton btnCH;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnError;
    private javax.swing.JButton btnHL;
    private javax.swing.JButton btnST;
    private javax.swing.JButton btnTeamLeader;
    private javax.swing.JComboBox cmbIncident;
    private javax.swing.JComboBox cmbVehicle;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JList lstManpower;
    private javax.swing.JPanel pnlAttendance;
    private javax.swing.JPanel pnlFunctions;
    private javax.swing.JPanel pnlIncident;
    private javax.swing.JPanel pnlManPower;
    private javax.swing.JPanel pnlMyContribution;
    private javax.swing.JTable tblRoleTime;
    private javax.swing.JTextField txtManHours;
    // End of variables declaration//GEN-END:variables
}
