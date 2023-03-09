package forum.service;
import forum.dao.*;

public class MsgService {
    private PostDao postDao = new PostDao();
    private MsgDao msgDao = new MsgDao();
    private MsgLikeDao msgLikeDao = new MsgLikeDao();

    public void likeGenerator() {
        msgLikeDao.generator();
    }
    public void likeClean() {
        msgLikeDao.clean();
    }
    public int likeClick(int msgId, int userId) {
        if (msgLikeDao.check(msgId, userId)) {
            msgLikeDao.delete(msgId, userId);
        } else {
            msgLikeDao.insert(msgId, userId);
        }
        return msgLikeDao.count(msgId);
    }
    public void MsgGenerator() {
     msgDao.generator(postDao.getPostIds());
    }

}





