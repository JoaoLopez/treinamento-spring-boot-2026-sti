package br.uff.sti.exercicioOpcional;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UsarArquivo {
    String value();
}