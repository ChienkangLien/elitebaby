package forum.pojo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PostBean {
    private Post post;
    private ArrayList<Msg> msgs;
    private int dataLength;
    private int userId;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public ArrayList<Msg> getMsgs() {
        return msgs;
    }

    public void setMsgs(ArrayList<Msg> msgs) {
        this.msgs = msgs;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
