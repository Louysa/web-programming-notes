create table person(ID identity primary key, FNAME varchar(50), LNAME varchar(50), DATEOFBIRTH date);

-- Spring Security tables
create table users(
    username varchar(50) not null primary key,
    password varchar(100) not null,
    enabled boolean not null
);

create table authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);