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

create sequence seen_report_pk_sequence
    as integer;

create sequence animal_picture_pk_sequence
    as integer;

create sequence type_pk_sequence
    as integer;

create sequence animal_color_pk_sequence
    as integer;

create sequence role_pk_sequence
    as integer;

create table if not exists type
(
    id   integer default nextval('type_pk_sequence'::regclass) not null,
    name varchar                                               not null,
    constraint type_pk
        primary key (id)
);

create unique index if not exists type_name_uindex
    on type (name);

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

create table if not exists animal_color
(
    id   integer default nextval('animal_color_pk_sequence'::regclass) not null,
    name varchar                                                       not null,
    constraint animal_color_pk
        primary key (id)
);

create unique index if not exists animal_color_name_uindex
    on animal_color (name);

create table if not exists report_status
(
    id   integer default nextval('report_status_pk_sequence'::regclass) not null,
    name varchar                                                        not null,
    constraint report_status_pk
        primary key (id)
);

create unique index if not exists report_status_name_uindex
    on report_status (name);

create table if not exists role
(
    id   integer default nextval('role_pk_sequence'::regclass) not null,
    name varchar                                               not null,
    constraint role_pk
        primary key (id)
);

create unique index if not exists role_name_uindex
    on role (name);

create table if not exists "user"
(
    id           integer default nextval('user_pk_sequence'::regclass) not null,
    phone_number varchar,
    name         varchar,
    last_name    varchar,
    email        varchar                                               not null,
    password     varchar                                               not null,
    role_id      integer                                               not null,
    constraint user_pk
        primary key (id),
    constraint user_role_null_fk
        foreign key (role_id) references role
);

create unique index if not exists user_phone_number_uindex
    on "user" (phone_number);

create unique index if not exists user_email_uindex
    on "user" (email);

create table if not exists animal
(
    id              integer default nextval('animal_pk_sequence'::regclass) not null,
    user_id         integer,
    animal_color_id integer                                                 not null,
    breed_id        integer                                                 not null,
    name            varchar,
    chip            varchar,
    sex             varchar default 'NIEZNANA'::character varying           not null,
    constraint animal_pk
        primary key (id),
    constraint animal_user_null_fk
        foreign key (user_id) references "user"
            on update cascade on delete cascade,
    constraint animal_animal_color_null_fk
        foreign key (animal_color_id) references animal_color,
    constraint animal_breed_null_fk
        foreign key (breed_id) references breed
            on update cascade on delete cascade
);

create unique index if not exists animal_chip_uindex
    on animal (chip);

alter table animal
    add constraint check_name
        check ((sex)::text = ANY (ARRAY [('SAMIEC'::character varying)::text, ('SAMICA'::character varying)::text, ('NIEZNANA'::character varying)::text]));


create table if not exists animal_picture
(
    id           integer default nextval('animal_picture_pk_sequence'::regclass) not null,
    animal_id    integer                                                         not null,
    content_type varchar                                                         not null,
    url          varchar                                                         not null,
    constraint picture_pk
        primary key (id),
    constraint picture_animal_null_fk
        foreign key (animal_id) references animal
            on delete cascade
);

create table if not exists lost_report
(
    id               integer default nextval('lost_report_pk_sequence'::regclass) not null,
    animal_id        integer                                                      not null,
    report_status_id integer                                                      not null,
    date             timestamp(0)                                                 not null,
    description      varchar,
    geo_x            real                                                         not null,
    geo_y            real                                                         not null,
    constraint lost_report_pk
        primary key (id),
    constraint lost_report_animal_null_fk
        foreign key (animal_id) references animal
            on update cascade on delete cascade,
    constraint lost_report_report_status_null_fk
        foreign key (report_status_id) references report_status
);

alter table lost_report
    add constraint valid_coordinates_check
        check (((geo_x >= ('-90'::integer)::double precision) AND (geo_x <= (90)::double precision)) AND
               ((geo_y >= ('-180'::integer)::double precision) AND (geo_y <= (180)::double precision)));

create table if not exists seen_report
(
    id          integer default nextval('seen_report_pk_sequence'::regclass) not null,
    animal_id   integer                                                      not null,
    user_id     integer                                                      not null,
    date        timestamp(0)                                                 not null,
    description varchar,
    geo_x       real                                                         not null,
    geo_y       real                                                         not null,
    constraint seen_report_pk
        primary key (id),
    constraint seen_report_animal_null_fk
        foreign key (animal_id) references animal
            on update cascade on delete cascade,
    constraint seen_report_user_null_fk
        foreign key (user_id) references "user"
);

alter table seen_report
    add constraint valid_coordinates_check
        check (((geo_x >= ('-90'::integer)::double precision) AND (geo_x <= (90)::double precision)) AND
               ((geo_y >= ('-180'::integer)::double precision) AND (geo_y <= (180)::double precision)));



-- ------------------------------------------------------------------
--                            PROCEDURES
-- ------------------------------------------------------------------

-- use this procedure to seed database
create or replace procedure seed_db()
language plpgsql
as $$
begin
    TRUNCATE animal, animal_color, breed, role, lost_report, animal_picture,
    report_status, seen_report, type, "user" CASCADE;

    ALTER SEQUENCE animal_color_pk_sequence RESTART;
    ALTER SEQUENCE animal_pk_sequence RESTART;
    ALTER SEQUENCE breed_pk_sequence RESTART;
    ALTER SEQUENCE lost_report_pk_sequence RESTART;
    ALTER SEQUENCE animal_picture_pk_sequence RESTART;
    ALTER SEQUENCE report_status_pk_sequence RESTART;
    ALTER SEQUENCE seen_report_pk_sequence RESTART;
    ALTER SEQUENCE type_pk_sequence RESTART;
    ALTER SEQUENCE user_pk_sequence RESTART;
    ALTER SEQUENCE role_pk_sequence RESTART;

    INSERT INTO role(name)
    VALUES ('KLIENT');

    INSERT INTO role(name)
    VALUES ('ADMIN');

    INSERT INTO type (name)
    VALUES ('Pies');

    INSERT INTO breed (name, type_id)
    VALUES ('Husky', 1);

    INSERT INTO animal_color (name)
    VALUES ('Szary');

    INSERT INTO "user" (phone_number, name, last_name, email, password, role_id)
    VALUES ('123456789', 'Jakub', 'Grabowski', 'email@em.pl', 'pass', 1);

    INSERT INTO animal (user_id, animal_color_id, breed_id, name, chip, sex)
    VALUES (1, 1, 1, 'Grzegorz', '111111', 'SAMIEC');

    INSERT INTO report_status (name)
    VALUES ('ZAGUBIONY');

    INSERT INTO report_status (name)
    VALUES ('ODNALEZIONY');

    INSERT INTO lost_report (animal_id, report_status_id, date, description, geo_x, geo_y)
    VALUES (1, 1, now(), 'zaginął karakal grzegorz', 90, 180);

end $$;


-- ------------------------------------------------------------------
--                          SEED DATABASE
-- ------------------------------------------------------------------
CALL seed_db();
