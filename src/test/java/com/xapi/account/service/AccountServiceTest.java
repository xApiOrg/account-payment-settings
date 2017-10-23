package com.xapi.account.service;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xapi.data.model.Account;
import com.xapi.data.repository.AccountRepository;
import com.xapi.data.repository.PayeeRepository;
import com.xapi.data.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {	
	@Mock private UserRepository userRepository;
	@Mock private PayeeRepository payeeRepository;
	@Mock private AccountRepository accountRepository;
	
	@InjectMocks private AccountServiceImpl accountService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetAll() {
//		fail("Not yet implemented"); // TODO
		List<Account> accounts = accountService.getAll( 1l );
		System.out.println(accounts.size());
	}

	@Test
	public final void testGetAccountById() {
		System.out.println(accountRepository.count());
		
		Account account = accountService.getAccountById( 1L );

//		System.out.println(account.toString());
	}

//	@Test
//	public final void testGetUserAccountById() {
//		fail("Not yet implemented"); // TODO
//	}

//	@Test
//	public final void testGetAllPayableAccounts() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testGetUserPayeeAccounts() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testGetPayeeByIdAndUserId() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testCreateNewPayee() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testUpdatePayee() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testDeletePayee() {
//		fail("Not yet implemented"); // TODO
//	}
//
//	@Test
//	public final void testValidateAccount() {
//		fail("Not yet implemented"); // TODO
//	}

}
