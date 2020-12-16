DROP TABLE IF EXISTS ordem_servico_pendencia;
DROP TABLE IF EXISTS ordem_servico;
DROP TABLE IF EXISTS cliente;
DROP TABLE IF EXISTS endereco_cliente;
DROP TABLE IF EXISTS status_ordem;

CREATE TABLE endereco_cliente (
    id int auto_increment primary key,
    rua varchar(300) not null,
    numero int not null
);

CREATE TABLE cliente (
    id int auto_increment primary key,
    nome varchar (255) not null,
    email varchar(150) unique not null,
    endereco_id int not null,
    FOREIGN KEY (endereco_id) references endereco_cliente(id)
);

CREATE TABLE ordem_servico (
    id int auto_increment primary key,
    responsavel varchar(100), -- verificar a possibilidade de criar uma nova tabela
    cliente_id int not null,
    tipo varchar(150) not null, -- criar uma nova tabela?
    marca varchar(150) not null,  -- criar uma nova tabela?
    descricao varchar (500) not null,
    inicio_atendimento date,
    final_atendimento date,
    descricao_resolucao varchar(500),
    status_id int not null,
    FOREIGN KEY (cliente_id) references cliente(id) ON DELETE CASCADE
);

CREATE TABLE status_ordem (
    id int primary key,
    nome varchar(50) not null
);

INSERT INTO status_ordem (id, nome) VALUES (1, 'ABERTO');
INSERT INTO status_ordem (id, nome) VALUES (2, 'EM_ATENDIMENTO');
INSERT INTO status_ordem (id, nome) VALUES(3, 'PENDENTE');
INSERT INTO status_ordem (id, nome) VALUES(4, 'FECHADO');

ALTER TABLE ordem_servico ADD FOREIGN KEY (status_id) REFERENCES status_ordem(id);

CREATE TABLE ordem_servico_pendencia (
    id int auto_increment primary key,
    descricao varchar(500) not null,
    data_inicio date not null,
    data_encerramento date,
    ordem_servico_id int not null,
    FOREIGN KEY (ordem_servico_id) references ordem_servico(id) ON DELETE CASCADE
);