create table ExtraWork(

    id bigint not null auto_increment,
	description_extra_work varchar(255) not null,
	date_extra_work date,
	payment_extra_work decimal(10,2) not null,
	employee_id bigint not null,
	work_id bigint not null,

	primary key(id),
	constraint fk_employee_id foreign key(employee_id) references employee(id) ON DELETE CASCADE,
	constraint fk_work_extra_work_id foreign key(work_id) references work(id) ON DELETE CASCADE

);