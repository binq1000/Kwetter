package service;


import dao.JPA;
import dao.KweetDao;
import domain.Kweet;
import interceptorClasses.VolgTrendInterceptor;

import javax.ejb.Stateless;
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

	@Interceptors(VolgTrendInterceptor.class)
	public void addKweet(Kweet kweet) {
		kweetDao.addKweet(kweet);
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
