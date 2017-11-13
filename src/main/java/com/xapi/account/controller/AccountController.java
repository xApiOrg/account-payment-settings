package com.xapi.account.controller;

import java.io.Serializable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xapi.account.service.AccountService;
import com.xapi.data.model.Account;
import com.xapi.data.model.AccountDetails;
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
	/account/validation/{iban}				GET		- payeeAccountValidation		IBAN, IFSC, validation check	http://52.56.203.3:10001/ipay/account/validation/GB48LOYD30963846959260?type=iban
	
	OBSOLETE, See /account/validation/{iban}	GET
	/account/validation							POST	- payeeAccountValidation		IBAN, IFSC, validation check	http://localhost:10001/account

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
		logger.info(accounts != null  && ! accounts.isEmpty() ? accounts.toString(): "NO AccountS FOUND!!!");
		
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
		logger.info(account != null? account.toString(): "Account NOT FOUND!!!");
		
		return new ResponseEntity<Account>(account, account != null? HttpStatus.OK: HttpStatus.NOT_FOUND);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUserPayeeAccounts(@PathVariable("user_id") Long userId){ 
		String info = "\nMetod getAllUserPayeeAccounts( Integer userId) NOT IMPLEMENTED YET" + 
				"\nGet ALL User's PAYEE accounts by user Id" + "\n Parameters, user Id = " + userId;		
		logger.info(info);
		
		List<Payee> accounts = accountService.getUserPayeeAccounts( userId );
		logger.info(accounts != null? accounts.toString(): "NO AccountS FOUND!!!");
		
		return new ResponseEntity<List<Payee>>(accounts, accounts != null && ! accounts.isEmpty()? HttpStatus.OK: HttpStatus.NOT_FOUND);
	}	
	
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}/{payee_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPayeeAccountById(
			@PathVariable("user_id") Long userId, @PathVariable("payee_id") Long payeeId){ 
		String info = "Metod getPayeeAccountById( Integer userId, Integer payeeI) NOT IMPLEMENTED YET" + 
			"\nGet User's PAYEE account by user Id and payee Id" + 
			"\n Parameters, user Id = " + userId + ", payee Id = " + payeeId;		
		logger.info(info);
		
		Payee account = accountService.getPayeeByIdAndUserId( userId, payeeId );
		logger.info(account != null? account.toString(): "Account NOT FOUND!!!");
		
		return new ResponseEntity<Payee>(account, account != null? HttpStatus.OK: HttpStatus.NOT_FOUND);
	}
		
//	Content-Type = text/xml or application/xml 	and 	Body -> raw -> <xml>some xml</xml>
//	Content-Type = text/json 					and 	Body -> raw -> {"some": "jason"}, { "name": "Bai Ganyo" }    
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}", method = RequestMethod.PUT)
	public ResponseEntity<?> createUserPayeeAccount(@RequestBody PayeeDto account, @PathVariable("user_id") Long userId){//, @RequestBody Object account
		String info = "\nMetod createUserPayeeAccount( Integer userId) NOT IMPLEMENTED YET" + 
				"\nCreate User's PAYEE account by user Id" + "\n Parameters, user Id = " + userId + ", parameter account: " + account;
			logger.info(info);
		
		Payee payee = accountService.createNewPayee( (Payee) new Payee( account.getName() ), userId );
			logger.info(payee != null? payee.toString(): "Payee NOT FOUND!!!");
			
		return new ResponseEntity<Payee>(payee, payee != null? HttpStatus.OK: HttpStatus.EXPECTATION_FAILED);
	}
	
//	Content-Type = text/xml or application/xml 	and 	Body -> raw -> <xml>some xml</xml>
//	Content-Type = text/json 					and 	Body -> raw -> {"some": "jason"}	
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}/{payee_id}", method = RequestMethod.PATCH)
	public ResponseEntity<?> updateUserPayeeAccount(  @RequestBody Payee payeeDto,
			@PathVariable("user_id") Long userId, @PathVariable("payee_id") Long payeeId){//
		String info = "\nMetod updateUserPayeeAccount( Integer userId, Integer payeeI) NOT IMPLEMENTED YET" + 
			"\nUpdate User's PAYEE account by user Id and payee Id" + 
			"\n Parameters, user Id = " + userId + ", payee Id" + payeeId + ", parameter account " + payeeDto;
			logger.info(info);
		
		Payee payee = accountService.updatePayee( payeeDto, payeeId, userId );
			logger.info(payee  != null? payee.toString(): "Payee NOT FOUND!!!");
			
		return new ResponseEntity<Payee>(payee, payee != null? HttpStatus.OK: HttpStatus.EXPECTATION_FAILED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/payee/{user_id}/{payee_id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUserPayeeAccountById(
			@PathVariable("user_id") Long userId, @PathVariable("payee_id") Long payeeId){
		String info = "\nMetod deleteUserPayeeAccountById( Integer userId, Integer payeeI) NOT IMPLEMENTED YET" + 
			"\nDelete User's PAYEE account by user Id and payee Id" + 
			"\n Parameters, user Id = " + userId + ", payee Id = " + payeeId;
			logger.info(info);
		
		Payee payee = accountService.deletePayee( userId, payeeId);
			logger.info(payee  != null? payee.toString(): "Payee NOT FOUND!!!");
			
		return new ResponseEntity<Payee>(payee, payee != null? HttpStatus.OK: HttpStatus.EXPECTATION_FAILED);
	}
	
	// http://localhost:10001/ipay/account/validation/324377516?type=rtn
	// http://localhost:10001/ipay/account/validation/GB48LOYD30963846959260?type=iban
	// http://localhost:10001/ipay/account/validation/SBIN0000138?type=ifsc
	// http://localhost:10001/ipay/account/validation/842002002?type=micr
	
	@CrossOrigin
//	@RequestMapping(value = "/payee", method = RequestMethod.POST) 
	@RequestMapping(value = "/validation/{iban}", method = RequestMethod.GET)
//	public ResponseEntity<?> payeeAccountValidation( @RequestBody String iBanFscAccountDetails ){
	public ResponseEntity<?> payeeAccountValidation( @PathVariable("iban") String iBanFscAccountDetails, @RequestParam("type") String type){
		String info = // "\nMetod payeeAccountValidation() NOT IMPLEMENTED YET" + 
				"\nAccount User's new PAYEE account validation check: " + iBanFscAccountDetails;		
		logger.info(info);
		
		Object accountDetails = accountService.validateAccount(iBanFscAccountDetails == null? "": iBanFscAccountDetails.replaceAll("\\s+",""), type);
			logger.info(accountDetails != null? accountDetails.toString(): null);
			
		return new ResponseEntity<Object>(accountDetails, accountDetails != null? HttpStatus.OK: HttpStatus.EXPECTATION_FAILED);
	}
}

class PayeeDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public PayeeDto(){}
	public PayeeDto(String name){ this.name = name;}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
