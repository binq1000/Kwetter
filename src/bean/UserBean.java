package bean;

import domain.Account;
import domain.Kweet;
import service.AccountService;
import service.KweetService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nekkyou on 4-3-2017.
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable {
	private AccountService service = new AccountService();
	private String username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();

	public UserBean() {
		//currentAccount = service.findByName(username);
	}

	public String getString() {
		return service.toString();
	}

	public String getUser() {
		return username;
	}
}
