package domain;

import javax.enterprise.inject.Model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Nekkyou on 15-3-2017.
 */
@Entity @Model
@Table(name = "AccountGroup")
public class AccountGroup implements Serializable{
	@Id
	private String groupName;

	@ManyToMany
	@JoinTable(name = "Account_Group",
			joinColumns = @JoinColumn(name = "groupName",
			referencedColumnName = "groupName"),
	inverseJoinColumns = @JoinColumn(name = "username",
			referencedColumnName = "username"))
	private List<Account> accounts;


	public AccountGroup() {
		//Empty constructor
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
