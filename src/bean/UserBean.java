package bean;

import domain.Account;
import domain.Kweet;
import service.KweetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Nekkyou on 4-3-2017.
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable {
	private KweetService service = new KweetService();

	public String getString() {
		return "String";
	}
}
