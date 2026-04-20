package br.uff.sti.exercicioOpcional;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ap22Application {

	public static void main(String[] args) {
		try(var context = SpringApplication.run(Ap22Application.class, args)){

			//Inicializando os componentes
			var alunoService = context.getBean(AlunoService.class);

			try {
				alunoService.salvarAlunos();
			} catch (Exception e) {
				System.out.println("Erro ao manipular arquivos: ");
				e.printStackTrace();
			}
		}
	}

}
