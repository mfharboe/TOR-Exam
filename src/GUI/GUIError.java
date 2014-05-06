package GUI;

import BE.BEError;
import BE.BEVehicle;
import BLL.BLLError;
import BLL.BLLFireman;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;

public class GUIError extends javax.swing.JFrame {

    public GUIError() {
        initComponents();
        initializeSettings();

    }

    private void initializeSettings() {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        fillCmbVehicle();
        addListeners();
    }

    private void addListeners() {
        btnAction btn = new btnAction();
        btnCancel.addActionListener(btn);
        btnOK.addActionListener(btn);
    }

    private void fillCmbVehicle() {
        cmbVehicle.addItem(MessageDialog.getInstance().firemanComboVehicle());
        for (BEVehicle beVehicle : BLLFireman.getInstance().readAllVehicles()) {
            cmbVehicle.addItem(beVehicle);
        }

    }

    private void onClickOk() {
        BEVehicle vehicleOdinNumner;
        if (cmbVehicle.getSelectedIndex() != 0) {
            vehicleOdinNumner = (BEVehicle) cmbVehicle.getSelectedItem();
        } else {
            vehicleOdinNumner = null;
        }
        String filledBy = txtFilledBy.getText();
        java.util.Date utilDate = dateChooser.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Boolean outOfOrder = chkOutOfOrder.isSelected();
        boolean urgent = chkUrgent.isSelected();
        String description = txtErrorDescription.getText();
        String cause = txtCause.getText();
        boolean suitWash = chkFireSuit.isSelected();
        BEError be = new BEError(vehicleOdinNumner, filledBy, sqlDate, outOfOrder, urgent, description, cause, suitWash);
        BLLError.getInstance().createErrorReport(be);
        MessageDialog.getInstance().ErrorConfirmMessageApproved();
        dispose();

    }

    private void onClickCancel() {
        dispose();
    }

    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnOK)) {
                onClickOk();
            }
            if (e.getSource().equals(btnCancel)) {
                onClickCancel();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtErrorDescription = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCause = new javax.swing.JTextArea();
        cmbVehicle = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        txtFilledBy = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chkUrgent = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        chkOutOfOrder = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        chkFireSuit = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fejl Rapport", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel1.setLayout(null);
        jPanel1.add(jPanel5);
        jPanel5.setBounds(140, 549, 10, 10);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fejl/Mangler", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel6.setLayout(null);

        txtErrorDescription.setColumns(20);
        txtErrorDescription.setRows(5);
        jScrollPane1.setViewportView(txtErrorDescription);

        jPanel6.add(jScrollPane1);
        jScrollPane1.setBounds(10, 30, 420, 160);

        jPanel1.add(jPanel6);
        jPanel6.setBounds(10, 270, 440, 200);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Evt. Årsag", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel7.setLayout(null);

        txtCause.setColumns(20);
        txtCause.setRows(5);
        jScrollPane2.setViewportView(txtCause);

        jPanel7.add(jScrollPane2);
        jScrollPane2.setBounds(10, 30, 420, 160);

        jPanel1.add(jPanel7);
        jPanel7.setBounds(10, 480, 440, 200);

        jPanel1.add(cmbVehicle);
        cmbVehicle.setBounds(20, 130, 220, 40);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Udfyld af"));
        jPanel4.setLayout(null);
        jPanel4.add(txtFilledBy);
        txtFilledBy.setBounds(10, 30, 220, 40);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Dato");
        jPanel4.add(jLabel3);
        jLabel3.setBounds(240, 40, 36, 23);
        jPanel4.add(dateChooser);
        dateChooser.setBounds(290, 30, 120, 40);

        jPanel1.add(jPanel4);
        jPanel4.setBounds(10, 30, 420, 80);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status på Fejl Rapport", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel8.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("Haster");
        jPanel8.add(jLabel1);
        jLabel1.setBounds(20, 20, 70, 30);
        jPanel8.add(chkUrgent);
        chkUrgent.setBounds(130, 20, 30, 30);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("Ude Af Drift");
        jPanel8.add(jLabel2);
        jLabel2.setBounds(20, 50, 87, 23);
        jPanel8.add(chkOutOfOrder);
        chkOutOfOrder.setBounds(130, 50, 30, 25);

        jPanel1.add(jPanel8);
        jPanel8.setBounds(250, 120, 190, 80);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Brandragt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel2.setLayout(null);
        jPanel2.add(chkFireSuit);
        chkFireSuit.setBounds(130, 20, 30, 30);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Til Vask");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 20, 87, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(250, 200, 190, 60);

        btnCancel.setText("Fortryd");

        btnOK.setText("OK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox chkFireSuit;
    private javax.swing.JCheckBox chkOutOfOrder;
    private javax.swing.JCheckBox chkUrgent;
    private javax.swing.JComboBox cmbVehicle;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtCause;
    private javax.swing.JTextArea txtErrorDescription;
    private javax.swing.JTextField txtFilledBy;
    // End of variables declaration//GEN-END:variables
}
