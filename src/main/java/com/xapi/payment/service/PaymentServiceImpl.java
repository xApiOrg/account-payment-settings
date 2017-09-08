package com.xapi.payment.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.xapi.payment.config.ServiceConfig;

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
//    @Autowired private ServiceConfig paymentConfig;

    private final RestTemplate restTemplate = new RestTemplateBuilder().build(); // RestTemplate restTemplate = new RestTemplate();

	@Override
//	@HystrixCommand(fallbackMethod="getAllPaymentFallback")
	public Collection<?> getAll(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@HystrixCommand
//	private Collection<?> getAllPaymentFallback(Integer userId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
//	@HystrixCommand(fallbackMethod="placePaymentFallback")
	public Object placePayment(Object payment) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	@HystrixCommand
//	private Object placePaymentFallback(Object payment) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
//	@HystrixCommand(fallbackMethod="calculatePaymentFallback")
	public Object calculate(Object paymentPayeeAmounts) {
		// TODO Auto-generated method stub
		return null;
	}

//	@HystrixCommand
//	private Object calculatePaymentFallback(Object paymentPayeeAmounts) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public Object createPayment(Long userId, Long accountId, Long payeeId, Object paymentPayeeAmounts){
		// TODO Auto-generated method stub
		return null;
	}
	
}
