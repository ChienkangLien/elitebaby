package forum.dao;

import forum.pojo.Access;

import java.sql.*;

public class AccessDao extends DaoId {
    
    public Access login(String userName, String password) {
        String sql = "select * from access where user_name = ? and password = ?;";
        Access access = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, userName);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                access = new Access(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("password"));
            }
            return access;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String userNameById(int userId) {
        String userName = null;
        String sql = "select user_name from access where user_id = ?;";
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
