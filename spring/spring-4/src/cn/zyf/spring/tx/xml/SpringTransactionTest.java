package cn.zyf.spring.tx.xml;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zyf.spring.tx.xml.service.BookShopService;
import cn.zyf.spring.tx.xml.service.Cashier;

class SpringTransactionTest {

	private ApplicationContext applicationContext = null;
	private BookShopDao bookShopDao = null; 
	private BookShopService bookShopService = null;
	private Cashier cashier = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext-tx-xml.xml");
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

}
