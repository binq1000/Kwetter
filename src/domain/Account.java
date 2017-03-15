package domain;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nekkyou on 4-3-2017.
 */
@Entity @Model
@NamedQueries({
		@NamedQuery(name = "user.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
		@NamedQuery(name = "user.getFollowing", query = "SELECT a FROM Account a where :id in (select f.id from a.following f)")
})
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String username;

	private String details;
	private String eMail;
	private String imagePath;

	@OneToMany
	private List<Kweet> kweets;

	@OneToMany
	@JoinTable(name = "Account_Following")
	private List<Account> following;

	@OneToMany
	@JoinTable(name = "Account_Followers")
	private List<Account> followers;

	public Account() {
	}

	public Account(String username, String details, String eMail) {
		this.username = username;
		this.details = details;
		this.eMail = eMail;
		this.imagePath = "defaultPath";

		this.kweets = new ArrayList<Kweet>();
		this.following = new ArrayList<Account>();
		this.followers = new ArrayList<Account>();
	}

	@PostConstruct
	private void initUser() {
		this.username = "default";
		this.imagePath = "defaultPath";
		this.kweets = new ArrayList<Kweet>();
		this.following = new ArrayList<Account>();
		this.followers = new ArrayList<Account>();
	}

	//region Getters and setters
	public List<Kweet> getKweets() {
		return kweets;
	}

	public List<Account> getFollowing() {
		return following;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	//endregion

	public void addKweet(Kweet kweet) {
		this.kweets.add(kweet);
	}

	public void followUser(Account account) {
		if (this.equals(account)) throw new IllegalArgumentException("Can't add yourself");
		addFollowing(account);
		account.addFollower(this);
	}

	public void unfollowUser(Account account) {
		if (this.equals(account)) throw new IllegalArgumentException("Can't unfollow yourself");
		removeFollowing(account);
		account.removeFollower(this);
	}

	public void addFollowing(Account account) {
		if (!this.following.contains(account)) {
			this.following.add(account);
		}

	}

	public void removeFollowing(Account account) {
		if (this.following.contains(account)) {
			this.following.remove(account);
		}
	}

	public void addFollower(Account account) {
		if (!this.followers.contains(account)) {
			this.followers.add(account);
		}
	}

	public void removeFollower(Account account) {
		if (this.followers.contains(account)) {
			this.followers.remove(account);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Account)) {
			return false;
		}
		Account other = (Account) o;
		if (this.username.equals(other.username) && this.details.equals(other.details) && this.eMail.equals(other.eMail)) {
			return true;
		}

		return false;
	}





}
