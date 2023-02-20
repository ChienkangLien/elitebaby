package forum.pojo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class PostBean {
    private Post post;
    private ArrayList<Msg> msgs;
    private int dataLength;
    private int userId;
}
