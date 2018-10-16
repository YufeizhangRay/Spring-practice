package cn.zyf.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		/*
		//创建 HelloWorld 的一个对象
		HelloWorld helloWorld = new HelloWorld();
		//为 name 赋值
		helloWorld.setName("Ray");
		*/
		
		//1.创建 Spring 的 IOC 容器对象
		//ApplicationContext 代表 IOC 容器
		//ClassPathXmlApplicationContext 是 ApplicationContext 接口的实现类.该实现类从类路径下加载配置文件
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//2.从 IOC 容器中获取 Bean 实例
		//利用 id 定位到 IOC 容器中的 bean
	    HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorld");
		//利用类型返回 IOC 容器的 bean，但要求 IOC 容器必须只能有一个该类型的 bean
		//HelloWorld helloWorld = applicationContext.getBean(HelloWorld.class);
		System.out.println(helloWorld);
		
		//3.调用 hello 方法
		//helloWorld.hello();
		
		Car car = (Car) applicationContext.getBean("car");
		System.out.println(car);
		
		car = (Car) applicationContext.getBean("car2");
		System.out.println(car);
		
		Person person = (Person) applicationContext.getBean("person");
		System.out.println(person);
	}
}
