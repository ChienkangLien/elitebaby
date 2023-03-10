package forum.service;

import forum.dao.*;
import forum.pojo.Category;
import forum.pojo.Msg;
import forum.pojo.Post;
import forum.pojo.PostBean;

import java.util.ArrayList;

public class PostService {
    private CategoryDao categoryDao = new CategoryDao();
    private PostDao postDao = new PostDao();
    private PostLikeDao postLikeDao = new PostLikeDao();
    private PostImgDao postImgDao = new PostImgDao();
    private MsgDao msgDao = new MsgDao();
//    private MsgImgDao msgImgDao = new MsgImgDao();

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

    public PostBean getPostBean(int postId) {
        Post post = postDao.selectById(postId);
        post = postImgDao.selectById(post);
        ArrayList<Msg> msgs = msgDao.selectAll(postId);
//        msgs = msgImgDao.selectAll(msgs);
//        int length = msgs.size();
        PostBean postBean = new PostBean();
        postBean.setPost(post);
        postBean.setMsgs(msgs);
//        postBean.setDataLength(length);
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
}





