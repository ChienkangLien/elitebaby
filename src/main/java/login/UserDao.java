package login;

import forum.dao.DaoId;

import java.sql.*;

public class UserDao {
    private String URL = "jdbc:mysql://localhost:3306/elitebaby";
    private String USER = DaoId.USER;
    private String PASSWORD = DaoId.PASSWORD;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public User login(String userName, String password) {
        String sql = "select * from member where user_name = ? and user_password = ?;";
        User user = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String userNameById(int userId) {
        String userName = null;
        String sql = "select user_name from member where USER_ID = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                userName = rs.getString("user_name");
            }
            return userName;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
