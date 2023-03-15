package forum.service;

import forum.dao.*;
import forum.pojo.Category;
import forum.pojo.Msg;
import forum.pojo.Post;
import forum.pojo.PostBean;

import java.util.ArrayList;

public class PostService {
    private AccessDao accessDao = new AccessDao();
    private CategoryDao categoryDao = new CategoryDao();
    private PostDao postDao = new PostDao();
    private PostLikeDao postLikeDao = new PostLikeDao();
    private PostImgDao postImgDao = new PostImgDao();
    private MsgDao msgDao = new MsgDao();
    private MsgImgDao msgImgDao = new MsgImgDao();

    public ArrayList<Post> getAll(boolean order, String categoryId, String topic) {
        ArrayList<Post> posts = new ArrayList<>();
        if (order) {
            posts = postDao.selectPopular();
        } else {
            posts = postDao.selectAll();
        }

        if (categoryId != null && !categoryId.equals("null")) {
            int id = Integer.parseInt(categoryId);
            String category = categoryDao.selectById(id).getCategory();
            posts = categoryFilter(posts, category);
        }
        if (topic != null) {
            posts = topicFilter(posts, topic);
        }

        posts = postImgDao.selectAll(posts);
        return posts;
    }

    public ArrayList<Post> categoryFilter(ArrayList<Post> posts, String category) {
        ArrayList<Post> filteredPosts = new ArrayList<>();
        for (Post p : posts) {
            if (category.equals(p.getCategory()))
                filteredPosts.add(p);
        }
        return filteredPosts;
    }

    public ArrayList<Post> topicFilter(ArrayList<Post> posts, String topic) {
        ArrayList<Post> filteredPosts = new ArrayList<>();
        for (Post p : posts) {
            if (p.getTopic().contains(topic))
                filteredPosts.add(p);
        }
        return filteredPosts;
    }

    public void likeGenerator() {
        postLikeDao.generator(postDao.getPostIds());
    }

    public void likeClean() {
        postLikeDao.clean();
    }

    public int likeClick(int postId, int userId) {
        if (postLikeDao.check(postId, userId)) {
            postLikeDao.delete(postId, userId);
        } else {
            postLikeDao.insert(postId, userId);
        }
        return postLikeDao.count(postId);
    }

    public boolean checkUserAndPostMatch(int userId, int postId) {
        boolean b = postDao.selectByPostIdUserId(userId, postId);
        return b;
    }


    public PostBean getPostBean(int postId, int userId) {
        Post post = postDao.selectById(postId);//文章基本屬性
        post = postImgDao.selectById(post);//文章圖片
        ArrayList<Msg> msgs = msgDao.selectAll(postId);//全部留言
        msgs = msgImgDao.selectAll(msgs);//留言圖片
        int length = msgs.size();
        String userName = accessDao.userNameById(userId);//登入者名稱

        PostBean postBean = new PostBean();
        postBean.setPost(post);
        postBean.setMsgs(msgs);
        postBean.setDataLength(length);
        postBean.setUserName(userName);

        postBean.setUserId(userId);
        if (checkUserAndPostMatch(userId, postId)) {
            postBean.setEdit(true);
        }
        return postBean;
    }

    public void PostGenerator() {
        ArrayList<Category> categories = categoryDao.selectAll();
        ArrayList<String> categoryName = new ArrayList<>();
        for (Category c : categories) {
            categoryName.add(c.getCategory());
        }
        postDao.postGenerator(categoryName);
    }

    public void deleteAll() {
        postDao.deleteAll();
    }

    public boolean update(Post post) {
        return postDao.update(post);
    }
}





