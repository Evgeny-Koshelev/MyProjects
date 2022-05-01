create table tv_contracts(
    id integer not null,
    date_input date not null,
    date_out date not null,
    number_contract text not null,
    client_id integer not null,
    pakege_of_canals text not null,
    primary key(id)
);

create table mobile_contracts(
    id integer not null,
    date_input date not null,
    date_out date not null,
    number_contract text not null,
    client_id integer not null,
    number_of_minutes integer not null,
    number_of_sms integer not null,
    gbs float not null,
    primary key(id)
);

create table internet_contracts(
    id integer not null,
    date_input date not null,
    date_out date not null,
    number_contract text not null,
    client_id integer not null,
    speed integer not null,
    primary key(id)
);

create table persons(
    id integer not null,
    full_name text not null,
    birth_date date not null,
    gender text not null,
    passport text not null,
    primary key(id)
);