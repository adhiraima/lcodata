# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions
# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table city;

drop table employee;

drop table jv;

drop table lco;

drop table LCOMaster;

drop table state;

SET FOREIGN_KEY_CHECKS=1;


# --- !Ups

create table city (
  city_id                   bigint auto_increment not null,
  state_id                  bigint,
  city_name                 varchar(255),
  constraint pk_city primary key (city_id))
;

create table employee (
  employee_id               varchar(255) not null,
  name                      varchar(255),
  email                     varchar(255),
  constraint uq_employee_email unique (email),
  constraint pk_employee primary key (employee_id))
;

create table jv (
  jv_code                   varchar(255) not null,
  jv_name                   varchar(255),
  state                     bigint,
  city                      bigint,
  constraint pk_jv primary key (jv_code))
;

create table lco (
  lco_code                  varchar(255) not null,
  lco_name                  varchar(255),
  address                   varchar(255),
  contact                   varchar(255),
  phone                     varchar(255),
  jv_code                   varchar(255),
  state                     bigint,
  city                      bigint,
  employee_id               bigint,
  constraint pk_lco primary key (lco_code))
;

create table LCOMaster (
  serial_num                bigint auto_increment not null,
  lco_id                    varchar(255),
  aop_location              varchar(255),
  pin_code                  varchar(255),
  parent_code               varchar(255),
  area                      varchar(255),
  dealer_type               bigint,
  old_aop                   varchar(255),
  agreement                 bigint,
  kycId                     bigint,
  constraint pk_LCOMaster primary key (serial_num))
;

create table state (
  state_id                  bigint auto_increment not null,
  state_name                varchar(255),
  constraint pk_state primary key (state_id))
;


insert into jv (jv_code, jv_name) values ('DBKNP03', 'DEN Ambey Cable Networks Pvt. Ltd.');
insert into jv (jv_code, jv_name) values ('JVBHD001', 'Bhadohi DEN Entertainment Pvt. Ltd.');
insert into jv (jv_code, jv_name) values ('JVKNP01', 'DEN AMBEY');
insert into jv (jv_code, jv_name) values ('JVKNP02', 'DEN Ambey Jhansi Cable Network Pvt. Ltd. -Jhansi');
insert into jv (jv_code, jv_name) values ('JVKNP03', 'DEN Ambey Cable Networks Pvt. Ltd. Etawah');
insert into jv (jv_code, jv_name) values ('JVKNP04', 'Star Channel DEN Network Pvt. Ltd. Banda');
insert into jv (jv_code, jv_name) values ('JVKNP05', 'Seturn Digital Cable PVt. Ltd. Fatehpur');
