package service;

import exceptionsCustom.UserAlreadyExistsException;
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
public class AccountService {
	@Inject @JPA
	private UserDao userDao;

	public void addUser(Account account) throws UserAlreadyExistsException {
		try {
			userDao.addUser(account);
		}
		catch (Exception ex) {
			//TODO log exception with interceptor
			throw new UserAlreadyExistsException("Exception when trying to create a user", ex);
		}
	}

	public void removeUser(Account account) {
		userDao.removeUser(account);
	}

	public ArrayList<Account> getAccounts() {
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

	public AccountService() {

	}

	///  I Think it's terrible to create extra code for testing, but its needed. . .
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
