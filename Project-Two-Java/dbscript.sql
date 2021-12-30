USER_TYPE Table
create table user_type (id int , user_titel varchar(40));
Alter table user_type add primary key (id);

USERS Table
create table users (id int generated always as identity, name varchar(40) , password varchar(40) , email varchar(150) , usertype_id int ,request_id int, addres varchar(200), contact varchar(40)); 
Alter table users add primary key (id);
Alter table users add foreign key (usertype_id) references user_type(id);
Alter table users add foreign key (request_id) references request(id);

REQUEST Table
create table request (id int generated always as identity, req_name varchar(40), req_price int, req_description varchar(200), req_flag boolean, reject boolean,req_date date, accept_date date, img varchar(200), users_id int);
Alter table request add primary key (id);
ALTER TABLE request ALTER COLUMN req_flag SET DEFAULT FALSE;
ALTER TABLE request ALTER COLUMN reject SET DEFAULT FALSE;

Alter table request add foreign key (users_id) references users(id);


insert into user_type values(1,'employee');
insert into user_type values(2,'manager');

INSERT INTO users ( name,  password, email,  usertype_id) VALUES ('sam','123', 'sameh@gmail.com', 1 );
INSERT INTO users ( name,  password, email,  usertype_id) VALUES ('joe','456', 'joe@gmail.com', 2 );

File Upload Table
CREATE TABLE public.files (iod varchar NOT NULL, content_type varchar(255) NULL, "data" oid NULL,"name" varchar(255) NULL,"size" int8 NULL,id int4 NOT NULL,CONSTRAINT files_pk PRIMARY KEY (iod));
ALTER TABLE public.files ADD CONSTRAINT files_fk FOREIGN KEY (id) REFERENCES public.request(id);
