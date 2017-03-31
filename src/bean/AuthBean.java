package bean;

import domain.Account;
import service.AccountService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.security.Principal;

/**
 * Created by Nekkyou on 29-3-2017.
 */
@ManagedBean
@SessionScoped
public class AuthBean {
	private Account account;

	@Inject
	private AccountService service;

	public Account getAccount() {
		if (account == null) {
			Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
			if (principal != null) {
				account = service.findByName(principal.getName());
			}
		}
		return account;
	}
}
