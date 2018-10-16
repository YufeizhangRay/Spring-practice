package cn.zyf.spring.tx;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class SpringTransactionTest {

	private ApplicationContext applicationContext = null;
	private BookShopDao bookShopDao = null; 
	private BookShopService bookShopService = null;
	private Cashier cashier = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopDao = applicationContext.getBean(BookShopDao.class);
		bookShopService = applicationContext.getBean(BookShopService.class);
		cashier = applicationContext.getBean(Cashier.class);
	}
	
	@Test
	public void testTransactionPropagetion() {
		cashier.checkout("AA", Arrays.asList("1001","1002"));
	}
	
	@Test
	public void testBookService() {
		bookShopService.purchase("AA", "1001");
	}
	
	@Test
	public void testBookShopDaoUpdateUserAccount() {
		bookShopDao.updateUserAccount("AA", 100);
	}
	
	@Test
	public void testBookShopDaoUpdateBookStock() {
		bookShopDao.updateBookStock("1001");
	}
	
	@Test
	public void testBookShopDaoFindPriceByIsbn() {
		System.out.println(bookShopDao.findBookPriceByIsbn("1001"));
	}

}
