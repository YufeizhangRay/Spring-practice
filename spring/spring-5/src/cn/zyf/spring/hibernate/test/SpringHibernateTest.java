package cn.zyf.spring.hibernate.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zyf.spring.hibernate.service.BookShopService;
import cn.zyf.spring.hibernate.service.Cashier;

class SpringHibernateTest {

	private ApplicationContext applicationContext = null;
	private BookShopService bookShopService = null;
	private Cashier cashier = null;
	
	{
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		bookShopService = applicationContext.getBean(BookShopService.class);
		cashier = applicationContext.getBean(Cashier.class);
	}
	
	@Test
	public void testCashier() {
		cashier.checkout("AA", Arrays.asList("1001","1002"));
	}
	
	@Test
	public void testBookShopService() {
		bookShopService.purchase("AA", "1001");
	}
	
	@Test
	public void testDateSource() throws SQLException {
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
