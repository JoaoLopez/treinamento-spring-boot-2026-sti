package br.uff.sti.exercicioOpcional;

import java.io.BufferedWriter;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Service
public class AlunoService {
    @Autowired
    @Lazy
    private AlunoService self; // proxy

    @UsarArquivo("log.csv")
    @Loga
    public void salvarAlunos() throws Exception {

        for (int i = 0; i < 10; i++) {
            self.gravarNota(new Aluno("Aluno" + i, 20 + i));
        }
    }
    
    @UsarArquivo("alunos.csv")
    public void gravarNota(Aluno aluno) throws Exception {
        BufferedWriter writer = ArquivoContext.current();

        if (writer == null) {
            throw new IllegalStateException("Nenhum CSV aberto!");
        }

        writer.write(aluno.getNome() + "," + aluno.getNota());
        writer.newLine();
        writer.flush();
    }
}