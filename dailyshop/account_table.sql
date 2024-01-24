create table dailyshop.account_table
(
    id                bigint auto_increment
        primary key,
    account           varchar(255) not null,
    check_profile     bit          not null,
    confirm_password  varchar(255) not null,
    email             varchar(255) not null,
    enabled           bit          not null,
    password          varchar(255) not null,
    registration_time datetime(6)  null,
    constraint UK_fxobfouyn0ko6r5waj0ngm5m6
        unique (account),
    constraint UK_m9y4fvexgxkbp37olefipgn4q
        unique (email)
);

