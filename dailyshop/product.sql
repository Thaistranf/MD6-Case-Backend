create table dailyshop.product
(
    productid      bigint auto_increment
        primary key,
    description    varchar(255) not null,
    is_deleted     bit          not null,
    price          varchar(255) not null,
    product_name   varchar(255) not null,
    stock_quantity varchar(255) not null,
    account_id     bigint       null,
    category_id    bigint       null,
    constraint UK_383i0awxqlq7pc33hil7afbgo
        unique (product_name),
    constraint FK1mtsbur82frn64de7balymq9s
        foreign key (category_id) references dailyshop.category (id),
    constraint FK6gqbmc14vqlu5524qfd0mtme4
        foreign key (account_id) references dailyshop.account_table (id)
);

