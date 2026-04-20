package br.uff.sti.exercicioOpcional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;

@Aspect
@Component
@Order(1)
public class UsarArquivoAspect {

    @Around("@annotation(csv)")
    public Object abrirArquivo(ProceedingJoinPoint jp, UsarArquivo csv) throws Throwable {

        BufferedWriter writer = new BufferedWriter(
                new FileWriter(csv.value(), true)
        );

        ArquivoContext.push(writer);

        try {
            return jp.proceed();
        } finally {
            BufferedWriter w = ArquivoContext.pop();
            w.close();
        }
    }
}