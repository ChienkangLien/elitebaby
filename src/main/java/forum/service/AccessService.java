package forum.service;

import forum.dao.AccessDao;
import forum.pojo.Access;


public class AccessService {
    private AccessDao accessDao = new AccessDao();

    public Access login(String userName, String password) {
        return accessDao.login(userName, password);
    }

    public String userNameById(int userId) {
        return accessDao.userNameById(userId);
    }
}








