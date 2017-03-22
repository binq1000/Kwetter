import exceptionsCustom.UserAlreadyExistsException;
import domain.Kweet;
import domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.AccountService;

import javax.inject.Inject;

/**
 * Created by Nekkyou on 6-3-2017.
 */
public class AccountServiceTest {
	@Inject
	AccountService service;

	Account account1, account2, account3, account4, account5;
	Kweet kweet1, kweet2, kweet3, kweet4, kweet5;

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
	public void addUser() throws UserAlreadyExistsException {
		Assert.assertTrue(service.getAccounts().isEmpty());
		service.addUser(account1);
		Assert.assertTrue(service.getAccounts().size() == 1);
	}

	@Test
	public void addDupplicateUser() throws UserAlreadyExistsException {
		Assert.assertTrue(service.getAccounts().size() == 0);
		service.addUser(account1);
		Assert.assertTrue(service.getAccounts().size() == 1);
		service.addUser(account1);
		Assert.assertTrue(service.getAccounts().size() == 1);
	}

	@Test
	public void removeUser() throws UserAlreadyExistsException {
		service.addUser(account1);
		Assert.assertTrue(service.getAccounts().size() == 1);
		service.removeUser(account1);
		Assert.assertTrue(service.getAccounts().isEmpty());
		service.removeUser(account1);
		Assert.assertTrue(service.getAccounts().isEmpty());
	}

	@Test
	public void findByName() throws UserAlreadyExistsException {
		Assert.assertTrue(service.findByName("randomUsername") == null);
		service.addUser(account1);
		Assert.assertTrue(service.findByName("UN1") == account1);
	}

	@Test
	public void getUsers() throws UserAlreadyExistsException {
		Assert.assertTrue(service.getAccounts().isEmpty());
		service.addUser(account1);
		Assert.assertTrue(service.getAccounts().size() == 1);
		service.addUser(account2);
		Assert.assertTrue(service.getAccounts().size() == 2);
	}

}