import domain.Kweet;
import domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public class AccountTest {
	Account account1, account2, account3, account4, account5;
	Kweet kweet1, kweet2, kweet3, kweet4, kweet5;
	String alternativeUsername = "alt";
	String altDetails = "altDetails";
	String altEmail = "altEmail";
	String altImage = "altPath";

	@Before
	public void setUp(){
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


		account1.followUser(account2);
		account2.followUser(account1);
	}

	@Test
	public void getKweets() {
		Assert.assertTrue(account1.getKweets().size() == 2);
		Assert.assertTrue(account2.getKweets().size() == 1);
		Assert.assertTrue(account3.getKweets().size() == 0);
		Assert.assertTrue(account4.getKweets().size() == 1);
		Assert.assertTrue(account5.getKweets().size() == 1);
	}

	@Test
	public void getFollowing() {
		Assert.assertTrue(account1.getFollowing().size() == 1);
	}

	@Test
	public void getUsername() {
		Assert.assertTrue(account1.getUsername() == "UN1");
	}

	@Test
	public void setUsername() {
		account1.setUsername(alternativeUsername);
		Assert.assertTrue(account1.getUsername() == alternativeUsername);
	}

	@Test
	public void getDetails()  {
		Assert.assertTrue(account1.getDetails() == "first one");
	}

	@Test
	public void setDetails(){
		account1.setDetails(altDetails);
		Assert.assertTrue(account1.getDetails() == altDetails);
	}

	@Test
	public void geteMail() {
		Assert.assertTrue(account1.geteMail() == "test@hotmail.com");
	}

	@Test
	public void seteMail() {
		account1.seteMail(altEmail);
		Assert.assertTrue(account1.geteMail() == altEmail);
	}

	@Test
	public void getImagePath() {
		Assert.assertTrue(account1.getImagePath() == "defaultPath");
	}

	@Test
	public void setImagePath() {
		account1.setImagePath(altImage);
		Assert.assertTrue(account1.getImagePath() == altImage);
	}

	@Test
	public void addKweet() {
		//Tested by kweet construction.
	}

	@Test
	public void followUser() {
		//Used in setup

	}

	@Test
	public void unfollowUser() {

	}

}