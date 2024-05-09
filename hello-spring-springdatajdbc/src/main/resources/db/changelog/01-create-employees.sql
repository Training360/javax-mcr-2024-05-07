create table employees (id bigint generated always as identity, emp_name varchar(255), primary key (id));
insert into employees(emp_name) values ('John Doe');
insert into employees(emp_name) values ('Jack Doe');