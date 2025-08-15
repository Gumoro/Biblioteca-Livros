package br.com.grocha.Literatura;

import br.com.grocha.Literatura.controle.Menu;
import br.com.grocha.Literatura.servico.LivroServico;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class LiteraturaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(LiteraturaApplication.class, args);

		LivroServico livroServico = context.getBean(LivroServico.class);

		Menu menu = new Menu(livroServico);
		menu.iniciar();
	}
}