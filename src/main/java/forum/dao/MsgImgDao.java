package forum.dao;


import forum.pojo.Msg;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;


public class MsgImgDao extends DaoId {
    public ArrayList<Msg> selectAll(ArrayList<Msg> msgs) {
        String sql = "select * from msg_imgs where msg_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (Msg msg : msgs) {
                int msgId = msg.getMsgId();
                ps.setInt(1, msgId);
                ResultSet rs = ps.executeQuery();
                ArrayList<byte[]> imgs = new ArrayList<>();
                while (rs.next()) {
                    Blob imageBlob = rs.getBlob("img");
                    int length = (int) imageBlob.length();
                    byte[] imageData = imageBlob.getBytes(1, length);
                    imgs.add(imageData);
                }
                msg.setImgs(imgs);
            }
            return msgs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Msg selectById(Msg msg) {
        String sql = "select * from msg_imgs where msg_id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            int msgId = msg.getMsgId();
            ps.setInt(1, msgId);
            ResultSet rs = ps.executeQuery();
            ArrayList<byte[]> imgs = new ArrayList<>();
            while (rs.next()) {
                Blob imageBlob = rs.getBlob("img");
                int length = (int) imageBlob.length();
                byte[] imageData = imageBlob.getBytes(1, length);
                imgs.add(imageData);
            }
            msg.setImgs(imgs);
            return msg;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void insert(int msgId, ArrayList<InputStream> ins) {
        String sql = "insert into msg_imgs (msg_id, img) values(?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (InputStream in : ins) {
                ps.setInt(1, msgId);
                ps.setBlob(2, in);
                ps.addBatch();
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


