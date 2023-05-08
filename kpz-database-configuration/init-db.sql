create sequence animal_pk_sequence
    as integer;

create sequence lost_report_pk_sequence
    as integer;

create sequence user_pk_sequence
    as integer;

create sequence report_status_pk_sequence
    as integer;

create sequence breed_pk_sequence
    as integer;

create sequence coordinates_pk_sequence
    as integer;

create sequence seen_report_pk_sequence
    as integer;

create sequence picture_pk_sequence
    as integer;

create sequence type_pk_sequence
    as integer;

create sequence animal_color_pk_sequence
    as integer;

create table if not exists type
(
    id   integer default nextval('type_pk_sequence'::regclass) not null,
    name varchar                                               not null,
    constraint type_pk
        primary key (id)
);

create table if not exists breed
(
    id      integer default nextval('breed_pk_sequence'::regclass) not null,
    type_id integer                                                not null,
    name    varchar                                                not null,
    constraint breed_pk
        primary key (id),
    constraint breed_type_fk
        foreign key (type_id) references type
            on update cascade on delete cascade
);

create unique index if not exists breed_name_uindex
    on breed (name);

create unique index if not exists type_name_uindex
    on type (name);

create table if not exists animal_color
(
    id   integer default nextval('animal_color_pk_sequence'::regclass) not null,
    name varchar                                                       not null,
    constraint animal_color_pk
        primary key (id)
);

create unique index if not exists animal_color_name_uindex
    on animal_color (name);

create table if not exists "user"
(
    id           integer default nextval('user_pk_sequence'::regclass) not null,
    phone_number varchar                                               not null,
    name         varchar,
    last_name    varchar,
    email        varchar,
    password     varchar                                               not null,
    constraint user_pk
        primary key (id)
);

create unique index if not exists user_phone_number_uindex
    on "user" (phone_number);

create table if not exists animal
(
    id              integer default nextval('animal_pk_sequence'::regclass) not null,
    user_id         integer,
    animal_color_id integer                                                 not null,
    breed_id        integer                                                 not null,
    name            varchar                                                 not null,
    chip            varchar,
    constraint animal_pk
        primary key (id),
    constraint animal_user_null_fk
        foreign key (user_id) references "user"
            on update cascade on delete cascade,
    constraint animal_animal_color_null_fk
        foreign key (animal_color_id) references animal_color,
    constraint animal_breed_null_fk
        foreign key (breed_id) references breed
);

create unique index if not exists animal_chip_uindex
    on animal (chip);

create table if not exists picture
(
    id          integer default nextval('picture_pk_sequence'::regclass) not null,
    animal_id   integer                                                  not null,
    image_bytes oid                                                      not null,
    constraint picture_pk
        primary key (id),
    constraint picture_animal_null_fk
        foreign key (animal_id) references animal
            on delete cascade
);

create table if not exists report_status
(
    id   integer default nextval('report_status_pk_sequence'::regclass) not null,
    name varchar                                                        not null,
    constraint report_status_pk
        primary key (id)
);

create unique index if not exists report_status_name_uindex
    on report_status (name);

create table if not exists coordinates
(
    id integer default nextval('coordinates_pk_sequence'::regclass) not null,
    x  real                                                         not null,
    y  real                                                         not null,
    constraint coordinates_pk
        primary key (id)
);

create table if not exists lost_report
(
    id               integer default nextval('lost_report_pk_sequence'::regclass) not null,
    animal_id        integer                                                      not null,
    report_status_id integer                                                      not null,
    coordinates_id   integer                                                      not null,
    date             timestamp(0)                                                 not null,
    description      varchar,
    constraint lost_report_pk
        primary key (id),
    constraint lost_report_animal_null_fk
        foreign key (animal_id) references animal
            on update cascade on delete cascade,
    constraint lost_report_report_status_null_fk
        foreign key (report_status_id) references report_status,
    constraint lost_report_coordinates_null_fk
        foreign key (coordinates_id) references coordinates
);

create unique index if not exists coordinates_x_y_uindex
    on coordinates (x, y);

create table if not exists seen_report
(
    id             integer default nextval('seen_report_pk_sequence'::regclass) not null,
    animal_id      integer                                                      not null,
    user_id        integer                                                      not null,
    coordinates_id integer                                                      not null,
    date           timestamp(0)                                                 not null,
    description    varchar,
    constraint seen_report_pk
        primary key (id),
    constraint seen_report_animal_null_fk
        foreign key (animal_id) references animal
            on update cascade on delete cascade,
    constraint seen_report_user_null_fk
        foreign key (user_id) references "user",
    constraint seen_report_coordinates_null_fk
        foreign key (coordinates_id) references coordinates
);

-- ------------------------------------------------------------------
--                          SEED DATABASE
-- ------------------------------------------------------------------

TRUNCATE animal, animal_color, breed, coordinates, lost_report, picture,
    report_status, seen_report, type, "user" CASCADE;

ALTER SEQUENCE animal_color_pk_sequence RESTART;
ALTER SEQUENCE animal_pk_sequence RESTART;
ALTER SEQUENCE breed_pk_sequence RESTART;
ALTER SEQUENCE coordinates_pk_sequence RESTART;
ALTER SEQUENCE lost_report_pk_sequence RESTART;
ALTER SEQUENCE picture_pk_sequence RESTART;
ALTER SEQUENCE report_status_pk_sequence RESTART;
ALTER SEQUENCE seen_report_pk_sequence RESTART;
ALTER SEQUENCE type_pk_sequence RESTART;
ALTER SEQUENCE user_pk_sequence RESTART;

INSERT INTO type (name)
VALUES ('pies');

INSERT INTO breed (name, type_id)
VALUES ('husky', 1);

INSERT INTO animal_color (name)
VALUES ('szary');

INSERT INTO "user" (phone_number, name, last_name, email, password)
VALUES ('123456789', 'Jakub', 'Grabowski', 'email@em.pl', 'pass');

INSERT INTO animal (user_id, animal_color_id, breed_id, name, chip)
VALUES (1, 1, 1, 'Grzegorz', '111111');

INSERT INTO report_status (name)
VALUES ('ZAGUBIONY');

INSERT INTO coordinates(x, y)
VALUES (122.22, 133.33);

INSERT INTO lost_report (animal_id, report_status_id, coordinates_id, date, description)
VALUES (1, 1, 1, now(), 'zaginął karakal grzegorz');