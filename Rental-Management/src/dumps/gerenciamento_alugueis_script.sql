use gerenciamento_alugueis;

select * from gerenciamento_alugueis.pessoa;
select * from gerenciamento_alugueis.inquilino;
select * from gerenciamento_alugueis.proprietario;
select * from gerenciamento_alugueis.telefone_pessoa;

select * from gerenciamento_alugueis.imovel;
select * from gerenciamento_alugueis.imovel_comercial;
select * from gerenciamento_alugueis.imovel_residencial;

delete from gerenciamento_alugueis.pessoa
where idpessoa > '0';
delete from gerenciamento_alugueis.inquilino
where idinquilino > '0';
delete from gerenciamento_alugueis.proprietario
where idproprietario > '0';
delete from gerenciamento_alugueis.telefone_pessoa
where idtelefone_pessoa > '0';

delete from gerenciamento_alugueis.imovel
where idimovel > '0';
delete from gerenciamento_alugueis.imovel_comercial
where idimovel > '0';
delete from gerenciamento_alugueis.imovel_residencial
where idimovel > '0';

alter table gerenciamento_alugueis.pessoa auto_increment =1;
alter table gerenciamento_alugueis.inquilino auto_increment =1;
alter table gerenciamento_alugueis.proprietario auto_increment =1;
alter table gerenciamento_alugueis.telefone_pessoa auto_increment =1;

alter table gerenciamento_alugueis.imovel auto_increment =1;
alter table gerenciamento_alugueis.imovel_comercial auto_increment =1;
alter table gerenciamento_alugueis.imovel_residencial auto_increment =1;

alter table inquilino
modify column cpf varchar(14) after nome;

alter table proprietario
modify column cpf varchar(14) after nome;

INSERT INTO `gerenciamento_alugueis`.`pessoa` 
(`nome`, `cpf`, `email`, `saldo`, `cargo`) 
VALUES ('GURU', '147.258.369-40', 'guru@gmail.com', '90000', 'TENANT');

INSERT INTO `gerenciamento_alugueis`.`inquilino` 
(`nome`, `cpf`, `email`, `saldo`) 
VALUES ('GURU', '147.258.369-40', 'guru@gmail.com', '90000');

INSERT INTO `gerenciamento_alugueis`.`proprietario` 
(`nome`, `cpf`, `email`) 
VALUES ('GURU', '147.258.369-40', 'guru@gmail.com');

INSERT INTO `gerenciamento_alugueis`.`telefone_pessoa` 
(`idpessoa`, `primeiro_telefone`, `segundo_telefone`) 
VALUES ('1', '(81) 98765-4321', '(81) 99965-4321');

INSERT INTO `gerenciamento_alugueis`.`imovel` 
(`endereco`, `valor_aluguel`, `tipo`, `status`,`numero_quartos`,`cpf_proprietario`, `idinquilino`) 
VALUES ('', 9000, '', '', 2, '', 2);
