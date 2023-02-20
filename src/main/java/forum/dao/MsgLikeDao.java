package forum.dao;

import java.sql.*;

public class MsgLikeDao extends DaoId{


    public void generator() {
        String sql = "insert into msg_like(msg_id, user_id) values (?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < 50; i++) {
                ps.setInt(1, (int) (Math.random() * 19) + 1);
                ps.setInt(2, (int) (Math.random() * 5) + 1);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {

        }
    }

    public void clean() {
        String sql = "delete from msg_like where 1=1;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(int msgId, int userId) {
        String sql = "insert into msg_like(msg_id, user_id) values (?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, msgId);
            ps.setInt(2, userId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int msgId, int userId) {
        String sql = "delete from msg_like where msg_id = ? and user_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, msgId);
            ps.setInt(2, userId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean check(int msgId, int userId) {
        String sql = "select * from msg_like where msg_id = ? and user_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, msgId);
            ps.setInt(2, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int count(int msgId) {
        int like = 0;
        String sql = "select msg_id, count(*) as mlike from msg_like\n" +
                "where msg_id = ?\n" +
                "group by msg_id;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, msgId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                like = rs.getInt("mlike");
            }
            return like;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


