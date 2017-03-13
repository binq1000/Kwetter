package service;

import dao.JPA;
import dao.UserDao;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
@Stateless
public class UserService {
	@Inject @JPA
	private UserDao userDao;

	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void removeUser(User user) {
		userDao.removeUser(user);
	}

	public ArrayList<User> getUsers() {
		return userDao.getUsers();
	}

	public User findByName(String name){
		return userDao.findByName(name);
	}

	public UserService() {

	}

}
