package com.xapi.payment.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.xapi.payment.model.Payment;
import com.xapi.payment.model.PaymentRepository;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.xapi.payment.config.ServiceConfig;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
//    @Autowired private ServiceConfig paymentConfig;
	
	@Autowired private PaymentRepository paymentRepository;
    private final RestTemplate restTemplate = new RestTemplateBuilder().build(); // RestTemplate restTemplate = new RestTemplate();

	@Override
//	@HystrixCommand(fallbackMethod="getAllPaymentFallback")
	public Collection<Payment> getAll(Long userId) {
		List<Payment> payments = paymentRepository.findByUserId(userId);
		return payments;
	}
	
//	@HystrixCommand
//	private Collection<?> getAllPaymentFallback(Integer userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
//	@HystrixCommand(fallbackMethod="placePaymentFallback")
	public Payment placePayment(Payment payment) {
		payment.setPlaced( true );
		paymentRepository.saveAndFlush(payment);
		return payment;
	}
	
//	@HystrixCommand
//	private Object placePaymentFallback(Object payment) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
//	@HystrixCommand(fallbackMethod="calculatePaymentFallback")
	public Payment calculate(Payment payment, Boolean calculatePayee) {
		// TODO Auto-generated method stub
		return payment;
	}

//	@HystrixCommand
//	private Object calculatePaymentFallback(Object paymentPayeeAmounts) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public Payment createPayment(Long userId, Long accountId, Long payeeId, Object paymentPayeeAmounts){
		Payment payment = new Payment(userId, accountId, payeeId);
			paymentRepository.saveAndFlush(payment);
			
		return payment;
	}
	
	@Override
	public Payment createPayment(Payment payment){
		paymentRepository.saveAndFlush(payment);
		return payment;
	}	

	@Override
	public List<Payment> findByUserId(Long userId){
		return paymentRepository.findByUserId(userId);
	}	
}

/*
{
    "base": "EUR",
    "date": "2017-09-12",
    "rates": {
        "AUD": 1.4847,
        "BGN": 1.9558,
        "BRL": 3.7117,
        "CAD": 1.4477,
        "CHF": 1.1444,
        "CNY": 7.8024,
        "CZK": 26.105,
        "DKK": 7.44,
        "GBP": 0.89878,
        "HKD": 9.3235,
        "HRK": 7.4513,
        "HUF": 307.11,
        "IDR": 15752,
        "ILS": 4.2197,
        "INR": 76.438,
        "JPY": 130.93,
        "KRW": 1346,
        "MXN": 21.13,
        "MYR": 5.012,
        "NOK": 9.3593,
        "NZD": 1.6343,
        "PHP": 60.765,
        "PLN": 4.2549,
        "RON": 4.6018,
        "RUB": 68.384,
        "SEK": 9.5355,
        "SGD": 1.6074,
        "THB": 39.522,
        "TRY": 4.0948,
        "USD": 1.1933,
        "ZAR": 15.48
    }
} 
 * */
