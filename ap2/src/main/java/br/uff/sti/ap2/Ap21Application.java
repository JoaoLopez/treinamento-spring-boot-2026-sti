package br.uff.sti.ap2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class Ap21Application {

	public static void main(String[] args) {
//		var alo2 =  new AloMundoComponent();
//		alo2.aloMundo("sem spring");
		try(var context = SpringApplication.run(Ap21Application.class, args)){
			var aloMundoComponent = context.getBean(AloMundoComponent.class);
			aloMundoComponent.aloMundo("jupiter");
		}
	}

}
