
package Model;

import Server.DatabaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    
    private int userId;
    private String username;
    private String fullName;
    private String password;
    private int emailQuota;

    // Constructors
    public User() {
    }

    public User(String username, String fullName, String password, int emailQuota) {
        this.username = username;
        this.fullName = fullName;
        this.password = password;
        this.emailQuota = emailQuota;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmailQuota() {
        return emailQuota;
    }

    public void setEmailQuota(int emailQuota) {
        this.emailQuota = emailQuota;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", username=" + username + ", fullName=" + fullName + ", password=" + password
                + ", emailQuota=" + emailQuota + "]";
    }
    
    public static User getUserByUsername(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? and password = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            
            connection = DatabaseManager.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setFullName(resultSet.getString("full_name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmailQuota(resultSet.getInt("email_quota"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return user;
    }
}
