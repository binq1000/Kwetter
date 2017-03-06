package Rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Nekkyou on 6-3-2017.
 */
@Path("/user")
@Produces("plain/text")
public class UserRest {
	@GET
	@Produces("plain/text")
	public String test() {
		return "213";
	}
}
