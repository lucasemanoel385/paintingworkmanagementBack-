create table Expenses(

    id bigint not null auto_increment,
	description_expenses varchar(255),
	value_expenses decimal(10,2),
	date_expense date,
	work_id bigint,

	primary key(id),
	constraint fk_work_id foreign key(work_id) references work(id) ON DELETE CASCADE

);