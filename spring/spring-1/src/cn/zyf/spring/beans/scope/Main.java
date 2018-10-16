package cn.zyf.spring.beans.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.zyf.spring.beans.autowire.Address;
import cn.zyf.spring.beans.autowire.Car;
import cn.zyf.spring.beans.autowire.Person;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-scope.xml");
		
		Car car = (Car) applicationContext.getBean("car");
		Car car2 = (Car) applicationContext.getBean("car");
		
		System.out.println(car == car2);
	}
	
}
