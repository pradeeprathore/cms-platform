package com.ascent.cms.services.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Logging
{
	public Logging()
	{
		System.out.println("constructor called.");
	}

	/**
	 * Following is the definition for a pointcut to select all the methods
	 * available. So advice will be called for all the methods.
	 */
	@Pointcut("execution(* com.ascent.*.*(..))")
	private void selectAll()
	{
		System.out.println("select all called.");
	}

	/**
	 * This is the method which I would like to execute before a selected method
	 * execution.
	 */
	@Before("selectAll()")
	public void beforeAdvice()
	{
		System.out.println("Going to setup student profile.");
	}

	@Before("execution(public * *.something(..))")
	public void anyPublic()
	{
		System.out.println("HIT POINTCUT");
	}
}
