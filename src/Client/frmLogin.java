/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Server.SocketManager;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JOptionPane;


public class frmLogin extends javax.swing.JFrame {

    /**
     * Creates new form MailClient
     */
    public frmLogin() {
        initComponents();
        txtEmail.setBackground(new Color(0,0,0,0));
        txtPassword.setBackground(new Color(0,0,0,0));
        lbldangky1.setBackground(new Color(0,0,0,0));
        btnLogin.setBackground(new Color(0,0,0,0));
        this.setLocationRelativeTo(null);
    }
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 1234;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbldangky = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel7 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnLogin = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        lbldangky1 = new java.awt.Label();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ĐĂNG NHẬP");
        setMinimumSize(new java.awt.Dimension(550, 359));
        setSize(new java.awt.Dimension(550, 550));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbldangky.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        lbldangky.setForeground(new java.awt.Color(255, 0, 0));
        lbldangky.setText("Đăng ký ở đây!");
        lbldangky.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldangkyMouseClicked(evt);
            }
        });
        getContentPane().add(lbldangky, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 320, -1, 20));

        txtPassword.setFont(new java.awt.Font("Roboto", 0, 15)); // NOI18N
        txtPassword.setText("1111");
        txtPassword.setBorder(null);
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });
        getContentPane().add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 270, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/textFrame.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 270, 271, 40));

        txtEmail.setBackground(new java.awt.Color(255, 153, 153));
        txtEmail.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtEmail.setText("ngocuyenlepham@gmail.com");
        txtEmail.setBorder(null);
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 290, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/textFrame.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 271, 40));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 25)); // NOI18N
        jLabel1.setText("Đăng nhập");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 130, -1));

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel3.setText(" Email:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jLabel2.setText(" Mật khẩu:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, -1, 20));

        btnLogin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/btnLogin.png"))); // NOI18N
        btnLogin.setBorder(null);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setSelected(true);
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 100, 40));

        btnExit.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/btnThoat.png"))); // NOI18N
        btnExit.setBorder(null);
        btnExit.setContentAreaFilled(false);
        btnExit.setSelected(true);
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        getContentPane().add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 350, 100, 40));

        jLabel4.setFont(new java.awt.Font("Roboto", 2, 14)); // NOI18N
        jLabel4.setText("**Chưa có tài khoản");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, -1, 40));

        lbldangky1.setBackground(new java.awt.Color(255, 255, 255));
        lbldangky1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbldangky1.setFont(new java.awt.Font("Roboto", 1, 15)); // NOI18N
        lbldangky1.setForeground(new java.awt.Color(255, 0, 0));
        lbldangky1.setText("Đăng ký ở đây!");
        lbldangky1.setVisible(false);
        lbldangky1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldangky1MouseClicked(evt);
            }
        });
        getContentPane().add(lbldangky1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nenBG.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed

        try {
            SocketManager socketManager = new SocketManager(SERVER_HOST, SERVER_PORT);
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            String requestType = "login";

            String response = socketManager.sendRequest(requestType, email, password);

            if ("Đăng Nhập Thành Công".equals(response)) {
                // UserManager.setUser(User.getUserByUsername(email, password)); 
                new frmMailClient().setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(this, "Email hoặc mật khẩu chưa chính xác!");
            }

            // Sau khi sử dụng xong, đảm bảo đóng kết nối
            socketManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPasswordActionPerformed

    private void lbldangkyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldangkyMouseClicked
        // TODO add your handling code here:
        frmRegister f = new frmRegister();
        f.setVisible(true);
    }//GEN-LAST:event_lbldangkyMouseClicked

    private void lbldangky1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldangky1MouseClicked
        frmRegister f = new frmRegister();
        f.setVisible(true);
    }//GEN-LAST:event_lbldangky1MouseClicked

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
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        System.out.println("Client");
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmLogin().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbldangky;
    private java.awt.Label lbldangky1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables

}
