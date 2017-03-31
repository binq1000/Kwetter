package optionalEvents;

import dao.KweetDao;
import domain.Kweet;

/**
 * Created by Nekkyou on 29-3-2017.
 */
public class AddKweetEvent {

	private Kweet kweet;
	private KweetDao dao;

	public Kweet getKweet() {
		return kweet;
	}

	public KweetDao getDao() {
		return dao;
	}

	public AddKweetEvent(Kweet kweet, KweetDao dao) {
		this.dao = dao;
		this.kweet = kweet;
	}
}
