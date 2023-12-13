create table consoles (
    codigo bigint primary key,
    nome varchar(100) not null unique,
    data_lancamento date default current_date,
    empresa varchar(100) not null,
    jogo bigint not null,
    foreign key (jogo) references jogos(codigo) on delete set null
);