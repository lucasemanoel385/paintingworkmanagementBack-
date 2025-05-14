create table employee_presence(

    employee_id bigint,
    presence_id bigint,
    foreign key(employee_id) references employee(id) ON DELETE CASCADE ON UPDATE NO ACTION,
    foreign key(presence_id) references presence(id) ON DELETE CASCADE ON UPDATE NO ACTION);
