create table dailyshop.wards
(
    id          bigint auto_increment
        primary key,
    ward_name   varchar(255) null,
    district_id bigint       null,
    constraint FKfjqt744bo800mb5uax74lav8k
        foreign key (district_id) references dailyshop.districts (id)
);

