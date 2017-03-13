package Rest;

import domain.Kweet;
import domain.User;
import service.KweetService;
import service.UserService;

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
	UserService userService;

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
		User user = userService.findByName(username);
		Kweet kweet = new Kweet(message, user);
		service.addKweet(kweet);
	}

}
