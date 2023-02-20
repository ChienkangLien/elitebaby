package forum.pojo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Msg {
    private int msgId;
    private int postId;
    private int userId;
    private String userName;
    private String content;
    private int like;
    private ArrayList<byte[]> imgs;
    private String timestamp;


    public Msg(){};

    public Msg(int msgId, int postId, int userId, String userName, String content, int like, String timestamp) {
        this.msgId = msgId;
        this.postId = postId;
        this.userId = userId;
        this.userName = userName;
        this.content = content;
        this.like = like;
        this.timestamp = timestamp;
    }

    public Msg(int postId, int userId, String content) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
    }
}




