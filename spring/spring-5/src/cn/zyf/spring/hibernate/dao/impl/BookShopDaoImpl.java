package cn.zyf.spring.hibernate.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.zyf.spring.hibernate.dao.BookShopDao;
import cn.zyf.spring.hibernate.exception.BookStockException;
import cn.zyf.spring.hibernate.exception.UserAccountException;

@Repository
public class BookShopDaoImpl implements BookShopDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	//不推荐使用 HibernateTemplate 和 HibernateDaosSupport 
	//因为这样会导致 DAO 和 Spring 的 API 耦合，可移植性差
//	private HibernateTemplate hibernateTemplate;
	
	//获取和当前线程绑定的 Session
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public int findBookPriceByIsbn(String isbn) {
		String hql = "SELECT b.price FROM Book b WHERE b.isbn = ?0";
		Query query = getSession().createQuery(hql).setParameter(0, isbn);
		return (Integer) query.uniqueResult();
	}

	@Override
	public void updateBookStock(String isbn) {
		//验证书的库存是否充足
		String hql2 = "SELECT b.stock FROM Book b WHERE b.isbn = ?0";
		int stock = (int) getSession().createQuery(hql2).setParameter(0, isbn).uniqueResult();
		if(stock == 0) {
			throw new BookStockException("库存不足！");
		}
		
		String hql = "UPDATE Book b SET b.stock = b.stock - 1 WHERE b.isbn = ?0";
		getSession().createQuery(hql).setParameter(0, isbn).executeUpdate();
	}

	@Override
	public void updateUserAccount(String username, int price) {
		//验证余额是否足够
		String hql2 = "SELECT a.balance FROM Account a WHERE a.name = ?0";
		int balance = (int) getSession().createQuery(hql2).setParameter(0, username).uniqueResult();
		if(balance < price) {
			throw new UserAccountException("余额不足！");
		}	
		
		String hql = "UPDATE Account a SET a.balance = a.balance - ?0 WHERE a.name = ?1";
		getSession().createQuery(hql).setParameter(0, price).setParameter(1, username).executeUpdate();	
	}

}
