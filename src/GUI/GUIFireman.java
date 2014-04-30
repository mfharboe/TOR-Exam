/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BE.BEFireman;
import BE.BEIncident;
import BE.BEIncidentType;
import BE.BEVehicle;
import BLL.BLLFireman;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultListModel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Morten
 */
public class GUIFireman extends javax.swing.JFrame {

    DefaultListModel<BEFireman> firemanListModel;
    
    public GUIFireman() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
        initializeSettings();
    }
    
    /**
     * THe initial settings for this class
     */
    private void initializeSettings(){
        firemanListModel = new DefaultListModel<>();
        lstManpower.setModel(firemanListModel);
        fillFiremanList();
        fillVehicleCombo();
        fillIncidentTypeCombo();
        fillIncidentCombo();
        addListeners();
    }
    
    /**
     * Adds Listeners to all buttons, comboboxes, etc.
     */
    private void addListeners(){
        cmbAction cmb = new cmbAction();
        cmbIncident.addItemListener(cmb);
    }
    /**
     * Fills Up Fireman ComboBox
     */
    private void fillFiremanList(){
        for(BEFireman befireman : BLLFireman.getInstance().readAllFiremen())
            firemanListModel.addElement(befireman);      
    }
    /**
     * Fills Up the Vehicle ComboBox
     */
    private void fillVehicleCombo(){
        cmbVehicle.addItem("Vælg Køretøj..");
        for(BEVehicle bevehicle : BLLFireman.getInstance().readAllVehicles())
            cmbVehicle.addItem(bevehicle);
    }
    /**
     * Fills Up the IncidentType ComboBox
     */
    private void fillIncidentTypeCombo(){
        cmbIncidentType.addItem("Vælg Type..");
        for(BEIncidentType beincidenttype : BLLFireman.getInstance().readAllIncidentTypes())
            cmbIncidentType.addItem(beincidenttype);
    }
    /**
     * Fills up the Incident ComboBox
     */
    private void fillIncidentCombo(){
        cmbIncident.addItem("Vælg Anden Indsats..");
        for(BEIncident beincident : BLLFireman.getInstance().readIncompleteIncidents())
            cmbIncident.addItem(beincident);
        cmbIncident.addItem("Lav ny indsats..");
    }
    
    /**
     * Invokes this method when the Incident ComboBox changes values
     */
    private void onComboChange(){
        if(cmbIncident.getSelectedIndex() != 0){
            BEIncident selected = (BEIncident) cmbIncident.getSelectedItem();
            txtIncidentName.setText(selected.getM_incidentName());
            dateChooser.setDate(selected.getM_date());
            txtIncidentTime.setText("" + selected.getM_time());
            cmbIncidentType.setSelectedItem(selected.getM_incidentType());
        }
    }

    /**
     * Listener for the Incident ComboBox
     */
    private class cmbAction implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
            onComboChange();
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
        cmbIncident = new javax.swing.JComboBox();
        lblLogo = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAttendance = new javax.swing.JTable();
        btnError = new javax.swing.JButton();
        btnFiresuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(null);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mandskab", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel3.setLayout(null);

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

        jPanel6.add(cmbVehicle);
        cmbVehicle.setBounds(20, 40, 180, 40);

        txtManHours.setText("Antal Timer...");
        jPanel6.add(txtManHours);
        txtManHours.setBounds(20, 100, 180, 40);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Funktion"));
        jPanel1.setLayout(null);

        btnCH.setText("CH");
        jPanel1.add(btnCH);
        btnCH.setBounds(100, 70, 80, 40);

        btnST.setText("ST");
        jPanel1.add(btnST);
        btnST.setBounds(10, 20, 80, 40);

        btnBM.setText("BM");
        jPanel1.add(btnBM);
        btnBM.setBounds(100, 20, 80, 40);

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

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Indsats", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel4.setLayout(null);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("kl.");
        jPanel4.add(jLabel5);
        jLabel5.setBounds(330, 100, 30, 22);
        jPanel4.add(txtIncidentTime);
        txtIncidentTime.setBounds(360, 90, 110, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Dato");
        jPanel4.add(jLabel7);
        jLabel7.setBounds(150, 100, 50, 22);

        dateChooser.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(dateChooser);
        dateChooser.setBounds(200, 90, 120, 40);
        jPanel4.add(txtIncidentName);
        txtIncidentName.setBounds(20, 40, 450, 40);

        jPanel4.add(cmbIncidentType);
        cmbIncidentType.setBounds(20, 90, 120, 40);

        jPanel4.add(cmbIncident);
        cmbIncident.setBounds(20, 140, 450, 40);

        lblLogo.setFont(new java.awt.Font("Calibri", 0, 72)); // NOI18N
        lblLogo.setText("LOGO");

        btnSave.setText("Næste");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fremmødeliste", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel5.setLayout(null);

        tblAttendance.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblAttendance);

        jPanel5.add(jScrollPane3);
        jScrollPane3.setBounds(10, 40, 720, 430);

        btnError.setText("Fejl & Mangler");

        btnFiresuit.setText("Send Brandragt til Vask");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(230, 230, 230)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnFiresuit, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(btnError, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(720, 720, 720)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(lblLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFiresuit, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnError, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBM;
    private javax.swing.JButton btnCH;
    private javax.swing.JButton btnError;
    private javax.swing.JButton btnFiresuit;
    private javax.swing.JButton btnHL;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JList lstManpower;
    private javax.swing.JTable tblAttendance;
    private javax.swing.JTextField txtIncidentName;
    private javax.swing.JTextField txtIncidentTime;
    private javax.swing.JTextField txtManHours;
    // End of variables declaration//GEN-END:variables
}
