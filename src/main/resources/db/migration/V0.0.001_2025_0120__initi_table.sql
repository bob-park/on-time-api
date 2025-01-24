-- positions
create table positions
(
    id                 bigserial               not null primary key,
    name               varchar(50)             not null,
    description        text,
    created_date       timestamp default now() not null,
    created_by         varchar(50)             not null,
    last_modified_date timestamp,
    last_modified_by   varchar(50)
);

-- users_positions
create table users_positions
(
    id             bigserial   not null primary key,
    user_unique_id varchar(41) not null,
    position_id    bigint      not null,

    foreign key (position_id) references positions (id)
);

-- teams
create table teams
(
    id                 bigserial               not null primary key,
    name               varchar(50)             not null,
    description        text,
    is_deleted         bool      default false not null,
    created_date       timestamp default now() not null,
    created_by         varchar(50)             not null,
    last_modified_date timestamp,
    last_modified_by   varchar(50)
);

-- teams_users
create table teams_users
(
    id             bigserial          not null primary key,
    user_unique_id varchar(41)        not null,
    team_id        bigint             not null,
    is_leader      bool default false not null,

    foreign key (team_id) references teams (id)
);

-- attendances_checks
create table attendances_checks
(
    id                 varchar(41)             not null primary key,
    type               varchar(20)             not null,
    attendance_type    varchar(20)             not null,
    working_date       date                    not null,
    expired_date       timestamp               not null,
    created_date       timestamp default now() not null,
    created_by         varchar(50)             not null,
    last_modified_date timestamp,
    last_modified_by   varchar(50)
);

-- attendances_records
create table attendances_records
(
);