package com.xapi.payment.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
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
		Date now = new Date();
		mockMvc.perform(MockMvcRequestBuilders.post("/payment")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"id\" : \"1\" }") )
		.andExpect( jsonPath( "$.id").exists() )
		.andExpect( jsonPath( "$.id").value( 1 ) )
		.andExpect( jsonPath( "$.placed").exists() )
		.andExpect( jsonPath( "$.placed").value( true ) )
		.andExpect( jsonPath( "$.datePlaced").exists() )
//		.andExpect( jsonPath( "$.datePlaced").value(new BaseMatcher<Date>(){
//			@Override public boolean matches(Object datePlaced) {
////				return (new Date( (long) datePlaced) ).after(now);
//				return ( (long) datePlaced > now.getTime() );
//			}
//
//			@Override public void describeTo(Description description) {}
//		} ) )
				.andDo( print() );		
	}
	
	@Test
	public void verifyCancelPayment() throws Exception {
		
	}
	
	@Test
	public void verifyCreatePayment() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/payment/2/7/8")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"amount\": 500,\"paymentCurrency\": \"GBP\",\"payeeCurrency\": \"EUR\"}") )
		.andExpect( jsonPath( "$.id").exists() )
		.andExpect( jsonPath( "$.id").value( 2 ) )
		.andExpect( jsonPath( "$.amount").exists() )
		.andExpect( jsonPath( "$.amount").value( 500.0 ) )
		.andExpect( jsonPath( "$.paymentCurrency").exists() )
		.andExpect( jsonPath( "$.paymentCurrency").value( "GBP" ) )
		.andExpect( jsonPath( "$.payeeCurrency").exists() )
		.andExpect( jsonPath( "$.payeeCurrency").value( "EUR" ) )
		.andExpect( jsonPath( "$.placed").exists() )
		.andExpect( jsonPath( "$.placed").value( false ) )
		.andExpect( jsonPath( "$.cancelled").exists() )
		.andExpect( jsonPath( "$.cancelled").value( false ) )
		.andExpect( jsonPath( "$.settled").exists() )
		.andExpect( jsonPath( "$.settled").value( false ) )
		.andExpect( jsonPath( "$.user.id").exists() )
		.andExpect( jsonPath( "$.user.id").value( 2 ) )
		.andExpect( jsonPath( "$.account.id").exists() )
		.andExpect( jsonPath( "$.account.id").value( 7 ) )
		.andExpect( jsonPath( "$.payee.id").exists() )
		.andExpect( jsonPath( "$.payee.id").value( 8 ) )
				.andDo( print() );
	}
	
	@Test
	public void verifyCalculatePayment() throws Exception {
		
	}
}
