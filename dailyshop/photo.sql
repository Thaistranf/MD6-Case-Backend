create table dailyshop.photo
(
    photoid    bigint auto_increment
        primary key,
    photo_name varchar(255) not null,
    product_id bigint       null,
    constraint FK8hs00tlacip0319kutudailre
        foreign key (product_id) references dailyshop.product (productid)
);

