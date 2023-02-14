package forum.dao;

import forum.pojo.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDao {

    private String URL = DaoId.URL;
    private String USER = DaoId.USER;
    private String PASSWORD = DaoId.PASSWORD;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Category> selectAll() {
        String sql = "select * from category;";
        ArrayList<Category> categories = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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

    public ArrayList<Category> selectCollected(int userId) {
        String sql = "select * from category join collection_category cc on category.id = cc.category_id\n" +
                "where user_id = ?";
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,userId);
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