package com.centime.assignment.log;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.logging.LogLevel;



@Component
@Aspect
public class LoggingAspect {
	private  Logger logger = org.slf4j.LoggerFactory.getLogger(LoggingAspect.class);

	//	//@Before ("@annotation(LogMethodParam)")
	//	   public void preAuthorize(JoinPoint joinPoint, LogMethodParam logMethod) {
	//	      Object[] args = joinPoint.getArgs();
	//	      ExpressionParser elParser = new SpelExpressionParser();
	//	      Expression expression = elParser.parseExpression(logMethod.log());
	//	      String logStmt = (String) expression.getValue(args);
	//	      
	//	      logger.info(logStmt);
	//	    }


	@Around(value = "@annotation(com.centime.assignment.log.LogMethodParam)")
	public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		Method method = signature.getMethod();
		LogMethodParam loggableMethod = method.getAnnotation(LogMethodParam.class);

		//	LogMethodParam loggableClass = proceedingJoinPoint.getTarget().getClass().getAnnotation(LogMethodParam.class);

		//get current log level
		LogLevel logLevel = loggableMethod.value();

		String star = "**********";
		//before

		Logger logger = LoggerFactory.getLogger(proceedingJoinPoint.getTarget().getClass());

		logger.debug(star + method.getName() + "() start execution" + star);

		if (proceedingJoinPoint.getArgs() != null && proceedingJoinPoint.getArgs().length > 0) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < proceedingJoinPoint.getArgs().length; i++) {
				sb.append(method.getParameterTypes()[i].getName() + ":" + proceedingJoinPoint.getArgs()[i]);
				if (i < proceedingJoinPoint.getArgs().length - 1)
					sb.append(", ");
			}

			logger.debug( method.getName() + "() args " + sb);


		}
	}



}
