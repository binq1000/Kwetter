package bean;

import WebSocketClasses.Message;

import javax.ejb.*;
import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;

/**
 * Created by Nekkyou on 8-5-2017.
 */
@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EchoBean {

	@EJB
	private EchoBean delegate;

	@Asynchronous
	public void send(Session session, Message message, int repeats, long delay, double delayMultiplier) {
		try {
			synchronized(session){
				session.getBasicRemote().sendObject(message);
			}
			Thread.sleep(delay);
		} catch (InterruptedException | IOException | EncodeException ex) {
			throw new IllegalStateException(ex);
		}
		if(1<repeats){
			delegate.send(
					session,
					new Message("." + message.getText()),
					repeats-1,
					Math.round(delay*delayMultiplier),
					delayMultiplier
			);
		}
	}
}
