import domain.Account;
import domain.Kweet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.KweetService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Created by Nekkyou on 6-3-2017.
 */
@Stateless
public class KweetServiceTest {
	@Inject
	public KweetService service;

	Account account1, account2, account3, account4, account5;

	Kweet kweet1, kweet2, kweet3, kweet4, kweet5;

	@PostConstruct
	private void init() {
		if (service == null) {
			throw new NullPointerException("service is null");
		}
	}

	@Before
	public void setUp() throws Exception {
		account1 = new Account("UN1", "first one", "test@hotmail.com");
		account2 = new Account("UN2", "second one", "test2@hotmail.com");
		account3 = new Account("UN3", "third one", "test3@hotmail.com");
		account4 = new Account("UN4", "fourth one", "test4@hotmail.com");
		account5 = new Account("UN5", "fifth one", "test5@hotmail.com");

		kweet1 = new Kweet("message 1", account1);
		kweet2 = new Kweet("message 2", account1);
		kweet3 = new Kweet("message 3", account2);
		kweet4 = new Kweet("message 4", account4);
		kweet5 = new Kweet("message 5", account5);
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