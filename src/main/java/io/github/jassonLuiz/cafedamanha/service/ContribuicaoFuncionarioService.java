package io.github.jassonLuiz.cafedamanha.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import io.github.jassonLuiz.cafedamanha.ex.ErroValidacao;
import io.github.jassonLuiz.cafedamanha.model.ContribuicaoFuncionario;
import io.github.jassonLuiz.cafedamanha.repository.ContribuicaoFuncionarioRepository;

@Service
public class ContribuicaoFuncionarioService {

    @Autowired
    private ContribuicaoFuncionarioRepository repository;

    public ContribuicaoFuncionario salvar(ContribuicaoFuncionario contribuicaoFuncionario){
    	validar(contribuicaoFuncionario);
    	return repository.salvar(contribuicaoFuncionario);
		
    }

    private void validar(ContribuicaoFuncionario contribuicaoFuncionario) {
        if(repository.existsContribuicao(contribuicaoFuncionario.getDataCafe(), contribuicaoFuncionario.getItem().toLowerCase())){
            throw new ErroValidacao("Já existe para esta data, o mesmo item de café da manhã.");
       }
    }
    
    public List<ContribuicaoFuncionario> listarTodos(){
    	return repository.listarTodos();
    }
    
    public void deletar(Long id) {
       repository.deletar(id);
    }
    
    public ContribuicaoFuncionario atualizar(ContribuicaoFuncionario contribuicaoFuncionario, Long id){
        validar(contribuicaoFuncionario);
        return repository.atualizar(contribuicaoFuncionario, id);
    }
    
}
