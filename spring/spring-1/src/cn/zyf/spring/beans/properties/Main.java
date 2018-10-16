package cn.zyf.spring.beans.properties;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zyf.spring.beans.autowire.Address;
import cn.zyf.spring.beans.autowire.Person;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-properties.xml");
		
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}
	
}
