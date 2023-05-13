package tn.esprit.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {
    private  static final Logger logger = LogManager.getLogger(LoggingAspect.class);
    @Around("execution(* tn.esprit.Services.Services.*(..) )")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable
    {
        long start = System.currentTimeMillis();
        String name= pjp.getSignature().getName();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        logger.info("Method "+name + " execution time: " + elapsedTime + " milliseconds.");
        return obj;
    }
}