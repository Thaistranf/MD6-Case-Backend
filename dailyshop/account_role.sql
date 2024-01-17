create table dailyshop.account_role
(
    account_id bigint not null,
    role_id    bigint not null,
    primary key (account_id, role_id),
    constraint FKrc88gtgqrd4vd4k7dhd06y8d1
        foreign key (account_id) references dailyshop.account_table (id),
    constraint FKrs2s3m3039h0xt8d5yhwbuyam
        foreign key (role_id) references dailyshop.role (id)
);

