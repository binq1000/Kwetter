package restController;

import JmsClasses.SimpleAppGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	@DELETE
	@Path("{id}/delete")
	public void deleteKweet(@PathParam("id") long id) {
		Kweet kweet = service.findById(id);
		service.removeKweet(kweet);
	}


	@POST
	@Path("add/{username}/{message}")
	public void addKweet(@PathParam("username") String username, @PathParam("message") String message) {
		Account account = accountService.findByName(username);
		Kweet kweet = new Kweet(message, account);
		service.addKweet(kweet);
	}

	@POST
	@Path("queue/{username}/{text}")
	public void addToQueue(@PathParam("text") String text, @PathParam("username") String username) {
		System.out.println("Adding: " + text + "; To Queue");
		SimpleAppGateway sag = new SimpleAppGateway();

		Account account = accountService.findByName(username);
		Kweet kweet = new Kweet(text, account);

		ObjectMapper mapper = new ObjectMapper();
		String kweetAsJson = null;
		try {
			kweetAsJson = mapper.writeValueAsString(kweet);
			sag.sendText(kweetAsJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
