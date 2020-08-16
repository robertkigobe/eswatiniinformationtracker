package org.bantwana.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AttendanceLoggingAspect {

	// set up logger
	private Logger myLogger = Logger.getLogger(getClass().getName());

	// set up point cut decarations for controller
	@Pointcut("execution(* org.bantwana.controller.*.*(..))")
	private void forAttendanceControllerPackage() {
	}

	// set up point cut decarations for controller
	@Pointcut("execution(* org.bantwana.service.*.*(..))")
	private void forAttendanceServicePackage() {
	}

	// set up point cut decarations for controller
	@Pointcut("execution(* org.bantwana.dao.*.*(..))")
	private void forAttendanceDAOPackage() {
	}

	// merge pointcuts
	@Pointcut("forAttendanceControllerPackage() || forAttendanceServicePackage() || forAttendanceDAOPackage()")
	private void forAppFlow() {
	}

	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		//display the mothod called
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("=====>> in @Before: calling method: " + theMethod);
		
		// display methods
		//get the arguments
		Object[] args = theJoinPoint.getArgs();
		
		//loop through and display args
		for(Object tempArg: args) {
			myLogger.info("======>> argument: " + tempArg);
			
		}
	}

	// add @AfterReturnign advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		//display the mothod called
				String theMethod = theJoinPoint.getSignature().toShortString();
				myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);
				
		//display data returned
				myLogger.info("=====>> result: " + theResult);
						
		
	}

}
