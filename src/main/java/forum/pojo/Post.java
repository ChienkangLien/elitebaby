package forum.pojo;
import lombok.Data;

import java.util.List;
@Data
public class Post {
    private int postId;
    private int userId;
    private String userName;
    private String category;
    private String topic;
    private String content;
    private List<PostImage> imgsInPost;
    private PostImage preview;
    private int like;
    String timestamp;


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
}




