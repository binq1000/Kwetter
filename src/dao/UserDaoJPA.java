package dao;

import exceptionsCustom.UserAlreadyExistsException;
import domain.Account;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nekkyou on 13-3-2017.
 */
@Stateless @JPA
public class UserDaoJPA implements UserDao{

	@PersistenceContext(unitName = "KwetterPU")
	private EntityManager em;

	@Override
	public void addUser(Account account) throws UserAlreadyExistsException {
		try {
			System.out.println("Persisting account: " + account.getUsername());
			em.persist(account);
		}
		catch (Exception ex) {
			throw new UserAlreadyExistsException("User already exists");
		}

	}

	@Override
	public void removeUser(Account account) {
		em.remove(em.merge(account));
	}

	@Override
	public Account findByName(String username) {
		TypedQuery<Account> query = em.createNamedQuery("user.findByUsername", Account.class);
		query.setParameter("username", username);
		List<Account> result = query.getResultList();

		if (result == null || result.isEmpty()) {
			return null;
		}
		return result.get(0);
	}

	@Override
	public void followUser(Account account, Account accountToFollow) {
		if (account != null && accountToFollow != null) {
			account.followUser(accountToFollow);
		}
		em.merge(account);
		em.merge(accountToFollow);
	}

	@Override
	public void unfollowUser(Account account, Account accountToUnfollow) {
		account.unfollowUser(accountToUnfollow);
		em.persist(account);
	}

	public ArrayList<Account> getAccounts() {
		Query query = em.createQuery("SELECT u FROM Account u");
		return  new ArrayList<Account>(query.getResultList());
	}
}
