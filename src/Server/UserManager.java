package Server;

import Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserManager {

    private static Map<String, User> userByIp = new HashMap<>();

    private static User user = new User();

    public static void addUser(User user, String ip) {
        userByIp.put(ip, user);
    }

    public static User getUserByIp(String ip) {
        return userByIp.get(ip);
    }

    public static boolean registerUser(String username, String password) {
        try (Connection connection = DatabaseManager.connect()) {
            String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User u1) {
        user = u1;
    }

}
