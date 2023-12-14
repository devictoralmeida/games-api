create table if not exists desenvolvedores (
    codigo serial primary key not null,
    nome varchar(100) not null unique,
    data_fundacao date default current_date,
    website varchar(150) default null,
    sede varchar(50) not null
);