package bean;

import domain.Account;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Nekkyou on 4-3-2017.
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable {
	@Inject
	private Account account;

	public Account getAccount() {
		return account;
	}
}
