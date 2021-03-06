package yob.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import yob.repository.entities.Account;
import yob.repository.entities.Transaction;
import yob.util.FacesContextUtils;
import yob.mbeans.*;

@Named("accountController")
@RequestScoped
public class AccountController {
	private String accountNo;
	private Account account;
	public AccountController() {
		this.accountNo = FacesContextUtils.getParameter("no");
		this.account = ((AccountMbean) FacesContextUtils.getManagedBean("accountMbean")).getAccoutByNo(accountNo);
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public int getTransactionsNumber() {
		return account.getTransactions().size() +
				account.getRelatedTransactions().size();
	}
	public String getLastTransactionInInfo() {
		if(account.getRelatedTransactions().size() == 0) {
			return "No Tranaction In Info.";
		}else {
			Transaction transactionIn =
					account.getRelatedTransactions().get(account.getRelatedTransactions().size() -
							1);
			return transactionIn.toString();
		}
	}
	public String getLastTransactionOutInfo() {
		if (account.getTransactions().size() == 0) {
			return "No Tranaction Out Info.";
		}else {
			Transaction transactionOut =
					account.getTransactions().get(account.getTransactions().size() - 1);
			return transactionOut.toString();
		}
	}
}
