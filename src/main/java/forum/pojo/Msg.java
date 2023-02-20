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

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
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
}




