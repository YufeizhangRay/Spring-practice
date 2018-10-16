package cn.zyf.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		ArithmeticCalculator arithmeticCalculator =  (ArithmeticCalculator) applicationContext.getBean("arithmeticCalculator");
		
		System.out.println(arithmeticCalculator.getClass().getName());
		
		int result = arithmeticCalculator.add(1, 2);
		System.out.println("result: "+result);
		
		result = arithmeticCalculator.div(1000, 200);
		System.out.println("result: "+result);
	}
}
