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
	private Account poster;

	public Kweet() {
	}

	public Kweet(String message, Account poster) {
		this.message = message;
		this.poster = poster;
		this.datePosted = new Date();

		poster.addKweet(this);
	}

	public Kweet(Long id, String message, Account poster, Date datePosted) {
		this.id = id;
		this.message = message;
		this.poster = poster;
		this.datePosted = datePosted;
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

	public Account getPoster() {
		return poster;
	}

	public void setPoster(Account poster) {
		this.poster = poster;
	}

	//endregion

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Kweet)) {
			return false;
		}
		Kweet other = (Kweet) o;
		if (this.poster.equals(other.poster) && this.message.equals(other.message)) {
			return true;
		}

		return false;
	}
}
