package dao;

import domain.User;

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
	public void addUser(User user) {
		em.persist(user);
	}

	@Override
	public void removeUser(User user) {
		em.remove(em.merge(user));
	}

	@Override
	public User findByName(String username) {
		TypedQuery<User> query = em.createNamedQuery("user.findByUsername", User.class);
		query.setParameter("username", username);
		List<User> result = query.getResultList();

		return result.get(0);
	}

	@Override
	public ArrayList<User> getUsers() {
		Query query = em.createQuery("SELECT u FROM User u");
		return  new ArrayList<User>(query.getResultList());
	}
}
