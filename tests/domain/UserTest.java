package domain;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public class UserTest {
	User user1, user2, user3, user4, user5;
	Kweet kweet1, kweet2, kweet3, kweet4, kweet5;
	String alternativeUsername = "alt";

	@Before
	public void setUp(){
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


		user1.followUser(user2);

	}

	@Test
	public void getKweets() {
		Assert.assertTrue(user1.getKweets().size() == 2);
		Assert.assertTrue(user2.getKweets().size() == 1);
		Assert.assertTrue(user3.getKweets().size() == 0);
		Assert.assertTrue(user4.getKweets().size() == 1);
		Assert.assertTrue(user5.getKweets().size() == 1);
	}

	@Test
	public void getFollowing() {
		Assert.assertTrue(user1.getFollowing().size() == 1);
	}

	@Test
	public void getUsername() {
		Assert.assertTrue(user1.getUsername() == "UN1");
	}

	@Test
	public void setUsername() {
		user1.setUsername(alternativeUsername);
		Assert.assertTrue(user1.getUsername() == alternativeUsername);
	}

	@Test
	public void getDetails() throws Exception {

	}

	@Test
	public void setDetails() throws Exception {

	}

	@Test
	public void geteMail() throws Exception {

	}

	@Test
	public void seteMail() throws Exception {

	}

	@Test
	public void getImagePath() throws Exception {

	}

	@Test
	public void setImagePath() throws Exception {

	}

	@Test
	public void addKweet() throws Exception {

	}

	@Test
	public void followUser() throws Exception {

	}

}