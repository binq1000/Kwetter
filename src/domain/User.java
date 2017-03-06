package domain;


import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nekkyou on 4-3-2017.
 */
@Entity @Model
public class User {
	private String username;
	private String details;
	private String eMail;
	private String imagePath;
	private List<Kweet> kweets;
	private List<User> following;
	private List<User> followers;

	public User() {
	}

	public User(String username, String details, String eMail) {
		this.username = username;
		this.details = details;
		this.eMail = eMail;
		this.imagePath = "defaultPath";

		this.kweets = new ArrayList<Kweet>();
		this.following = new ArrayList<User>();
		this.followers = new ArrayList<User>();
	}

	@PostConstruct
	private void initUser() {
		this.imagePath = "defaultPath";
		this.kweets = new ArrayList<Kweet>();
		this.following = new ArrayList<User>();
		this.followers = new ArrayList<User>();
	}

	//region Getters and setters
	public List<Kweet> getKweets() {
		return kweets;
	}

	public List<User> getFollowing() {
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

	public void followUser(User user) {
		if (this.equals(user)) throw new IllegalArgumentException("Can't add yourself");
		addFollowing(user);
		user.addFollower(this);
	}

	public void addFollowing(User user) {
		this.following.add(user);
	}

	public void addFollower(User user) {
		this.followers.add(user);
	}

}
