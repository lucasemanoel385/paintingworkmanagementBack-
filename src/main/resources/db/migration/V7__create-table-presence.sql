create table Presence(

    id bigint not null auto_increment,
	day_presence date not null,
	presence_daily varchar(30) not null,
	work_id bigint not null,
	employee_id bigint not null,

	primary key(id),
	constraint fk_work_presence_id foreign key(work_id) references work(id) ON DELETE CASCADE,
	constraint fk_employee_presence_id foreign key(employee_id) references employee(id) ON DELETE CASCADE


);