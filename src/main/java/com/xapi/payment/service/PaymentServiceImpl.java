package com.xapi.payment.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapi.data.model.Payment;
import com.xapi.data.model.User;
import com.xapi.data.repository.PaymentRepository;
import com.xapi.data.repository.UserRepository;
import com.xapi.rate.service.FXRateService;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.xapi.payment.config.ServiceConfig;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
//    @Autowired private ServiceConfig paymentConfig;

	@Autowired private PaymentRepository paymentRepository;
	@Autowired private UserRepository userRepository;
//    private final RestTemplate restTemplate = new RestTemplateBuilder().build(); // RestTemplate restTemplate = new RestTemplate();

	@Override
//	@HystrixCommand(fallbackMethod="getAllPaymentFallback")
	public Collection<Payment> getAll(Long userId) {
		List<Payment> payments = paymentRepository.findByUserIdAndPlaced(userId, true);
		return payments;
	}
	
//	@HystrixCommand
//	private Collection<?> getAllPaymentFallback(Integer userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
//	@HystrixCommand(fallbackMethod="cancelPaymentFallback")
	public Payment cancelPayment(Payment payment) {
		payment = paymentRepository.findById(payment.getId());
		Date now = new Date();
		
		if(payment != null && ! payment.getSettled() && payment.getPaymentDate().after(now) ){
			payment.setCancelled(true);
			payment.setDateCancelled(now);
			paymentRepository.save(payment);			
		}
		
		return payment;
	}
	
//	@HystrixCommand
//	private Object cancelPaymentFallback(Object payment) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
//	@HystrixCommand(fallbackMethod="placePaymentFallback")
	public Payment placePayment(Payment payment) {
		payment = paymentRepository.findById(payment.getId());
		Date now = new Date();
		
		if(payment != null && ! payment.getPlaced()  && ! payment.getSettled() && ! payment.getCancelled() 
				&& payment.getPaymentDate().after(now) ){
			payment.setPlaced( true );
			payment.setDatePlaced(now);
			paymentRepository.save(payment);			
		}
		
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
		Payment calculatedPayment = paymentRepository.findById(payment.getId());// paymentRepository.getOne(payment.getId());// paymentRepository.findById(payment.getId())
		
		// TODO, FIXME Check for 0 amounts
		if(calculatedPayment != null){
			calculatedPayment.setAmount(payment.getAmount());
			calculatedPayment.setCalculatedAmount(payment.getCalculatedAmount());
			calculatedPayment.setPaymentCurrency(payment.getPaymentCurrency());
			calculatedPayment.setPayeeCurrency(payment.getPayeeCurrency());
		}else
			calculatedPayment = payment;
		

		calculatedPayment = recalculate(calculatedPayment, calculatePayee);
		paymentRepository.save(calculatedPayment);
		
		return calculatedPayment;
	}

//	@HystrixCommand
//	private Object calculatePaymentFallback(Object paymentPayeeAmounts) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	private Payment recalculate(Payment payment, Boolean calculatePayee){
		double fxRate = FXRateService.getRate(payment.getPaymentCurrency(), payment.getPayeeCurrency());
		double charge = FXRateService.getCharge( payment.getPaymentCurrency(), payment.getPayeeCurrency(), 
			calculatePayee ? payment.getAmount(): payment.getCalculatedAmount());
		payment.setRate(fxRate);
		payment.setCharge(charge);
		
		if(calculatePayee)
			payment.setCalculatedAmount(payment.getAmount() * fxRate - charge);
		else
			payment.setAmount(payment.getCalculatedAmount()/fxRate + charge);
		
		return payment;
	}
	
	@Override
	public Payment createPayment(Long userId, Long accountId, Long payeeId, Payment paymentTransferred){
		User user = userRepository.findById(userId);
		Payment payment = new Payment(user, accountId, payeeId);
			payment.setAmount(paymentTransferred.getAmount());
			payment.setPaymentCurrency(paymentTransferred.getPaymentCurrency());
			payment.setPayeeCurrency(paymentTransferred.getPayeeCurrency());
			
		boolean calculatePayee = payment.getAmount() != null ? true: false;
//				&& (paymentTransferred.getCalculatedAmount() == null || calculatedResult.getCalculatedAmount().intValue() == 0 )? true: false;
		payment = recalculate(payment, calculatePayee);
			
		paymentRepository.save(payment);
			
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
    "id": 13,
    "userId": 1000,
    "accountId": 12,
    "payeeId": 102,
    "created": 1505383141468,
    "amount": 2000,
    "paymentCurrency": "GBP",
    "rate": 1.1081,
    "charge": 0,
    "calculatedAmount": 2216.2000000000003,
    "payeeCurrency": "EUR",
    "placed": false,
    "datePlaced": 1505383141468,
    "cancelled": false,
    "dateCancelled": 1505383141468,
    "settled": false,
    "dateSettled": 1505383141468
}
 * */
