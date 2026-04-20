package br.uff.sti.exercicioOpcional;

import java.io.BufferedWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class ArquivoContext {

    private static final ThreadLocal<Deque<BufferedWriter>> STACK = ThreadLocal.withInitial(ArrayDeque::new);

    public static void push(BufferedWriter writer) {
        STACK.get().push(writer);
    }

    public static BufferedWriter current() {
        return STACK.get().peek();
    }

    public static BufferedWriter pop() {
        return STACK.get().pop();
    }
}