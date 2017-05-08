package JmsClasses;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Created by Nekkyou on 1-5-2017.
 */
@Stateless
public class MessageSenderGateway {

	@Resource(mappedName = "jms/ConnectionFactory")
	ConnectionFactory connectionFactory;

	private Connection connection;
	private Session session;
	private Destination destination;
	private MessageProducer producer;

	public MessageSenderGateway() {
		System.out.println("Creating Message Sender Gateway");
		try {
			if (connectionFactory == null) {
				Context jndiContext = new InitialContext();
				connectionFactory = (ConnectionFactory) jndiContext.lookup("jms/ConnectionFactory");
			}

			connection = connectionFactory.createConnection();
			connection.start();

			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("incoming");
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public Message createMessage(String message){
		try {
			return session.createTextMessage(message);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void send(Message msg) {
		try {
			producer.send(msg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
