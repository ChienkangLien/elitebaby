package forum.dao;

import java.sql.*;

public class CollectionDao {

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

    public void add(int userId, int categoryId) {
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

    public void remove(int userId, int categoryId) {
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

//    public ArrayList<Integer> getCategoryIds(int userId) {
//        ArrayList<Integer> categoryIds = new ArrayList<>();
//        String sql = "select * from collection_category where user_id = ?;";
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, userId);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                categoryIds.add(rs.getInt("id"));
//            }
//        return categoryIds;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


}