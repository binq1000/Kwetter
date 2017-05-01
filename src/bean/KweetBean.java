package bean;

import domain.Account;
import domain.Kweet;
import service.KweetService;

import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Nekkyou on 2-4-2017.
 */
@Named(value = "KweetBean")
@SessionScoped
public class KweetBean implements Serializable{

	@Inject
	private KweetService service;

	private AuthBean authBean = null;

	private void setAuthBean() {
		if (authBean == null) {
			ELContext elContext = FacesContext.getCurrentInstance().getELContext();
			authBean
					= (AuthBean) FacesContext.getCurrentInstance().getApplication()
					.getELResolver().getValue(elContext, null, "authBean");
		}
	}

	public String getKweetText() {
		return kweetText;
	}

	public void setKweetText(String kweetText) {
		this.kweetText = kweetText;
	}

	private String kweetText;

	private Account getAccount(String username) {
		setAuthBean();
		return authBean.getAccountFromName(username);
	}

	public List<Kweet> getAllKweets() {
		return service.getKweets();
	}

	public List<Kweet> getKweetsFromAccount(String username) {
		return getAccount(username).getKweets();
	}

	public String postKweet() {
		setAuthBean();
		if (kweetText != null) {
			setKweetText(kweetText);
			System.out.println(kweetText);

			Account acc = authBean.getAccount();
			if (!kweetText.isEmpty() && acc != null) {
				Kweet k = new Kweet(kweetText, acc);
				service.addKweet(k);
			}

		}
		return null;
	}
}
