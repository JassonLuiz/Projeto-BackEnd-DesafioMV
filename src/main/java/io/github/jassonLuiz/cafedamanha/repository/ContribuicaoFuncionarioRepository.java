package io.github.jassonLuiz.cafedamanha.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.github.jassonLuiz.cafedamanha.model.ContribuicaoFuncionario;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Repository
public class ContribuicaoFuncionarioRepository {

    public static final String INSERT_STATEMENT = "insert into contribuicaofuncionario (nome, cpf, data_cafe, item) values (?,?,?,?)";
    public static final String SELECT_STATEMENT = "select * from contribuicaofuncionario ";
    public static final String SELECT_CONTRIBUICAO_POR_DATA_ITEM = "select exists (select * from contribuicaofuncionario where item = ? and data_cafe = ?)";
    public static final String DELETE_STATEMENT = "delete from contribuicaofuncionario where id = ?";
    public static final String UPDATE_STATEMENT = "update contribuicaofuncionario set nome = ?, item = ? where id = ?";

    @Autowired
    private DataSource dataSource;

    public ContribuicaoFuncionario salvar(ContribuicaoFuncionario contribuicao){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT_STATEMENT);
        ){
            statement.setString(1, contribuicao.getNome());
            statement.setString(2, contribuicao.getCpf());
            statement.setObject(3, contribuicao.getDataCafe());
            statement.setObject(4, contribuicao.getItem().toLowerCase());

            statement.execute();

            return contribuicao;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro de SQL ao salvar a contribuição.");
        }
    }

    public List<ContribuicaoFuncionario> listarTodos(){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_STATEMENT);
        ){

            final ResultSet rs = statement.executeQuery();
            List<ContribuicaoFuncionario> lista = new ArrayList<>();
            while(rs.next()){
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String item = rs.getString("item");
                Date data = (Date) rs.getObject("data_cafe");
                LocalDate dataCafe = converterDataSQLEMLocalDate(data);

                lista.add(
                    ContribuicaoFuncionario
                        .builder()
                        .nome(nome)
                        .cpf(cpf)
                        .id(id)
                        .item(item)
                        .dataCafe(dataCafe)
                        .build()
                );
            }

            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro de SQL ao listas as contribuições.");
        }
    }
    
    public void deletar(Long id){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(DELETE_STATEMENT);
        ){
            statement.setLong(1, id);
           
            statement.executeUpdate();

           
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro de SQL ao deletar a contribuição.");
        }
    }
    
    public ContribuicaoFuncionario atualizar(ContribuicaoFuncionario contribuicao, Long id){
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(UPDATE_STATEMENT);
        ){
            statement.setString(1, contribuicao.getNome());
            statement.setString(2, contribuicao.getItem());
            statement.setLong(3, id);

            statement.executeUpdate();

            return contribuicao;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro de SQL ao salvar a contribuição.");
        }
    }
    

    private LocalDate converterDataSQLEMLocalDate(Date data) {
        return new java.util.Date(data.getTime()).toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public boolean existsContribuicao(LocalDate dataCafe, String item) {
        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(SELECT_CONTRIBUICAO_POR_DATA_ITEM);
        ){

            statement.setString(1, item);
            statement.setObject(2, dataCafe);

            final ResultSet rs = statement.executeQuery();
            while(rs.next()){
                return rs.getBoolean(1);
            }

            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro de SQL ao listar as contribuições por item e data.");
        }
    }
}
