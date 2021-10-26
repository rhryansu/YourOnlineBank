package yob.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import yob.repository.entities.Account;
import yob.repository.entities.Transaction;


@Stateless
public class TransactionRepositoryImpl implements TransactionRepository {
	@PersistenceContext(unitName = "YourOnlineBank-ejbPU")
	EntityManager em;
	
	@Override
	public void add(Transaction transaction) {
		em.persist(transaction);
	}
	@Override
	public void del(String no) {
		Transaction t = this.findByNo(no);
		if (t!=null) {
			em.remove(t);
		}
	}
	@Override
	public void update(Transaction transaction) {
		em.merge(transaction);
	}
	@Override
	public Transaction findByNo(String no) {
		return em.find(Transaction.class, no);
	}
	@Override
	public List<Transaction> findAll() {
		return
				em.createNamedQuery(Transaction.GET_ALL_QUERY_NAME).getResultList();
	}
	@Override
	public List<Transaction> findAllTransactionsByAccount(Account account) {
		List<Transaction> list1 = this.findTransactionsInByAccount(account);
		List<Transaction> list2 = this.findTransactionsOutByAccount(account);
		list1.addAll(list2);
		return list1;
	}
	@Override
	public List<Transaction> findTransactionsOutByAccount(Account account) {
		Query createQuery = em.createQuery("select t from transactions t where t.userAccount = :taccount");
				createQuery.setParameter("taccount", account);
				return createQuery.getResultList();
	}
	@Override
	public List<Transaction> findTransactionsInByAccount(Account account) {
		Query createQuery = em.createQuery("select t from transactions t where t.targetAccount = :taccount");
				createQuery.setParameter("taccount", account);
				return createQuery.getResultList();
	}
}