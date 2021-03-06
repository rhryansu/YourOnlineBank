package yob.repository.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import yob.util.IDUtils;

@Entity(name = "account")
@NamedQueries({ @NamedQuery(name = Account.GET_ALL_NAMED_QUERY, query =
		"select a from account a") })
public class Account implements Serializable {
	public static final String GET_ALL_NAMED_QUERY = "Account.getAll";
	@Id
	@Column(name = "account_no")
	private String accountNo;
	@Column(name = "account_name")
	private String accountName;
	@Embedded
	private Address address;
	private double total;
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "owned_by", nullable = false)
	private User owner;
	@OneToMany(mappedBy = "userAccount", fetch = FetchType.EAGER)
	private List<Transaction> transactions;
	@OneToMany(mappedBy = "targetAccount", fetch = FetchType.EAGER)
	private List<Transaction> relatedTransactions;
	public Account() {
		super();
	}
	public Account(String accountName, Address address, double total, User
			owner) {
		super();
		this.accountNo = IDUtils.generateAccountNo();
		this.accountName = accountName;
		this.address = address;
		this.total = total;
		this.owner = owner;
		this.transactions = null;
		this.relatedTransactions = null;
	}
	public Account(String accountNo, String accountName, Address address,
			double total, User owner,
			List<Transaction> transactions, List<Transaction>
	relatedTransactions) {
		super();
		this.accountNo = accountNo;
		this.accountName = accountName;
		this.address = address;
		this.total = total;
		this.owner = owner;
		this.transactions = transactions;
		this.relatedTransactions = relatedTransactions;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public List<Transaction> getRelatedTransactions() {
		return relatedTransactions;
	}
	public void setRelatedTransactions(List<Transaction>
	relatedTransactions) {
		this.relatedTransactions = relatedTransactions;
	}
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountName=" +
				accountName + ", address=" + address + ", total="
				+ total + ", owner=" + owner + ", transactions=" +
				transactions + ", relatedTransactions="
				+ relatedTransactions + "]";
	}
}