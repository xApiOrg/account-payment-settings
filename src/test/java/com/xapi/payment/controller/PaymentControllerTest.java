package com.xapi.payment.controller;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.xapi.AccountPaymentSettingsServerApplication;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AccountPaymentSettingsServerApplication.class)
@FixMethodOrder
public class PaymentControllerTest {
	private MockMvc mockMvc;
	
	@Autowired
    private WebApplicationContext wac;

	@Before
	public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void verifyGetUserPayments() throws Exception {
		
	}
	
	@Test
	public void verifyPlacePayment() throws Exception {
		
	}
	
	@Test
	public void verifyCancelPayment() throws Exception {
		
	}
	
	@Test
	public void verifyCreatePayment() throws Exception {
		
	}
	
	@Test
	public void verifyCalculatePayment() throws Exception {
		
	}
}
