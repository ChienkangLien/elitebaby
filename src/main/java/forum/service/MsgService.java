package forum.service;
import forum.dao.MsgDao;
import forum.dao.MsgLikeDao;
import forum.dao.PostDao;
import org.junit.Test;

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
            msgLikeDao.minus(msgId, userId);
        } else {
            msgLikeDao.plus(msgId, userId);
        }
        return msgLikeDao.count(msgId);
    }
    public void MsgGenerator() {
     msgDao.generator(postDao.getPostIds());
    }
    @Test
    public void test(){

    }
}





