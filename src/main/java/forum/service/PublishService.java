package forum.service;

import forum.dao.*;
import forum.pojo.*;
import login.UserDao;


import java.io.InputStream;
import java.util.ArrayList;

public class PublishService {
    private CategoryDao categoryDao = new CategoryDao();
    private PostDao postDao = new PostDao();
    private UserDao userDao = new UserDao();
    private PostImgDao postImgDao = new PostImgDao();
    private MsgDao msgDao = new MsgDao();
//    private MsgImgDao msgImgDao = new MsgImgDao();


    public FormBean getForm(int userId) {
        ArrayList<Category> categories = categoryDao.selectAll();
        String[] categoryNames = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoryNames[i] = categories.get(i).getCategory();
        }
        ;
//        String name = userDao.userNameById(userId);

        FormBean formBean = new FormBean();
        formBean.setCategoryNames(categoryNames);
//        formBean.setUserName(name);

        return formBean;
    }

    public void insertPost(Post post, ArrayList<InputStream> ins) {
        int postId = postDao.insert(post);
        postImgDao.insert(postId, ins);
    }

    public Msg insertMsg(Msg msg, ArrayList<InputStream> ins) {
        int msgId = msgDao.insert(msg);
//        msgImgDao.insert(msgId,ins);
        msg = msgDao.selectById(msgId);
//        msg = msgImgDao.selectById(msg);
        System.out.println(msg);
        return msg;
    }
}





