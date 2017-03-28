package batchClasses;

import domain.Account;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * Created by Nekkyou on 28-3-2017.
 */
@Dependent
@Named(value = "AccountProcessor")
public class AccountProcessor implements ItemProcessor {
	@Override
	public Object processItem(Object o) throws Exception {
		ImportUser user = (ImportUser) o;
		System.out.println("Processing item: " + user.username);
		Account account = new Account(user.username, user.details, user.email, user.imagePath);
		account.setPassword("f148389d080cfe85952998a8a367e2f7eaf35f2d72d2599a5b0412fe4094d65c");
		return account;
	}
}
