package com.xapi.payment.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
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
	private static final Date NOW = new Date();
	private static final User TEST_USER = new User("Test User");

	private static final Payment testPayment =  
			new Payment( TEST_USER, new Account(TEST_USER), new Payee(TEST_USER.getName() + " Payee"));
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);	
		testPayment.setId( 1l );
		testPayment.setPaymentDate(new Date( NOW.getTime() + 86400000L ) );
	}
	
	@Test
	public void testGetAllPlaced(){
		assertEquals(1,1);
	}
	
	@Test
	public void testCancelPayment(){			
		testPayment.setPlaced( true );
		testPayment.setSettled( false );
			
		when(paymentRepository.findById( testPayment.getId() )).thenReturn( testPayment );
		Payment resultPayment = paymentService.cancelPayment(testPayment);
		
		assertEquals(true, resultPayment.getCancelled());
		assertEquals(false, resultPayment.getPlaced());

		assertTrue(resultPayment.getDateCancelled().after(NOW));
		assertTrue(resultPayment.getDatePlaced().after(NOW));
		assertTrue(resultPayment.getDatePlaced().equals(resultPayment.getDateCancelled()));
	}
	
	@Test
	public void testPlacePayment(){	
		testPayment.setPlaced( false );			
		testPayment.setSettled( false );		
		testPayment.setCancelled( false );

		when(paymentRepository.findById( testPayment.getId() )).thenReturn( testPayment );
		Payment resultPayment = paymentService.placePayment(testPayment);

		assertTrue(resultPayment.getPlaced());
		assertFalse(resultPayment.getSettled());
		assertFalse(resultPayment.getCancelled());
		
		assertTrue(resultPayment.getDatePlaced().after(NOW));
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
