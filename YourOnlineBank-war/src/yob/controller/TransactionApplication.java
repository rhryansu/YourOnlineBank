package yob.controller;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import yob.mbeans.TransactionMbean;
import yob.repository.entities.Transaction;
import yob.util.FacesContextUtils;

@Named("transactionApp")
@ApplicationScoped
public class TransactionApplication {
	private List<Transaction> transactions;
	private TransactionMbean transactionMbean;
	public TransactionApplication() throws Exception {
		this.transactions = new ArrayList<Transaction>();
		this.transactionMbean = FacesContextUtils.getManagedBean("transactionMbean");
		updateTransactions();
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public TransactionMbean getTransactionMbean() {
		return transactionMbean;
	}
	public void setTransactionMbean(TransactionMbean transactionMbean) {
		this.transactionMbean = transactionMbean;
	}
	public void updateTransactions() throws Exception{
		transactions.clear();
		for (Transaction t : transactionMbean.getAllTransactions()) {
			transactions.add(t);
		}
	}
	public void remove(Transaction transaction) throws Exception {
		try {
			transactionMbean.removeTransactionByNo(transaction.getTransactionNo());
			FacesContextUtils.showMessage("Transaction(" + transaction.getTransactionNo() + ") has been removed.");
		} catch (Exception e) {
			FacesContextUtils.showMessage("Remove failed...");
			e.printStackTrace();
		}
		updateTransactions();
	}
	public void add(TransactionContainer transactionContainer) throws Exception {
		UserAccountController userAccountController = FacesContextUtils.getManagedBean("userAccountController");
		TargetAccountController targetAccountController = FacesContextUtils.getManagedBean("targetAccountController");
		transactionContainer.setFromAccountNo(userAccountController.getAccountNo());
		transactionContainer.setTargetAccountNo(targetAccountController.getAccountNo());
		transactionMbean.addTransaction(transactionContainer);
		FacesContextUtils.showMessage("Transaction made!");
		transactionMbean.processTransaction(transactionContainer);
	}
}