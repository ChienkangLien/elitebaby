package forum.dao;

import forum.pojo.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDao extends DaoId {

    public ArrayList<Category> selectAll() {
        String sql = "select * from category;";
        ArrayList<Category> categories = new ArrayList<>();

        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(
                        new Category(
                                rs.getInt("id"),
                                rs.getString("category"),
                                rs.getString("img"),
                                rs.getInt("level")
                        ));
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Category selectById(int id) {
        String sql = "select * from category where id = ?;";
        Category category = null;
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getInt("id"),
                        rs.getString("category"),
                        rs.getString("img"),
                        rs.getInt("level"));
            }
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    //載入用戶已收藏category
    public ArrayList<Category> selectCollected(int userId) {
        String sql = "select * from category join collection_category cc on category.id = cc.category_id\n" +
                "where user_id = ?";
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection connection = ds.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(
                        new Category(
                                rs.getInt("id"),
                                rs.getString("category"),
                                rs.getString("img"),
                                rs.getInt("level")
                        ));
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}