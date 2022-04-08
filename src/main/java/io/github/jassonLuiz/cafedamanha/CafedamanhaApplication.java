package io.github.jassonLuiz.cafedamanha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import io.github.jassonLuiz.cafedamanha.model.ContribuicaoFuncionario;
import io.github.jassonLuiz.cafedamanha.repository.ContribuicaoFuncionarioRepository;
import io.github.jassonLuiz.cafedamanha.service.ContribuicaoFuncionarioService;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;

@SpringBootApplication
public class CafedamanhaApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext ctx = SpringApplication.run(CafedamanhaApplication.class, args);
		// final ContribuicaoFuncionarioRepository rep =
		// ctx.getBean(ContribuicaoFuncionarioRepository.class);

		// final ContribuicaoFuncionario contribuicao =
		// ContribuicaoFuncionario.builder().item("suco").nome("Dougllas").cpf("03124062310").dataCafe(LocalDate.now()).build();
		// rep.salvar(contribuicao);

		// final ContribuicaoFuncionario contribuicao1 =
		// ContribuicaoFuncionario.builder().item("agua").nome("Junior").cpf("11284368416").dataCafe(LocalDate.now()).build();
		// rep.salvar(contribuicao1);

		// final ContribuicaoFuncionario contribuicao2 =
		// ContribuicaoFuncionario.builder().item("guarana").nome("Felipe").cpf("71178097447").dataCafe(LocalDate.now()).build();
		// rep.salvar(contribuicao2);

		// rep.listarTodos().forEach(System.out::println);

		// final ContribuicaoFuncionarioService service =
		// ctx.getBean(ContribuicaoFuncionarioService.class);
		// service.salvar(contribuicao);
	}

}
