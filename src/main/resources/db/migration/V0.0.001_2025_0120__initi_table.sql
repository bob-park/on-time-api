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
    created_date       timestamp default now() not null,
    created_by         varchar(50)             not null,
    last_modified_date timestamp,
    last_modified_by   varchar(50)
);

-- teams_users
create table teams_users
(
    id             bigserial   not null primary key,
    user_unique_id varchar(41) not null,
    team_id        bigint      not null,

    foreign key (team_id) references teams (id)
);