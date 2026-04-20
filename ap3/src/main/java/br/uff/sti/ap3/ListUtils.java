package br.uff.sti.ap3;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ListUtils {
    public static <T> T escolherAleatorio(List<T> lista) {
        if (lista == null || lista.isEmpty()) {
            return null; // ou throw exception
        }
        int index = ThreadLocalRandom.current().nextInt(lista.size());
        return lista.get(index);
    }
}
