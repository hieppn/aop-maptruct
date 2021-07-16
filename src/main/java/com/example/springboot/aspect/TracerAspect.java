package com.example.springboot.aspect;

import com.example.springboot.model.Level;
import com.example.springboot.model.Logger;
import com.example.springboot.model.Operation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order
@Component
public class TracerAspect extends BaseAspect {

    @Around(value = "execution(* com.example.springboot.controller.*.*(..))")
    public Object processTx(ProceedingJoinPoint joinPoint) throws java.lang.Throwable {

        logger.log("INFO", getClassName(joinPoint), "@Around for " + getMethod(joinPoint) + " Interceptor Call : " + joinPoint.getSignature());
        mongoOperations.insert(Logger.class).one(getLogger(joinPoint).setOperation(Operation.INTERCEPTION_START).build());

        final long start = System.currentTimeMillis();

        Object proceed;

        try {
            proceed = joinPoint.proceed();
        } finally {
            updateStats(getClassName(joinPoint), getMethod(joinPoint).getName(), (System.currentTimeMillis() - start));
        }

        final long executionTime = System.currentTimeMillis() - start;

        logger.log("INFO", getClassName(joinPoint), "@Around for " + getMethod(joinPoint) + " Interceptor Called : "	+ joinPoint.getSignature() + " executed in " + executionTime + "ms");
        mongoOperations.insert(Logger.class).one(getLogger(joinPoint).setOperation(Operation.INTERCEPTION_END).setExecutionTime(executionTime).build());

        return proceed;
    }

    @Before("execution(* com.example.springboot.controller.*.*(..))")
    public void beforeSampleCreation(JoinPoint joinPoint) {

        logger.log("INFO", getClassName(joinPoint), "@Before for " + getMethod(joinPoint) + " Method Invoked: "+ getMethodName(joinPoint));
        mongoOperations.insert(Logger.class).one(getLogger(joinPoint).build());

        //TODO: stream sobre los argumentos
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            logger.log("INFO", getClassName(joinPoint), "@Before for " + getMethod(joinPoint) + " Arguments Passed: " + joinPoint.getArgs()[0]);
            mongoOperations.insert(Logger.class).one(getLogger(joinPoint).setArguments(joinPoint.getArgs()).build());
        }
    }

    @AfterReturning(pointcut = "execution(* com.example.springboot.controller.*.*(..))", returning = "retVal")
    public void logServiceAccess(JoinPoint joinPoint, Object retVal) {
        logger.log("INFO", getClassName(joinPoint), "@AfterReturning for " + getMethod(joinPoint) + "  Completed: " + joinPoint);
        mongoOperations.insert(Logger.class).one(getLogger(joinPoint).setOperation(Operation.COMPLETE).setArguments(joinPoint.getArgs()).build());

        if (retVal != null) {
            logger.log("INFO", getClassName(joinPoint), "@AfterReturning for " + getMethod(joinPoint) + " Returned: " + retVal.toString());
            mongoOperations.insert(Logger.class).one(getLogger(joinPoint).setOperation(Operation.RETURN).setReturn(retVal).build());
        }
    }

    @AfterThrowing(pointcut = "execution(* com.example.springboot.controller.*.*(..))", throwing = "ex")
    public void doRecoveryActions(JoinPoint joinPoint, Exception ex) {

        mongoOperations.insert(Logger.class).one(getLogger(joinPoint, Level.ERROR).setExceptionMessage(ex.getMessage()).build());

        logger.log("ERROR", "After Throwing ", getMethod(joinPoint) + " Method Invoked: "+ joinPoint.getSignature().getName());
        logger.log("ERROR", "Exception Message: ", ex.getMessage());
    }

    //TODO: Separar en un componente??
    <T extends JoinPoint> Logger.Builder getLogger(T t, Level level) {
        return new Logger.Builder(level, getClassName(t), getMethodName(t));
    }

    <T extends JoinPoint> Logger.Builder getLogger(T t) {
        return getLogger(t, Level.INFO);
    }

}