package WebSocketClasses;

import domain.Account;
import domain.Kweet;
import service.AccountService;
import service.KweetService;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.nio.ByteBuffer;

/**
 * Created by Nekkyou on 8-5-2017.
 */
@ServerEndpoint(
		value = "/receive",
		configurator = Configurator.class,
		encoders = {MessageEncoder.class},
		decoders = {MessageDecoder.class})
public class KweetEndPoint {
	@OnOpen
	public void open (Session session, EndpointConfig c) {
		//Add to list of active users

		Object o = session.getUserProperties();
		System.out.println("Opened end point");
	}

	@OnClose
	public void close(Session session, CloseReason reason) {
		//Remove from list of active users
	}

	@OnMessage
	public void textMessage(Session session, String msg){

		System.out.println("Text : " + msg);

		KweetService service = new KweetService();
		AccountService accountService = new AccountService();
		Account account = accountService.findByName("steve");

		Kweet k = new Kweet(msg, account);

		service.addKweet(k);
	}

	@OnMessage
	public void binaryMessage(Session session, ByteBuffer msg){

		System.out.println("Binary : " + msg.toString());
	}

	@OnMessage
	public void pongMessage(Session session, PongMessage msg){
		System.out.println("Pong : " + msg.getApplicationData().toString());
	}

}
