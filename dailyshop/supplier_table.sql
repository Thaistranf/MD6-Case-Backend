create table dailyshop.supplier_table
(
    id                 bigint auto_increment
        primary key,
    address            varchar(255) null,
    contact_name       varchar(255) null,
    edit_supplier_time datetime(6)  null,
    image_supplier     varchar(255) null,
    phone              varchar(255) null,
    start_date         date         null,
    supplier_name      varchar(255) null,
    account_id         bigint       null,
    constraint UK_11rwghk3274q3yoggpncpcd20
        unique (supplier_name),
    constraint FK1ctkwjrgsm5i4qdxkj69sqofo
        foreign key (account_id) references dailyshop.account_table (id)
);

