package batchClasses;

import domain.Kweet;
import service.AccountService;
import service.KweetService;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Nekkyou on 22-3-2017.
 */
@Dependent
@Named("KweetWriter")
public class KweetWriter implements ItemWriter {

	@Inject
	private KweetService service;

	@Inject
	private AccountService accountService;

	@Override
	public void open(Serializable serializable) throws Exception {

	}

	@Override
	public void close() throws Exception {

	}

	@Override
	public void writeItems(List<Object> list) throws Exception {
		for (Object item : list) {
			if (item != null) {
				Kweet kweet = (Kweet) item;

				if (accountService.findByName(kweet.getPoster().getUsername()) == null) {
					accountService.addUser(kweet.getPoster());
				}

				service.addKweet(kweet);
			}
		}
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return null;
	}
}
