package cn.zyf.spring.hibernate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zyf.spring.hibernate.dao.BookShopDao;
import cn.zyf.spring.hibernate.service.BookShopService;

@Service
public class BookShopServiceImpl implements BookShopService{

	@Autowired
	private BookShopDao bookShopDao;
	
	/**
	 * Spring Hibernate 的事务流程
	 * 1.在方法开始之前
	 * ①获取 Session
	 * ②把 Session 和当前线程绑定，这样就可以以在 DAO 中使用 SessionFactory
	 * 	的 getCurrentSession() 方法来获取 Session 了
	 * ③开启事务
	 * 
	 * 2.若方法正常结束，即没有出现异常，则
	 * ①提交事务
	 * ②使和当前线程绑定的 Session 解除绑定
	 * ③关闭 Session
	 * 
	 * 3.若方法出现异常则
	 * ①事务回滚
	 * ②使和当前线程绑定的 Session 解除绑定
	 * ③关闭 Session
	 */
	@Override
	public void purchase(String username, String isbn) {
		int price = bookShopDao.findBookPriceByIsbn(isbn);
		bookShopDao.updateBookStock(isbn);
		bookShopDao.updateUserAccount(username, price);
		
	}

}
