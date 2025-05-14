create table employee_extrawork(

    employee_id bigint,
    extrawork_id bigint,
    foreign key(employee_id) references employee(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    foreign key(extrawork_id) references extrawork(id) ON DELETE CASCADE ON UPDATE NO ACTION);
