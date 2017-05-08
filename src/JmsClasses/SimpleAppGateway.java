package JmsClasses;

import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Created by Nekkyou on 1-5-2017.
 */
public class SimpleAppGateway {

	@Inject
	MessageSenderGateway injectSender;

	MessageSenderGateway sender;

	MessageReceiverGateway receiver;

	public SimpleAppGateway() {
		sender = new MessageSenderGateway();
	}

	public void sendText(String text) {
		Message msg = sender.createMessage(text);
		sender.send(msg);
	}
}
