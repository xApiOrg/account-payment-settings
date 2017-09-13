
insert into T_ACCOUNT (NUMBER, NAME) values ('123456789', 'Keri Lee');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456001', 'Dollie R. Schnidt');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456002', 'Cornelia J. LeClerc');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456003', 'Cynthia Rau');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456004', 'Douglas R. Cobbs');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456005', 'Michael Patel');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456006', 'Suzanne Wong');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456007', 'Ivan C. Jaya');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456008', 'Ida Ketterer');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456009', 'Laina Ochoa Lucero');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456010', 'Wesley M. Montana');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456011', 'Leslie F. McCleary');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456012', 'Sayeed D. Mudra');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456013', 'Pietronella J. Domingo');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456014', 'John S. O''Leary');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456015', 'Gladys D. Smith');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456016', 'Sally O. Thygesen');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456017', 'Rachel Vogt');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456018', 'Julia DeLong');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456019', 'Mark T. Rizzoli');
insert into T_ACCOUNT (NUMBER, NAME) values ('123456020', 'Maria J. Angelo');


insert into COUNTRY (NAME, CODE, FLAG, CURRENCY) values ('Spain', 'ES', 'es', 'EUR');
insert into COUNTRY (NAME, CODE, FLAG, CURRENCY) values ('United States', 'US', 'us', 'USD');
insert into COUNTRY (NAME, CODE, FLAG, CURRENCY) values ('United Kingdom', 'GB', 'gb', 'GBP');
insert into COUNTRY (NAME, CODE, FLAG, CURRENCY) values ('India', 'IN', 'in', 'INR');
insert into COUNTRY (NAME, CODE, FLAG, CURRENCY) values ('Bulgaria', 'BG', 'bg', 'BGN');
insert into COUNTRY (NAME, CODE, FLAG, CURRENCY) values ('Romania', 'RO', 'ro', 'ROL');

insert into SECTION (NAME, VALUE) values ('title', 'Payee''s full name');
insert into SECTION (NAME, VALUE) values ('type', 'text');
insert into SECTION (NAME, VALUE) values ('maxlength', '30');
insert into SECTION (NAME, VALUE) values ('title', 'Payee''s address');
insert into SECTION (NAME, VALUE) values ('maxlength', '50');
insert into SECTION (NAME, VALUE) values ('mandatory', 'YES');
insert into SECTION (NAME, VALUE) values ('type', 'button');
/* */

insert into payment (USER_ID, ACCOUNT_ID, PAYEE_ID, AMOUNT, PAYMENT_CURRENCY, PAYEE_CCURRENCY) 
			values ( 1000, 10, 100, 2000.00, 'GBP', 'EUR' );


