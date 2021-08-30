package org.tlabs.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Aspect
@Component
public class PMALoggingAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(PMALoggingAspect.class);

	@Pointcut("execution(* org.tlabs.pma.controller.*.*(..))")
	public void logBeforeControllerMethods() {
	}

	@Pointcut("execution(* org.tlabs.pma.service.*.*(..))")
	public void logBeforeServiceMethods() {
	}

	@Before("logBeforeControllerMethods() || logBeforeServiceMethods()")
	public void beforeContollerMethod(JoinPoint joinPoint) {
		LOGGER.info("{}.{}()", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		for (Object arg : joinPoint.getArgs()) {
			if (!(arg instanceof Model)) {
				LOGGER.info("Entity: {}", arg.toString());
			}
		}
	}

	
	@Around("@annotation(org.tlabs.pma.logging.LogTime)")
	public Object logExecutionTime(ProceedingJoinPoint pjp) throws Throwable {
		Long startTime = System.currentTimeMillis();
		Object result = pjp.proceed();
		Long endTime = System.currentTimeMillis();
		Signature signature = pjp.getSignature();
		LOGGER.info("Execution Time Of Method: {}.{} ====> {} ms",signature.getDeclaringTypeName(), signature.getName(), endTime - startTime);
		return result;
	}

}
