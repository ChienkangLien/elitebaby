package forum.dao;

import forum.pojo.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDao extends DaoId {

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

    public Category selectById(int id) {
        String sql = "select * from category where id = ?;";
        Category category = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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

    public ArrayList<Category> selectByIds(ArrayList<Integer> ids) {
        StringBuilder sqlBuilder = new StringBuilder("select * from category where id in (?");
        for (int i = 0; i < ids.size(); i++) {
            if (i > 0) {
                sqlBuilder.append(",?");
            }
        }
        sqlBuilder.append(")");
        String sql = sqlBuilder.toString();

        ArrayList<Category> categories = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int i = 0; i < ids.size(); i++) {
                ps.setInt(i + 1, ids.get(i));
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(new Category(rs.getInt("id"),
                        rs.getString("category"),
                        rs.getString("img"),
                        rs.getInt("level")));
            }
            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Category selectByName(String categoryName) {
        String sql = "select * from category where category = ?;";
        Category category = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, categoryName);
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

    public int insert(Category category) {
        String sql = "insert into category (category, img, level) values (?,?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getCategory());
            ps.setString(2, category.getImg());
            ps.setInt(3, category.getLevel());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //載入用戶已收藏category
    public ArrayList<Category> selectCollected(int userId) {
        String sql = "select * from category join collection_category cc on category.id = cc.category_id\n" +
                "where user_id = ?";
        ArrayList<Category> categories = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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