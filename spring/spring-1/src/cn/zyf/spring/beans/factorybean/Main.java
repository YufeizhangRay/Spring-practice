package cn.zyf.spring.beans.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-beanfactory.xml");
		
		Car car = (Car) applicationContext.getBean("car");
		System.out.println(car);
	}
}
