package com.xapi.payment.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xapi.data.model.Account;
import com.xapi.data.model.Payee;
import com.xapi.data.model.Payment;
import com.xapi.data.model.User;
import com.xapi.data.repository.AccountRepository;
import com.xapi.data.repository.PayeeRepository;
import com.xapi.data.repository.PaymentRepository;
import com.xapi.data.repository.UserRepository;
import com.xapi.rate.service.RateService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentServiceTest {
	@Mock private PaymentRepository paymentRepository;
	@Mock private UserRepository userRepository;
	@Mock private AccountRepository accountRepository;
	@Mock private PayeeRepository payeeRepository;
	@Mock private RateService fxRateService; 
	
	@InjectMocks private PaymentServiceImpl paymentService;
	
	private static final List<Payment> PAYMENTS = new ArrayList<>();
	private static final Date NOW = new Date();
	private static final User TEST_USER = new User("Test User");
	private static final Account TEST_ACCOUNT = new Account(TEST_USER);
	private static final Payee TEST_PAYEE = new Payee(TEST_USER.getName() + " Payee");

	private static final Payment TEST_PAYMENT =  
			new Payment( TEST_USER, TEST_ACCOUNT, TEST_PAYEE);
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);	
		TEST_PAYMENT.setId( 1l );
		TEST_PAYMENT.setPaymentDate(new Date( NOW.getTime() + 86400000L ) );
		TEST_PAYMENT.setAmount( 500.00 );
		TEST_PAYMENT.setPaymentCurrency( "EUR" );
		TEST_PAYMENT.setPayeeCurrency( "GBP" );
		
		TEST_USER.setId( 1l );
		
		TEST_PAYEE.setId( 1l );
		
		TEST_ACCOUNT.setId( 1l );
		TEST_ACCOUNT.setBalance( 1000.00 );
		TEST_ACCOUNT.setOverDraft( 100.00 );
	}
	
	@Test
	public void testPlacePayment(){	
		TEST_PAYMENT.setPlaced( false );			
		TEST_PAYMENT.setSettled( false );		
		TEST_PAYMENT.setCancelled( false );

		when(paymentRepository.findById( TEST_PAYMENT.getId() )).thenReturn( TEST_PAYMENT );
		Payment resultPayment = paymentService.placePayment(TEST_PAYMENT);

		assertTrue(resultPayment.getPlaced());
		assertFalse(resultPayment.getSettled());
		assertFalse(resultPayment.getCancelled());
		
		assertTrue(resultPayment.getDatePlaced().after(NOW));
	}
	
	@Test
	public void testCancelPayment(){			
		TEST_PAYMENT.setPlaced( true );
		TEST_PAYMENT.setSettled( false );
			
		when(paymentRepository.findById( TEST_PAYMENT.getId() )).thenReturn( TEST_PAYMENT );
		Payment resultPayment = paymentService.cancelPayment(TEST_PAYMENT);
		
		assertEquals(true, resultPayment.getCancelled());
		assertEquals(false, resultPayment.getPlaced());

		assertTrue(resultPayment.getDateCancelled().after(NOW));
		assertTrue(resultPayment.getDatePlaced().after(NOW));
		assertTrue(resultPayment.getDatePlaced().equals(resultPayment.getDateCancelled()));
	}
	
	@Test
	public void testCalculate(){
		assertEquals(1,1);
	}
	
	@Test
	public void testCreatePaymentOverBalanceAndDraft(){
		TEST_PAYMENT.setAmount( 1500.00 );
		when(userRepository.findById( TEST_USER.getId() )).thenReturn( TEST_USER );
		when(accountRepository.findById( TEST_ACCOUNT.getId() )).thenReturn( TEST_ACCOUNT );
		when(payeeRepository.findPayeeByIdandUserId( TEST_PAYEE.getId(), TEST_USER.getId() ) ).thenReturn( TEST_PAYEE );
		when(fxRateService.getRate("EUR", "GBP")).thenReturn( 0.89283 );
		
		System.out.println(accountRepository.count());
		Payment paymentCreated = paymentService.createPayment( 1l, 1l, 1l, TEST_PAYMENT);
		assertEquals(null, paymentCreated.getId());
		assertEquals( new Double( 0.0 ) , paymentCreated.getCalculatedAmount()); 
				
		TEST_PAYMENT.setAmount( 0.00 );		
		paymentCreated = paymentService.createPayment( 1l, 1l, 1l, TEST_PAYMENT);
		assertEquals(null, paymentCreated.getId());
		assertTrue( paymentCreated.getCalculatedAmount() == 0.0 );
	}
	
	@Test
	public void testGetAllPlaced(){
		assertEquals(1,1);
	}
	
	@Test
	public void testCreatePaymentSave(){
		when(userRepository.findById( TEST_USER.getId() )).thenReturn( TEST_USER );
		when(accountRepository.findById( TEST_ACCOUNT.getId() )).thenReturn( TEST_ACCOUNT );
		when(payeeRepository.findPayeeByIdandUserId( TEST_PAYEE.getId(), TEST_USER.getId() ) ).thenReturn( TEST_PAYEE );
		when(fxRateService.getRate("EUR", "GBP")).thenReturn( 0.89283 );
		
		when(paymentRepository.save( any(Payment.class) )).thenAnswer(new Answer<Payment>(){
			@Override
			public Payment answer(InvocationOnMock invocation){
				Payment payment = invocation.getArgumentAt( 0, Payment.class);
					payment.setId( 1l );
				return payment;
			}
		});		
		
		TEST_PAYMENT.setAmount( 600.00 );
		Payment paymentCreated = paymentService.createPayment( 1l, 1l, 1l, TEST_PAYMENT);
		
		assertEquals(  new Long( 1 ) , paymentCreated.getId()); 
		assertTrue( paymentCreated.getCalculatedAmount() != 0.0 );
		assertTrue( paymentCreated.getAmount() != 0.0 );
	}
}
