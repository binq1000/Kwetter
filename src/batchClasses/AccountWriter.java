package batchClasses;

import domain.Account;
import service.AccountService;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Nekkyou on 28-3-2017.
 */
@Dependent
@Named(value = "AccountWriter")
public class AccountWriter implements ItemWriter {

	@Inject
	private AccountService service;

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
				Account account = (Account) item;
				service.addUser(account);
			}
		}
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return null;
	}
}
