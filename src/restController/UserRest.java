package restController;

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
	@Path("{username}/delete")
	public void removeUser(@PathParam("username") String username) {
		Account account = service.findByName(username);
		service.removeUser(account);
	}

	@GET
	@Path("add/{username}/{details}/{email}")
	public Account addUser(@PathParam("username") String username, @PathParam("details") String details, @PathParam("email") String email) {
		Account account = new Account(username, details, email);
		service.addUser(account);
		return account;
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
	@Path("followers/{username}")
	public ArrayList<Account> getFollowers(@PathParam("username") String username) {
		Account account = service.findByName(username);
		return new ArrayList<Account>(account.getFollowing());
	}
}
