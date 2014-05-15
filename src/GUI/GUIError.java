package GUI;

import BE.BEError;
import BE.BEVehicle;
import BLL.BLLCreate;
import BLL.BLLError;
import BLL.BLLRead;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTextField;

public class GUIError extends javax.swing.JFrame {

    private static GUIError m_instance;

    private GUIError() {
        initComponents();
        this.setTitle(MessageDialog.getInstance().errorTitle());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initialSettings();
    }

    public static GUIError getInstance() {
        if (m_instance == null) {
            m_instance = new GUIError();
        }
        return m_instance;
    }

    /**
     * The initial settings for this class.
     */
    private void initialSettings() {
        addListeners();
        fillCmbVehicle();
        addColors();
        clearGUI();
    }

    /**
     * Adds Listeners.
     */
    private void addListeners() {
        btnAction btn = new btnAction();
        wndAction wnd = new wndAction();
        btnCancel.addActionListener(btn);
        btnOK.addActionListener(btn);
        this.addWindowListener(wnd);
    }

    /**
     * Adds color.
     */
    private void addColors() {
        this.getContentPane().setBackground(Color.WHITE);
        pnlCause.setBackground(Color.WHITE);
        pnlError.setBackground(Color.WHITE);
        pnlFaults.setBackground(Color.WHITE);
        pnlFilledBy.setBackground(Color.WHITE);
        pnlFiresuit.setBackground(Color.WHITE);
        pnlStatus.setBackground(Color.WHITE);
        cmbVehicle.setBackground(Color.WHITE);
        chkFireSuit.setBackground(Color.WHITE);
        chkOutOfOrder.setBackground(Color.WHITE);
        chkUrgent.setBackground(Color.WHITE);

    }

    /**
     * Fills the Vehicle ComboBox.
     */
    private void fillCmbVehicle() {
        cmbVehicle.addItem(MessageDialog.getInstance().cmbVehicle());
        for (BEVehicle beVehicle : BLLRead.getInstance().readAllVehicles()) {
            cmbVehicle.addItem(beVehicle);
        }
        MessageError.getInstance().printError();

    }

    /**
     * Clears the GUI
     */
    private void clearGUI() {
        txtCause.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtErrorDescription.setText(MessageDialog.getInstance().EMPTY_TEXT());
        txtFilledBy.setText(MessageDialog.getInstance().EMPTY_TEXT());
        ((JTextField) dateChooser.getDateEditor().getUiComponent()).setText(MessageDialog.getInstance().txtDate());
        chkFireSuit.setSelected(false);
        chkOutOfOrder.setSelected(false);
        chkUrgent.setSelected(false);
        cmbVehicle.setSelectedIndex(0);

    }

    /**
     * Checks whether or not all necessary information has been filled out
     *
     * @return true/false
     */
    private boolean isInformationFilled() {
        if (txtFilledBy.getText().isEmpty()) {
            MessageDialog.getInstance().dialogFillAllInformation();
            return false;
        }
        if (cmbVehicle.getSelectedIndex() == 0 && !chkFireSuit.isSelected()) {
            MessageDialog.getInstance().dialogChooseClothesOrVehicle();
            return false;
        }
        if (((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().equals(MessageDialog.getInstance().txtDate())) {
            MessageDialog.getInstance().dialogFillAllInformation();
            return false;
        }
        return true;
    }

    /**
     * Invokes this method when the OK button is pressed.
     */
    private void onClickOk() {
        if (isInformationFilled()) {
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
            BLLCreate.getInstance().createErrorReport(be);
            MessageError.getInstance().printError();
            onClickClose();
        }
    }

    /**
     * Invokes this method when the Cancel button is pressed.
     */
    private void onClickClose() {
        clearGUI();
        dispose();
    }

    /**
     * Listener for the buttons.
     */
    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnOK)) {
                onClickOk();
            }
            if (e.getSource().equals(btnCancel)) {
                onClickClose();

            }
        }
    }

    /**
     * WindowListener for this window
     */
    private class wndAction extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            onClickClose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlError = new javax.swing.JPanel();
        pnlFaults = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtErrorDescription = new javax.swing.JTextArea();
        pnlCause = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCause = new javax.swing.JTextArea();
        cmbVehicle = new javax.swing.JComboBox();
        pnlFilledBy = new javax.swing.JPanel();
        txtFilledBy = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        pnlStatus = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        chkUrgent = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        chkOutOfOrder = new javax.swing.JCheckBox();
        pnlFiresuit = new javax.swing.JPanel();
        chkFireSuit = new javax.swing.JCheckBox();
        jLabel4 = new javax.swing.JLabel();
        btnCancel = new javax.swing.JButton();
        btnOK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlError.setLayout(null);

        pnlFaults.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fejl/Mangler", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        pnlFaults.setLayout(null);

        txtErrorDescription.setColumns(20);
        txtErrorDescription.setRows(5);
        jScrollPane1.setViewportView(txtErrorDescription);

        pnlFaults.add(jScrollPane1);
        jScrollPane1.setBounds(10, 30, 420, 160);

        pnlError.add(pnlFaults);
        pnlFaults.setBounds(10, 240, 440, 200);

        pnlCause.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Evt. Årsag", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        pnlCause.setLayout(null);

        txtCause.setColumns(20);
        txtCause.setRows(5);
        jScrollPane2.setViewportView(txtCause);

        pnlCause.add(jScrollPane2);
        jScrollPane2.setBounds(10, 30, 420, 160);

        pnlError.add(pnlCause);
        pnlCause.setBounds(10, 450, 440, 200);

        pnlError.add(cmbVehicle);
        cmbVehicle.setBounds(20, 100, 220, 40);

        pnlFilledBy.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Udfyldt af", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        pnlFilledBy.setLayout(null);
        pnlFilledBy.add(txtFilledBy);
        txtFilledBy.setBounds(10, 30, 220, 40);

        jLabel3.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel3.setText("Dato");
        pnlFilledBy.add(jLabel3);
        jLabel3.setBounds(250, 40, 36, 23);
        pnlFilledBy.add(dateChooser);
        dateChooser.setBounds(290, 30, 120, 40);

        pnlError.add(pnlFilledBy);
        pnlFilledBy.setBounds(10, 0, 440, 80);

        pnlStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        pnlStatus.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel1.setText("Haster");
        pnlStatus.add(jLabel1);
        jLabel1.setBounds(20, 20, 70, 30);
        pnlStatus.add(chkUrgent);
        chkUrgent.setBounds(130, 20, 30, 30);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("Ude Af Drift");
        pnlStatus.add(jLabel2);
        jLabel2.setBounds(20, 50, 87, 23);
        pnlStatus.add(chkOutOfOrder);
        chkOutOfOrder.setBounds(130, 50, 30, 21);

        pnlError.add(pnlStatus);
        pnlStatus.setBounds(250, 90, 200, 80);

        pnlFiresuit.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Brandragt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        pnlFiresuit.setLayout(null);
        pnlFiresuit.add(chkFireSuit);
        chkFireSuit.setBounds(130, 20, 30, 30);

        jLabel4.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel4.setText("Til Vask");
        pnlFiresuit.add(jLabel4);
        jLabel4.setBounds(20, 20, 87, 30);

        pnlError.add(pnlFiresuit);
        pnlFiresuit.setBounds(250, 170, 200, 60);

        btnCancel.setText("Fortryd");
        pnlError.add(btnCancel);
        btnCancel.setBounds(270, 660, 80, 40);

        btnOK.setText("OK");
        pnlError.add(btnOK);
        btnOK.setBounds(360, 660, 80, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlError, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(pnlError, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
                .addGap(20, 20, 20))
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pnlCause;
    private javax.swing.JPanel pnlError;
    private javax.swing.JPanel pnlFaults;
    private javax.swing.JPanel pnlFilledBy;
    private javax.swing.JPanel pnlFiresuit;
    private javax.swing.JPanel pnlStatus;
    private javax.swing.JTextArea txtCause;
    private javax.swing.JTextArea txtErrorDescription;
    private javax.swing.JTextField txtFilledBy;
    // End of variables declaration//GEN-END:variables
}
