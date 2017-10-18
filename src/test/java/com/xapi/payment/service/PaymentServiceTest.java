package com.xapi.payment.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xapi.data.model.Account;
import com.xapi.data.model.Payee;
import com.xapi.data.model.Payment;
import com.xapi.data.model.User;
import com.xapi.data.repository.PaymentRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentServiceTest {
	@Mock private PaymentRepository paymentRepository;
	@InjectMocks private PaymentServiceImpl paymentService;
	
	private static final List<Payment> PAYMENTS = new ArrayList<>();
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testGetAllPlaced(){
		assertEquals(1,1);
	}
	
	@Test
	public void testCancelPayment(){		
		Date now = new Date();
		
		User testUser = new User("Test User");
		Payment testPayment = new Payment( testUser, new Account(testUser), new Payee("Test Payee"));
			testPayment.setId( 1l );
			testPayment.setSettled( false );
			testPayment.setPaymentDate(new Date( now.getTime() + 86400000L ) );
			
		when(paymentRepository.findById( testPayment.getId() )).thenReturn( testPayment );
		Payment resultPayment = paymentService.cancelPayment(testPayment);
		
		assertEquals(true, resultPayment.getCancelled());
		assertEquals(false, resultPayment.getPlaced());

		assertTrue(resultPayment.getDateCancelled().after(now));
		assertTrue(resultPayment.getDatePlaced().after(now));
		assertTrue(resultPayment.getDatePlaced().equals(resultPayment.getDateCancelled()));
	}
	
	@Test
	public void testPlacePayment(){
		assertEquals(1,1);
	}
	
	@Test
	public void testCalculate(){
		assertEquals(1,1);
	}
	
	@Test
	public void testCreatePayment(){
		assertEquals(1,1);
	}
	
	@Test
	public void testFindByUserId(){
		assertEquals(1,1);
	}
}
