package dao;

import domain.Kweet;

import javax.ejb.DependsOn;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
@Stateless @Default
public class KweetDaoColl implements KweetDao {
	private CopyOnWriteArrayList<Kweet> kweets = new CopyOnWriteArrayList<Kweet>();

	@Override
	public void addKweet(Kweet kweet) {
		if (!kweets.contains(kweet)) {
			Long id = new Long(kweets.size());
			kweet.setId(id);
			kweets.add(kweet);
		}

	}

	@Override
	public void removeKweet(Kweet kweet) {
		if (kweets.contains(kweet)) {
			kweets.remove(kweet);
		}
	}

	@Override
	public Kweet findById(Long id) {
		for (Kweet kweet : kweets) {
			if (kweet.getId().equals(id)) {
				return kweet;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Kweet> getKweets() {
		return new ArrayList<Kweet>(kweets);
	}
}
