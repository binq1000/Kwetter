import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Kweet;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by Nekkyou on 13-3-2017.
 */
public class KweetRestTest {
	ObjectMapper mapper;

	String basePath = "http://localhost:8081/Kwetter_war_exploded/rest";

	Kweet kweet;

	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper();

		HttpUriRequest request = new HttpGet( basePath + "/users/add/123/asd/zc");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		request = new HttpPost( basePath + "/kweets/add/123/message");
		response = HttpClientBuilder.create().build().execute(request);


		request = new HttpGet(basePath + "/kweets");
		response = HttpClientBuilder.create().build().execute(request);

		Kweet[] kweets = mapper.readValue(response.getEntity().getContent(), Kweet[].class);
		kweet = kweets[0];

		//TODO create a database cleaner so it will clean at every setup.
	}

	@Test
	public void getAllKweets() throws Exception {
		HttpUriRequest request = new HttpGet( basePath + "/kweets");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		Kweet[] kweets = mapper.readValue(response.getEntity().getContent(), Kweet[].class);
		Assert.assertEquals(1, kweets.length);
	}

	@Test
	public void getKweet() throws Exception {
		HttpUriRequest request = new HttpGet( basePath + "/kweets/" + kweet.getId());
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		Kweet resource = mapper.readValue(response.getEntity().getContent(), Kweet.class);
		Assert.assertEquals(kweet, resource);
	}

	@Test
	public void deleteKweet() throws Exception {
		Long kweetId = kweet.getId();

		HttpUriRequest request = new HttpDelete(basePath + "/kweets/" + kweetId + "/delete");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		request = new HttpGet(basePath + "/kweets/" + kweetId);
		response = HttpClientBuilder.create().build().execute(request);
		int code = response.getStatusLine().getStatusCode();

		Assert.assertTrue(code == 500);
	}

	@Test
	public void addKweet() throws Exception {
		String message = "SomeMessage";

		HttpUriRequest request = new HttpPost( basePath + "/kweets/add/123/" + message);
		HttpResponse response = HttpClientBuilder.create().build().execute(request);

		request = new HttpGet( basePath + "/kweets");
		response = HttpClientBuilder.create().build().execute(request);

		Kweet[] kweets = mapper.readValue(response.getEntity().getContent(), Kweet[].class);

		Kweet resource = kweets[kweets.length - 1];

		Assert.assertEquals(resource.getMessage(), message);
		Assert.assertEquals(resource.getPoster().getUsername(), "123");
	}

}