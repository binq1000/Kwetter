package bean;

import domain.Account;
import domain.Kweet;
import domain.TestObject;
import service.AccountService;

import javax.annotation.ManagedBean;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.POST;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nekkyou on 4-3-2017.
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

	@Inject
	private AccountService service;

	private Account account = null;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String username;

	public UserBean() {

	}

	private Account getAccount() {
		if (account == null) {
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			AuthBean neededBean
					= (AuthBean) FacesContext.getCurrentInstance().getApplication()
					.getELResolver().getValue(elContext, null, "authBean");

			account = neededBean.getAccount();
		}

		return account;
	}

	public String getString() {
		return service.toString();
	}

	public String getUser() {
		return getAccount().getUsername();
	}

	public List<Kweet> getKweets() {
		if (getAccount() != null) {
			ArrayList<Kweet> kweets = new ArrayList<Kweet>();
			for (Kweet k : account.getKweets()) {
				Kweet newKweet = new Kweet(k.getId(), k.getMessage(), k.getPoster(), k.getDatePosted());
				kweets.add(newKweet);
			}
			return kweets;
		}
		return new ArrayList<Kweet>();

	}

	public String returnString(String s) {
		return s;
	}

	public List<TestObject> testString() {
		ArrayList<TestObject> strings = new ArrayList<TestObject>();
		strings.add(new TestObject("test"));
		strings.add(new TestObject("test2"));

		return strings;
	}

	public List<Account> getFollowingFromAccount(String username) {
		return service.findByName(username).getFollowing();
	}

	public List<Account> getFollowersFromAccount(String username) {
		return service.findByName(username).getFollowers();
	}

	public void followUser(String username) {
		Account accountToFollow = service.findByName(username);
		if (!getAccount().getFollowing().contains(accountToFollow)) {
			service.followUser(getAccount(), accountToFollow);
			if (getAccount().getFollowing().contains(accountToFollow)) {
				System.out.println("Followed user");
			}
			else {
				System.out.println("Failed to follow");
			}
		}
	}
}
