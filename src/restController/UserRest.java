package restController;

import domain.User;
import service.UserService;

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
	UserService service;

	@GET
	public ArrayList<User> getAllUsers() {
		return service.getUsers();
	}

	@GET
	@Path("{username}")
	public User pathTest(@PathParam("username") String username) {
		return service.findByName(username);
	}

	@GET
	@Path("delete/{username}")
	public void removeUser(@PathParam("username") String username) {
		User user = service.findByName(username);
		service.removeUser(user);
	}

	@GET
	@Path("add/{username}/{details}/{email}")
	public void addUser(@PathParam("username") String username, @PathParam("details") String details, @PathParam("email") String email) {
		User user = new User(username, details, email);
		service.addUser(user);
	}
}
