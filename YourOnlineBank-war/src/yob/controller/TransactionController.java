package yob.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import yob.repository.entities.Transaction;
import yob.util.FacesContextUtils;
import yob.mbeans.*;

@Named("transactionController")
@RequestScoped
public class TransactionController {
	private String transactionNo;
	private Transaction transaction;
	public TransactionController() throws Exception{
		this.transactionNo = FacesContextUtils.getParameter("no");
		this.transaction = ((TransactionMbean)FacesContextUtils.getManagedBean("transactionMbean")).getTransactionByNo(transactionNo);
	}
	public String getTransactionNo() {
		return transactionNo;
	}
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public String getStringTypes() {
		String str = "";
		for(String type : this.transaction.getTypes()) {
			str += type + " ";
		}
		return str;
	}
}
