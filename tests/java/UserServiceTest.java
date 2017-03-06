import domain.Kweet;
import domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.UserService;

import javax.inject.Inject;

import static org.junit.Assert.*;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public class UserServiceTest {
	@Inject
	UserService service;

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
	public void addUser() {
		Assert.assertTrue(service.getUsers().isEmpty());
		service.addUser(user1);
		Assert.assertTrue(service.getUsers().size() == 1);
	}

	@Test
	public void addDupplicateUser() {
		Assert.assertTrue(service.getUsers().size() == 0);
		service.addUser(user1);
		Assert.assertTrue(service.getUsers().size() == 1);
		service.addUser(user1);
		Assert.assertTrue(service.getUsers().size() == 1);
	}

	@Test
	public void removeUser() {
		service.addUser(user1);
		Assert.assertTrue(service.getUsers().size() == 1);
		service.removeUser(user1);
		Assert.assertTrue(service.getUsers().isEmpty());
		service.removeUser(user1);
		Assert.assertTrue(service.getUsers().isEmpty());
	}

	@Test
	public void findByName() {
		Assert.assertTrue(service.findByName("randomUsername") == null);
		service.addUser(user1);
		Assert.assertTrue(service.findByName("UN1") == user1);
	}

	@Test
	public void getUsers() {
		Assert.assertTrue(service.getUsers().isEmpty());
		service.addUser(user1);
		Assert.assertTrue(service.getUsers().size() == 1);
		service.addUser(user2);
		Assert.assertTrue(service.getUsers().size() == 2);
	}

}