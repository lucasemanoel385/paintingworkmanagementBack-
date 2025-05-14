create table Employee(

    id bigint not null auto_increment,
	name_employee varchar(255) not null,
	role_id bigint not null,

	primary key(id),
	constraint fk_role_id foreign key(role_id) references role(id)


);