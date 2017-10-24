package com.xapi.payment.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
import com.xapi.data.model.User;
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
		/payment/{user_id}		GET		- getUserPayments	List recent placed payments							http://localhost:8080/ipay/payment/100
		
		NEW!!! Create and calculate (recalculate) payment Object to ease re calculation and payment confirmation 
		
		/payment/{user_id}/{account_id}/{payee_id}		POST	createPayment					http://localhost:8080/ipay/payment/1/1/1 
																					MINIMAL		{ "amount": 555.00 }
																					OPTIONAL	{ "amount": 1000, "paymentCurrency": "GBP", "payeeCurrency": "EUR" }
																					
		/payment/{user_id}/{account_id}/{payee_id}		POST	create Quick Payment			http://localhost:8080/ipay/payment/1/1/1/555.00
																								No POST body or element. Payment with default elements
																								
		/payment										PATCH	cancelPayment					http://localhost:8080/ipay/payment
																					MINIMAL		{ "id": 1 }
																or
		/payment/{id}									PATCH	cancelPayment					http://localhost:8080/ipay/payment/1
			

	 */	
	
	
	/*
	 * Example:
	 * METHOD: POST
	 * URL: http://localhost:10001/ipay/payment/calculation?calculatePayee=true 
	 * BODY: minimal
	      {
	      		"id": 3,
	      		"amount": 1000,
	      		"calculatedAmount": 0,
	      		"paymentCurrency": "GBP",
	      		"payeeCurrency": "EUR"
	      }
	      
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
			{
				"amount": 1000,
				"paymentCurrency": "GBP",
				"payeeCurrency": "EUR"
			}
			
		paymentCurrency overrides account's currency
		payeeCurrency overrides payee's country currency
	 * */
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}/{account_id}/{payee_id}", method = RequestMethod.POST)
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment,
			@PathVariable("user_id") Long userId, @PathVariable("account_id") Long accountId, @PathVariable("payee_id") Long payeeId){ 
		
		String info = "\nMetod createPayment( Long userId, Long accountId, Long payeeId, Object paymentDetails )" + // "\nNOT IMPLEMENTED YET" + 
				"\n Parameters, payment = " + payment.toString() + ", userId = " + userId + ", accountId = " + accountId + ", payeeId = " + payeeId;		
		logger.info(info);
		
		Payment calculatedResult = paymentService.createPayment(userId, accountId, payeeId, payment);
		logger.info(calculatedResult != null && calculatedResult.getId() != null &&
				(calculatedResult.getAmount() == 0.0 || calculatedResult.getCalculatedAmount() == 0.0)? calculatedResult.toString():
				"Payment NOT CREATED!!!\n" + calculatedResult.toString());
		
		return new ResponseEntity<Payment>(calculatedResult, 
				calculatedResult != null && calculatedResult.getId() != null? HttpStatus.OK: HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	/*
	 * Example:
	 * METHOD: POST
	 * URL: http://localhost:10001/ipay/payment/2/7/8/500.00 
	 * BODY: NO BODY, QUICK Payment forming with default elements.
	 * */
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}/{account_id}/{payee_id}/{amount}", method = RequestMethod.POST)
	public ResponseEntity<Payment> createPayment( @PathVariable("user_id") Long userId, @PathVariable("account_id") Long accountId, 
			@PathVariable("payee_id") Long payeeId, @PathVariable("amount") Double amount ){ 
		
		Payment ammountPaymentDto = new Payment();
			ammountPaymentDto.setAmount(amount);
			
		String info = "\nMetod createPayment( Long userId, Long accountId, Long payeeId, Double amount )" + // "\nNOT IMPLEMENTED YET" + 
				"\n Parameters, amount = " + amount.toString() + ", userId = " + userId + ", accountId = " + accountId + ", payeeId = " + payeeId;		
		logger.info(info);
		
		Payment calculatedResult = paymentService.createPayment(userId, accountId, payeeId, ammountPaymentDto);
		logger.info(calculatedResult != null && calculatedResult.getId() != null &&
				(calculatedResult.getAmount() == 0.0 || calculatedResult.getCalculatedAmount() == 0.0)? calculatedResult.toString():
				"Payment NOT CREATED!!!\n" + calculatedResult.toString());
		
		return new ResponseEntity<Payment>(calculatedResult, 
				calculatedResult != null && calculatedResult.getId() != null? HttpStatus.OK: HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	/*
	 * Example:
	 * METHOD: PATCH
	 * URL: http://localhost:10001/ipay/payment
	 * BODY: minimal
			{
				"id": 6
			}
	 * */
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PATCH)
	public ResponseEntity<Payment> cancelPayment(@RequestBody Payment paymentRef){ 
		String info = "\nMetod cancelPayment( JSONObject payment)  NOT IMPLEMENTED YET" + 
				// "\nPlace to execute User's placed PAYMENT by payment object" + 
				"\nParameters, payment:\n" + paymentRef.toString();		
		logger.info(info);
		
		Payment payment = paymentService.cancelPayment(paymentRef.getId());
		logger.info(payment != null && payment.getCancelled()? payment.toString(): 
			"\nNOT CANCELLED Payment:\n" + paymentRef.toString());
		
		return new ResponseEntity<Payment>( payment, payment == null? HttpStatus.NOT_FOUND: 
			payment.getCancelled()? HttpStatus.OK: HttpStatus.NOT_MODIFIED);
	}
	
	/*
	 * Cancel payment. No body. The above functionality is more appropriate for UPDATE payment
	 * Example:
	 * METHOD: PATCH
	 * URL: http://localhost:10001/ipay/payment/6
	 * BODY: NO BODY
	 * */
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH) //value = "", 
	public ResponseEntity<Payment> cancelPayment(@PathVariable("user_id") Long id){ 			
		String info = "\nMetod cancelPayment( Long id )  NOT IMPLEMENTED YET" + 
				// "\nPlace to execute User's placed PAYMENT by payment object" + 
				"\nParameters, id: " + id;		
		logger.info(info);
		
		Payment payment = paymentService.cancelPayment(id);
		logger.info(payment != null && payment.getCancelled()? payment.toString(): 
			"\nNOT CANCELLED Payment Id:" + id);
		
		return new ResponseEntity<Payment>( payment, payment == null? HttpStatus.NOT_FOUND: 
			payment.getCancelled()? HttpStatus.OK: HttpStatus.NOT_MODIFIED);
	}
	
	/*
	 * Example:
	 * METHOD: POST
	 * URL: http://localhost:10001/ipay/payment
	 * BODY: minimal
			{
				"id": 6
			}
	 * */
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST) //value = "", 
	public ResponseEntity<Payment> placePayment(@RequestBody Payment paymentRef){ 
		String info = "\nMetod placePayment( JSONObject payment) // NOT IMPLEMENTED YET" + 
				// "\nPlace to execute User's placed PAYMENT by payment object" + 
				"\n Parameters, payment = " + paymentRef.toString();		
		logger.info(info);
		
		Payment payment = paymentService.placePayment(paymentRef);
		logger.info(payment != null && payment.getPlaced()? payment.toString(): 
			"NOT PLACED/CONFIRMED Payment " + paymentRef.toString());
		
		return new ResponseEntity<Payment>( payment, payment == null? HttpStatus.NOT_FOUND: 
			payment.getPlaced()? HttpStatus.OK: HttpStatus.NOT_MODIFIED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET)
	public ResponseEntity<Collection<Payment>> getUserPayments(@PathVariable("user_id") Long userId){ 
		String info = "\nMetod getUserPayments( Long userId )" + //" NOT IMPLEMENTED YET" + 
				"\nGet ALL User's placed PAYMENTS by user Id" + "\n Parameters, user Id = " + userId;
		logger.info(info);
		
		Collection<Payment> placedPayments = paymentService.getAllPlaced( userId );
		logger.info( placedPayments != null && ! placedPayments.isEmpty()? placedPayments.toString(): 
				"NO PLACED PAYMENTS FOR USER ID " + userId );		
		
		return new ResponseEntity<Collection<Payment>>(placedPayments, 
				placedPayments != null && ! placedPayments.isEmpty()? HttpStatus.OK: HttpStatus.NO_CONTENT);
	}	
}

