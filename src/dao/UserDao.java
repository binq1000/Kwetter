package dao;

import domain.Account;

import java.util.ArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public interface UserDao {
	void addUser(Account account);

	void removeUser(Account account);

	Account findByName(String username);

	void followUser(Account account, Account accountToFollow);

	void unfollowUser(Account account, Account accountToUnfollow);

	ArrayList<Account> getAccounts();

}
