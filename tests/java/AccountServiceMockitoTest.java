import dao.UserDao;
import domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import service.AccountService;

import java.util.ArrayList;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Nekkyou on 14-3-2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceMockitoTest {

	AccountService service;

	@Mock
	UserDao userDao;

	Account account;

	@Before
	public void setUp() throws Exception {
		service = new AccountService();

		service.setUserDao(userDao);
		account = new Account("user", "details", "mail");
		when(userDao.findByName("user")).thenReturn(account);
	}

	@Test
	public void addUser() throws Exception {
		service.addUser(account);
		ArrayList<Account> accounts = service.getAccounts();
		verify(userDao, times(1)).getAccounts();
	}

	@Test
	public void removeUser() throws Exception {
		service.removeUser(account);
		verify(userDao, times(1)).removeUser(account);
	}

	@Test
	public void getAccounts() throws Exception {
		service.getAccounts();
		verify(userDao, times(1)).getAccounts();
	}

	@Test
	public void findByName() throws Exception {
		Account foundAccount = service.findByName("user");
		Assert.assertSame(account, foundAccount);
	}

	@Test
	public void followUser() throws Exception {
		Account otherAccount = new Account("124", "asd", "qwezxd");
		service.followUser(account, otherAccount);
		verify(userDao, times(1)).followUser(account, otherAccount);
	}

	@Test
	public void unfollowUser() throws Exception {
		Account otherAccount = new Account("124", "asd", "qwezxd");
		service.followUser(account, otherAccount);
		verify(userDao, times(1)).followUser(account, otherAccount);

		service.unfollowUser(account, otherAccount);
		verify(userDao, times(1)).unfollowUser(account, otherAccount);

	}

}