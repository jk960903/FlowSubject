
-- 테이블들의 초기 DDL H2 DB mem 모드를 사용하였기 때문에 필수적으로 있어야함

drop table if exists fixed_extension;
drop table if exists custom_extension;

create table fixed_extension(
    idx bigint primary key auto_increment,
    extension_name varchar not null unique,
    ischecked integer not null,
    regdate datetime not null,
    changedate datetime not null
);

create table custom_extension(
    idx bigint primary key auto_increment,
    extension_name varchar(20) not null unique,
    regdate datetime not null,
    changedate datetime not null
)