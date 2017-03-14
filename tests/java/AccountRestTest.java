import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Account;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Nekkyou on 13-3-2017.
 */
public class AccountRestTest {
	ObjectMapper mapper;

	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper();

		HttpUriRequest request = new HttpGet("localhost:8081/Kwetter_war_exploded/restController/users/add/123/asd/zc");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
	}

	@Test
	public void getAllUsers() throws IOException {
		HttpUriRequest request = new HttpGet("localhost:8081/Kwetter_war_exploded/restController/users");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		Account resource = mapper.readValue(response.getEntity().getContent(), Account.class);
	}

	@Test
	public void findByUsername() throws IOException {
		HttpUriRequest request = new HttpGet("localhost:8081/Kwetter_war_exploded/restController/users/123");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		Account resource = mapper.readValue(response.getEntity().getContent(), Account.class);
	}

	@Test
	public void removeUser() throws Exception {

	}

	@Test
	public void addUser() throws Exception {

	}

}