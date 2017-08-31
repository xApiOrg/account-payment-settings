package com.xapi.account.controller;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xapi.account.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	public static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired AccountService accountService;
/**
 * 
	/account/{user_id}						GET		- getAllUserPaymentAccounts		http://localhost:8080/account/100
	/account/{user_id}/{account_id}			GET		- getAllUserPayeeAccounts		http://localhost:8080/account/100/200
	/account/payee/{user_id}				GET		- getUserPayeeAccountById		http://localhost:8080/account/payee/100
	/account/payee/{user_id}/{payee_id}		GET		- createUserPayeeAccount		http://localhost:8080/account/payee/100/10
	/account/payee/{user_id}				PUT		- getUserPayeeAccountById		http://localhost:8080/account/payee/100
	/account/payee/{user_id}/{payee_id}		PATCH	- updateUserPayeeAccount		http://localhost:8080/account/payee/100/10
	/account/payee/{user_id}/{payee_id}		DELETE	- deleteUserPayeeAccountById	http://localhost:8080/account/payee/100/10
	/account/validation						POST	- accountNewPayeeValidation		http://localhost:8080/account/validation
	 * 
	See for logging
	https://stackoverflow.com/questions/33744875/spring-boot-how-to-log-all-requests-and-responses-with-exceptions-in-single-pl

 */
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllUserPaymentAccounts(@PathVariable("user_id") Integer userId){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "Metod getAllUserPaymentAccounts( Integer userId) NOT IMPLEMENTED YET" + 
				"\nGet ALL User's PAYMENT accounts by user Id" + "\n Parameters, user Id = " + userId;
		
		logger.info(info);
		Collection<?> accounts = accountService.getAll( userId );
		
		return new ResponseEntity<String>(info + "\n" + accounts.toString(), HttpStatus.I_AM_A_TEAPOT);
	}
	
}
