package vn.truonggiang.service.impl;

import vn.truonggiang.dao.UserDao;
import vn.truonggiang.dao.impl.UserDaoImpl;
import vn.truonggiang.model.User;
import vn.truonggiang.service.UserService;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();

    @Override
    public User login(String username, String password) {
        User user = userDao.get(username.trim()); 

        if (user != null) {
            String dbPassword = user.getPassword().trim();
            String inputPassword = password.trim();

            if (dbPassword.equals(inputPassword)) {
                return user;
            }
        }
        return null; 
    }

    @Override
    public User get(String username) {
        return userDao.get(username);
    }
}
