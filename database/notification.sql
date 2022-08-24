create table notification
(
    identification_number        varchar(255) not null
        primary key,
    creation_time                timestamp,
    description                  varchar(255),
    last_modified_time           timestamp,
    parent_identification_number varchar(255),
    title                        varchar(255)
);

alter table notification
    owner to postgres;

