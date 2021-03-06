package yob.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import yob.util.IDUtils;

@Named("transaction")
@RequestScoped
public class TransactionContainer {
	private String transactionNo;
	private String transactionName;
	private double amount;
	private String fromAccountNo;
	private String targetAccountNo;
	private String types;
	public TransactionContainer() {
		super();
	
	}
	public String getTransactionNo() {
		setTransactionNo();
		return transactionNo;
	}
	public void setTransactionNo() {
		this.transactionNo = IDUtils.generateTransactionNo();
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
	public String getFromAccountNo() {
		return fromAccountNo;
	}
	public void setFromAccountNo(String fromAccountNo) {
		this.fromAccountNo = fromAccountNo;
	}
	public String getTargetAccountNo() {
		return targetAccountNo;
	}
	public void setTargetAccountNo(String targetAccountNo) {
		this.targetAccountNo = targetAccountNo;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
}