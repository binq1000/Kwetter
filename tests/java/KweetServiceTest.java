import domain.Kweet;
import domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.KweetService;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public class KweetServiceTest {
	@Inject
	public KweetService service;

	User user1, user2, user3, user4, user5;
	Kweet kweet1, kweet2, kweet3, kweet4, kweet5;

	@Before
	public void setUp() throws Exception {
		user1 = new User("UN1", "first one", "test@hotmail.com");
		user2 = new User("UN2", "second one", "test2@hotmail.com");
		user3 = new User("UN3", "third one", "test3@hotmail.com");
		user4 = new User("UN4", "fourth one", "test4@hotmail.com");
		user5 = new User("UN5", "fifth one", "test5@hotmail.com");

		kweet1 = new Kweet("message 1", user1);
		kweet2 = new Kweet("message 2", user1);
		kweet3 = new Kweet("message 3", user2);
		kweet4 = new Kweet("message 4", user4);
		kweet5 = new Kweet("message 5", user5);
	}

	@Test
	public void addKweet() throws Exception {
		Assert.assertTrue(service.getKweets().isEmpty());
		service.addKweet(kweet1);
		Assert.assertTrue(service.getKweets().size() == 1);
	}

	@Test
	public void removeKweet() throws Exception {
		Assert.assertTrue(service.getKweets().isEmpty());
		service.addKweet(kweet1);
		Assert.assertTrue(service.getKweets().size() == 1);
		service.removeKweet(kweet1);
		Assert.assertTrue(service.getKweets().isEmpty());
	}

	@Test
	public void findById() throws Exception {
		Assert.assertTrue(service.getKweets().isEmpty());
		service.addKweet(kweet1);

		Assert.assertTrue(service.findById(kweet1.getId()) == kweet1);
	}

	@Test
	public void getKweets() throws Exception {
		Assert.assertTrue(service.getKweets().isEmpty());
		service.addKweet(kweet1);
		Assert.assertTrue(service.getKweets().size() == 1);
	}

}