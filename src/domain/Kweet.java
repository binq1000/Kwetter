package domain;

import javax.enterprise.inject.Model;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Nekkyou on 4-3-2017.
 */
@Entity @Model
@NamedQueries({
		@NamedQuery(name = "kweet.findById", query = "SELECT k FROM Kweet k WHERE k.id = :id")
})
@Table(name = "Kweet")
public class Kweet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String message;
	private Date datePosted;

	@ManyToOne
	private User poster;

	public Kweet() {
	}

	public Kweet(String message, User poster) {
		this.message = message;
		this.poster = poster;
		this.datePosted = new Date();

		poster.addKweet(this);
	}

	//region Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}

	public User getPoster() {
		return poster;
	}

	public void setPoster(User poster) {
		this.poster = poster;
	}

	//endregion


}
