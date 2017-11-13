drop table user if exists;
CREATE TABLE user (
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  NAME varchar(255) NOT NULL,
  PRIMARY KEY (ID)
);	

drop table currency if exists;
create table currency (
	ISO varchar(3) not null primary key, 
	NAME varchar(60) not null unique,
	SYMBOL varchar(3) 
);

drop table country if exists;
create table country (
	NAME varchar(60) not null unique,
	CODE varchar(2) not null primary key,
	FLAG varchar(2) not null unique, 
	CURRENCY varchar(3) not null unique,
	FOREIGN KEY (CURRENCY) REFERENCES currency (ISO)
);

drop table bank if exists;
create table bank (
	ID bigint NOT NULL identity primary key,
    BBAN VARCHAR(255) NOT NULL,
    BBAN_CHECK_DIGITS TINYINT,
    BANK_IDENTIFIER VARCHAR(255) NOT NULL,
    SEPA_MEMBER BOOLEAN NOT NULL,
    SWIFT_BIC VARCHAR(255) NOT NULL,
    COUNTRY_CODE VARCHAR(2),
    BANK_ID BIGINT REFERENCES bank (ID),
	FOREIGN KEY (COUNTRY_CODE) REFERENCES country (CODE),
  	UNIQUE( ID, BANK_ID)
);

drop table account_details if exists;
CREATE TABLE account_details (
    ID bigint NOT NULL identity primary key,
    ACCOUNT_NUMBER VARCHAR(255) NOT NULL,
    BANK_ID BIGINT NOT NULL,
    BRANCH_ID BIGINT NOT NULL,
  	CREATED datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (ID),
  	FOREIGN KEY (BANK_ID) REFERENCES bank (ID),
  	FOREIGN KEY (BRANCH_ID) REFERENCES bank (ID),
  	UNIQUE( ACCOUNT_NUMBER, BRANCH_ID) /*,
    ACCOUNT_ID BIGINT,
	PAYEE_ID BIGINT,
  	FOREIGN KEY (ACCOUNT_ID) REFERENCES account (ID),
  	FOREIGN KEY (PAYEE_ID) REFERENCES payee (ID) */
);
	
drop table account if exists;
CREATE TABLE account(
  ID bigint NOT NULL identity primary key,
  USER_ID bigint NOT NULL,
  ACCOUNT_DETAILS_ID bigint NOT NULL,
  CREATED datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CURRENCY varchar(3) NOT NULL,
  BALANCE decimal(8,2) NOT NULL DEFAULT 0.0,
  OVERDRAFT decimal(8,2) NOT NULL DEFAULT 0.0,
  TYPE varchar(255) NOT NULL DEFAULT 'CURRENT',
  PRIMARY KEY (ID),
  FOREIGN KEY (USER_ID) REFERENCES user (ID),
  FOREIGN KEY (ACCOUNT_DETAILS_ID) REFERENCES account_details (ID),
  UNIQUE( USER_ID, CURRENCY, TYPE),
  UNIQUE(ACCOUNT_DETAILS_ID)
);

drop table payee if exists;
CREATE TABLE payee (
  ID bigint(20) NOT NULL AUTO_INCREMENT,
  NAME varchar(255) UNIQUE NOT NULL,  
  ACTIVE bit(1) NOT NULL DEFAULT 1,
  ACCOUNT_DETAILS_ID bigint NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (ACCOUNT_DETAILS_ID) REFERENCES account_details (ID),
  UNIQUE(ACCOUNT_DETAILS_ID)
);
ALTER TABLE payee ALTER ACTIVE bit(1) NOT NULL DEFAULT 1;


drop table user_payee if exists;
CREATE TABLE user_payee (
  USER_ID bigint(20) NOT NULL,
  PAYEE_ID bigint(20) NOT NULL,
  PRIMARY KEY ( USER_ID, PAYEE_ID),
  FOREIGN KEY ( USER_ID ) REFERENCES user ( ID ),
  FOREIGN KEY ( PAYEE_ID ) REFERENCES payee ( ID )
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
  PAYEE_CURRENCY varchar(3) NOT NULL,
  
  PAYMENT_DATE datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
  PLACED bit(1) NOT NULL DEFAULT 0,
  DATE_PLACED datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  
  CANCELLED bit(1) NOT NULL DEFAULT 0,
  DATE_CANCELLED datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  
  SETTLED bit(1) NOT NULL DEFAULT 0,
  DATE_SETTLED datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  DATE_CALCULATED datetime NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY ( ID ),
  FOREIGN KEY ( USER_ID ) REFERENCES user ( ID ),
  FOREIGN KEY ( PAYEE_ID ) REFERENCES payee ( ID ),
  FOREIGN KEY ( ACCOUNT_ID ) REFERENCES account ( ID )
);

INSERT INTO currency (ISO, NAME, SYMBOL) VALUES ('AUD', 'Australian Dollar', '$'), ('BGN', 'Bulgarian Lev', 'bgn'), ('BRL', 'Brazilian Real', 'R$')
, ('CAD', 'Canadian Dollar', '$'), ('CHF', 'Swiss Franc', 'CHF'), ('CNY', 'Chinese Yuan', '¥')
, ('CZK', 'Czech Republic Koruna', 'Kč'), ('DKK', 'Danish Krone', 'kr'), ('GBP', 'British Pound', '£')
, ('HKD', 'Hong Kong Dollar', '$'), ('HRK', 'Croatioan Kuna', 'kn'), ('HUF', 'Hungarian Forint', 'Ft')
, ('IDR', 'Indonesian Rupiah', 'Rp'), ('ILS', 'Israeli Shekel', '₪'), ('INR', 'Indian Rupee', 'Rp')
, ('JPY', 'Japanese Yen', '¥'), ('KRW', '(South) Korean Won', '₩'), ('MXN', 'Mexican Peso', '$')
, ('MYR', 'Malaysian Ringgit', 'RM'), ('NOK', 'Norwegian Kroner', 'kr'), ('NZD', 'New Zealand Dollar', '$')
, ('PHP', 'Philippine Peso', 'Php'), ('PLN', 'Polish Zloty', 'zł'), ('RON', 'Romanian Lei', 'lei')
, ('RUB', 'Russian Ruble', 'руб'), ('SEK', 'Swedish Krona', 'kr'), ('SGD', 'Singapore Dollar', '$')
, ('THB', 'Thai Baht', '฿'), ('TRY', 'Turkish Lira', 'TL'), ('USD', 'US Dollar', '$')
, ('ZAR', 'South African Rand', 'R'), ('EUR', 'EURO', '€');
;

insert into COUNTRY (NAME, CODE, FLAG, CURRENCY) values ('Australia', 'AU', 'au', 'AUD'), ('Brazil', 'BR', 'br', 'BRL'), ('Bulgaria', 'BG', 'bg', 'BGN')
, ('Canada', 'CA', 'ca', 'CAD'), ('China', 'CN', 'cn', 'CNY'), ('Croatia', 'HR', 'hr', 'HRK')
, ('Czech Republic', 'CZ', 'cz', 'CZK'), ('Denmark', 'DK', 'dk', 'DKK'), ('Hong Kong', 'HK', 'hk', 'HKD')
, ('Hungary', 'HU', 'hu', 'HUF'), ('Indonesia', 'ID', 'id', 'IDR'), ('Israel', 'IL', 'il', 'ILS')
, ('Japan', 'JP', 'jp', 'JPY'), ('Malaysia', 'MY', 'my', 'MYR'), ('Mexico', 'MX', 'mx', 'MXN')
, ('Norway', 'NO', 'no', 'NOK'), ('New Zealand', 'NZ', 'nz', 'NZD'), ('Philippines', 'PH', 'ph', 'PHP')
, ('Poland', 'PL', 'pl', 'PLN'), ('Romania', 'RO', 'ro', 'RON'), ('Russia', 'RU', 'ru', 'RUB')
, ('Singapore', 'SG', 'sg', 'SGD'), ('South Africa', 'ZA', 'zr', 'ZAR'), ('South Korea', 'KR', 'kr', 'KRW')
, ('Spain', 'ES', 'es', 'EUR'), ('Sweden', 'SE', 'se', 'SEK'), ('Switzerland', 'CH', 'ch', 'CHF')
, ('Thailand', 'TH', 'th', 'THB'), ('Turkey', 'TR', 'tr', 'TRY'), ('United Kingdom', 'GB', 'gb', 'GBP')
, ('United States', 'US', 'us', 'USD'), ('India', 'IN', 'in', 'INR');

insert into BANK (BBAN, SWIFT_BIC, BBAN_CHECK_DIGITS, BANK_IDENTIFIER, SEPA_MEMBER, COUNTRY_CODE ) values
 ('CITI 9250 440C 9TEI AI', 'CITI', '44', 'CITI', 1, 'BG') ,('RZBB 9155 4027 1608 04', 'RZBB', '40', 'RZBB', 1, 'BG')
,('0076 7000 S521 7090 4', '00767', null, '00767', 1, 'CH') ,('BARC 2004 1568 0883 06', 'BARC', null, 'BARC', 1, 'GB')
,('LOYD 3094 6610 4015 60', 'LOYD', null, 'LOYD', 1, 'GB')
;

insert into account_details(ACCOUNT_NUMBER, BANK_ID, BRANCH_ID) values
  ('111', 1, 1), ('211', 1, 1), ('311', 1, 1), ('411', 1, 1), ('511', 1, 1), ('611', 1, 1), ('711', 1, 1), ('811', 1, 1), ('911', 1, 1)
, ('122', 2, 2), ('222', 2, 2), ('322', 2, 2), ('422', 2, 2), ('522', 2, 2), ('622', 2, 2), ('722', 2, 2), ('822', 2, 2), ('922', 2, 2)
, ('133', 3, 3), ('233', 3, 3), ('333', 3, 3), ('433', 3, 3), ('533', 3, 3), ('633', 3, 3), ('733', 3, 3), ('833', 3, 3), ('933', 3, 3)
, ('144', 4, 4), ('244', 4, 4), ('344', 4, 4), ('444', 4, 4), ('544', 4, 4), ('644', 4, 4), ('744', 4, 4), ('844', 4, 4), ('944', 4, 4)
, ('155', 5, 5), ('255', 5, 5), ('355', 5, 5), ('455', 5, 5), ('555', 5, 5), ('655', 5, 5), ('755', 5, 5), ('855', 5, 5), ('955', 5, 5)
, ('154', 5, 4), ('254', 5, 4), ('354', 5, 4), ('454', 5, 4), ('545', 5, 4), ('546', 5, 4), ('754', 5, 4), ('854', 5, 4), ('954', 5, 4)
;

insert into user (NAME) values ('Keri Lee');
insert into user (NAME) values ('Dollie R. Schnidt');
insert into user (NAME) values ('Cornelia J. LeClerc');
insert into user (NAME) values ('Cynthia Rau');
insert into user (NAME) values ('Douglas R. Cobbs');
insert into user (NAME) values ('Michael Patel');
insert into user (NAME) values ('Suzanne Wong');
insert into user (NAME) values ('Ivan C. Jaya');
insert into user (NAME) values ('Ida Ketterer');
insert into user (NAME) values ('Laina Ochoa Lucero');
insert into user (NAME) values ('Wesley M. Montana');
insert into user (NAME) values ('Leslie F. McCleary');
insert into user (NAME) values ('Sayeed D. Mudra');
insert into user (NAME) values ('Pietronella J. Domingo');
insert into user (NAME) values ('John S. O''Leary');
insert into user (NAME) values ('Gladys D. Smith');
insert into user (NAME) values ('Sally O. Thygesen');
insert into user (NAME) values ('Rachel Vogt');
insert into user (NAME) values ('Julia DeLong');
insert into user (NAME) values ('Mark T. Rizzoli');
insert into user (NAME) values ('Maria J. Angelo');


insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED, ACCOUNT_DETAILS_ID) values 
  ( 1, 'GBP', 1000, 100, 'CURRENT', current_timestamp, 1), ( 1, 'EUR', 1000, 100, 'CASH', current_timestamp, 2)
, ( 1, 'EUR', 1000, 0, 'ISA', current_timestamp, 3), ( 1, 'RUB', 1000, 0, 'FX', current_timestamp, 4)
, ( 1, 'GBP', 1000, 0, 'ISA', current_timestamp, 5), ( 1, 'USD', 1000, 0, 'CASH', current_timestamp, 6);

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED, ACCOUNT_DETAILS_ID) values 
  ( 2, 'GBP', 2000, 200, 'CURRENT', current_timestamp, 7), ( 2, 'EUR', 2000, 200, 'CASH', current_timestamp, 8)
, ( 2, 'EUR', 2000, 0, 'ISA', current_timestamp, 9), ( 2, 'RUB', 2000, 0, 'FX', current_timestamp, 10)
, ( 2, 'GBP', 2000, 0, 'ISA', current_timestamp, 11), ( 2, 'USD', 2000, 0, 'CASH', current_timestamp, 12);

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED, ACCOUNT_DETAILS_ID) values
  ( 3, 'GBP', 3000, 300, 'CURRENT', current_timestamp, 13), ( 3, 'EUR', 3000, 300, 'CASH', current_timestamp, 14)
, ( 3, 'EUR', 3000, 0, 'ISA', current_timestamp, 15), ( 3, 'RUB', 3000, 0, 'FX', current_timestamp, 16)
, ( 3, 'GBP', 3000, 0, 'ISA', current_timestamp, 17), ( 3, 'USD', 3000, 0, 'CASH', current_timestamp, 18);

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED, ACCOUNT_DETAILS_ID) values
  ( 4, 'GBP', 4000, 400, 'CURRENT', current_timestamp, 19), ( 4, 'EUR', 4000, 400, 'CASH', current_timestamp, 20)
, ( 4, 'EUR', 4000, 0, 'ISA', current_timestamp, 21), ( 4, 'RUB', 4000, 0, 'FX', current_timestamp, 22)
, ( 4, 'GBP', 4000, 0, 'ISA', current_timestamp, 23), ( 4, 'USD', 4000, 0, 'CASH', current_timestamp, 24);

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED, ACCOUNT_DETAILS_ID) values
  ( 5, 'GBP', 5000, 500, 'CURRENT', current_timestamp, 25);


insert into payee (NAME, ACCOUNT_DETAILS_ID) values
  ('Ferdinand Burgazov', 26), ('Manuel Ferry', 27), ('Duno Nuque', 28), ('Maria Merry', 29)
, ('Gustav Haraldson', 30), ('Hans Zimmerman', 31), ('Jeff Redcheeck', 32), ('Robert DeBurg', 33)
, ('Ivan Ivanov', 34), ('Peter Petrov', 35), ('Ganyo Balkanski', 36), ('Hasan Kassas', 37)
, ('Redjeb Mutlu', 38), ('Vladimir Esenin', 39), ('Alexandra Kostenko', 40), ('Ilya Grechko', 41)
, ('Efrem Kossigin', 42), ('Petar Hitrev', 43), ('Alil Chaush', 44), ('Osama Osman', 45), ('Yurii Izotzadze', 46)
;

insert into user_payee (user_id, payee_id) values
 ( 1, 1 ),( 1, 2 ),( 1, 3 ),( 1, 4 ),( 1, 5 ),( 1, 6 ),( 1, 7 )
,( 2, 8 ),( 2, 9 ),( 2, 10 ),( 2, 11 ),( 2, 12 ),( 2, 13 ),( 2, 14 )
,( 3, 15 ),( 3, 16 ),( 3, 17 ),( 3, 18 ),( 3, 19 ),( 3, 20 ),( 3, 21 )
,( 4, 2 ),( 4, 3 ),( 4, 4 ),( 4, 5 ),( 4, 6 ),( 4, 7 ),( 4, 8 );


insert into payment (USER_ID, ACCOUNT_ID, PAYEE_ID, AMOUNT, PAYMENT_CURRENCY, PAYEE_CURRENCY) 
			values ( 1, 1, 3, 2000.00, 'GBP', 'EUR' );
			
insert into payment (USER_ID, ACCOUNT_ID, PAYEE_ID, AMOUNT, PAYMENT_CURRENCY, PAYEE_CURRENCY, PAYMENT_DATE) 
			values ( 1, 2, 2, 100.00, 'EUR', 'EUR', '2067-03-10' );
			
update payment set placed = 1 where id = 1;
/* */