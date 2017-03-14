package restController;

import domain.Kweet;
import domain.Account;
import service.KweetService;
import service.AccountService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by Nekkyou on 13-3-2017.
 */
@Path("kweets")
@Produces(MediaType.APPLICATION_JSON)
@Stateless
public class KweetRest {
	@Inject
	KweetService service;

	@Inject
	AccountService accountService;

	@GET
	public ArrayList<Kweet> getAllKweets() {
		return service.getKweets();
	}

	@GET
	@Path("{id}")
	public Kweet getKweet(@PathParam("id") long id) {
		return service.findById(id);
	}

	@GET
	@Path("user/{username}")
	public ArrayList<Kweet> getKweetsFromUser(@PathParam("username") String username) {
		return new ArrayList<Kweet>(accountService.findByName(username).getKweets());
	}

	@GET
	@Path("delete/{username}/{id}")
	public void deleteKweet(@PathParam("username") String username, @PathParam("id") long id) {
		Kweet kweet = service.findById(id);
		if (kweet.getPoster().getUsername().equals(username)) {
			service.removeKweet(kweet);
		}
	}


	@GET
	@Path("add/{username}/{message}")
	public void addKweet(@PathParam("username") String username, @PathParam("message") String message) {
		Account account = accountService.findByName(username);
		Kweet kweet = new Kweet(message, account);
		service.addKweet(kweet);
	}

}
