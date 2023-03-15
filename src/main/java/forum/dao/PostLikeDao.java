package forum.dao;

import java.sql.*;
import java.util.ArrayList;


public class PostLikeDao extends DaoId{

    public void generator(ArrayList<Integer> ids) {
        int length = ids.size();
        int begin = ids.get(0);
        System.out.println("length:"+length+ "begin:"+begin);
        String sql = "insert into post_like(post_id, user_id) values (?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < 200; i++) {
                ps.setInt(1, (int) (Math.random() * length) + begin);
                ps.setInt(2, (int) (Math.random() * 9) + 1);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {

        }
    }

    public void clean() {
        String sql = "delete from post_like where 1=1;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(int postId, int userId) {
        String sql = "insert into post_like(post_id, user_id) values (?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int postId, int userId) {
        String sql = "delete from post_like where post_id = ? and user_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ps.setInt(2, userId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean check(int postId, int userId) {
        String sql = "select * from post_like where post_id = ? and user_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
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

    public int count(int postId) {
        int like = 0;
        String sql = "select post_id, count(*) as plike from post_like\n" +
                "where post_id = ?\n" +
                "group by post_id;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                like = rs.getInt("plike");
            }
            return like;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


