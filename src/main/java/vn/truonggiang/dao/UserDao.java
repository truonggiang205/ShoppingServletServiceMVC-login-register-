
package vn.truonggiang.dao;

import vn.truonggiang.model.User;

public interface UserDao {
	User get(String username);
	void insert(User user);
}
