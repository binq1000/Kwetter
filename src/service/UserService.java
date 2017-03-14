package service;

import dao.JPA;
import dao.UserDao;
import domain.Account;

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

	public void addUser(Account account) {
		userDao.addUser(account);
	}

	public void removeUser(Account account) {
		userDao.removeUser(account);
	}

	public ArrayList<Account> getUsers() {
		return userDao.getAccounts();
	}

	public Account findByName(String name){
		return userDao.findByName(name);
	}

	public void followUser(Account account, Account accountToFollow) {
		userDao.followUser(account, accountToFollow);
	}

	public void unfollowUser(Account account, Account accountToUnfollow) {
		userDao.unfollowUser(account, accountToUnfollow);
	}

	public UserService() {

	}

}
