package forum.dao;

import forum.pojo.Post;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PostDao {
    private String URL = DaoId.URL;
    private String USER = DaoId.USER;
    private String PASSWORD = DaoId.PASSWORD;
    private SimpleDateFormat format = new SimpleDateFormat("M月d日 HH:mm");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Post> selectAll() {
        String sql = "select p.*, m.user_name, count(l.like_id) as plike\n" +
                "from post p\n" +
                "         left join post_like l on p.post_id = l.post_id\n" +
                "         join member m on m.USER_ID = p.user_id\n" +
                "group by p.post_id\n" +
                "order by p.post_id desc";
        ArrayList<Post> posts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(
                        new Post(
                                rs.getInt("post_id"),
                                rs.getInt("user_id"),
                                rs.getString("user_name"),
                                rs.getString("category"),
                                rs.getString("topic"),
                                rs.getString("content"),
                                rs.getInt("plike"),
                                format.format(rs.getObject("timing", Timestamp.class))
                        ));
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Post> selectPopular() {
        String sql = "select p.*, m.user_name, count(l.like_id) as plike\n" +
                "from post p\n" +
                "         left join post_like l on p.post_id = l.post_id\n" +
                "         join member m on m.USER_ID = p.user_id\n" +
                "group by p.post_id\n" +
                "order by plike desc;";
        ArrayList<Post> posts = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                posts.add(
                        new Post(
                                rs.getInt("post_id"),
                                rs.getInt("user_id"),
                                rs.getString("user_name"),
                                rs.getString("category"),
                                rs.getString("topic"),
                                rs.getString("content"),
                                rs.getInt("plike"),
                                format.format(rs.getObject("timing", Timestamp.class))
                        ));
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Post getPostById(int postId) {
        String sql = "select p.*, m.user_name, count(l.like_id) as plike\n" +
                "from post p\n" +
                "         left join post_like l on p.post_id = l.post_id\n" +
                "         join member m on m.USER_ID = p.user_id\n" +
                "where p.post_id = ?\n" +
                "group by l.post_id;";
        Post post = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                post = new Post(
                        rs.getInt("post_id"),
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("category"),
                        rs.getString("topic"),
                        rs.getString("content"),
                        rs.getInt("plike"),
                        format.format(rs.getObject("timing", Timestamp.class))
                );
            }
            return post;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCategoryById(int id) {
        String category = null;
        String sql = "select category from category where id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = rs.getString("category");
            }
            return category;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //posts生成器
    public void postGenerator(ArrayList<String> categoryName) {
        String sql = "insert into post (user_id, category, topic, content) VALUES (?,?,?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int c = 0; c < categoryName.size(); c++) {
                for (int i = 1; i <= 4; i++) {
                    ps.setInt(1, (int) (Math.random() * 5) + 1);
                    ps.setString(2, categoryName.get(c));
                    ps.setString(3, "第" + i + "篇" + categoryName.get(c) + "文章");
                    ps.setString(4, DaoId.content);
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Integer> getPostIds() {
        ArrayList<Integer> ids = new ArrayList<>();
        String sql = "select post_id from post;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt("post_id"));
            }
            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

