package cn.zyf.spring.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 可以使用 @Order 注解指定切面的优先级，值越小优先级越高 
 * @author ray_cn
 *
 */
@Order(2)  //切面执行的优先级
@Aspect    //定义为一个且切面
@Component //放入 IOC 容器
public class LoggingAspect {

	/**
	 * 定义一个方法，用于声明切入点表达式.一般的，该方法中不需要填入其他代码
	 * 使用 @Pointcut 来声明切入点表达式
	 * 后面的其他通知直接使用方法名来引用当前的切入点表达式(提取公因式)
	 */
//	@Pointcut("execution(* cn.zyf.spring.aop.ArithmeticCalculator.*(..))")
//	public void declareJointPointExpression() {}
//	
//	@Before("execution(* cn.zyf.spring.aop.ArithmeticCalculator.*(..))")
//	public void beforeMethod(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().getName();
//		List<Object> list = Arrays.asList(joinPoint.getArgs());
//		
//		System.out.println("The method "+methodName+" begins with "+list);
//	}
//	
//	@After("execution(* cn.zyf.spring.aop.ArithmeticCalculator.*(..))")
//	public void afterMethod(JoinPoint joinPoint) {
//		String methodName = joinPoint.getSignature().getName();		
//		System.out.println("The method "+methodName+" ends");
//	}
//	
//	@AfterReturning(value="declareJointPointExpression()",returning ="result")
//	public void afterReturning(JoinPoint joinPoint, Object result) {
//		String methodName = joinPoint.getSignature().getName();		
//		System.out.println("The method "+methodName+" ends with "+result);
//	}
//	
//	@AfterThrowing(value="declareJointPointExpression()",throwing="e")
//	public void afterThrowing(JoinPoint joinPoint, Exception e) {
//		String methodName = joinPoint.getSignature().getName();		
//		System.out.println("The method "+methodName+" occurs exception "+e);
//	}
	
	/**
	 * 环绕通知需要携带 ProceedingJoinPoint 类型的参数
	 * 环绕通知类似于动态代理的全过程:ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
	 * 且环绕通知必须有返回值 返回值即为目标方法的返回值
	 */
	@Around("declareJointPointExpression()")
	public Object aroundMethod(ProceedingJoinPoint point) {
		Object result = null;
		String methodName = point.getSignature().getName();
		
		try {
			//前置通知
			System.out.println("The method " + methodName + " begins with "+Arrays.asList(point.getArgs()));
			//执行目标方法
			result = point.proceed();
			//返回通知
			System.out.println("The method " + methodName + " ends with "+result);
		} catch (Throwable e) {
			//异常通知
			System.out.println("The method " + methodName + " occurs exception: "+e);
		}
		//后置通知
		System.out.println("The Method " + methodName + " ends");
		
		return result;
	}
}
