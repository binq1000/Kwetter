package restController;

import exceptionsCustom.UserAlreadyExistsException;
import domain.Account;
import service.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by Nekkyou on 6-3-2017.
 */
@Path("users")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class UserRest {

	@Inject
	AccountService service;

	@GET
	public ArrayList<Account> getAllUsers() {
		return service.getAccounts();
	}

	@GET
	@Path("{username}")
	public Account pathTest(@PathParam("username") String username) {
		return service.findByName(username);
	}

	@DELETE
	@Path("{username}")
	public void removeUser(@PathParam("username") String username) {
		Account account = service.findByName(username);
		service.removeUser(account);
	}

	@GET
	@Path("add/{username}/{details}/{email}")
	public Account addUser(@PathParam("username") String username, @PathParam("details") String details, @PathParam("email") String email) throws Exception {
		Account account = new Account(username, details, email);
		account.setPassword("f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c");
		try {
			service.addUser(account);
			return account;
		} catch (UserAlreadyExistsException uae) {
			throw uae;
		}
		catch (Exception ex) {
			throw ex;
		}

	}

	@POST
	@Path("follow/{username}/{otherUsername}")
	public void followUser(@PathParam("username") String username, @PathParam("otherUsername") String otherUsername) {
		Account account = service.findByName(username);
		Account accountToFollow = service.findByName(otherUsername);
		service.followUser(account, accountToFollow);
	}

	@POST
	@Path("unfollow/{username}/{otherUsername}")
	public void unfollowUser(@PathParam("username") String username, @PathParam("otherUsername") String otherUsername) {
		Account account = service.findByName(username);
		Account accountToFollow = service.findByName(otherUsername);
		service.unfollowUser(account, accountToFollow);
	}

	@GET
	@Path("{username}/followers")
	public ArrayList<Account> getFollowers(@PathParam("username") String username) {
		Account account = service.findByName(username);
		return new ArrayList<Account>(account.getFollowing());
	}
}
