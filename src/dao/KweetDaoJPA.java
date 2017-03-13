package dao;

import domain.Kweet;

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
public class KweetDaoJPA implements KweetDao {

	@PersistenceContext(unitName = "KwetterPU")
	private EntityManager em;

	public KweetDaoJPA() {

	}

	@Override
	public void addKweet(Kweet kweet) {
		em.persist(kweet);
	}

	@Override
	public void removeKweet(Kweet kweet) {
		em.remove(em.merge(kweet));
	}

	@Override
	public Kweet findById(Long id) {
		TypedQuery<Kweet> query = em.createNamedQuery("kweet.findById", Kweet.class);
		query.setParameter("id", id);
		List<Kweet> result = query.getResultList();

		return result.get(0);
	}

	@Override
	public ArrayList<Kweet> getKweets() {
		Query query = em.createQuery("SELECT k FROM Kweet k");
		return  new ArrayList<Kweet>(query.getResultList());
	}
}
