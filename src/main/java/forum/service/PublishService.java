package forum.service;

import forum.dao.CategoryDao;
import forum.dao.MsgDao;
import forum.dao.PostDao;
import forum.dao.PostLikeDao;
import forum.pojo.Category;
import forum.pojo.FormBean;
import login.UserDao;

import java.util.ArrayList;

public class PublishService {
    private CategoryDao categoryDao = new CategoryDao();
    private PostDao postDao = new PostDao();
    private PostLikeDao postLikeDao = new PostLikeDao();
    private MsgDao msgDao = new MsgDao();
    private UserDao userDao = new UserDao();


    public FormBean getForm(int userId) {
        ArrayList<Category> categories = categoryDao.selectAll();
        String[] categoryNames = new String[categories.size()];
        for (int i = 0; i < categories.size(); i++) {
            categoryNames[i] = categories.get(i).getCategory();
        }
        ;
        String name = userDao.userNameById(userId);

        FormBean formBean = new FormBean();
        formBean.setCategoryNames(categoryNames);
        formBean.setUserName(name);

        return formBean;
    }

}





