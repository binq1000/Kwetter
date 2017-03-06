package dao;

import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
@Stateless @Default
public class UserDaoColl implements UserDao {
	CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<User>();

	@Override
	public void addUser(User user) {
		if (!users.contains(user)) {
			users.add(user);
		}
	}

	@Override
	public void removeUser(User user) {
		if (users.contains(user)) {
			users.remove(user);
		}
	}

	@Override
	public User findByName(String username) {
		for (User user : users) {
			if (user.getUsername().contentEquals(username)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public ArrayList<User> getUsers() {
		return new ArrayList<User>(users);
	}
}
