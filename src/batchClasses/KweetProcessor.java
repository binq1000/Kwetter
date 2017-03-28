package batchClasses;

import domain.Account;
import domain.Kweet;
import service.AccountService;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Nekkyou on 22-3-2017.
 */

@Dependent
@Named("KweetProcessor")
public class KweetProcessor implements ItemProcessor{

	@Inject
	private AccountService service;



	@Override
	public Object processItem(Object o) throws Exception {
		ImportKweet importKweet = (ImportKweet) o;

		Account account = service.findByName(importKweet.poster.username);
		if (account == null) {
			account = new Account(importKweet.poster.username, importKweet.poster.details, importKweet.poster.email, importKweet.poster.imagePath);
			account.setPassword("f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c");
		}
		Kweet kweet = new Kweet(importKweet.message, account);

		return kweet;
	}
}
