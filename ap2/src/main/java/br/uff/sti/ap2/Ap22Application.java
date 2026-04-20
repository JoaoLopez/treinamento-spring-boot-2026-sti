package br.uff.sti.ap2;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Ap22Application {

	public static void main(String[] args) {
		try(var context = SpringApplication.run(Ap22Application.class, args)){

			//Inicializando os componentes
			var fileReaderComponent = context.getBean(FileReaderComponent.class);
			var fileWriterComponent = context.getBean(FileWriterComponent.class);
			var parserComponent = context.getBean(ParserComponent.class);

			try {
				String json = fileReaderComponent.read("example_input.json");
				String csv = parserComponent.jsonToCsv(json);
				fileWriterComponent.write("output.csv", csv);
			} catch (IOException e) {
				System.out.println("Erro ao manipular arquivos: ");
				e.printStackTrace();
			}
		}
	}

}
