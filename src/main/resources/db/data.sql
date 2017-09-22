
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

/* BEGIN MySQL statements*/

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

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 1, 'GBP', 1000, 100, 'CURRENT', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 1, 'EUR', 1000, 100, 'CASH', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 1, 'EUR', 1000, 0, 'ISA', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 1, 'RUB', 1000, 0, 'FX', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 1, 'GBP', 1000, 0, 'ISA', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 1, 'USD', 1000, 0, 'CASH', current_timestamp);

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 2, 'GBP', 2000, 200, 'CURRENT', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 2, 'EUR', 2000, 200, 'CASH', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 2, 'EUR', 2000, 0, 'ISA', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 2, 'RUB', 2000, 0, 'FX', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 2, 'GBP', 2000, 0, 'ISA', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 2, 'USD', 2000, 0, 'CASH', current_timestamp);

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 3, 'GBP', 3000, 300, 'CURRENT', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 3, 'EUR', 3000, 300, 'CASH', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 3, 'EUR', 3000, 0, 'ISA', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 3, 'RUB', 3000, 0, 'FX', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 3, 'GBP', 3000, 0, 'ISA', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 3, 'USD', 3000, 0, 'CASH', current_timestamp);

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 4, 'GBP', 4000, 400, 'CURRENT', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 4, 'EUR', 4000, 400, 'CASH', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 4, 'EUR', 4000, 0, 'ISA', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 4, 'RUB', 4000, 0, 'FX', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 4, 'GBP', 4000, 0, 'ISA', current_timestamp);
insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 4, 'USD', 4000, 0, 'CASH', current_timestamp);

insert into account (USER_ID, CURRENCY, BALANCE, OVERDRAFT, TYPE, CREATED) values ( 5, 'GBP', 5000, 500, 'CURRENT', current_timestamp);


insert into payee (NAME) values ('Ferdinand Burgazov');
insert into payee (NAME) values ('Manuel Ferran');
insert into payee (NAME) values ('Nuno Duque');
insert into payee (NAME) values ('Maria Merino');
insert into payee (NAME) values ('Gustav Haraldson');
insert into payee (NAME) values ('Hans Zimmerman');
insert into payee (NAME) values ('Jeff Redcheeck');
insert into payee (NAME) values ('Robert DeBurg');
insert into payee (NAME) values ('Ivan Ivanov');
insert into payee (NAME) values ('Peter Petrov');
insert into payee (NAME) values ('Ganyo Balkanski');
insert into payee (NAME) values ('Hasan Kassas');
insert into payee (NAME) values ('Redjeb Mutlu');
insert into payee (NAME) values ('Vladimir Esenin');
insert into payee (NAME) values ('Alexandra Kostenko');
insert into payee (NAME) values ('Ilya Grechko');
insert into payee (NAME) values ('Efrem Kossigin');
insert into payee (NAME) values ('Petar Hitrev');
insert into payee (NAME) values ('Alil Chaush');
insert into payee (NAME) values ('Osama Osman');
insert into payee (NAME) values ('Yurii Izotzadze');

insert into user_payee (user_id, payee_id) values( 1, 1 );
insert into user_payee (user_id, payee_id) values( 1, 2 );
insert into user_payee (user_id, payee_id) values( 1, 3 );
insert into user_payee (user_id, payee_id) values( 1, 4 );
insert into user_payee (user_id, payee_id) values( 1, 5 );
insert into user_payee (user_id, payee_id) values( 1, 6 );
insert into user_payee (user_id, payee_id) values( 1, 7 );

insert into user_payee (user_id, payee_id) values( 2, 8 );
insert into user_payee (user_id, payee_id) values( 2, 9 );
insert into user_payee (user_id, payee_id) values( 2, 10 );
insert into user_payee (user_id, payee_id) values( 2, 11 );
insert into user_payee (user_id, payee_id) values( 2, 12 );
insert into user_payee (user_id, payee_id) values( 2, 13 );
insert into user_payee (user_id, payee_id) values( 2, 14 );

insert into user_payee (user_id, payee_id) values( 3, 15 );
insert into user_payee (user_id, payee_id) values( 3, 16 );
insert into user_payee (user_id, payee_id) values( 3, 17 );
insert into user_payee (user_id, payee_id) values( 3, 18 );
insert into user_payee (user_id, payee_id) values( 3, 19 );
insert into user_payee (user_id, payee_id) values( 3, 20 );
insert into user_payee (user_id, payee_id) values( 3, 21 );

insert into user_payee (user_id, payee_id) values( 4, 2 );
insert into user_payee (user_id, payee_id) values( 4, 3 );
insert into user_payee (user_id, payee_id) values( 4, 4 );
insert into user_payee (user_id, payee_id) values( 4, 5 );
insert into user_payee (user_id, payee_id) values( 4, 6 );
insert into user_payee (user_id, payee_id) values( 4, 7 );
insert into user_payee (user_id, payee_id) values( 4, 8 );

/*minimal insert statement. N.B. AMOUNT and CALCULATED_AMOUNT can be omitted but it's useful to have at least one of them*/
insert into payment (USER_ID, ACCOUNT_ID, PAYEE_ID, AMOUNT, PAYMENT_CURRENCY, PAYEE_CCURRENCY) 
			values ( 1000, 10, 100, 2000.00, 'GBP', 'EUR' );
			
/* END MySQL statements*/

