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
		@NamedQuery(name = "user.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})
@Table(name = "Account")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;
	private String details;
	private String eMail;
	private String imagePath;

	@OneToMany
	private List<Kweet> kweets;
	@OneToMany
	private List<User> following;
	@OneToMany
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
		this.username = "default";
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

	public void unfollowUser(User user) {
		if (this.equals(user)) throw new IllegalArgumentException("Can't unfollow yourself");
		removeFollowing(user);
		user.removeFollower(this);
	}

	public void addFollowing(User user) {
		if (!this.following.contains(user)) {
			this.following.add(user);
		}

	}

	public void removeFollowing(User user) {
		if (this.following.contains(user)) {
			this.following.remove(user);
		}
	}

	public void addFollower(User user) {
		if (!this.followers.contains(user)) {
			this.followers.add(user);
		}
	}

	public void removeFollower(User user) {
		if (this.followers.contains(user)) {
			this.followers.remove(user);
		}
	}

}
