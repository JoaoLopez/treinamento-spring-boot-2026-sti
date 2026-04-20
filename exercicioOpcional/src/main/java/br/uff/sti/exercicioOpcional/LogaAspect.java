package br.uff.sti.exercicioOpcional;

import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

@Aspect
@Component
@Order(2)
public class LogaAspect {
    @Around("@annotation(Loga)")
    public Object logaInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String timestamp = LocalDateTime.now().toString();

        long start = System.currentTimeMillis();        
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        writeLog(className, methodName, timestamp, String.valueOf(executionTime));


        return proceed;
    }
    
    private void writeLog(String className, String methodName, String timestamp, String executionTime) {
        try (BufferedWriter writer = ArquivoContext.current();) {
            writer.append(className)
                  .append(",")
                  .append(methodName)
                  .append(",")
                  .append(timestamp)
                  .append(",")
                  .append(executionTime + "ms")
                  .append("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
