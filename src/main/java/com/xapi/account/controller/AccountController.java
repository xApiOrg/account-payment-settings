package com.xapi.account.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xapi.account.service.AccountService;
import com.xapi.data.model.Account;
import com.xapi.data.model.Payee;

@RestController
@RequestMapping("/account")
public class AccountController {
	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired AccountService accountService;
/**
 * 
	/account/{user_id}						GET		- getAllUserPaymentAccounts 	All user payment accounts		http://localhost:10001/account/100
	/account/{user_id}/{account_id}			GET		- getUserAccountById			User's account details			http://localhost:10001/account/100/200
	/account/payee/{user_id}				GET		- getAllUserPayeeAccounts		All user payees					http://localhost:10001/account/payee/100
	/account/payee/{user_id}/{payee_id}		GET		- getPayeeAccountById			Payee account details			http://localhost:10001/account/payee/100/10
	/account/payee/{user_id}				PUT		- createUserPayeeAccount		Create new payee account		http://localhost:10001/account/payee/100
	/account/payee/{user_id}/{payee_id}		PATCH	- updateUserPayeeAccount		Update payee account			http://localhost:10001/account/payee/100/10
	/account/payee/{user_id}/{payee_id}		DELETE	- deleteUserPayeeAccountById	Delete payee account			http://localhost:10001/account/payee/100/10
	/account								POST	- payeeAccountValidation		IBAN, IFSC, validation check	http://localhost:10001/account

	See for logging
	https://stackoverflow.com/questions/33744875/spring-boot-how-to-log-all-requests-and-responses-with-exceptions-in-single-pl

 */
	
	@CrossOrigin(origins="*")
	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUserPaymentAccounts(@PathVariable("user_id") Long userId){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = //"\nMetod getAllUserPaymentAccounts( Integer userId) NOT IMPLEMENTED YET" + 
				"\nGet ALL User's PAYMENT accounts by user Id" + "\n Parameters, user Id = " + userId;		
		logger.info(info);
		
		List<Account> accounts = accountService.getAllPayableAccounts(userId);// .getAll( userId );
		logger.info(accounts != null? accounts.toString(): null);
		
		return new ResponseEntity<List<Account>>(accounts, accounts != null && ! accounts.isEmpty()?  HttpStatus.OK: HttpStatus.NOT_FOUND);
	}	
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}/{account_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserAccountById(
			@PathVariable("user_id") Long userId, @PathVariable("account_id") Long accountId){ //ResponseEntity<Collection<PaymentAccounts>>

		String info = //"\nMetod getUserAccountById( Integer userId, Integer accountId) NOT IMPLEMENTED YET" + 
			"\nGet User's PAYMENT account by user Id and account Id" + 
			"\n Parameters, user Id = " + userId + ", account Id = " + accountId;		
		logger.info(info);
		
		// getAccountById(accountId) - should return same result if accountId is unique across all system
		Account account = accountService.getUserAccountById(userId, accountId);
		logger.info(account != null? account.toString(): null);
		
		return new ResponseEntity<Account>(account, account != null? HttpStatus.OK: HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserPayeeAccounts(@PathVariable("user_id") Long userId){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = //"\nMetod getAllUserPayeeAccounts( Integer userId) NOT IMPLEMENTED YET" + 
				"\nGet ALL User's PAYEE accounts by user Id" + "\n Parameters, user Id = " + userId;		
		logger.info(info);
		
		List<Payee> accounts = accountService.getUserPayeeAccounts( userId );
		logger.info(accounts != null? accounts.toString(): null);
		
		return new ResponseEntity<List<Payee>>(accounts, accounts != null && ! accounts.isEmpty()? HttpStatus.OK: HttpStatus.NOT_FOUND);
	}	
	
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}/{payee_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPayeeAccountById(
			@PathVariable("user_id") Long userId, @PathVariable("payee_id") Long payeeId){ //ResponseEntity<Collection<PaymentAccounts>>

		String info = "Metod getPayeeAccountById( Integer userId, Integer payeeI) NOT IMPLEMENTED YET" + 
			"\nGet User's PAYEE account by user Id and payee Id" + 
			"\n Parameters, user Id = " + userId + ", payee Id" + payeeId;
		
		logger.info(info);
		
		Collection<Account> accounts = accountService.getAll( userId );// FIXME, accountService.getPayees(user_id).getAccount(payee_id); or accountService.getPayee(userId, payee_id)
		return new ResponseEntity<String>(info, HttpStatus.I_AM_A_TEAPOT);
	}
		
//	Content-Type = text/xml or application/xml 	and 	Body -> raw -> <xml>some xml</xml>
//	Content-Type = text/json 					and 	Body -> raw -> {"some": "jason"}
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}", method = RequestMethod.PUT)
	public ResponseEntity<?> createUserPayeeAccount(@RequestBody Object account, @PathVariable("user_id") Long userId){//, @RequestBody Object account
		String info = "Metod createUserPayeeAccount( Integer userId) NOT IMPLEMENTED YET" + 
				"\nCreate User's PAYEE account by user Id" + "\n Parameters, user Id = " + userId + ", parameter account " + account;
		
		logger.info(info);
		
		Collection<Account> accounts = accountService.getAll( userId );// FIXME, accountService.createPayeeAccount(userId, account)
		return new ResponseEntity<String>(info, HttpStatus.I_AM_A_TEAPOT);
	}
	
//	Content-Type = text/xml or application/xml 	and 	Body -> raw -> <xml>some xml</xml>
//	Content-Type = text/json 					and 	Body -> raw -> {"some": "jason"}	
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}/{payee_id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateUserPayeeAccount(  @RequestBody Object account,
			@PathVariable("user_id") Long userId, @PathVariable("payee_id") Long payeeId){//
		String info = "Metod updateUserPayeeAccount( Integer userId, Integer payeeI) NOT IMPLEMENTED YET" + 
			"\nUpdate User's PAYEE account by user Id and payee Id" + 
			"\n Parameters, user Id = " + userId + ", payee Id" + payeeId + ", parameter account " + account;
		
		logger.info(info);
		
		Collection<?> accounts = accountService.getAll( userId );// FIXME, accountService.updatePayeeAccount(userId, account)
		return new ResponseEntity<String>(info, HttpStatus.I_AM_A_TEAPOT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}/{payee_id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserPayeeAccountById(
			@PathVariable("user_id") Long userId, @PathVariable("payee_id") Long payeeId){
		String info = "Metod deleteUserPayeeAccountById( Integer userId, Integer payeeI) NOT IMPLEMENTED YET" + 
			"\nDelete User's PAYEE account by user Id and payee Id" + 
			"\n Parameters, user Id = " + userId + ", payee Id" + payeeId;
		
		logger.info(info);
		
		Collection<Account> accounts = accountService.getAll( userId );// FIXME, accountService.delete(userId, payee_id)
		return new ResponseEntity<String>(info, HttpStatus.I_AM_A_TEAPOT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/payee", method = RequestMethod.POST)
	public ResponseEntity<?> payeeAccountValidation( @RequestBody String iBanFscAccountDetails ){ // @RequestBody String iBaniFscAccountDetails
		String info = "Metod payeeAccountValidation() NOT IMPLEMENTED YET" + 
				"\nAccount User's new PAYEE account validation check: " + iBanFscAccountDetails;
		
		logger.info(info);
		
//		Account accounts = accountService.accountValidate( ); // FIXME Account account = accountService.validate(iBanFscAccountDetails)
		return new ResponseEntity<String>(info, HttpStatus.I_AM_A_TEAPOT);
	}
}

/*
{
    "timestamp": 1504795569656,
    "status": 400,
    "error": "Bad Request",
    "exception": "org.springframework.http.converter.HttpMessageNotReadableException",
    "message": "Required request body is missing: public org.springframework.http.ResponseEntity<?> com.xapi.account.controller.AccountController.createUserPayeeAccount(java.lang.String,java.lang.Integer)",
    "path": "/ipay/account/payee/100"
}

to Avoid add in postman or in real request 
Body -> raw -> "account any string" but NOT NOTHING 

{
    "timestamp": 1504796642464,
    "status": 415,
    "error": "Unsupported Media Type",
    "exception": "org.springframework.web.HttpMediaTypeNotSupportedException",
    "message": "Content type 'text/plain;charset=UTF-8' not supported",
    "path": "/ipay/account/payee/100"
}
		Content-Type = text/xml
		<properties>
			<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		</properties>
		
or 		Content-Type = application/json	
		{"some": "jason"}
 * */

/*
{
    "timestamp": 1504785871789,
    "status": 415,
    "error": "Unsupported Media Type",
    "exception": "org.springframework.web.HttpMediaTypeNotSupportedException",
    "message": "Content type 'text/plain;charset=UTF-8' not supported",
    "path": "/ipay/account/payee/100/10"
}

{
    "timestamp": 1504786033592,
    "status": 400,
    "error": "Bad Request",
    "exception": "org.springframework.http.converter.HttpMessageNotReadableException",
    "message": "Required request body is missing: public org.springframework.http.ResponseEntity<?> com.xapi.account.controller.AccountController.updateUserPayeeAccount(java.lang.Integer,java.lang.Integer,java.lang.Object)",
    "path": "/ipay/account/payee/100/10"
}

	Content-Type = text/xml or application/xml 	and 	Body -> raw -> <xml>some xml</xml>
	Content-Type = text/json 					and 	Body -> raw -> {"some": "jason"}

https://stackoverflow.com/questions/19468572/spring-mvc-why-not-able-to-use-requestbody-and-requestparam-together
 * */	
