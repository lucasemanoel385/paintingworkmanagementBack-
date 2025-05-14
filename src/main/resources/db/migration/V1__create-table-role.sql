create table Role(

    id bigint not null auto_increment,
	name_role varchar(255) not null unique,
	monthly_payment decimal(10,2),
	daily_payment decimal(10,2),

	primary key(id)

);