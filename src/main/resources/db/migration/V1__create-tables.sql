CREATE TABLE "desenvolvedores" (
    codigo serial primary key not null,
    nome varchar(100) not null unique,
    data_fundacao date default current_date,
    website varchar(150) default null,
    sede varchar(50) not null
);

CREATE TABLE "jogos" (
    codigo serial primary key not null,
    nome varchar(100) not null unique,
    descricao text default null,
    data_lancamento date default current_date,
    website varchar(150) default null,
    desenvolvedor int not null,
    genero varchar(50) not null,
    url_capa varchar(200) default null,
    foreign key ("desenvolvedor") references "desenvolvedores" ("codigo") on delete
    set
        null
);

CREATE TABLE "consoles" (
    codigo serial primary key not null,
    nome varchar(100) not null unique,
    data_lancamento date default current_date,
    empresa varchar(100) not null
);

CREATE TABLE "jogo_console" (
    jogo_codigo int references jogos (codigo) on update cascade on delete cascade,
    console_codigo int references consoles (codigo) on update cascade on delete cascade,
    constraint jogo_codigo_pkey primary key (jogo_codigo, console_codigo)
);