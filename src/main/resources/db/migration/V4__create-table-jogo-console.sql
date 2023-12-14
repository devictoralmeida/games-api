create table if not exists jogo_console (
    jogo_codigo int references jogos(codigo) on update cascade on delete cascade,
    console_codigo int references consoles(codigo) on update cascade on delete cascade,
    constraint jogo_codigo_pkey primary key (jogo_codigo, console_codigo)
);