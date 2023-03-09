package forum.pojo;
import java.util.ArrayList;

public class Post {
    private int postId;
    private int userId;
    private String userName;
    private String category;
    private String topic;
    private String content;
    private int like;
    private ArrayList<byte[]> imgs;
    private String timestamp;

    public Post() {
    }

    public Post(int postId, int userId, String userName, String category, String topic, String content, int like, String timestamp) {
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.category = category;
        this.topic = topic;
        this.content = content;
        this.like = like;
        this.timestamp = timestamp;
    }

    public Post(int userId, String category, String topic, String content) {
        this.userId = userId;
        this.category = category;
        this.topic = topic;
        this.content = content;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public ArrayList<byte[]> getImgs() {
        return imgs;
    }

    public void setImgs(ArrayList<byte[]> imgs) {
        this.imgs = imgs;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", category='" + category + '\'' +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", like=" + like +
                ", imgs=" + imgs +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}




