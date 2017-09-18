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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xapi.data.model.Payment;
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
	
	
	/*
	 * Example:
	 * METHOD: POST
	 * URL: http://localhost:10001/ipay/payment/calculation?calculatePayee=true 
	 * BODY: minimal
	 *     {
	 *     		"id": 3,
	 *     		"amount": 1000,
	 *     		"calculatedAmount": 0,
	 *     		"paymentCurrency": "GBP",
	 *     		"payeeCurrency": "EUR"
	 *     }
	 *     
	 *  IMMUTABLE ELEMENTS   
	 *     		"userId": 1000,
	 *     		"accountId": 10,
	 *     		"payeeId": 100,
	 * */
	@CrossOrigin
	@RequestMapping(value = "/calculation", method = RequestMethod.POST)
	public ResponseEntity<Payment> calculatePayment(@RequestBody Payment payment, 
			@RequestParam(value="calculatePayee", required=false) String calculatePayee){ 
		String info = "\nMetod calculatePayment( Object paymentDetails) NOT IMPLEMENTED YET" + 
				"\nPlace to calculate User's to be placed PAYMENT by payment details" + 
				"\n Parameters, payment = " + payment.toString();		
		logger.info(info);
		
		Boolean flag = calculatePayee != null? new Boolean( calculatePayee ): payment.getAmount() != null && payment.getAmount() > 0 ? true: false;
		payment = paymentService.calculate(payment, flag);		
		logger.info(payment.toString());
		
		return new ResponseEntity<Payment>(payment, HttpStatus.I_AM_A_TEAPOT);
	}
	
	/*
	 * Example:
	 * METHOD: POST
	 * URL: http://localhost:10001/ipay/payment/1000/11/101 
	 * BODY: minimal
	 *     {
	 *     		"amount": 1000,
	 *     		"paymentCurrency": "GBP",
	 *     		"payeeCurrency": "EUR"
	 *     }
	 * */
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}/{account_id}/{payee_id}", method = RequestMethod.POST)
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment,
			@PathVariable("user_id") Long userId, @PathVariable("account_id") Long accountId, @PathVariable("payee_id") Long payeeId){ 
		
		String info = "\nMetod createPayment( Long userId, Long accountId, Long payeeId, Object paymentDetails ) NOT IMPLEMENTED YET" + 
				"\n Parameters, payment = " + payment.toString() + ", userId = " + userId + ", accountId = " + accountId + ", payeeId = " + payeeId;		
		logger.info(info);
		
		Payment calculatedResult = paymentService.createPayment(userId, accountId, payeeId, payment);
//		Boolean calculatePayee = calculatedResult.getAmount() != null 
//				&& (calculatedResult.getCalculatedAmount() == null || calculatedResult.getCalculatedAmount().intValue() == 0 )? true: false;
//		calculatedResult = paymentService.calculate(payment, calculatePayee);	
		logger.info(calculatedResult.toString());
		
		return new ResponseEntity<Payment>(calculatedResult, HttpStatus.I_AM_A_TEAPOT);
	}
	
	/*
	 * Example:
	 * METHOD: POST
	 * URL: http://localhost:10001/ipay/payment
	 * BODY: minimal
	 *     {
	 *     		"id": 3
	 *     }
	 * */
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST) //value = "", 
	public ResponseEntity<Payment> placePayment(@RequestBody Payment paymentRef){ 
		String info = "\nMetod placePayment( Object payment) NOT IMPLEMENTED YET" + 
				"\nPlace to execute User's placed PAYMENT by payment object" + 
				"\n Parameters, payment = " + paymentRef.toString();
		
		logger.info(info);
		
		Payment payment = paymentService.placePayment(paymentRef);
		logger.info(payment.toString());
		
		return new ResponseEntity<Payment>( payment, HttpStatus.I_AM_A_TEAPOT);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Payment>> getUserPayments(@PathVariable("user_id") Long userId){ //, HttpServletRequest request
		String info = "\nMetod getUserPayments( Long userId) NOT IMPLEMENTED YET" + 
				"\nGet ALL User's placed PAYMENTS by user Id" + "\n Parameters, user Id = " + userId;
//				+ "\n" + "method = " + request.getMethod() +  ", URI - " + request.getRequestURI() + ", URL - " + request.getRequestURL() + ", -->" + RequestMethod.GET.toString();
		
		logger.info(info);
		
		Collection<Payment> placedPayments = paymentService.getAll( userId );
		logger.info( placedPayments.toString() );
		
		return new ResponseEntity<Collection<Payment>>(placedPayments, HttpStatus.I_AM_A_TEAPOT);
	}	
}
