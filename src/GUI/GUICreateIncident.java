package GUI;

import BE.BEIncident;
import BE.BEIncidentType;
import BLL.BLLCreate;
import BLL.BLLRead;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import javax.swing.JTextField;

public class GUICreateIncident extends javax.swing.JFrame {

    private static GUICreateIncident m_instance;

    /**
     * Creates new form GUICreateIncident
     */
    private GUICreateIncident() {
        initComponents();
        this.setTitle(MessageDialog.getInstance().createIncidentTitle());
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initialSettings();
        addColors();
    }

    /**
     * @return m_instance of GUICreateIncident
     */
    public static GUICreateIncident getInstance() {
        if (m_instance == null) {
            m_instance = new GUICreateIncident();
        }
        return m_instance;
    }

    private void initialSettings() {
        addListeners();
        fillIncidentTypeCombo();
        clearGUI();
    }

    private void addColors() {
        this.getContentPane().setBackground(Color.WHITE);
        cmbIncidentType.setBackground(Color.WHITE);
    }

    private void addListeners() {
        btnAction btn = new btnAction();
        txtFocus txtFc = new txtFocus();
        wndAction wnd = new wndAction();
        btnCancel.addActionListener(btn);
        btnCreate.addActionListener(btn);
        txtIncidentName.addFocusListener(txtFc);
        txtIncidentTime.addFocusListener(txtFc);
        this.addWindowListener(wnd);
    }

    private void fillIncidentTypeCombo() {
        cmbIncidentType.addItem(MessageDialog.getInstance().cmbIncidentType());
        for (BEIncidentType beincidenttype : BLLRead.getInstance().readIncidentTypes()) {
            cmbIncidentType.addItem(beincidenttype);
        }
    }

    private void clearGUI() {
        cmbIncidentType.setSelectedIndex(0);
        ((JTextField) dateChooser.getDateEditor().getUiComponent()).setText(MessageDialog.getInstance().txtDate());
        resetIncidentName();
        resetIncidentTime();
    }

    private void resetIncidentName() {
        txtIncidentName.setText(MessageDialog.getInstance().txtIncidentName());
    }

    private void resetIncidentTime() {
        txtIncidentTime.setText(MessageDialog.getInstance().txtIncidentTime());
    }

    private void clearIncidentName() {
        txtIncidentName.setText(MessageDialog.getInstance().EMPTY_TEXT());
    }

    private void clearIncidentTime() {
        txtIncidentTime.setText(MessageDialog.getInstance().EMPTY_TEXT());
    }

    private boolean isInformationFilled() {
        if (txtIncidentName.getText().isEmpty() || txtIncidentName.getText().equals(MessageDialog.getInstance().txtIncidentName())) {
            return false;
        }
        if (txtIncidentTime.getText().isEmpty() || txtIncidentTime.getText().equals(MessageDialog.getInstance().txtIncidentTime())) {
            return false;
        }
        if (cmbIncidentType.getSelectedIndex() == 0) {
            return false;
        }
        if (((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().isEmpty()) {
            return false;
        }
        if (((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().equals(MessageDialog.getInstance().txtDate())) {
            return false;
        }
        return true;
    }

    private BEIncident getMyInformation() {
        BEIncidentType incidentType = (BEIncidentType) cmbIncidentType.getSelectedItem();
        java.util.Date utilDate = dateChooser.getDate();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Time time = java.sql.Time.valueOf(txtIncidentTime.getText() + ":00");
        String incidentName = txtIncidentName.getText();
        Boolean isdone = false;
        BEIncident incident = new BEIncident(incidentName, sqlDate, time, incidentType, isdone);
        return incident;
    }

    private void onClickClose() {
        clearGUI();
        this.dispose();
    }

    private void onClickCreate() {
        if (isInformationFilled()) {
            BEIncident tmpIncident = getMyInformation();
            if (BLLCreate.getInstance().createIncident(tmpIncident)) {

                BLLCreate.getInstance().createInitialIncidentDetails(tmpIncident);

                GUIFireman.getInstance().addToIncidentCombo(tmpIncident);

                onClickClose();
            }
        } else {
            MessageDialog.getInstance().dialogFillAllInformation();
        }
    }

    private class btnAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(btnCancel)) {
                onClickClose();
            } else if (e.getSource().equals(btnCreate)) {
                onClickCreate();
            }
        }
    }

    private class txtFocus extends FocusAdapter {

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource().equals(txtIncidentName)) {
                clearIncidentName();
            }

            if (e.getSource().equals(txtIncidentTime)) {
                clearIncidentTime();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (txtIncidentName.getText().isEmpty()) {
                resetIncidentName();
            }
            if (txtIncidentTime.getText().isEmpty()) {
                resetIncidentTime();
            }
        }
    }

    private class wndAction extends WindowAdapter {

        @Override
        public void windowClosing(WindowEvent e) {
            onClickClose();
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

        cmbIncidentType = new javax.swing.JComboBox();
        txtIncidentName = new javax.swing.JTextField();
        lblDate = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        lblTime = new javax.swing.JLabel();
        txtIncidentTime = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmbIncidentType.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        txtIncidentName.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N

        lblDate.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblDate.setText("dato");

        dateChooser.setDateFormatString("yyyy-MM-dd");

        lblTime.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblTime.setText("kl.");

        btnCancel.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnCancel.setText("Fortryd");

        btnCreate.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        btnCreate.setText("Opret");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIncidentName, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(cmbIncidentType, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(lblDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(20, 20, 20)
                            .addComponent(lblTime, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(txtIncidentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(240, 240, 240)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbIncidentType, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIncidentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDate)
                            .addComponent(lblTime))))
                .addGap(10, 10, 10)
                .addComponent(txtIncidentName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreate;
    private javax.swing.JComboBox cmbIncidentType;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextField txtIncidentName;
    private javax.swing.JTextField txtIncidentTime;
    // End of variables declaration//GEN-END:variables
}
