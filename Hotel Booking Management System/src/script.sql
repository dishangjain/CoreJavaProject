drop table users cascade constraints;
drop table hotels cascade constraints;
drop table roomdetails cascade constraints;
drop table bookingdetails cascade constraints;

create table users(
	user_id varchar(4) primary key,
	password varchar(7), 
	role varchar(10),
	user_name varchar(20),
	mobile_no varchar(10),
	phone varchar(10),
	address varchar(25),
	email varchar(15)
);

create table hotels(
	hotel_id varchar(4) primary key,
	city varchar(10),
	hotel_name varchar(20),
	address varchar(25),
	description varchar(50),
	avg_rate_per_night number(6,2),
	phone_no1 varchar(10),
	phone_no2 varchar(10),
	rating varchar(4),
	email varchar(15),
	fax varchar(15)
);

create table roomdetails(
	room_id varchar(4) primary key,
	hotel_id varchar(4) references hotels(hotel_id),
	room_no varchar(3),
	room_type varchar(20),
	per_night_rate number(6,2),
	availability char,
	photo varchar2(20)
);

create table bookingdetails(
	booking_id varchar(4) primary key,
	room_id varchar(4) references roomdetails(room_id),
	user_id varchar(4) references users(user_id),
	booked_from date,
	booked_to date,
	no_of_adults number(2),
	no_of_children number(2),
	amount number(6,2)
);

drop sequence booking_id_seq;
drop sequence hotel_id_seq;
drop sequence user_id_seq;
drop sequence room_id_seq;

create sequence booking_id_seq;
create sequence hotel_id_seq;
create sequence user_id_seq;
create sequence room_id_seq;