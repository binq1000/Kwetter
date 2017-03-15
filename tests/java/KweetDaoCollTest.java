import dao.KweetDao;
import dao.KweetDaoColl;
import domain.Account;
import domain.Kweet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public class KweetDaoCollTest {
	KweetDao kweetDao = new KweetDaoColl();
	Account account1, account2, account3, account4, account5;
	Kweet kweet1, kweet2, kweet3, kweet4, kweet5;

	@Before
	public void setUp() {
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
	public void addKweet() {
		kweetDao.addKweet(kweet1);
		Assert.assertTrue(kweetDao.getKweets().size() == 1);
	}

	@Test
	public void removeKweet() {
		Assert.assertTrue(kweetDao.getKweets().size() == 0);

		kweetDao.addKweet(kweet1);
		Assert.assertTrue(kweetDao.getKweets().size() == 1);

		kweetDao.removeKweet(kweet1);
		Assert.assertTrue(kweetDao.getKweets().size() == 0);
	}

	@Test
	public void findById() {
		kweetDao.addKweet(kweet1);

		Assert.assertEquals(kweetDao.findById(kweet1.getId()), kweet1);
	}

	@Test
	public void getKweets() {
		kweetDao.addKweet(kweet1);
		Assert.assertTrue(kweetDao.getKweets().size() == 1);
	}

}