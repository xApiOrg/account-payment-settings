package com.xapi.payment.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xapi.data.model.Account;
import com.xapi.data.model.Payee;
import com.xapi.data.model.Payment;
import com.xapi.data.model.User;
import com.xapi.data.repository.AccountRepository;
import com.xapi.data.repository.PayeeRepository;
import com.xapi.data.repository.PaymentRepository;
import com.xapi.data.repository.UserRepository;
import com.xapi.rate.service.FXRateService;
import com.xapi.rate.service.RateService;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.xapi.payment.config.ServiceConfig;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
//    @Autowired private ServiceConfig paymentConfig;

	@Autowired private PaymentRepository paymentRepository;
	@Autowired private UserRepository userRepository;
	@Autowired private AccountRepository accountRepository;
	@Autowired private PayeeRepository payeeRepository;
	@Autowired private RateService fxRateService; // = new FXRateService();
//    private final RestTemplate restTemplate = new RestTemplateBuilder().build(); // RestTemplate restTemplate = new RestTemplate();

	@Override
//	@HystrixCommand(fallbackMethod="getAllPaymentFallback")
	public Collection<Payment> getAllPlaced(Long userId) {
		List<Payment> payments = paymentRepository.findByUserIdAndPlaced(userId, true);
		return payments;
	}
	
//	@HystrixCommand
//	private Collection<?> getAllPaymentFallback(Integer userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
//	@HystrixCommand(fallbackMethod="updatePaymentFallback")
	public Payment updatePayment(Payment payment) {
		Payment paymentToUpdate = paymentRepository.findById(payment.getId());
		Date now = new Date();
		
		// N.B. FIXME, TODO To be analysed what elements and under what circumstances can be updated
		
		if(paymentToUpdate != null && ! paymentToUpdate.getCancelled()  && ! paymentToUpdate.getSettled() 
				&& paymentToUpdate.getPaymentDate().after(now) && paymentToUpdate.getPlaced()){
			paymentToUpdate.setCancelled(true);
			paymentToUpdate.setDateCancelled(now);
			
			paymentToUpdate.setPlaced( false );
			paymentToUpdate.setDatePlaced(now);
			
			paymentToUpdate.setPaymentDate(new Date( 0 ));
			paymentRepository.save(paymentToUpdate);			
		}
		
		return paymentToUpdate;
	}
	
//	@HystrixCommand
//	private Object updatePaymentFallback(Object payment) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
//	@HystrixCommand(fallbackMethod="cancelPaymentFallback")
	public Payment cancelPayment(Long id) {
		Payment payment = paymentRepository.findById(id);
		Date now = new Date();
		
		if(payment != null && ! payment.getCancelled()  && ! payment.getSettled() 
				&& payment.getPaymentDate().after(now) && payment.getPlaced()){
			payment.setCancelled(true);
			payment.setDateCancelled(now);
			
			payment.setPlaced( false );
			payment.setDatePlaced(now);
			
			payment.setPaymentDate(new Date( 0 ));
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
		double fxRate = fxRateService.getRate(payment.getPaymentCurrency(), payment.getPayeeCurrency());
		double charge = RateService.getCharge( payment.getPaymentCurrency(), payment.getPayeeCurrency(), 
			calculatePayee ? payment.getAmount(): payment.getCalculatedAmount());
		payment.setRate(fxRate);
		payment.setCharge(charge);
		
		Double amount = calculatePayee? payment.getAmount() * fxRate - charge: 
							payment.getCalculatedAmount()/fxRate + charge;
			amount = amount > payment.getAccount().getBalance() + payment.getAccount().getOverDraft()? 0: amount;
		
		payment.setDateCalculated(new Date());
		if(calculatePayee)
			payment.setCalculatedAmount(amount);
		else
			payment.setAmount(amount);
		
		return payment;
	}
	
	// SELECT u.id, a.id, up.payee_id from user u join account a on u.id = a.user_id join user_payee up on up.user_id = u.id
	@Override
	public Payment createPayment(Long userId, Long accountId, Long payeeId, Payment paymentTransferred){
		User user = userRepository.findById(userId);
		Account account = accountRepository.findById(accountId);
		Payee payee = payeeRepository.findPayeeByIdandUserId( payeeId, userId);
		Account userAccount = accountRepository.findByUserIdAndId(userId, accountId);
		
		Payment payment = new Payment(user, account, payee);
			payment.setAmount(paymentTransferred.getAmount()); 
			payment.setPaymentCurrency(paymentTransferred.getPaymentCurrency() == null || paymentTransferred.getPaymentCurrency().isEmpty() ?
					account.getCurrency(): paymentTransferred.getPaymentCurrency() );
			payment.setPayeeCurrency(paymentTransferred.getPayeeCurrency() == null || paymentTransferred.getPayeeCurrency().isEmpty() ? 
					payee.getAccountDetails().getBank().getCountry().getCurrency().getIso(): 
						paymentTransferred.getPayeeCurrency() );
			
		boolean calculatePayee = payment.getAmount() != null ? true: false;
//				&& (paymentTransferred.getCalculatedAmount() == null || calculatedResult.getCalculatedAmount().intValue() == 0 )? true: false;
		payment = recalculate(payment, calculatePayee);
		
		if(payment.getAmount() != null && payment.getAmount() > 0 && 
			payment.getCalculatedAmount() != null && payment.getCalculatedAmount() > 0)
				payment = paymentRepository.save(payment);
			
		return payment;
	}

	@Override
	public Collection<Payment> getAll() {
		return paymentRepository.findAll();
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
