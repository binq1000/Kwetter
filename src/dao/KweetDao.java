package dao;

import domain.Kweet;

import java.util.ArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public interface KweetDao {
	void addKweet(Kweet kweet);

	void removeKweet(Kweet kweet);

	Kweet findById(Long id);

	ArrayList<Kweet> getKweets();
}
