package forum.service;

import forum.dao.CategoryDao;
import forum.dao.CollectionDao;
import forum.pojo.Category;

import java.util.ArrayList;

public class CategoryService {
    private CategoryDao categoryDao = new CategoryDao();
    private CollectionDao collectionDao = new CollectionDao();

    //Category分類HCs/LCs
    public ArrayList<ArrayList<Category>> getAll() {
        ArrayList<Category> LCs = new ArrayList<>();
        ArrayList<Category> HCs = new ArrayList<>();

        ArrayList<Category> categories = categoryDao.selectAll();
        for (Category c : categories) {
            if (c.getLevel() == 0) {
                LCs.add(c);
            } else {
                HCs.add(c);
            }
        }
        ArrayList<ArrayList<Category>> Cs = new ArrayList<>();
        Cs.add(LCs);
        Cs.add(HCs);
        return Cs;
    }
    public boolean collect(int userId, int categoryId) {
        if (collectionDao.check(userId, categoryId)) {
            collectionDao.delete(userId, categoryId);
        } else {
            collectionDao.insert(userId, categoryId);
        }
        return collectionDao.check(userId, categoryId);
    }
    public ArrayList<Category> getCollectedCategories(int userId){
        return categoryDao.selectCollected(userId);
    }
}


