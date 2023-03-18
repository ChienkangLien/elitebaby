package forum.dao;

import forum.pojo.Post;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class PostDao extends DaoId {
    private SimpleDateFormat format = new SimpleDateFormat("M月d日 HH:mm");
    private String content = "當孩子年齡越來越大，勢必會越來越有自己的想法，如果仍慣性地使用過去對待「小小孩」的方式跟他們互動，他們只會從父母那兒感受到被監控、被批判的束縛與壓力，進而想要逃離，去呼吸自由的空氣。\n" +
            "即便這些監控、批判的出發點是基於關心與善意，但在得不到尊重與認可的情況下，孩子選擇轉身離去，投入同溫層的懷抱，就成了必然的結果。\n" +
            "面對越來越有個性和想法的「酷」小孩，為人父母該如何是好呢？\n" +
            "我認為，從「感興趣」的角度、用「感興趣」的態度去與他們互動、相處，會是一條「雙贏」的活路。\n" +
            "做孩子的「參謀」，是需要學習的\n" +
            "聖誕假期，小學六年級的女兒和班上同學私下相約，要看電影，順便逛街。\n" +
            "由於她是「先斬後奏」，事先並未與我或太太討論，因此當我們得知消息時，第一時間的反應都是不置可否，對幾個小六生要出門一整天，覺得有些擔心，不太同意這件事。\n" +
            "但回想起自己從前跟同學一塊出門晃遊的經歷，確實充滿許多愉快、美好的回憶，也就不忍直接否定她的計畫。\n" +
            "直到她要出門的前一天，我們才針對這件事情「直球對決」。\n" +
            "女兒興高采烈地說著，她多麼期待有生以來第一次「沒有大人」隨行的逛街行程。\n" +
            "可她說得越興奮，就越增添我的擔心，便打趣地對她說：「聽起來很好玩的樣子。乾脆我載你們去百貨公司，順便去那裡的書店逛逛，喝杯咖啡，在那邊等你們一起回家。你覺得如何？」\n" +
            "女兒皺著眉頭，十分抗拒地說：「不要！不要！就跟你說不要有大人跟了嘛！你這樣，我們會很不自由。」\n" +
            "看來她只想要我擔任司機就好，「不給跟」的意圖相當堅定。";

    public ArrayList<Post> selectAll() {
        String sql = "select p.*, ac.user_name, count(l.like_id) as plike\n" +
                "from post p\n" +
                "         left join post_like l on p.post_id = l.post_id\n" +
                "         join member ac on ac.user_id = p.user_id\n" +
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
        String sql = "select p.*, ac.user_name, count(l.like_id) as plike\n" +
                "from post p\n" +
                "         left join post_like l on p.post_id = l.post_id\n" +
                "         join member ac on ac.user_id = p.user_id\n" +
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

    public Post selectById(int postId) {
        String sql = "select p.*, ac.user_name, count(l.like_id) as plike\n" +
                "from post p\n" +
                "         left join post_like l on p.post_id = l.post_id\n" +
                "         join member ac on ac.user_id = p.user_id\n" +
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


    public int insert(Post post) {
        int key = -1;
        String sql = "insert into post(USER_ID, CATEGORY, TOPIC, CONTENT) values  (?,?,?,?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, post.getUserId());
            ps.setString(2, post.getCategory());
            ps.setString(3, post.getTopic());
            ps.setString(4, post.getContent());
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

    public boolean update(Post post) {
        String sql = "update post\n" +
                "set category = ?, topic = ?, content = ?\n" +
                "where post_id = ?;";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, post.getCategory());
            ps.setString(2, post.getTopic());
            ps.setString(3, post.getContent());
            ps.setInt(4, post.getPostId());
            boolean execute = ps.execute();
            return execute;
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
                for (int i = 1; i <= 2; i++) {
                    ps.setInt(1, (int) (Math.random() * 5) + 1);
                    ps.setString(2, categoryName.get(c));
                    ps.setString(3, "第" + i + "篇" + categoryName.get(c) + "文章");
                    ps.setString(4, content);
                    ps.addBatch();
                }
            }
            ps.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //刪除所有資料
    public void deleteAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement("delete from post_imgs where 1=1;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("alter table post_imgs auto_increment = 0;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("delete from post_like where 1=1;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("alter table post_like auto_increment = 0;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("delete from msg_imgs where 1=1;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("alter table msg_imgs auto_increment = 0;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("delete from msg_like where 1=1;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("alter table msg_like auto_increment = 0;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("delete from msg where 1=1;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("alter table msg auto_increment = 0;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("delete from post where 1=1;");
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("alter table post auto_increment = 0;");
            preparedStatement.executeUpdate();
            connection.commit();

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
    }

    public boolean deleteById(int postId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement("delete from msg_like where msg_id in (select msg_id from msg where post_id = ?);");
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from msg_imgs where msg_id in (select msg_id from msg where post_id = ?);");
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from msg where post_id = ?;");
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from post_like where post_id = ?;");
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from post_imgs where post_id = ?;");
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from post where post_id = ?;");
            preparedStatement.setInt(1, postId);
            preparedStatement.executeUpdate();

            connection.commit();
            return true;
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
        return false;
    }

    //取得所有Post的id
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

    public boolean selectByPostIdUserId(int userId, int postId) {
        String sql = "select * from post where user_id = ? and post_id = ?;";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setInt(2, postId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

