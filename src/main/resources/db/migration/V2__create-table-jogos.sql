create table if not exists jogos (
    codigo serial primary key not null,
    nome varchar(100) not null unique,
    descricao text default null,
    data_lancamento date default current_date,
    website varchar(150) default null,
    desenvolvedor int not null,
    genero varchar(50) not null,
    url_capa varchar(200) default null,
    foreign key (desenvolvedor) references desenvolvedores(codigo) on delete set null
);