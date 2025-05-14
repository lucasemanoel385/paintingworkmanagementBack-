create table Work(

    id bigint not null auto_increment,
    name_work varchar(255),
    start_work date,
    final_work date,
    gross_value decimal(10,2),
    liquid_value decimal(10,2),
    city varchar(255),
    street varchar(255),
    district varchar(255),
    number varchar(20),
    cep varchar(50),

	primary key(id)


);