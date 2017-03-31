package optionalEvents;

import dao.KweetDao;
import dao.UserDao;

import javax.enterprise.event.Observes;

/**
 * Created by Nekkyou on 29-3-2017.
 */
public class AddKweetHandler {

	public void addKweet(@Observes AddKweetEvent addKweetEvent) {
		System.out.println("Enterd the Handler");
		addKweetEvent.getDao().addKweet(addKweetEvent.getKweet());
	}
}
