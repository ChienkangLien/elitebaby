package forum.dao;


import forum.pojo.Post;

import java.io.IOException;
import java.io.InputStream;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;


public class PostImgDao extends DaoId {
    public ArrayList<Post> selectAll(ArrayList<Post> posts) {
        String sql = "select * from post_imgs where post_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Post post : posts) {
                int postId = post.getPostId();
                ps.setInt(1, postId);
                ResultSet rs = ps.executeQuery();
                ArrayList<byte[]> imgs = new ArrayList<>();
                while (rs.next()) {
                    Blob imageBlob = rs.getBlob("img");
                    int length = (int) imageBlob.length();
                    byte[] imageData = imageBlob.getBytes(1, length);
                    imgs.add(imageData);
                }
                post.setImgs(imgs);
            }
            return posts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Post selectById(Post post) {
        String sql = "select * from post_imgs where post_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            int postId = post.getPostId();
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            ArrayList<byte[]> imgs = new ArrayList<>();
            while (rs.next()) {
                Blob imageBlob = rs.getBlob("img");
                int length = (int) imageBlob.length();
                byte[] imageData = imageBlob.getBytes(1, length);
                imgs.add(imageData);
            }
            post.setImgs(imgs);
            return post;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void insert(int postId, ArrayList<InputStream> ins) {
        String sql = "insert into post_imgs (post_id, img) values(?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (InputStream in : ins) {
                System.out.println("in:"+in);
                ps.setInt(1, postId);
                ps.setBlob(2, in);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


