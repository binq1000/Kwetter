import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Account;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Nekkyou on 13-3-2017.
 */
public class AccountRestTest {
	ObjectMapper mapper;

	String basePath = "http://localhost:8081/Kwetter_war_exploded/rest";

	Account account;

	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper();

		HttpUriRequest request = new HttpGet( basePath + "/users/add/123/asd/zc");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		request = new HttpGet(basePath + "/users/123");
		response = HttpClientBuilder.create().build().execute(request);

		account = mapper.readValue(response.getEntity().getContent(), Account.class);

		//TODO create a database cleaner so it will clean at every setup.
	}

	@Test
	public void getAllUsers() throws IOException {
		HttpUriRequest request = new HttpGet(basePath + "/users");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		Account[] resource = mapper.readValue(response.getEntity().getContent(), Account[].class);

		Assert.assertTrue(resource.length == 1);
	}

	@Test
	public void findByUsername() throws IOException {
		HttpUriRequest request = new HttpGet(basePath + "/users/123");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		Account resource = mapper.readValue(response.getEntity().getContent(), Account.class);

		Assert.assertEquals(account, resource);
	}

	@Test
	public void removeUser() throws IOException{
		HttpUriRequest request = new HttpDelete(basePath + "/users/123/delete");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		request = new HttpGet(basePath + "/users/123");
		response = HttpClientBuilder.create().build().execute(request);
		int code = response.getStatusLine().getStatusCode();

		Assert.assertTrue(code == 500);
	}

	@Test
	public void addUser() throws Exception {
		HttpUriRequest request = new HttpGet( basePath + "/users/add/124/asd/zc");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		request = new HttpGet(basePath + "/users/124");
		response = HttpClientBuilder.create().build().execute(request);

		Account createdAccount = mapper.readValue(response.getEntity().getContent(), Account.class);
		Assert.assertTrue(createdAccount.getUsername().equals("124"));
		Assert.assertTrue(createdAccount.getDetails().equals("asd"));
		Assert.assertTrue(createdAccount.geteMail().equals("zc"));
	}

}