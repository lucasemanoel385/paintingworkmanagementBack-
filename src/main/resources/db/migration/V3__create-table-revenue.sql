create table Revenue(

    id bigint not null auto_increment,
	description_revenue varchar(255),
	value_entry decimal(10,2),
	date_revenue date,
	work_id bigint not null,

	primary key(id),
	constraint fk_work_revenue_id foreign key(work_id) references work(id) ON DELETE CASCADE

);