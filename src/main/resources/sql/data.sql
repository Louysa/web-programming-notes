insert into person(FNAME, LNAME, DATEOFBIRTH) values('Jack', 'Miller', parsedatetime('12-02-2000', 'dd-MM-yyyy'));
insert into person(FNAME, LNAME, DATEOFBIRTH) values('Eric', 'Becker', parsedatetime('19-02-1994', 'dd-MM-yyyy'));
insert into person(FNAME, LNAME, DATEOFBIRTH) values('Stephanie', 'Wiley', parsedatetime('10-04-1986', 'dd-MM-yyyy'));
insert into person(FNAME, LNAME, DATEOFBIRTH) values('Laura', 'Thompson', parsedatetime('06-11-2002', 'dd-MM-yyyy'));
insert into person(FNAME, LNAME, DATEOFBIRTH) values('David', 'Diamond', parsedatetime('03-05-1974', 'dd-MM-yyyy'));
insert into person(FNAME, LNAME, DATEOFBIRTH) values('Alison', 'Fowler', parsedatetime('26-06-1989', 'dd-MM-yyyy'));
insert into person(FNAME, LNAME, DATEOFBIRTH) values('Karen', 'Evans', parsedatetime('01-07-1998', 'dd-MM-yyyy'));

-- Spring Security test users
-- Passwords are BCrypt encoded: 'password' for all users
insert into users(username, password, enabled) values('user', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true);
insert into users(username, password, enabled) values('editor', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true);
insert into users(username, password, enabled) values('admin', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW', true);

-- User roles
insert into authorities(username, authority) values('user', 'ROLE_USER');
insert into authorities(username, authority) values('editor', 'ROLE_EDITOR');
insert into authorities(username, authority) values('admin', 'ROLE_ADMIN');