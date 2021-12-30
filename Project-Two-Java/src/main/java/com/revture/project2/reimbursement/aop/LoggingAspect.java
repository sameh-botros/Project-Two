package com.revture.project2.reimbursement.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

//references
//https://www.baeldung.com/spring-aop
//https://www.baeldung.com/spring-aop-vs-aspectj 
//https://www.techgeeknext.com/spring-boot/spring-boot-logging-aop-example

@Aspect
@Component
public class LoggingAspect {
 private static final Logger LOGGER = LogManager.getLogger(LoggingAspect.class);
 
 // Aspect - is the class that contains the pointcut expressions to match the joinpoint methods
 // Advice - An Advice is an action taken by an aspect at a particular Joinpoint. Different types of advice include “around,” “before,” and “after.”. 
 //			@Around is an advice, other advices that can be used is @Before, @After, @AfterThrowing, @AfterReturning
 // Pointcut - A Pointcut is a predicate that helps match an Advice to be applied by an Aspect at a particular JoinPoint.
 //			  We often associate the Advice with a Pointcut expression, and it runs at any Joinpoint matched by the Pointcut.
 //			  After the advice we specify the Pointcut expression or predicate to match the joinpoints
 // JoinPoints - A Joinpoint is a point during the execution of a program, such as the execution of a method or the handling of an exception.
 
 @Around("execution(* com.revture.project2.reimbursement.service..*(..)))")
 public Object logMethodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
     MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

     final StopWatch stopWatch = new StopWatch();

     //calculate method execution time
     stopWatch.start();
     Object result = proceedingJoinPoint.proceed();
     stopWatch.stop();

     //Log method execution time
     LOGGER.info("BOOKCRUD - Spring Boot Logging AOP EXAMPLE - Execution time of "
             + methodSignature.getDeclaringType().getSimpleName() // Class Name
             + "." + methodSignature.getName() + " " // Method Name
             + ":: " + stopWatch.getTotalTimeMillis() + " ms");

     return result;
 }
}
