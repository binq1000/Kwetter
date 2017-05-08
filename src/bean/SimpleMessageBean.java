package bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Kweet;
import service.AccountService;
import service.KweetService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.IOException;

/**
 * Created by Nekkyou on 1-5-2017.
 */
@MessageDriven(
		activationConfig = {
				@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/incoming"),
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "incoming"),
				@ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.14.4")
		}
)
public class SimpleMessageBean implements MessageListener {

	@Inject
	private KweetService service;

	@Inject
	private AccountService accountService;

	@Override
	public void onMessage(Message message) {
		System.out.println("Message Received");
		if (message instanceof TextMessage) {
			try {
				System.out.println(((TextMessage) message).getText());
				handleIncomingKweet(((TextMessage) message).getText());
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleIncomingKweet(String text) {
		Kweet k = null;

		ObjectMapper mapper = new ObjectMapper();
		try {
			k = mapper.readValue(text, Kweet.class);
			k.setPoster(accountService.findByName(k.getPoster().getUsername()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (k != null)
			service.addKweet(k);
	}
}
