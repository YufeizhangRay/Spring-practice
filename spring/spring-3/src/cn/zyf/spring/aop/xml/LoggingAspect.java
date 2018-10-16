package cn.zyf.spring.aop.xml;

import java.util.Arrays;

import javax.naming.spi.DirStateFactory.Result;

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


public class LoggingAspect {
	
	public void beforeMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		Object[] args = joinPoint.getArgs();
		
		System.out.println("The method "+methodName+" begins with "+Arrays.asList(args));
	}
	
	public void afterMethod(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();		
		System.out.println("The method "+methodName+" ends");
	}
	
	public void afterReturning(JoinPoint joinPoint, Object result) {
		String methodName = joinPoint.getSignature().getName();		
		System.out.println("The method "+methodName+" ends with "+result);
	}
	
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		String methodName = joinPoint.getSignature().getName();		
		System.out.println("The method "+methodName+" occurs exception "+e);
	}
	
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
