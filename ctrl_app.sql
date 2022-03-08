DROP DATABASE IF EXISTS under_ctrl_app;

Create database under_ctrl_app;
Use under_ctrl_app;

Create table users (
	id int primary key auto_increment,
    email varchar(255) not null,
    password text not null,
    birthday date,
    firstname varchar(255) not null,
    lastname varchar(255) not null,
    deleted bool default false,
    createdAt datetime,
    updatedAt datetime
);

Create table roles (
	id int primary key auto_increment,
    name varchar(255) not null
);

Create table user_has_role (
	user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (role_id) references roles(id) on delete cascade
);

Create table connections (
    connect_from int not null,
    connect_to int not null,
    foreign key (connect_from) references users(id) on delete cascade,
    foreign key (connect_to) references users(id) on delete cascade
);

 Create table currencies (
    id int primary key auto_increment,
    name varchar(50) not null,
    iso varchar(3) not null
);

Create table accounts (
	id int primary key auto_increment,
    name varchar(255) not null,
    currency_id int not null,
    owner_id int not null,
    deleted bool default false,
	createdAt datetime,
    updatedAt datetime,
    foreign key (currency_id) references currencies(id),
    foreign key (owner_id) references users(id) on delete cascade
);

Create table collaborators (
    user_id int not null,
    account_id int not null,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (account_id) references accounts(id) on delete cascade
);

Create table records_types (
	id int primary key auto_increment,
    name varchar(255) not null
);

Create table records_categories (
	id int primary key auto_increment,
    name varchar(255) not null,
    deleted bool default false
);

Create table records (
	id int primary key auto_increment,
    record_type_id int not null,
    record_category_id int default null,
    account_id int not null,
    amount decimal(6,2) not null,
    deleted bool default false,
	createdAt datetime,
    updatedAt datetime,
    foreign key (record_category_id) references records_categories(id),
    foreign key (record_type_id) references records_types(id),
    foreign key (account_id) references accounts(id) on delete cascade
);

Insert into roles VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');
Insert into currencies VALUES (1, 'Dollar', 'USD'), (2, 'Peruvian Sol', 'PEN');
Insert into records_categories(name) VALUES ('Food'), ('Shopping'), ('Housing'), ('Transportation'), ('Income'), ('Health'), ('Other');
Insert into records_types (name) VALUES ('Income'), ('Expense');