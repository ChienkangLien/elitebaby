package forum.dao;

import java.sql.*;


public class CollectionDao extends DaoId{

    public boolean check(int userId, int categoryId) {
        String sql = "select * from collection_category where user_id = ? and category_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, categoryId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void insert(int userId, int categoryId) {
        String sql = "insert into collection_category(user_id, category_id) VALUES (?, ?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, categoryId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(int userId, int categoryId) {
        String sql = "delete from collection_category where user_id = ? and category_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, categoryId);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}