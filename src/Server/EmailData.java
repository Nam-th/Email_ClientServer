package Server;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//chứa dữ liệu để gửi về client từ các table khác nhau
public class EmailData implements Serializable {

    private Timestamp timestamp;
    private String username;
    private String subject;
    private String body;
    private int id;
    private String attachmentFileName;

    // Constructor với các tham số
    public EmailData(int id, String timestampStr, String username, String subject, String body) {
        this.id = id;
        // Chuyển đổi String thành Timestamp
        this.timestamp = convertStringToTimestamp(timestampStr);
        this.username = username;
        this.subject = subject;
        this.body = body;
    }

    // Default constructor
    public EmailData() {
    }

    // Phương thức chuyển đổi String thành Timestamp
    private Timestamp convertStringToTimestamp(String timestampStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date parsedDate = dateFormat.parse(timestampStr);
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;  // Hoặc có thể ném một ngoại lệ tùy thuộc vào cách bạn muốn xử lý lỗi
        }
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Thêm getter và setter cho các trường dữ liệu
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
