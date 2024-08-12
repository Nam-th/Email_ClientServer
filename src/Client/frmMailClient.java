
package Client;

import Server.EmailData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JOptionPane;


public class frmMailClient extends javax.swing.JFrame {

    /**
     * Creates new form frmMailClient
     */
    public frmMailClient() {
        initComponents();
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

        btnGui = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNoidung = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnPhanhoi = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtnguoidanhan = new javax.swing.JTextField();
        txtNguoigui = new javax.swing.JTextField();
        txtTieude = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnDangxuat = new javax.swing.JButton();
        btnThugui = new javax.swing.JButton();
        btnSoanthu = new javax.swing.JButton();
        btnHopthuden = new javax.swing.JButton();
        btnThurac = new javax.swing.JButton();
        btnThungrac = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HỘP THƯ ĐẾN");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGui.setBackground(new java.awt.Color(255, 0, 0));
        btnGui.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnGui.setForeground(new java.awt.Color(255, 255, 255));
        btnGui.setText("Send");
        btnGui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiActionPerformed(evt);
            }
        });
        getContentPane().add(btnGui, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 390, 90, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/HTD.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 308, 65));

        jTable1.setFont(new java.awt.Font("Roboto", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Thời gian", "Người Gửi", "Tiêu Đề"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 545, 340));

        txtNoidung.setColumns(20);
        txtNoidung.setRows(5);
        jScrollPane2.setViewportView(txtNoidung);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 240, 291, 180));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 102, 102));
        jLabel3.setText("  Tiêu Đề");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 80, -1, 30));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 102, 102));
        jLabel4.setText(" Nội Dung");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 217, -1, 20));

        btnPhanhoi.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        btnPhanhoi.setForeground(new java.awt.Color(255, 0, 0));
        btnPhanhoi.setText("Phản Hồi");
        btnPhanhoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPhanhoiActionPerformed(evt);
            }
        });
        getContentPane().add(btnPhanhoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 350, -1, 30));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 102, 102));
        jLabel5.setText(" Những người đã nhận email");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 150, -1, 30));
        getContentPane().add(txtnguoidanhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 180, 390, 30));
        getContentPane().add(txtNguoigui, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 170, 30));
        getContentPane().add(txtTieude, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 110, 180, 30));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 102, 102));
        jLabel2.setText(" Người Gửi");
        jLabel2.setName("jLabel2"); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, -1, 30));

        btnDangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/8.png"))); // NOI18N
        btnDangxuat.setBorder(null);
        btnDangxuat.setContentAreaFilled(false);
        btnDangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangxuatActionPerformed(evt);
            }
        });
        getContentPane().add(btnDangxuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 138, 40));

        btnThugui.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/6.png"))); // NOI18N
        btnThugui.setBorder(null);
        btnThugui.setContentAreaFilled(false);
        btnThugui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuguiActionPerformed(evt);
            }
        });
        getContentPane().add(btnThugui, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 138, 40));

        btnSoanthu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/4.png"))); // NOI18N
        btnSoanthu.setBorder(null);
        btnSoanthu.setContentAreaFilled(false);
        btnSoanthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSoanthuActionPerformed(evt);
            }
        });
        getContentPane().add(btnSoanthu, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 138, 40));

        btnHopthuden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/5.png"))); // NOI18N
        btnHopthuden.setBorder(null);
        btnHopthuden.setContentAreaFilled(false);
        btnHopthuden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHopthudenActionPerformed(evt);
            }
        });
        getContentPane().add(btnHopthuden, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 138, 40));

        btnThurac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/thurac.png"))); // NOI18N
        btnThurac.setBorder(null);
        btnThurac.setContentAreaFilled(false);
        btnThurac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuracActionPerformed(evt);
            }
        });
        getContentPane().add(btnThurac, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 138, 40));

        btnThungrac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/7.png"))); // NOI18N
        btnThungrac.setBorder(null);
        btnThungrac.setContentAreaFilled(false);
        getContentPane().add(btnThungrac, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 138, 40));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/nenHopthuden1.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loaddata(List<EmailData> emailDataList) {
        try {
            DefaultTableModel tableModel = new DefaultTableModel();
            String[] columnNames = {"Thời gian", "Người gửi", "Tiêu đề", "Nội dung"};
            for (String columnName : columnNames) {
                tableModel.addColumn(columnName);
            }
            jTable1.setModel(tableModel);

            // Xóa dữ liệu cũ trong JTable (nếu có)
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }

            // Thêm dữ liệu mới từ danh sách emailDataList vào JTable
            for (EmailData emailData : emailDataList) {
                Object[] rowData = {
                    emailData.getTimestamp(),
                    emailData.getUsername(),
                    emailData.getSubject(),
                    emailData.getBody()
                };
                tableModel.addRow(rowData);
            }

            // Làm gì đó với txtNguoigui, txtTieude và txtNoidung (nếu cần)
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    List<EmailData> emailDataList;
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setGD(false);
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Timestamp");
        tableModel.addColumn("Username");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Body");
        try {
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            outputStream.writeUTF("hopthuden");
            outputStream.flush();

            // Tạo đối tượng ObjectInputStream để đọc dữ liệu từ máy chủ
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            // Nhận danh sách dữ liệu từ máy chủ
            emailDataList = (List<EmailData>) objectInputStream.readObject();

            // Xử lý danh sách dữ liệu
//            for (EmailData data : emailDataList) {
//                // Thực hiện xử lý dữ liệu ở đây
//                System.out.println("Timestamp: " + data.getTimestamp());
//                System.out.println("Username: " + data.getUsername());
//                System.out.println("Subject: " + data.getSubject());
//                System.out.println("Body: " + data.getBody());
//            }
            loaddata(emailDataList);
            // Đóng kết nối và luồng đầu vào
            objectInputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_formWindowOpened

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        try {
            setGD(false);

            System.out.println(emailDataList.get(jTable1.getSelectedRow()).getId());
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());

            int ID = emailDataList.get(jTable1.getSelectedRow()).getId();
            outputStream.writeUTF("laycc");
            outputStream.writeInt(ID);

            outputStream.flush();

            String listNguoiNhan = "";
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            List<EmailData> emailDataList2 = (List<EmailData>) objectInputStream.readObject();

            for (EmailData emailData : emailDataList2) {

                listNguoiNhan += ", " + emailData.getBody();
            }
            txtnguoidanhan.setText(listNguoiNhan.replaceFirst(", ", ""));

            String messageType = inputStream.readUTF();
            if ("attachment".equals(messageType)) {
                // Nếu là tin nhắn có tệp đính kèm, nhận và lưu tệp
                String fileName = inputStream.readUTF();

                // Lưu tệp đính kèm
                String savePath = "src\\FileDinhKemClient\\" + fileName;

                try (FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Attachment saved at: " + savePath);
                }
            }else if("no_attachment".equals(messageType)){
                System.out.println("Mail này k có file");
            }

            

            DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
            int row = jTable1.rowAtPoint(evt.getPoint());

            Object[] data = new Object[4];

            for (int i = 0; i < 4; i++) {
                data[i] = tableModel.getValueAt(row, i);
            }
            txtNguoigui.setText(data[1].toString());
            txtTieude.setText(data[2].toString());
            txtNoidung.setText(data[3].toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    public void setGD(boolean val) {
        if (val == true) {
            jLabel2.setText("Người Nhận");
            // btnPhanhoi.addFocusListener(fl);
            txtNoidung.setText("");
            txtTieude.setText("re: " + txtTieude.getText());

        } else {
            jLabel2.setText("Người Gửi");
        }

 
        btnGui.setVisible(!val);
        btnPhanhoi.setVisible(!val);
    }
    private void btnPhanhoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPhanhoiActionPerformed
        setGD(true);
    }//GEN-LAST:event_btnPhanhoiActionPerformed

    private void btnThuracActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuracActionPerformed
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Timestamp");
        tableModel.addColumn("Username");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Body");
        try {
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            outputStream.writeUTF("thurac");
            outputStream.flush();

            // Tạo đối tượng ObjectInputStream để đọc dữ liệu từ máy chủ
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            // Nhận danh sách dữ liệu từ máy chủ
            emailDataList = (List<EmailData>) objectInputStream.readObject();

            // Xử lý danh sách dữ liệu
            //            for (EmailData data : emailDataList) {
                //                // Thực hiện xử lý dữ liệu ở đây
                //                System.out.println("Timestamp: " + data.getTimestamp());
                //                System.out.println("Username: " + data.getUsername());
                //                System.out.println("Subject: " + data.getSubject());
                //                System.out.println("Body: " + data.getBody());
                //            }
            loaddata(emailDataList);
            // Đóng kết nối và luồng đầu vào
            objectInputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThuracActionPerformed

    private void btnHopthudenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHopthudenActionPerformed
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Timestamp");
        tableModel.addColumn("Username");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Body");
        try {
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            outputStream.writeUTF("hopthuden");
            outputStream.flush();

            // Tạo đối tượng ObjectInputStream để đọc dữ liệu từ máy chủ
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            // Nhận danh sách dữ liệu từ máy chủ
            emailDataList = (List<EmailData>) objectInputStream.readObject();

            loaddata(emailDataList);
            // Đóng kết nối và luồng đầu vào
            objectInputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnHopthudenActionPerformed

    private void btnSoanthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSoanthuActionPerformed
        frmSend f = new frmSend();
        f.setVisible(true);
    }//GEN-LAST:event_btnSoanthuActionPerformed

    private void btnThuguiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuguiActionPerformed
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Timestamp");
        tableModel.addColumn("Username");
        tableModel.addColumn("Subject");
        tableModel.addColumn("Body");
        try {
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            outputStream.writeUTF("thudagui");
            outputStream.flush();

            // Tạo đối tượng ObjectInputStream để đọc dữ liệu từ máy chủ
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            // Nhận danh sách dữ liệu từ máy chủ
            emailDataList = (List<EmailData>) objectInputStream.readObject();

            // Xử lý danh sách dữ liệu
            //            for (EmailData data : emailDataList) {
                //                // Thực hiện xử lý dữ liệu ở đây
                //                System.out.println("Timestamp: " + data.getTimestamp());
                //                System.out.println("Username: " + data.getUsername());
                //                System.out.println("Subject: " + data.getSubject());
                //                System.out.println("Body: " + data.getBody());
                //            }
            loaddata(emailDataList);
            // Đóng kết nối và luồng đầu vào
            objectInputStream.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThuguiActionPerformed

    private void btnDangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangxuatActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnDangxuatActionPerformed

    private void btnGuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiActionPerformed
        // TODO add your handling code here:
        try {
            Socket clientSocket = new Socket(SERVER_HOST, SERVER_PORT);

            // Mở luồng gửi dữ liệu lên server
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // Gọi phương thức để gửi email
            String recipient = (txtNguoigui.getText()); // Lấy địa chỉ người nhận từ giao diện
            String subject = txtTieude.getText(); // Lấy tiêu đề từ giao diện
            String content = txtNoidung.getText(); // Lấy nội dung từ giao diện

            // Ghi các thông tin cần gửi lên server
            outputStream.writeUTF("guimail");
            outputStream.writeUTF(recipient);
            outputStream.writeUTF(subject);
            outputStream.writeUTF("NO");
            outputStream.writeUTF(content);

            outputStream.flush();

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
    }//GEN-LAST:event_btnGuiActionPerformed

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
            java.util.logging.Logger.getLogger(frmMailClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmMailClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmMailClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmMailClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmMailClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDangxuat;
    private javax.swing.JButton btnGui;
    private javax.swing.JButton btnHopthuden;
    private javax.swing.JButton btnPhanhoi;
    private javax.swing.JButton btnSoanthu;
    private javax.swing.JButton btnThugui;
    private javax.swing.JButton btnThungrac;
    private javax.swing.JButton btnThurac;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtNguoigui;
    private javax.swing.JTextArea txtNoidung;
    private javax.swing.JTextField txtTieude;
    private javax.swing.JTextField txtnguoidanhan;
    // End of variables declaration//GEN-END:variables
}
