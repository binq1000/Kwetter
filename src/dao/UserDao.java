package dao;

import domain.User;

import java.util.ArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public interface UserDao {
	void addUser(User user);

	void removeUser(User user);

	User findByName(String username);

	ArrayList<User> getUsers();

}
