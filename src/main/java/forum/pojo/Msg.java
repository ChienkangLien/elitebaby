package forum.pojo;
import lombok.Data;
@Data
public class Msg {
    private int msgId;
    private int postId;
    private int userId;
    private String userName;
    private String content;
    private PostImage preview;
    private int like;
    String timestamp;
    public Msg(int msgId, int postId,int userId, String userName, String content, int like, String timestamp) {
        this.msgId = msgId;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.like = like;
        this.timestamp = timestamp;
    }
}




