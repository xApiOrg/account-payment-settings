drop table T_ACCOUNT if exists;

create table T_ACCOUNT (ID bigint identity primary key, NUMBER varchar(9),
                        NAME varchar(50) not null, BALANCE decimal(8,2), unique(NUMBER));
                        
ALTER TABLE T_ACCOUNT ALTER COLUMN BALANCE SET DEFAULT 0.0;

drop table country if exists;
create table country (
	ID bigint identity primary key, 
	NAME varchar(60) not null unique,
	CODE varchar(2) not null unique,
	FLAG varchar(2) not null unique, 
	CURRENCY varchar(3) not null unique);
	
/*
CREATE TABLE `country` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `NAME` varchar(60) NOT NULL,
  `CODE` varchar(2) NOT NULL,
  `FLAG` varchar(2) NOT NULL,
  `CURRENCY` varchar(3) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `NAME_UNIQUE` (`NAME`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `CODE_UNIQUE` (`CODE`),
  UNIQUE KEY `FLAG_UNIQUE` (`FLAG`),
  UNIQUE KEY `CURRENCY_UNIQUE` (`CURRENCY`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 
*/	


drop table section if exists;
create table section (ID bigint identity primary key, NAME varchar(255) not null, 
	VALUE varchar(255), 
	unique (NAME, VALUE));
	
/*
CREATE TABLE `ferdinand`.`section` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(255) NOT NULL,
  `VALUE` VARCHAR(255) NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `ID_UNIQUE` (`ID` ASC),
  UNIQUE INDEX `NAME_VALUE_UNIQUE` (`NAME` ASC, `VALUE` ASC)); 
 * */	

drop table sections if exists;
create table sections (NAME varchar(255) not null, 
	ACCOUNT_ID bigint not null, 
	SECTION_ID bigint not null, 
	foreign key (ACCOUNT_ID) references public.country(ID),
	foreign key (SECTION_ID) references public.section(ID),
);


drop table payment if exists;
CREATE TABLE payment (
  ID bigint(20) unsigned not null auto_increment,
  USER_ID bigint(20) NOT NULL,
  ACCOUNT_ID bigint(20) NOT NULL,
  PAYEE_ID bigint(20) NOT NULL,
  CREATED datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  
  AMOUNT double NOT NULL DEFAULT 0.00,
  PAYMENT_CURRENCY varchar(3) NOT NULL,
  RATE double NOT NULL DEFAULT 1.00,
  CHARGE double   NOT NULL DEFAULT 0.00,  
  CALCULATED_AMOUNT double NOT NULL DEFAULT 0.00,
  PAYEE_CCURRENCY varchar(3) NOT NULL,
  
  PLACED bit(1) NOT NULL DEFAULT 0,
  DATE_PLACED datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  
  CANCELED bit(1) NOT NULL DEFAULT 0,
  DATE_CANCELED datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  
  SETTLED bit(1) NOT NULL DEFAULT 0,
  DATE_SETTLED datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1
/*	CURRENT_TIMESTAMP	*/