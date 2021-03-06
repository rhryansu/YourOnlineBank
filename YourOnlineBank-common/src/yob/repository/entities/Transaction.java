package yob.repository.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import yob.util.IDUtils;

@Entity(name = "transactions")
@NamedQueries({ @NamedQuery(name = Transaction.GET_ALL_QUERY_NAME, query =
		"select t from transactions t") })
public class Transaction implements Serializable {
	public static final String GET_ALL_QUERY_NAME = "Transaction.getAll";
	@Id
	@Column(name = "transaction_no")
	private String transactionNo;
	@Column(name = "transaction_name")
	private String transactionName;
	private double amount;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "types")
	@Column(name = "type")
	private List<String> types;
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE,
			CascadeType.DETACH })
	@JoinColumn(name = "user_account_no", nullable = false)
	private Account userAccount;
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE,
			CascadeType.DETACH })
	@JoinColumn(name = "target_account_no", nullable = false)
	private Account targetAccount;
	public Transaction() {
		super();
	}
	public Transaction(String transactionName, double amount, List<String>
	types, Account userAccount,
	Account targetAccount) {
		super();
		this.transactionNo = IDUtils.generateTransactionNo();
		this.transactionName = transactionName;
		this.amount = amount;
		this.types = types;
		this.userAccount = userAccount;
		this.targetAccount = targetAccount;
	}
	public Transaction(String transactionNo, String transactionName, double
			amount, List<String> types,
			Account userAccount, Account targetAccount) {
		super();
		this.transactionNo = transactionNo;
		this.transactionName = transactionName;
		this.amount = amount;
		this.types = types;
		this.userAccount = userAccount;
		this.targetAccount = targetAccount;
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public String getTransactionName() {
		return transactionName;
	}
	public void setTransactionName(String transactionName) {
		this.transactionName = transactionName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public Account getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}
	public Account getTargetAccount() {
		return targetAccount;
	}
	public void setTargetAccount(Account targetAccount) {
		this.targetAccount = targetAccount;
	}
	@Override
	public String toString() {
		return "Transaction No: " + transactionNo + ", Amount: " + amount +
				", From Account: "
				+ userAccount.getAccountNo() + ", To Account : " +
				targetAccount.getAccountNo();
	}
}
