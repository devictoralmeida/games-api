create table desenvolvedores (
    codigo bigint primary key,
    nome varchar(100) not null unique,
    website varchar(150) default null,
    sede varchar(50) not null,
    data_fundacao date default current_date
);