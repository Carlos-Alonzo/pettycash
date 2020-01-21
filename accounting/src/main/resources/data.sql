
create table expenses
(
    id int auto_increment primary key,
    datum timestamp not null,
    amount double not null,
    description varchar(100) not null,
    receivedBy varchar(20) not null,
    authorizedBy varchar(20) not null,
    paidTo varchar(20) not null
);