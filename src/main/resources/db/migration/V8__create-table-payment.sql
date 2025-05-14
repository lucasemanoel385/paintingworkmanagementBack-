create table Payment(

    id bigint not null auto_increment,
	description_payment varchar(255),
	value_pay decimal(10,2) not null,
	start_date date,
	final_date date,
	employee_id bigint not null,
	work_id bigint not null,

	primary key(id),
	constraint fk_employee_payment_id foreign key(employee_id) references employee(id) ON DELETE CASCADE,
	constraint fk_payment_work_id foreign key(work_id) references work(id) ON DELETE CASCADE


);