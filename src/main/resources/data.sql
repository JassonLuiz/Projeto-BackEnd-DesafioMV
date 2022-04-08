create table contribuicaofuncionario(
  id bigint auto_increment not null primary key,
  nome varchar(100) not null,
  cpf varchar(14) not null,
  item varchar(100) not null,
  data_cafe date
);