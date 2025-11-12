package vn.truonggiang.service;

import vn.truonggiang.model.User;

public interface UserService {
    User login(String username, String password);
    User get(String username);
}
