create table if not exists consoles (
    codigo serial primary key not null,
    nome varchar(100) not null unique,
    data_lancamento date default current_date,
    empresa varchar(100) not null
);