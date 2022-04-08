package io.github.jassonLuiz.cafedamanha.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.jassonLuiz.cafedamanha.ex.ErroValidacao;
import io.github.jassonLuiz.cafedamanha.model.ContribuicaoFuncionario;
import io.github.jassonLuiz.cafedamanha.service.ContribuicaoFuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/ContribuicaoFuncionario")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContribuicaoFuncionarioController {
	
	private final ContribuicaoFuncionarioService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ContribuicaoFuncionario salvar(@Valid @RequestBody ContribuicaoFuncionario contribuicao) {
		try {
			return service.salvar(contribuicao);
		} catch (ErroValidacao e) {
			 throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
	
	@GetMapping
	public List<ContribuicaoFuncionario> listarTodos(){
		return service.listarTodos();
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long id) {
		service.deletar(id);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizar( @PathVariable Long id, @Valid @RequestBody ContribuicaoFuncionario contribuicao) {
		try {
			service.atualizar(contribuicao, id);
		} catch (ErroValidacao e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
