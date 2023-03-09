package forum.pojo;
import java.util.ArrayList;

public class PostBean {
    private Post post;
    private ArrayList<Msg> msgs;
    private int dataLength;
    private int userId;
    private String userName;
    private boolean edit;

    public PostBean() {
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    @Override
    public String toString() {
        return "PostBean{" +
                "post=" + post +
                ", msgs=" + msgs +
                ", dataLength=" + dataLength +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
