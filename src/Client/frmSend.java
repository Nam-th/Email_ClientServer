
package Client;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class frmSend extends javax.swing.JFrame {

    /**
     * Creates new form frmSend
     */
    public frmSend() {
        initComponents();
        txtTieuDe.setBackground(new Color(0,0,0,0));
        txtTepdinhkem.setBackground(new Color(0,0,0,0));
        txtNoiDung.setBackground(new Color(0,0,0,0));
        txtNguoinhan.setBackground(new Color(0,0,0,0));
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

        fcpath = new javax.swing.JFileChooser();
        btnBack = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        radCC = new javax.swing.JRadioButton();
        radBCC = new javax.swing.JRadioButton();
        txtNguoinhan = new javax.swing.JTextField();
        txtTieuDe = new javax.swing.JTextField();
        txtNoiDung = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        txtTepdinhkem = new javax.swing.JTextField();
        btnchonduongdan = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SOẠN THƯ");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBack.setBorder(null);
        btnBack.setContentAreaFilled(false);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 400, 150, 30));

        btnSend.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSend.setBorder(null);
        btnSend.setContentAreaFilled(false);
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });
        getContentPane().add(btnSend, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 400, 110, 30));

        radCC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        radCC.setText("CC");
        getContentPane().add(radCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, -1, 30));

        radBCC.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        radBCC.setText("BCC");
        getContentPane().add(radBCC, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 180, 50, 30));

        txtNguoinhan.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtNguoinhan.setBorder(null);
        getContentPane().add(txtNguoinhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 240, 30));

        txtTieuDe.setFont(new java.awt.Font("Roboto", 0, 14)); // NOI18N
        txtTieuDe.setBorder(null);
        getContentPane().add(txtTieuDe, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 290, 40));

        txtNoiDung.setColumns(20);
        txtNoiDung.setFont(new java.awt.Font("Roboto", 0, 13)); // NOI18N
        txtNoiDung.setRows(5);
        txtNoiDung.setText("Dear ...,");
        txtNoiDung.setBorder(null);
        txtNoiDung.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtNoiDung.setDragEnabled(true);
        txtNoiDung.setOpaque(false);
        getContentPane().add(txtNoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 360, 140));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(554, 51, -1, -1));

        txtTepdinhkem.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        txtTepdinhkem.setBorder(null);
        getContentPane().add(txtTepdinhkem, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 330, 40));

        btnchonduongdan.setBorder(null);
        btnchonduongdan.setContentAreaFilled(false);
        btnchonduongdan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchonduongdanActionPerformed(evt);
            }
        });
        getContentPane().add(btnchonduongdan, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 20, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nenGuiMail.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        //System.out.println("tset" + radCC.isSelected());
        try {
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);

            // Mở luồng gửi dữ liệu lên server
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Gọi phương thức để gửi email
            String recipient = (txtNguoinhan.getText()); // Lấy địa chỉ người nhận từ giao diện
            String subject = txtTieuDe.getText(); // Lấy tiêu đề từ giao diện
            String content = txtNoiDung.getText(); // Lấy nội dung từ giao diện

            // Ghi các thông tin cần gửi lên server
            outputStream.writeUTF("guimail");
            outputStream.writeUTF(recipient);
            outputStream.writeUTF(subject);
            if (radCC.isSelected()) {
                outputStream.writeUTF("CC");
            } else if (radBCC.isSelected()) {
                outputStream.writeUTF("BCC");
            } else {
                outputStream.writeUTF("NO");
            }
            outputStream.writeUTF(content);
            // Send the attachment
            //String attachmentFilePath = txtTepdinhkem.getText();
            if (txtTepdinhkem.getText() != "") {
                sendAttachment(outputStream, txtTepdinhkem.getText());
            }

            // Nhận kết quả từ server
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            String response = inputStream.readUTF();
            JOptionPane.showMessageDialog(this, response);

            // sendMailToServer(clientSocket, outputStream, recipient, subject, content);
            // Sau khi gửi email, bạn có thể tiếp tục sử dụng kết nối và luồng cho các yêu cầu khác nếu cần.
            // Đóng kết nối khi không cần thiết nữa
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_btnSendActionPerformed

    private void btnchonduongdanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchonduongdanActionPerformed
        this.fcpath.setVisible(true);

        fcpath.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if (this.fcpath.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                txtTepdinhkem.setText(fcpath.getSelectedFile().getCanonicalPath());
            } catch (Exception e) {
                {
                    JOptionPane.showMessageDialog(this, e);
                }
            }
        }
    }//GEN-LAST:event_btnchonduongdanActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        frmMailClient f = new frmMailClient();
        f.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void sendAttachment(DataOutputStream outputStream, String attachmentFilePath) throws IOException {
        File file = new File(attachmentFilePath);

        if (file.exists()) {
            JOptionPane.showMessageDialog(this, "Attachment file be found.");
            outputStream.writeUTF("attachment"); // Indicate that an attachment is being sent
            outputStream.writeUTF(file.getName()); // Send the file name to the server

            // Before sending the attachment, inform the server about its length
            long attachmentLength = file.length();
            outputStream.writeLong(attachmentLength);

            // Send the attachment
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        } else {
            outputStream.writeUTF("no_attachment");
        }
    }

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
            java.util.logging.Logger.getLogger(frmSend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSend.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSend().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnchonduongdan;
    private javax.swing.JFileChooser fcpath;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton radBCC;
    private javax.swing.JRadioButton radCC;
    private javax.swing.JTextField txtNguoinhan;
    private javax.swing.JTextArea txtNoiDung;
    private javax.swing.JTextField txtTepdinhkem;
    private javax.swing.JTextField txtTieuDe;
    // End of variables declaration//GEN-END:variables

}
