package service;


import dao.JPA;
import dao.KweetDao;
import domain.Kweet;
import interceptorClasses.VolgTrendInterceptor;
import optionalEvents.AddKweetEvent;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.ArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
@Stateless
public class KweetService {
	@Inject @JPA
	private KweetDao kweetDao;

	@Inject
	private Event<AddKweetEvent> event;

	@Interceptors(VolgTrendInterceptor.class)
	public void addKweet(Kweet kweet) {
		AddKweetEvent kweetEvent = new AddKweetEvent(kweet, kweetDao);
		event.fire(kweetEvent);
		//kweetDao.addKweet(kweet);
	}

	public void removeKweet(Kweet kweet) {
		kweetDao.removeKweet(kweet);
	}

	public Kweet findById(Long id) {
		return kweetDao.findById(id);
	}

	public ArrayList<Kweet> getKweets() {
		return kweetDao.getKweets();
	}

	public KweetService() {

	}
}
