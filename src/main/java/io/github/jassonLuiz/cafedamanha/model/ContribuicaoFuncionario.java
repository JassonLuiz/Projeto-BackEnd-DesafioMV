package io.github.jassonLuiz.cafedamanha.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.Column;

import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContribuicaoFuncionario {

    private Long id;
    private String nome;
    
    @CPF(message = "CPF inválido")
    private String cpf;
    private String item;
    private LocalDate dataCafe;

}
