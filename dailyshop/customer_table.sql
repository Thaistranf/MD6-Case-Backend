create table dailyshop.customer_table
(
    id                 bigint auto_increment
        primary key,
    address            varchar(255) null,
    customer_name      varchar(255) null,
    date_of_birth      date         null,
    edit_customer_time datetime(6)  null,
    image_customer     varchar(255) null,
    phone              varchar(255) null,
    account_id         bigint       null,
    constraint FKm3ad6rxdmkhqd5qjfm6bla6ot
        foreign key (account_id) references dailyshop.account_table (id)
);

