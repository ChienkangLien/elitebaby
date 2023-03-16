package forum.dao;

import forum.pojo.Msg;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MsgDao extends DaoId {

    private SimpleDateFormat format = new SimpleDateFormat("M月d日 HH:mm");

    public ArrayList<Msg> selectAll(int postId) {
        String sql = "select msg.*, ac.user_name, count(ml.like_id) as mLike\n" +
                "from msg join access ac on ac.user_id = msg.user_id\n" +
                "     left join msg_like ml on msg.msg_id = ml.msg_id\n" +
                "where post_id = ?\n" +
                "group by msg.msg_id\n" +
                "order by msg_id asc;";
        ArrayList<Msg> msgs = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                msgs.add(
                        new Msg(
                                rs.getInt("msg_id"),
                                rs.getInt("post_id"),
                                rs.getInt("user_id"),
                                rs.getString("user_name"),
                                rs.getString("content"),
                                rs.getInt("mlike"),
                                format.format(rs.getObject("timing", Timestamp.class))
                        ));
            }
            return msgs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Msg selectById(int msgId) {
        String sql = "select msg.*, ac.user_name, count(ml.like_id) as mlike\n" +
                "from msg\n" +
                "join access ac on ac.user_id = msg.user_id\n" +
                "left join msg_like ml on msg.msg_id = ml.msg_id\n" +
                "where msg.msg_id =? \n" +
                "group by msg.msg_id;";

        Msg msg = null;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, msgId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                msg = new Msg(
                        rs.getInt("msg_id"),
                        rs.getInt("post_id"),
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getString("content"),
                        rs.getInt("mlike"),
                        format.format(rs.getObject("timing", Timestamp.class))
                );
            }
            return msg;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert(Msg msg) {
        int key = -1;
        String sql = "insert into msg(user_id, post_id, content) values (?,?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, msg.getUserId());
            ps.setInt(2, msg.getPostId());
            ps.setString(3, msg.getContent());
            ps.execute();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    key = rs.getInt(1);
                }
            }
            return key;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Msg生產器
    public void generator(ArrayList<Integer> ids) {
        int length = ids.size();
        int begin = ids.get(0);
        String sql = "insert into msg (user_id, post_id, content) values (?,?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            for (int j = 0; j < length; j++) {
                for (int i = 1; i <= 3; i++) {
                    ps.setInt(1, (int) (Math.random() * 9) + 1);
                    ps.setInt(2, begin + j);
                    ps.setString(3, "第" + i + "筆留言內容");
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        } catch (SQLException e) {

        }
    }

    public int deleteById(int msgId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("delete from msg_like where msg_id = ?;");
            preparedStatement.setInt(1, msgId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from msg_imgs where msg_id = ?;");
            preparedStatement.setInt(1, msgId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from msg where msg_id = ?;");
            preparedStatement.setInt(1, msgId);
            preparedStatement.executeUpdate();

            connection.commit();
            return 1;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}


