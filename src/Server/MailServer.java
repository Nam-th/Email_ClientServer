package Server;

import Model.Ceasar;
import Model.User;
import static Server.DatabaseManager.connect;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import javax.swing.JOptionPane;

public class MailServer {

    private static final int SERVER_PORT = 1234;
    private static final Map<String, String> userCredentials = new HashMap<>();
    private static final Map<String, Integer> userQuotas = new HashMap<>();
    private static final Map<String, Boolean> userLockStatus = new HashMap<>();
    private static Ceasar ceasar = new Ceasar();
    private static final int DEFAULT_MAILBOX_SIZE_MB = 100; // Dung lượng mặc định cho hộp thư

    public static Socket clientSocket;

    public static void main(String[] args) throws SQLException {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server đã sẵn sàng với cổng " + SERVER_PORT);

            while (true) {
                clientSocket = serverSocket.accept();
//                System.out.println("Client đã kết nối: " + clientSocket.getInetAddress().getHostAddress());

                // Xử lý yêu cầu đăng nhập của client
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) throws SQLException {
        try {
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            String requestType = inputStream.readUTF();

            if ("login".equals(requestType)) {
                System.out.println("Client đã kết nối: " + clientSocket.getInetAddress().getHostAddress());

                String email = inputStream.readUTF();
                String password = inputStream.readUTF();
                UserManager.addUser(User.getUserByUsername(email, password), clientSocket.getInetAddress().getHostAddress());
                // Gọi phương thức authenticateUser để kiểm tra đăng nhập

                if (UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()) != null) {
                    outputStream.writeUTF("Đăng Nhập Thành Công");
                } else {
                    outputStream.writeUTF("Đăng Nhập Thất Bại");
                }

            } else if ("REGISTER".equals(requestType)) {
                String email = inputStream.readUTF();
                String password = inputStream.readUTF();
                String fullName = inputStream.readUTF();
                // Xử lý đăng ký
                boolean registrationSuccessful = DatabaseManager.registerUser(email, password, fullName);

                // Gửi kết quả đăng ký tới client
                if (registrationSuccessful) {
                    userQuotas.put(email, DEFAULT_MAILBOX_SIZE_MB);
                    outputStream.writeUTF("Đăng ký thành công.");
                } else {
                    outputStream.writeUTF("Đăng ký thất bại.");
                }
            } else if ("guimail".equals(requestType)) {
                String recipient = inputStream.readUTF();
                String subject = inputStream.readUTF();
                String role = inputStream.readUTF();
                String[] allRec = recipient.split(",");

                byte cc = 0;
                byte bcc = 0;
                byte addEmail = 1;
                int is_spam = 0;
                if (subject.equals("")) {
                    is_spam = 1;
                } else {
                    is_spam = 0;
                }
                String content = inputStream.readUTF();

                //mahoa
                String messageType = inputStream.readUTF();
                String savePath = "";
                if ("attachment".equals(messageType)) {
                    // The client is sending an attachment
                    String fileName = inputStream.readUTF();
                    long attachmentLength = inputStream.readLong();

                    // Define a directory where you want to save the attachment
                    savePath = "src\\FileDinhKem\\" + fileName;

                    byte[] buffer = new byte[4096];
                    int bytesRead;

                    try (FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
                        while (attachmentLength > 0 && (bytesRead = inputStream.read(buffer, 0, (int) Math.min(buffer.length, attachmentLength))) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                            attachmentLength -= bytesRead;
                        }
                    }
                } else if ("no_attachment".equals(messageType)) {
                    savePath = "";
                }
                String message = UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId() + ":" + ceasar.Encrypt(content, UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId()) + "@";
                if (allRec.length >= 1) {
                    if ("CC".equals(role)) {
                        cc = 1;
                    } else if ("BCC".equals(role)) {
                        bcc = 1;
                    }

                    for (int i = 0; i < allRec.length; i++) {

                        //ma hoa
                        Connection connection = DatabaseManager.getConnection();
                        String query = "Select user_id from Users WHERE username = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, allRec[i]);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        // Thực hiện câu SQL INSERT                      
                        while (resultSet.next()) {
                            // Lấy dữ liệu từ các cột của bản ghi
                            int id = resultSet.getInt("user_id");
                            message = message + id + ":" + ceasar.Encrypt(content, id) + "@";
                        }
                        connection.close();
                    }
                    for (int i = 0; i < allRec.length; i++) {
                        if (i == 0) {
                            addEmail = 1;
                        } else {
                            addEmail = 0;
                        }
                        // Gửi email cho người nhận
                        System.out.println(allRec[i]);
                        DatabaseManager.sendEmail(allRec[i], subject, message, is_spam, cc, bcc, addEmail, savePath);

                        // Gửi kết quả thành công cho client
                        outputStream.writeUTF("Gửi email thành công.");
                    }
                }

            } else if ("hopthuden".equals(requestType)) {
                try {
                    // Kết nối đến cơ sở dữ liệu
                    Connection connection = DatabaseManager.getConnection();

                    // Tạo câu lệnh truy vấn
                    String query = "select e.email_id, e.timestamp, u.username, e.subject, e.body from Emails e join Recipients r on e.email_id = r.email_id join Users u on u.user_id = e.sender_id where r.user_id = ? order by e.timestamp desc";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    // Đặt giá trị cho tham số
                    preparedStatement.setInt(1, UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId());

                    // Thực hiện truy vấn
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Tạo danh sách để lưu trữ dữ liệu
                    List<EmailData> emailDataList = new ArrayList<>();

                    while (resultSet.next()) {

                        EmailData emailData = new EmailData();
                        emailData.setId(resultSet.getInt("email_id"));
                        emailData.setTimestamp(resultSet.getTimestamp("timestamp"));
                        emailData.setUsername(resultSet.getString("username"));
                        emailData.setSubject(resultSet.getString("subject"));
                        String ciphertextString = resultSet.getString("body");
//                        String[] allRec = ciphertextString.split("@");
//                        for (int i = 0; i < allRec.length; i++) {
//                            System.out.println(allRec[i]);
//                            if (i == 1) {
//                                String decryptedMessage = ceasar.Encrypt(allRec[i], -(UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId()));
//                                emailData.setBody(decryptedMessage);
//                            }
//                        }
                        String[] messageParts = ciphertextString.split("@");
                        for (String part : messageParts) {
                            if (part.contains(UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId() + ":")) {
                                String[] messageValue = part.split(":");
                                if (messageValue.length == 2) {
                                    String decryptedMessage = ceasar.Encrypt(messageValue[1], -(UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId()));
                                    emailData.setBody(decryptedMessage);
                                }
                            }
                        }

                        //emailData.setBody(resultSet.getString("body"));
                        emailDataList.add(emailData);
                    }

                    // Gửi danh sách dữ liệu cho client
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    objectOutputStream.writeObject(emailDataList);

                    // Đóng kết nối và tài nguyên
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            } else if ("thudagui".equals(requestType)) {
                try {
                    // Kết nối đến cơ sở dữ liệu
                    Connection connection = DatabaseManager.getConnection();

                    // Tạo câu lệnh truy vấn
                    String query = "SELECT e.timestamp,u.username,  e.subject, e.body\n"
                            + "FROM Recipients r\n"
                            + "INNER JOIN Users u ON r.user_id = u.user_id\n"
                            + "inner join Emails e ON e.email_id = r.email_id\n"
                            + "WHERE r.email_id IN (SELECT email_id FROM Emails WHERE sender_id = ?)order by e.timestamp desc;";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    // Đặt giá trị cho tham số
                    preparedStatement.setInt(1, UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId());

                    // Thực hiện truy vấn
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Tạo danh sách để lưu trữ dữ liệu
                    List<EmailData> emailDataList = new ArrayList<>();

                    while (resultSet.next()) {
                        EmailData emailData = new EmailData();
                        emailData.setTimestamp(resultSet.getTimestamp("timestamp"));
                        emailData.setUsername(resultSet.getString("username"));
                        emailData.setSubject(resultSet.getString("subject"));
                        String ciphertextString = resultSet.getString("body");

                        String[] messageParts = ciphertextString.split("@");
                        for (String part : messageParts) {
                            if (part.contains(UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId() + ":")) {
                                String[] messageValue = part.split(":");
                                if (messageValue.length == 2) {
                                    String decryptedMessage = ceasar.Encrypt(messageValue[1], -(UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId()));
                                    emailData.setBody(decryptedMessage);
                                }
                            }
                        }
                        emailDataList.add(emailData);
                    }

                    // Gửi danh sách dữ liệu cho client
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    objectOutputStream.writeObject(emailDataList);

                    // Đóng kết nối và tài nguyên
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            } else if ("thurac".equals(requestType)) {
                try {
                    // Kết nối đến cơ sở dữ liệu
                    Connection connection = DatabaseManager.getConnection();

                    // Tạo câu lệnh truy vấn
                    String query = "select e.timestamp, u.username, e.subject, e.body from Emails e join Recipients r on e.email_id = r.email_id join Users u on u.user_id = e.sender_id where r.user_id = ? and e.is_spam = 1 order by e.timestamp desc";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    // Đặt giá trị cho tham số
                    preparedStatement.setInt(1, UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId());

                    // Thực hiện truy vấn
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Tạo danh sách để lưu trữ dữ liệu
                    List<EmailData> emailDataList = new ArrayList<>();

                    while (resultSet.next()) {
                        EmailData emailData = new EmailData();
                        emailData.setTimestamp(resultSet.getTimestamp("timestamp"));
                        emailData.setUsername(resultSet.getString("username"));
                        emailData.setSubject(resultSet.getString("subject"));
                        emailData.setBody(resultSet.getString("body"));
                        emailDataList.add(emailData);
                    }

                    // Gửi danh sách dữ liệu cho client
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    objectOutputStream.writeObject(emailDataList);

                    // Đóng kết nối và tài nguyên
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }
            } else if ("laycc".equals(requestType)) {
                try {
                    int IP = inputStream.readInt();
                    // Kết nối đến cơ sở dữ liệu
                    Connection connection = DatabaseManager.getConnection();

                    // Tạo câu lệnh truy vấn
                    String query = "SELECT DISTINCT u.username, e.file_name "
                            + "FROM Recipients r "
                            + "JOIN Users u ON r.user_id = u.user_id "
                            + "JOIN EmailAttachments e ON e.email_id = r.email_id "
                            + "WHERE r.email_id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);

                    // Đặt giá trị cho tham số
                    preparedStatement.setInt(1, IP);

                    // Thực hiện truy vấn
                    ResultSet resultSet = preparedStatement.executeQuery();

                    // Tạo danh sách để lưu trữ dữ liệu
                    List<EmailData> emailDataList = new ArrayList<>();

                    while (resultSet.next()) {

                        EmailData emailData = new EmailData();
                        emailData.setBody(resultSet.getString("username"));
                        emailData.setAttachmentFileName(resultSet.getString("file_name"));
                        emailDataList.add(emailData);
                    }

                    // Gửi danh sách dữ liệu cho client
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    objectOutputStream.writeObject(emailDataList);

                    // Đóng kết nối và tài nguyên
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();

                    if (!emailDataList.isEmpty() && emailDataList.get(0).getAttachmentFileName() != null) {
                        String attachmentFile = emailDataList.get(0).getAttachmentFileName();
                        File file = new File(attachmentFile);
                        if (file.exists()) {
                            outputStream.writeUTF("attachment"); // Indicate that an attachment is being sent
                            outputStream.writeUTF(file.getName()); // Send the file name to the client
                            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                                byte[] buffer = new byte[4096];
                                int bytesRead;
                                // Read the file and write it to the output stream
                                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                                    outputStream.write(buffer, 0, bytesRead);
                                }
                            }
                        } else {
                            outputStream.writeUTF("no_attachment");
                        }
                    } else {
                        outputStream.writeUTF("no_attachment");
                    }
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                }

            }

            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //để nâng cấp dung lượng
    public static void setMailboxSize(String username, int newSizeInMB) {
        userQuotas.put(username, newSizeInMB);
    }

    // Hàm giả định cho việc nâng cấp hộp thư của người dùng
    public void upgradeMailbox(String username) {
        int newMailboxSize = 200; // Dung lượng mới sau khi nâng cấp
        setMailboxSize(username, newMailboxSize);
    }
}
