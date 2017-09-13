package com.xapi.payment.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

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

import com.xapi.payment.model.Payment;
import com.xapi.payment.service.PaymentService;


@RestController
@RequestMapping("/payment")
public class PaymentController {
	public static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired PaymentService paymentService;
	/**
	 * 
		/payment/calculation	POST	- calculatePayment	Calculate (recalculate) payment and payee amounts	http://localhost:8080/ipay/payment/calculation
		/payment				POST	- placePayment		Place payment										http://localhost:8080/ipay/payment
		/payment/{user_id}		GET		- getUserPayments	List recent payments								http://localhost:8080/ipay/payment/100
		
		
		/payment//{user_id}/{account_id}/{payee_id}	POST	createPayment	
			NEW!!! Create and calculate (recalculate) payment Object to ease re calculation an payment confirmation 

	 */	
	
	@CrossOrigin
	@RequestMapping(value = "/calculation", method = RequestMethod.POST)
	public ResponseEntity<Payment> calculatePayment(@RequestBody Payment payment){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "Metod calculatePayment( Object paymentDetails) NOT IMPLEMENTED YET" + 
				"\nPlace to calculate User's to be placed PAYMENT by payment details" + "\n Parameters, payment = " + payment.toString();		
		logger.info(info);
		
		Boolean calculatePayee = payment.getAmount() != null 
				&& (payment.getCalculatedAmount() == null || payment.getCalculatedAmount().intValue() == 0 )? false: true;
		Payment calculatedResult = paymentService.calculate(payment, calculatePayee);		
		logger.info(calculatedResult.toString());
		
		return new ResponseEntity<Payment>(calculatedResult, HttpStatus.I_AM_A_TEAPOT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}/{account_id}/{payee_id}", method = RequestMethod.POST)
	public ResponseEntity<?> createPayment(@RequestBody Object payment,
			@PathVariable("user_id") Long userId, @PathVariable("account_id") Long accountId, @PathVariable("payee_id") Long payeeId){ //ResponseEntity<Collection<PaymentAccounts>>
		
		String info = "Metod createPayment( Long userId, Long accountId, Long payeeId, Object paymentDetails ) NOT IMPLEMENTED YET" + 
				"\nPlace to calculate User's to be placed PAYMENT by payment details" + "\n Parameters, payment = " + payment.toString() 
				+ ", userId = " + userId + ", accountId = " + accountId + ", payeeId = " + payeeId;		
		logger.info(info);
		
		Object calculatedResult = paymentService.createPayment(userId, accountId, payeeId, payment);
		return new ResponseEntity<Object>(info + "\n" + calculatedResult.toString(), HttpStatus.I_AM_A_TEAPOT);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST) //value = "", 
	public ResponseEntity<Payment> placePayment(@RequestBody Payment payment){ //ResponseEntity<Collection<PaymentAccounts>>
		String info = "Metod placePayment( Object payment) NOT IMPLEMENTED YET" + 
				"\nPlace to execute User's placed PAYMENT by payment object" + "\n Parameters, payment = " + payment.toString();
		
		logger.info(info);
		
		Payment paymentReference = paymentService.placePayment(payment);
		logger.info(paymentReference.toString());
		
		return new ResponseEntity<Payment>( paymentReference, HttpStatus.I_AM_A_TEAPOT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Payment>> getUserPayments(@PathVariable("user_id") Long userId){ //ResponseEntity<Collection<PaymentAccounts>> //, HttpServletRequest request
		String info = "\nMetod getUserPayments( Long userId) NOT IMPLEMENTED YET" + 
				"\nGet ALL User's placed PAYMENTS by user Id" + "\n Parameters, user Id = " + userId;
//				+ "\n" + "method = " + request.getMethod() +  ", URI - " + request.getRequestURI() + ", URL - " + request.getRequestURL() + ", -->" + RequestMethod.GET.toString();
		
		logger.info(info);
		
		Collection<Payment> placedPayments = paymentService.getAll( userId );
		logger.info( placedPayments.toString() );
		return new ResponseEntity<Collection<Payment>>(placedPayments, HttpStatus.OK);
	}	
}
