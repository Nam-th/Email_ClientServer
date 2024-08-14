package Server;

import Model.Ceasar;
import Model.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
        try (DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream()); DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream())) {

            String requestType = inputStream.readUTF();

            switch (requestType) {
                case "login":
                    handleLogin(clientSocket, inputStream, outputStream);
                    break;
                case "REGISTER":
                    handleRegister(inputStream, outputStream);
                    break;
                case "guimail":
                    handleSendMail(clientSocket, inputStream, outputStream);
                    break;
                case "hopthuden":
                    handleInbox(clientSocket, outputStream);
                    break;
                case "thudagui":
                    handleSentMails(clientSocket, outputStream);
                    break;
                case "thurac":
                    handleSpamMails(clientSocket, outputStream);
                    break;
                case "laycc":
                    handleGetAttachment(inputStream, outputStream);
                    break;
                default:
                    outputStream.writeUTF("Yêu cầu không hợp lệ");
                    break;
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

    private static void handleLogin(Socket clientSocket, DataInputStream inputStream, DataOutputStream outputStream) throws IOException, SQLException {
        System.out.println("Client đã kết nối: " + clientSocket.getInetAddress().getHostAddress());

        String email = inputStream.readUTF();
        String password = inputStream.readUTF();
        UserManager.addUser(User.getUserByUsername(email, password), clientSocket.getInetAddress().getHostAddress());

        if (UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()) != null) {
            outputStream.writeUTF("Đăng Nhập Thành Công");
        } else {
            outputStream.writeUTF("Đăng Nhập Thất Bại");
        }
    }

    private static void handleRegister(DataInputStream inputStream, DataOutputStream outputStream) throws IOException, SQLException {
        String email = inputStream.readUTF();
        String password = inputStream.readUTF();
        String fullName = inputStream.readUTF();

        boolean registrationSuccessful = DatabaseManager.registerUser(email, password, fullName);

        if (registrationSuccessful) {
            userQuotas.put(email, DEFAULT_MAILBOX_SIZE_MB);
            outputStream.writeUTF("Đăng ký thành công.");
        } else {
            outputStream.writeUTF("Đăng ký thất bại.");
        }
    }

    private static void handleSendMail(Socket clientSocket, DataInputStream inputStream, DataOutputStream outputStream) throws IOException, SQLException {
        String recipient = inputStream.readUTF();
        String subject = inputStream.readUTF();
        String role = inputStream.readUTF();
        String[] allRec = recipient.split(",");

        byte cc = "CC".equals(role) ? (byte) 1 : 0;
        byte bcc = "BCC".equals(role) ? (byte) 1 : 0;
        byte addEmail = 1;
        int is_spam = subject.equals("") ? 1 : 0;
        String content = inputStream.readUTF();

        String messageType = inputStream.readUTF();
        String savePath = handleAttachment(inputStream, messageType);

        String message = buildEncryptedMessage(clientSocket, content, allRec);

        sendEmails(allRec, subject, message, is_spam, cc, bcc, savePath, outputStream);
    }

    private static String handleAttachment(DataInputStream inputStream, String messageType) throws IOException {
        if ("attachment".equals(messageType)) {
            String fileName = inputStream.readUTF();
            long attachmentLength = inputStream.readLong();
            String savePath = "src\\FileDinhKem\\" + fileName;

            try (FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while (attachmentLength > 0 && (bytesRead = inputStream.read(buffer, 0, (int) Math.min(buffer.length, attachmentLength))) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                    attachmentLength -= bytesRead;
                }
            }
            return savePath;
        } else {
            return "";
        }
    }

    private static void sendEmails(String[] recipients, String subject, String message, int is_spam, byte cc, byte bcc, String savePath, DataOutputStream outputStream) throws SQLException, IOException {
        byte[] fileData = null;

        // Kiểm tra nếu có tệp đính kèm
        if (savePath != null && !savePath.isEmpty()) {
            fileData = Files.readAllBytes(Paths.get(savePath)); // Đọc dữ liệu từ tệp
        }
        for (int i = 0; i < recipients.length; i++) {
            byte addEmail = (byte) (i == 0 ? 1 : 0);
            DatabaseManager.sendEmail(recipients[i], subject, message, is_spam, cc, bcc, addEmail, savePath != null ? new File(savePath).getName() : null, fileData);
            outputStream.writeUTF("Gửi email thành công.");
        }
    }

    private static void handleInbox(Socket clientSocket, DataOutputStream outputStream) throws SQLException, IOException {
        String query = "SELECT e.email_id, e.timestamp, u.username, e.subject, e.body FROM Emails e "
                + "JOIN Recipients r ON e.email_id = r.email_id "
                + "JOIN Users u ON u.user_id = e.sender_id "
                + "WHERE r.user_id = ? ORDER BY e.timestamp DESC";
        fetchAndSendEmails(clientSocket, outputStream, query);
    }

    private static void handleSentMails(Socket clientSocket, DataOutputStream outputStream) throws SQLException, IOException {
        String query = "SELECT e.email_id, e.timestamp, u.username, e.subject, e.body FROM Recipients r "
                + "INNER JOIN Users u ON r.user_id = u.user_id "
                + "INNER JOIN Emails e ON e.email_id = r.email_id "
                + "WHERE r.email_id IN (SELECT email_id FROM Emails WHERE sender_id = ?) ORDER BY e.timestamp DESC";
        fetchAndSendEmails(clientSocket, outputStream, query);
    }

    private static void handleSpamMails(Socket clientSocket, DataOutputStream outputStream) throws SQLException, IOException {
        String query = "SELECT e.timestamp, u.username, e.subject, e.body FROM Emails e "
                + "JOIN Recipients r ON e.email_id = r.email_id "
                + "JOIN Users u ON u.user_id = e.sender_id "
                + "WHERE r.user_id = ? AND e.is_spam = 1 ORDER BY e.timestamp DESC";
        fetchAndSendEmails(clientSocket, outputStream, query);
    }

    private static void handleGetAttachment(DataInputStream inputStream, DataOutputStream outputStream) throws IOException, SQLException {
        int emailId = inputStream.readInt();
        String query = "SELECT DISTINCT u.username, e.file_name "
                + "FROM Recipients r "
                + "JOIN Users u ON r.user_id = u.user_id "
                + "JOIN EmailAttachments e ON e.email_id = r.email_id "
                + "WHERE r.email_id = ?";

        List<EmailData> emailDataList = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, emailId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    EmailData emailData = new EmailData();
                    emailData.setBody(resultSet.getString("username"));
                    emailData.setAttachmentFileName(resultSet.getString("file_name"));

                    emailDataList.add(emailData);
                }
            }
        }

        sendAttachmentToClient(outputStream, emailDataList);
    }

    private static void fetchAndSendEmails(Socket clientSocket, DataOutputStream outputStream, String query) throws SQLException, IOException {
        List<EmailData> emailDataList = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    EmailData emailData = new EmailData();
                    emailData.setId(resultSet.getInt("email_id"));
                    emailData.setTimestamp(resultSet.getTimestamp("timestamp"));
                    emailData.setUsername(resultSet.getString("username"));
                    emailData.setSubject(resultSet.getString("subject"));

                    String encryptedBody = resultSet.getString("body");
                    int userId = UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId();
                    emailData.setBody(decryptMessage(encryptedBody, userId));

                    emailDataList.add(emailData);
                }
            }
        }

        sendEmailsToClient(outputStream, emailDataList);
    }

    private static void sendEmailsToClient(DataOutputStream outputStream, List<EmailData> emailDataList) throws IOException {
        if (!emailDataList.isEmpty()) {
            outputStream.writeUTF("Có " + emailDataList.size() + " email mới.");
            outputStream.writeInt(emailDataList.size());

            for (EmailData emailData : emailDataList) {
                outputStream.writeInt(emailData.getId());
                outputStream.writeUTF(emailData.getTimestamp().toString());
                outputStream.writeUTF(emailData.getUsername());
                outputStream.writeUTF(emailData.getSubject());
                outputStream.writeUTF(emailData.getBody());
            }
        } else {
            outputStream.writeUTF("Không có email mới.");
        }
    }

    private static void sendAttachmentToClient(DataOutputStream outputStream, List<EmailData> emailDataList) throws IOException {
        if (!emailDataList.isEmpty()) {
            for (EmailData emailData : emailDataList) {
                outputStream.writeUTF(emailData.getBody());
                outputStream.writeUTF(emailData.getAttachmentFileName());
            }
        } else {
            outputStream.writeUTF("Không có tệp đính kèm.");
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

    private static String buildEncryptedMessage(Socket clientSocket, String content, String[] allRec) throws SQLException {
        String message = UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId() + ":" + ceasar.Encrypt(content, UserManager.getUserByIp(clientSocket.getInetAddress().getHostAddress()).getUserId()) + "@";

        try (Connection connection = DatabaseManager.getConnection()) {
            for (String recipient : allRec) {
                String query = "Select user_id from Users WHERE username = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, recipient);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        int id = resultSet.getInt("user_id");
                        message += id + ":" + ceasar.Encrypt(content, id) + "@";
                    }
                }
            }
        }
        return message;
    }

    private static String decryptMessage(String encryptedMessage, int userId) {
        StringBuilder decryptedMessage = new StringBuilder();

        // Tách các phần của thông điệp đã mã hóa, phân cách bởi dấu '@'
        String[] parts = encryptedMessage.split("@");

        for (String part : parts) {
            String[] subParts = part.split(":");

            if (subParts.length == 2) {
                int id = Integer.parseInt(subParts[0]);
                String encryptedContent = subParts[1];

                if (id == userId) {
                    // Giải mã nội dung cho userId hiện tại
                    decryptedMessage.append(ceasar.Decrypt(encryptedContent, userId));
                    decryptedMessage.append("@");
                }
            }
        }

        // Xóa dấu phân cách '@' cuối cùng nếu có
        if (decryptedMessage.length() > 0) {
            decryptedMessage.setLength(decryptedMessage.length() - 1);
        }

        return decryptedMessage.toString();
    }

}
