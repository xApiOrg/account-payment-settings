package com.xapi.payment.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
		mockMvc.perform(MockMvcRequestBuilders.post("/payment")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\"id\" : \"1\" }") )
			.andDo( print() );
		
		mockMvc.perform(MockMvcRequestBuilders.get("/payment/1")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect( jsonPath("$", hasSize( 1 ) ) )
				.andExpect( jsonPath("$[ 0 ].id").exists() )
				.andExpect( jsonPath("$[ 0 ].id").value( 1 ) )
				.andExpect( status().isOk() )
				.andExpect( content().contentType(MediaType.APPLICATION_JSON_UTF8) )
				.andDo( print() );
		
		mockMvc.perform(MockMvcRequestBuilders.get("/payment/2")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect( content().contentType(MediaType.APPLICATION_JSON_UTF8) )
				.andExpect( status().is( 204 ) )
				.andDo( print() );
	}
	
	@Test
	public void verifyPlacePayment() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\" : \"1\" }") )
				.andDo( print() );		
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
