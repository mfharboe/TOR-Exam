/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Morten
 */
public class GUITeamLeader extends javax.swing.JFrame {

    /**
     * Creates new form GUITeamLeader
     */
    public GUITeamLeader() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox3 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jPanel5 = new javax.swing.JPanel();
        jTextField4 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Eva Rapport Nr");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(370, 40, 150, 22);
        getContentPane().add(jTextField1);
        jTextField1.setBounds(190, 40, 160, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Indsatte Styrker", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel1.setLayout(null);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vælg Køretøj..." }));
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(20, 30, 140, 40);

        jCheckBox1.setText("Afvigelser");
        jPanel1.add(jCheckBox1);
        jCheckBox1.setBounds(170, 90, 93, 25);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Kørsels Type..." }));
        jPanel1.add(jComboBox3);
        jComboBox3.setBounds(170, 30, 140, 40);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(330, 30, 730, 150);

        jButton2.setText("Tilføj");
        jPanel1.add(jButton2);
        jButton2.setBounds(230, 140, 90, 40);

        jTextField9.setText("Antal...");
        jPanel1.add(jTextField9);
        jTextField9.setBounds(20, 80, 140, 40);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 120, 1080, 190);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forbrug", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel2.setLayout(null);

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vælg Materiel..." }));
        jPanel2.add(jComboBox4);
        jComboBox4.setBounds(18, 32, 290, 40);
        jPanel2.add(jTextField3);
        jTextField3.setBounds(20, 80, 90, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Liter/stk/Kg");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(120, 90, 100, 20);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(330, 30, 730, 150);

        jButton4.setText("Tilføj");
        jPanel2.add(jButton4);
        jButton4.setBounds(230, 140, 90, 40);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(10, 320, 1080, 190);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Brand Rapport Nr");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(370, 80, 150, 22);
        getContentPane().add(jTextField5);
        jTextField5.setBounds(530, 80, 160, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Indsats Leder");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 80, 120, 22);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("Melding");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 40, 120, 22);
        getContentPane().add(jTextField6);
        jTextField6.setBounds(530, 40, 160, 30);
        getContentPane().add(jTextField7);
        jTextField7.setBounds(190, 80, 160, 30);

        jButton3.setText("Gem og Afslut");
        getContentPane().add(jButton3);
        jButton3.setBounds(960, 660, 140, 40);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Oplysninger om skadelidte", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel3.setLayout(null);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Navn");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(30, 30, 120, 22);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Adresse");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(30, 70, 120, 22);
        jPanel3.add(jTextField2);
        jTextField2.setBounds(120, 30, 230, 30);
        jPanel3.add(jTextField8);
        jTextField8.setBounds(120, 70, 230, 30);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(710, 10, 380, 110);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Beretning", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel4.setLayout(null);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Vælg Beretning..." }));
        jPanel4.add(jComboBox2);
        jComboBox2.setBounds(20, 30, 350, 40);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ved ABA Alarm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 18))); // NOI18N
        jPanel5.setLayout(null);
        jPanel5.add(jTextField4);
        jTextField4.setBounds(190, 20, 160, 30);
        jPanel5.add(jTextField10);
        jTextField10.setBounds(190, 70, 160, 30);

        jLabel10.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel10.setText("Gruppe nummer");
        jPanel5.add(jLabel10);
        jLabel10.setBounds(20, 70, 130, 16);

        jLabel2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel2.setText("Detektor nummer");
        jPanel5.add(jLabel2);
        jLabel2.setBounds(20, 30, 140, 16);

        jPanel4.add(jPanel5);
        jPanel5.setBounds(20, 80, 360, 110);

        getContentPane().add(jPanel4);
        jPanel4.setBounds(10, 510, 390, 200);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bemærkninger", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Calibri", 0, 24))); // NOI18N
        jPanel6.setLayout(null);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jPanel6.add(jScrollPane3);
        jScrollPane3.setBounds(10, 30, 520, 160);

        getContentPane().add(jPanel6);
        jPanel6.setBounds(410, 510, 540, 200);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUITeamLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUITeamLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUITeamLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUITeamLeader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUITeamLeader().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JComboBox jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}