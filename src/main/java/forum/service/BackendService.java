package forum.service;

import forum.dao.CategoryDao;
import forum.dao.MsgDao;
import forum.dao.PostDao;
import forum.pojo.Category;
import forum.pojo.Post;

import java.util.ArrayList;

public class BackendService {
    private CategoryDao categoryDao = new CategoryDao();
    private PostDao postDao = new PostDao();
    private MsgDao msgDao = new MsgDao();

    public ArrayList<Category> categoryList() {
        ArrayList<Category> categories = categoryDao.selectAll();
        return categories;
    }

    public boolean categoryAdd(Category category) {
        Category exist = categoryDao.selectByName(category.getCategory());
        if (exist == null) {
            int insert = categoryDao.insert(category);
            if (insert > 0) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Post> getPostsByIds(ArrayList<Integer> ids) {
        ArrayList<Category> categories = categoryDao.selectByIds(ids);
        ArrayList<Post> posts = postDao.selectAll();
        ArrayList<Post> postsByCategory = new ArrayList<>();
        for (Post p : posts) {
            for (Category c : categories) {
                if (p.getCategory().equals(c.getCategory()))
                    postsByCategory.add(p);
            }
        }
        return postsByCategory;
    }

    public boolean deletePost(int postId) {
        return postDao.deleteById(postId);
    }

    public int deleteMsgByIds(ArrayList<Integer> ids) {
        int succeed = 0;
        for (int id : ids) {
            int i = msgDao.deleteById(id);
            succeed += i;
        }
        return succeed;
    }
}



