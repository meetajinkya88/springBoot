package com.centime.assignment.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.slf4j.Logger;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

@Component
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogMethodParam {

	LogLevel value() default LogLevel.DEBUG;

	String log() default "";
	
}
