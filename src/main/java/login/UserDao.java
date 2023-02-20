package login;

import forum.dao.DaoId;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class UserDao {

    private static DataSource ds = null;
    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public User login(String userName, String password) {
        String sql = "select * from member where user_name = ? and user_password = ?;";
        User user = null;
        System.out.println("UserDao" + userName + password);
        try (Connection connection = ds.getConnection();
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
            System.out.println("UserDao-user: " + user);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String userNameById(int userId) {
        String userName = null;
        String sql = "select user_name from member where USER_ID = ?;";
        try (Connection connection =ds.getConnection();
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
