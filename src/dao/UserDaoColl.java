package dao;

import domain.Account;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
@Stateless @Default
public class UserDaoColl implements UserDao {
	CopyOnWriteArrayList<Account> accounts = new CopyOnWriteArrayList<Account>();

	@Override
	public void addUser(Account account) {
		if (!accounts.contains(account)) {
			accounts.add(account);
		}
	}

	@Override
	public void removeUser(Account account) {
		if (accounts.contains(account)) {
			accounts.remove(account);
		}
	}

	@Override
	public Account findByName(String username) {
		for (Account account : accounts) {
			if (account.getUsername().contentEquals(username)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public void followUser(Account account, Account accountToFollow) {
		account.followUser(accountToFollow);
	}

	@Override
	public void unfollowUser(Account account, Account accountToUnfollow) {
		account.unfollowUser(accountToUnfollow);
	}

	public ArrayList<Account> getAccounts() {
		return new ArrayList<Account>(accounts);
	}
}
