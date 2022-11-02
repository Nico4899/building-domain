create table component
(
    identification_number        varchar(255) not null
        primary key,
    description                  varchar(255),
    parent_identification_number varchar(255),
    type                         integer,
    geographical_location_id     bigint
        constraint fkfxf6t4kl6dao783ql9ev8eqqy
            references geographical_location
);

alter table component
    owner to postgres;

