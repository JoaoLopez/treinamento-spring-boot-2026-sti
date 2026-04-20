package br.uff.sti.ap2;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogaAspect {
    private static final String FILE_PATH = "log.csv";

    @Around("@annotation(Loga)")
    public Object logaInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        // BEFORE
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String timestamp = LocalDateTime.now().toString();

        writeLog(className, methodName, timestamp);

        return joinPoint.proceed();
    }

    private void writeLog(String className, String methodName, String timestamp) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.append(className)
                  .append(",")
                  .append(methodName)
                  .append(",")
                  .append(timestamp)
                  .append("\n");

        } catch (IOException e) {
            e.printStackTrace(); // simples, pode melhorar depois
        }
    }
}
