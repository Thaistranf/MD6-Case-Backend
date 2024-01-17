create table dailyshop.districts
(
    id            bigint auto_increment
        primary key,
    district_name varchar(255) null,
    province_id   bigint       null,
    constraint FK82doq1t64jhly7a546lpvnu2c
        foreign key (province_id) references dailyshop.provinces (id)
);

